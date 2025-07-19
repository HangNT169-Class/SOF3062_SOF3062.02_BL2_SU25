package com.example.sof3062.B2_RestfullAPI.model.response;

import com.example.sof3062.B2_RestfullAPI.entity.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponse {
    // du lieu tra ra
    private Long id;

    private String productCode;

    private String productName;

    private Double price;

    private String categoryCode;

    private String categoryName;

    public ProductResponse(Product p) {
        this.id = p.getId();
        this.productCode = p.getProductCode();
        this.productName = p.getProductName();
        this.price = p.getPrice();
        this.categoryCode = p.getCate().getCategoryCode();
        this.categoryName = p.getCate().getCategoryName();
    }

}
