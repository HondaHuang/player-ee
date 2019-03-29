package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bo.CrudBO;
import com.bo.CrudBOImpl;
import com.exception.BusinessException;
import com.to.Player;
import com.to.Team;

/**
 * Servlet implementation class ViewController
 */
public class ViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	private CrudBO crudBO;

	private CrudBO getCrudBO() {
		if (crudBO == null) {
			crudBO = new CrudBOImpl();
		}
		return crudBO;
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		crudBO = getCrudBO();
		String[] headers = {"Player ID", "Name", "DOB", "Gender", "Contact", "Email", "Team Name"};
		String[] teamheaders = {"Team ID", "Team Name", "Coach Name"};
		List<Player> playerList;
		List<Team> teamList;
		try {
			playerList = crudBO.getAllPlayers();
			teamList = crudBO.getAllTeams();
			if (playerList != null && playerList.size() > 0 && teamList != null && teamList.size() > 0) {
				HttpSession session = request.getSession();
				session.setAttribute("headers", headers);
				session.setAttribute("playerList", playerList);
				session.setAttribute("teamheaders", teamheaders);
				session.setAttribute("teamList", teamList);
				response.sendRedirect("view.jsp");
			}
		} catch (BusinessException e) {
			request.setAttribute("errorMessage", e.getMessage());
			request.getRequestDispatcher("view.jsp").include(request, response);
		}
	}

}
