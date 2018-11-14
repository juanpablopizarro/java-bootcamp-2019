package com.globant.bootcamp.shoppingcart.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.globant.bootcamp.shoppingcart.dto.Mapper;
import com.globant.bootcamp.shoppingcart.dto.ProductDto;
import com.globant.bootcamp.shoppingcart.model.Product;
import com.globant.bootcamp.shoppingcart.service.ProductServiceInMem;

@RestController
public class ProductController {

	@Autowired
	private ProductServiceInMem products;

	@PostMapping("products")
	public ProductDto addProduct(@RequestBody ProductDto product) {
		Product newProduct = products.add(Mapper.convert(product));
		return Mapper.convert(newProduct);
	}

	@GetMapping("products/{id}")
	public ProductDto getProduct(@PathVariable("id") Long id) {
		Product product = products.get(id);
		return Mapper.convert(product);
	}

	@DeleteMapping("products/{id}")
	public void removeProduct(@PathVariable("id") Long id) {
		products.remove(id);
	}

	@GetMapping("products/all")
	public List<ProductDto> getProduct() {
		List<Product> all = products.getAll();

		if (all == null) {
			return new ArrayList<>();
		}

		return all.stream().map(p -> Mapper.convert(p)).collect(Collectors.toList());
	}

}
