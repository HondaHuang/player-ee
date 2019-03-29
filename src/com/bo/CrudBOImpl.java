package com.bo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.dao.CrudDAOImpl;
import com.dao.CrudDAO;
import com.exception.BusinessException;
import com.to.Player;
import com.to.Team;

public class CrudBOImpl implements CrudBO {
	private CrudDAO crudDAO;

	@Override
	public Player registerPlayer(String name, Date dob, String email, String gender, String teamname, long contact,
			String password) throws BusinessException {
		Player player = null;
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
		if (name.matches("[a-zA-Z]{3,20}")) {
			if (formatter.format(dob).matches("[0-9]{2}-[0-9]{2}-[0-9]{4}")) {
				if (email.matches("\\w+@\\w+\\.\\w+")) {
					if (gender.matches("[MF]{1}")) {
						if (teamname.matches("[a-zA-Z]{3,15}")) {
							if (Long.toString(contact).matches("[0-9]{10}")) {
								crudDAO = getPlayerDAO();
								player = crudDAO.registerPlayer(name, dob, email, gender, teamname, contact, password);
							} else {
								throw new BusinessException("Entered player contact: " + contact + " is invalid");
							}
						} else {
							throw new BusinessException("Entered team name: " + teamname + " is invalid");
						}
					} else {
						throw new BusinessException("Entered gender: " + gender + " is invalid");
					}
				} else {
					throw new BusinessException("Entered player email: " + email + " is invalid");
				}
			} else {
				throw new BusinessException("Entered player dob: " + dob + " is invalid");
			}
		} else {
			throw new BusinessException("Entered name: " + name + " is invalid");
		}
		return player;
	}

	@Override
	public List<Player> getAllPlayers() {
		crudDAO = getPlayerDAO();
		List<Player> playerList = null;
		try {
			playerList = crudDAO.getAllPlayers();
		} catch (BusinessException e) {
			System.out.println("Error viewing players from database");
		}
		return playerList;
	}

	@Override
	public int updatePlayerContact(String id, long newContact) throws BusinessException {
		int c = 0;
		if (Long.toString(newContact).matches("[0-9]{10}")) {
			if (id.matches("[Pp]{1}[a-zA-Z]{2}[0-9]{7}")) {
				crudDAO = getPlayerDAO();
				c = crudDAO.updatePlayerContact(id, newContact);
			} else {
				throw new BusinessException("Entered player ID: " + id + " is invalid");
			}
		} else {
			throw new BusinessException("New contact num: " + newContact + " is invalid");
		}
		return c;
	}

	@Override
	public int deletePlayer(String id) throws BusinessException {
		int c = 0;
		if (id.matches("[Pp]{1}[a-zA-Z]{2}[0-9]{7}")) {
			crudDAO = getPlayerDAO();
			c = crudDAO.deletePlayer(id);
		} else {
			throw new BusinessException("Entered player ID: " + id + " is invalid");
		}
		return c;
	}

	private CrudDAO getPlayerDAO() {
		if (crudDAO == null) {
			crudDAO = new CrudDAOImpl();
		}
		return crudDAO;
	}

	@Override
	public Team registerTeam(String teamname, String coachname) throws BusinessException {
		Team team = null;
		if (teamname.matches("[a-zA-Z]{3,50}")) {
			if (coachname.matches("[a-zA-Z]{3,50}")) {
				crudDAO = getPlayerDAO();
				team = crudDAO.registerTeam(teamname, coachname);
			} else {
				throw new BusinessException("Entered coach name: " + coachname + " is invalid");
			}
		} else {
			throw new BusinessException("Entered team name: " + teamname + " is invalid");
		}
		return team;
	}

	@Override
	public List<Team> getAllTeams() throws BusinessException {
		crudDAO = getPlayerDAO();
		List<Team> teamList = null;
		try {
			teamList = crudDAO.getAllTeams();
		} catch (BusinessException e) {
			System.out.println("Error viewing teams from database");
		}
		return teamList;
	}
}
