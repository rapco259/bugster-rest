package sk.upjs.bugster_rest;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import sk.upjs.dao.BugDao;
import sk.upjs.entity.Bug;
import sk.upjs.entity.Severity;
import sk.upjs.entity.Status;
import sk.upjs.factory.DaoFactory;

@RestController
public class BugDaoController {

	private BugDao bugDao = DaoFactory.INSTANCE.getBugDao();

	@ResponseStatus(HttpStatus.ACCEPTED)
	@RequestMapping(value = "/bugs/{id}", method = RequestMethod.GET)
	public Bug getById(@PathVariable long id) {

		Bug bug = null;
		try {
			bug = bugDao.getById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new BugNotFoundException("Bug with id: " + id + " was not found");
		}

		return bug;
	}

	@ResponseStatus(HttpStatus.ACCEPTED)
	@RequestMapping(value = "/bugs", method = RequestMethod.GET)
	public List<Bug> getAll() {
		List<Bug> bugs = bugDao.getAll();
		return bugs;
	}

	@ResponseStatus(HttpStatus.ACCEPTED)
	@RequestMapping(value = "/bugStatus", method = RequestMethod.GET)
	public List<Status> getAllStatuses() {
		List<Status> statusOfBugs = bugDao.getAllStatuses();

		return statusOfBugs;
	}

	@ResponseStatus(HttpStatus.ACCEPTED)
	@RequestMapping(value = "/bugSeverities", method = RequestMethod.GET)
	public List<Severity> getAllSeverities() {
		List<Severity> allSeverities = bugDao.getAllSeverities();

		return allSeverities;
	}

	@ResponseStatus(HttpStatus.ACCEPTED)
	@RequestMapping(value = "/bugs/severity/{id}", method = RequestMethod.GET)
	public Severity getSeverityById(@PathVariable long id) {
		// predpokladam ze kazdy bug ma serverity, ale nemusi byt bug s tymto id

		Severity severity = null;
		try {
			severity = bugDao.getSeverityById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new SeverityNotFoundException("Severity with id: " + id + " was not found");
		}

		return severity;
	}

	// status id 1
	@ResponseStatus(HttpStatus.ACCEPTED)
	@RequestMapping(value = "/bugs/status/{id}", method = RequestMethod.GET)
	public Status getStatusById(@PathVariable long id) {

		Status status = null;
		try {
			status = bugDao.getStatusById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new StatusNotFoundException("Status with id: " + id + " was not found");
		}

		if (status == null) {
			throw new StatusNotFoundException("Status with id: " + id + " was not found");
		}

		return status;
	}

	@ResponseStatus(HttpStatus.ACCEPTED)
	@RequestMapping(value = "/bugs", method = RequestMethod.POST)
	public Bug save(Bug bug) {
		Bug newBug = bugDao.save(bug);
		return newBug;
	}

	@ResponseStatus(HttpStatus.ACCEPTED)
	@RequestMapping(value = "/bugs/status/{bugId}/{statusId}", method = RequestMethod.PUT)
	public Bug changeStatus(@PathVariable long bugId, @PathVariable long statusId) {
		Bug bug = null;
		try {
			bug = bugDao.getById(bugId);
		} catch (EmptyResultDataAccessException e1) {
			throw new BugNotFoundException("Bug with id: " + bugId + " was not found");
		}

		try {
			bugDao.getStatusById(statusId);
		} catch (EmptyResultDataAccessException e) {
			throw new StatusNotFoundException("Status with id: " + statusId + " was not found");
		}

		bugDao.changeStatus(bugId, statusId);

		return bug;

	}

	@ResponseStatus(HttpStatus.ACCEPTED)
	@RequestMapping(value = "/bugs/{id}", method = RequestMethod.DELETE)
	public boolean delete(@PathVariable long id) {

		Bug bug = null;
		try {
			bug = bugDao.getById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new BugNotFoundException("Bug with id = " + id + " was not found");
		}

		boolean del = bugDao.delete(id);

		return del;

	}
}
