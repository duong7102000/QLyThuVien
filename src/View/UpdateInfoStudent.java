package View;

import Model.Account;
import Model.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import static Controller.AccountController.getAccountByUsername;
import static Controller.StudentController.updateStudent;

public class UpdateInfoStudent extends JDialog{
    private JPanel updateInfoPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JButton updateButton;
    private JButton cancelButton;

    public UpdateInfoStudent(JFrame parent, Student student) {
        super(parent);
        setTitle("Update info");
        setContentPane(updateInfoPanel);
        setMinimumSize(new Dimension(500, 600));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        textField1.setText(getAccountByUsername(student.getUsername()).getPassword());
        textField2.setText(student.getFullName());
        textField3.setText(student.getBirth().toString());
        textField4.setText(student.getAddress());
        textField5.setText(student.getMajor());
        textField6.setText(student.getClass_());
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Date date = Date.valueOf(textField3.getText());
                String userName = student.getUsername();
                Student student = new Student(userName, textField2.getText(), date, textField4.getText(), textField5.getText(), textField6.getText());
                Student student1 = updateStudent(student);
                if (student1 != null){
                    JOptionPane.showMessageDialog(UpdateInfoStudent.this, "Cập nhật thành công");
                    dispose();
                    new StudentForm(null, student1);
                }
                else {
                    JOptionPane.showMessageDialog(UpdateInfoStudent.this, "Cập nhật không thành công");
                    dispose();
                    new StudentForm(null, student);
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
                new StudentForm(null, student);
            }
        });
        setVisible(true);
    }

    public static void main(String[] args) {
        Date date = Date.valueOf("2000-10-7");
        Student student = new Student("duongdt", "Đào Tùng Dương", date, "Hải Dương", "Khoa học máy tính", "IT1-04-K63");
        new UpdateInfoStudent(null, student);
    }
}
