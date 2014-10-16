package com.dj.web.credit;

public class UserToken {
	private String nick;
	private String token;
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getToken() {
		return token;
	}
	@Override
	public String toString() {
		return "UserToken [nick=" + nick + ", token=" + token + "]";
	}
	public void setToken(String token) {
		this.token = token;
	}
}
