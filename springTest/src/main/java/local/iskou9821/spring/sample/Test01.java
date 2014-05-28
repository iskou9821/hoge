package local.iskou9821.spring.sample;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import local.iskou9821.spring.sample.bean.HogeBean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.datasource.DataSourceUtils;


public class Test01 {
	public static void main(String[] args) {
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		HogeBean hoge = (HogeBean)ctx.getBean("hoge");
		System.out.println(hoge.getMsg());
		
		DataSource ds = (DataSource)ctx.getBean("dataSource");
		try (Connection conn = DataSourceUtils.getConnection(ds)) {
			try (Statement st = conn.createStatement()) {
				st.execute("create table hoge(id integer, name varchar(100))");
				st.execute("insert into hoge(id,name) values(10, 'aaaaa') ");
				try (ResultSet rs = st.executeQuery("select * from hoge")) {
					while (rs.next()) {
						System.out.println(rs.getInt(1) + " / " + rs.getString(2));
					}
				}
			}
		} catch (CannotGetJdbcConnectionException|SQLException e) {
			e.printStackTrace();
		}
	}
}
