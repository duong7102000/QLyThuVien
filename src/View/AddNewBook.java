package View;

import Model.Book;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Controller.BookController.insertBook;

public class AddNewBook extends JDialog{
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JButton thêmSáchButton;
    private JPanel j1;

    public AddNewBook(JFrame parent){
        super(parent);
        setTitle("Signup");
        setContentPane(j1);
        setMinimumSize(new Dimension(500, 600));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        thêmSáchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String bookName = textField1.getText();
                String artist = textField2.getText();
                String content = textField3.getText();
                String major = textField4.getText();
                int total = Integer.parseInt(textField5.getText());
                int remain = Integer.parseInt(textField6.getText());
                Book book = new Book(bookName, artist, content, major, total, remain);
                if (insertBook(book)){
                    dispose();
                    JOptionPane.showMessageDialog(AddNewBook.this, "Thêm sách thành công!");
                }
            }
        });
        setVisible(true);
    }
}
