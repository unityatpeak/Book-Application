package com.book.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.book.bean.Book;
import com.book.bean.Customer;
import com.book.exception.BookException;
import com.book.pl.BookMain;
import com.book.util.DBConnection;


public class BookDaoImpl implements BookDao{
	Logger logger=Logger.getRootLogger();
	public BookDaoImpl()
	{
	PropertyConfigurator.configure("resources//log4j.properties");
	
	}
	
	//------------------------ 1. Book Application --------------------------
		/*******************************************************************************************************
		 - Function Name	:	addBookDetails(DonorBean donor)
		 - Input Parameters	:	Book book
		 - Return Type		:	String
		 - Throws			:  	BookException
		 - Author			:	Divya
		 - Creation Date	:	18/12/2018
		 - Description		:	Adding Book
		 ********************************************************************************************************/
	
	@Override
	public String addBook(Book book) throws BookException, ClassNotFoundException, IOException, SQLException {
		Connection con=DBConnection.getConnection();
		PreparedStatement pst=null;
		//PreparedStatement pst1=null;
		ResultSet resultSet=null;
		String bookId=null;
		int queryResult=0;
		try
		{
		
		 pst=con.prepareStatement(QueryMapper.Add_Book);
		pst.setString(1,book.getBookName());
		pst.setString(2,book.getAuthorName());
		pst.setInt(3,book.getBookPrice());
		queryResult=pst.executeUpdate();
		//System.out.println("inserted");
		Statement st=con.createStatement();
		
		resultSet=st.executeQuery(QueryMapper.Add_Book_Seq);
		while(resultSet.next())
		{
			bookId=resultSet.getString(1);
		}
		
		//return bookId;
		if(queryResult==0)
		{
			logger.error("Insertion failed ");
			throw new BookException("Inserting book details failed ");
		

		}
		else
		{
			logger.info("book details added successfully:");
			return bookId;
		}
		}catch(SQLException sql)
		{
			logger.error(sql.getMessage());
			System.out.println(sql);
		}
	
		return bookId;
	}

	
	//------------------------ 1.Book Application --------------------------
		/*******************************************************************************************************
		 - Function Name	:	viewBookDetails(String donorId)
		 - Input Parameters	:	Bookname
		 - Return Type		:	Book
		 - Throws			:  	BookException
		 - Author			:	Divya
		 - Creation Date	:	18/12/2018
		 - Description		:	ViewBookDetails
		 ********************************************************************************************************/
	@Override
	public Book viewBookDetails(String bookName) throws BookException, ClassNotFoundException, IOException, SQLException {
		Connection connection=DBConnection.getConnection();
		ResultSet resultSet=null;
		PreparedStatement pst=null;
		Scanner sc=new Scanner(System.in);
		Book book=new Book();
		
		pst=connection.prepareStatement(QueryMapper.View_book_details);
		 //bookName=sc.nextLine();
		pst.setString(1,sc.next());
		resultSet=pst.executeQuery();
		while(resultSet.next())
		{
			
			book.setBookId(resultSet.getString(1));
			book.setBookName(resultSet.getString(2));
			book.setAuthorName(resultSet.getString(3));
			book.setBookPrice(resultSet.getInt(4));
		   // return book;
		}
		//return null;
		if( book != null)
		{
			logger.info("Record Found Successfully");
			return book;
		}
		else
		{
			logger.info("Record Not Found Successfully");
			return null;
		}
		
}
	//------------------------ 1. Book Application --------------------------
		/*******************************************************************************************************
		 - Function Name	:	retriveAllDetails()
		 - Input Parameters	:	
		 - Return Type		:	List
		 - Throws		    :  	BookException
		 - Author	     	:	Divya
		 - Creation Date	:	18/12/2018
		 - Description		:	retrive details
		 ********************************************************************************************************/

	@Override
	public List retriveAll() throws BookException, ClassNotFoundException, IOException, SQLException {
		Connection con=DBConnection.getConnection();
		int bookCount = 0;
		
		PreparedStatement ps=null;
		ResultSet resultset = null;
		
		List<Book> bookList=new ArrayList<Book>();
		try
		{
			ps=con.prepareStatement(QueryMapper.Retrive_All);
			resultset=ps.executeQuery();
			
			while(resultset.next())
			{	
				Book book=new Book();
				book.setBookId(resultset.getString(1));
				book.setBookName(resultset.getString(2));
				book.setAuthorName(resultset.getString(3));
				book.setBookPrice(resultset.getInt(4));
				
				bookList.add(book);
			bookCount++;
			}			
			
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
			logger.error(sqlException.getMessage());
			throw new BookException("Error in closing db connection");
			
		}
		
		finally
		{
			try 
			{
				resultset.close();
				ps.close();
				con.close();
			} 
			catch (SQLException e) 
			{
				logger.error(e.getMessage());
				e.printStackTrace();
				throw new BookException("Error in closing db connection");

			}
		}
		
		if( bookCount == 0)
			return null;
		else
			return  bookList;
	
	}
	
