package com.zealous.service;

import com.zealous.exception.ProductException;
import com.zealous.model.Cart;
import com.zealous.model.User;
import com.zealous.request.AddItemRequest;

public interface CartService {

	
	public Cart createCart(User user);
    public String addCartItem(Long userId,AddItemRequest req)throws ProductException;
    public Cart findUserCart(Long userId);
	
}
