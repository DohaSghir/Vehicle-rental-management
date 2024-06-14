package VehicleManagement;

public class Customer {

    int id;
    String firstName;
    String lastName;
    String cin;

    public Customer(int id, String firstname, String lastName, String cin) {
        super();
        this.id = id;
        this.firstName = firstname;
        this.lastName = lastName;
        this.cin = cin;
    }

    @Override
    public String toString() {
        return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", cin=" + cin + "]";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

}
