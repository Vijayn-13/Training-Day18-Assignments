import java.math.BigDecimal;
import java.sql.*;
import java.util.Scanner;

public class updateData_qn5 {
    private static final String URL="jdbc:postgresql://localhost:5432/school_db";
    private static final String USER="postgres";
    private static final String PASSWORD="postgresSql";
    public static void main(String[] args){
        Connection conn=null;
        PreparedStatement pt=null;
        ResultSet rs=null;
        Scanner sc=new Scanner(System.in);
        try{
            Class.forName("org.postgresql.Driver");
            conn=DriverManager.getConnection(URL,USER,PASSWORD);
            conn.setAutoCommit(false);
            String retrieve="Select*from students";
            pt=conn.prepareStatement(retrieve);
            rs=pt.executeQuery();
            System.out.println("Student Details:-");
            while(rs.next()){
                int id=rs.getInt("stud_id");
                String name=rs.getString("name");
                double grade=rs.getDouble("grade");
                System.out.println("\nStudent ID: "+id+"\nName: "+name+"\nGrade: "+grade);
            }
            System.out.print("\nEnter student ID for updating grade: ");
            int id=sc.nextInt();
            System.out.print("Enter grade to update(out of 10): ");
            double grade=sc.nextDouble();
            String update="Update students set grade=? where stud_ID=?";
            pt=conn.prepareStatement(update);
            pt.setDouble(1,grade);
            pt.setInt(2,id);
            pt.executeUpdate();
            conn.commit();
            System.out.println("Grade updated successfully!!!");
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
            try {
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
            catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
