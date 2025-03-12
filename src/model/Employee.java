package model;


/**
 * Represents an employee with personal and contact details. This class stores
 * information such as employee ID, name, title, contact details, and address information.
 * 
 * 
 * @author Christoffer Søndergaard
 * @version 12/03/2025 - 15:12
 */
public class Employee
{
	private int employeeId;
	private String firstName;
	private String lastName;
	private String title;
	private String phoneNumber;
	private String emailAddress;
	private String country;
	private String state;
	private String city;
	private int postalCode;
	private String streetName;
	private int houseNumber;
	private int floorNumber;
	private String doorNumber;

	
	/**
	 * Constructs a new Employee object with the specified details.
	 *
	 * @param employeeId   The unique ID of the employee.
	 * @param firstName    The first name of the employee.
	 * @param lastName     The last name of the employee.
	 * @param title        The job title of the employee.
	 * @param phoneNumber  The employee's phone number.
	 * @param emailAddress The employee's email address.
	 * @param country      The employee's country of residence.
	 * @param state        The employee's state or province.
	 * @param city         The employee's city.
	 * @param postalCode   The postal code of the employee's address.
	 * @param streetName   The name of the street where the employee resides.
	 * @param houseNumber  The house number of the employee's address.
	 * @param floorNumber  The floor number in case of an apartment.
	 * @param doorNumber   The specific door number or unit identifier.
	 */
	public Employee(int employeeId, String firstName, String lastName, String title, String phoneNumber,
			String emailAddress, String country, String state, String city, int postalCode, String streetName,
			int houseNumber, int floorNumber, String doorNumber)
	{
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.title = title;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
		this.country = country;
		this.state = state;
		this.city = city;
		this.postalCode = postalCode;
		this.streetName = streetName;
		this.houseNumber = houseNumber;
		this.floorNumber = floorNumber;
		this.doorNumber = doorNumber;
	}
	

	/**
	 * Gets the employee ID.
	 *
	 * @return The employee ID.
	 */
	public int getEmployeeId()
	{
		return employeeId;
	}
	

	/**
	 * Sets the employee ID.
	 *
	 * @param employeeId The employee ID to set.
	 */
	private void setEmployeeId(int employeeId)
	{
		this.employeeId = employeeId;
	}

	
	/**
	 * Gets the employee's first name.
	 *
	 * @return The first name.
	 */
	public String getFirstName()
	{
		return firstName;
	}

	
	/**
	 * Sets the employee's first name.
	 *
	 * @param firstName The first name to set.
	 */
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	
	/**
	 * Gets the employee's last name.
	 *
	 * @return The last name.
	 */
	public String getLastName()
	{
		return lastName;
	}

	
	/**
	 * Sets the employee's last name.
	 *
	 * @param lastName The last name to set.
	 */
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	
	/**
	 * Gets the employee's job title.
	 *
	 * @return The job title.
	 */
	public String getTitle()
	{
		return title;
	}

	
	/**
	 * Sets the employee's job title.
	 *
	 * @param title The job title to set.
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}

	
	/**
	 * Gets the employee's phone number.
	 *
	 * @return The phone number.
	 */
	public String getPhoneNumber()
	{
		return phoneNumber;
	}

	
	/**
	 * Sets the employee's phone number.
	 *
	 * @param phoneNumber The phone number to set.
	 */
	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}

	
	/**
	 * Gets the employee's email address.
	 *
	 * @return The email address.
	 */
	public String getEmailAddress()
	{
		return emailAddress;
	}

	
	/**
	 * Sets the employee's email address.
	 *
	 * @param emailAddress The email address to set.
	 */
	public void setEmailAddress(String emailAddress)
	{
		this.emailAddress = emailAddress;
	}

	
	/**
	 * Gets the employee's country of residence.
	 *
	 * @return The country.
	 */
	public String getCountry()
	{
		return country;
	}

	
	/**
	 * Sets the employee's country of residence.
	 *
	 * @param country The country to set.
	 */
	public void setCountry(String country)
	{
		this.country = country;
	}

	
	/**
	 * Gets the employee's state or province.
	 *
	 * @return The state.
	 */
	public String getState()
	{
		return state;
	}

	
	/**
	 * Sets the employee's state or province.
	 *
	 * @param state The state to set.
	 */
	public void setState(String state)
	{
		this.state = state;
	}

	
	/**
	 * Gets the employee's city.
	 *
	 * @return The city.
	 */
	public String getCity()
	{
		return city;
	}

	
	/**
	 * Sets the employee's city.
	 *
	 * @param city The city to set.
	 */
	public void setCity(String city)
	{
		this.city = city;
	}

	
	/**
	 * Gets the employee's postal code.
	 *
	 * @return The postal code.
	 */
	public int getPostalCode()
	{
		return postalCode;
	}

	
	/**
	 * Sets the employee's postal code.
	 *
	 * @param postalCode The postal code to set.
	 */
	public void setPostalCode(int postalCode)
	{
		this.postalCode = postalCode;
	}

	
	/**
	 * Gets the employee's street name.
	 *
	 * @return The street name.
	 */
	public String getStreetName()
	{
		return streetName;
	}

	
	/**
	 * Sets the employee's street name.
	 *
	 * @param streetName The street name to set.
	 */
	public void setStreetName(String streetName)
	{
		this.streetName = streetName;
	}

	
	/**
	 * Gets the employee's house number.
	 *
	 * @return The house number.
	 */
	public int getHouseNumber()
	{
		return houseNumber;
	}

	
	/**
	 * Sets the employee's house number.
	 *
	 * @param houseNumber The house number to set.
	 */
	public void setHouseNumber(int houseNumber)
	{
		this.houseNumber = houseNumber;
	}

	
	/**
	 * Gets the employee's floor number.
	 *
	 * @return The floor number.
	 */
	public int getFloorNumber()
	{
		return floorNumber;
	}

	
	/**
	 * Sets the employee's floor number.
	 *
	 * @param floorNumber The floor number to set.
	 */
	public void setFloorNumber(int floorNumber)
	{
		this.floorNumber = floorNumber;
	}

	
	/**
	 * Gets the employee's door number.
	 *
	 * @return The door number.
	 */
	public String getDoorNumber()
	{
		return doorNumber;
	}

	
	/**
	 * Sets the employee's door number.
	 *
	 * @param doorNumber The door number to set.
	 */
	public void setDoorNumber(String doorNumber)
	{
		this.doorNumber = doorNumber;
	}
	

    /**
     * Returns a string representation of the Employee object.
     *
     * @return A string containing all relevant details of the employee.
     */
    @Override
    public String toString()
    {
        return "Employee [" +
                "employeeId=" + employeeId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", title='" + title + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", country='" + country + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", postalCode=" + postalCode +
                ", streetName='" + streetName + '\'' +
                ", houseNumber=" + houseNumber +
                ", floorNumber=" + floorNumber +
                ", doorNumber='" + doorNumber + '\'' +
                ']';
    }
}