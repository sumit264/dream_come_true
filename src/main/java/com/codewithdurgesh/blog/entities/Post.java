package com.codewithdurgesh.blog.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Posts")
@Getter
@Setter
@NoArgsConstructor
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postId;

	@Column(name = "post_title", length = 100, nullable = false)
	private String title;
	@Column(length = 10000)
	private String content;

	private String imageName;

	private Date addDate;

	@ManyToOne //multiple post single categoy se map honge (Bi-directional mapping)
	@JoinColumn(name = "category_Id")
	private Category category;

	@ManyToOne //multiple post ek single user kar sakta h (Bi-directional mapping)
	private User user;
	
	@OneToMany(mappedBy = "post",cascade = CascadeType.ALL) //mappedby =post likha jisse ki o foreign ki bane wo comment table mein bane
	private Set<Comment> comments=new HashSet<>();
}
