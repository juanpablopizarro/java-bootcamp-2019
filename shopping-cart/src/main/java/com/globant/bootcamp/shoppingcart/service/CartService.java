package com.globant.bootcamp.shoppingcart.service;

import com.globant.bootcamp.shoppingcart.model.Cart;
import com.globant.bootcamp.shoppingcart.model.Product;

public interface CartService {

	public Cart addCart(Cart cart);

	public Cart getCart(Long cartId);

	public void removeCart(Long cartId);

	public Cart addProduct(Long cartId, Product product);

	public Cart removeProduct(Long cartId, Product product);

}
