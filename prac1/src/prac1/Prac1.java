import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Prac1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter username");
        String un = sc.next();

        System.out.println("Enter password");
        String pass = sc.next();

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo","root","");
            Statement stm = con.createStatement();
            String Query = "select * from student WHERE username='"+un+"'AND password='"+pass+"'";

            ResultSet rs = stm.executeQuery(Query);

            rs.next();
            if(rs.getRow()>0){
                System.out.println("Login Successfull");
            }else{
                System.out.println("Login fialed");
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
}