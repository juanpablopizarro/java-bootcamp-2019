package com.globant.bootcamp.shoppingcart.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.globant.bootcamp.shoppingcart.model.Cart;
import com.globant.bootcamp.shoppingcart.model.Product;

public class Mapper {

	public static CartDto convert(Cart cart) {
		List<ProductDto> products = cart.getProducts() == null ? new ArrayList<>()
				: cart.getProducts().stream().map(p -> convert(p)).collect(Collectors.toList());
		return new CartDto(cart.getId(), cart.getUser(), products);
	}

	public static Cart convert(CartDto cart) {
		List<Product> products = cart.getProducts() == null ? new ArrayList<>()
				: cart.getProducts().stream().map(p -> convert(p)).collect(Collectors.toList());
		Cart result = new Cart();
		result.setId(cart.getId());
		result.setUser(cart.getUser());
		result.setProducts(products);
		return result;
	}

	public static ProductDto convert(Product product) {
		return new ProductDto(product.getId(), product.getName(), product.getPrice());
	}

	public static Product convert(ProductDto product) {
		Product result = new Product();
		result.setId(product.getId());
		result.setName(product.getName());
		result.setPrice(product.getPrice());
		return result;
	}

}
