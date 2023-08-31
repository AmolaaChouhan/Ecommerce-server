//package com.zealous.service;
//import com.zealous.exception.ProductException;
//import com.zealous.model.Product;
//import com.zealous.request.CreateProductRequest;
//
//import java.util.List;
//
//import org.springframework.data.domain.Page;
//
//public interface ProductService {
//	public  Product createProduct(CreateProductRequest req) throws ProductException;
//    public String deleteProduct(Long ProductId) throws ProductException;
//    public Product updateProduct(Long ProductId,Product req) throws ProductException;
//	public Product findProductById(Long Id) throws ProductException;
//	public List<Product> findProductByCategory(String category);
//	public List<Product> searchProduct(String query);
//	
//	public Page<Product> getAllProduct(String category,List<String>colors,List<String>sizes,Integer minPrice,Integer maxPrice,Integer minDiscount,String sort,String stock,Integer pageNumber,Integer pageSize);
//	public List<Product> findAllProducts();
//	
//}

package com.zealous.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.zealous.exception.ProductException;
import com.zealous.model.Product;
import com.zealous.request.CreateProductRequest;


public interface ProductService {

	public Product createProduct(CreateProductRequest req) throws ProductException;
	
	public String deleteProduct(Long productId) throws ProductException;
	
	public Product updateProduct(Long productId,Product product)throws ProductException;
	
	public List<Product> getAllProducts();

	public Product findProductById(Long id) throws ProductException;
	
	public List<Product> findProductByCategory(String category);
	
	public List<Product> searchProduct(String query);
	
//	public List<Product> getAllProduct(List<String>colors,List<String>sizes,int minPrice, int maxPrice,int minDiscount, String category, String sort,int pageNumber, int pageSize);
	public Page<Product> getAllProduct(String category, List<String>colors, List<String> sizes, Integer minPrice, Integer maxPrice, Integer minDiscount,String sort, String stock, Integer pageNumber, Integer pageSize);
	
	
	
	

}
