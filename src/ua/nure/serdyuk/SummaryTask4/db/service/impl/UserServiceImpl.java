package ua.nure.serdyuk.SummaryTask4.db.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

import ua.nure.serdyuk.SummaryTask4.db.dao.UserDao;
import ua.nure.serdyuk.SummaryTask4.db.service.UserService;
import ua.nure.serdyuk.SummaryTask4.entity.User;
import ua.nure.serdyuk.SummaryTask4.exception.AppException;
import ua.nure.serdyuk.SummaryTask4.util.Util;

public class UserServiceImpl implements UserService {

	private static final Logger LOG = Logger.getLogger(UserServiceImpl.class);

	private UserDao userDao;

	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public boolean create(User user) {
		String hash = generateHash(user.getEmail(), user.getPassword());
		user.setPassword(hash);
		LOG.debug(String.format("Adding user %s", user.toString()));

		return userDao.create(user);
	}

	@Override
	public User auth(String login, String password) {
		User user = userDao.getUserByEmail(login);
		LOG.debug(user);
		if (user != null) {
			String inPassword = generateHash(login, password);

			if (user.getPassword().equals(inPassword)) {
				return user;
			}
		}
		return null;
	}

	private String generateHash(String email, String password) {
		StringBuilder input = new StringBuilder(password).append(email);

		return Util.hash(input.toString());
	}

	@Override
	public User get(long id) {
		return userDao.get(id);
	}

}
