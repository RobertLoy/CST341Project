package edu.gcu.cst341.project;

import java.util.Date;

public class Credential {

	private int userId;
	private String userName;
	private String userPW;
	private String fname;
	private int userStatus;
	private int productID;
	private String pName;
	private double pPrice;
	private int pStockStatus;
	private Date addDate;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPW() {
		return userPW;
	}

	public void setUserPW(String userPW) {
		this.userPW = userPW;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public int getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(int userStatus) {
		this.userStatus = userStatus;
	}

	public int getProdcutID() {
		return productID;
	}

	public void setProdcutID(int prodcutID) {
		this.productID = prodcutID;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public double getpPrice() {
		return pPrice;
	}

	public void setpPrice(double pPrice) {
		this.pPrice = pPrice;
	}

	public int getpStockStatus() {
		return pStockStatus;
	}

	public void setpStockStatus(int pStockStatus) {
		this.pStockStatus = pStockStatus;
	}

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	@Override
	public String toString() {
		return "Credential [userId=" + userId + ", userName=" + userName + ", userPW=" + userPW + ", fname=" + fname
				+ ", userStatus=" + userStatus + ", prodcutID=" + productID + ", pName=" + pName + ", pPrice=" + pPrice
				+ ", pStockStatus=" + pStockStatus + ", addDate=" + addDate + "]";
	}

	public Credential(int userId, String userName, String userPW, String fname, int userStatus) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPW = userPW;
		this.fname = fname;
		this.userStatus = userStatus;
	}

	public Credential(int productID, String pName, double pPrice, int pStockStatus) {
		super();
		this.productID = productID;
		this.pName = pName;
		this.pPrice = pPrice;
		this.pStockStatus = pStockStatus;
		
	}

	
	

}
