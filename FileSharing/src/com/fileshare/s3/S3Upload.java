/**
 * 
 */
package com.fileshare.s3;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.fileupload.FileItem;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.fileshare.util.RemovePrefix;

/**
 * @author simranjit
 */
public class S3Upload {
	
	public void uploadToS3(String folder,FileItem item) throws IOException{
		String bucket = "com.filesharing.simranjit";
		String user = folder;
		String key = item.getName();
		ObjectMetadata omd = new ObjectMetadata();
		omd.setContentLength(item.getSize());
		InputStream inputS = item.getInputStream();
		
		AmazonS3 s3Client = new AmazonS3Client(AWSCreds.credentialProfile());
		Region usWest2 = Region.getRegion(Regions.US_WEST_2);
		s3Client.setRegion(usWest2);
		
		try{
			s3Client.putObject(new PutObjectRequest(bucket, user + "/" + key,inputS,omd));
		} catch(AmazonServiceException ase){
			ase.printStackTrace();
		} catch(AmazonClientException ace){
			ace.printStackTrace();
		}
	}
	
	public void uploadToS3(String user,S3Object obj) throws IOException{
		String bucket = "com.filesharing.simranjit";
		String folder = "shared";
		String tempKey = obj.getKey();
		String key = RemovePrefix.removePrefix(user+"/", tempKey);
		ObjectMetadata om = obj.getObjectMetadata();
		InputStream inputS = obj.getObjectContent();
		System.out.println(key);
		AmazonS3 s3Client = new AmazonS3Client(AWSCreds.credentialProfile());
		Region usWest2 = Region.getRegion(Regions.US_WEST_2);
		s3Client.setRegion(usWest2);
		
		try{
			s3Client.putObject(new PutObjectRequest(bucket, folder+"/"+key,inputS,om));
		} catch(AmazonServiceException ase){
			ase.printStackTrace();
		} catch(AmazonClientException ace){
			ace.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException {
		S3Download s3d = new S3Download();
		S3Upload s3u = new S3Upload();
		S3Object obj = s3d.DownloadFromS3("sample.txt", "user1");
		s3u.uploadToS3("user1", obj);
	}

}
