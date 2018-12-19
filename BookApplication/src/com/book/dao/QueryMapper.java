package com.book.dao;

public interface QueryMapper {

	String Add_Book="insert into book_detail values(b_seq.nextval,?,?,?)";
	String Add_Book_Seq="select b_seq.currval from dual";
	String View_book_details="select * from book_detail where bname=?";
	String Retrive_All="SELECT * FROM book_detail";
	String Delete_book="delete from book_detail where bname=?";
	String Delete_book_sequence="select max(bid) from book_detail";
	String Add_customer="insert into customer_details values(customer_seq.nextval,?,?,?,?)";
	String Add_customer_seq="select MAX(cid) from customer_details";
	String update_details="update  book_detail  SET bprice=? where bname= ?";
	String update_details_seq="select * from book_detail where bname= ?";
}
