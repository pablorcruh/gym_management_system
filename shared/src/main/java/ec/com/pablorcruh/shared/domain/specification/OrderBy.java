package ec.com.pablorcruh.shared.domain.specification;


import lombok.Getter;

@Getter
public class OrderBy {

    private String field;
    private OrderType orderType;

    public OrderBy(String field, OrderType orderType) {
        this.field = field;
        this.orderType = orderType;
    }
}
