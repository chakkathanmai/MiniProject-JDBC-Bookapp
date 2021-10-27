package com.bookapp.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.bookapp.bean.Book;
import com.bookapp.exception.AuthorNotFoundException;
import com.bookapp.exception.CategoryNotFoundException;

public class BookImpl implements BookInter {
    List<Book> bookList=new ArrayList<>();
	@Override
	public void addBook(Book book) {
		// TODO Auto-generated method stub
		bookList.add(book);
		
	}

	public List<Book> bunchOfBooks() {
		
		return bookList =Arrays.asList(new Book("5Am club","Robin","selfgrowth",21,450),
				new Book("Life Is What You Make","Shenoy","Fiction",22,250),
				new Book("Wings of Fire","APJ","Selfgrowth",23,170),
				new Book("How Not TO DIe","Michael","Health",24,508),
				new Book("The Story of My Experiments With Truth","MahatmaGandhi","Autobiography",25,500),
				new Book("Learning How To Fly","APJ","Selfgrowth",26,150),
				new Book("Letters of mahatma gandhi","mahatmaGandhi","selfgrowth",27,170),
				new Book("Wake up life is calling","Shenoy","Fiction",28,130),
				new Book("My health my hand","Darbesh","Health",28,1900));
	}

	@Override
	public List<Book> getBookbyAuthor(String author) throws AuthorNotFoundException {
		// TODO Auto-generated method stub
		List<Book> bookList=bunchOfBooks();
		List<Book> bookAuthor=new ArrayList<>();
		
		for(Book book:bookList) {
			if(author.toLowerCase().equals(book.getAuthor().toLowerCase())) {
				bookAuthor.add(book);
				System.out.println(book);
			}
		}
		if(bookAuthor.size()==0) {
			throw new AuthorNotFoundException("Author Not Found");
		}
		return bookAuthor;
		//return null;
	}

	@Override
	public List<Book> getBookbycategory(String category) throws CategoryNotFoundException {
		// TODO Auto-generated method stub
		List<Book> bookList=bunchOfBooks();
		List<Book> bookCategory=new ArrayList<>();
		
		for(Book book:bookList) {
			if(category.equals(book.getCategory())) {
				bookCategory.add(book);
				System.out.println(book);
			}	
	}if(bookCategory.size()==0) {
				throw new CategoryNotFoundException("Category Not Found");
			}
		return bookCategory;
	}	
	@Override
	public List<Book> getAllBooks() {
		// TODO Auto-generated method stub
		Collections.sort(bookList,(book1,book2)->{
			return book1.getTitle().compareTo(book2.getTitle());
		});
		return bookList;
	}
	}
