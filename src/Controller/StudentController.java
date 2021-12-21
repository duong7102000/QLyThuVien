package Controller;
import Model.Book;
import Model.Student;
import Util.ConnectionDB;
import Util.NormalizeString;

import java.sql.*;
import java.util.List;

public class StudentController {
    public static List<Student> getAllStudent(){
        Connection connection = ConnectionDB.openConnection();
        List<Student> listStudent = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from tbl_sinhvien");
            while (resultSet.next()){
                String username = resultSet.getString("username");
                String fullName = resultSet.getString("fullname");
                Date birth = resultSet.getDate("birth");
                String address = resultSet.getString("address");
                String major = resultSet.getString("major");
                String class_ = resultSet.getString("class");
                Student student = new Student(username, fullName, birth, address, major, class_);
                listStudent.add(student);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        finally {
            ConnectionDB.closeConnection(connection);
        }
        return listStudent;
    }

    public static boolean insertStudent(Student student){
        Connection connection = ConnectionDB.openConnection();
        boolean check = false;
        String username = student.getUsername();
        String fullName = student.getFullName();
        Date birth = student.getBirth();
        String address = student.getAddress();
        String major = student.getMajor();
        String class_ = student.getClass_();
        try {
            CallableStatement callableStatement = connection.prepareCall(String.format("insert into tbl_sinhvien values (N\'%s\', \'%s\', N\'%s\', N\'%s\', N\'%s\', \'%s\')", fullName, birth.toString(), address, major, class_, username));
            check = !callableStatement.execute();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return check;
    }

    public static boolean updateStudent(Student student){
        Connection connection = ConnectionDB.openConnection();
        boolean check = false;
        String username = student.getUsername();
        String fullName = student.getFullName();
        Date birth = student.getBirth();
        String address = student.getAddress();
        String major = student.getMajor();
        String class_ = student.getClass_();
        try {
            CallableStatement callableStatement = connection.prepareCall(String.format("update tbl_sinhvien set fullname = N\'%s\', birth = \'%s\', address = N\'%s\', major = N\'%s\', class = N\'%s\' where username = \'%s\'", fullName, birth.toString(), address, major, class_, username));
            check = !callableStatement.execute();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return check;
    }

    public static List<Student> searchStudentByName(String studentName){
        List<Student> listStudentResult = null;
        List<Student> listStudent = StudentController.getAllStudent();
        for (Student student:
                listStudent) {
            if (NormalizeString.normalizeSearchString(student.getFullName()).contains(NormalizeString.normalizeSearchString(studentName))){
                listStudentResult.add(student);
            }
        }
        return listStudentResult;
    }

    public static void main(String[] args) {
        Date date = Date.valueOf("2000-10-7");
        Student student = new Student("duongdt", "Đào Tùng Dương 1", date, "Hải Dương", "Khoa học máy tính", "IT1-04-K63");
        System.out.println(StudentController.updateStudent(student));
    }
}
