package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import control.Control;
import model.Book;
import model.User;
import javax.swing.JTextArea;

public class UIAdmin {
	private final Control control;

	private JFrame frmBookstoreAdminPanel;
	private JTextArea textUsers;
	private JTextArea textBooks;
	private JTextField txtId;
	private JTextField textUID;
	private JTextField txtUsername;
	private JTextField textUUsername;
	private JTextField textUPassword;
	private JTextField txtPassword;
	private JTextField txtType;
	private JTextField textUType;
	private JTextField txtAddress;
	private JTextField textUAddress;
	private JTextField txtUser;
	private JTextField txtId_1;
	private JTextField textBID;
	private JTextField txtTitle;
	private JTextField txtAuthor;
	private JTextField textBTitle;
	private JTextField textBAuthor;
	private JTextField txtGenre;
	private JTextField textBGenre;
	private JTextField txtQuantity;
	private JTextField textBQuantity;
	private JTextField txtPrice;
	private JTextField textBPrice;
	private JTextField txtBook;


	/**
	 * Create the application.
	 */
	public UIAdmin(Control control) {
		initialize();
		this.control = control;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBookstoreAdminPanel = new JFrame();
		frmBookstoreAdminPanel.setTitle("BookStore Admin Panel");
		frmBookstoreAdminPanel.setResizable(false);
		frmBookstoreAdminPanel.setBounds(100, 100, 850, 600);
		frmBookstoreAdminPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBookstoreAdminPanel.getContentPane().setLayout(null);
		
		textUsers = new JTextArea();
		textUsers.setBounds(10, 11, 395, 416);
		frmBookstoreAdminPanel.getContentPane().add(textUsers);
		textUsers.setColumns(10);
		
		textBooks = new JTextArea();
		textBooks.setBounds(429, 11, 395, 416);
		frmBookstoreAdminPanel.getContentPane().add(textBooks);
		textBooks.setColumns(10);
		
		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setText("ID:");
		txtId.setBounds(10, 435, 28, 20);
		frmBookstoreAdminPanel.getContentPane().add(txtId);
		txtId.setColumns(10);
		
		textUID = new JTextField();
		textUID.setBounds(48, 435, 50, 20);
		frmBookstoreAdminPanel.getContentPane().add(textUID);
		textUID.setColumns(10);
		
		txtUsername = new JTextField();
		txtUsername.setEditable(false);
		txtUsername.setText("Username:");
		txtUsername.setBounds(108, 435, 70, 20);
		frmBookstoreAdminPanel.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		textUUsername = new JTextField();
		textUUsername.setBounds(188, 435, 86, 20);
		frmBookstoreAdminPanel.getContentPane().add(textUUsername);
		textUUsername.setColumns(10);
		
		textUPassword = new JTextField();
		textUPassword.setBounds(92, 466, 70, 20);
		frmBookstoreAdminPanel.getContentPane().add(textUPassword);
		textUPassword.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setEditable(false);
		txtPassword.setText("Password:");
		txtPassword.setBounds(10, 466, 70, 20);
		frmBookstoreAdminPanel.getContentPane().add(txtPassword);
		txtPassword.setColumns(10);
		
		txtType = new JTextField();
		txtType.setEditable(false);
		txtType.setText("Type:");
		txtType.setBounds(284, 435, 41, 20);
		frmBookstoreAdminPanel.getContentPane().add(txtType);
		txtType.setColumns(10);
		
		textUType = new JTextField();
		textUType.setBounds(335, 435, 70, 20);
		frmBookstoreAdminPanel.getContentPane().add(textUType);
		textUType.setColumns(10);
		
		txtAddress = new JTextField();
		txtAddress.setEditable(false);
		txtAddress.setText("Address:");
		txtAddress.setBounds(172, 466, 56, 20);
		frmBookstoreAdminPanel.getContentPane().add(txtAddress);
		txtAddress.setColumns(10);
		
		textUAddress = new JTextField();
		textUAddress.setBounds(238, 466, 167, 20);
		frmBookstoreAdminPanel.getContentPane().add(textUAddress);
		textUAddress.setColumns(10);
		
		JButton btnUselect = new JButton("Select");
		btnUselect.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				User user = control.selectUser(textUID.getText());
				textUUsername.setText(user.getUsername());
				textUPassword.setText(user.getPassword());
				textUAddress.setText(user.getAddress());
				textUType.setText(user.getUserType());
			}
		});
		btnUselect.setBounds(69, 497, 75, 23);
		frmBookstoreAdminPanel.getContentPane().add(btnUselect);
		
		JButton btnUAdd = new JButton("Add");
		btnUAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String username = textUUsername.getText();
				String password = textUPassword.getText();
				String userType = textUType.getText();
				String address  = textUAddress.getText();
				
				if (username.equals("") || password.equals("") || userType.equals("") || address.equals("")) {
					JOptionPane.showMessageDialog(null, "Input field empty!");
				} else if (control.isUsed(username)) {
					JOptionPane.showMessageDialog(null, "Username already used!");
				} else {
					if (userType.equals("admin")) {
						control.addUser(new User(username, password, userType, address));
						JOptionPane.showMessageDialog(null, "User succesfullly created!");
					} else {
						control.addUser(new User(username, password, "client", address));
						JOptionPane.showMessageDialog(null, "User succesfullly created!");
					}
				}
			}
		});
		btnUAdd.setBounds(154, 497, 86, 23);
		frmBookstoreAdminPanel.getContentPane().add(btnUAdd);
		
		JButton btnUUpdate = new JButton("Update");
		btnUUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (textUID.getText().equals(""))
					JOptionPane.showMessageDialog(null, "Selected ID not valid!");
				if (control.isUsed(textUUsername.getText())) {
					JOptionPane.showMessageDialog(null, "Username already used!");
				} else {
					control.updateUser(textUID.getText() ,textUUsername.getText(), textUPassword.getText(), textUType.getText(), textUAddress.getText());
					JOptionPane.showMessageDialog(null, "User updated!");
				}
			}
		});
		btnUUpdate.setBounds(250, 497, 75, 23);
		frmBookstoreAdminPanel.getContentPane().add(btnUUpdate);
		
		JButton btnUDelete = new JButton("Delete");
		btnUDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(control.deleteUser(textUID.getText()))
					JOptionPane.showMessageDialog(null, "User deleted!");
				else JOptionPane.showMessageDialog(null, "User does not exist!");
			}
		});
		btnUDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnUDelete.setBounds(330, 497, 75, 23);
		frmBookstoreAdminPanel.getContentPane().add(btnUDelete);
		
		txtUser = new JTextField();
		txtUser.setEditable(false);
		txtUser.setText("User:");
		txtUser.setBounds(10, 498, 50, 20);
		frmBookstoreAdminPanel.getContentPane().add(txtUser);
		txtUser.setColumns(10);
		
		txtId_1 = new JTextField();
		txtId_1.setEditable(false);
		txtId_1.setText("ID:");
		txtId_1.setBounds(429, 435, 28, 20);
		frmBookstoreAdminPanel.getContentPane().add(txtId_1);
		txtId_1.setColumns(10);
		
		textBID = new JTextField();
		textBID.setBounds(467, 435, 50, 20);
		frmBookstoreAdminPanel.getContentPane().add(textBID);
		textBID.setColumns(10);
		
		txtTitle = new JTextField();
		txtTitle.setEditable(false);
		txtTitle.setText("Title:");
		txtTitle.setBounds(658, 435, 41, 20);
		frmBookstoreAdminPanel.getContentPane().add(txtTitle);
		txtTitle.setColumns(10);
		
		txtAuthor = new JTextField();
		txtAuthor.setEditable(false);
		txtAuthor.setText("Author:");
		txtAuthor.setBounds(429, 466, 50, 20);
		frmBookstoreAdminPanel.getContentPane().add(txtAuthor);
		txtAuthor.setColumns(10);
		
		textBTitle = new JTextField();
		textBTitle.setBounds(709, 435, 115, 20);
		frmBookstoreAdminPanel.getContentPane().add(textBTitle);
		textBTitle.setColumns(10);
		
		textBAuthor = new JTextField();
		textBAuthor.setBounds(491, 466, 105, 20);
		frmBookstoreAdminPanel.getContentPane().add(textBAuthor);
		textBAuthor.setColumns(10);
		
		txtGenre = new JTextField();
		txtGenre.setEditable(false);
		txtGenre.setText("Genre:");
		txtGenre.setBounds(527, 435, 41, 20);
		frmBookstoreAdminPanel.getContentPane().add(txtGenre);
		txtGenre.setColumns(10);
		
		textBGenre = new JTextField();
		textBGenre.setBounds(578, 435, 70, 20);
		frmBookstoreAdminPanel.getContentPane().add(textBGenre);
		textBGenre.setColumns(10);
		
		txtQuantity = new JTextField();
		txtQuantity.setEditable(false);
		txtQuantity.setText("Quantity:");
		txtQuantity.setBounds(606, 466, 56, 20);
		frmBookstoreAdminPanel.getContentPane().add(txtQuantity);
		txtQuantity.setColumns(10);
		
		textBQuantity = new JTextField();
		textBQuantity.setBounds(672, 466, 41, 20);
		frmBookstoreAdminPanel.getContentPane().add(textBQuantity);
		textBQuantity.setColumns(10);
		
		txtPrice = new JTextField();
		txtPrice.setEditable(false);
		txtPrice.setText("Price:");
		txtPrice.setBounds(723, 466, 41, 20);
		frmBookstoreAdminPanel.getContentPane().add(txtPrice);
		txtPrice.setColumns(10);
		
		textBPrice = new JTextField();
		textBPrice.setBounds(774, 466, 50, 20);
		frmBookstoreAdminPanel.getContentPane().add(textBPrice);
		textBPrice.setColumns(10);
		
		txtBook = new JTextField();
		txtBook.setEditable(false);
		txtBook.setText("Book:");
		txtBook.setBounds(429, 498, 41, 20);
		frmBookstoreAdminPanel.getContentPane().add(txtBook);
		txtBook.setColumns(10);
		
		JButton btnSelect = new JButton("Select");
		btnSelect.setEnabled(false);
		btnSelect.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		btnSelect.setBounds(488, 497, 75, 23);
		frmBookstoreAdminPanel.getContentPane().add(btnSelect);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (textBGenre.getText().equals("") || textBTitle.getText().equals("") || textBAuthor.getText().equals("") || textBPrice.getText().equals("") || textBQuantity.getText().equals("")) 
					JOptionPane.showMessageDialog(null, "Fill all the fields!");
				else {
					control.addBook(new Book(textBGenre.getText(), textBTitle.getText(), textBAuthor.getText(), Integer.parseInt(textBPrice.getText()), Integer.parseInt(textBQuantity.getText())));
				}
			}
		});
		btnAdd.setBounds(573, 497, 75, 23);
		frmBookstoreAdminPanel.getContentPane().add(btnAdd);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (textBID.getText().equals(""))
					JOptionPane.showMessageDialog(null, "Selected ID not valid!");
				else if (textBID.getText().equals("") || textBGenre.getText().equals("") || textBTitle.getText().equals("") || textBAuthor.getText().equals("") || textBPrice.getText().equals("") || textBQuantity.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Fill all the fields!");
				} else {
					control.updateBook(textBID.getText(), textBGenre.getText(), textBTitle.getText(), textBAuthor.getText(), textBPrice.getText(), textBQuantity.getText());
					JOptionPane.showMessageDialog(null, "User updated!");
				}
			}
		});
		btnUpdate.setBounds(662, 497, 75, 23);
		frmBookstoreAdminPanel.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(control.deleteBook(textBID.getText()))
					JOptionPane.showMessageDialog(null, "Book deleted!");
				else JOptionPane.showMessageDialog(null, "Book does not exist!");
			}
		});
		btnDelete.setBounds(747, 497, 75, 23);
		frmBookstoreAdminPanel.getContentPane().add(btnDelete);
		
		JButton btnPdfReport = new JButton("PDF Report");
		btnPdfReport.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				control.createPDFReport();
			}
		});
		btnPdfReport.setBounds(238, 531, 167, 23);
		frmBookstoreAdminPanel.getContentPane().add(btnPdfReport);
		
		JButton btnCsvReport = new JButton("CSV Report");
		btnCsvReport.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				control.createCSVReport();
			}
		});
		btnCsvReport.setBounds(429, 531, 149, 23);
		frmBookstoreAdminPanel.getContentPane().add(btnCsvReport);
	}

	public void showWindow() {
		this.frmBookstoreAdminPanel.setVisible(true);
	}
	
	public void printUsers(String usersText) {
		textUsers.setText(usersText);
	}
	
	public void printBooks(String booksText) {
		textBooks.setText(booksText);
	}
}
