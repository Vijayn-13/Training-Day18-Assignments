import java.sql.*;
public class retrieve_records_qn2 {
    private static final String URL="jdbc:postgresql://localhost:5432/company_db";
    private static final String USER="postgres";
    private static final String PASSWORD="postgresSql";
    public static void main(String[] args){
        Connection conn=null;
        PreparedStatement pt=null;
        ResultSet rs=null;
        try{
            Class.forName("org.postgresql.Driver");
            conn=DriverManager.getConnection(URL,USER,PASSWORD);
            String query="Select*from employees";
            pt=conn.prepareStatement(query);
            rs=pt.executeQuery();
            while(rs.next()){
                int id=rs.getInt("emp_id");
                String fname=rs.getString("first_name");
                String lname=rs.getString("last_name");
                String email=rs.getString("email");
                String contact=rs.getString("contact_number");
                System.out.println("\nEmployee ID: "+id+"\nFirstname: "+fname+"\nLastname: "+lname+"\nEmail: "+email+"\nContact number: "+contact);
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
                if(pt!=null){
                    pt.close();
                }
                if(rs!=null){
                    rs.close();
                }
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}
