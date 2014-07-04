# JERSEY-TEST
Jerseyを利用し、RESTな感じの通信を行うサンプルです。  
EclipseのDynamic Web Moduleとして作成していることもあり、ちょっとフォルダ構成が変です。。。  
(ま、直さないわけですがf(^_^;))

特に配布の予定もないので、Eclipse上で動作すれば特に問題ないと思います。

## ビルドの仕方
ライブラリの管理にmavenとbowerを利用しています。  
そのため、上記2つのソフトウェアを先にインストールしておいてください。

まず、一番初めにレポジトリのルート・ディレクトリで

    mvn package

を実行。

その次に、WebContentディレクトリに移動して

    bower update

を実行。

最後に、レポジトリのルート・ディレクトリで

    mvn dependency:copy-dependencies -DoutputDirectory=./WebContent/WEB-INF/lib

を実行して下さい。

その後、Eclipse用に.projectファイルを作成してプロジェクトを開き、Mavenを有効にしてから、WTPを有効にすれば完了です。