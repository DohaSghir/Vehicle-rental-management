package VehicleManagement;

import java.time.LocalDate;

public class Rental {
	
	int rentalId;
	int customerId;
	int vehicleId;
	LocalDate startDate;
	LocalDate returnDate;
	int returnMileage;
	String returnState;
	
	public Rental(int rentalId, int customerId, int vehicleId, LocalDate startDate, LocalDate returnDate,
			int returnMileage, String returnState) {
		super();
		this.rentalId=rentalId;
		this.customerId = customerId;
		this.vehicleId = vehicleId;
		this.startDate = startDate;
		this.returnDate = returnDate;
		this.returnMileage = returnMileage;
		this.returnState = returnState;
	}

	@Override
	public String toString() {
		return "Rental [rentalId=" + rentalId + ", customerId=" + customerId + ", vehicleId=" + vehicleId
				+ ", startDate=" + startDate + ", returnDate=" + returnDate + ", returnMileage=" + returnMileage
				+ ", returnState=" + returnState + "]";
	}

	public int getRentalId() {
		return rentalId;
	}

	public void setRentalId(int rentalId) {
		this.rentalId = rentalId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	public int getReturnMileage() {
		return returnMileage;
	}

	public void setReturnMileage(int returnMileage) {
		this.returnMileage = returnMileage;
	}

	public String getReturnState() {
		return returnState;
	}

	public void setReturnState(String returnState) {
		this.returnState = returnState;
	}	
}
