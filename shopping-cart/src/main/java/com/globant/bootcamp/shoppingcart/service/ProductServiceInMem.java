package com.globant.bootcamp.shoppingcart.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.globant.bootcamp.shoppingcart.model.Product;

import org.springframework.stereotype.Service;

@Service
public class ProductServiceInMem implements ProductService {

	private Map<Long, Product> products = new HashMap<>();
	private AtomicLong sequence = new AtomicLong(0L);
	private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

	public Product add(Product product) {
		if (product == null) {
			throw new RuntimeException("product can't be null.");
		}

		if (product.getId() == null) {
			product.setId(sequence.incrementAndGet());
		}

		lock.writeLock().lock();
		try {
			products.put(product.getId(), product);
			return product;
		} finally {
			lock.writeLock().unlock();
		}
	}

	public Product get(long id) {
		lock.readLock().lock();
		try {
			return products.get(id);
		} finally {
			lock.readLock().unlock();
		}
	}

	public void remove(long id) {
		lock.writeLock().lock();
		try {
			products.remove(id);
		} finally {
			lock.writeLock().unlock();
		}
	}

	public List<Product> getAll() {
		lock.readLock().lock();
		try {
			return new ArrayList<>(products.values());
		} finally {
			lock.readLock().unlock();
		}
	}

}
