package com.bo;

import com.dao.LoginDAOImpl;
import com.exception.BusinessException;
import com.to.Player;

public class LoginBOImpl implements LoginBO {

	@Override
	public boolean isValidPlayer(Player player) throws BusinessException {
		boolean b = false;
		if (player != null && player.getEmail() != null && player.getPassword() != null
				&& player.getEmail().matches("\\w+@\\w+\\.\\w+")
				&& player.getPassword().matches("[a-z]{3,6}@[0-9]{3}")) {
			b = new LoginDAOImpl().isValidPlayer(player);
		} else {
			throw new BusinessException("Invalid Email/Password");
		}
		return b;
	}

}
