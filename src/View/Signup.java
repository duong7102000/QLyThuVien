package View;

import Model.Account;
import Model.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import static Controller.AccountController.insertAccount;
import static Controller.StudentController.insertStudent;

public class Signup extends JDialog{
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JButton signUpButton;
    private JButton cancelButton;
    private JPanel signUpPanel;
    private JTextField textField0;

    public Signup(JFrame parent) {
        super(parent);
        setTitle("Signup");
        setContentPane(signUpPanel);
        setMinimumSize(new Dimension(500, 600));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Account account = new Account(textField0.getText(), textField1.getText(), "student");
                if (!insertAccount(account)){
                    JOptionPane.showMessageDialog(Signup.this, "Xin mời bạn nhập Username khác");
                }
                else{
                    Date date = Date.valueOf(textField3.getText());
                    Student student = new Student(textField0.getText(), textField2.getText(), date, textField4.getText(), textField5.getText(), textField6.getText());
                    insertStudent(student);
                    JOptionPane.showMessageDialog(Signup.this, "Đăng ký thành công!");
                    dispose();
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
            }
        });
        setVisible(true);
    }

    public static void main(String[] args) {
        Signup a = new Signup(null);
    }
}
