package model;

import java.util.concurrent.atomic.AtomicInteger;


public class Book {
	protected static AtomicInteger count = new AtomicInteger(0); 
	protected int id;
	private String genre;
	private String title;
	private String author;
	private int price;
	private int stock;
	
	public Book() {
		id = count.incrementAndGet();
	}
	
	public Book(String genre, String title, String author, int price, int stock) {
		this.id = count.incrementAndGet();
		this.genre = genre;
		this.title = title;
		this.author = author;
		this.price = price;
		this.stock = stock;
	}
	
	public Book(Book book, int stock) {
		this.id = book.getId();
		this.genre = book.getGenre();
		this.title = book.getTitle();
		this.author = book.getAuthor();
		this.price = book.getPrice();
		this.stock = stock;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the stock
	 */
	public int getStock() {
		return stock;
	}

	/**
	 * @param stock the stock to set
	 */
	public void setStock(int stock) {
		this.stock = stock;
	}

	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * @return the genre
	 */
	public String getGenre() {
		return genre;
	}
	/**
	 * @param genre the genre to set
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

}
