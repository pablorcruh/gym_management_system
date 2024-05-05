package ec.com.pablorcruh.shared.domain.specification;

import ec.com.pablorcruh.shared.domain.models.EnumBase;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class OrderType extends EnumBase {
    private String expression;

    private static OrderType asc = new OrderType(1,"asc","asc");
    private static OrderType desc = new OrderType(2,"desc","desc");

    public OrderType(Integer id, String name, String expression) {
        this.setId(id);
        this.setName(name);
        this.expression = expression;
    }

    public static List<OrderType> list(){
        List<OrderType> iterables = new ArrayList<>();
        iterables.add(asc);
        iterables.add(desc);
        return iterables;
    }

    public static OrderType fromName(String name){
        return list().stream()
                .filter(filterOperator -> filterOperator.getExpression().equals(name))
                .findAny()
                .orElse(null);
    }

    public static OrderType from(Integer id){
        return list().stream()
                .filter(filterOperator -> filterOperator.getId().equals(id))
                .findAny()
                .orElse(null);
    }
}
