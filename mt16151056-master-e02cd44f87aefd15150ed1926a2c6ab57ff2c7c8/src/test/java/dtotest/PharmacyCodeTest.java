package dtotest;

import java.io.File;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import dto.Pharmacy;
import util.Json2Codec;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public class PharmacyCodeTest 
{

	public PharmacyCodeTest() 
	{
		super();
	}
	XStream xstream = new XStream(new StaxDriver());
	
	public void codec_Pharm() throws Exception
	{
		Pharmacy dto = new Pharmacy();
		String address1 = new String("9010 Pharma Add");
		String address2 = new String("Room 1");
		String city = new String("City");
		String state = new String("MD");
		String zip = new String("20005");
		dto.setAddress(address1, address2, city, state, zip);
		System.out.println( ToStringBuilder.reflectionToString(dto.getAddress(), ToStringStyle.MULTI_LINE_STYLE) );
		dto.setEmail("pharmer@pharmacy.com");
		dto.setFax("2020400");
		dto.setH24Store(true);
		dto.setNcpdpid("2929210000");
		dto.setNpi("999");
		dto.setPhonePrimary("240-242-2644");
		dto.setStoreName("Name");
		dto.setStoreNumber("Store #5");
		
		
		String payload = Json2Codec.marshal(dto);		
		System.out.println(payload);		
	}
	
	public void codec_XML() throws Exception
	{
		Pharmacy dto = new Pharmacy();
		String address1 = new String("9010 Pharma Add");
		String address2 = new String("Room 1");
		String city = new String("City");
		String state = new String("MD");
		String zip = new String("20005");
		dto.setAddress(address1, address2, city, state, zip);
		dto.setEmail("pharmer@pharmacy.com");
		dto.setFax("2020400");
		dto.setH24Store(true);
		dto.setNcpdpid("2929210000");
		dto.setNpi("999");
		dto.setPhonePrimary("240-242-2644");
		dto.setStoreName("Name");
		dto.setStoreNumber("Store #5");
		
		String xml = xstream.toXML(dto);
		
		System.out.println(xml);
				
		//System.out.println(payload);		
	}
	
	/*
	public void read_Pharm(String jSonPayload) throws Exception
	{
		Pharmacy dto = new Pharmacy();
		dto = Json2Codec.unmarshal(Pharmacy.class, jSonPayload);
		System.out.println("JSON String");
		System.out.println(dto.getAddress1());
		System.out.println(dto.getAddress2());
		System.out.println(dto.getCity());
		System.out.println(dto.getEmail());
		System.out.println(dto.getFax());
		System.out.println(dto.isH24Store());
		System.out.println(dto.getNcpdpid());
		System.out.println(dto.getNpi());
		System.out.println(dto.getPhonePrimary());
		System.out.println(dto.getState());
		System.out.println(dto.getStoreName());
		System.out.println(dto.getStoreNumber());
		System.out.println(dto.getZip());
		
	}
	*/
	
	
	public void read_Pharm_File() throws Exception
	{
		Pharmacy dto = new Pharmacy();
		File jSonPayload = new File("pharmJSON.txt");
		//System.out.println(jSonPayload.getAbsolutePath());
		if(jSonPayload.exists())
			dto = Json2Codec.unmarshal(Pharmacy.class, jSonPayload);
		
		System.out.println("JSON File:");
	
		System.out.println("Email: " + dto.getEmail());
		System.out.println("Fax: " + dto.getFax());
		System.out.println("H24Store: " + dto.isH24Store());
		System.out.println("Ncpdpid: " + dto.getNcpdpid());
		System.out.println("Npi: " + dto.getNpi());
		System.out.println("Primary Phone: " + dto.getPhonePrimary());

		dto.parse(); //address

		System.out.println(dto.getAddressStr());
		System.out.println("Store Name: " + dto.getStoreName());
		System.out.println("Store Number: " + dto.getStoreNumber());
		
		System.out.println("Testing individual Parts");
		System.out.println(dto.getAddress().getAddress1());
		System.out.println(dto.getAddress().getAddress2());
		System.out.println(dto.getAddress().getCity());
		System.out.println(dto.getAddress().getCode());
		System.out.println(dto.getAddress().getZip());
	
	}
	public void read_XML() throws Exception
	{
	
		File xml = new File("pharmXML.xml");
		//System.out.println(jSonPayload.getAbsolutePath());
		
		xstream.alias("dto", Pharmacy.class);
		Pharmacy dto = (Pharmacy) xstream.fromXML(xml);
		System.out.println("xml File:");
	
		System.out.println("Email: " + dto.getEmail());
		System.out.println("Fax: " + dto.getFax());
		System.out.println("H24Store: " + dto.isH24Store());
		System.out.println("Ncpdpid: " + dto.getNcpdpid());
		System.out.println("Npi: " + dto.getNpi());
		System.out.println("Primary Phone: " + dto.getPhonePrimary());

		System.out.println(dto.getAddress().getAddress1());
		System.out.println(dto.getAddress().getAddress2());
		System.out.println(dto.getAddress().getCity());
		System.out.println(dto.getAddress().getCode());
		System.out.println(dto.getAddress().getZip());
		System.out.println("Store Name: " + dto.getStoreName());
		System.out.println("Store Number: " + dto.getStoreNumber());
	}
	
	
	public static void main(String[] args) throws Exception {		
		PharmacyCodeTest agent = new PharmacyCodeTest();
		
		//String jSonPayload = new String("{\"ncpdpid\" : \"2929210000\", \"storeNumber\" : \"Store #5\",  \"storeName\" : \"Name\",  \"npi\" : \"NPI: 999\", \"h24Store\" : true,	\"address1\" : \"9010 Pharma Add\",	\"address2\" : \"Room 1\", \"city\" : \"City\", \"state\" : \"MD\", \"zip\" : \"20005\", \"email\" : \"pharmer@pharmacy.com\", \"fax\" : \"2020400\", \"phonePrimary\" : \"240-242-2644\"}");
		//agent.read_Pharm(jSonPayload);
		
		//agent.read_Pharm_File();
		//agent.read_XML();
		
		//agent.codec_XML();
		//agent.read_XML();
		
		agent.codec_Pharm();
		
		System.exit(0);
	}

}
