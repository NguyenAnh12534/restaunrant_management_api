package com.example.restaurant_app_spring.enums;

import java.util.Arrays;
import java.util.List;

public enum BillOrderStatus {
    PAID, UN_PAID, CANCELLED;
    public static List<BillOrderStatus> getEditableStatuses() {
        return Arrays.asList(UN_PAID);
    }
}
