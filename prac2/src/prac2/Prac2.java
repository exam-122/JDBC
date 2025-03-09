import java.sql.*;
import java.util.Scanner;

public class Prac2{

         private static final String URL = "jdbc:mysql://localhost:3306/demo";
         private static final String USER = "root";
         private static final String PASS = "";

    public static void main(String[] args) {
        try(Connection con = DriverManager.getConnection(URL, USER,PASS)){
            System.out.println("Connected to the database");

            String createTableSQL = "CREATE TABLE IF NOT EXISTS student ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY, "
                    + "username VARCHAR(50) NOT NULL, "
                    + "password VARCHAR(50) NOT NULL)";
            try (Statement stmt = con.createStatement()) {
                stmt.execute(createTableSQL);
                System.out.println("Table 'student' is ready.");
            } catch (SQLException e) {
                e.printStackTrace();
            }


            Scanner sc = new Scanner(System.in);

            while(true){
                System.out.println("Choose an option");
                System.out.println("1. Insert");
                System.out.println("2. Update");
                System.out.println("3. Retrieve");
                System.out.println("4. Delete");
                System.out.println("5. Exit");

                int choice = sc.nextInt();

                sc.nextLine();

                switch (choice){
                    case 1:
                        System.out.println("Insert option selected");
                        System.out.println("Enter username");
                        String usernameInsert = sc.nextLine();
                        System.out.println("Enter password");
                        String passwordInsert = sc.nextLine();
                        insert(con, usernameInsert,passwordInsert);
                        break;
                    case 2:
                        System.out.println("Update option selected");
                        System.out.println("Enter username to update");
                        String usernameUpdate = sc.nextLine();
                        System.out.println("Enter password to update");
                        String passwordUpdate = sc.nextLine();
                        update(con, usernameUpdate,passwordUpdate);
                        break;
                    case 3:
                        System.out.println("Retreive  selected");
                        retrieve(con);
                        break;
                    case 4:
                        System.out.println("Delete option selected");
                        System.out.println("Enter id to delete");
                        String id = sc.nextLine();
                        delete(con, id);
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        sc.close();
                        return;
                    default:
                        System.out.println("Select correct option");
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void insert(Connection con, String username, String password){
        String sql = "INSERT INTO student (username, password) VALUES (?, ?)";
        try(PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, username);
            ps.setString(2,password);
            int rowInserted = ps.executeUpdate();

            if(rowInserted > 0){
                System.out.println("Inserted");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void update(Connection con, String username, String newPassword){
        String sql = "update student set password = ? where username = ?";
        try(PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1,newPassword);
            ps.setString(2,username);
            int rowInserted = ps.executeUpdate();
            if(rowInserted > 0){
                System.out.println("Updated");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void retrieve(Connection con){
        String sql = "select * from student";
        try(PreparedStatement ps = con.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String username = rs.getString("username");
                String password = rs.getString("password");
                System.out.println("Username: "+ username + ", Password: "+ password);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void delete(Connection con, String id){
        String sql = "delete from student where id = ?";
        try(PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, id);
            int rowInserted = ps.executeUpdate();
            if(rowInserted > 0){
                System.out.println("Deleted");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
