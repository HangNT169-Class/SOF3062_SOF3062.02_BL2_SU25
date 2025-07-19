package com.example.sof3062.B2_RestfullAPI.model.response;

import com.example.sof3062.B2_RestfullAPI.entity.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryResponse {
    // liet ke cac thuoc tinh tra ve
    private Integer id;

    private String categoryCode ;

    private String categoryName ;

    public CategoryResponse(Category cate) {
        this.id = cate.getId();
        this.categoryCode = cate.getCategoryCode();
        this.categoryName = cate.getCategoryName();
    }
}
