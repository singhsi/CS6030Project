package com.fileshare.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

public class S3Download {
	public S3Object DownloadFromS3(String filename, String folder){
		String bucketName = "com.filesharing.simranjit";
		String keyname = folder+"/"+filename;
		AmazonS3 s3Client = new AmazonS3Client(AWSCreds.credentialProfile());
		GetObjectRequest objRequest = new GetObjectRequest(bucketName, keyname);
		S3Object obj = s3Client.getObject(objRequest);
		return obj;
	}
}
