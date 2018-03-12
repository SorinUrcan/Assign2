package view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

import control.Control;

public class UILogin {
	private final Control control;

	private JFrame frame;
	private JTextField txtUsername;
	private JTextField textUsername;
	private JTextField txtPassword;
	private JTextField textPassword;

	/**
	 * Create the application.
	 */
	public UILogin(Control control) {
		initialize();
		this.control = control;
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 394, 320);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtUsername = new JTextField();
		txtUsername.setBackground(UIManager.getColor("Button.background"));
		txtUsername.setColumns(10);
		txtUsername.setEditable(false);
		txtUsername.setText("Username:");
		txtUsername.setBounds(93, 77, 91, 20);
		frame.getContentPane().add(txtUsername);
		
		textUsername = new JTextField();
		textUsername.setBounds(194, 77, 86, 20);
		frame.getContentPane().add(textUsername);
		textUsername.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setEditable(false);
		txtPassword.setText("Password:");
		txtPassword.setBounds(93, 108, 91, 20);
		frame.getContentPane().add(txtPassword);
		txtPassword.setColumns(10);
		
		textPassword = new JTextField();
		textPassword.setBounds(194, 108, 86, 20);
		frame.getContentPane().add(textPassword);
		textPassword.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (textUsername.getText().equals("") || textPassword.getText().equals(""))
					JOptionPane.showMessageDialog(null, "Empty input fields!");
				else {
					control.loginButtonClicked();
				}			
			}
		});
		btnLogin.setBounds(134, 154, 89, 23);
		frame.getContentPane().add(btnLogin);
	}


	public void showLoginWindow() {
		this.frame.setVisible(true);
	}


	/**
	 * @return the textUsername
	 */
	public String getTextUsername() {
		return this.textUsername.getText();
	}


	/**
	 * @return the textPassword
	 */
	public String getTextPassword() {
		return this.textPassword.getText();
	}
}
