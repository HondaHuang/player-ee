package com.to;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Player {
	private String id;
	private String name;
	private Date dob; 
	private String email;
	private String gender;
	private String teamname;
	private long contact;
	private Team team;
	private String password;
	private String t_dob;

	public Player() {
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	public void setDob()
	{
		try {
			this.dob = new SimpleDateFormat("yyyy-MM-dd").parse(t_dob);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getTeamname() {
		return teamname;
	}

	public void setTeamname(String teamname) {
		this.teamname = teamname;
	}

	public long getContact() {
		return contact;
	}

	public void setContact(long contact) {
		this.contact = contact;
	}

	public Team getTeam() {
		return team;
	}
	
	public void setTeam(Team team) {
		this.team = team;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Player(String id, String name, Date dob, String email, String gender, String teamname, long contact, Team team) {
		this.id = id;
		this.name = name;
		this.dob = dob;
		this.email = email;
		this.gender = gender;
		this.teamname = teamname;
		this.contact = contact;
		this.team = team;
	}

	public Player(String name, Date dob, String email, String gender, String teamname, long contact, Team team) {
		this.name = name;
		this.dob = dob;
		this.email = email;
		this.gender = gender;
		this.teamname = teamname;
		this.contact = contact;
		this.team = team;
	}
	
	public Player(String name, Date dob, String email, String gender, String teamname, long contact) {
		this.name = name;
		this.dob = dob;
		this.email = email;
		this.gender = gender;
		this.teamname = teamname;
		this.contact = contact;
	}

	public Player(String name, Date dob, String gender, long contact, String teamname, String email, String password) {
		this.name = name;
		this.dob = dob;
		this.gender = gender;
		this.contact = contact;
		this.teamname = teamname;
		this.email = email;
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "Player [name=" + name + ", dob=" + dob + ", email=" + email + ", gender=" + gender
				+ ", teamname=" + teamname + ", contact=" + contact + "]";
	}

	public String getT_dob() {
		return t_dob;
	}

	public void setT_dob(String t_dob) {
		this.t_dob = t_dob;
	}

	
}
