import java.sql.*;
public class basic_connection_qn1 {
    private static final String URL="jdbc:postgresql://localhost:5432/test_db";
    private static final String USER="postgres";
    private static final String PASSWORD="postgresSql";
    public static void main(String[] args){
        Connection conn=null;
        try{
            Class.forName("org.postgresql.Driver");
            conn=DriverManager.getConnection(URL,USER,PASSWORD);
            if(conn!=null && !conn.isClosed()){
                System.out.println("Connection is successful!!!");
            }
            else{
                System.out.println("Connection failed");
            }
        }
        catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            try{
                if(conn!=null){
                    conn.close();
                }
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}
