package model;


/**
 * Represents a customer with personal and purchase-related information. This
 * class stores details such as name, contact info, address, membership status,
 * and purchase-related attributes like purchase threshold.
 * 
 * 
 * @author Christoffer Søndergaard, Anders Have & Line Bertelsen
 * @version 13/03/2025 - 07:23
 */
public class Customer
{
	private int customerNumber;
	private String firstName;
	private String lastName;
	private String customerType;
	private String phoneNumber;
	private String emailAddress;
	private boolean clubMember;
	private double purchaseThreshhold;
	private String country;
	private String state;
	private String city;
	private int postalCode;
	private String streetName;
	private int houseNumber;
	private int floorNumber;
	private String doorNumber;

	
	/**
	 * Constructs a new Customer object with all necessary details.
	 *
	 * @param customerNumber     The customer's unique id.
	 * @param firstName          The customer's first name.
	 * @param lastName           The customer's last name.
	 * @param customerType       The type of customer (e.g., regular, VIP).
	 * @param phoneNumber        The customer's phone number.
	 * @param emailAddress       The customer's email address.
	 * @param clubMember         Whether the customer is a club member.
	 * @param country            The customer's country.
	 * @param state              The customer's state or province.
	 * @param city               The customer's city.
	 * @param postalCode         The customer's postal code.
	 * @param streetName         The name of the street where the customer resides.
	 * @param houseNumber        The house number of the customer's address.
	 * @param floorNumber        The floor number in case of an apartment.
	 * @param doorNumber         The specific door number or unit identifier.
	 */
	public Customer(int customerNumber, String firstName, String lastName, String customerType, String phoneNumber, String emailAddress,
			boolean clubMember, String country, String state, String city, int postalCode,
			String streetName, int houseNumber,	int floorNumber, String doorNumber)
	{
		this.customerNumber = customerNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.customerType = customerType;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
		this.clubMember = clubMember;
		this.country = country;
		this.state = state;
		this.city = city;
		this.postalCode = postalCode;
		this.streetName = streetName;
		this.houseNumber = houseNumber;
		this.floorNumber = floorNumber;
		this.doorNumber = doorNumber;
		
		
		
		this.purchaseThreshhold = getPurchaseThreshhold();
	}

	
	/**
	 * Gets the customer's id number.
	 *
	 * @return The customer's uniquely identifiable customer id / customer number.
	 */
	public int getCustomerNumber()
	{
		return customerNumber;
	}

	
	/**
	 * Sets the customer's postal code.
	 *
	 * @param customerNumber The customer's uniquely identifiable customer id / customer number.
	 */
	public void setCustomerNumber(int customerNumber)
	{
		this.customerNumber = customerNumber;
	}
	
	
	/**
	 * Gets the customer's first name.
	 *
	 * @return The first name.
	 */
	public String getFirstName()
	{
		return firstName;
	}

	
	/**
	 * Sets the customer's first name.
	 *
	 * @param firstName The first name to set.
	 */
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	
	/**
	 * Gets the customer's last name.
	 *
	 * @return The last name.
	 */
	public String getLastName()
	{
		return lastName;
	}

	
	/**
	 * Gets the type of customer.
	 *
	 * @return The customer type.
	 */
	public String getCustomerType()
	{
		return customerType;
	}

	
	/**
	 * Sets the type of customer.
	 *
	 * @param customerType The customer type to set.
	 */
	public void setCustomerType(String customerType)
	{
		this.customerType = customerType;
	}

	
	/**
	 * Gets the customer's phone number.
	 *
	 * @return The phone number.
	 */
	public String getPhoneNumber()
	{
		return phoneNumber;
	}
	

