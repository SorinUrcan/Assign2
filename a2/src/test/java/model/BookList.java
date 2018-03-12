package model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "books")
public class BookList {
	@XmlElement(name = "book", type = Book.class)
	private List<Book> books = new ArrayList<Book>();

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
	public boolean find(int id) {
		for (Book user : books) {
			if (user.getId() == id)
				return true;
		}
		return false;
	}
	
	public Book findBook(int id) {
		for (Book user : books) {
			if (user.getId() == id)
				return user;
		}
		return null;
	}
}
