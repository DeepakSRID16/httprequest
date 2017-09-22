package file.FileReaderfromdl;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.util.Base64;

public class App {
	 private static final int BUFFER_SIZE = 4096;
	public static void main(String[] args) {
		
		String server = "192.168.6.79";
	    int port = 21;
	    String user = "john";
	    String pass = "john";

	    FTPClient ftpClient = new FTPClient();
	    try {

	      ftpClient.connect(server, port);
	      boolean loggedIn = ftpClient.login(user, pass);
	      System.out.println("loged in " + loggedIn);
	      ftpClient.enterLocalPassiveMode();
	      ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

	      String remoteFile1 = "hi.txt";
	      System.out.println("remoteFile1 " + remoteFile1);

	      /*File downloadFile1 = new File("/");
	      if (!downloadFile1.exists()) {
	        downloadFile1.mkdirs();
	        System.out.println("here");
	        
	      }*/
	      OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream("/home/deepak/hi.txt"));
	      
	      boolean success = ftpClient.retrieveFile("/www/hi.txt", outputStream1);
	      outputStream1.close();

	      if (success) {
	    	  BufferedReader br1 = new BufferedReader(new FileReader("/home/deepak/hi.txt"));
              String st1="";
              String temp1="";
              String base64Str1 = "";
              while((st1=br1.readLine()) != null){
                   temp1 += st1+"\n";    
              }
              if(temp1 !="" & temp1 !=null)
              {
            	  byte a []= Base64.encodeBase64(temp1.getBytes(Charset.forName("UTF-8")));
            	  base64Str1 += new String(a);
            	  System.out.println("File #1 has been downloaded successfully."+base64Str1);
              }
	      	  }
              else{
            	  System.out.println("not download");
              }
	    } catch (IOException ex) {
	      System.out.println("Error: " + ex.getMessage());
	      ex.printStackTrace();
	    } finally {
	      try {
	        if (ftpClient.isConnected()) {
	          ftpClient.logout();
	          ftpClient.disconnect();
	        }
	      } catch (IOException ex) {
	        ex.printStackTrace();
	      }
	    }

		/*
		  	String ftpUrl = "ftp://%s:%s@%s/%s;type=i";
	    
		  	String host = "192.168.6.79";
		    int port = 21;
		    String user = "rohan";
		    String pass = "oodles1";
		  
	        String filePath = "ftp://192.168.6.79/www/hi.txt";
	        String uploadPath = "/home/deepak/rohan.txt";

	        ftpUrl = String.format(ftpUrl, user, pass, host, uploadPath);
	        System.out.println("Upload URL: " + ftpUrl);
	        ftpUrl = String.format(ftpUrl, user, pass, host, uploadPath);
	        System.out.println("Upload URL: " + ftpUrl);
	 
	        try {
	            URL url = new URL(ftpUrl);
	            URLConnection conn = url.openConnection();
	            OutputStream outputStream = conn.getOutputStream();
	            FileInputStream inputStream = new FileInputStream(filePath);
	 
	            byte[] buffer = new byte[BUFFER_SIZE];
	            int bytesRead = -1;
	            while ((bytesRead = inputStream.read(buffer)) != -1) {
	                outputStream.write(buffer, 0, bytesRead);
	            }
	 
	            inputStream.close();
	            outputStream.close();
	 
	            System.out.println("File uploaded");
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }*/
	}
}