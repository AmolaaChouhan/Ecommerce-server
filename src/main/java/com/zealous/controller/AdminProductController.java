package com.zealous.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.zealous.exception.ProductException;
import com.zealous.model.Product;
import com.zealous.request.CreateProductRequest;
import com.zealous.response.ApiResponse;
import com.zealous.service.ProductService;

@RestController
@RequestMapping("/api/admin/products")
public class AdminProductController {

    private final ProductService productService;

    public AdminProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/")
    public ResponseEntity<Product> createProductHandler(@RequestBody CreateProductRequest req) throws ProductException {

        Product createdProduct = productService.createProduct(req);

        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @DeleteMapping("/{productId}/delete")
    public ResponseEntity<ApiResponse> deleteProductHandler(@PathVariable Long productId) throws ProductException {

        String msg = productService.deleteProduct(productId);
        ApiResponse res = new ApiResponse(msg, true);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> findAllProduct() {

        List<Product> products = productService.findAllProducts();

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PutMapping("/{productId}/update")
    public ResponseEntity<Product> updateProductHandler(
            @RequestBody Product req, @PathVariable Long productId) throws ProductException {

        Product updatedProduct = productService.updateProduct(productId, req);

        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @PostMapping("/creates")
    public ResponseEntity<ApiResponse> createMultipleProduct(
            @RequestBody CreateProductRequest[] reqs) throws ProductException {

        for (CreateProductRequest product : reqs) {
            productService.createProduct(product);
        }

        ApiResponse res = new ApiResponse("Products created successfully", true);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }
}
