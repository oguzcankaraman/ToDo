package com.example.todo.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pagination {

    @Min(0)
    @Max(Integer.MAX_VALUE)
    int pageNo = 0;
    @Min(0)
    @Max(5)
    @JsonProperty(value = "size")
    int pageSize = 5;


}
