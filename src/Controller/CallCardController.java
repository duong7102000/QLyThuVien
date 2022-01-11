package Controller;

import Model.CallCard;
import Util.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CallCardController {
    public static List<CallCard> getAllCallCard(){
        Connection connection = ConnectionDB.openConnection();
        List<CallCard> listCallCard = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from tbl_phieumuon");
            while (resultSet.next()){
                int id = resultSet.getInt("id_phieumuon");
                String studentUsername = resultSet.getString("username_sinhvien");
                String librarianUsername = resultSet.getString("username_thuthu");
                Date startDate = resultSet.getDate("date_start");
                Date endDate = resultSet.getDate("date_end");
                float deposit = resultSet.getFloat("deposit");
                CallCard callCard = new CallCard(id, studentUsername, librarianUsername, startDate, endDate, deposit);
                listCallCard.add(callCard);
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return listCallCard;
    }

    public static int insertCallCard(CallCard callCard){
        boolean check = false;
        Connection connection = ConnectionDB.openConnection();
        List<CallCard> listCallCard = CallCardController.getAllCallCard();
        if (listCallCard == null) callCard.setId(1);
        else callCard.setId(listCallCard.get(listCallCard.size()-1).getId() + 1);
        int id = callCard.getId();
        String librarianUsername = callCard.getLibrarianUsername();
        String studentUsername = callCard.getStudentUsername();
        Date startDate = callCard.getStartDate();
        Date endDate = callCard.getEndDate();
        Float deposit = callCard.getDeposit();
        try{
            CallableStatement callableStatement = connection.prepareCall(String.format("insert into tbl_phieumuon values (%d, \'%s\', \'%s\', \'%s\', \'%s\', %f)", id, studentUsername, librarianUsername, startDate.toString(), endDate.toString(), deposit));
            check = !callableStatement.execute();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return id;
    }

    public static void updateCallCard(CallCard callCard){
        Connection connection = ConnectionDB.openConnection();
        int id = callCard.getId();
        String librarianUsername = callCard.getLibrarianUsername();
        String studentUsername = callCard.getStudentUsername();
        Date startDate = callCard.getStartDate();
        Date endDate = callCard.getEndDate();
        Float deposit = callCard.getDeposit();
        try{
            CallableStatement callableStatement = connection.prepareCall(String.format("update tbl_phieumuon set username_sinhvien = \'%s\', username_thuthu = \'%s\', date_start = \'%s\', date_end = \'%s\', deposit = %f where id_phieumuon = %d", studentUsername, librarianUsername, startDate.toString(), endDate.toString(), deposit, id));
            callableStatement.execute();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection);
        }
    }

    public static void main(String[] args) {
        Date startDate = Date.valueOf("2021-12-20");
        Date endDate = Date.valueOf("2021-12-27");
        CallCard callCard = new CallCard("duongdt", "namnv", startDate, endDate, 20000);
        System.out.println(CallCardController.insertCallCard(callCard));
    }
}
