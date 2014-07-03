package local.iskou9821.aws.test;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

import junit.framework.TestCase;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;

public class AbsAWSTest extends TestCase {
	private static String FILE_NAME=
			System.getProperty("user.home") + "/Documents/AWS/awsaccess.properties";
	
	private static String ACCESS_KEY="accessKey";
	private static String SEC_ACCESS_KEY="secretKey";

	private String accessKey;
	private String secAccessKey;
	private AWSCredentials credential;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		Properties props = new Properties();
		try (FileReader rd =new FileReader(new File(FILE_NAME))) {
			props.load(rd);
		}
		setFromProperties(props);
		credential = makeCredential();
	}
	
	protected void setFromProperties(Properties props) {
		accessKey = props.getProperty(ACCESS_KEY);
		secAccessKey = props.getProperty(SEC_ACCESS_KEY);
	}
	
	protected AWSCredentials makeCredential() {
		//PropertiesCredentialsなら引数にファイルを渡すことも出来るが、プロパティファイルに他の情報も入れたいので。。。
		return new BasicAWSCredentials(accessKey, secAccessKey);
	}
	
	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public String getSecAccessKey() {
		return secAccessKey;
	}

	public void setSecAccessKey(String secAccessKey) {
		this.secAccessKey = secAccessKey;
	}

	public AWSCredentials getCredential() {
		return credential;
	}

	public void setCredential(AWSCredentials credential) {
		this.credential = credential;
	}
}
