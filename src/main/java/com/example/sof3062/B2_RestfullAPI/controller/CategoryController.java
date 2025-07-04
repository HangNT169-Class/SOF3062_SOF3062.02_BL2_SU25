package com.example.sof3062.B2_RestfullAPI.controller;

import com.example.sof3062.B2_RestfullAPI.entity.Category;
import com.example.sof3062.B2_RestfullAPI.model.request.CategoryRequest;
import com.example.sof3062.B2_RestfullAPI.model.response.ResponseObject;
import com.example.sof3062.B2_RestfullAPI.service.CategoryService;
import com.example.sof3062.B2_RestfullAPI.util.MapperUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category-management/")
public class CategoryController {
    // Viet chuan theo RestFull API: 4 method: Get, Post, Put, Delete
    // GET => Hien thi, Lay => Read
    // Delete => Xoa => Delete
    // Post => Add => CREATE
    // Put => Update => Updadte

    @Autowired
    private CategoryService categoryService;

    @GetMapping("playlist")
    public ResponseObject<?> hienThiDanhSachCategory() {
        List<Category> lists = categoryService.getAll();
        return new ResponseObject<>(lists);
    }

    @GetMapping("playlist/paging")
    public ResponseObject<?> phanTrangCategory(@RequestParam(value = "pageNo1", defaultValue = "0") Integer pageNo,
                                               @RequestParam(value = "pageSize1", defaultValue = "2") Integer pageSize) {
        return new ResponseObject<>(categoryService.phanTrang(pageNo, pageSize));
    }

    @DeleteMapping("/delete/{id1}")
    public ResponseObject<?> delete(@PathVariable("id1") Integer id) {
        categoryService.delete(id);
        return new ResponseObject<>(null, "Xoa category thanh cong");
    }

    @GetMapping("/detail/{id1}")
    public ResponseObject<?> detail(@PathVariable("id1") Integer id) {
        return new ResponseObject<>(categoryService.detail(id));
    }

    @PostMapping("/add")
    public ResponseObject<?> addCategory(@Valid @RequestBody CategoryRequest cateRequest) {
        // dang co: CategoryRequest
        // can: Category
        // => Convert request => entity
//        Category1 cate = new Category1();
//        cate.setId(request.getId());
//        categoryService.addOrUpdateCategory()
        Category cate = MapperUtils.map(cateRequest, Category.class);
        return new ResponseObject<>(categoryService.addOrUpdateCategory(cate), "Add thanh cong");
    }

    @PutMapping("/update/{id}")
    public ResponseObject<?> updateCategory(@PathVariable("id") Integer id, @Valid @RequestBody CategoryRequest cateRequest) {
        Category cate = categoryService.detail(id);
        MapperUtils.mapToExisting(cateRequest, cate);
        // Boi vi sau mapping id se chuyen ve null theo id cua request nen can set lai
        cate.setId(id);
        return new ResponseObject<>(categoryService.addOrUpdateCategory(cate), "Update thanh cong");
    }

    // CO 2 loai loi:
    // Validate be ngoai: trong, k dung dinh dang... - 2 noi de check (FE, BE) => 400 : Bad Request => Spring Valition
    // Loi logic: Vao DB  => BE => Custom Hander Expection
}
