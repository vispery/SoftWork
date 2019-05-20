package dtotest;

import java.io.File;
import dto.Drug;
import util.Json2Codec;

/**
 * @author ztang
 * generated at : Mar 10, 2015 - 10:38:34 AM
 */
public class DrugCodeTest {

	public DrugCodeTest() {
		super();
	}
	
	public void codec_Drug() throws Exception{
		Drug dto = new Drug();
		dto.setNdcId("1234567");
		dto.setStrength(1000);
		dto.setBrandName("GenericBrand");
		dto.setDrugId("1111");
		dto.setRoute("Route x");
		dto.setGenericName("Generic Name");
		dto.setFdbGcnSeqNum("1234");
		
		String payload = Json2Codec.marshal(dto);		
		System.out.println(payload);		
	}
	/*
	public void read_Drug_String(String jSonPayload) throws Exception
	{
		Drug dto = new Drug();
	
		dto = Json2Codec.unmarshal(Drug.class, jSonPayload);
		System.out.println("JSON String");
		System.out.println(dto.getNdcId());
		System.out.println(dto.getStrength());
		System.out.println(dto.getBrandName());
		System.out.println(dto.getDrugId());
		System.out.println(dto.getRoute());
		System.out.println(dto.getGenericName());
		System.out.println(dto.getFdbGcnSeqNum());
	
	}
	*/
	
	public void read_Drug_File() throws Exception
	{
		Drug dto = new Drug();
		File jSonPayload = new File("drugJSON.txt");
		//System.out.println(jSonPayload.getAbsolutePath());
		if(jSonPayload.exists())
			dto = Json2Codec.unmarshal(Drug.class, jSonPayload);
		
		System.out.println("JSON File");
		System.out.println("NdcId: " + dto.getNdcId());
		System.out.println("strength: " + dto.getStrength());
		System.out.println("Brand Name: " + dto.getBrandName());
		System.out.println("Drug ID: " + dto.getDrugId());
		System.out.println("Route: " + dto.getRoute());
		System.out.println("Generic name: " + dto.getGenericName());
		System.out.println("FdbGcnSeqNum: " + dto.getFdbGcnSeqNum());
	
	}
	
	public static void main(String[] args) throws Exception {		
		DrugCodeTest agent = new DrugCodeTest();
		//String jSonPayload = new String("{\"ndcId\" : \"2929210000\", \"strength\" : \"12\",  \"brandName\" : \"Name\",  \"drugId\" : \"1111\", \"route\" : \"route 2\",	\"genericName\" : \"genName\",	\"fdbGcnSeqNum\" : \"555\"}");
		
		agent.read_Drug_File();
		//agent.read_Drug_String(jSonPayload);

		agent.codec_Drug();
		
		System.exit(0);
	}

}
