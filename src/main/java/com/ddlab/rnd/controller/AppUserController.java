package com.ddlab.rnd.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.ContextValue;
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

import graphql.GraphQLContext;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

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
	public AppResult deleteAppUser(@ContextValue(name = "myHeader1") String header1,
			@ContextValue(name = "myHeader2") String header2, @Argument String userId) {
		System.out.println("What is the userId to be deleted: "+userId);
		System.out.println("What is the header1: ?"+header1);
		System.out.println("What is the header2: ?"+header2);
		return new AppResult("AppUser information deleted successfully ...");
	}
	
	@QueryMapping
	public List<String> getAllPhoneNosByNames(@Argument List<String> names) {
		return List.of("111", "222", "333");
	}
	
	@QueryMapping
	public List<AppUser> getAllAppUsers() {
		List<AppUser> users = new ArrayList<>();
		PodamFactory factory = new PodamFactoryImpl();
		AppUser appUser1 = factory.manufacturePojo(AppUser.class);
		users.add(AppUtil.getDefaultUser(Long.valueOf(123)));
		users.add(appUser1);
		return users;
	}
	
	@QueryMapping
	public Book getBookByName(GraphQLContext context, @Argument String name) {
		Book book = new Book();
		book.setIsbnNo("Some ISBN No");
		book.setName("A Good Book");
		List<Author> authors = List.of(
				new Author(111, "James Bond", List.of(book)), 
				new Author(333, "Charles Dickens", List.of(book))
			);
		book.setAuthors(authors);
		context.put("ResponseHeader1", "Value-1");
		context.put("ResponseHeader2", "Value-2");
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
