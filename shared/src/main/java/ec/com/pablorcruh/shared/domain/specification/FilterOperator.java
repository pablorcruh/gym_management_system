package ec.com.pablorcruh.shared.domain.specification;

import ec.com.pablorcruh.shared.domain.models.EnumBase;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class FilterOperator extends EnumBase {

    private String expression;

    private static FilterOperator equal = new FilterOperator(1,"=", "=");
    private static FilterOperator notEqual = new FilterOperator(2,"!=","!=");
    private static FilterOperator greaterThanOrEqual = new FilterOperator(3,">=",">=");
    private static FilterOperator lessThanOrEqual = new FilterOperator(4,"<=","<=");
    private static FilterOperator greaterThan = new FilterOperator(3,">",">");
    private static FilterOperator lessThan = new FilterOperator(4,"<","<");
    private static FilterOperator contains = new FilterOperator(5,"contains","like");
    private static FilterOperator notContains = new FilterOperator(6,"notContains","not like");
    private static FilterOperator in = new FilterOperator(7,"in","in");

    public FilterOperator(Integer id, String name, String expression) {
        this.setId(id);
        this.setName(name);
        this.expression = expression;
    }

    public static List<FilterOperator> list(){
        List<FilterOperator> iterables = new ArrayList<>();
        iterables.add(equal);
        iterables.add(notEqual);
        iterables.add(greaterThanOrEqual);
        iterables.add(lessThanOrEqual);
        iterables.add(greaterThan);
        iterables.add(lessThan);
        iterables.add(contains);
        iterables.add(notContains);
        iterables.add(in);
        return iterables;
    }

    public static FilterOperator fromName(String name){
        return list().stream()
                .filter(filterOperator -> filterOperator.getName().equals(name))
                .findAny()
                .orElse(null);
    }

    public static FilterOperator from(Integer id){
        return list().stream()
                .filter(filterOperator -> filterOperator.getId().equals(id))
                .findAny()
                .orElse(null);
    }
}

