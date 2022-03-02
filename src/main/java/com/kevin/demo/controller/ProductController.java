package com.kevin.demo.controller;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kevin.demo.model.Product;
import com.kevin.demo.service.ProductAsyncService;
import com.kevin.demo.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	private final ProductService productService;
	private final ProductAsyncService productAsyncService;
	private final static Logger log = LoggerFactory.getLogger(ProductController.class);
	
	public ProductController(ProductService productService, ProductAsyncService productAsyncService) {
		this.productService = productService;
		this.productAsyncService = productAsyncService;
	}
	
	@GetMapping
	public List<Product> getAllProducts () throws Exception {
		List<Product> list1 = productService.getProducts1();
		List<Product> list2 = productService.getProducts2();
		List<Product> list3 = productService.getProducts3();
		
		List<Product> list4 = Stream.of(list1, list2, list3)
				.flatMap(Collection::stream)
				.collect(Collectors.toList());
		return list4;
	}
	
	@GetMapping("/async")
	public List<Product> getAllProductsAsync () throws Exception {
		CompletableFuture<List<Product>> c1 = productAsyncService.getProducts1();
		CompletableFuture<List<Product>> c2 = productAsyncService.getProducts2();
		CompletableFuture<List<Product>> c3 = productAsyncService.getProducts3();
		
		CompletableFuture.allOf(c1, c2, c3).join();
		
		List<Product> list4 = Stream.of(c1.get(), c2.get(), c3.get())
				.flatMap(Collection::stream)
				.collect(Collectors.toList());
		return list4;
	}
	
}
