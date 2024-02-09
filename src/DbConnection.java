import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
	  private static final String url="jdbc:mysql://localhost:3306/railwayresv";
	    private static final String userName="root";
		private static final String password="Ranjith@492";
		
		public static Connection getConnection() throws Exception {
			return DriverManager.getConnection(url,userName,password);
		}
}
