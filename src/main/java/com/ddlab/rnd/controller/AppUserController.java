package com.ddlab.rnd.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.ddlab.rnd.dto.AppUserInput;
import com.ddlab.rnd.entity.AppResult;
import com.ddlab.rnd.entity.AppUser;
import com.ddlab.rnd.entity.Author;
import com.ddlab.rnd.entity.Book;
import com.ddlab.rnd.entity.ContractUser;
import com.ddlab.rnd.entity.FulltimeUser;
import com.ddlab.rnd.entity.User;
import com.ddlab.rnd.util.AppUtil;

@Controller
public class AppUserController {
	
	@MutationMapping
	public AppUser createAppUser(@Argument AppUserInput inUser) {
		System.out.println("User Details: "+inUser);
		return AppUtil.getDefaultUser(Long.valueOf(123));
	}
	
	@MutationMapping
	public String updateAppUserByAddress(@Argument AppUserInput inUser) {
		System.out.println("User Details for address update: "+inUser);
		return "AppUser address updated successfully ...";
	}
	
	@MutationMapping
	public AppResult deleteAppUser(@Argument String userId) {
		System.out.println("What is the userId to be deleted: "+userId);
		return new AppResult("AppUser information deleted successfully ...");
	}
	
	@QueryMapping
	public Book getBookByName(@Argument String name) {
		Book book = new Book();
		book.setIsbnNo("Some ISBN No");
		book.setName("A Good Book");
		List<Author> authors = List.of(
				new Author(111, "James Bond", List.of(book)), 
				new Author(333, "Charles Dickens", List.of(book))
			);
		book.setAuthors(authors);
		return book;
	}
	
	@QueryMapping
	public AppUser getAppUserById(@Argument Long id) {
		if(id == null) throw new NullPointerException("Id can't be null");
		return AppUtil.getDefaultUser(Long.valueOf(id));
	}
	
	@QueryMapping
	public Collection<User> getAllTypesOfUser() {
		return List.of(
				new ContractUser(123L,"John Abraham","Bangalore", List.of("111","222")),
				new FulltimeUser(456L,"Vidya Balan","Chennai", List.of("6666","999"))
				);
	}
	
	

}
