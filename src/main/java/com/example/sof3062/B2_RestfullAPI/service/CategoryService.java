package com.example.sof3062.B2_RestfullAPI.service;


import com.example.sof3062.B2_RestfullAPI.entity.Category;
import com.example.sof3062.B2_RestfullAPI.entity.Product;
import com.example.sof3062.B2_RestfullAPI.expection.ApiException;
import com.example.sof3062.B2_RestfullAPI.model.request.CategoryRequest;
import com.example.sof3062.B2_RestfullAPI.model.response.CategoryResponse;
import com.example.sof3062.B2_RestfullAPI.model.response.PageableObject;
import com.example.sof3062.B2_RestfullAPI.model.response.ProductResponse;
import com.example.sof3062.B2_RestfullAPI.repository.CategoryRepository;
import com.example.sof3062.B2_RestfullAPI.util.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository cateRepository;


    public List<CategoryResponse> getAll() {
        return cateRepository.findAll()
                .stream()
                .map(CategoryResponse::new)
                .toList();
    }
//
//    public PageableObject<Category1> phanTrang(Integer pageNo, Integer pageSize) {
//        Pageable pageable = PageRequest.of(pageNo, pageSize);
//        List<Category1> cates = cateRepository.findAll(pageable).getContent();
//        return new PageableObject<>(cates,pageSize,pageNo);
//    }

    public PageableObject<CategoryResponse> phanTrang(Integer pageNo, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Category> pageProduct = cateRepository.findAll(pageable);
        // map tu Product => Product Response
        Page<CategoryResponse> pageResponse = pageProduct.map(CategoryResponse::new);
        return new PageableObject<>(pageResponse);
    }

    public void delete(Integer id) {
        cateRepository.findById(id).orElseThrow(
                () -> new ApiException("ID khong ton tai", "TV01")
        );
        cateRepository.deleteById(id);
    }

    public void addCategory(CategoryRequest request) {
        // B1: Mapping tu request ve entity
        Category p = MapperUtils.map(request,Category.class);
        cateRepository.save(p);
    }

    public void updateCategory(CategoryRequest request,Integer id) {
        // B1: Mapping tu request ve entity
        MapperUtils.mapToExisting(request,Category.class);
        Category cateExist = cateRepository.findById(id).get();
        MapperUtils.mapToExisting(request, cateExist);
        // Boi vi sau mapping id se chuyen ve null theo id cua request nen can set lai
        cateExist.setId(id);
        cateRepository.save(cateExist);
    }


    public CategoryResponse detail(Integer id) {
        return new CategoryResponse(cateRepository.findById(id).orElseThrow(() -> new ApiException("aaa", "aa")));
    }

}
