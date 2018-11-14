package com.globant.bootcamp.shoppingcart.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.globant.bootcamp.shoppingcart.model.Product;

@Service
public class ProductServiceInMem implements ProductService {

	private Map<Long, Product> products = new HashMap<>();
	private Long sequence = 0L;

	public synchronized Product add(Product product) {
		if (product.getId() == null) {
			sequence++;
			product.setId(sequence);
		}
		products.put(sequence, product);
		return product;
	}

	public synchronized Product get(Long id) {
		return products.get(id);
	}

	public synchronized void remove(Long id) {
		products.remove(id);
	}

	public synchronized List<Product> getAll() {
		return products.values().stream().collect(Collectors.toList());
	}

}
