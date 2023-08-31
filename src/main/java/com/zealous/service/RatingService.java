package com.zealous.service;

import java.util.List;

import com.zealous.exception.ProductException;
import com.zealous.model.Rating;
import com.zealous.model.User;
import com.zealous.request.RatingRequest;

public interface RatingService {
	
	public Rating createRating(RatingRequest req,User user) throws ProductException;
	
	public List<Rating> getProductsRating(Long productId);

}

