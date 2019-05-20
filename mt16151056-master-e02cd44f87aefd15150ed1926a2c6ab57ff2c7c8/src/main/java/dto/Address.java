package dto;

import java.io.Serializable;

public class Address implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public Address()
	{
		super();
	}
	
	public Address(String add1, String add2, String city, String zip, String code)
	{
		this.setAddress1(add1);
		this.setAddress2(add2);
		this.setCity(city);
		this.setZip(zip);
		this.setCode(code);
	}
	
	

	private String address1;	
	public String getAddress1() {	return address1;	}
	public void setAddress1(String address) {	this.address1 = address;	}
	
	private String address2;
	public String getAddress2() {	return address2;	}
	public void setAddress2(String address2) {	this.address2 = address2;	}

	private String city;
	public String getCity() {	return this.city;	}
	public void setCity(String city) {	this.city = city;	}
	
	private String zip;
	public String getZip() {	return this.zip;	}
	public void setZip(String zip) {	this.zip = zip;	}
	
	private String code;
	public String getCode() {	return code;	}
	public void setCode(String code) {	this.code = code;	}
	/*
	public String toString()
	{
		String address = new String();
		address = address.concat(this.getAddress1()) + "\n";
		address = address.concat(this.getAddress2()+ "\n");
		address = address.concat(this.getCity() + ", " + code + "\n");
		address = address.concat(this.getZip() + "\n");
		return address;
	}
	*/

	@Override
	public String toString() {
		return "Address [address1=" + address1 + ", address2=" + address2 + ", city=" + city + ", zip=" + zip
				+ ", code=" + code + "]";
	}
}
