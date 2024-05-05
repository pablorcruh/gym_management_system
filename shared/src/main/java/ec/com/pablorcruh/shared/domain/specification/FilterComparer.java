package ec.com.pablorcruh.shared.domain.specification;

import ec.com.pablorcruh.shared.domain.models.EnumBase;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class FilterComparer extends EnumBase {

    private String expression;
    private static FilterComparer and = new FilterComparer(1, "AND", "and");
    private static FilterComparer or = new FilterComparer(2, "OR", "or");

    public FilterComparer(Integer id, String name, String expression) {
        this.setId(id);
        this.setName(name);
        this.expression = expression;
    }

    public static List<FilterComparer> list() {
        List<FilterComparer> iterables = new ArrayList<>();
        iterables.add(and);
        iterables.add(or);
        return iterables;
    }

    public static FilterComparer fromName(String name) {
        return  list().stream()
                .filter(filterComparer -> filterComparer.getExpression().equals(name))
                .findAny()
                .orElse(null);
    }

    public static FilterComparer from(Integer id) {
        return list().stream()
                .filter(filterComparer -> filterComparer.getId().equals(id))
                .findAny()
                .orElse(null);
    }
}
