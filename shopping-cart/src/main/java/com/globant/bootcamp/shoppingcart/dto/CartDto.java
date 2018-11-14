package com.globant.bootcamp.shoppingcart.dto;

import java.util.List;

public class CartDto {

	private Long id;
	private String user;
	private List<ProductDto> products;

	public CartDto() {

	}

	public CartDto(Long id, String user, List<ProductDto> products) {
		this.id = id;
		this.user = user;
		this.products = products;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public List<ProductDto> getProducts() {
		return products;
	}

	public void setProducts(List<ProductDto> products) {
		this.products = products;
	}

	public Double getTotal() {
		Double total = 0D;
		if (products == null) {
			return total;
		}

		for (ProductDto p : products) {
			total += p.getPrice();
		}
		return total;
	}

}
