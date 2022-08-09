package com.codewithdurgesh.blog.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="comments")
@Getter
@Setter
public class Comment {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String content;
	
	@ManyToOne
	private Post post;//ek post par kai sare comment honge to hum chalenge post pars aur post mein lagaienge @onetomany kyunki
	                    //ek post mein kai sare comment h aur yha par @manytoone(bi-directional mapping)
}
