package ua.nure.serdyuk.entity;

import java.io.Serializable;

import ua.nure.serdyuk.constants.Const;
import ua.nure.serdyuk.validation.Validation;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;

	@Validation(regex = Const.REGEX_EMAIL, message = "Email is not valid")
	private String email;

	@Validation(regex = Const.REGEX_PASSWORD, message = "Password should contain at least 4 symbols")
	private String password;

	@Validation(regex = Const.REGEX_NAME, required = false, message = "Name should start with upper-case letter")
	private String firstName;

	@Validation(regex = Const.REGEX_NAME, required = false, message = "Name should start with upper-case letter")
	private String lastName;

	private Role role;

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", firstName="
				+ firstName + ", lastName=" + lastName + ", role=" + role + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
