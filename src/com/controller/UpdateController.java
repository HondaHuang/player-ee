package com.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
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
 * Servlet implementation class UpdateController
 */
public class UpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateController() {
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
			Long contact = Long.parseLong(request.getParameter("contact"));
			crudBO.updatePlayerContact(id, contact);
			player = searchBO.getPlayerById(id);
			if (player != null) {
				playerList = new ArrayList<>();
				playerList.add(player);
				HttpSession session = request.getSession();
				session.setAttribute("headers", headers);
				session.setAttribute("playerList", playerList);
				response.sendRedirect("update.jsp");
			}
		} catch (BusinessException e) {
			request.setAttribute("errorMessage", e.getMessage());
			request.getRequestDispatcher("update.jsp").include(request, response);
		}

	}

}
