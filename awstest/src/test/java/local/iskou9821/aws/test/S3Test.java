package local.iskou9821.aws.test;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.Properties;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.event.ProgressEvent;
import com.amazonaws.event.ProgressListener;
import com.amazonaws.regions.RegionUtils;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
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
		
		/*
		 * バゲット名やアクセスキーの書かれたファイルを公開レポジトリにアップするわけにもいかないので、プロパティファイルから読み込む。
		 * プロパティファイルのサンプルは/test/resources/awsaccess.properties.sample
		 */
		AmazonS3Client client = new AmazonS3Client(getCredential(), conf);
		client.setRegion(RegionUtils.getRegion("ap-northeast-1")); //東京リージョンを指定
		return client;
	}
	
	/*
	 * アップロードのテスト
	 */
	public void testUpload() throws Exception {
		TransferManagerConfiguration tmConf = new TransferManagerConfiguration();
		tmConf.setMinimumUploadPartSize(5 * 1024 * 1024);
		
		AmazonS3Client client = makeClient();
		TransferManager tm = new TransferManager(client);
		tm.setConfiguration(tmConf);
		
		//適当なテキストを保存することにする。
		String content = "ドーモ　アマゾン＝サン　Test from Java API デス。ユウジョウ！";
		byte[] data = content.getBytes();
		
		//保存処理
		ObjectMetadata md = new ObjectMetadata();
		md.setContentLength(data.length);
		try (ByteArrayInputStream input = new ByteArrayInputStream(data)) {
			System.out.println("Amazon S3へのアップロードを開始");
			
			String key ="test/sample03.txt"; //保存するオブジェクトのパス
			
			PutObjectRequest req = new PutObjectRequest(bucketName, key, input, md);
			req.withCannedAcl(CannedAccessControlList.PublicRead); //認証なしで読み出しができるように設定(デフォルトでは認証なしで読み出すことができない)
			
			URL url = client.getUrl(bucketName, key);
			System.out.println("生成するオブジェクトのURL:" + url); //生成されるS3オブジェクトのURLを確認
			
			Upload up = tm.upload(req);
			
			//状態を監視(アップロードは非同期で実行されるっぽい)
			up.addProgressListener(new ProgressListener() {
				@Override
				public void progressChanged(ProgressEvent arg0) {
					switch (arg0.getEventCode()) {
					case ProgressEvent.COMPLETED_EVENT_CODE :
						System.out.println("完了しました");
						break;
					case ProgressEvent.CANCELED_EVENT_CODE :
						throw new IllegalStateException("キャンセルされました");
					case ProgressEvent.FAILED_EVENT_CODE :
						throw new IllegalStateException("失敗です");
					case ProgressEvent.PART_FAILED_EVENT_CODE :
						throw new IllegalStateException("ちょっと失敗です");
					default : 
						System.out.println("EventCode=" + arg0.getEventCode());
					}
				}
			});
			up.waitForCompletion(); //アップロードのスレッドが終わるのを待つ
			System.out.println("アップロード処理終了");
		}
	}
	
	/*
	 * S3に保存しているファイルの一覧を取得
	 */
	public void testList2() throws Exception {
		AmazonS3Client client = makeClient();
		ObjectListing list = client.listObjects(bucketName);
		
		System.out.println("list size=" + list.getObjectSummaries().size());
		for (S3ObjectSummary sm : list.getObjectSummaries()) {
			System.out.println(sm.getBucketName() + " / " +sm.getKey() + " / " + sm.getSize() + " : " + 
					client.getUrl(bucketName,sm.getKey()));
		}
	}	
}
