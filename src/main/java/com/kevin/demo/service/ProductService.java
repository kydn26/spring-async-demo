package com.kevin.demo.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kevin.demo.model.Product;

@Service
public class ProductService {

	public List<Product> getProducts1() throws Exception {
		Thread.sleep(1000);
		return Arrays.asList(
			new Product(1, "Product A"),
			new Product(2, "Product B"),
			new Product(3, "Product C")
		);
	}
	
	public List<Product> getProducts2() throws Exception {
		Thread.sleep(3000);
		return Arrays.asList(
			new Product(4, "Product D"),
			new Product(5, "Product E"),
			new Product(6, "Product F")
		);
	}
	
	public List<Product> getProducts3() throws Exception {
		Thread.sleep(2000);
		return Arrays.asList(
			new Product(7, "Product G"),
			new Product(8, "Product H"),
			new Product(9, "Product I")
		);
	}
}
