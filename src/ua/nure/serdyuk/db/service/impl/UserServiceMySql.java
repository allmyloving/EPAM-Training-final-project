package ua.nure.serdyuk.db.service.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

import ua.nure.serdyuk.db.dao.UserDao;
import ua.nure.serdyuk.db.service.UserService;
import ua.nure.serdyuk.entity.User;
import ua.nure.serdyuk.exception.AppException;

public class UserServiceMySql implements UserService {

	private static final Logger LOG = Logger.getLogger(UserServiceMySql.class);

	private UserDao userDao;

	public UserServiceMySql(UserDao userDao) {
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
		StringBuilder hash = new StringBuilder(password).append(email);
		String modified = hash.toString();
		hash.setLength(0);

		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			byte[] bytes = digest.digest(modified.getBytes());

			// convert to hex
			for (int i = 0; i < bytes.length; i++) {
				hash.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16)
						.substring(1));
			}
		} catch (NoSuchAlgorithmException e) {
			LOG.error(String.format("Error while creating hash -- %s",
					e.getMessage()));
			throw new AppException(e.getMessage());
		}
		return hash.toString();
	}

}
