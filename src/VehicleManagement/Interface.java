package VehicleManagement;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import javax.swing.*;


@SuppressWarnings("serial")
public class Interface extends JFrame implements ActionListener{
	Management M;
	JPanel mainPanel,tPanel,bPanel,menuPanel,avPanel,dvPanel,mvPanel,rvPanel,resultPanel,acPanel,dcPanel,mcPanel,elPanel,clPanel;
	JButton[] buttons;
	JButton returnButton;
	JLabel title;
	JButton b1,b2;
	int info;
	JTextField fieldBrand = new JTextField(20);
	JTextField fieldModel = new JTextField(20);
	JTextField fieldYear = new JTextField(20);
	JTextField fieldVehicleId = new JTextField(20);
	JTextField fieldValue = new JTextField(20);
	JTextField fieldFirstName = new JTextField(20);
	JTextField fieldLastName = new JTextField(20);
	JTextField fieldCin = new JTextField(20);
	JTextField fieldCustomerId = new JTextField(20);
	JTextField fieldStartDate = new JTextField(20);
	JTextField fieldReturnDate = new JTextField(20);
	JTextField fieldRentalId = new JTextField(20);
	JTextField fieldReturnMileage = new JTextField(20);
	JTextField[] field = {fieldBrand,fieldModel,fieldYear,fieldVehicleId,fieldValue,fieldLastName,
			fieldFirstName,fieldCin,fieldCustomerId,fieldStartDate,fieldReturnDate,
			fieldRentalId,fieldReturnMileage};
	JLabel brand = new JLabel("Brand");
	JLabel model = new JLabel("Model");
	JLabel year = new JLabel("Year");
	JLabel vehicleId = new JLabel("Id of the vehicle");
	JLabel lastName = new JLabel("Last Name");
	JLabel firstName = new JLabel("First Name");
	JLabel cin = new JLabel("CIN");
	JLabel customerId = new JLabel("Id of the customer");
	JLabel startDate = new JLabel("Rental date (YYYY-MM-DD)");
	JLabel returnDate = new JLabel("Return date (YYYY-MM-DD)");
	JLabel rentalId = new JLabel("Id de location");
	JLabel returnMileage = new JLabel("Return mileage");
	JLabel returnState = new JLabel("  Return state  ");
	JLabel[] label = {brand,model,year,vehicleId,firstName,lastName,cin,customerId,startDate,returnDate,rentalId,returnMileage,returnState};
	JLabel successMessage,modifyMessage;
	String[] criteriaOptions= {"id","brand","model","year"};
	JComboBox<String> criteriaBox;
	String[] stateOptions= {"     Intact     ", "     Slightly damaged     ", "     Moderately damaged     ", 
			"     Severely damaged     ", "     Not returned     ", "     Lost     ", "     Stolen     ",
			"     Mechanical defect     "};
	JComboBox<String> stateBox;
	public Interface() {
		this.setTitle("Vehicle Rental Management");
		this.setSize(500,700);
		this.setLocationRelativeTo(null);
		try {
			M = new Management();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		title = new JLabel();
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setForeground(new Color(0x6d3792));
		title.setFont(new Font("MV BOli",Font.BOLD,30));
		mainPanel = new JPanel(new BorderLayout());
		tPanel = new JPanel(new BorderLayout());
		bPanel = new JPanel(new GridLayout(1,2, 20, 20));
		b1 = new JButton("Send");b1.addActionListener(this);
		b1.setFont(new Font("MV BOli",Font.BOLD,20));
		b1.setBackground(new Color(0x2283a7));
		b1.setForeground(Color.white);
		b2 = new JButton("Cancel");b2.addActionListener(this);
		b2.setFont(new Font("MV BOli",Font.BOLD,20));
		b2.setBackground(new Color(0x2283a7));
		b2.setForeground(Color.white);
		bPanel.add(b1);
		bPanel.add(b2);
		//return button
		returnButton = new JButton("<");returnButton.addActionListener(this);
		returnButton.setFont(new Font(null, Font.BOLD,30));
		returnButton.setBackground(new Color(0x6d3792));
		returnButton.setForeground(Color.white);
		menuPanel();
		for(int i=0;i<field.length;i++) {
			field[i].setFont(new Font("MV BOli", Font.BOLD, 25));
			field[i].setHorizontalAlignment(JTextField.CENTER);
		}
		
		for(int i=0;i<label.length;i++) {
			label[i].setFont(new Font("MV BOli", Font.BOLD, 25));
			label[i].setHorizontalAlignment(JTextField.CENTER);
			label[i].setForeground(new Color(0x2283a7));
		}
		successMessage = new JLabel();
		successMessage.setFont(new Font("MV BOli", Font.BOLD, 25));
		modifyMessage = new JLabel();
		modifyMessage.setFont(new Font("MV BOli", Font.BOLD, 20));
		criteriaBox = new JComboBox<String>(criteriaOptions);
		stateBox = new JComboBox<String>(stateOptions);
		resultPanel = new JPanel();
		this.add(mainPanel);
		
		this.setVisible(true);
	}
	//menuPanel
	public void menuPanel() {
		this.setSize(500,700);
		this.setLocationRelativeTo(null);
		title.setText("Menu");
		tPanel.removeAll();
		mainPanel.removeAll();
		tPanel.add(title,BorderLayout.CENTER);
		buttons = new JButton[11];
		for(int i=0;i<11;i++) {
			buttons[i] = new JButton();
			buttons[i].addActionListener(this);
			buttons[i].setFont(new Font("MV BOli",Font.BOLD,20));
			buttons[i].setForeground(Color.white);
			buttons[i].setBackground(new Color(0x6d3792));
		}
		buttons[0].setText("Add a vehicle");
		buttons[1].setText("Delete a vehicle");
		buttons[2].setText("Modify a vehicle");
		buttons[3].setText("Search for vehicles");
		buttons[4].setText("Add a customer");
		buttons[5].setText("Delete a customer");
		buttons[6].setText("Modify a customer");
		buttons[7].setText("Search for customers");
		buttons[8].setText("Record a rental");
		buttons[9].setText("Close a rental");
		buttons[10].setText("View rentals");
		menuPanel = new JPanel(new GridLayout(11,1));
		for(int i=0;i<11;i++) {
			menuPanel.add(buttons[i]);
		}
		mainPanel.add(tPanel,BorderLayout.NORTH);
		mainPanel.add(menuPanel,BorderLayout.CENTER);
		revalidate();
	}
			
	//The panel for adding a vehicle: avPanel
	public void addVehiclePanel() {
		info=1;
		title.setText("Adding vehicles");
		tPanel.removeAll();
		mainPanel.removeAll();
		tPanel.add(title,BorderLayout.CENTER);
		tPanel.add(returnButton,BorderLayout.WEST);
		mainPanel.add(tPanel,BorderLayout.NORTH);
		avPanel = new JPanel();
		avPanel.add(brand);avPanel.add(fieldBrand);
		avPanel.add(model);avPanel.add(fieldModel);
		avPanel.add(year);avPanel.add(fieldYear);
		avPanel.add(b1);
		avPanel.add(b2);
		mainPanel.add(avPanel,BorderLayout.CENTER);
		revalidate();
	}
	
	public void removeVehiclePanel() {
		info=2;
		title.setText("Remove of vehicles");
		tPanel.removeAll();
		mainPanel.removeAll();
		tPanel.add(title,BorderLayout.CENTER);
		tPanel.add(returnButton,BorderLayout.WEST);
		mainPanel.add(tPanel,BorderLayout.NORTH);
		dvPanel = new JPanel();
		dvPanel.add(vehicleId);
		dvPanel.add(fieldVehicleId);
		dvPanel.add(b1);
		dvPanel.add(b2);
		mainPanel.add(dvPanel,BorderLayout.CENTER);
		revalidate();
	}
	
	public void modifyVehiclePanel() {
		info = 3;
		title.setText("Vehicle modification");
		tPanel.removeAll();
		mainPanel.removeAll();
		tPanel.add(title,BorderLayout.CENTER);
		tPanel.add(returnButton,BorderLayout.WEST);
		mainPanel.add(tPanel,BorderLayout.NORTH);
		mvPanel = new JPanel();
		modifyMessage.setText("    Enter the new vehicle information:    ");
		mvPanel.add(vehicleId);mvPanel.add(fieldVehicleId);
		mvPanel.add(modifyMessage);
		mvPanel.add(brand);mvPanel.add(fieldBrand);
		mvPanel.add(model);mvPanel.add(fieldModel);
		mvPanel.add(year);mvPanel.add(fieldYear);
		mvPanel.add(b1);mvPanel.add(b2);
		mainPanel.add(mvPanel,BorderLayout.CENTER);
		revalidate();
	}
	
	public void searchVehiclePanel() {
		this.setSize(500,700);
		this.setLocationRelativeTo(null);
		info = 4;
		title.setText("Search for vehicles");
		tPanel.removeAll();
		mainPanel.removeAll();
		tPanel.add(returnButton,BorderLayout.WEST);
		tPanel.add(title,BorderLayout.CENTER);
		JLabel criterion = new JLabel("        Choose a criterion         ");
		criterion.setFont(new Font("MV BOli", Font.BOLD, 25));
		criterion.setHorizontalAlignment(JLabel.CENTER);
		criterion.setForeground(new Color(0x2283a7));
		criteriaBox.setFont(new Font("MV BOli", Font.BOLD, 25));
		criteriaBox.setMaximumRowCount(1);
		
		JLabel value = new JLabel("  Enter the value of this criterion  ");
		value.setFont(new Font("MV BOli", Font.BOLD, 25));
		value.setHorizontalAlignment(JLabel.CENTER);
		value.setForeground(new Color(0x2283a7));
		rvPanel = new JPanel();
		rvPanel.add(criterion);
		rvPanel.add(criteriaBox);
		rvPanel.add(value);
		rvPanel.add(fieldValue);
		rvPanel.add(b1);
		rvPanel.add(b2);
		mainPanel.add(tPanel,BorderLayout.NORTH);
		mainPanel.add(rvPanel,BorderLayout.CENTER);
		revalidate();
	}
	
	public void addCustomerPanel() {
		info=5;
		title.setText("Adding customers");
		tPanel.removeAll();
		mainPanel.removeAll();
		tPanel.add(title,BorderLayout.CENTER);
		tPanel.add(returnButton,BorderLayout.WEST);
		mainPanel.add(tPanel,BorderLayout.NORTH);
		acPanel = new JPanel();
		acPanel.add(lastName);acPanel.add(fieldLastName);
		acPanel.add(firstName);acPanel.add(fieldFirstName);
		acPanel.add(cin);acPanel.add(fieldCin);
		acPanel.add(b1);acPanel.add(b2);
		mainPanel.add(acPanel,BorderLayout.CENTER);
		revalidate();
	}
	
	public void removeCustomerPanel() {
		info=6;
		title.setText("remove of customers");
		tPanel.removeAll();
		mainPanel.removeAll();
		tPanel.add(title,BorderLayout.CENTER);
		tPanel.add(returnButton,BorderLayout.WEST);
		mainPanel.add(tPanel,BorderLayout.NORTH);
		dcPanel = new JPanel();
		dcPanel.add(customerId);
		dcPanel.add(fieldCustomerId);
		dcPanel.add(b1);
		dcPanel.add(b2);
		mainPanel.add(dcPanel,BorderLayout.CENTER);
		revalidate();
	}
	
	public void modifyCustomerPanel() {
		info = 7;
		title.setText("customer's modification");
		tPanel.removeAll();
		mainPanel.removeAll();
		tPanel.add(title,BorderLayout.CENTER);
		tPanel.add(returnButton,BorderLayout.WEST);
		mainPanel.add(tPanel,BorderLayout.NORTH);
		mcPanel = new JPanel();
		modifyMessage.setText("Enter the new customer information:");
		mcPanel.add(customerId);mcPanel.add(fieldCustomerId);
		mcPanel.add(modifyMessage);
		mcPanel.add(lastName);mcPanel.add(fieldLastName);
		mcPanel.add(firstName);mcPanel.add(fieldFirstName);
		mcPanel.add(cin);mcPanel.add(fieldCin);
		mcPanel.add(b1);mcPanel.add(b2);
		mainPanel.add(mcPanel,BorderLayout.CENTER);
		revalidate();
	}
	
	public void searchCustomerPanel() {
		info = 8;
		title.setText("Search for customers");
		tPanel.removeAll();
		mainPanel.removeAll();
		tPanel.add(title,BorderLayout.CENTER);
		tPanel.add(returnButton,BorderLayout.WEST);
		mainPanel.add(tPanel,BorderLayout.NORTH);
		try {
			resultPanel.removeAll();
			ResultSet res = M.searchCustomers();
			if(res.next()) {
				int numLines;
				res.last();
				numLines = res.getRow();
				this.setSize(700,(numLines+2)*50);
				this.setLocationRelativeTo(null);
				res.first();
				resultPanel.setLayout(new GridLayout(numLines+1,4));
				JLabel[][] lineLabel = new JLabel[numLines+1][4];
				for(int i=0;i<numLines+1;i++) {
					for(int j=0;j<4;j++) {
						lineLabel[i][j] = new JLabel();
						lineLabel[i][j].setFont(new Font("MV BOli", Font.BOLD, 20));
						lineLabel[i][j].setHorizontalAlignment(JLabel.CENTER);
						lineLabel[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
						resultPanel.add(lineLabel[i][j]);
					}
				}
				int indexline=0;
				lineLabel[indexline][0].setForeground(new Color(0x6d3792));lineLabel[indexline][0].setText("Id");
				lineLabel[indexline][1].setForeground(new Color(0x6d3792));lineLabel[indexline][1].setText("lastName");
				lineLabel[indexline][2].setForeground(new Color(0x6d3792));lineLabel[indexline][2].setText("firstName");
				lineLabel[indexline][3].setForeground(new Color(0x6d3792));lineLabel[indexline][3].setText("CIN");
				indexline++;
				do {
					String id = res.getString("id");
					String lastName = res.getString("lastName");
					String firstName = res.getString("firstName");
					String cin = res.getString("cin");
					lineLabel[indexline][0].setText(id);
					lineLabel[indexline][1].setText(lastName);
					lineLabel[indexline][2].setText(firstName);
					lineLabel[indexline][3].setText(cin);
					indexline++;
				}while(res.next());
			}
			else {
				successMessage.setText("There are no customers!");
				resultPanel.add(successMessage);
			}
			mainPanel.add(resultPanel);
			revalidate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
	}
	public void recordRentalPanel() {
		info=9;
		title.setText("Adding rentals");
		tPanel.removeAll();
		mainPanel.removeAll();
		tPanel.add(title,BorderLayout.CENTER);
		tPanel.add(returnButton,BorderLayout.WEST);
		mainPanel.add(tPanel,BorderLayout.NORTH);
		elPanel = new JPanel();
		elPanel.add(customerId);elPanel.add(fieldCustomerId);
		elPanel.add(vehicleId);elPanel.add(fieldVehicleId);
		elPanel.add(startDate);elPanel.add(fieldStartDate);
		elPanel.add(returnDate);elPanel.add(fieldReturnDate);
		elPanel.add(b1);elPanel.add(b2);
		mainPanel.add(elPanel,BorderLayout.CENTER);
		revalidate();
	} 
	
	public void closeRentalPanel() {
		info = 10;
		title.setText("Closing of rentals");
		tPanel.removeAll();
		mainPanel.removeAll();
		tPanel.add(title,BorderLayout.CENTER);
		tPanel.add(returnButton,BorderLayout.WEST);
		mainPanel.add(tPanel,BorderLayout.NORTH);
		stateBox.setFont(new Font("MV BOli", Font.BOLD, 25));
		stateBox.setMaximumRowCount(1);
		clPanel = new JPanel();
		modifyMessage.setText("Enter the following information :");
		clPanel.add(rentalId);clPanel.add(fieldRentalId);
		clPanel.add(returnMileage);clPanel.add(fieldReturnMileage);
		clPanel.add(returnState);clPanel.add(stateBox);
		clPanel.add(b1);clPanel.add(b2);
		mainPanel.add(clPanel,BorderLayout.CENTER);
		revalidate();
	}
	
	public void viewRentalPanel() {
		info = 11;
		title.setText("Rentals");
		tPanel.removeAll();
		mainPanel.removeAll();
		tPanel.add(title,BorderLayout.CENTER);
		tPanel.add(returnButton,BorderLayout.WEST);
		mainPanel.add(tPanel,BorderLayout.NORTH);
		try {
			resultPanel.removeAll();
			ResultSet res = M.viewRental();
			if(res.next()) {
				int numLines;
				res.last();
				numLines = res.getRow();
				this.setSize(1400,(numLines+2)*50);
				this.setLocationRelativeTo(null);
				res.first();
				resultPanel.setLayout(new GridLayout(numLines+1,7));
				JLabel[][] lineLabel = new JLabel[numLines+1][7];
				for(int i=0;i<numLines+1;i++) {
					for(int j=0;j<7;j++) {
						lineLabel[i][j] = new JLabel();
						lineLabel[i][j].setFont(new Font("MV BOli", Font.BOLD, 20));
						lineLabel[i][j].setHorizontalAlignment(JLabel.CENTER);
						lineLabel[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
						resultPanel.add(lineLabel[i][j]);
					}
				}
				int indexline=0;
				lineLabel[indexline][0].setForeground(new Color(0x6d3792));lineLabel[indexline][0].setText("Id");
				lineLabel[indexline][1].setForeground(new Color(0x6d3792));lineLabel[indexline][1].setText("Customer id");
				lineLabel[indexline][2].setForeground(new Color(0x6d3792));lineLabel[indexline][2].setText("Vehicle id");
				lineLabel[indexline][3].setForeground(new Color(0x6d3792));lineLabel[indexline][3].setText("Rental date");
				lineLabel[indexline][4].setForeground(new Color(0x6d3792));lineLabel[indexline][4].setText("Return date");
				lineLabel[indexline][5].setForeground(new Color(0x6d3792));lineLabel[indexline][5].setText("Return mileage");
				lineLabel[indexline][6].setForeground(new Color(0x6d3792));lineLabel[indexline][6].setText("State");
				indexline++;
				do {
					String id = res.getString("id");
					String customerId = res.getString("customerId");
					String vehicleId = res.getString("vehicleId");
					String startDate = res.getString("startDate");
					String returnDate = res.getString("returnDate");
					String returnMileage = res.getString("returnMileage")== null? "not close":res.getString("returnMileage");
					String stateRetour = res.getString("returnState") == null?"not close":res.getString("returnState") ;
					lineLabel[indexline][0].setText(id);
					lineLabel[indexline][1].setText(customerId);
					lineLabel[indexline][2].setText(vehicleId);
					lineLabel[indexline][3].setText(startDate);
					lineLabel[indexline][4].setText(returnDate);
					lineLabel[indexline][5].setText(returnMileage);
					lineLabel[indexline][6].setText(stateRetour);
					indexline++;
				}while(res.next());
			}
			else {
				successMessage.setText("There is no rental!");
				resultPanel.add(successMessage);
			}
			mainPanel.add(resultPanel,BorderLayout.CENTER);
			revalidate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
	}
	
	public static void main(String[] args) {
	
		new Interface();
	}
	@SuppressWarnings({ "deprecation" })
	@Override
	public void actionPerformed(ActionEvent e) {
		String stringId = fieldVehicleId.getText();
		String Brand = fieldBrand.getText();
		String Model = fieldModel.getText();
		String stringYear = fieldYear.getText();
		String lastName = fieldLastName.getText();
		String firstName = fieldFirstName.getText();
		String Cin = fieldCin.getText();
		String customerIdString = fieldCustomerId.getText();
		String startDateString = fieldStartDate.getText();
		String returnDateString = fieldReturnDate.getText();
		String rentalIdString = fieldRentalId.getText();
		String mileageString = fieldReturnMileage.getText();
		if(e.getSource()==buttons[0])
			addVehiclePanel();
		else if(e.getSource()==buttons[1])
			removeVehiclePanel();
		else if(e.getSource()==buttons[2])
			modifyVehiclePanel();
		else if(e.getSource() == buttons[3])
			searchVehiclePanel();
		else if(e.getSource() == buttons[4])
			addCustomerPanel();
		else if(e.getSource() == buttons[5])
			removeCustomerPanel();
		else if(e.getSource() == buttons[6])
			modifyCustomerPanel();
		else if(e.getSource() == buttons[7])
			searchCustomerPanel();
		else if(e.getSource() == buttons[8])
			recordRentalPanel();
		else if(e.getSource() == buttons[9])
			closeRentalPanel();
		else if(e.getSource() == buttons[10])
			viewRentalPanel();
		else if(e.getSource()==returnButton) {
			if(info == 12)
				searchVehiclePanel();
			else
				menuPanel();
		}
		else if(e.getSource()==b2) {
			switch (info) {
			case 1: {
				fieldBrand.setText("");
				fieldModel.setText("");
				fieldYear.setText("");
				break;
			}
			case 2:{
				fieldVehicleId.setText("");
				break;
			}
			case 3:{
				fieldBrand.setText("");
				fieldModel.setText("");
				fieldYear.setText("");
				fieldVehicleId.setText("");
				break;
			}
			case 4:{
				fieldValue.setText("");
				break;
			}
			case 5: {
				fieldLastName.setText("");
				fieldFirstName.setText("");
				fieldCin.setText("");
				break;
			}
			case 6:{
				fieldCustomerId.setText("");
				break;
			}
			case 7:{
				fieldLastName.setText("");
				fieldFirstName.setText("");
				fieldCin.setText("");
				fieldCustomerId.setText("");
				break;
			}
			case 9:{
				fieldCustomerId.setText("");
				fieldVehicleId.setText("");
				fieldStartDate.setText("");
				fieldReturnDate.setText("");
				break;
			}
			case 10:{
				fieldRentalId.setText("");
				fieldReturnMileage.setText("");
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + info);
			}
			
		}
		if(e.getSource()==b1) {
			switch (info) {
			case 1: {
				if(Brand.trim().isEmpty() || Model.trim().isEmpty() || stringYear.trim().isEmpty())
					JOptionPane.showMessageDialog(null, "Please fill in the vehicle information carefully!","Error",JOptionPane.ERROR_MESSAGE);
				else {
					try {
						int Year = Integer.parseInt(stringYear);
						String message=M.addVehicle(new Vehicle(1, Brand, Model, Year));
						successMessage.setText(message);
						avPanel.add(successMessage);
						revalidate();
						fieldBrand.setText("");
						fieldModel.setText("");
						fieldYear.setText("");
						timer.setRepeats(false);
						timer.start();
					}catch(NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "the year must be an integer!","Error",JOptionPane.ERROR_MESSAGE);
					}catch(SQLException ex) {
						ex.printStackTrace();
					}
				}
				break;
			}
			case 2 :{
				if(stringId.trim().isEmpty())
					JOptionPane.showMessageDialog(null, "Please fill in 'id' of vehicle to delete!","Error",JOptionPane.ERROR_MESSAGE);
				else {
					try {
						int id = Integer.parseInt(stringId);
						String message=M.deleteVehicle(id);
						successMessage.setText(message);
						dvPanel.add(successMessage);
						revalidate();
						fieldVehicleId.setText("");
						timer.setRepeats(false);
						timer.start();
					}catch(NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "'id' must be an integer!","Error",JOptionPane.ERROR_MESSAGE);
					}catch(SQLException ex) {
						ex.printStackTrace();
					}
				}
				break;
			}
			case 3 :{
				if(stringId.trim().isEmpty() || Brand.trim().isEmpty() || Model.trim().isEmpty() || stringYear.trim().isEmpty())
					JOptionPane.showMessageDialog(null, "Please fill in the vehicle information carefully!","Error",JOptionPane.ERROR_MESSAGE);
				else {
					try {
						int id = Integer.parseInt(stringId);
						int Year = Integer.parseInt(stringYear);
						String message=M.modifyVehicle(id, new Vehicle(id, Brand, Model, Year));
						successMessage.setText(message);
						mvPanel.add(successMessage);
						revalidate();
						fieldVehicleId.setText("");
						fieldBrand.setText("");
						fieldModel.setText("");
						fieldYear.setText("");
						timer.setRepeats(false);
						timer.start();
					}catch(NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "'id' and the year must be integers!","Error",JOptionPane.ERROR_MESSAGE);
					}catch(SQLException ex) {
						ex.printStackTrace();
					}
				}
				break;
			}
			case 4:{
				String critère = criteriaBox.getItemAt(criteriaBox.getSelectedIndex());
				String valeur = fieldValue.getText();
				if(valeur.trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please fill in the vehicle information carefully!","Error",JOptionPane.ERROR_MESSAGE);
				}
				else {
					int numLines = 0;
					try {
						resultPanel.removeAll();
						mainPanel.removeAll();
						ResultSet res ;
						if(critère.equals("id") || critère.equals("year")) {
							int intValeur = Integer.parseInt(valeur);
							res = M.searchVehicleInt(critère, intValeur);
						}
						else {
							res = M.searchVehiclesString(critère, valeur);
						}
						if(res.next()) {
							res.last();
							numLines = res.getRow();
							res.first();
							resultPanel.setLayout(new GridLayout(numLines+1,4));
							JLabel[][] lineLabel = new JLabel[numLines+1][4];
							for(int i=0;i<numLines+1;i++) {
								for(int j=0;j<4;j++) {
									lineLabel[i][j] = new JLabel();
									lineLabel[i][j].setFont(new Font("MV BOli", Font.BOLD, 20));
									lineLabel[i][j].setHorizontalAlignment(JLabel.CENTER);
									lineLabel[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
									resultPanel.add(lineLabel[i][j]);
								}
							}
							int indexline=0;
							lineLabel[indexline][0].setForeground(new Color(0x6d3792));lineLabel[indexline][0].setText("Id");
							lineLabel[indexline][1].setForeground(new Color(0x6d3792));lineLabel[indexline][1].setText("Brand");
							lineLabel[indexline][2].setForeground(new Color(0x6d3792));lineLabel[indexline][2].setText("Model");
							lineLabel[indexline][3].setForeground(new Color(0x6d3792));lineLabel[indexline][3].setText("Year");
							indexline++;
							do {
								String id = res.getString("id");
								String brand = res.getString("brand");
								String model = res.getString("model");
								String year = res.getString("year");
								lineLabel[indexline][0].setText(id);
								lineLabel[indexline][1].setText(brand);
								lineLabel[indexline][2].setText(model);
								lineLabel[indexline][3].setText(year);
								indexline++;
							}while(res.next());
							
						}
						else {
							successMessage.setText("Not found!");
							resultPanel.add(successMessage);
							numLines = 1;
						}
						info = 12;
						title.setText("Vehicles having "+critère+" = "+valeur);
						tPanel.removeAll();
						tPanel.add(returnButton);
						tPanel.add(title);
						mainPanel.add(tPanel,BorderLayout.NORTH);
						mainPanel.add(resultPanel,BorderLayout.CENTER);
						this.resize(title.getText().length()*20, (numLines+2)*60);;
						this.setLocationRelativeTo(null);
						revalidate();
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "'id' and the year must be integers!","Error",JOptionPane.ERROR_MESSAGE);
					}catch(SQLException ex) {
						ex.printStackTrace();
					}
					
				}
				break;
			}
			case 5: {
				if(lastName.trim().isEmpty() || firstName.trim().isEmpty() || Cin.trim().isEmpty())
					JOptionPane.showMessageDialog(null, "Please fill in the vehicle information carefully!","Error",JOptionPane.ERROR_MESSAGE);
				else {
					try {
						String message=M.addCustomer(new Customer(1, lastName, firstName, Cin));
						successMessage.setText(message);
						acPanel.add(successMessage);
						revalidate();
						fieldLastName.setText("");
						fieldFirstName.setText("");
						fieldCin.setText("");
						timer.setRepeats(false);
						timer.start();
					}catch(SQLException ex) {
						ex.printStackTrace();
					}
				}
				break;
			}
			case 6 :{
				if(customerIdString.trim().isEmpty())
					JOptionPane.showMessageDialog(null, "Please fill in customer 'id' to delete!","Error",JOptionPane.ERROR_MESSAGE);
				else {
					try {
						int id = Integer.parseInt(customerIdString);
						String message=M.deleteCustomer(id);
						successMessage.setText(message);
						dcPanel.add(successMessage);
						revalidate();
						fieldCustomerId.setText("");
						timer.setRepeats(false);
						timer.start();
					}catch(SQLException ex) {
						ex.printStackTrace();
					}
				}
				break;
			}
			case 7 :{
				if(customerIdString.trim().isEmpty() || lastName.trim().isEmpty() || firstName.trim().isEmpty() || Cin.trim().isEmpty())
					JOptionPane.showMessageDialog(null, "Please fill in the customer information carefully!","Error",JOptionPane.ERROR_MESSAGE);
				else {
					try {
						int id = Integer.parseInt(customerIdString);
						String message=M.modifyCustomer(id, new Customer(id, lastName, firstName, Cin));
						successMessage.setText(message);
						mcPanel.add(successMessage);
						revalidate();
						fieldCustomerId.setText("");
						fieldLastName.setText("");
						fieldFirstName.setText("");
						fieldCin.setText("");
						timer.setRepeats(false);
						timer.start();
					}catch(NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "'id' must be an integer!","Error",JOptionPane.ERROR_MESSAGE);
					}catch(SQLException ex) {
						ex.printStackTrace();
					}
				}
				break;
			}
			
			case 9: {
				if(customerIdString.trim().isEmpty() || stringId.trim().isEmpty() || startDateString.trim().isEmpty() || returnDateString.trim().isEmpty())
					JOptionPane.showMessageDialog(null, "Please fill out the rental information carefully!","Error",JOptionPane.ERROR_MESSAGE);
				else {
					try {
						int customerId = Integer.parseInt(customerIdString);
						int vehicleId = Integer.parseInt(stringId);
						LocalDate startDate= LocalDate.parse(startDateString);
						LocalDate returnDate= LocalDate.parse(returnDateString);
						String message=M.registerRental(new Rental(1, customerId, vehicleId, startDate, returnDate,0,null));
						successMessage.setText(message);
						elPanel.add(successMessage);
						revalidate();
						fieldCustomerId.setText("");
						fieldVehicleId.setText("");
						fieldStartDate.setText("");
						fieldReturnDate.setText("");
						timer.setRepeats(false);
						timer.start();
					}catch(NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "Please respect the following instructions:\n"
								+ "Identifiers must be integers\nDates must be in the form YYYY-MM-DD","Error",JOptionPane.ERROR_MESSAGE);
					}catch(SQLException ex) {
						ex.printStackTrace();
					}
				}
				break;
			}
			case 10:{
				String state = stateBox.getItemAt(stateBox.getSelectedIndex());
				if(rentalIdString.trim().isEmpty() || mileageString.trim().isEmpty())
					JOptionPane.showMessageDialog(null, "Please fill out the rental closing information carefully!","Error",JOptionPane.ERROR_MESSAGE);
				else {
					try {
						String message = M.closeRental(Integer.parseInt(rentalIdString), Integer.parseInt(mileageString), state.trim());
						successMessage.setText(message);
						clPanel.add(successMessage);
						revalidate();
						fieldRentalId.setText("");
						fieldReturnMileage.setText("");
						timer.setRepeats(false);
						timer.start();
						
					}catch(NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "'id' and mileage must be integers!","Error",JOptionPane.ERROR_MESSAGE);
					}catch(SQLException ex) {
						ex.printStackTrace();
					}
				}
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + info);
			}
				
		}
		
	}
	Timer timer = new Timer(2000, event -> {
	    // The code to execute after two second
	    successMessage.setText("");
	});
	

}
