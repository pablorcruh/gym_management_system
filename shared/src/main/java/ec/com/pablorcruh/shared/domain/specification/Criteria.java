package ec.com.pablorcruh.shared.domain.specification;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
public class Criteria {

    private List<Specification> specifications;
    private List<Order> orders;
    @Setter
    private Integer page;
    @Setter
    private Integer pageSize;

    @Setter
    private Boolean group;

    public Criteria(List<Filter> filters, List<OrderBy> orderByList, Integer page, Integer pageSize) {
        this.specifications = getSpecifications(filters);
        this.orders = getOrders(orderByList);
        this.page = page != null ? page : 0;
        this.pageSize = pageSize != null ? pageSize : Integer.MAX_VALUE;
        this.group=false;
    }

    public Criteria(List<Filter> filters, List<OrderBy> orderByList, Integer page, Integer pageSize,Boolean group) {
        this.specifications = getSpecifications(filters);
        this.orders = getOrders(orderByList);
        this.page = page != null ? page : 0;
        this.pageSize = pageSize != null ? pageSize : Integer.MAX_VALUE;
        this.group=group;
    }

    private List<Specification> getSpecifications(List<Filter> filters) {
        List<Specification> specificationsList = new ArrayList<>();

        filters.forEach(filter -> {
            Specification specification =
                    new Specification(
                            new FilterField(filter.getField()),
                            filter.getOperator(),
                            filter.getComparer(),
                            filter.getValue()
                    );
            specificationsList.add(specification);
        });
        return specificationsList;
    }

    private List<Order> getOrders(List<OrderBy> orderByList) {
        List<Order> list = new ArrayList<>();
        orderByList.forEach(orderBy ->
                list.add(new Order(new OrderField(orderBy.getField()), orderBy.getOrderType()))
        );
        return list;
    }
}
