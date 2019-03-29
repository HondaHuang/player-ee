package com.bo;

import java.util.Date;
import java.util.List;

import com.exception.BusinessException;
import com.to.Player;
import com.to.Team;

public interface CrudBO {
	public Player registerPlayer(String name, Date dob, String email, String gender, String teamname, long contact, String password) throws BusinessException;
	public List<Player> getAllPlayers() throws BusinessException;
	public int updatePlayerContact(String id, long newContact) throws BusinessException;
	public int deletePlayer(String id) throws BusinessException;
	public Team registerTeam(String teamname, String coachname) throws BusinessException;
	public List<Team> getAllTeams() throws BusinessException;
}
