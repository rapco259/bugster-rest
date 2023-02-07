package sk.upjs.bugster_rest;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import sk.upjs.dao.ProjectDao;
import sk.upjs.dao.UserDao;
import sk.upjs.entity.Project;
import sk.upjs.entity.Role;
import sk.upjs.entity.User;
import sk.upjs.factory.DaoFactory;

@RestController
public class UserDaoController {

	private UserDao userDao = DaoFactory.INSTANCE.getUserDao();
	private ProjectDao projectDao = DaoFactory.INSTANCE.getProjectDao();

	@RequestMapping("/users")
	public List<User> getAll() {
		return userDao.getAll();
	}

	@GetMapping("/users/{id}")
	public User getById(@PathVariable long id) {
		User byId = null;
		try {
			byId = userDao.getById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new UserNotFoundException("User with id: " + id + " was not found");
		}
		return byId;
	}


	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public void addUser(@RequestBody User user) {
		System.out.println(user);
		userDao.save(user);
	}

	// aj s id
	@ResponseStatus(HttpStatus.ACCEPTED)
	@RequestMapping(value = "/users", method = RequestMethod.PUT)
	public void updateUser(@RequestBody User user) {
		System.out.println(user);
		userDao.save(user);
	}

	// iba id hore do metody
	@ResponseStatus(HttpStatus.ACCEPTED)
	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
	public void deleteUser(@PathVariable Long id) {

		User user = null;
		try {
			user = userDao.getById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new UserNotFoundException("User with id: " + id + " was not found");
		}
		if (user == null) {
			throw new UserNotFoundException("User with id: " + id + " was not found");
		} else {
			userDao.delete(id);
		}

	}

	// iba jeden ma username, nie dvaja rovnaky
	@ResponseStatus(HttpStatus.ACCEPTED)
	@RequestMapping(value = "/user/{username}", method = RequestMethod.GET)
	public User getByUsername(@PathVariable String username) {
		User user = null;

		try {
			user = userDao.getByUsername(username);
		} catch (EmptyResultDataAccessException e) {
			throw new UserNotFoundException("User with username " + username + " was not found");
		}

		return user;
	}

	// bez id vypis
	@ResponseStatus(HttpStatus.ACCEPTED)
	@RequestMapping(value = "/userProject/{id}", method = RequestMethod.GET)
	public List<User> getByProjectId(@PathVariable long id) {

		Project project = null;
		List<User> users = null;
		try {
			project = projectDao.getById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ProjectNotFoundException("Project with id: " + id + " was not found");
		} catch (NoSuchElementException e) {
			throw new ProjectNotFoundException("Project with id: " + id + " was not found");
		}

		users = userDao.getByProjectId(id);

		return users;

	}

	@ResponseStatus(HttpStatus.ACCEPTED)
	@RequestMapping(value = "/userRole", method = RequestMethod.GET)
	public List<Role> getAllRoles() {
		List<Role> roles = userDao.getAllRoles();
		return roles;
	}

	// aka to je rola usera
	@ResponseStatus(HttpStatus.ACCEPTED)
	@RequestMapping(value = "/userRole/{id}", method = RequestMethod.GET)
	public Role getByRoleId(@PathVariable long id) {
		Role rola = null;
		try {
			rola = userDao.getByRoleId(id);
		} catch (EmptyResultDataAccessException e) {
			throw new RoleNotFoundException("Role with id: " + id + " was not found");
		}

		return rola;
	}
}
