package com.bo;

import java.util.Date;
import java.util.List;

import com.exception.BusinessException;
import com.to.Player;

public interface SearchBO {
	public Player getPlayerById(String id) throws BusinessException;
	public Player getPlayerByContact(long contact) throws BusinessException;
	public Player getPlayerByEmail(String email) throws BusinessException;
	public List<Player> getPlayersByGender(String gender) throws BusinessException;
	public List<Player> getPlayersByDOB(Date dob) throws BusinessException;
	public List<Player> getPlayersByName(String name) throws BusinessException;
	public List<Player> getPlayersByTeamName(String teamName) throws BusinessException;
}
