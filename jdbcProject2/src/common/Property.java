package common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Property {

	public static void main(String[] args) {

		Properties prop  = new Properties();
		
		String driver = "oracle.jdbc.driver.OracleDriver";
		String user = "bm";
		String password = "test1";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		
		prop.setProperty("driver", driver);
		prop.setProperty("user", user);
		prop.setProperty("password", password);
		prop.setProperty("url", url);
		

		try {
			prop.store(new FileOutputStream("db.properties"), "db");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
