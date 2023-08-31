package com.zealous.service;

import java.util.List;

import com.zealous.exception.ProductException;
import com.zealous.model.Review;
import com.zealous.model.User;
import com.zealous.request.ReviewRequest;

public interface ReviewService {
public Review createReview(ReviewRequest req,User user) throws ProductException;
	
	public List<Review> getAllReview(Long productId);
}
