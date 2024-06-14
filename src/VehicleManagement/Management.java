package VehicleManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Management {
	
	private String user = "root";
	private String pass = "";
	private String url = "jdbc:mysql://localhost:3306/rentalmanagement";
	Connection myConnection;
	
	public Management() throws ClassNotFoundException,SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		myConnection = DriverManager.getConnection(url,user,pass);
		System.out.println("Connection established");
	}
	
	public String addVehicle(Vehicle vehicle) throws SQLException{
		String query = "INSERT INTO vehicle (brand,model,year) VALUES (?,?,?)";
		PreparedStatement pr = myConnection.prepareStatement(query);
		pr.setString(1, vehicle.getBrand());
		pr.setString(2,vehicle.getModel());
		pr.setInt(3, vehicle.getYear());
		
		pr.executeUpdate();
		return "    Successfully added    ";
	}
	
	public String deleteVehicle(int id) throws SQLException {
		String search = "SELECT * FROM vehicle WHERE id=?";
		PreparedStatement pr1 = myConnection.prepareStatement(search);
		pr1.setInt(1, id);
		ResultSet exists = pr1.executeQuery();
		if(exists.next()) {
			String query = "DELETE FROM vehicle WHERE id=?";
			PreparedStatement pr = myConnection.prepareStatement(query);
			pr.setInt(1, id);
			pr.executeUpdate();
			return "   Deletion successful   ";
		}
		else
			return "This vehicle does not exist!";
		
	}
	
	public String modifyVehicle(int id,Vehicle newVehicle) throws SQLException {
		String search = "SELECT * FROM vehicle WHERE id=?";
		PreparedStatement pr1 = myConnection.prepareStatement(search);
		pr1.setInt(1, id);
		ResultSet exists = pr1.executeQuery();
		if(exists.next()) {
			String query = "UPDATE vehicle SET brand = ?, model = ?, year = ? WHERE id = ?";
			PreparedStatement pr = myConnection.prepareStatement(query);
			pr.setString(1, newVehicle.getBrand());
			pr.setString(2, newVehicle.getModel());
			pr.setInt(3, newVehicle.getYear());
			pr.setInt(4, id);
			pr.executeUpdate();
			return "     Update successful     ";
		}
		else
			return "This vehicle does not exist!";
	}
	
	public ResultSet searchVehiclesString(String criteria, String value) throws SQLException {
	    String query = "SELECT * FROM vehicle WHERE " + criteria + " = ?";
	    PreparedStatement pr = myConnection.prepareStatement(query);
	    
	    pr.setString(1, value);
	    
	    ResultSet res = pr.executeQuery();
	    return res;
	
	}
		
	public ResultSet searchVehicleInt(String criteria,int value) throws SQLException{
		String query = "SELECT * FROM vehicle WHERE "+criteria+"=?";
		PreparedStatement pr = myConnection.prepareStatement(query);
		pr.setInt(1, value);
		ResultSet res = pr.executeQuery();
		return res;
		
	}
	
	public String addCustomer(Customer customer) throws SQLException{
		String query = "INSERT INTO customer (lastname,firstname,cin) VALUES (?,?,?)";
		PreparedStatement pr = myConnection.prepareStatement(query);
		pr.setString(1,customer.getLastName());
		pr.setString(2, customer.getFirstName());
		pr.setString(3, customer.getCin());
		pr.executeUpdate();
		return "    Successfully added    ";
	}

	public String deleteCustomer(int id) throws SQLException{
		String search = "SELECT * FROM customer WHERE id=?";
		PreparedStatement pr1 = myConnection.prepareStatement(search);
		pr1.setInt(1, id);
		ResultSet exists = pr1.executeQuery();
		if(exists.next()) {
			String query = "DELETE FROM customer WHERE id=?";
			PreparedStatement pr = myConnection.prepareStatement(query);
			pr.setInt(1, id);
			pr.executeUpdate();
			return "    Deletion successful    ";
		}
		else
			return "This customer does not exist!";
	}

	public String modifyCustomer(int id,Customer customer) throws SQLException{
		String search = "SELECT * FROM customer WHERE id=?";
		PreparedStatement pr1 = myConnection.prepareStatement(search);
		pr1.setInt(1, id);
		ResultSet exists = pr1.executeQuery();
		if(exists.next()) {
			String query = "UPDATE customer SET lastname=?, firstname=? ,cin=? WHERE id=?";
			PreparedStatement pr = myConnection.prepareStatement(query);
			pr.setString(1, customer.getLastName());
			pr.setString(2, customer.getFirstName());
			pr.setString(3, customer.getCin());
			pr.setInt(4, id);
			pr.executeUpdate();
			return "    Update successful    ";
		}
		else
			return "This customer does not exist!";
	}

	public ResultSet searchCustomers() throws SQLException{
		String query = "SELECT * FROM customer";
		PreparedStatement pr = myConnection.prepareStatement(query);
		ResultSet res = pr.executeQuery();
		return res;
	}

	public String registerRental(Rental r) throws SQLException{
		String search = "SELECT * FROM customer WHERE id=?";
		PreparedStatement pr1 = myConnection.prepareStatement(search);
		pr1.setInt(1, r.customerId);
		ResultSet exists = pr1.executeQuery();
		if(exists.next()) {
			search = "SELECT * FROM vehicle WHERE id=?";
			pr1 = myConnection.prepareStatement(search);
			pr1.setInt(1, r.vehicleId);
			exists = pr1.executeQuery();
			if(exists.next()) {
				String query = "INSERT INTO rental (customerId,vehicleId,startDate,returnDate) VALUES (?,?,?,?)";
				PreparedStatement pr = myConnection.prepareStatement(query);
				pr.setInt(1, r.getCustomerId());
				pr.setInt(2, r.getVehicleId());
				pr.setDate(3,java.sql.Date.valueOf(r.getStartDate()));
				pr.setDate(4,java.sql.Date.valueOf(r.getReturnDate()));
				pr.executeUpdate();
				return "The rental is registered successfully.";
			}
			else {
				return "This vehicle does not exist";
			}
		}
		else {
			return "This customer does not exist";
		}
		
	}

	public String closeRental(int id,int returnMileage,String returnState) throws SQLException{
		String search = "SELECT * FROM rental WHERE id=?";
		PreparedStatement pr1 = myConnection.prepareStatement(search);
		pr1.setInt(1, id);
		ResultSet exists = pr1.executeQuery();
		if(exists.next()) {
			String query  = "UPDATE rental SET returnMileage=?,returnState=? WHERE id=?";
			PreparedStatement pr = myConnection.prepareStatement(query);
			pr.setInt(1, returnMileage);
			pr.setString(2, returnState);
			pr.setInt(3, id);
			pr.executeUpdate();
			return "The rental is closed successfully";
		}
		else {
			return "This rental does not exist!";
		}
	}

	public void searchCustomerId(int id) throws SQLException{
		String query ="SELECT * FROM customer WHERE id=?";
		PreparedStatement pr = myConnection.prepareStatement(query);
		pr.setInt(1, id);
		ResultSet res = pr.executeQuery();
		if(res.next()) {
			String lastName = res.getString("lastname");
			String firstName = res.getString("firstname");
			String cin = res.getString("cin");
			System.out.print("\t\t"+lastName+"\t\t"+firstName+"\t\t\t"+cin);
		}
		else 
			System.out.print("There is no customer with id= "+id+"!");
		
	}
	public ResultSet viewRental() throws SQLException{
		String query = "SELECT * FROM rental";
		PreparedStatement pr = myConnection.prepareStatement(query);		
		ResultSet res = pr.executeQuery();
		return res;
	}
}

