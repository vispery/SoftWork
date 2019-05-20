package httpSample;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.commons.lang3.StringEscapeUtils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;







import util.Json2Codec;

public class HttpSample {
	static CloseableHttpClient httpClient;
	private static final Logger logger = Logger.getLogger(HttpFTP.class);
	public HttpSample() {
		super();
		init();
	}
	public void init(){
		httpClient = HttpClients.createDefault();		
	}
	
	public UploadResponse runUploadGet(String fileName)
	{
		UploadResponse upload = null;
		String page = new String("https://drfirst.brickftp.com/api/rest/v1/files/");
		page = page.concat(fileName);
		
		//Authorization
		String authString = new String("79094c529c0bf4bd2279018800ef40064ab38934:x");
		byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
		String authStringEnc = new String(authEncBytes);
		HttpGet httpGet = new HttpGet(page);
		httpGet.setHeader("Authorization", "Basic " + authStringEnc);
		
		
		System.out.println(httpGet.getRequestLine());
		CloseableHttpResponse response = null;
      	 BufferedReader br = null;
		try {
			response = httpClient.execute(httpGet);

         br = new BufferedReader(
				new InputStreamReader((response.getEntity().getContent())));

		String output;
		System.out.println("Output from Server .... \n");
		output = br.readLine();
			System.out.println(output);
			upload = Json2Codec.unmarshal(UploadResponse.class, output);
			String uploadEscape = new String(StringEscapeUtils.unescapeHtml4(upload.getDownload_uri()));
			upload.setUpload_uri(uploadEscape);
		response.close();
		
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			logger.warn(e.getMessage(), e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.warn(e.getMessage(), e);
		}finally{
          if(br!=null)
        	br.close();
        }
		return upload;
	}
	
	/* First step of the upload
	 * Post to brickftp url files/filename
	 * stores data from response into UploadResponse
	 * aws upload link will be used in next step
	 * ref will be used in closing step
	 */
	public UploadResponse runUploadStart(String fileName)
	{
		UploadResponse upload = null;
		String page = null;
		page = new String("https://drfirst.brickftp.com/api/rest/v1/files/");
		page = page.concat(fileName);
		System.out.println(page);
		String authString = new String("79094c529c0bf4bd2279018800ef40064ab38934:x");
		byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
		String authStringEnc = new String(authEncBytes);
      	BufferedReader br =null; 
		try
		{
			HttpPost httppost = new HttpPost(page);
		
			httppost.setHeader("Authorization", "Basic " + authStringEnc);
			StringEntity input = new StringEntity("{\"action\":\"put\"}"); //request body
			input.setContentType("application/json");
			httppost.setEntity(input);
			System.out.println(httppost.getRequestLine());
			CloseableHttpResponse response = httpClient.execute(httppost);
			 br = new BufferedReader(
					new InputStreamReader((response.getEntity().getContent())));
			
			String output;
			System.out.println("Output from Server .... \n");
			output = br.readLine();
				System.out.println(output);
				upload = Json2Codec.unmarshal(UploadResponse.class, output); //unmarshal json payload from response
				String uploadEscape = new String(StringEscapeUtils.unescapeHtml4(upload.getUpload_uri()));
				upload.setUpload_uri(uploadEscape);
			response.close();

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			logger.warn(e.getMessage(), e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.warn(e.getMessage(), e);
		}finally{
          if(br!=null)
        	br.close();
        }
		return upload;
	}
	
	/*Executes a HttpPut command to the upload link given by the first step (String url)
	 * returns a null response
	 */
	public void runUploadAWSStep(String url, String filePath)
	{	BufferedReader br  = null;
		HttpPut httpPut = new HttpPut(url);
		try {
			CloseableHttpResponse response = null;
			File file = new File(filePath);
			
			FileEntity input = new FileEntity(file);
			httpPut.setEntity(input);
			response = httpClient.execute(httpPut);
			 br = new BufferedReader(
					new InputStreamReader((response.getEntity().getContent())));

			String output;
			System.out.println("Output from Server .... \n");
			output = br.readLine();
				System.out.println(output);
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			logger.warn(e.getMessage(), e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.warn(e.getMessage(), e);
		}finally{
          if(br!=null)
        	br.close();
        }
	}
		
	/*
	 * closes the upload after it has completed
	 * body contains action : end and ref from starting step 
	 */
	public void closeUpload(String refId, String fileName)
	{
		String page = new String("https://drfirst.brickftp.com/api/rest/v1/files/");
		page = page.concat(fileName);
		String authString = new String("79094c529c0bf4bd2279018800ef40064ab38934:x");
		byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
		String authStringEnc = new String(authEncBytes);
    	BufferedReader br  = null;
      
		try
		{
			HttpPost httppost = new HttpPost(page);
			httppost.setHeader("Authorization", "Basic " + authStringEnc);
			StringEntity input = new StringEntity("{\"action\":\"end\", \"ref\":\""+ refId+ "\"}"); //request body with refId
			input.setContentType("application/json");
			httppost.setEntity(input);
			System.out.println(httppost.getRequestLine());
			CloseableHttpResponse response = httpClient.execute(httppost);
			 br = new BufferedReader(
					new InputStreamReader((response.getEntity().getContent())));

			String output;
			System.out.println("Output from Server .... \n");
			output = br.readLine();
				System.out.println(output);
			response.close();

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			logger.warn(e.getMessage(), e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.warn(e.getMessage(), e);
		}finally{
          if(br!=null)
        	br.close();
        }
	}
	
	/*downloads file from aws download link, which can be accessed by command runUploadGet or from UploadResponse class
	 * 
	 */
	public void downloadFile(String downloadURL, String filePath)
	{
		long startTime = System.currentTimeMillis();
		HttpGet httpget = new HttpGet(downloadURL);
      BufferedInputStream is = null;
      FileOutputStream fos =null;
      File f = null;
		try {
			HttpResponse response = httpClient.execute(httpget);
			HttpEntity entity = response.getEntity();
			if(entity != null)
			{
				long len = entity.getContentLength();
				 is = new BufferedInputStream(entity.getContent());
             	f = new File(filePath);
				 fos = new FileOutputStream(f);
				int inByte;
				while((inByte = is.read()) != -1)
				     fos.write(inByte);
				//is.close();
				//fos.close();
              //f.close();
			}
			
			long endTime = System.currentTimeMillis();

			System.out.println("That took " + (endTime - startTime) + " milliseconds");
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			logger.warn(e.getMessage(), e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.warn(e.getMessage(), e);
		}finally{
          if(is!=null)
        	is.close();
          if(fos!=null)
          	fos.close();
          if(f!=null)
            f.close();
        }
		
	}
	
	public static void main(String[] args) throws MalformedURLException
	{
		UploadResponse upload = null;
		UploadResponse download = null;
		HttpSample sample = new HttpSample();
		
//		String fileName = new String("testing.zip");
//		String upPath = new String ("C:\\Users\\ncheng\\Downloads\\spring-tool-suite-3.7.3.RELEASE-e4.5.2-win32-x86_64.zip");
//		String path = new String("C:\\users\\ncheng\\documents\\testingJava.zip");
		
		String fileName = new String("testing.txt");
		String upPath = new String ("testing.txt");
		String path = new String("C:\\users\\ncheng\\documents\\testingJava.txt");
		
		upload = sample.runUploadStart(fileName);
		
		sample.runUploadAWSStep(upload.getUpload_uri(), upPath);
		sample.closeUpload(upload.getRef(), fileName);
		download = sample.runUploadGet(fileName);
		System.out.println("Paste this link into browser: \n" + download.getDownload_uri());
		
		sample.downloadFile(download.getDownload_uri(), path);	
	}

}
