package com.example.sof3062.B2_RestfullAPI.service;


import com.example.sof3062.B2_RestfullAPI.entity.Category;
import com.example.sof3062.B2_RestfullAPI.expection.ApiException;
import com.example.sof3062.B2_RestfullAPI.model.response.PageableObject;
import com.example.sof3062.B2_RestfullAPI.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository cateRepository;


    public List<Category> getAll() {
        return cateRepository.findAll();
    }
//
//    public PageableObject<Category1> phanTrang(Integer pageNo, Integer pageSize) {
//        Pageable pageable = PageRequest.of(pageNo, pageSize);
//        List<Category1> cates = cateRepository.findAll(pageable).getContent();
//        return new PageableObject<>(cates,pageSize,pageNo);
//    }

    public PageableObject<Category> phanTrang(Integer pageNo, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return new PageableObject<>(cateRepository.findAll(pageable));
    }

    public void delete(Integer id) {
        cateRepository.findById(id).orElseThrow(
                () -> new ApiException("ID khong ton tai", "TV01")
        );
        cateRepository.deleteById(id);
    }

    public Category addOrUpdateCategory(Category category) {
        return cateRepository.save(category);
    }

    public Category detail(Integer id) {
        return cateRepository.findById(id).orElseThrow(()->new ApiException("aaa","aa"));
    }

}
