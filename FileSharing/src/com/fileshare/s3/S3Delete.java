package com.fileshare.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.DeleteObjectRequest;

public class S3Delete {
	public void deleteSharedObject(String keyName) {
		AmazonS3 s3Client = new AmazonS3Client(AWSCreds.credentialProfile());
		String bucketName = "com.filesharing.simranjit";
		s3Client.deleteObject(new DeleteObjectRequest(bucketName, "shared"
				+ "/" + keyName));
	}
}
