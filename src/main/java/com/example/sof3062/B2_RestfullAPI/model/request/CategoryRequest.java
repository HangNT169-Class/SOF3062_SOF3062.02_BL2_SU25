package com.example.sof3062.B2_RestfullAPI.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryRequest {

    private Integer id;

    @NotBlank(message = "Code k duoc de trong")
    private String code1;

    @NotBlank(message = "Name k duoc de trong")
    private String name;

}
