package com.tags;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.to.Team;

public class PrintTeamTag extends SimpleTagSupport {
	private String[] teamheaders;
	private List<Team> teamList;

	@Override
	public void doTag() throws IOException {
		JspWriter out = getJspContext().getOut();
		out.print("<table border='1px' id='t01'>");
		out.print("<tr>");
		for (String teamheader : teamheaders) {
			out.print("<th>" + teamheader + "</th>");
		}
		out.print("</tr>");
		for(Team team:teamList) {
			out.print("<tr>");
			out.print("<td>" + team.getTeam_id() + "</td>");
			out.print("<td>" + team.getTeamname() + "</td>");
			out.print("<td>" + team.getCoachname() + "</td>");
			out.print("</tr>");
		}
		out.print("</table>");
	}

	public void setHeaders(String[] teamheaders) {
		this.teamheaders = teamheaders;
	}

	public void setTeamList(List<Team> teamList) {
		this.teamList = teamList;
	}
}
