package com.codewithdurgesh.blog.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // For Auto table Generation with class name
@Table(name = "users") // For Table Generation with Nam e

@NoArgsConstructor // using lombok we can create no argument constructor internal
@Getter // using lombok we can create getter and setter automatically
@Setter
public class User implements UserDetails{ //implement User Detail for Spring Security mechanism achive

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) // For Auto generation of Key
	private int id;

	@Column(name = "user_name", nullable = false, length = 100) // for defining the coulumn name and value
	private String name;
	private String email;
	private String password;
	private String about;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // ek user multiple post kar sakta
																						// h
	private List<Post> posts = new ArrayList<>();

	@ManyToMany (cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user", referencedColumnName = "id"), 
	inverseJoinColumns = @JoinColumn(name = "role", referencedColumnName = "id"))
	private Set<Role> roles = new HashSet<>();
	// We will keep this class seprate and User Dto class will use for data transfer
	// because it may possible that some field
	// we need to make seprate and calculate dynamically that we are not getting
	// from user.
	
	//implement User Detail for Spring Security mechanism achives

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
   
		List<SimpleGrantedAuthority> authorities = this.roles.stream().map((role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
		
		return authorities;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	// mainly we will use this class for database purpose only
}
