package com.bo;

import com.exception.BusinessException;
import com.to.Player;

public interface LoginBO {
	public boolean isValidPlayer(Player player) throws BusinessException;
}
