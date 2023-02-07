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
import sk.upjs.dao.UnauthorizedAccessException;
import sk.upjs.dao.UserDao;
import sk.upjs.entity.Project;
import sk.upjs.entity.User;
import sk.upjs.factory.DaoFactory;

@RestController
public class ProjectDaoController {

	private ProjectDao projectDao = DaoFactory.INSTANCE.getProjectDao();
	private UserDao userDao = DaoFactory.INSTANCE.getUserDao();

	@ResponseStatus(HttpStatus.ACCEPTED)
	@GetMapping("/projects/{id}")
	public Project getById(@PathVariable long id) {

		Project project = null;
		try {
			project = projectDao.getById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ProjectNotFoundException("Project with id: " + id + " was not found");
		}

		return project;
	}

	@ResponseStatus(HttpStatus.ACCEPTED)
	@RequestMapping("/projects")
	public List<Project> getAll() {
		return projectDao.getAll();

	}

	@ResponseStatus(HttpStatus.ACCEPTED)
	@RequestMapping(value = "/userProjects/{id}", method = RequestMethod.GET)
	public List<Project> getByUserId(@PathVariable long id) {

		User user = null;

		try {
			user = userDao.getById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new UserInProjectNotFoundException("User with id: " + id + " was not found");
		}

		List<Project> list = projectDao.getByUserId(id);
		return list;

	}

	// vytvorenie
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/projects", method = RequestMethod.POST)
	public Project save(@RequestBody Project project) {
		projectDao.save(project);
		return project;

	}

	// false lebo nikto neni na projekte
	@ResponseStatus(HttpStatus.ACCEPTED)
	@RequestMapping(value = "/projects/{id}", method = RequestMethod.DELETE)
	public boolean delete(@PathVariable long id) {
		boolean del = false;

		try {
			projectDao.getById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ProjectNotFoundException("Project with id: " + id + " was not found");
		}

		del = projectDao.delete(id);

		return del;
	}

	@ResponseStatus(HttpStatus.ACCEPTED)
	@RequestMapping(value = "/projectAddUser/{userId}/{projectId}", method = RequestMethod.POST)
	public Project addUserToProject(@PathVariable long userId, @PathVariable long projectId) {

		try {
			userDao.getById(userId);
		} catch (EmptyResultDataAccessException e1) {
			throw new UserNotFoundException("User with id: " + userId + " was not found");
		}

		try {
			projectDao.getById(projectId);
		} catch (EmptyResultDataAccessException e2) {
			throw new ProjectNotFoundException("Project with id: " + projectId + " was not found");
		}

		Project project = projectDao.addUserToProject(userId, projectId);

		return project;
	}

	@ResponseStatus(HttpStatus.ACCEPTED)
	@RequestMapping(value = "/projectDeleteUser/{userId}/{projectId}", method = RequestMethod.DELETE)
	public boolean deleteUserFromProject(@PathVariable long userId, @PathVariable long projectId) {

		try {
			userDao.getById(userId);
		} catch (EmptyResultDataAccessException e1) {
			throw new UserNotFoundException("User with id: " + userId + " was not found");
		}

		try {
			projectDao.getById(projectId);
		} catch (EmptyResultDataAccessException e2) {
			throw new ProjectNotFoundException("Project with id: " + projectId + " was not found");
		}

		boolean del = projectDao.deleteUserFromProject(userId, projectId);

		return del;
	}

}
