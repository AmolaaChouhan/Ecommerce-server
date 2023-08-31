package com.zealous.service;

import com.zealous.exception.UserException;
import com.zealous.model.User;

public interface UserService {
	
	public User findUserById(Long userId) throws UserException;
	
	public User findUserProfileByJwt(String jwt) throws UserException;

}
