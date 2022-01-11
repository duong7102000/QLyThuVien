package View;

import Model.Account;
import Model.Librarian;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import static Controller.AccountController.insertAccount;
import static Controller.LibrarianController.insertLibrarian;

public class SigninAdmin extends JDialog{
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton createButton;
    private JButton cancelButton;
    private JTextField textField4;
    private JPanel SigninAdmin;

    public SigninAdmin(JFrame parent){
        super(parent);
        setTitle("Signup");
        setContentPane(SigninAdmin);
        setMinimumSize(new Dimension(500, 600));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Account account = new Account(textField1.getText(), textField2.getText(), "librarian");
                if (!insertAccount(account)){
                    JOptionPane.showMessageDialog(SigninAdmin.this, "Xin mời bạn nhập Username khác");
                }
                else {
                    Date date = Date.valueOf(textField4.getText());
                    Librarian librarian = new Librarian(textField1.getText(), textField3.getText(), date);
                    insertLibrarian(librarian);
                    JOptionPane.showMessageDialog(SigninAdmin.this, "Đăng ký thành công!");
                    dispose();
                    new Login(null);
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
                new Login(null);
            }
        });
        setVisible(true);
    }
}
