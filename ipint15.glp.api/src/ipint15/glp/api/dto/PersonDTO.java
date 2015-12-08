package ipint15.glp.api.dto;

import java.io.Serializable;

public class PersonDTO implements Serializable {
	
	private String firstName;
	private String lastName;

	
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String toString() {
		return firstName + " " + lastName + " ";
	}

}
