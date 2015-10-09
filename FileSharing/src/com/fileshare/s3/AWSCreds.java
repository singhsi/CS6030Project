/**
 * 
 */
package com.fileshare.s3;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;

/**
 * @author simranjit
 *
 */
public class AWSCreds {
	/**
	 * 
	 * @return default credential profile by reading the 
	 * 			credentials file located at .aws/credentials
	 */
	public static AWSCredentials credentialProfile() throws AmazonClientException{
		return new ProfileCredentialsProvider("default").getCredentials();
	}
}
