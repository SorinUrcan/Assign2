package model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "users")
public class UserList {
	@XmlElement(name = "user", type = User.class)
	private List<User> users = new ArrayList<User>();

	public List<User> getUsers() {
        return users;
    }

	public void setUsers(List<User> users) {
		this.users = users;
	}	
	
	public boolean findUsername(String username) {
		for (User user : users) {
			if (user.getUsername().equals(username)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean find(int id) {
		for (User user : users) {
			if (user.getId() == id)
				return true;
		}
		return false;
	}
	
	public User findUser(int id) {
		for (User user : users) {
			if (user.getId() == id)
				return user;
		}
		return null;
	}
	
	public String login(String username, String password) {
		for (User user : users) {
			if (user.getUsername().equals(username))
				if (user.getPassword().equals(password))
					return user.getUserType();
		}
		return "fail";
	}

	public void add(User user) {
		users.add(user);
	}
}
