package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dbutil.OracleDbConnection;
import com.exception.BusinessException;
import com.to.Player;

public class SearchDAOImpl implements SearchDAO {

	@Override
	public Player getPlayerById(String id) throws BusinessException {
		Player player = null;
		try (Connection connection = OracleDbConnection.getConnection()) {
			String sql = "select p.id,p.name,p.dob,p.email,p.gender,p.contact,t.teamName from player p join teams t on p.team_id = t.team_id where p.id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, id.toUpperCase());
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				player = new Player();
				player.setId(id);
				player.setName(resultSet.getString("name"));
				player.setEmail(resultSet.getString("email"));
				player.setTeamname(resultSet.getString("teamName"));
				player.setGender(resultSet.getString("gender"));
				player.setContact(resultSet.getLong("contact"));
				player.setDob(resultSet.getDate("dob"));
			} else {
				throw new BusinessException("Player with ID: " + id + " not found");
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal error occurred, kindly contact SYSADMIN");
		}
		return player;
	}

	@Override
	public Player getPlayerByContact(long contact) throws BusinessException {
		Player player = null;
		try (Connection connection = OracleDbConnection.getConnection()) {
			String sql = "select p.id,p.name,p.dob,p.email,p.gender,p.contact,t.teamName from player p join teams t on p.team_id = t.team_id where p.contact = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, contact);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				player = new Player();
				player.setId(resultSet.getString("id"));
				player.setName(resultSet.getString("name"));
				player.setEmail(resultSet.getString("email"));
				player.setTeamname(resultSet.getString("teamName"));
				player.setGender(resultSet.getString("gender"));
				player.setContact(contact);
				player.setDob(resultSet.getDate("dob"));
			} else {
				throw new BusinessException("Player with contact: " + contact + " not found");
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal error occurred, kindly contact SYSADMIN");
		}
		return player;
	}

	@Override
	public Player getPlayerByEmail(String email) throws BusinessException {
		Player player = null;
		try (Connection connection = OracleDbConnection.getConnection()) {
			String sql = "select p.id,p.name,p.dob,p.gender,p.contact,t.teamName from player p join teams t on p.team_id = t.team_id where p.email = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, email);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				player = new Player();
				player.setId(resultSet.getString("id"));
				player.setName(resultSet.getString("name"));
				player.setEmail(email);
				player.setTeamname(resultSet.getString("teamName"));
				player.setGender(resultSet.getString("gender"));
				player.setContact(resultSet.getLong("contact"));
				player.setDob(resultSet.getDate("dob"));
			} else {
				throw new BusinessException("Player with email: " + email + " not found");
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal error occurred, kindly contact SYSADMIN");
		}
		return player;
	}

	@Override
	public List<Player> getPlayersByGender(String gender) throws BusinessException {
		List<Player> playerList = new ArrayList<>();
		try (Connection connection = OracleDbConnection.getConnection()) {
			String sql = "select p.id,p.name,p.dob,p.email,p.contact,t.teamName from player p join teams t on p.team_id = t.team_id where p.gender = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, gender);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Player player = new Player();
				player.setId(resultSet.getString("id"));
				player.setName(resultSet.getString("name"));
				player.setEmail(resultSet.getString("email"));
				player.setTeamname(resultSet.getString("teamName"));
				player.setGender(gender);
				player.setContact(resultSet.getLong("contact"));
				player.setDob(resultSet.getDate("dob"));
				playerList.add(player);
			}
			if (playerList.size() == 0) {
				throw new BusinessException("Players with gender: " + gender + " not found");
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal error occurred, kindly contact SYSADMIN");
		}
		return playerList;
	}

	@Override
	public List<Player> getPlayersByDOB(Date dob) throws BusinessException {
		List<Player> playerList = new ArrayList<>();
		try (Connection connection = OracleDbConnection.getConnection()) {
			String sql = "select p.id,p.name,p.dob,p.email,p.gender,p.contact,t.teamName from player p join teams t on p.team_id = t.team_id where p.dob = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setDate(1, new java.sql.Date(dob.getTime()));
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Player player = new Player();
				player.setId(resultSet.getString("id"));
				player.setName(resultSet.getString("name"));
				player.setEmail(resultSet.getString("email"));
				player.setTeamname(resultSet.getString("teamName"));
				player.setGender(resultSet.getString("gender"));
				player.setContact(resultSet.getLong("contact"));
				player.setDob(resultSet.getDate("dob"));
				playerList.add(player);
			}
			if (playerList.size() == 0) {
				throw new BusinessException("Players with DOB: " + dob + " not found");
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal error occurred, kindly contact SYSADMIN");
		}
		return playerList;
	}

	@Override
	public List<Player> getPlayersByName(String name) throws BusinessException {
		List<Player> playerList = new ArrayList<>();
		try (Connection connection = OracleDbConnection.getConnection()) {
			String sql = "select p.id,p.dob,p.email,p.gender,p.contact,t.teamName from player p join teams t on p.team_id = t.team_id where p.name = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Player player = new Player();
				player.setId(resultSet.getString("id"));
				player.setName(name);
				player.setEmail(resultSet.getString("email"));
				player.setTeamname(resultSet.getString("teamName"));
				player.setGender(resultSet.getString("gender"));
				player.setContact(resultSet.getLong("contact"));
				player.setDob(resultSet.getDate("dob"));
				playerList.add(player);
			}
			if (playerList.size() == 0) {
				throw new BusinessException("Players with name: " + name + " not found");
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal error occurred, kindly contact SYSADMIN");
		}
		return playerList;
	}

	@Override
	public List<Player> getPlayersByTeamName(String teamName) throws BusinessException {
		List<Player> playerList = new ArrayList<>();
		try (Connection connection = OracleDbConnection.getConnection()) {
			String sql = "select p.id,p.name,p.dob,p.email,p.gender,p.contact from player p join teams t on p.team_id = t.team_id where t.teamName = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, teamName);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Player player = new Player();
				player.setId(resultSet.getString("id"));
				player.setName(resultSet.getString("name"));
				player.setEmail(resultSet.getString("email"));
				player.setTeamname(teamName);
				player.setGender(resultSet.getString("gender"));
				player.setContact(resultSet.getLong("contact"));
				player.setDob(resultSet.getDate("dob"));
				playerList.add(player);
			}
			if (playerList.size() == 0) {
				throw new BusinessException("Players with team name: " + teamName + " not found");
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal error occurred, kindly contact SYSADMIN");
		}
		return playerList;
	}

}
