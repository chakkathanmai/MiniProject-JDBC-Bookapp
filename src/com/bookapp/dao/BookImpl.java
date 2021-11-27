package com.bookapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bookapp.bean.Book;
import com.bookapp.dao.ModelDAO;
import com.bookapp.exception.AuthorNotFoundException;
import com.bookapp.exception.BookNotFoundException;
import com.bookapp.exception.CategoryNotFoundException;
import com.bookapp.exception.IdNotFoundException;

public class BookImpl implements BookInter {

//	String insertQuery = "insert into book values(?,?,?,?,?)"; // query to add book
//	String updateBookQuery = "update book set price=? where bookid=?"; // query to update book
//	String deleteQuery = "delete from book where bookid=?"; // query to delete book
//	String authorQuery = "select * from book where author=?"; // query to search book by author
//	String categoryQuery = "select * from book where category=?"; // query to search book by category
//	String getAllBooksQuery = "select * from book"; // query to retrieve all books
	PreparedStatement stmt = null; // Interfaces
	Connection connection = null; // Interfaces

	@Override
	public void addBook(Book book) {
		// TODO Auto-generated method stub

		try {
			String sql = "insert into Book values(?,?,?,?,?)";
			connection = ModelDAO.openConnection();
			stmt = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.setString(1, book.getTitle());
			stmt.setString(2, book.getAuthor());
			stmt.setString(3, book.getCategory());
			stmt.setInt(4, book.getBookid());
			stmt.setInt(5, book.getPrice());
			stmt.execute();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {

				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ModelDAO.closeConnection();
		}
	}
	@Override
	public void deleteBook(int bookid) throws BookNotFoundException {
		// TODO Auto-generated method stub
		boolean val = false;
		try {
			String delsql = "delete from Book where bookid=?";
			connection = ModelDAO.openConnection();
			stmt = connection.prepareStatement(delsql, ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_SCROLL_INSENSITIVE);
			stmt.setInt(1, bookid);
			val = stmt.execute();
			System.out.println("Book Deleted");

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {

				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ModelDAO.closeConnection();
		}
		if (val == true) {
			throw new BookNotFoundException("Invalid Book id");
		}
		return;
	}

	@Override
	public int updateBook(int bookid, int price)throws BookNotFoundException {
		// TODO Auto-generated method stub
		int rs = 0;
		try {
			String updsql = "update Book set price=? where bookId=?";
			connection = ModelDAO.openConnection();
			stmt = connection.prepareStatement(updsql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.setInt(1, price);
			stmt.setInt(2, bookid);
			rs = stmt.executeUpdate();
			if (rs == 0) {
				throw new BookNotFoundException("not found");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {

				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ModelDAO.closeConnection();
		}
		return rs;
	}

	@Override
	public List<Book> getAllBooks() {
		// TODO Auto-generated method stub
		List<Book> getAllBooks = new ArrayList<>();
		try {

			String selsql = "select * from Book";
			connection = ModelDAO.openConnection();
			stmt = connection.prepareStatement(selsql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Book book = new Book();
				book.setTitle(rs.getString(1));
				book.setAuthor(rs.getString(2));
				book.setCategory(rs.getString(3));
				book.setBookid(rs.getInt(4));
				book.setPrice(rs.getInt(5));
				getAllBooks.add(book);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {

				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ModelDAO.closeConnection();
		}
		return getAllBooks;
	}

	@Override
	public List<Book> getBookbyAuthor(String author) throws AuthorNotFoundException {
		// TODO Auto-generated method stub
		List<Book> getBooksbyAuthor = new ArrayList<>();
		try {
			String selsql1 = "select * from Book where author=?";
			connection = ModelDAO.openConnection();
			stmt = connection.prepareStatement(selsql1, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			stmt.setString(1, author);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Book book = new Book();
				book.setTitle(rs.getString(1));
				book.setAuthor(rs.getString(2));
				book.setCategory(rs.getString(3));
				book.setBookid(rs.getInt(4));
				book.setPrice(rs.getInt(5));
				getBooksbyAuthor.add(book);

			}
			if (getBooksbyAuthor.isEmpty()) {
				throw new AuthorNotFoundException("Invalid Author name");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {

				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ModelDAO.closeConnection();
		}

		return getBooksbyAuthor;
	}
	@Override
	public List<Book> getBookbycategory(String category) throws CategoryNotFoundException {
		// TODO Auto-generated method stub
		List<Book> getBooksbyCategory = new ArrayList<>();

		try {
			String selsql1 = "select * from Book where category=?";
			connection = ModelDAO.openConnection();
			stmt = connection.prepareStatement(selsql1, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			stmt.setString(1, category);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Book book = new Book();
				book.setTitle(rs.getString(1));
				book.setAuthor(rs.getString(2));
				book.setCategory(rs.getString(3));
				book.setBookid(rs.getInt(4));
				book.setPrice(rs.getInt(5));
				getBooksbyCategory.add(book);
			}
			if (getBooksbyCategory.isEmpty()) {
				throw new CategoryNotFoundException("Invalid Category name");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {

				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ModelDAO.closeConnection();
		}
		return getBooksbyCategory;
	}
	@Override
	public List<Book> getBookById(int id) throws IdNotFoundException {
		List<Book> getBookById=new ArrayList<Book>();
		try {
			String selsql1 = "select * from Book where bookId=?";
			connection = ModelDAO.openConnection();
			stmt = connection.prepareStatement(selsql1, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Book book = new Book();
				book.setTitle(rs.getString(1));
				book.setAuthor(rs.getString(2));
				book.setCategory(rs.getString(3));
				book.setBookid(rs.getInt(4));
				book.setPrice(rs.getInt(5));
				getBookById.add(book);
			}
			if (getBookById.isEmpty()) {
				throw new IdNotFoundException("Invalid book id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {

				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ModelDAO.closeConnection();
		}
		return getBookById;
	}
	
}