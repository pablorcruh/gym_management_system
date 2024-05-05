package ec.com.pablorcruh.shared.domain.specification;

import lombok.Getter;

@Getter
public class Order {
    private final OrderField orderField;
    private final OrderType orderType ;

    public Order(OrderField orderField, OrderType orderType) {
        this.orderField = orderField;
        this.orderType = orderType;
    }
}
