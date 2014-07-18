# Spring Hibernate JPA
いくつかの要素のテストをするサンプルです。

 * SpringからJPAを利用する。
 * JPAの実装として、Hibernateを利用する。
 * トランザクションの管理をSpringで行う
   * @Transactional - HogeServiceImpl
 * Springのコンポーネント登録を自動で行う
   * component-scan - applicationContext_ut.xml
 * 永続化対象クラスの登録を自動で行う
   * packagesToScan - jpa.xml
 * Hibernateを利用してテーブル作成を自動で行う。
   * hibernate.hbm2ddl.auto - jpa.xml
 * xmlの設定をプロパティファイルに出す
   * context:property-placeholder - applicationContext_ut.xml

