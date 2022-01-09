package Controller;
import Model.Librarian;
import Util.ConnectionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibrarianController {
    public static List<Librarian> getAllLibrarian(){
        Connection connection = ConnectionDB.openConnection();
        List<Librarian> listLibrarian = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from tbl_thuthu");
            while (resultSet.next()){
                String username = resultSet.getString("username");
                String name = resultSet.getString("name");
                Date birth = resultSet.getDate("birth");
                Librarian librarian = new Librarian(username, name, birth);
                listLibrarian.add(librarian);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        finally {
            ConnectionDB.closeConnection(connection);
        }
        return listLibrarian;
    }

    public static boolean insertLibrarian(Librarian librarian){
        Connection connection = ConnectionDB.openConnection();
        boolean check = false;
        String name = librarian.getName();
        Date birth = librarian.getBirth();
        String username = librarian.getUsername();
        try {
            CallableStatement callableStatement = connection.prepareCall(String.format("insert into tbl_thuthu values (N\'%s\', \'%s\', \'%s\')", name, birth.toString(), username));
            check = !callableStatement.execute();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return check;
    }

    public static boolean updateLibrarian(Librarian librarian){
        Connection connection = ConnectionDB.openConnection();
        boolean check = false;
        String username = librarian.getUsername();
        String name = librarian.getName();
        Date birth = librarian.getBirth();
        try {
            CallableStatement callableStatement = connection.prepareCall(String.format("update tbl_thuthu set name = N\'%s\', birth = \'%s\', where username = \'%s\'", name, birth.toString(), username));
            check = !callableStatement.execute();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return check;
    }

    public static void main(String[] args) {
        Date date = Date.valueOf("2000-07-10");
        Librarian librarian = new Librarian("namnv", "Nguyễn Văn Nam", date);
        System.out.println(LibrarianController.insertLibrarian(librarian));
    }
}
