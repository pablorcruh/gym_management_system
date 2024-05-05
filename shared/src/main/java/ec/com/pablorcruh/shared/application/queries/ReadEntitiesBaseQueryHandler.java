package ec.com.pablorcruh.shared.application.queries;

import ec.com.pablorcruh.shared.application.dto.PaginationResponse;
import ec.com.pablorcruh.shared.application.dto.response.GetEntitiesResponse;
import ec.com.pablorcruh.shared.domain.specification.Criteria;
import ec.com.pablorcruh.shared.domain.specification.ISpecificationBaseRepository;
import io.jkratz.mediator.core.RequestHandler;
import jakarta.validation.constraints.NotNull;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReadEntitiesBaseQueryHandler<T> implements RequestHandler<ReadEntitiesBaseQuery, GetEntitiesResponse> {

    private ISpecificationBaseRepository<T> repository = null;

    public ReadEntitiesBaseQueryHandler() {
    }

    public ReadEntitiesBaseQueryHandler(ISpecificationBaseRepository<T> repossitory) {
        this.repository = repository;
    }

    @SneakyThrows
    private GetEntitiesResponse getPaginatedEntities(ReadEntitiesBaseQuery query, Integer totalAmount, Criteria criteria){

        if (query.getPageSize() == null || query.getPage() == null || totalAmount <= query.getPageSize()) {
            criteria.setPage(0);
            criteria.setPageSize(totalAmount);
        }else if (query.getPage() >= totalAmount / query.getPageSize()) {
            throw new Exception("The page number is incorrect");
        }

        List<T> entitiesList = repository.find(criteria);
        PaginationResponse paginationResponse = new PaginationResponse(criteria.getPage(),
                criteria.getPageSize(), totalAmount);

        return new GetEntitiesResponse(entitiesList, paginationResponse);
    }

    @SneakyThrows
    @Override
    public GetEntitiesResponse handle(@NotNull ReadEntitiesBaseQuery query) {
        if (query.getPage() == null) {
            query.setPage(0);
        }
        if (query.getPageSize() == null) {
            query.setPageSize(Integer.MAX_VALUE);
        }
        Criteria criteria = new Criteria(query.getFilters(),
                query.getOrders(),
                Integer.valueOf(query.getPage().toString()),
                Integer.valueOf(query.getPageSize().toString()));

        Integer totalAmount = Math.toIntExact(repository.count(criteria));
        if (totalAmount == 0) {
            return new GetEntitiesResponse(new ArrayList<>(), new PaginationResponse(0, 0, 0));
        }
        return getPaginatedEntities(query, totalAmount, criteria);
    }

}
