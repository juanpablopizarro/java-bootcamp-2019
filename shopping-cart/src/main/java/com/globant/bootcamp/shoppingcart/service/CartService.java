package com.globant.bootcamp.shoppingcart.service;


import java.util.List;

import com.globant.bootcamp.shoppingcart.model.Cart;
import com.globant.bootcamp.shoppingcart.model.Product;

public interface CartService {

	public Cart addCart(Cart cart);

	public Cart getCart(long cartId);

	public List<Cart> getCarts();

	public void removeCart(long cartId);

	public Cart addProduct(long cartId, Product product);

	public Cart removeProduct(long cartId, Product product);

}
