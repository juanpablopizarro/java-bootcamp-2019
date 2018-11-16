package com.globant.bootcamp.shoppingcart.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.globant.bootcamp.shoppingcart.model.Cart;
import com.globant.bootcamp.shoppingcart.model.Product;

import org.springframework.stereotype.Service;

@Service
public class CartServiceInMem implements CartService {

	private Map<Long, Cart> carts = new HashMap<>();
	private AtomicLong sequence = new AtomicLong(0L);
	private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

	public Cart addCart(Cart cart) {
		if (cart == null) {
			throw new RuntimeException("invalid cart: null");
		}

		if (cart.getId() == null) {
			cart.setId(sequence.addAndGet(1));
		}

		lock.writeLock().lock();
		try {
			carts.put(sequence.get(), cart);
		} finally {
			lock.writeLock().unlock();
		}

		return cart;
	}

	public Cart getCart(long cartId) {
		lock.readLock().lock();
		try {
			return carts.get(cartId);
		} finally {
			lock.readLock().unlock();
		}
	}

	public void removeCart(long cartId) {
		lock.writeLock().lock();
		try {
			carts.remove(cartId);
		} finally {
			lock.writeLock().unlock();
		}
	}

	public Cart addProduct(long cartId, Product product) {
		if (product == null) {
			throw new RuntimeException("invalid product. PRODUCT=" + product);
		}

		lock.writeLock().lock();
		try {
			Cart cart = carts.get(cartId);
			if (cart == null) {
				throw new RuntimeException("cart does not exists. CART_ID=" + cartId);
			}
			cart.addProduct(product);
			return cart;
		} finally {
			lock.writeLock().unlock();
		}
	}

	public Cart removeProduct(long cartId, Product product) {
		Cart cart = carts.get(cartId);

		if (cart == null) {
			return null;
		}

		lock.writeLock().lock();
		// TODO use the double check technique
		try {
			List<Product> products = cart.getProducts();
			if (products == null) {
				return cart;
			}
			products.remove(product);
			return cart;
		} finally {
			lock.writeLock().unlock();
		}
	}

	@Override
	public List<Cart> getCarts() {
		lock.readLock().lock();
		try {
			return new ArrayList<Cart>(carts.values());
		} finally {
			lock.readLock().unlock();
		}
	}

}
