package local.iskou9821.aws.test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Properties;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.regions.RegionUtils;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerConfiguration;
import com.amazonaws.services.s3.transfer.Upload;

public class S3Test extends AbsAWSTest {
	private static String BUCKET_NAME="bucketName";
	private String bucketName;
	
	@Override
	protected void setFromProperties(Properties props) {
		super.setFromProperties(props);
		bucketName = props.getProperty(BUCKET_NAME);
		System.out.println("bucket-name:" + bucketName);
	}

	protected AmazonS3Client makeClient() {
		ClientConfiguration conf = new ClientConfiguration();
		conf.setConnectionTimeout(30000);
		
		AmazonS3Client client = new AmazonS3Client(getCredential(), conf);
		client.setRegion(RegionUtils.getRegion("ap-northeast-1"));
		return client;
	}
	
	public void testUpload() throws Exception {
		TransferManagerConfiguration tmConf = new TransferManagerConfiguration();
		tmConf.setMinimumUploadPartSize(5 * 1024 * 1024);
		
		AmazonS3Client client = makeClient();
		TransferManager tm = new TransferManager(client);
		tm.setConfiguration(tmConf);
		
		String content = "ドーモ　アマゾン＝サン　Test from Java API デス。ユウジョウ！";
		
		ObjectMetadata md = new ObjectMetadata();
		md.setContentLength(content.length());
		
		try (ByteArrayInputStream input = new ByteArrayInputStream(content.getBytes())) {
			PutObjectRequest req = new PutObjectRequest(bucketName, "test/TEST", input, md);
			Upload up = tm.upload(req);
			up.waitForCompletion();
		}
	}
	
	public void testList2() throws Exception {
		AmazonS3Client client = makeClient();
		ObjectListing list = client.listObjects(bucketName);
		
		System.out.println("list size=" + list.getObjectSummaries().size());
		for (S3ObjectSummary sm : list.getObjectSummaries()) {
			System.out.println(sm.getBucketName() + " / " +sm.getKey() + " / " + sm.getSize());
		}
	}
	
	public void _testList1() throws Exception {
		String backetName = "hoge";
		String fileName =  System.getProperty("user.home") +
							"/Documents/AWS/awsaccess.properties";
		System.out.println("file path=" + fileName);
		AWSCredentials cred = new PropertiesCredentials(new File(fileName));
		
		ClientConfiguration conf = new ClientConfiguration();
		conf.setConnectionTimeout(30000);
		
		AmazonS3Client client = new AmazonS3Client(cred, conf);
		client.setRegion(RegionUtils.getRegion("ap-northeast-1"));
		
		ObjectListing list = client.listObjects(backetName);
		
		System.out.println("list size=" + list.getObjectSummaries().size());
		for (S3ObjectSummary sm : list.getObjectSummaries()) {
			System.out.println(sm.getBucketName() + " / " +sm.getKey() + " / " + sm.getSize());
		}
	}
}
