package control;

import java.util.List;

import db.Jaxb;
import model.Book;
import model.BookList;
import model.User;
import model.UserList;
import report.Report;
import report.ReportFactory;
import view.UIAdmin;
import view.UILogin;
import view.UIUser;

public class Control {
	static UILogin UILogin;
	static UIAdmin UIAdmin;
	static UIUser  UIUser;
	
    String username;
    String password;
    String userType;
    
    static BookList books;
    static UserList users;
    
    Control control;
    
    public static void main(String[] args){
        Control control = new Control();
        
		//users.add(new User("admin", "admin", "admin", "address"));
		//Jaxb.saveUsers();
		
        UILogin = new UILogin(control);
        UILogin.showLoginWindow();
        
        
        //temp
        //control = new Control();	
		//books = Jaxb.loadBooks();
		
        //UILogin = new UILogin(control);
        //UILogin.showLoginWindow();
		
		//UIUser = new UIUser(control);	
		//printUserBooks();
		//UIUser.showWindow();
        //temp
        
    }

	public void loginButtonClicked() {
		this.username = UILogin.getTextUsername();
		this.password = UILogin.getTextPassword();
		
		users = Jaxb.loadUsers();
		
		if (login(username, password).equals("admin")) {
			Control control = new Control();
			this.control = control;
			
			users = Jaxb.loadUsers();
			books = Jaxb.loadBooks();
			
			UIAdmin = new UIAdmin(control);
			
			printBooks();
			printUsers();
			
			UIAdmin.showWindow();
		} else if (login(username, password).equals("client")) {
			Control control = new Control();
			this.control = control;
			
			UIUser = new UIUser(control);
			
			books = Jaxb.loadBooks();
			printUserBooks();
			
			UIUser.showWindows();
		}
	}



	public String login(String username, String password) {
		return users.login(username, password);
	}

	//USERS SECTION
	public boolean isUsed(String username) {
		boolean answer = users.findUsername(username);
		return answer;
	}
	
	public User selectUser(String text) {
		return users.findUser(Integer.parseInt(text));
	}

	public void addUser(User user) {
		users.add(user);
		printUsers();
	}
	
	public static void printUsers() {
		List<User> userList = users.getUsers();
        StringBuilder output = new StringBuilder();
        output.append("User ID | Username | Password | Type | Address \n");
        for (User user : userList) {
        	output.append(user.getId());
            output.append("  ");
            output.append(user.getUsername());
            output.append("  ");
            output.append(user.getPassword());
            output.append("  ");
            output.append(user.getUserType());
            output.append("  ");
            output.append(user.getAddress());
            output.append("\n");
        }
        UIAdmin.printUsers(output.toString());
        Jaxb.saveUsers(users);
	}

	public void updateUser(String id, String username, String pass, String type, String address) {
		for (User user : users.getUsers()) {
			if (user.getId() == Integer.parseInt(id)) {
				user.setUsername(username);
				user.setPassword(pass);
				user.setUserType(type);
				user.setAddress(address);
			}
		}	
		printUsers();
	}

	public boolean deleteUser(String id) {
		for (User user : users.getUsers())
			if (user.getId() == Integer.parseInt(id)) {
				users.getUsers().remove(users.getUsers().indexOf(user));
				printUsers();
				return true;
			}
		return false;
	}

	//BOOKS SECTION
	public void addBook(Book book) {
		books.getBooks().add(book);
		printBooks();
	}
	
	public Book selectBook(String text) {
		for (Book book : books.getBooks()) {
			if (book.getId() == Integer.parseInt(text))
				return book;
		}
		return null;
	}
	
	public boolean isUsedBook(String username) {
		boolean answer = users.findUsername(username);
		return answer;
	}
	
	public boolean deleteBook(String id) {
		for (Book book : books.getBooks())
			if (book.getId() == Integer.parseInt(id)) {
				books.getBooks().remove(books.getBooks().indexOf(book));
				printBooks();
				return true;
			}
		return false;
	}
	
	public void updateBook(String id, String gen, String tit, String aut, String pri, String qua) {
		for (Book book : books.getBooks()) {
			if (book.getId() == Integer.parseInt(id)) {
				book.setGenre(gen);
				book.setTitle(tit);
				book.setAuthor(aut);
				book.setPrice(Integer.parseInt(pri));
				book.setStock(Integer.parseInt(qua));
			}
		}	
		printUsers();
	}

