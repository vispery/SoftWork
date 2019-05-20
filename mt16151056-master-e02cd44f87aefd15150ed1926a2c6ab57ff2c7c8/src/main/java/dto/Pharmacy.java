package dto;


public class Pharmacy {
	
	public Pharmacy() {
		super();
	}
	
	private String ncpdpid;
	public String getNcpdpid() {return ncpdpid;	}
	public void setNcpdpid(String ncpdpid) {	this.ncpdpid = ncpdpid;	}
	
	private String storeNumber;	
	public String getStoreNumber() {	return storeNumber;	}
	public void setStoreNumber(String storeNumber) {	this.storeNumber = storeNumber;	}
	
	private String storeName;
	public String getStoreName() {	return storeName; }
	public void setStoreName(String storeName) {	this.storeName = storeName;	}
		
	private String npi;
	public String getNpi() {return npi;	}
	public void setNpi(String npi) {	this.npi = npi;	}
	
	
	private boolean h24Store;
	public boolean isH24Store() {	return h24Store;	}
	public void setH24Store(boolean h24Store) {	this.h24Store = h24Store;	}
	
	private String address = new String();
	private Address newAddress = new Address();
	public void setAddress(String address1, String address2, String city, String state, String zip)
	{
		newAddress.setAddress1(address1);
		newAddress.setAddress2(address2);
		newAddress.setCity(city);
		newAddress.setCode(state);
		newAddress.setZip(zip);
	}
	public String getAddressStr()
	{
		return this.address;
	}
	public Address getAddress()
	{
		return this.newAddress;
	}
	public void parse()
	{
		int firstLine = this.getAddressStr().indexOf("\n");
		int secondLine = this.getAddressStr().indexOf("\n", firstLine+1);
		int thirdLine = this.getAddressStr().indexOf(",", secondLine+1);
		int fourthLine = this.getAddressStr().indexOf("\n", thirdLine+1);
		this.setAddress(this.getAddressStr().substring(0, firstLine), //address1
				this.getAddressStr().substring(firstLine+1, secondLine), //address2
						this.getAddressStr().substring(secondLine+1, thirdLine), //City
						this.getAddressStr().substring(this.getAddressStr().indexOf(",")+2, fourthLine), //state
						this.getAddressStr().substring(fourthLine+1, this.getAddressStr().length())); //zip
	}
	
	/*
	private String address1;	
	public String getAddress1() {	return address1;	}
	public void setAddress1(String address1) {	this.address1 = address1;	}
	
	private String address2;
	public String getAddress2() {	return address2;	}
	public void setAddress2(String address2) {	this.address2 = address2;	}

	private String city;
	public String getCity() {	return this.city;	}
	public void setCity(String city) {	this.city = city;	}
	
	
	private String state;
	public String getState() {	return this.state;	}
	public void setState(String state) {	this.state = state;	}
	
	private String zip;
	public String getZip() {	return this.zip;	}
	public void setZip(String zip) {	this.zip = zip;	}
	*/
	
	private String email;
	public String getEmail() {	return this.email;	}
	public void setEmail(String email) {	this.email = email;	}

	private String fax;
	public String getFax() {	return this.fax;	}
	public void setFax(String fax) {	this.fax = fax;	}
	
	private String phonePrimary;
	public String getPhonePrimary() {	return phonePrimary;	}
	public void setPhonePrimary(String phonePrimary) {	this.phonePrimary = phonePrimary;	}

	

}