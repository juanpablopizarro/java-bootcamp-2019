package com.globant.bootcamp.shoppingcart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.globant.bootcamp.shoppingcart.dto.CartDto;
import com.globant.bootcamp.shoppingcart.dto.Mapper;
import com.globant.bootcamp.shoppingcart.model.Cart;
import com.globant.bootcamp.shoppingcart.model.Product;
import com.globant.bootcamp.shoppingcart.service.CartServiceInMem;
import com.globant.bootcamp.shoppingcart.service.ProductServiceInMem;

@RestController
public class CartController {

	@Autowired
	private ProductServiceInMem products;
	@Autowired
	private CartServiceInMem carts;

	@PostMapping("carts")
	public CartDto addCart(@RequestBody CartDto cart) {
		Cart newCart = carts.addCart(Mapper.convert(cart));
		return Mapper.convert(newCart);
	}

	@GetMapping("carts/{id}")
	public CartDto getCart(@PathVariable("id") Long id) {
		Cart cart = carts.getCart(id);
		return Mapper.convert(cart);
	}

	@DeleteMapping("carts/{id}")
	public void removeCart(@PathVariable("id") Long id) {
		carts.removeCart(id);
	}

	@PutMapping("carts/{cart}/products/{product}")
	public CartDto addProduct(@PathVariable("cart") Long cart, @PathVariable("product") Long product) {
		Product p = products.get(product);
		Cart updatedCart = carts.addProduct(cart, p);
		return Mapper.convert(updatedCart);
	}

	@DeleteMapping("carts/{cart}/products/{product}")
	public CartDto removeProduct(@PathVariable("cart") Long cart, @PathVariable("product") Long product) {
		Product p = products.get(product);
		Cart updatedCart = carts.removeProduct(cart, p);
		return Mapper.convert(updatedCart);
	}
}
