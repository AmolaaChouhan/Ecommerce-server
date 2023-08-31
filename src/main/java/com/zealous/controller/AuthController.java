package com.zealous.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zealous.config.JwtProvider;
//import com.zealous.config.JwtTokenProvider;
import com.zealous.exception.UserException;
import com.zealous.model.Cart;
//import com.zealous.model.Cart;
import com.zealous.model.User;
import com.zealous.repository.UserRepository;
import com.zealous.request.LoginResquest;
import com.zealous.service.CartService;
//import com.zealous.request.LoginRequest;
//import com.zealous.response.AuthResponse;
//import com.zealous.service.CartService;
import com.zealous.service.CustomerUserServiceImplementation;
import com.zealous.response.AuthResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	private UserRepository userRepository;
	private JwtProvider jwtProvider;
	private PasswordEncoder passwordEncoder;
	private CustomerUserServiceImplementation customerUserService;
	private CartService cartService;
	public AuthController(UserRepository userRepository,PasswordEncoder passwordEncoder,CustomerUserServiceImplementation customerUserService,JwtProvider jwtProvider,CartService cartService) {
		this.userRepository=userRepository;
		this.customerUserService=customerUserService;
		this.passwordEncoder=passwordEncoder;
		this.jwtProvider=jwtProvider;
		this.cartService=cartService;
	}
	
	
	@PostMapping("/signup")
	public ResponseEntity<AuthResponse>createUserHandler(@RequestBody User user) throws UserException{
		String email=user.getEmail();
		String password=user.getPassword();
		String firstName = user.getFirstName();
		String lastName =user.getLastName();
		
		User isEmailExist=userRepository.findByEmail(email);
		
		if(isEmailExist!=null) {
			throw new UserException("Email is already used with another account");
		}
		
		
		User createdUser = new User();
		createdUser.setEmail(email);
		createdUser.setPassword(passwordEncoder.encode(password));
		createdUser.setFirstName(firstName);
		createdUser.setLastName(lastName);
		
		User savedUser = userRepository.save(createdUser);
		Cart cart=cartService.createCart(savedUser);
		
		Authentication authentication =new UsernamePasswordAuthenticationToken(savedUser.getEmail(),savedUser.getPassword());
		SecurityContextHolder.getContext()
.setAuthentication(authentication);
		
		String token=jwtProvider.generateToken(authentication);
		AuthResponse authResponse = new AuthResponse();
		
		authResponse.setJwt(token);
		authResponse.setMessage("SignUp Success");
		return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.CREATED);
		}
	
	@PostMapping("signin")
	public ResponseEntity<AuthResponse>loginUserHandler(@RequestBody LoginResquest loginRequest){
		
		String username=loginRequest.getEmail();
		String password=loginRequest.getPassword();
		
		Authentication authentication = authenticate(username,password);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token=jwtProvider.generateToken(authentication);
		
AuthResponse authResponse1 = new AuthResponse();
		
		authResponse1.setJwt(token);
		authResponse1.setMessage("SignIn Success");
		
		return new ResponseEntity<AuthResponse>(authResponse1,HttpStatus.CREATED);
		
	}


	private Authentication authenticate(String username, String password) {
		// TODO Auto-generated method stub
		UserDetails userDetails = customerUserService.loadUserByUsername(username);
		
		if(userDetails==null) {
			throw new BadCredentialsException("Invalid username");
		}
		
		if(!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("Invalid Password..");
		}
		
		
		return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
	}
	

}
