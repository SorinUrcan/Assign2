package view;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import control.Control;
import model.Book;
import model.BookList;

public class UIUser {
	private final Control control;

	private JFrame frame;
	private JTextField txtSearchByGenre;
	private JTextField textGenre;
	private JTextField textField_2;
	private JTextField textTitle;
	private JTextField textField_4;
	private JTextField textAuthor;
	private JTextArea textList;
	private JTextArea textChart;
	private JTextField textField_8;
	private JTextField textID;
	private JTextField textField_10;
	private JTextField textQuantity;

	private Book templateBook = new Book("", "", "", 0, 0);
	private BookList chart = new BookList();
	private int chartPrice = 0;

	/**
	 * Create the application.
	 */
	public UIUser(Control control) {
		initialize();
		this.control = control;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 675, 555);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtSearchByGenre = new JTextField();
		txtSearchByGenre.setBounds(10, 11, 106, 20);
		txtSearchByGenre.setText("Search by Genre:");
		txtSearchByGenre.setEditable(false);
		txtSearchByGenre.setColumns(10);
		txtSearchByGenre.setBackground(SystemColor.menu);
		frame.getContentPane().add(txtSearchByGenre);
		
		textGenre = new JTextField();
		textGenre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				updateTemplate();
			}
		});
		textGenre.setBounds(126, 11, 140, 20);
		textGenre.setColumns(10);
		frame.getContentPane().add(textGenre);
		
		textField_2 = new JTextField();
		textField_2.setBounds(84, 42, 32, 20);
		textField_2.setText("Title:");
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		frame.getContentPane().add(textField_2);
		
		textTitle = new JTextField();
		textTitle.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				updateTemplate();
			}
		});
		textTitle.setBounds(126, 42, 140, 20);
		textTitle.setColumns(10);
		frame.getContentPane().add(textTitle);
		
		textField_4 = new JTextField();
		textField_4.setBounds(69, 73, 47, 20);
		textField_4.setText("Author:");
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		frame.getContentPane().add(textField_4);
		
		textAuthor = new JTextField();
		textAuthor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				updateTemplate();
			}
		});
		textAuthor.setBounds(126, 73, 140, 20);
		textAuthor.setColumns(10);
		frame.getContentPane().add(textAuthor);
		
		textList = new JTextArea();
		textList.setBackground(Color.WHITE);
		textList.setEditable(false);
		textList.setBounds(10, 104, 315, 411);
		textList.setColumns(10);
		frame.getContentPane().add(textList);
		
		textChart = new JTextArea();
		textChart.setBackground(Color.WHITE);
		textChart.setEditable(false);
		textChart.setBounds(344, 11, 315, 309);
		textChart.setColumns(10);
		frame.getContentPane().add(textChart);
		
		textField_8 = new JTextField();
		textField_8.setBounds(356, 331, 56, 20);
		textField_8.setText("Book ID:");
		textField_8.setEditable(false);
		textField_8.setColumns(10);
		frame.getContentPane().add(textField_8);
		
		textID = new JTextField();
		textID.setBounds(417, 331, 56, 20);
		textID.setColumns(10);
		frame.getContentPane().add(textID);
		
		textField_10 = new JTextField();
		textField_10.setBounds(356, 362, 56, 20);
		textField_10.setText("Quantity:");
		textField_10.setEditable(false);
		textField_10.setColumns(10);
		frame.getContentPane().add(textField_10);
		
		textQuantity = new JTextField();
		textQuantity.setBounds(419, 362, 47, 20);
		textQuantity.setColumns(10);
		frame.getContentPane().add(textQuantity);
		
		JButton buttonAdd = new JButton("Add to chart");
		buttonAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(validateInput()) {
					if (control.getBooks().find(Integer.parseInt(textID.getText()))) {
						if (chart.find(Integer.parseInt(textID.getText()))) {
							chart.findBook(Integer.parseInt(textID.getText())).setStock(chart.findBook(Integer.parseInt(textID.getText())).getStock() + Integer.parseInt(textQuantity.getText()));
							chartPrice += chart.findBook(Integer.parseInt(textID.getText())).getPrice() * Integer.parseInt(textQuantity.getText());
						} else {
							Book book = control.getBooks().findBook(Integer.parseInt(textID.getText()));
							Book newBook = new Book(book, Integer.parseInt(textQuantity.getText()));
							chart.getBooks().add(newBook);
							chartPrice += book.getPrice() * Integer.parseInt(textQuantity.getText());
						}
					}
					printChart();
					
					
					
					
					/*
					System.out.println("1");
					for (Book book : control.getBooks().getBooks()) {
						System.out.println("1.1");
						if (book.getId() == Integer.parseInt(textID.getText())) {
							System.out.println("1.2");
							System.out.println("bookStock " + book.getStock());
							if (chart.find(Integer.parseInt(textID.getText()))) {
								System.out.println("2 + STOCK currentStock");
								int indexInChart = chart.getBooks().indexOf(book);				
								int currentStock = chart.getBooks().get(indexInChart).getStock();
								System.out.println("2 + STOCK " + currentStock);
								int stockAdded   = Integer.parseInt(textQuantity.getText());
								int wantedStock = currentStock + stockAdded;
								
								System.out.println("Z" + indexInChart);
								System.out.println("Z" + currentStock);
								System.out.println("Z" + stockAdded);
								System.out.println("wantedStock " + wantedStock);
								System.out.println("bookStock " + book.getStock());
								
								if (book.getStock() - wantedStock >= 0) {
									System.out.println("2.1");
									chart.getBooks().get(indexInChart).setStock(wantedStock);
									chartPrice +=  stockAdded * book.getPrice();
								}
								printChart();
							} else {
								System.out.println("3");
								int stockAdded   = Integer.parseInt(textQuantity.getText());
								if (book.getStock() - stockAdded >= 0) {
									//chart.getBooks().add(new Book(book, stockAdded));
									chart.getBooks().add(book);
									chart.getBooks().get(chart.getBooks().indexOf(book)).setStock(stockAdded);
									chartPrice += stockAdded * book.getPrice();
									printChart();
								}
								printChart();
							}
								
						}
					}
					
					*/
					
							
							/*
							if (chart.getBooks().indexOf(book) < 0) {
								Book newBook = new Book(book, Integer.parseInt(textQuantity.getText()));
								//book.setStock(Integer.parseInt(textQuantity.getText()));
								chart.getBooks().add(newBook);
							} else {
								int stock = chart.getBooks().get(chart.getBooks().indexOf(book)).getStock();
								chart.getBooks().get(chart.getBooks().indexOf(book)).setStock(stock + Integer.parseInt(textQuantity.getText()));
								System.out.println("STOCK " + book.getStock());
							}
							chartPrice += book.getPrice() * Integer.parseInt(textQuantity.getText());
						}
					*/
				}
			}
		});
		buttonAdd.setBounds(356, 393, 117, 23);
		frame.getContentPane().add(buttonAdd);
		
		JButton buttonRemove = new JButton("Remove from chart");
		buttonRemove.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				/*
				if(validateInput()) {
					for (Book book : chart.getBooks())
						if (Integer.toString(book.getId()).equals(textID.getText())) {
							if (book.getStock() > Integer.parseInt(textQuantity.toString())) {
								int stock = book.getStock();
								book.setStock(stock - Integer.parseInt(textQuantity.getText()));
								chartPrice -= book.getPrice() * Integer.parseInt(textQuantity.getText());
							} else {
								chartPrice -= book.getPrice() * book.getStock();
								chart.getBooks().remove(book);
							}
							
						}
					printChart();
				}*/
				
				if(validateInput()) {
					if (control.getBooks().find(Integer.parseInt(textID.getText()))) {
						if (chart.find(Integer.parseInt(textID.getText()))) {
							if (chart.findBook(Integer.parseInt(textID.getText())).getStock() - Integer.parseInt(textQuantity.getText()) > 0) {
								chart.findBook(Integer.parseInt(textID.getText())).setStock(chart.findBook(Integer.parseInt(textID.getText())).getStock() - Integer.parseInt(textQuantity.getText()));
								chartPrice -= chart.findBook(Integer.parseInt(textID.getText())).getPrice() * Integer.parseInt(textQuantity.getText());
							} else {
								chartPrice -= chart.findBook(Integer.parseInt(textID.getText())).getPrice() * chart.findBook(Integer.parseInt(textID.getText())).getStock();
								int index = -1;
								for (Book book : chart.getBooks()) {
									if (book.getId() == Integer.parseInt(textID.getText())) {
										index = chart.getBooks().indexOf(book);
									}
								}
								chart.getBooks().remove(index);
							}
						} 
					}
					printChart();
				}
			}
		});
		buttonRemove.setBounds(483, 393, 125, 23);
		frame.getContentPane().add(buttonRemove);
		
		JButton buttonCheckout = new JButton("Checkout");
		buttonCheckout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(control.checkout(chart))
					JOptionPane.showMessageDialog(null, "Checkout successfully!");
				else JOptionPane.showMessageDialog(null, "Checkout failed! items may not be on stock");
			}
		});
		buttonCheckout.setBounds(560, 482, 89, 23);
		frame.getContentPane().add(buttonCheckout);
	}

	protected void updateTemplate() {
		this.templateBook.setAuthor(textAuthor.getText());
		this.templateBook.setGenre(textGenre.getText());
		this.templateBook.setTitle(textTitle.getText());
		
		Control.printUserBooks();
	}

	public void showWindows() {
		this.frame.setVisible(true);
	}

	public void showWindow() {
		this.frame.setVisible(true);
	}

	public void printBooks(String string) {
		this.textList.setText(string);
	}

	public Book getTemplate() {
		return this.templateBook;
	}
	
	private boolean validateInput() {
		if (textID.getText().equals("") || textQuantity.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Empty input fields!");
			return false;
		}
		return true;
	}
	
	public void printChart() {
        StringBuilder output = new StringBuilder();
        output.append("ID | Genre | Title | Author | Price | Quantity \n");
        for (Book book : chart.getBooks()) {
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
        output.append("Total cash: " + chartPrice);
        
        textChart.setText(output.toString());
	}
}
