package com.to;

public class Team {
	private int team_id;
	private String teamname;
	private String coachname;

	public Team() {
		// TODO Auto-generated constructor stub
	}

	public int getTeam_id() {
		return team_id;
	}

	public void setTeam_id(int team_id) {
		this.team_id = team_id;
	}

	public String getTeamname() {
		return teamname;
	}

	public void setTeamname(String teamname) {
		this.teamname = teamname;
	}

	public String getCoachname() {
		return coachname;
	}

	public void setCoachname(String coachname) {
		this.coachname = coachname;
	}

	public Team(String teamname, String coachname) {
		this.teamname = teamname;
		this.coachname = coachname;
	}

	public Team(int team_id, String teamname, String coachname) {
		this.team_id = team_id;
		this.teamname = teamname;
		this.coachname = coachname;
	}

	@Override
	public String toString() {
		return "Team [teamname=" + teamname + ", coachname=" + coachname + "]";
	}
}
