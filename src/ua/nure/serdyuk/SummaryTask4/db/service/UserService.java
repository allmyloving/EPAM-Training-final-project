package ua.nure.serdyuk.SummaryTask4.db.service;

import ua.nure.serdyuk.SummaryTask4.entity.User;

public interface UserService {

	boolean create(User user);

	User get(long id);

	User auth(String login, String password);

}
