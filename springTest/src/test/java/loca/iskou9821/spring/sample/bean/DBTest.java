package loca.iskou9821.spring.sample.bean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.datasource.DataSourceUtils;

public class DBTest extends AbsSpringTest {
	public void testDataSource() {
		DataSource ds = getBean(DataSource.class);
		try (Connection conn = DataSourceUtils.getConnection(ds)) {
			try (Statement st = conn.createStatement()) {
				st.execute("drop table if exists hoge");
				st.execute("create table hoge(id integer, name varchar(100))");
				st.execute("insert into hoge(id,name) values(10, 'aaaaa') ");
				try (ResultSet rs = st.executeQuery("select * from hoge")) {
					while (rs.next()) {
						System.out.println(rs.getInt(1) + " / " + rs.getString(2));
					}
				}
			}
		} catch (CannotGetJdbcConnectionException|SQLException e) {
			throw new IllegalStateException(e);
		}
	}
}
