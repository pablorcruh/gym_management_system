package ec.com.pablorcruh.shared.infrastructure.models;

import ec.com.pablorcruh.shared.domain.specification.Criteria;
import ec.com.pablorcruh.shared.domain.specification.Order;
import ec.com.pablorcruh.shared.domain.specification.OrderType;
import jakarta.persistence.EntityManager;
import lombok.Getter;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;


import java.util.ArrayList;
import java.util.List;

@Getter
public class DslBuilder {

    private BooleanBuilder builder;
    private PathBuilder<?> path;
    private List<OrderSpecifier<?>> orders;

    public DslBuilder(Class entityClass, String model) {
        this.builder = new BooleanBuilder();
        this.path = new PathBuilder(entityClass, model);
        this.orders = new ArrayList<>();
    }

    private BooleanBuilder buildPredicates(Criteria criteria) {

        criteria.getSpecifications().forEach(specification -> {
            Predicate predicate = null;
            boolean isNumber = specification.getValue() instanceof Number;
            boolean isBoolean = specification.getValue() instanceof Boolean;
            boolean isText = specification.getValue() instanceof String;
            boolean isList = specification.getValue() instanceof ArrayList;
            //TODO add case when value is a kind of Dates
            if (isNumber) {
                var fieldName = path.getNumber(specification.getFilterField().getFieldName(), Double.class);
                var value = Double.parseDouble(specification.getValue().toString());
                switch (specification.getOperator().getExpression()) {
                    case "!=":
                        predicate = fieldName.ne(value);
                        break;
                    case "=":
                        predicate = fieldName.eq(value);
                        break;
                    case ">":
                        predicate = fieldName.gt(value);
                        break;
                    case ">=":
                        predicate = fieldName.goe(value);
                        break;
                    case "<":
                        predicate = fieldName.lt(value);
                        break;
                    case "<=":
                        predicate = fieldName.loe(value);
                        break;

                    default:
                        //TODO throw new exception
                        break;
                }
            } else if (isText) {
                var fieldName = path.getString(specification.getFilterField().getFieldName());
                var value = specification.getValue().toString();

                switch (specification.getOperator().getExpression()) {
                    case "!=":
                        predicate = fieldName.ne(value);
                        break;
                    case "=":
                        predicate = fieldName.eq(value);
                        break;
                    case ">":
                        predicate = fieldName.gt(value);
                        break;
                    case ">=":
                        predicate = fieldName.goe(value);
                        break;
                    case "<":
                        predicate = fieldName.lt(value);
                        break;
                    case "<=":
                        predicate = fieldName.loe(value);
                        break;
                    case "like":
                        predicate = fieldName.like("%" + value + "%", '!');
                        break;
                    case "not like":
                        predicate = fieldName.notLike("%" + value + "%");
                        break;
                    default:
                        //TODO throw new exception
                        break;
                }

            } else if (isBoolean) {
                var fieldName = path.getBoolean(specification.getFilterField().getFieldName());
                var value = Boolean.valueOf(specification.getValue().toString());

                switch (specification.getOperator().getExpression()) {
                    case "!=":
                        predicate = fieldName.ne(value);
                        break;
                    case "=":
                        predicate = fieldName.eq(value);
                        break;
                    default:
                        //TODO throw new exception
                        break;
                }

            } else if (isList) {
                try {
                    var fieldName = path.getString(specification.getFilterField().getFieldName());
                    var value = (ArrayList<String>) specification.getValue();
                    predicate = fieldName.in(value);
                } catch (Exception ignored) {}
            }


            switch (specification.getComparer().getExpression()) {
                case "and":
                    builder.and(predicate);
                    break;
                case "or":
                    builder.or(predicate);
                    break;
                default:
                    //TODO throw new exception
                    break;
            }

        });
        return builder;
    }

    private OrderSpecifier[] buildOrders(Criteria criteria) {
        OrderSpecifier[] list = new OrderSpecifier[(int) criteria.getOrders().stream().count()];
        Integer count = 0;

        for (Order order : criteria.getOrders()) {
            if (OrderType.fromName(order.getOrderType().getExpression()).getExpression().equals("asc")) {
                list[count] = new OrderSpecifier(com.querydsl.core.types.Order.ASC, path.getString(order.getOrderField().getFieldName()));
            } else {
                list[count] = new OrderSpecifier(com.querydsl.core.types.Order.DESC, path.getString(order.getOrderField().getFieldName()));
            }
            count++;
        }

        return list;
    }


    public JPAQuery<?> getQuery(Criteria criteria,
                                EntityManager entityManager,
                                EntityPath<?> entityClass) {
        JPAQuery<?> query = new JPAQuery(entityManager);

        int iniciar = (criteria.getPage() == 0 ? 0 :criteria.getPage());
        System.out.println("--"+iniciar+"iniciar");

        criteria.setPage(iniciar);

        if(!criteria.getGroup()) {
            query.from(entityClass)
                    .where(buildPredicates(criteria))
                    .orderBy(buildOrders(criteria))
                    .limit(criteria.getPageSize())
                    .offset(iniciar * criteria.getPageSize());
        }else{
            query.from(entityClass)
                    .where(buildPredicates(criteria))
                    .orderBy(buildOrders(criteria))
                    .limit(criteria.getPageSize())
                    .offset(iniciar * criteria.getPageSize());
        }



        return query;
    }
}
