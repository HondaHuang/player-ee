package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bo.CrudBO;
import com.bo.CrudBOImpl;
import com.bo.SearchBO;
import com.bo.SearchBOImpl;
import com.exception.BusinessException;
import com.to.Player;

/**
 * Servlet implementation class DeleteController
 */
public class DeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	private SearchBO searchBO;

	private SearchBO getSearchBO() {
		if (searchBO == null) {
			searchBO = new SearchBOImpl();
		}
		return searchBO;
	}

	private CrudBO crudBO;

	private CrudBO getCrudBO() {
		if (crudBO == null) {
			crudBO = new CrudBOImpl();
		}
		return crudBO;
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Player player = null;
			List<Player> playerList = null;
			searchBO = getSearchBO();
			crudBO = getCrudBO();
			String[] headers = { "Player ID", "Name", "DOB", "Gender", "Contact", "Email", "Team Name" };
			String id = request.getParameter("id");
			player = searchBO.getPlayerById(id);
			HttpSession session = request.getSession();
			if (player != null) {
				playerList = new ArrayList<>();
				playerList.add(player);
				session.setAttribute("headers", headers);
				session.setAttribute("playerList", playerList);
				response.sendRedirect("delete.jsp");
			}
			if (player.getEmail().equals(session.getAttribute("email"))) {
				session.invalidate();
			}
			crudBO.deletePlayer(id);
		} catch (BusinessException e) {
			request.setAttribute("errorMessage", e.getMessage());
			request.getRequestDispatcher("delete.jsp").include(request, response);
		}
	}

}
