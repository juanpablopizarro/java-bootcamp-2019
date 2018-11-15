package com.globant.bootcamp.shoppingcart.service;

import java.util.List;

import com.globant.bootcamp.shoppingcart.model.Product;

public interface ProductService {

	public Product add(Product product);

	public Product get(long id);

	public void remove(long id);

	public List<Product> getAll();

}
