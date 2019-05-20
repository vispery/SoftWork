package httpSample;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;
import org.apache.commons.lang3.StringEscapeUtils;

import util.Json2Codec;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;


public class HttpFTP {
	private static final Logger logger = Logger.getLogger(HttpFTP.class);
	
/**uploads file from param filePath to brickftp/fileName
 * 
 * @param fileName: the name of the file as it appears on brickftp
 * @param filePath: the file being uploaded
 * @return aws download link
 */
	public static String upload(String fileName, String filePath)
	{	
		CloseableHttpClient httpClient = HttpClients.createDefault();
		

		String page = "https://drfirst.brickftp.com/api/rest/v1/files/";
		page = page.concat(fileName);

		//Authorization
		String authString = "79094c529c0bf4bd2279018800ef40064ab38934:x";
		byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
		String authStringEnc = new String(authEncBytes);
		HttpPost httppost = new HttpPost(page);
		httppost.setHeader("Authorization", "Basic " + authStringEnc);
		String downloadURI = "upload unavailable";
		BufferedReader br = null;
     
		try {
			//upload step
			StringEntity upInput = new StringEntity("{\"action\":\"put\"}"); 
			upInput.setContentType("application/json");
			httppost.setEntity(upInput);
			
			CloseableHttpResponse response = httpClient.execute(httppost);

			 br = new BufferedReader(
					new InputStreamReader((response.getEntity().getContent())));
			String output = br.readLine();
			logger.info(output);
			UploadResponse upload = Json2Codec.unmarshal(UploadResponse.class, output);
			String uploadEscape = StringEscapeUtils.unescapeHtml4(upload.getUpload_uri());

			upload.setUpload_uri(uploadEscape);
			logger.info("upload done");
			
			//aws step	
			String uri = upload.getUpload_uri();

			HttpPut httpPut = new HttpPut(uri);

			File file = new File(filePath);

			FileEntity input = new FileEntity(file);
			httpPut.setEntity(input);
			response = httpClient.execute(httpPut);
			logger.info("aws done");

			//close step
			String refId = upload.getRef();
			StringEntity input2 = new StringEntity("{\"action\":\"end\", \"ref\":\""+ refId+ "\"}"); //request body with refId
			input2.setContentType("application/json");
			httppost.setEntity(input2);
			response = httpClient.execute(httppost);
			logger.info("close done");
			
			
			//get step for donwload link
			UploadResponse download = runUploadGet(fileName);
			logger.info("get done");
			if(download != null)
				downloadURI = download.getDownload_uri();
          	else
              	throw (new Exception("uri error"));
			br.close();
          response.close();

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			logger.warn(e.getMessage(), e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.warn(e.getMessage(), e);
		}
 		catch(Exception e) {
          logger.warn(e.getMessage(),e);
        } 
      	finally {
      
         if(br!=null)
            try {
              br.close();
            } catch (IOException ioe) {
              logger.warn(ioe.getMessage(), ioe);
            }
        }
		return downloadURI;
	}
	
	/**
	 * HttpGet response from downURI into byte[]
	 * @param downURI
	 * @return byte[] bytes contains the file downloaded as it appears in a byte[]
	 */
	public static byte[] download(String downURI)
	{
		byte[] bytes = null;
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(downURI);
		try {
			CloseableHttpResponse response = httpClient.execute(httpget);
			 ByteArrayOutputStream baos = new ByteArrayOutputStream();
			    response.getEntity().writeTo(baos);
			    bytes = baos.toByteArray();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			logger.warn(e.getMessage(), e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.warn(e.getMessage(), e);
		}
		return bytes;
		
	}
	
	private static UploadResponse runUploadGet(String fileName) throws IOException
	{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		UploadResponse upload = null;
		String page = "https://drfirst.brickftp.com/api/rest/v1/files/";
		page = page.concat(fileName);
		
		//Authorization
		String authString = "79094c529c0bf4bd2279018800ef40064ab38934:x";
		byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
		String authStringEnc = new String(authEncBytes);
		HttpGet httpGet = new HttpGet(page);
		httpGet.setHeader("Authorization", "Basic " + authStringEnc);
		
		BufferedReader br = null;
		try {
			CloseableHttpResponse response = httpClient.execute(httpGet);

		 br = new BufferedReader(
				new InputStreamReader((response.getEntity().getContent())));


		String output = br.readLine();
		logger.info(output);
			upload = Json2Codec.unmarshal(UploadResponse.class, output);
			String downloadEscape = StringEscapeUtils.unescapeHtml4(upload.getDownload_uri());
			upload.setDownload_uri(downloadEscape);
			
		response.close();
		
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			logger.warn(e.getMessage(), e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.warn(e.getMessage(), e);
		}finally{
         if(br!=null)
            try {
              br.close();
            } catch (IOException ioe) {
              logger.warn(ioe.getMessage(), ioe);
            }
        }
		httpClient.close();
		return upload;
	}
	
}

