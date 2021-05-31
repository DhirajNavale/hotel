package com.example.hotel.search;

import com.example.hotel.model.Hotel;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class HotelSpecificationsBuilder {

    private List<SearchCriteria> params = new ArrayList<SearchCriteria>();

    public HotelSpecificationsBuilder with(
            String key, String operation, Object value, String prefix, String suffix) {

        SearchOperation op = SearchOperation.getSimpleOperation(operation.charAt(0));
        if (op != null) {
            if (op == SearchOperation.EQUALITY) {
                boolean startWithAsterisk = prefix.contains("*");
                boolean endWithAsterisk = suffix.contains("*");

                if (startWithAsterisk && endWithAsterisk) {
                    op = SearchOperation.CONTAINS;
                } else if (startWithAsterisk) {
                    op = SearchOperation.ENDS_WITH;
                } else if (endWithAsterisk) {
                    op = SearchOperation.STARTS_WITH;
                }
            }
            SearchCriteria searchCriteria = new SearchCriteria();
            searchCriteria.setKey(key);
            searchCriteria.setOperation(op);
            searchCriteria.setValue(value);
            params.add(searchCriteria);
        }
        return this;
    }

    public Specification<Hotel> build() {
        if (params.size() == 0) {
            return null;
        }

        Specification result = new HotelSpecification(params.get(0));
        System.out.println(params.get(0));
        for (int i = 1; i < params.size(); i++) {

//            result = params.get(i)
//                    .isOrPredicate()
//                    ? Specification.where(result)
//                    .or(specs.get(i))
//                    : Specification.where(result)
//                    .and(specs.get(i));
            result = Specification.where(result).and(new HotelSpecification(params.get(i)));
        }

        return result;
    }
}
