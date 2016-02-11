package ua.nure.serdyuk.db.dao;

import ua.nure.serdyuk.entity.User;

public interface UserDao extends GenericDao<User> {
	
	User getUserByEmail(String email);
}
