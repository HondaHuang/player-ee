package com.tags;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.to.Player;

public class PrintPlayerTag extends SimpleTagSupport {
	private String[] headers;
	private List<Player> playerList;

	@Override
	public void doTag() throws IOException {
		JspWriter out = getJspContext().getOut();
		out.print("<table border='1px' id='t01'>");
		out.print("<tr>");
		for (String header : headers) {
			out.print("<th>" + header + "</th>");
		}
		out.print("</tr>");
		for(Player player:playerList) {
			out.print("<tr>");
			out.print("<td>" + player.getId() + "</td>");
			out.print("<td>" + player.getName() + "</td>");
			out.print("<td>" + player.getDob() + "</td>");
			out.print("<td>" + player.getGender() + "</td>");
			out.print("<td>" + player.getContact() + "</td>");
			out.print("<td>" + player.getEmail() + "</td>");
			out.print("<td>" + player.getTeamname() + "</td>");
			out.print("</tr>");
		}
		out.print("</table>");
	}

	public void setHeaders(String[] headers) {
		this.headers = headers;
	}

	public void setPlayerList(List<Player> playerList) {
		this.playerList = playerList;
	}
}
