package com.bookapp.main;

import com.bookapp.bean.Book;
import com.bookapp.exception.AuthorNotFoundException;
import com.bookapp.exception.BookNotFoundException;
import com.bookapp.exception.CategoryNotFoundException;
import com.bookapp.dao.BookImpl;
import com.bookapp.dao.BookInter;
import java.util.Scanner;

/**
 * @author ThanmaiChakka
 * @version 2.0
 * @date 10/11/21
 */
public class Client {

	public static void main(String[] args) {  //main class

		//BookInter bookInter = new BookImpl();    //imp object creation
		// System.out.println(bookInter.getAllBooks());

		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to Book World...");
		System.out.println();
		
		System.out.println(
				"1.Add Books Details \n2.Get By Id\n3.Delete Book Details\n4.Update Book Details\n5.Book Details\n6.Author Name\n7.Category Name");
		System.out.println();
		System.out.println("Select your option here");
		System.out.println();
		
		BookInter obj = new BookImpl();
		int n = sc.nextInt();
		switch (n) {
		case 1:
			System.out.println("Enter Title");
			String title = sc.next();
			System.out.println("Enter Author Name");
			String author = sc.next();
			System.out.println("Enter Category");
			String category = sc.next();
			System.out.println("Enter Book Id");
			int bookid = sc.nextInt();
			System.out.println("Enter Price");
			int price = sc.nextInt();
			Book book = new Book();
			book.setTitle(title);
			book.setAuthor(author);
			book.setCategory(category);
			book.setBookid(bookid);
			book.setPrice(price);
			obj.addBook(book);
			break;
		case 2:
			try {
				System.out.println("Enter the Book Id to Delete");
				int id1 = sc.nextInt();
				System.out.println(obj.deleteBook(id1));
				
			} catch (BookNotFoundException e) {
				System.out.println(e.getMessage());
			}
			break;
		case 3:
			System.out.println("Enter Book ID and Price to Update");
			int id2 = sc.nextInt();
			int price1 = sc.nextInt();
			try {
				obj.updateBook(id2, price1);
			} catch (BookNotFoundException e) {
				System.out.println(e.getMessage());
			}
			break;
		case 4:
			System.out.println(obj.getAllBooks());
			break;

		case 5:
			System.out.println("Enter Author Name");
			String author1 = sc.next();
			try {
				System.out.println(obj.getBookbyAuthor(author1));
			} catch (AuthorNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
			break;

		case 6:
			System.out.println("Enter Category Name");
			String category1 = sc.next();
			try {
				System.out.println(obj.getBookbycategory(category1));
			} catch (CategoryNotFoundException e) {
				System.out.println(e.getMessage());
			}
		}
		System.out.println("Thank You");
     sc.close();
	}
}
