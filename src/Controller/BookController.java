package Controller;

import Model.Book;
import Util.ConnectionDB;

import java.sql.*;
import java.util.List;

public class BookController {
    public static List<Book> getAllBook(){
        Connection connection = ConnectionDB.openConnection();
        List<Book> listBook = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from tbl_sach");
            while (resultSet.next()){
                int id = Integer.parseInt(resultSet.getString("id"));
                String name = resultSet.getString("name");
                String artist = resultSet.getString("artist");
                String content = resultSet.getString("content");
                String major = resultSet.getString("major");
                int total = Integer.parseInt(resultSet.getString("total"));
                int remain = Integer.parseInt(resultSet.getString("remain"));
                Book book = new Book(id, name, artist, content, major, total, remain);
                listBook.add(book);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        finally {
            ConnectionDB.closeConnection(connection);
        }
        return listBook;
    }
    public static Book getBookById(int bookId){
        Connection connection = ConnectionDB.openConnection();
        Book book = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(String.format("select * from tbl_sach where id = %d", bookId));
            if (resultSet.next()){
                String name = resultSet.getString("name");
                String artist = resultSet.getString("artist");
                String content = resultSet.getString("content");
                String major = resultSet.getString("major");
                int total = Integer.parseInt(resultSet.getString("total"));
                int remain = Integer.parseInt(resultSet.getString("remain"));
                book = new Book(bookId, name, artist, content, major, total, remain);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return book;
    }
    public static boolean updateBook(Book book){
        Connection connection = ConnectionDB.openConnection();
        boolean check = false;
        int id = book.getId();
        String name = book.getName();
        String artist = book.getArtist();
        String content = book.getContent();
        String major = book.getMajor();
        int total = book.getTotal();
        int remain = book.getRemain();
        try {
            CallableStatement callableStatement = connection.prepareCall(String.format("update tbl_sach set name = N\'%s\', artist = N\'%s\', content = N\'%s\', major = N\'%s\', total = %d, remain = %d where id = %d", name, artist, content, major, total, remain, id));
            check = !callableStatement.execute();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return check;
    }
    public static boolean deleteBookById(int bookId){
        Connection connection = ConnectionDB.openConnection();
        boolean check = false;
        try {
            CallableStatement callableStatement = connection.prepareCall(String.format("delete from tbl_sach where id = %d", bookId));
            check = !callableStatement.execute();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return check;
    }
    public static boolean insertBook(Book book){
        Connection connection = ConnectionDB.openConnection();
        boolean check = false;
        List<Book> listBook = BookController.getAllBook();
        if (listBook == null) book.setId(1);
        else {
            Book bookLast = listBook.get(listBook.size() - 1);
            book.setId(bookLast.getId() + 1);
        }
        int id = book.getId();
        String name = book.getName();
        String artist = book.getArtist();
        String content = book.getContent();
        String major = book.getMajor();
        int total = book.getTotal();
        int remain = book.getRemain();
        try {
            CallableStatement callableStatement = connection.prepareCall(String.format("insert into tbl_sach values (%d, N\'%s\', N\'%s\', N\'%s\', N\'%s\', %d, %d)", id, name, artist, content, major, total, remain));
            check = !callableStatement.execute();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return check;
    }

    public static boolean borrowBook(int bookId){
        Book book = BookController.getBookById(bookId);
        if (book == null) return false;
        if (book.getRemain() == 0) return false;
        int remain = book.getRemain() - 1;
        book.setRemain(remain);
        return BookController.updateBook(book);
    }

    public static boolean giveBackBook(int bookId){
        Book book = BookController.getBookById(bookId);
        if (book.getRemain() >= book.getTotal()) return false;
        int remain = book.getRemain() + 1;
        book.setRemain(remain);
        return BookController.updateBook(book);
    }

    public static boolean addBook(int numberBook, int bookId){
        Book book = BookController.getBookById(bookId);
        int remain = book.getRemain() + numberBook;
        book.setRemain(remain);
        int total = book.getTotal() + numberBook;
        book.setTotal(total);
        return BookController.updateBook(book);
    }

    public static void main(String[] args) {
        Book book = new Book(1, "Giải tích 2", "Giáo sư A", "Sách giải tích cơ bản", "Toán học", 50, 50);
        System.out.println(BookController.addBook(1, 1));
    }
}
