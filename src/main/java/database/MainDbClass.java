package database;

import java.sql.*;

/**
 * Created by araragi on 7/31/16.
 */
public class MainDbClass {


    private static final String url = "jdbc:mysql://localhost:3306/mydb";
    private static final String user = "root";
    private static final String password = "21301510";

    private static Connection conn;
    private static ResultSet rs;
    private static PreparedStatement preparedStatement;


    public static void main(String[] args) {

        querySelectAll();


    }

    public static void querySelectAll() {


        String query = "SELECT * FROM table1";

        try {
            conn = DriverManager.getConnection(url, user, password);

            preparedStatement = conn.prepareStatement(query);

            //preparedStatement.setString(1, "hirurgiya");

            rs = preparedStatement.executeQuery();

            while (rs.next()) {

                String name = rs.getString(2);
                String dep = rs.getString(3);

                System.out.println("-----" + name + "  " + dep + "-----");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (Exception ex) {
                System.out.println("cant close connection");
            }
            try {
                preparedStatement.close();
            } catch (Exception ex) {
                System.out.println("cant close pre[ared statement");
            }
            try {
                rs.close();
            } catch (Exception ex) {
                System.out.println("cant close result set");
            }


        }

    }


    public static void queryIncert(String name, String dep) {

        String query = "INSERT INTO mydb.table1 (table1colname, table1coldepartment) VALUES ('" +
                name + "', '" + dep + "');";

        try {

            conn = DriverManager.getConnection(url, user, password);

            preparedStatement = conn.prepareStatement(query);

            preparedStatement.executeUpdate(query);


        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {

            try {
                preparedStatement.close();
            } catch (Exception ex) {
                System.out.println("cant close prepared statement");
            }
            try {
                conn.close();
            } catch (Exception ex) {
                System.out.println("cant close connection");
            }


        }
    }

    public static void queryDelete(String name) {
        String query = "DELETE FROM table1 WHERE table1colname=?";

        try {

            conn = DriverManager.getConnection(url, user, password);

            preparedStatement = conn.prepareStatement(query);

            preparedStatement.setString(1, name);

            int count = preparedStatement.executeUpdate();

            System.out.println(count + " units were deleted");


        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {

            try {
                preparedStatement.close();
            } catch (Exception ex) {
                System.out.println("cant close prepared statement");
            }
            try {
                conn.close();
            } catch (Exception ex) {
                System.out.println("cant close connection");
            }


        }
    }

    public static void queryUpdate(int id) {

        String query = "UPDATE table1 SET table1coldepartment=? WHERE table1coldepartment=?";

        try {

            conn = DriverManager.getConnection(url, user, password);

            preparedStatement = conn.prepareStatement(query);


            preparedStatement.setString(1, "endohirurgiya");
            preparedStatement.setString(2, "hirurgiya");

            int count = preparedStatement.executeUpdate();

            System.out.println(count + " units were updated");


        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {

            try {
                preparedStatement.close();
            } catch (Exception ex) {
                System.out.println("cant close prepared statement");
            }
            try {
                conn.close();
            } catch (Exception ex) {
                System.out.println("cant close connection");
            }

        }


    }

}