	private static void printBooks() {
		List<Book> bookList = books.getBooks();
        StringBuilder output = new StringBuilder();
        output.append("ID | Genre | Title | Author | Price | Quantity \n");
        for (Book book : bookList) {
        	output.append(book.getId());
            output.append("     ");
            output.append(book.getGenre());
            output.append("  ");
            output.append(book.getTitle());
            output.append("  ");
            output.append(book.getAuthor());
            output.append("  ");
            output.append(book.getPrice());
            output.append("  ");
            output.append(book.getStock());
            output.append("\n");
        }
        UIAdmin.printBooks(output.toString());
        Jaxb.saveBooks(books);
	}

	public void createPDFReport() {
		ReportFactory reportFactory = new ReportFactory();
		Report report = reportFactory.setReport(".pdf");
		report.createReport(books, "outdatedBooks.pdf");
	}

	public void createCSVReport() {
		ReportFactory reportFactory = new ReportFactory();
		Report report = reportFactory.setReport(".csv");

		List<Book> bookList = books.getBooks();
        StringBuilder output = new StringBuilder();
        output.append("ID | Genre | Title | Author | Price | Quantity \n");
        for (Book book : bookList) {
        	if (book.getStock() <= 0) {
        		output.append(book.getId());
        		output.append("     ");
        		output.append(book.getGenre());
        		output.append("  ");
        		output.append(book.getTitle());
        		output.append("  ");
        		output.append(book.getAuthor());
        		output.append("  ");
        		output.append(book.getPrice());
        		output.append("  ");
        		output.append(book.getStock());
        		output.append("\n");
        	}
        }
        
		report.createReport(books, "outdatedBooks.csv");
	}

	//USER PAGE SECTION
	public static void printUserBooks() {
		List<Book> bookList = books.getBooks();
        StringBuilder output = new StringBuilder();
        output.append("ID | Genre | Title | Author | Price | Quantity \n");
        
        Book template = UIUser.getTemplate();
        
        if ((template.getGenre().equals("")) && (template.getAuthor().equals("")) && (template.getTitle().equals("")))
        	for (Book book : bookList) {
        		output.append(book.getId());
        		output.append("     ");
        		output.append(book.getGenre());
        		output.append("  ");
        		output.append(book.getTitle());
            	output.append("  ");
            	output.append(book.getAuthor());
            	output.append("  ");
            	output.append(book.getPrice());
            	output.append("  ");
            	output.append(book.getStock());
            	output.append("\n");
        	} else 
        	for (Book book : searchList(template).getBooks()) {
        		output.append(book.getId());
        		output.append("     ");
        		output.append(book.getGenre());
        		output.append("  ");
        		output.append(book.getTitle());
            	output.append("  ");
            	output.append(book.getAuthor());
            	output.append("  ");
            	output.append(book.getPrice());
            	output.append("  ");
            	output.append(book.getStock());
            	output.append("\n");
        	}
        UIUser.printBooks(output.toString());
        Jaxb.saveBooks(books);
	}
	
	public static BookList searchList(Book template) {
		
		BookList genreFilter = new BookList();
		if ((!template.getGenre().equals(""))) {
			for (Book book : books.getBooks())
				if (book.getGenre().equals(template.getGenre())) {
					genreFilter.getBooks().add(book);
				} 
		} else genreFilter = books;
		
		BookList authorFilter = new BookList();
		if ((!template.getAuthor().equals(""))) {
			for (Book book : genreFilter.getBooks())
				if (book.getAuthor().equals(template.getAuthor())) {
					authorFilter.getBooks().add(book);
				}
		} else authorFilter = genreFilter;
						
		BookList titleFilter = new BookList();
		if ((!template.getTitle().equals(""))) {
			for (Book book : authorFilter.getBooks())
				if (book.getTitle().equals(template.getTitle())) {
					titleFilter.getBooks().add(book);
				}
		} else titleFilter = authorFilter;
		
		return titleFilter;
	}
	
	public BookList getBooks() {
		return Control.books;
	}

	public boolean checkout(BookList chart) {
		for (Book book : chart.getBooks()) {
			if (books.findBook(book.getId()).getStock() - book.getStock() < 0)
				return false;
		}
		
		stockAfterCheckout(chart);
		return true;
	}

	private void stockAfterCheckout(BookList chart) {
		for (Book book : chart.getBooks()) {
			books.findBook(book.getId()).setStock(books.findBook(book.getId()).getStock() - book.getStock());
		}
		printUserBooks();
	}
}
