package me.timo.hw18.user;

import java.util.ArrayList;
import java.util.List;

import me.timo.hw18.Game;

public class UserManager {
	
	private Game game;
	private List<User> users;
	
	public UserManager(Game game) {
		this.game = game;
		users = new ArrayList<>();
	
	}
	
	public User getNextUser() {
		User currentUser = game.getCurrentEvent().getUser();
		User nextUser = currentUser;
		
		int index = 0;
		
		for (User us : users) {
			if (us.equals(currentUser)) {
				
				if (index == users.size() - 1) {
					nextUser = users.get(0);
					
				} else {
					nextUser = users.get(index + 1);
				}
			}
			index++;
		}
		
		return nextUser;
		
	}

	public List<User> getUsers() {
		return users;
	}
}
