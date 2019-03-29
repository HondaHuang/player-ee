package com.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bo.SearchBO;
import com.bo.SearchBOImpl;
import com.exception.BusinessException;
import com.to.Player;

/**
 * Servlet implementation class SearchController
 */
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchController() {
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

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int ch = Integer.parseInt(request.getParameter("searchoption"));
		try {
			Player player = null;
			List<Player> playerList = null;
			searchBO = getSearchBO();
			String[] headers = {"Player ID", "Name", "DOB", "Gender", "Contact", "Email", "Team Name"};
			switch (ch) {
			case 1:
				String id = request.getParameter("searchValue");
				player = searchBO.getPlayerById(id);
				if (player != null) {
					playerList = new ArrayList<>();
					playerList.add(player);
					HttpSession session = request.getSession();
					session.setAttribute("headers", headers);
					session.setAttribute("playerList", playerList);
					response.sendRedirect("search.jsp");
				}
				break;
			case 2:
				String name = request.getParameter("searchValue");
				playerList = searchBO.getPlayersByName(name);
				if (playerList != null && playerList.size() > 0) {
					HttpSession session = request.getSession();
					session.setAttribute("headers", headers);
					session.setAttribute("playerList", playerList);
					response.sendRedirect("search.jsp");
				}
				break;
			case 3:
				String email = request.getParameter("searchValue");
				player = searchBO.getPlayerByEmail(email);
				if (player != null) {
					playerList = new ArrayList<>();
					playerList.add(player);
					HttpSession session = request.getSession();
					session.setAttribute("headers", headers);
					session.setAttribute("playerList", playerList);
					response.sendRedirect("search.jsp");
				}
				break;
			case 4:
				String s = request.getParameter("searchValue");
				Date dob = new SimpleDateFormat("yyyy-MM-dd").parse(s);
				playerList = searchBO.getPlayersByDOB(dob);
				if (playerList != null && playerList.size() > 0) {
					HttpSession session = request.getSession();
					session.setAttribute("headers", headers);
					session.setAttribute("playerList", playerList);
					response.sendRedirect("search.jsp");
				}
				break;
			case 5:
				Long contact = Long.parseLong(request.getParameter("searchValue"));
				player = searchBO.getPlayerByContact(contact);
				if (player != null) {
					playerList = new ArrayList<>();
					playerList.add(player);
					HttpSession session = request.getSession();
					session.setAttribute("headers", headers);
					session.setAttribute("playerList", playerList);
					response.sendRedirect("search.jsp");
				}
				break;
			case 6:
				String gender = request.getParameter("searchValue");
				playerList = searchBO.getPlayersByGender(gender);
				if (playerList != null && playerList.size() > 0) {
					HttpSession session = request.getSession();
					session.setAttribute("headers", headers);
					session.setAttribute("playerList", playerList);
					response.sendRedirect("search.jsp");
				}
				break;
			case 7:
				String teamName = request.getParameter("teamname");
				playerList = searchBO.getPlayersByTeamName(teamName);
				if (playerList != null && playerList.size() > 0) {
					HttpSession session = request.getSession();
					session.setAttribute("headers", headers);
					session.setAttribute("playerList", playerList);
					response.sendRedirect("search.jsp");
				}
				break;
			default:
				throw new BusinessException("Invalid Search Criteria");
			}
		} catch (BusinessException | ParseException e) {
			request.setAttribute("errorMessage", e.getMessage());
			request.getRequestDispatcher("search.jsp").include(request, response);
		}
	}

}
