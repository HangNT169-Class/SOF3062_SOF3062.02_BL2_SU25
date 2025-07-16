package com.example.sof3062.B2_RestfullAPI.controller;

import com.example.sof3062.B2_RestfullAPI.model.request.ProductRequest;
import com.example.sof3062.B2_RestfullAPI.model.response.ResponseObject;
import com.example.sof3062.B2_RestfullAPI.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService proService;

    @GetMapping
    public ResponseObject<?>hienThiDuLieu(){
        return new ResponseObject<>(proService.getAll());
    }
    @GetMapping("playlist/paging")
    public ResponseObject<?> phanTrangProduct(@RequestParam(value = "pageNo1", defaultValue = "0") Integer pageNo,
                                              @RequestParam(value = "pageSize1", defaultValue = "2") Integer pageSize) {
        return new ResponseObject<>(proService.phanTrang(pageNo, pageSize));
    }
    @DeleteMapping("/delete/{id1}")
    public ResponseObject<?> delete(@PathVariable("id1") String id) {
        proService.delete(id);
        return new ResponseObject<>(null, "Xoa product thanh cong");
    }

    @GetMapping("/detail/{id1}")
    public ResponseObject<?> detail(@PathVariable("id1") Long id) {
        return new ResponseObject<>(proService.detail(id));
    }

    @PostMapping("/add")
    public ResponseObject<?> addProduct( @RequestBody ProductRequest productRequest) {
        proService.add(productRequest);
        return new ResponseObject<>(null, "Add thanh cong");
    }

    @PutMapping("/update/{id}")
    public ResponseObject<?> updateCategory(@PathVariable("id") Long id, @RequestBody ProductRequest cateRequest) {
        proService.update(id,cateRequest);
        return new ResponseObject<>(null, "Update thanh cong");
    }
}
