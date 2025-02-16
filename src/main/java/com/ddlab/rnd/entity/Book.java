package com.ddlab.rnd.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class Book {

	private String isbnNo;
	private String name;
	private List<Author> authors;
}
