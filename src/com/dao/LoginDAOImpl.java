package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dbutil.OracleDbConnection;
import com.exception.BusinessException;
import com.to.Player;

public class LoginDAOImpl implements LoginDAO {

	@Override
	public boolean isValidPlayer(Player player) throws BusinessException {
		boolean b = false;
		String sql = "select email from player where email=? and password=?";
		try (Connection connection = OracleDbConnection.getConnection();){
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, player.getEmail());
			preparedStatement.setString(2, player.getPassword());
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				b = true;
			} else {
				throw new BusinessException("Invalid Login Credentials");
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Internal Error: " + e);
		}
		return b;
	}

}
