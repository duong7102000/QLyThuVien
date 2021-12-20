package Controller;

import Model.CallCardDetail;
import Util.ConnectionDB;

import java.sql.*;
import java.util.List;

public class CallCardDetailController {
    public static List<CallCardDetail> getAllCallCardDetail(){
        Connection connection = ConnectionDB.openConnection();
        List<CallCardDetail> listCallCardDetail = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from tbl_chitietphieumuon");
            while (resultSet.next()){
                int callCardId = Integer.parseInt(resultSet.getString("id_phieumuon"));
                int bookId = Integer.parseInt(resultSet.getString("id_sach"));
                Date borrowDate = resultSet.getDate("date_borrow");
                Date returnDate = resultSet.getDate("date_return");
                float forfeit = Float.parseFloat(resultSet.getString("forfeit"));
                CallCardDetail callCardDetail = new CallCardDetail(callCardId, bookId, borrowDate, returnDate, forfeit);
                listCallCardDetail.add(callCardDetail);
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return listCallCardDetail;
    }

    public static boolean insertCallCardDetail(CallCardDetail callCardDetail){
        boolean check = false;
        int callCardId = callCardDetail.getCallCardId();
        int bookId = callCardDetail.getBookId();
        Date borrowDate = callCardDetail.getBorrowDate();
        Date returnDate = callCardDetail.getReturnDate();
        float forfeit = callCardDetail.getForfeit();
        if (!BookController.borrowBook(bookId)) return false;
        Connection connection = ConnectionDB.openConnection();
        try{
            CallableStatement callableStatement = connection.prepareCall(String.format("insert into tbl_chitietphieumuon values (%d, %d, \'%s\', \'%s\', %f)", callCardId, bookId, borrowDate.toString(), returnDate.toString(), forfeit));
            check = !callableStatement.execute();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return check;
    }
}
