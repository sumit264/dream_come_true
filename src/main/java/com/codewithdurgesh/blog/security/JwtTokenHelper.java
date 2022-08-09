package com.codewithdurgesh.blog.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenHelper {
	
	public static final long JWT_TOKEN_VALIDITY= 5 * 60 * 60;
	
	private String secret="jwtTokenKey";
	
	// Retrieve userName from jwt token
	
	public String getUsernameFromToken(String token) {
		
		return getClaimFromToken(token, Claims::getSubject);
	}
	

	// Retrieve expiration date from jwt token
	
	/*
	 * public Date getExpirationDateFromToken(String token) {
	 * 
	 * 
	 * return getClaimFromToken(token, Claims::getExpiration);
	 * 
	 * }
	 */
	
	public Date getExpirationDateFromToken(String token) {
	    Date expiration;
	    try {
	      final Claims claims = this.getClaimsFromToken(token);
	      
	      System.out.println("======claims====="+claims);
	      expiration = claims.getExpiration();
	    } catch (Exception e) {
	      expiration = null;
	    }
	    return expiration;
	  }
	
	private Claims getClaimsFromToken(String token) {
	    Claims claims;
	    try {
	      claims = Jwts.parser()
	        .setSigningKey(this.secret)
	        .parseClaimsJws(token)
	        .getBody();
	    } catch (Exception e) {
	      claims = null;
	    }
	    return claims;
	  }
	
	public <T> T getClaimFromToken(String token,Function<Claims,T> claimsResolver) {
		
		final Claims claims =getAllClaimsFromToken(token);
		
		return claimsResolver.apply(claims);
	}
	
	private Claims getAllClaimsFromToken(String token) {
		
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
				
	}
	
	//Check if the token has expired
	
	private Boolean isTokenExpired(String token) {
		
		final Date expiration=getExpirationDateFromToken(token);
		
		System.out.println("=======Expired Date===="+expiration);
		//return expiration.after(expiration);
		
		return expiration.before(new Date());
	}
	
	//generate Token for User
	
	public String generateToken(UserDetails userDetails) {
		
		Map<String, Object> claims=new HashMap<>();
		
		return doGenerateToken(claims,userDetails.getUsername());
	}
	
	private String doGenerateToken(Map<String, Object> claims,String subject) {
		
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}
	
	//validate Token
	
	public Boolean validateToken(String token, UserDetails userDetails) {
		
		final String userName=getUsernameFromToken(token);
		
		System.out.println("===userName===="+userName+"===" +userDetails.getUsername());

		System.out.println("===token===="+token);
		
		return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
