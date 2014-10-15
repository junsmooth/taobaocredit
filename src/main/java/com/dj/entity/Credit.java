package com.dj.entity;

public class Credit {

	private String userName;
	private String registerTime;
	private String buyerPoints;
	public String getBuyerPoints() {
		return buyerPoints;
	}

	public void setBuyerPoints(String buyerPoints) {
		this.buyerPoints = buyerPoints;
	}

	// buyer last week point
	private String b_pointOfLastWeek;

	@Override
	public String toString() {
		return "Credit [userName=" + userName + ", registerTime="
				+ registerTime + ", buyerPoints=" + buyerPoints
				+ ", b_pointOfLastWeek=" + b_pointOfLastWeek
				+ ", b_pointOfLastMonth=" + b_pointOfLastMonth
				+ ", securityLevel=" + securityLevel + "]";
	}

	private String b_pointOfLastMonth;
	private String securityLevel;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	public String getB_pointOfLastWeek() {
		return b_pointOfLastWeek;
	}

	public void setB_pointOfLastWeek(String b_pointOfLastWeek) {
		this.b_pointOfLastWeek = b_pointOfLastWeek;
	}

	public String getB_pointOfLastMonth() {
		return b_pointOfLastMonth;
	}

	public void setB_pointOfLastMonth(String b_pointOfLastMonth) {
		this.b_pointOfLastMonth = b_pointOfLastMonth;
	}

	public String getSecurityLevel() {
		return securityLevel;
	}

	public void setSecurityLevel(String securityLevel) {
		this.securityLevel = securityLevel;
	}

}
