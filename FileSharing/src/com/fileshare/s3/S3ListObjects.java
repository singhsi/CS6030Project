package com.fileshare.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;

public class S3ListObjects {
	
	public ObjectListing listFromS3(String folder){
		AmazonS3 s3client = new AmazonS3Client(AWSCreds.credentialProfile());
		String bucket = "com.filesharing.simranjit";
		ListObjectsRequest listObjectsRequest = new ListObjectsRequest()
		.withBucketName(bucket)
		.withPrefix(folder+"/");
		ObjectListing objectListing;
		objectListing = s3client.listObjects(listObjectsRequest);
		return objectListing;
	}
}
