# AWS-test
Amazon Web ServicesのAPIを使ってみるテスト。

## ビルド方法
ビルドにはMavenを利用しますので、mavenをインストールした状態で

    mvn clean package

でビルドできます。

Eclipse(Keplerを想定)で開く場合の手順は以下の通りです。

 1. __sample.project__を__コピー__して__.project__という名前のファイルを作る(＊リネームではなくコピーの方が良いです)
 1. このディレクトリをEclipseのワークスペースにインポートする(File → Import → General → Existing Projects into Workspace)
 1. プロジェクトを右クリック → Configure → Convert to Maven Project
 
 以上の手順で完了です。

## 見るべきクラス

### Amazon S3
__src/test/java - local.iskou9821.aws.test.S3Test__

__testUpload__(アップロードするテスト)と__testList__(ファイル一覧を取るテスト)の2つがあります。

利用にはAWSのAccessKeyとSecretAcceesKey、作成済のS3 Bucketが必要です。  
test/resources/awsaccess.properties.sampleを任意の場所にコピーし、必要事項を記入してください。

それが完了したら、local.iskou9821.aws.test.AbsAWSTest の FILE_NAMEという定数に、上記のファイルパスを記入して下さい。

なお、実行ユーザーの作成は、AWSの管理コンソールから「IAM(AWS Identity and Access Management)」のメニューで行うことができます。
