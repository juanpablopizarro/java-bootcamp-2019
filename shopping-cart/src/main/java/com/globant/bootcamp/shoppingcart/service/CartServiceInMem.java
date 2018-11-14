package com.globant.bootcamp.shoppingcart.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.globant.bootcamp.shoppingcart.model.Cart;
import com.globant.bootcamp.shoppingcart.model.Product;

@Service
public class CartServiceInMem implements CartService {

	private Map<Long, Cart> carts = new HashMap<>();
	private Long sequence = 0L;

	public synchronized Cart addCart(Cart cart) {
		if (cart.getId() == null) {
			sequence++;
			cart.setId(sequence);
		}
		carts.put(sequence, cart);
		return cart;
	}

	public synchronized Cart getCart(Long cartId) {
		return carts.get(cartId);
	}

	public synchronized void removeCart(Long cartId) {
		carts.remove(cartId);
	}

	public synchronized Cart addProduct(Long cartId, Product product) {
		Cart cart = carts.get(cartId);

		if (cart == null) {
			return null;
		}

		cart.addProduct(product);
		return cart;
	}

	public synchronized Cart removeProduct(Long cartId, Product product) {
		Cart cart = carts.get(cartId);

		if (cart == null) {
			return null;
		}

		List<Product> products = cart.getProducts();
		if (products == null) {
			return cart;
		}
		products.remove(product);
		return cart;

	}

}
