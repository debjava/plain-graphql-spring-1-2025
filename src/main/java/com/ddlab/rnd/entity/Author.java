package com.ddlab.rnd.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class Author {

	private long id;
	private String name;
	private List<Book> books;
}
