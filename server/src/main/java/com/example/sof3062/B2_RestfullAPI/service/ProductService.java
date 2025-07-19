package com.example.sof3062.B2_RestfullAPI.service;

import com.example.sof3062.B2_RestfullAPI.entity.Category;
import com.example.sof3062.B2_RestfullAPI.entity.Product;
import com.example.sof3062.B2_RestfullAPI.model.request.ProductRequest;
import com.example.sof3062.B2_RestfullAPI.model.response.PageableObject;
import com.example.sof3062.B2_RestfullAPI.model.response.ProductResponse;
import com.example.sof3062.B2_RestfullAPI.repository.CategoryRepository;
import com.example.sof3062.B2_RestfullAPI.repository.ProductRepository;
import com.example.sof3062.B2_RestfullAPI.util.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository proRepository;

    @Autowired
    private CategoryRepository cateRepository;

    public List<ProductResponse>getAll(){
        return proRepository.findAll()
                .stream()
                .map(ProductResponse::new)
                .toList();
    }

    public PageableObject<ProductResponse> phanTrang(Integer pageNo, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Product>pageProduct = proRepository.findAll(pageable);
        // map tu Product => Product Response
        Page<ProductResponse>pageResponse = pageProduct.map(ProductResponse::new);
        return new PageableObject<>(pageResponse);
    }

    public ProductResponse detail(Long id){
        Product p = proRepository.findById(id).orElseThrow();
        return new ProductResponse(p);
    }

    public void delete(Long id){
        proRepository.deleteById(id);
    }

    public void deleteByMa(String ma){
        proRepository.deleteByMa(ma);
    }

    public void add(ProductRequest request){
        // B1: Mapping tu request ve entity
        Product p = MapperUtils.map(request,Product.class);
        // B2: lay ra category tu request
        Category cate = cateRepository.findById(request.getCategoryId()).orElse(null);
        // B3: Moi set ve entity
        p.setCate(cate);
        // B4: Goi add
        proRepository.save(p);
    }
    public void update(Long id, ProductRequest productRequest) {
        Product p = proRepository.findById(id).orElse(null);
        MapperUtils.mapToExisting(productRequest, p);
        Category cate = cateRepository.findById(productRequest.getCategoryId()).orElse(null);
        p.setCate(cate);
        p.setId(id);
        proRepository.save(p);
    }

    // Full CRUD 2 bang => 8
    // Validate => 9
    // Tim kiem, Search, Sort.. => 10
}
