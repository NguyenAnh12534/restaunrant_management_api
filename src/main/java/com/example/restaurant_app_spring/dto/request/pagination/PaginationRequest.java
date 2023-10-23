package com.example.restaurant_app_spring.dto.request.pagination;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PaginationRequest {
    public static final int DEFAULT_PAGE = 0;
    public static final int DEFAULT_LIMIT = 10;
    @Min(0)
    private int pageNumber = DEFAULT_PAGE;
    @Min(1)
    private int pageLimit = DEFAULT_LIMIT;

}
