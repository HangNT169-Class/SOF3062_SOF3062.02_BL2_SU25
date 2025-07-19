package com.example.sof3062.B2_RestfullAPI.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

@Getter
@Setter
@Entity
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 100)
    @Column(name = "product_code", length = 100)
    private String productCode;

    @Size(max = 100)
    @Nationalized
    @Column(name = "product_name", length = 100)
    private String productName;

    @Column(name = "price")
    private Double price;

    @Size(max = 100)
    @Column(name = "description", length = 100)
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id",referencedColumnName = "id")
    private Category cate;
}