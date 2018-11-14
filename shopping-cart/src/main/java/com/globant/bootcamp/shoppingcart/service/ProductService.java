package com.globant.bootcamp.shoppingcart.service;

import java.util.List;

import com.globant.bootcamp.shoppingcart.model.Product;

public interface ProductService {

	public Product add(Product product);

	public Product get(Long id);

	public void remove(Long id);

	public List<Product> getAll();

}
