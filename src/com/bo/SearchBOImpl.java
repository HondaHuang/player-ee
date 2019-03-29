package com.bo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.dao.SearchDAO;
import com.dao.SearchDAOImpl;
import com.exception.BusinessException;
import com.to.Player;

public class SearchBOImpl implements SearchBO {
	private SearchDAO searchDAO;

	@Override
	public Player getPlayerById(String id) throws BusinessException {
		Player player = null;
		if(id.matches("[Pp]{1}[a-zA-Z]{2}[0-9]{7}")) {
			// code here for DAO
			searchDAO = getSearchDAO();
			player = searchDAO.getPlayerById(id);
		} else {
			throw new BusinessException("Entered player ID: " + id + " is invalid"); 
		}
		return player;
	}

	@Override
	public Player getPlayerByContact(long contact) throws BusinessException {
		Player player = null;
		if(Long.toString(contact).matches("[0-9]{10}")) {
			searchDAO = getSearchDAO();
			player = searchDAO.getPlayerByContact(contact);
		} else {
			throw new BusinessException("Entered player contact: " + contact + " is invalid"); 
		}
		return player;
	}

	@Override
	public Player getPlayerByEmail(String email) throws BusinessException {
		Player player = null;
		if(email.matches("\\w+@\\w+\\.\\w+")) {
			searchDAO = getSearchDAO();
			player = searchDAO.getPlayerByEmail(email);
		} else {
			throw new BusinessException("Entered player email: " + email + " is invalid"); 
		}
		return player;
	}

	@Override
	public List<Player> getPlayersByGender(String gender) throws BusinessException {
		List<Player> playerList = null;
		if (gender.matches("[MF]{1}")) {
			searchDAO = getSearchDAO();
			playerList = searchDAO.getPlayersByGender(gender);
		} else {
			throw new BusinessException("Entered gender: " + gender + " is invalid");
		}
		return playerList;
	}

	@Override
	public List<Player> getPlayersByDOB(Date dob) throws BusinessException {
		List<Player> playerList = null;
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
		if (formatter.format(dob).matches("[0-9]{2}-[0-9]{2}-[0-9]{4}")) {
			searchDAO = getSearchDAO();
			playerList = searchDAO.getPlayersByDOB(dob);
		} else {
			throw new BusinessException("Entered player dob: " + dob + " is invalid");
		}
		return playerList;
	}

	@Override
	public List<Player> getPlayersByName(String name) throws BusinessException {
		List<Player> playerList = null;
		if (name.matches("[a-zA-Z]{3,20}")) {
			searchDAO = getSearchDAO();
			playerList = searchDAO.getPlayersByName(name);
		} else {
			throw new BusinessException("Entered name: " + name + " is invalid");
		}
		return playerList;
	}

	@Override
	public List<Player> getPlayersByTeamName(String teamName) throws BusinessException {
		List<Player> playerList = null;
		if (teamName.matches("[a-zA-Z ]{3,15}")) {
			searchDAO = getSearchDAO();
			playerList = searchDAO.getPlayersByTeamName(teamName);
		} else {
			throw new BusinessException("Entered team name: " + teamName + " is invalid");
		}
		return playerList;
	}
	
	private SearchDAO getSearchDAO() {
		if(searchDAO == null) {
			searchDAO = new SearchDAOImpl();
		}
		return searchDAO;
	}
}
