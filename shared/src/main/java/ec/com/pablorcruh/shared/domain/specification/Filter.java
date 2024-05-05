package ec.com.pablorcruh.shared.domain.specification;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Filter {

    @Setter
    private String field;
    private FilterOperator operator;
    private FilterComparer comparer;
    private Object value;

    public Filter(String field, FilterOperator operator, FilterComparer comparer, Object value) {
        this.field = field;
        this.operator = operator;
        this.comparer = comparer;
        this.value = value;
    }
}
