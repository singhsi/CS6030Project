/**
 * 
 */
package com.fileshare.s3;


import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

import java.io.File;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;

/**
 * @author simranjit
 * Reference: S3Sample code and Ben's Pseudo code
 */
public class S3Util {
	/**
	 * 
	 * @return default credential profile by reading the 
	 * 			credentials file located at .aws/credentials
	 */
	public static AWSCredentials credentialProfile() throws AmazonClientException{
		return new ProfileCredentialsProvider("default").getCredentials();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if(args.length != 3){
			System.err.println("Please provide appropriate arguments");
			System.err.println("java <javaclassname> <srcDir> <bucket> <bucketFolderPath>");
			System.exit(0);
		}
		
		String srcDir = args[0];
		String bucket = args[1];
		String prefix = args[2];
		
		AWSCredentials awsCredentials = null;
		try{
			System.out.println("Instantiating AWS Credentials.");
			awsCredentials = credentialProfile();
		} catch (Exception e){
			throw new AmazonClientException(
                    "Cannot load the credentials from the credential profiles file. " +
                    "Please make sure that your credentials file is at the correct " +
                    "location (/home/simranjit/.aws/credentials), and is in valid format.",
                    e);
		}
		System.out.println("Instantiating a AmazonS3 connection");
		AmazonS3 s3 = new AmazonS3Client(awsCredentials);
		Region usWest2 = Region.getRegion(Regions.US_WEST_2);
		s3.setRegion(usWest2);
		System.out.println("Calling syncDir method.");
		syncDir(srcDir, s3, bucket, prefix, true);
	}

	private static void syncDir(String srcDir, AmazonS3 s3, String bucket,
			String prefix, boolean delete) {
		System.out.println("Getting all the file in local directory.");
		List<File> filesInDir = getFiles(srcDir);
		for(File f: filesInDir){
			if(f.isFile()){
				String key = "";
				key = prefix + "/" + f.getName();
				System.out.println(String.format("Syncing %s to S3", f.getName()));
				s3.putObject(new PutObjectRequest(bucket, key, f));
			}
			if(f.isDirectory()){
				System.out.println("Directory, Recursive call to syncDir");
				String newDir = srcDir + "/" + f.getName();
				syncDir(newDir, s3, bucket, prefix, delete);
			}
		}
		
		if(delete==true){
			ObjectListing objectListing = s3.listObjects(new ListObjectsRequest()
			.withBucketName(bucket)
			.withPrefix(prefix));
			String localPath = "";
			for(S3ObjectSummary objectSummary:
				objectListing.getObjectSummaries()){
				localPath = objectSummary.getKey().replace(prefix, srcDir);
				File localFile = new File(localPath);
				if(!localFile.exists()){
					System.out.println("Delete: " + objectSummary.getKey());
					s3.deleteObject(bucket, objectSummary.getKey());
				}
			}
		}
	}
	
	/**
	 * 
	 * @param srcDir
	 * @return All the files in the srcDir
	 */
	private static List<File> getFiles(String srcDir){
		File directory = new File(srcDir);
		List<File> allFiles = (List<File>) FileUtils.listFiles(directory, TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
		return allFiles;
	}

}
