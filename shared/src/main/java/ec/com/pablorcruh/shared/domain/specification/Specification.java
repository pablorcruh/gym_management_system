package ec.com.pablorcruh.shared.domain.specification;

import lombok.Getter;

@Getter
public class Specification {
    private FilterField filterField;
    private FilterOperator operator;
    private FilterComparer comparer;
    private Object value;

    public Specification(FilterField filterField, FilterOperator operator, FilterComparer comparer, Object value) {
        this.filterField = filterField;
        this.operator = operator;
        this.comparer = comparer;
        this.value = value;
    }
}
