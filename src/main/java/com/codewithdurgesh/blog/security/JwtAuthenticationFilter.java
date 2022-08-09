package com.codewithdurgesh.blog.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private JwtTokenHelper jwtTokenHelper;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// 1. get token (token aayga header mein to humein request ki help se milega)

		String requestToken = request.getHeader("Authorization"); // "Authorization key hogi jiski help se hum token ko
																	// get karenge"

		// Bearer 23252354sdgs token is format mein hoga
		System.out.println(requestToken);
		String username = null;// token se username ko nikalenge

		String token = null;

		if (requestToken != null && requestToken.startsWith("Bearer")) {

			token = requestToken.substring(7);
			System.out.println("============"+token);
			try {
				username = this.jwtTokenHelper.getUsernameFromToken(token);
			} catch (IllegalArgumentException e) {
				System.out.println("unable To get JWT Token !!");

			} catch (ExpiredJwtException e) {

				System.out.println("Jwt Token has Expired");
			} catch (MalformedJwtException e) {

				System.out.println("Invalid JWT");

			}

		} else {

			System.out.println("Jwt Token doen not begin with Bearer !!");
		}

		// once we get the token so we will validate the token

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

			if (this.jwtTokenHelper.validateToken(token, userDetails)) {

				// sab sahi chal rha h
				// Authentication karna h

				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				;

				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			} else {

				System.out.println("in valid JWT Token");
			}
		} else {
			System.out.println("UserName is Null or Context is Not Null");
		}
		filterChain.doFilter(request, response);
	}

}
