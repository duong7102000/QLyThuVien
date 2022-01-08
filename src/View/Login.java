package View;

import Model.Account;
import Model.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static Controller.AccountController.logIn;
import static Controller.StudentController.getAllStudent;

public class Login extends JDialog{
    private JPanel panel1;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton signInButton;
    private JButton signUpButton;
    private JPanel loginPanel;

    public Login(JFrame parent){
        super(parent);
        setTitle("Login");
        setContentPane(loginPanel);
        setMinimumSize(new Dimension(500, 600));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Account account = logIn(textField1.getText(), String.valueOf(passwordField1.getPassword()));
                if (account==null){
                    JOptionPane.showMessageDialog(Login.this, "Xin mời bạn nhập lại");
                }
                else if(account.getPosition().equals("student")){
                    Student student = null;
                    List<Student> listStudent = getAllStudent();
                    for (Student std:
                         listStudent) {
                        if (std.getUsername().equals(account.getUsername())){
                            student = std;
                            break;
                        }
                    }
                    dispose();
                    new StudentForm(null, student);
                }
                else if(account.getPosition().equals("librarian")){

                }
                else if(account.getPosition().equals("admin")){
                    dispose();
                    new SigninAdmin(null);
                }
            }
        });
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new Signup(null);
            }
        });
        setVisible(true);
    }

    public static void main(String[] args) {
        new Login(null);
    }
}
