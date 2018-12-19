package com.book.test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

	import org.junit.After;
	import org.junit.AfterClass;
	import org.junit.Assert;
	import org.junit.Before;
	import org.junit.BeforeClass;
	import org.junit.Ignore;
	import org.junit.Test;

import com.book.bean.Book;
import com.book.dao.BookDaoImpl;
import com.book.exception.BookException;

	

	public class  BookDaoTest {

		static BookDaoImpl dao;
		static Book book;

		@BeforeClass
		public static void initialize() {
			System.out.println("in before class");
			dao = new BookDaoImpl();
			book = new Book();
		}

		@Test
		public void testAddBook() throws BookException, ClassNotFoundException, IOException, SQLException {

			assertNotNull(dao.addBook(book));

		}

		@Test
		public void testAddBook1() throws BookException, NumberFormatException, ClassNotFoundException, IOException, SQLException {

			book.setBookName("Java");
			book.setAuthorName("Robert");
			book.setBookPrice(100);
			
			assertTrue("Data Inserted successfully",
					Integer.parseInt(dao.addBook(book)) > 1000);

		}

		@Test
		public void testViewAll() throws BookException, ClassNotFoundException, IOException, SQLException {
			assertNotNull(dao.retriveAll());
		}

	@Test
	public void testdelete() throws BookException, ClassNotFoundException, IOException, SQLException {
		assertNotNull(dao.deleteBook(book));
	}
	
	@Test
      public void testdelete1() throws BookException, NumberFormatException, ClassNotFoundException, IOException, SQLException {
		book.setBookName("Java");
		
					assertTrue("Data deleted successfully", Integer.parseInt(dao.deleteBook(book)) >1000);
		
	
	}
	
		@Test
		public void testdelete2() throws BookException, ClassNotFoundException, IOException, SQLException {
			assertNotNull(dao.updateDetails(2000, "Java"));
		}
		
		
	}
