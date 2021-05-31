package com.example.hotel.search;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class SearchCriteria {
    private String key;
    private SearchOperation operation;
    private Object value;


    public SearchCriteria(String key, SearchOperation op, Object value) {
        this.key = key;
        this.operation = op;
        this.value = value;
    }


}
