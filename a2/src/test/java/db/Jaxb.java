package db;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import model.BookList;
import model.UserList;

public class Jaxb {
	
	public static boolean saveUsers(UserList users) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(UserList.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			jaxbMarshaller.marshal(users, new File("users.xml"));
			return true;
			} catch (JAXBException e) {
				e.printStackTrace();
			}
		return false;
	}
	
	public static UserList loadUsers() {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(UserList.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			UserList users = (UserList) jaxbUnmarshaller.unmarshal(new File("users.xml"));
			return users;
		} catch (JAXBException e) {
	    	  e.printStackTrace();
	    }
		return null;
	}

	public static boolean saveBooks(BookList books) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(BookList.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			jaxbMarshaller.marshal(books, new File("books.xml"));
			return true;
			} catch (JAXBException e) {
				e.printStackTrace();
			}
		return false;
	}
	
	public static BookList loadBooks() {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(BookList.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			BookList books = (BookList) jaxbUnmarshaller.unmarshal(new File("books.xml"));
			return books;
		} catch (JAXBException e) {
	    	  e.printStackTrace();
	    }
		return null;
	}
}