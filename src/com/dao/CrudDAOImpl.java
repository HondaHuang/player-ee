package com.dao;

import java.sql.CallableStatement;
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
import com.to.Team;

public class CrudDAOImpl implements CrudDAO {
	public Player registerPlayer(String name, Date dob, String email, String gender, String teamname, long contact, String password) {
		Player player = null;
		try (Connection connection = OracleDbConnection.getConnection()) {
			String sql = "{call REGISTERPLAYER(?,?,?,?,?,?,?,?,?)}";
			CallableStatement callableStatement = connection.prepareCall(sql);
			callableStatement.registerOutParameter(1, java.sql.Types.VARCHAR);
			callableStatement.setString(2, name);
			callableStatement.setDate(3, new java.sql.Date(dob.getTime()));
			callableStatement.setString(4, email);
			callableStatement.setString(5, gender);
			callableStatement.setString(6, teamname);
			callableStatement.setLong(7, contact);
			callableStatement.setString(8, password);
			callableStatement.registerOutParameter(9, java.sql.Types.VARCHAR);
			callableStatement.execute();
			player = new Player(name, dob, email, gender, teamname, contact);
			System.out.println(player);
			if (callableStatement.getString(9) == null) {
				player.setId(callableStatement.getString(1));
			} else {
				throw new SQLException(callableStatement.getString(9));
			}
			player.setId(callableStatement.getString(1));
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Internal error occurred, kindly contact SYSADMIN");
		}
		return player;
	}

	public List<Player> getAllPlayers() {
		List<Player> playerList = new ArrayList<>();
		try (Connection connection = OracleDbConnection.getConnection()) {
			String sql = "select p.id,p.name,p.dob,p.email,p.gender,p.contact,t.teamName,t.team_id,t.coachname from player p join teams t on p.team_id = t.team_id";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Player player = new Player();
				player.setId((resultSet.getString("id")));
				player.setName(resultSet.getString("name"));
				player.setDob(resultSet.getDate("dob"));
				player.setEmail(resultSet.getString("email"));
				player.setGender(resultSet.getString("gender"));
				player.setContact(resultSet.getLong("contact"));
				player.setTeamname(resultSet.getString("teamName"));
				/*Team team = new Team();
				team.setTeamname(resultSet.getString("teamName"));
				team.setTeam_id(resultSet.getInt("team_id"));
				team.setCoachname(resultSet.getString("coachname"));
				player.setTeam(team);*/
				playerList.add(player);
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Internal error occurred, kindly contact SYSADMIN");
		}
		return playerList;
	}

	@Override
	public int updatePlayerContact(String id, long newContact) {
		int c = 0;
		try (Connection connection = OracleDbConnection.getConnection()) {
			String sql = "update player set contact = ? where id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, newContact);
			preparedStatement.setString(2, id);
			c = preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Internal error occurred, kindly contact SYSADMIN");
		}
		return c;
	}

	@Override
	public int deletePlayer(String id) {
		int c = 0;
		try (Connection connection = OracleDbConnection.getConnection()) {
			String sql = "delete from player where id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			c = preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Internal error occurred, kindly contact SYSADMIN");
		}
		return c;
	}

	@Override
	public Team registerTeam(String teamname, String coachname) throws BusinessException {
		Team team = null;
		try (Connection connection = OracleDbConnection.getConnection()) {
			String sql = "{call REGISTERTEAM(?,?,?)}";
			CallableStatement callableStatement = connection.prepareCall(sql);
			callableStatement.registerOutParameter(1, java.sql.Types.NUMERIC);
			callableStatement.setString(2, teamname);
			callableStatement.setString(3, coachname);
			callableStatement.execute();
			team = new Team(teamname, coachname);
			System.out.println(team);
			team.setTeam_id(callableStatement.getInt(1));
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Internal error occurred, kindly contact SYSADMIN");
		}
		return team;
	}

	@Override
	public List<Team> getAllTeams() throws BusinessException {
		List<Team> teamList = new ArrayList<>();
		try (Connection connection = OracleDbConnection.getConnection()) {
			String sql = "select team_id,teamname,coachname from teams";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Team team = new Team();
				team.setTeam_id(resultSet.getInt("team_id"));
				team.setTeamname((resultSet.getString("teamname")));
				team.setCoachname(resultSet.getString("coachname"));
				teamList.add(team);
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Internal error occurred, kindly contact SYSADMIN");
		}
		return teamList;
	}
}