	//------------------------ 1. Book Application --------------------------
		/*******************************************************************************************************
		 - Function Name	:	deleteBookDetails()
		 - Input Parameters	:	
		 - Return Type		:	String
		 - Throws		    :  	BookException
		 - Author	     	:	Divya
		 - Creation Date	:	18/12/2018
		 - Description		:	deleting book
		 ********************************************************************************************************/
	
	@Override
	public String deleteBook(Book book) throws ClassNotFoundException, IOException, SQLException {
		Connection con=DBConnection.getConnection();
		PreparedStatement pst=null;
		//PreparedStatement pst1=null;
		Scanner sc=new Scanner(System.in);
        ResultSet resultSet=null;
		String bookId=null;
		String bookName=null;
		int queryResult=0;
		try
		{
		
		 pst=con.prepareStatement(QueryMapper.Delete_book);
		//pst.setString(1,book.getBookName());
		 pst.setString(1,sc.next());
		pst.executeUpdate();
       //String bookName =null;
		
		//resultSet=pst.executeQuery("select max(bid) from book_detail");
           Statement st=con.createStatement();
			resultSet=st.executeQuery(QueryMapper. Delete_book_sequence);
			while(resultSet.next())
			{
				
				bookId=resultSet.getString(1);
			
			}
			return bookId;
		
		}
		catch(SQLException sql)
		{
			System.out.println(sql);
			sql.printStackTrace();
		}
		
		
			return bookId;
		}

	//------------------------ 1. Book Application --------------------------
			/*******************************************************************************************************
			 - Function Name	:	deleteBookDetails()
			 - Input Parameters	:	
			 - Return Type		:	String
			 - Throws		    :  	BookException
			 - Author	     	:	Divya
			 - Creation Date	:	18/12/2018
			 - Description		:	Add customer details
			 ********************************************************************************************************/
	

	@Override
	public String addCustomer(Customer customer) throws ClassNotFoundException, IOException, SQLException {
		Connection connection=DBConnection.getConnection();
		PreparedStatement pst=null;
		//PreparedStatement pst1=null;
		ResultSet resultSet=null;
		String customerId=null;
		int queryResult=0;
		try
		{
		pst=connection.prepareStatement(QueryMapper.Add_customer);
		pst.setString(1,customer.getCustomerName());
		pst.setString(2,customer.getPhno());
		pst.setString(3, customer.getEmailid());
		pst.setString(4,customer.getAddress());
		pst.executeUpdate();
     Statement st=connection.createStatement();
		
	resultSet=st.executeQuery(QueryMapper.Add_customer_seq);
		while(resultSet.next())
		{
			customerId=resultSet.getString(1);
		}
		
		return customerId;
	
	
		
		}catch(SQLException sql)
		{
			System.out.println(sql);
		}
		
			return customerId;
	
	}

	@Override
	public List<Book> calPrice(String[] arr) throws ClassNotFoundException, IOException, SQLException {
		Connection con=DBConnection.getConnection();
		
	    PreparedStatement ps=null;
		ResultSet resultset = null;
		
		List<Book> bookList=new ArrayList<Book>();
		try
		{
			int sum=0;
			for(int i=0;i<=arr.length;i++) {
				
			ps=con.prepareStatement("SELECT * FROM book_detail");
			resultset=ps.executeQuery();
			
			while(resultset.next())
			{	
				Book book=new Book();
				book.setBookId(resultset.getString(1));
				book.setAuthorName(resultset.getString(3));
				book.setBookName(resultset.getString(2));
				sum = sum + Integer.parseInt(resultset.getString(4));
				//int j=book.setBookPrice(resultset.getInt(4));
				bookList.add(book);
				
			}
			System.out.println("sum of books is: "+sum);
		}
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
			
		}
		
		return bookList;
	}
	public Book updateDetails(int bookPrice, String bookName) throws BookException, ClassNotFoundException, IOException, SQLException {

		Connection connection = DBConnection.getConnection();
		ResultSet resultSet = null;
		Statement st = connection.createStatement();
		PreparedStatement pst = null;
		Book book = null;
		try {
			
			pst = connection.prepareStatement(QueryMapper.update_details);
			pst.setInt(1, bookPrice);
			pst.setString(2,bookName);
			pst.executeUpdate();
		
			System.out.println(bookName);
			
			pst=connection.prepareStatement(QueryMapper.update_details_seq);
			pst.setString(1,bookName);
			resultSet=pst.executeQuery();
			
			//resultSet = st.executeQuery("select * from book_detail where bname='" + bookName + "'");

			while (resultSet.next()) {
				book = new Book();
				book.setBookName(resultSet.getString(1));
				book.setBookPrice(resultSet.getInt(4));
				//System.out.println("i a");
				return book;

			}

		} catch (SQLException e) {
			 System.out.println(e);
		}
	
		
				return null;
	}

	
}
	
	


