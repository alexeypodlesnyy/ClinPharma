package database;

import java.sql.*;

/**
 * Created by araragi on 7/31/16.
 */
public class MainDbClass {


    private static final String url = "jdbc:mysql://localhost:3306/mydb";
    private static final String user = "root";
    private static final String password = "21301510";



    public static void main(String[] args) {



        querySelectAll();
        insertToCross(1, 3, "not comparable because they have the same active substance");
        System.out.println(selectFromCross(1,3));


    }

    public static void querySelectAll() {


        String query = "SELECT * FROM drugs";


        try (Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement = con.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
        ){

            while (resultSet.next()) {

                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String dep = resultSet.getString(3);

                System.out.println("-----" + id + "  " + name + "  " + dep + "-----");
            }


        }
        catch (SQLException sqlEx){
            sqlEx.printStackTrace();
        }
        catch (Exception ex) {
            ex.printStackTrace();

        }

    }

    public static String selectByDrug(String drug) {

        String query = "SELECT * FROM drugs WHERE drug=?";
        StringBuilder results = new StringBuilder(" ");

        try (Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, drug);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {


                while (resultSet.next()) {

                    results.append(resultSet.getString(2));
                    results.append("  ");
                    results.append(resultSet.getString(3));
                    results.append("\n");


                }
            }
        }catch (SQLException ex) {
            ex.printStackTrace();
        }catch (Exception ex){
            ex.printStackTrace();
        }


        return results.toString();

    }



    public static void queryInsert(String drugName, String drugDescription) {

        String query = "INSERT INTO mydb.drugs (drug, description) VALUES ('" +
                drugName + "', '" + drugDescription + "');";

        try (Connection connection = DriverManager.getConnection(url,user, password);
            PreparedStatement preparedStatement = connection.prepareStatement(query);

        ){

            preparedStatement.executeUpdate(query);


        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public static void queryDelete(String name) {
        String query = "DELETE FROM drugs WHERE drug=?";

        try (Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public static void queryUpdate(String oldDrugName, String newDrugName) {

        String query = "UPDATE drugs SET drug=? WHERE drug=?";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)){

            preparedStatement.setString(1, newDrugName);
            preparedStatement.setString(2, oldDrugName);

            int count = preparedStatement.executeUpdate();

            System.out.println(count + " units were updated");


        } catch (SQLException ex) {
            ex.printStackTrace();
        }  catch (Exception ex){
            ex.printStackTrace();
        }

    }

    public static void insertToCross(int idFirst, int idSecond, String compartability){

        String query = "INSERT INTO mydb.cross (id_first_drug, id_second_drug, crosscol) VALUES (" +
                idFirst + ", " + idSecond + ", '" + compartability + "');";

        try(Connection connection = DriverManager.getConnection(url,user, password);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
        ){
            preparedStatement.executeUpdate(query);
        }catch (SQLException sql){
            sql.printStackTrace();
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }
    public static String selectFromCross(int idFirst, int idSecond){

        String selection = "SELECT crosscol FROM mydb.cross WHERE id_first_drug=? AND id_second_drug=?";

        String res = "nothing";

        try(Connection connection = DriverManager.getConnection(url,user, password);
            PreparedStatement preparedStatement = connection.prepareStatement(selection);
        ){

            preparedStatement.setInt(1, idFirst);
            preparedStatement.setInt(2, idSecond);

            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    res = resultSet.getString(1);
                }
            }

        }catch (SQLException sql){
            sql.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }



        return res;
    }
    public static int getDrugId(String drug){

        String selection = "SELECT iddrug FROM drugs WHERE drug=?";
        int drugId=0;


        try(Connection connection = DriverManager.getConnection(url,user, password);
            PreparedStatement preparedStatement = connection.prepareStatement(selection)){

            preparedStatement.setString(1, drug);

            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
                    drugId = resultSet.getInt(1);
                }

            }
        }catch (SQLException sql){
            sql.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }

        return drugId;
    }

}
