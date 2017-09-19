import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/**
 * 呼叫中心数据库服务器测试
 * @author tom
 *
 */
public class SqlServerConnectTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String driver2="com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String url="jdbc:sqlserver://172.18.10.90:1433;DatabaseName=ICC";
		String user="sa";
		String password="Good1234";
		try {
//			Class.forName(driver2);
//			Connection cnt = DriverManager.getConnection(url, user, password);
			Class.forName(driver2);
			Connection cnt = DriverManager.getConnection(url, user, password);
			String sql = "select id, seatno, password, SipPassword, Gateway, GatewayNumber, ExtGateway, ExtGatewayNumber, DID, station, holdcall, callstate, called, state, modifytime, actor, seattype, LocalTime, DDDtime, EnterpriseID, Email, Emailsmtp, Emailpop3, Emailusername, Emailpassword, groupID, setbusy, ExtMobile, isextrecord, Name from sys_seat where id = 175";
			PreparedStatement ps = (PreparedStatement) cnt.prepareStatement(sql);
//			ps.setInt(0, 1);
			ResultSet rs = ps.executeQuery();
			System.out.println("*********************************");
			System.out.println("rs:"+rs);
			while(rs.next()){
				System.out.println(rs.getString("SEATNO"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("tttttt");
		
		
		
	}

}
