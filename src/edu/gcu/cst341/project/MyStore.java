package edu.gcu.cst341.project;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class MyStore {

	private String name; 
	private DBConnect con;
	private Scanner sc = new Scanner(System.in);//Madalina Marian Dec02

	MyStore (String name){
		this.name = name;
		con = new DBConnect();
	}

	public void open() {
		String user = null;
		boolean exit = false;
		do {
			switch (UserInterface.menuMain()) {
			case 0:
				System.out.println("Thank you! Come again!");
				exit = true;
				break;
			case 1:
				user = login();
				if (user != null) {
					System.out.println("Login successful!!");
					shop();
				}
				else {
					System.out.println("Login unsuccessful");
				}
				break;
			case 2:
				admin();
				break;
			default:
				open();
			}
		} while (!exit);
	}

	private String login() {
		String result = null;

		String [] login = UserInterface.login();

		String sql = "SELECT UserId, UserFirstName FROM users WHERE UserName = ? AND UserPassword = ? AND UserStatus = 1";

		try (PreparedStatement ps = con.getConnection().prepareStatement(sql)){
			ps.setString(1, login[0]);
			ps.setString(2, login[1]);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				result = rs.getString("UserFirstName");
			}
			else {
				result = null;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private void shop() {
		switch (UserInterface.menuShop()) {
		case 0:
			return;
		case 1:
			createCartItem();
			break;
		case 2:
			readCartItems();
			break;
		case 3:
			deleteCartItem();
			break;
		default:
			return;
		}
	}

	private void admin() {
		switch (UserInterface.menuAdmin()) {
		case 0:
			return;
		case 1:
			createProduct();
			break;
		case 2:
			readProducts();
			break;
		case 3:
			updateProduct();
			break;
		case 4:
			deleteProduct();
			break;	
		default:
			open();
		}
	}
	
	private void createCartItem() {
		System.out.println("Add (Create) item to cart...");
		System.out.println();
	}
	
	private void readCartItems() {
		System.out.println("View (Read) cart...");
		System.out.println();
	}
	
	private void deleteCartItem() {
		System.out.println("Delete from cart...");
		System.out.println();
	}
	
	private void createProduct() { //Madalina Marian Dec02
		System.out.println("Create product...");
		System.out.println();
		System.out.println("==============================" + "\n");
		System.out.println("What is the Product Id Number?");
		int productId = sc.nextInt();
		sc.nextLine();
		System.out.println("What is the Product Name?");
		String pName = sc.nextLine();
		System.out.println("What is the Product Price?");
		String pPrice = sc.nextLine();
		System.out.println("What is the Product Stock Status?");
		int pStockStatus = sc.nextInt();
		
		String sql = "INSERT INTO products" + "(ProductID, ProductName, ProductPrice, ProductStockStatus)"
				+ "VALUE (?,?,?,?);";
		try {
			
			PreparedStatement ps = con.getConnection().prepareStatement(sql);
			ps.setInt(1, productId);
			ps.setString(2, pName);
			ps.setString(3, pPrice);
			ps.setInt(4, pStockStatus);
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		readProducts();
	}
	
	private void readProducts() {
		System.out.println("View (Read) all products...");
		System.out.println();
	}
	
	private void updateProduct() {
		System.out.println("Update product...");

	}
	
	private void deleteProduct() {
		System.out.println("Delete product...");
		System.out.println();
	}

}

