package ua.nure.serdyuk.db.service;

import ua.nure.serdyuk.entity.User;

public interface UserService {

	boolean create(User user);

	User auth(String login, String password);

}
