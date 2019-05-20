package dtotest;

import java.io.File;

import dto.UsState;
import util.Json2Codec;


public class StateCodeTest 
{

	public StateCodeTest() 
	{
		super();
	}
	
	public void codec_State() throws Exception
	{
		UsState dto = new UsState();
		dto.setCode("ST");
		dto.setName("State");
		
		String payload = Json2Codec.marshal(dto);		
		System.out.println(payload);		
	}
	
	/*
	public void read_State(String jSonPayload) throws Exception
	{
		UsState dto = new UsState();
		dto = Json2Codec.unmarshal(UsState.class, jSonPayload);
		System.out.println("JSON String");
		System.out.println(dto.getName());
		System.out.println(dto.getCode());
	}
	*/
	
	public void read_State_File() throws Exception
	{
		UsState dto = new UsState();
		File jSonPayload = new File("USJSON.txt");
		//System.out.println(jSonPayload.getAbsolutePath());
		if(jSonPayload.exists())
			dto = Json2Codec.unmarshal(UsState.class, jSonPayload);
		
		System.out.println("JSON File:");
		System.out.println("Name: " + dto.getName());
		System.out.println("Code: " + dto.getCode());
	
	}
	
	public static void main(String[] args) throws Exception {		
		StateCodeTest agent = new StateCodeTest();
		//String jSonPayload = new String("{\"code\" : \"ST\", \"name\" : \"State\"}");

		//agent.read_State(jSonPayload);
		
		agent.read_State_File();
		agent.codec_State();
		
		System.exit(0);
	}

}
