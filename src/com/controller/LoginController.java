package com.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bo.LoginBOImpl;
import com.exception.BusinessException;
import com.to.Player;

/**
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		Player player = new Player();
		player.setEmail(request.getParameter("email"));
		player.setPassword(request.getParameter("password"));
		RequestDispatcher rd = null;
		try {
			if(new LoginBOImpl().isValidPlayer(player)) {
				//rd = request.getRequestDispatcher("success");
				//rd.forward(request, response);
				HttpSession session = request.getSession();
				session.setAttribute("email", player.getEmail());
				response.sendRedirect("login.jsp");
			}
		} catch (BusinessException e) {
			rd = request.getRequestDispatcher("login.jsp");
			request.setAttribute("errorMessage", e.getMessage());
			rd.include(request, response);
			//out.println("<div align='center' style='color:red;'>" + e.getMessage() + "</div>");
		}
	}
}
