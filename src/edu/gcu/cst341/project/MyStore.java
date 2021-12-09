package edu.gcu.cst341.project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Scanner;

public class MyStore {

	private Scanner sc = new Scanner(System.in);
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
	
	// Matt Kollar
	// 12/2/2021
	private void readProducts() {
		System.out.println("PRODUCTS");
		String sql = "SELECT * FROM cst341project.products;"; 
		try {
			Statement stmt = con.getConnection().createStatement();
			ResultSet results = stmt.executeQuery(sql);
			System.out.println("Product Id, Product Name,  Product Price,  Product Stock Status");
			System.out.println("---------------------------------------------------------------------");
			while (results.next()) {
				System.out.println(results.getInt("ProductID") + ",   " + "\t" + "   "
						+ results.getString("ProductName") + "," + "\t" + "   " + "$" 
						+ results.getString("ProductPrice") + "," + "\t" + "   " + "inStock = "
						+ results.getBoolean("ProductStockStatus"));

			}
			System.out.println();
			stmt.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//Dez adding ...I should be able to update the products in the catalog so users can add them to the shopping cart. 
	private void updateProduct() {
		System.out.println("Update product...");

		readProducts();
		System.out.println("------------------");
		System.out.println("Enter the product ID you would like to add to the shopping cart." + "\n");
		int productId = sc.nextInt();
		sc.nextLine();
		
		String previous = "SELECT * FROM products where productID = ?";
		
		Credential c1 =null;
		
		try {
			PreparedStatement ps = con.getConnection().prepareStatement(previous);//Connection in question!!
			ps.setInt(1, productId);
			//ps.execute();
			ResultSet previousInfo = ps.executeQuery();
			previousInfo.next();
			c1 = new Credential(productId, previousInfo.getString("productName"), previousInfo.getInt("productPrice"),
					previousInfo.getInt("productStockStatus"));//ASK ROB
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	readProducts();
	
	String pname = null;
	double pprice = 0;
	int pstockstatus = 0;

	System.out.println(">>Just hit ENTER if no change is needed : <<");
	
	try {

//		System.out.println("What is your updated Product Number? [" + c1.getProductId() + "]");
//		productId = sc.nextInt();
//		sc.nextLine();
//		// if (!climit.equals(""))
//		c1.setProductId(productId);
		
		System.out.println("What is your Product name? [" + c1.getpName() + "] ");
		pname = sc.nextLine();
		if (!pname.equals(""))// input users info as a string
			c1.setpName(pname);// set

		System.out.println("What is your Product Price? [" + c1.getpPrice() + "] ");
		pprice = sc.nextDouble();
		sc.nextLine();
		if (pprice != 0);
		c1.setpPrice(pprice);// set
		
		System.out.println("What is your Product stock status? [" + c1.getpStockStatus() + "] ");
		pstockstatus = sc.nextInt();
		sc.nextLine();
		//if (pstockstatus != false);
		c1.setpStockStatus(pstockstatus);// set
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	String sql = "UPDATE products SET productPrice = ?, productStockStatus = ? WHERE productName = ?"; //visit productId focus on 5th ?, disallow the productid as primary key editing
			
			try {
				PreparedStatement ps = con.getConnection().prepareStatement(sql);
				//ps.setInt(1, productId);// specified type of parameter value compatable with sql
				//ps.setString(1, pname);
				ps.setDouble(1, pprice);
				ps.setInt(2, pstockstatus);
				ps.setString(3, pname); //ps.setInt(4, this.parseInt(productId1));
			
				// pst.setString(2, dayTxt.getText()+"-" + monthTxt.getText()+"-" + yearTxt.getText());
				ps.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
			readProducts();
	}
	
	private void deleteProduct() {
		System.out.println("Delete product...");
		System.out.println();
	}

}

