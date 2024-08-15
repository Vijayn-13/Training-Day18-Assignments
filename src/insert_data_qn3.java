import java.math.BigDecimal;
import java.sql.*;
import java.util.Scanner;

public class insert_data_qn3 {
    private static final String URL="jdbc:postgresql://localhost:5432/inventory_db";
    private static final String USER="postgres";
    private static final String PASSWORD="postgresSql";
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        Connection conn=null;
        PreparedStatement pt=null;
        try{
            Class.forName("org.postgresql.Driver");
            conn=DriverManager.getConnection(URL,USER,PASSWORD);
            String st="Insert into products values(?,?,?,?)";
            pt=conn.prepareStatement(st);
            System.out.print("Enter product Id: ");
            int id=sc.nextInt();
            System.out.print("Enter product name: ");
            String name=sc.next();
            System.out.print("Enter price: ");
            BigDecimal price=sc.nextBigDecimal();
            System.out.print("Enter quantity: ");
            int quantity=sc.nextInt();
            pt.setInt(1,id);
            pt.setString(2,name);
            pt.setBigDecimal(3, price);
            pt.setInt(4,quantity);
            int n=pt.executeUpdate();
            System.out.println("Number of rows inserted: "+n);
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
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}
