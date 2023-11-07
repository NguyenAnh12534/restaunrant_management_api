package com.example.restaurant_app_spring.dto.request.sort;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class SortRequest {
    private List<SortItem> sortFields = new ArrayList<>();

    public SortRequest() {

    }

    public void setSort(String sortString) {
        String[] fieldNames = sortString.split(",");
        for(String fieldName : fieldNames) {
            SortItem sortItem = new SortItem();
            if(fieldName.startsWith("-"))
            {
                fieldName = fieldName.substring(1);
                sortItem.setSortType(Sort.Direction.DESC);
                sortItem.setFieldName(fieldName);
            }
            else {
                sortItem.setSortType(Sort.Direction.ASC);
                sortItem.setFieldName(fieldName);
            }
            this.sortFields.add(sortItem);
        }
    }

    public List<Order> extractOrders() {
        List<Order> orders = new ArrayList<>();
        this.sortFields.forEach(sortField ->{
            orders.add(new Order(sortField.getSortType(), sortField.getFieldName()));
        });

        return orders;
    }

    public Sort build() {
        return Sort.by(this.extractOrders());
    }
}
