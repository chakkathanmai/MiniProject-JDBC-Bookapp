package com.bookapp.main;

import com.bookapp.bean.Book;
import com.bookapp.exception.AuthorNotFoundException;
import com.bookapp.exception.BookNotFoundException;
import com.bookapp.exception.CategoryNotFoundException;
import com.bookapp.exception.IdNotFoundException;
import com.bookapp.dao.BookImpl;
import com.bookapp.dao.BookInter;
import java.util.Scanner;

/**
 * @author ThanmaiChakka
 * @version 2.0
 * @date 10/11/21
 */
public class Client {

	public static void main(String[] args) { // main class

		// BookInter bookInter = new BookImpl(); //imp bookObject creation
		// System.out.println(bookInter.getAllBooks());

		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to Book World...");
		System.out.println();
		while (true) {
		System.out.println(
				"1.Add Book Details \n2.Delete Book Details\n3.Update Book Price\n4.Book Details\n5.Get by Author Name\n6.Get by Category Name\n7.Get by Book Id");
		System.out.println();
		System.out.println("Select your option here");
		System.out.println();

		BookInter bookObj = new BookImpl();
		
			int n = sc.nextInt();
			switch (n) {
			case 1:
				sc.nextLine();
				System.out.println("Enter Title");
				String title = sc.nextLine();
				System.out.println("Enter Author Name");
				String author = sc.nextLine();
				System.out.println("Enter Category");
				String category = sc.nextLine();
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
				bookObj.addBook(book);
				break;
			case 2:
				try {
					System.out.println("Enter the Book Id to Delete");
					int id1 = sc.nextInt();
					bookObj.deleteBook(id1);

				} catch (BookNotFoundException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 3:
				System.out.println("Enter Book ID and Price to Update");
				int id2 = sc.nextInt();
				int price1 = sc.nextInt();
				try {
					bookObj.updateBook(id2, price1);
				} catch (BookNotFoundException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 4:
				System.out.println(bookObj.getAllBooks());
				break;

			case 5:
				System.out.println("Enter Author Name");
				String author1 = sc.next();
				try {
					System.out.println(bookObj.getBookbyAuthor(author1));
				} catch (AuthorNotFoundException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
				break;

			case 6:
				System.out.println("Enter Category Name");
				String category1 = sc.next();
				try {
					System.out.println(bookObj.getBookbycategory(category1));
				} catch (CategoryNotFoundException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 7:
				System.out.println("Enter Book Id");
				int id=sc.nextInt();
				try {
					System.out.println(bookObj.getBookById(id));
				} catch (IdNotFoundException e) {
					System.out.println(e.getMessage());
				}
			}
			System.out.println("\n Thank You");
			System.out.println();
			System.out.println();

		}
		
	}
}
