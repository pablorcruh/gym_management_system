package ec.com.pablorcruh.shared.application.queries;

import ec.com.pablorcruh.shared.application.dto.response.GetEntitiesResponse;
import ec.com.pablorcruh.shared.domain.specification.Filter;
import ec.com.pablorcruh.shared.domain.specification.OrderBy;
import io.jkratz.mediator.core.Request;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ReadEntitiesBaseQuery implements Request<GetEntitiesResponse> {
    private List<OrderBy> orders;
    private List<Filter> filters;
    private Integer page;
    private Integer pageSize;

    public ReadEntitiesBaseQuery(List<OrderBy> orders, List<Filter> filters, Integer page, Integer pageSize) {
        this.orders = orders;
        this.filters = filters;
        this.page = page;
        this.pageSize = pageSize;
    }
}
