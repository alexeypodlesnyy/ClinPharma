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


        queryIncert("analgin", "nobody uses it now");
        queryIncert("dimedrol", "good old first generation");
        queryIncert("baralgin", "the same old metamisol natrium");

        querySelectAll();


    }

    public static void querySelectAll() {


        String query = "SELECT * FROM drugs";

        try {
            conn = DriverManager.getConnection(url, user, password);
            preparedStatement = conn.prepareStatement(query);
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

    public static String selectByDrug(String drug) {


        String query = "SELECT * FROM drugs WHERE drug=?";
        StringBuilder results = new StringBuilder(" ");

        try {
            conn = DriverManager.getConnection(url, user, password);
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, drug);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {

                results.append(rs.getString(2));
                results.append("  ");
                results.append(rs.getString(3));
                results.append("\n");


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

        return results.toString();

    }



    public static void queryIncert(String drugName, String drugDescription) {

        String query = "INSERT INTO mydb.drugs (drug, description) VALUES ('" +
                drugName + "', '" + drugDescription + "');";

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
        String query = "DELETE FROM drugs WHERE drug=?";

        try {

            conn = DriverManager.getConnection(url, user, password);
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();

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

    public static void queryUpdate(String oldDrugName, String newDrugName) {

        String query = "UPDATE drugs SET drug=? WHERE drug=?";

        try {

            conn = DriverManager.getConnection(url, user, password);

            preparedStatement = conn.prepareStatement(query);


            preparedStatement.setString(1, newDrugName);
            preparedStatement.setString(2, oldDrugName);

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
