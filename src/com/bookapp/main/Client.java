package com.bookapp.main;

import com.bookapp.bean.Book;
import com.bookapp.exception.AuthorNotFoundException;
import com.bookapp.exception.CategoryNotFoundException;
import com.bookapp.service.BookImpl;
import com.bookapp.service.BookInter;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {

		BookInter bookInter = new BookImpl();
		// System.out.println(bookInter.getAllBooks());

		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to Book World...");
		System.out.println();
		System.out.println("Select your option by entering number");
		System.out.println("1.Add Book");
		System.out.println("2.Search for Book");

		int choice = sc.nextInt();
		int searchOption = 0;
		if (choice == 1) {
			System.out.println("Enter the name of Book");
	            String title = sc.next();

	            System.out.println("Enter the author name");
	            String author = sc.next();

	            System.out.println("Enter Your book Category");
	            String category = sc.next();

	            System.out.println("Enter Book Id");
	            int bookId = sc.nextInt();

	            System.out.println("Enter Price Of Your Book");
	            int price = sc.nextInt();
	            System.out.println("Succesfully added\nThank you for Adding Your Book");
	        Book allBooks=new Book(title,author,category,bookId,price);
	        bookInter.addBook(allBooks);
	        
		} else if (choice == 2) {
			System.out.println("Select your search option by entering number");
			System.out.println("1.Search by Author\n2.Search by category");
			searchOption = sc.nextInt();
		}
		if (searchOption == 1) {
			System.out.println("Enter Author name");
			String author = sc.next();
			try {
				bookInter.getBookbyAuthor(author);
				// System.out.println(bookInter.getAllBooks());
			} catch (AuthorNotFoundException e) {
				System.out.println(e.getMessage());

			}
		} else if (searchOption == 2) {
			System.out.println("Enter Category");
			String category = sc.next();
			try {
				bookInter.getBookbycategory(category);
				// System.out.println(bookInter.bunchOfBooks());
			} catch (CategoryNotFoundException e) {
				System.out.println(e.getMessage());
			} finally {

			}
			sc.close();
		}
	}
}