	/**
	 * Sets the customer's phone number.
	 *
	 * @param phoneNumber The phone number to set.
	 */
	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}

	
	/**
	 * Gets the customer's email address.
	 *
	 * @return The email address.
	 */
	public String getEmailAddress()
	{
		return emailAddress;
	}

	
	/**
	 * Sets the customer's email address.
	 *
	 * @param emailAddress The email address to set.
	 */
	public void setEmailAddress(String emailAddress)
	{
		this.emailAddress = emailAddress;
	}

	
	/**
	 * Checks if the customer is a club member.
	 *
	 * @return True if the customer is a club member, otherwise false.
	 */
	public boolean isClubMember()
	{
		return clubMember;
	}

	
	/**
	 * Sets the customer's club membership status.
	 *
	 * @param clubMember True if the customer is a club member, otherwise false.
	 */
	public void setClubMember(boolean clubMember)
	{
		this.clubMember = clubMember;
	}

	
	/**
	 * Gets the purchase threshold required for a customer to purchase
	 * goods for, in order to receive order perks.
	 *
	 * @return The purchase threshold, 1500 for club members and 2500 for private customers.
	 */
	public double getPurchaseThreshhold()
	{
		// If the customer instance is a club member then execute this section
		if(isClubMember() == true)
		{
			// Sets the purchase threshold to 1500
			setPurchaseThreshhold(1500.00);
		}
		
		// If the customer instance is not a club member then execute this section
		else
		{
			// Sets the purchase threshold to 2500
			setPurchaseThreshhold(2500.00);
		}
		
		return this.purchaseThreshhold;
	}

	
	/**
	 * Sets the purchase threshold.
	 *
	 * @param purchaseThreshhold The purchase threshold to set.
	 */
	public void setPurchaseThreshhold(double purchaseThreshhold)
	{
		this.purchaseThreshhold = purchaseThreshhold;
	}

	
	/**
	 * Gets the customer's country.
	 *
	 * @return The country.
	 */
	public String getCountry()
	{
		return country;
	}
	

	/**
	 * Sets the customer's country.
	 *
	 * @param country The country to set.
	 */
	public void setCountry(String country)
	{
		this.country = country;
	}

	
	/**
	 * Gets the customer's state.
	 *
	 * @return The state.
	 */
	public String getState()
	{
		return state;
	}

	
	/**
	 * Sets the customer's state.
	 *
	 * @param state The state to set.
	 */
	public void setState(String state)
	{
		this.state = state;
	}

	
	/**
	 * Gets the customer's city.
	 *
	 * @return The city.
	 */
	public String getCity()
	{
		return city;
	}

	
	/**
	 * Sets the customer's city.
	 *
	 * @param city The city to set.
	 */
	public void setCity(String city)
	{
		this.city = city;
	}

	
	/**
	 * Gets the customer's postal code.
	 *
	 * @return The postal code.
	 */
	public int getPostalCode()
	{
		return postalCode;
	}

	
	/**
	 * Sets the customer's postal code.
	 *
	 * @param postalCode The postal code to set.
	 */
	public void setPostalCode(int postalCode)
	{
		this.postalCode = postalCode;
	}

	
	/**
	 * Gets the street name of the customer.
	 *
	 * @return The street name.
	 */
	public String getStreetName()
	{
		return streetName;
	}

	
	/**
	 * Sets the street name of the customer.
	 *
	 * @param streetName The street name to set.
	 */
	public void setStreetName(String streetName)
	{
		this.streetName = streetName;
	}

	
	/**
	 * Gets the house number.
	 *
	 * @return The house number.
	 */
	public int getHouseNumber()
	{
		return houseNumber;
	}

	
	/**
	 * Sets the house number.
	 *
	 * @param houseNumber The house number to set.
	 */
	public void setHouseNumber(int houseNumber)
	{
		this.houseNumber = houseNumber;
	}

	
	/**
	 * Gets the floor number.
	 *
	 * @return The floor number.
	 */
	public int getFloorNumber()
	{
		return floorNumber;
	}

	
	/**
	 * Sets the floor number.
	 *
	 * @param floorNumber The floor number to set.
	 */
	public void setFloorNumber(int floorNumber)
	{
		this.floorNumber = floorNumber;
	}

	
	/**
	 * Gets the door number.
	 *
	 * @return The door number.
	 */
	public String getDoorNumber()
	{
		return doorNumber;
	}

	
	/**
	 * Sets the door number.
	 *
	 * @param doorNumber The door number to set.
	 */
	public void setDoorNumber(String doorNumber)
	{
		this.doorNumber = doorNumber;
	}
	
	
    /**
     * Returns a string representation of the Customer object.
     *
     * @return A string containing all relevant details of the customer.
     */
    @Override
    public String toString()
    {
        return "Customer [" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", customerType='" + customerType + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", clubMember=" + clubMember +
                ", purchaseThreshhold=" + purchaseThreshhold +
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