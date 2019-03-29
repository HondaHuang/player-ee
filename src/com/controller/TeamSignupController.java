package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bo.CrudBO;
import com.bo.CrudBOImpl;
import com.exception.BusinessException;
import com.to.Team;

/**
 * Servlet implementation class TeamSignupController
 */
public class TeamSignupController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TeamSignupController() {
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
		HttpSession session = request.getSession(false);
		Team team = (Team) session.getAttribute("team");
		crudBO = getCrudBO();
		try {
			crudBO.registerTeam(team.getTeamname(), team.getCoachname());
		} catch (BusinessException e) {
			request.setAttribute("errorMessage2", e.getMessage());
			request.getRequestDispatcher("signup.jsp").include(request, response);
		}
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>Team with below details added</h3>");
		out.print("<h3>" + team + "</h3>");
	}

}
