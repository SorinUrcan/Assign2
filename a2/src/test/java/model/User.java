package model;

import java.util.concurrent.atomic.AtomicInteger;

public class User {
	protected static AtomicInteger count = new AtomicInteger(0); 
	protected int id;
	protected String username;
	protected String password;
	protected String userType;
	protected String address;
	
	public User() {
		id = count.incrementAndGet();
	}
	
	/**
	 * @param username
	 * @param password
	 * @param userType
	 * @param address
	 */
	public User(String username, String password, String userType, String address) {
		this.id = count.incrementAndGet();
		this.username = username;
		this.password = password;
		this.userType = userType;
		this.address = address;
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
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * @return the userType
	 */
	public String getUserType() {
		return userType;
	}

	/**
	 * @param userType the userType to set
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
}
