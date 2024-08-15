import java.math.BigDecimal;
import java.sql.*;
public class transaction_qn4 {
    private static final String URL="jdbc:postgresql://localhost:5432/bank_db";
    private static final String USER="postgres";
    private static final String PASSWORD="postgresSql";
    public static void main(String[] args){
        Connection conn=null;
        PreparedStatement pt1=null;
        PreparedStatement pt2=null;
        try{
            Class.forName("org.postgresql.Driver");
            conn=DriverManager.getConnection(URL,USER,PASSWORD);
            conn.setAutoCommit(false);
            String query1_withdraw="Update accounts set amount=amount-? where acc_number=?";
            String query2_deposit="Update accounts set amount=amount+? where acc_number=?";
            pt1=conn.prepareStatement(query1_withdraw);
            pt2=conn.prepareStatement(query2_deposit);
            pt1.setBigDecimal(1,new BigDecimal(2500));
            pt1.setInt(2,1);
            pt2.setBigDecimal(1,new BigDecimal(2500));
            pt2.setInt(2,2);
            pt1.executeUpdate();
            pt2.executeUpdate();
            conn.commit();
            System.out.println("Amount transferred successfully!!!");
        }
        catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        catch (SQLException e) {
            if(conn!=null){
                try{
                    conn.rollback();
                    System.out.println("Rollback happened due to an error...");
                }
                catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        }
        finally {
            try{
                if(conn!=null){
                    conn.close();
                }
                if(pt1!=null){
                    pt1.close();
                }
                if(pt2!=null){
                    pt2.close();
                }
            }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
