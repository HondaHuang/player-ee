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
import com.to.Player;

/**
 * Servlet implementation class SignupController
 */
public class SignupController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SignupController() {
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
		Player player = (Player) session.getAttribute("player");
		player.setDob();
		crudBO = getCrudBO();
		try {
			crudBO.registerPlayer(player.getName(), player.getDob(), player.getEmail(), player.getGender(),
					player.getTeamname(), player.getContact(), player.getPassword());
		} catch (BusinessException e) {
			request.setAttribute("errorMessage", e.getMessage());
			request.getRequestDispatcher("signup.jsp").include(request, response);
		}
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>Player with below details added</h3>");
		out.print("<h3>" + player + "</h3>");
	}

}
