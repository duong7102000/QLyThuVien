package View;

import Model.Account;
import Model.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.sql.Date;

import static Controller.AccountController.logIn;

public class StudentForm extends JDialog{
    private JPanel studentForm;
    private JTabbedPane tabbedPane1;
    private JButton thayĐổiThôngTinButton;
    private JButton đăngXuấtButton;
    private JLabel welCome;
    private JLabel studentName;

    public StudentForm(JFrame parent, Student student){
        super(parent);
        setTitle(student.getFullName());
        setContentPane(studentForm);
        setMinimumSize(new Dimension(750, 800));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        welCome.setText("Welcome");
        studentName.setText(student.getFullName());
        đăngXuấtButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
                new Login(null);
            }
        });
        thayĐổiThôngTinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
                new UpdateInfoStudent(null, student);
            }
        });
        setVisible(true);
    }

    public static void main(String[] args) {
        Student student = new Student("duongdt", "Duong", Date.valueOf("2000-10-07"), "HD", "M1", "C1");
        new StudentForm(null, student);
    }
}
