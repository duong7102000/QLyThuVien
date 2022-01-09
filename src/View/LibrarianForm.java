package View;

import Model.Account;
import Model.Book;
import Model.Librarian;
import Model.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import static Controller.AccountController.insertAccount;
import static Controller.BookController.*;
import static Controller.StudentController.*;
import static Util.ExportToCSV.exportToCSV;
import static Util.ReadCSV.csvListBook;
import static Util.ReadCSV.csvListStudent;

public class LibrarianForm extends JDialog{
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JLabel librarianName;
    private JButton thayĐổiThôngTinButton;
    private JButton đăngXuấtButton;
    private JTable table1;
    private JTextField bookNameSearch;
    private JButton searchButton1;
    private JButton thêmSáchButton;
    private JButton xuấtSáchRaFileButton;
    private JButton xóaSáchButton;
    private JButton thêmSáchBằngFileButton;
    private JTable table2;
    private JTextField textField2;
    private JButton searchButton;
    private JButton xuấtDanhSáchĐộcButton;
    private JButton thêmĐộcGiảBằngButton;

    public LibrarianForm(JFrame parent, Librarian librarian){
        super(parent);
        setTitle(librarian.getName());
        setContentPane(panel1);
        setMinimumSize(new Dimension(1500, 800));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        librarianName.setText(librarian.getName());
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
                new UpdateInfoLibrarian(null);
            }
        });
        DefaultTableModel defaultTableModel1 = new DefaultTableModel();
        table1.setModel(defaultTableModel1);
        defaultTableModel1.addColumn("Id");
        defaultTableModel1.addColumn("Tên sách");
        defaultTableModel1.addColumn("Tác giả");
        defaultTableModel1.addColumn("Nội dung");
        defaultTableModel1.addColumn("Chuyên ngành");
        defaultTableModel1.addColumn("Tổng số sách");
        defaultTableModel1.addColumn("Sách còn lại");
        List<Book> listBook = getAllBook();
        for (Book book:
                listBook) {
            int id = book.getId();
            String name = book.getName();
            String artist = book.getArtist();
            String content = book.getContent();
            String major = book.getMajor();
            int total = book.getTotal();
            int remain = book.getRemain();
            Object[] row = new Object[]{id, name, artist, content, major, total, remain};
            defaultTableModel1.addRow(row);
        }
        searchButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String bookSearch = bookNameSearch.getText();
                List<Book> listBook = searchBookByName(bookSearch);
                if (listBook.size()==0){
                    JOptionPane.showMessageDialog(LibrarianForm.this, "Không có sách bạn tìm kiếm!");
                }
                else{
                    int rows = defaultTableModel1.getRowCount();
                    for(int i = rows - 1; i >=0; i--)
                    {
                        defaultTableModel1.removeRow(i);
                    }
                    for (Book book:
                            listBook) {
                        int id = book.getId();
                        String name = book.getName();
                        String artist = book.getArtist();
                        String content = book.getContent();
                        String major = book.getMajor();
                        int total = book.getTotal();
                        int remain = book.getRemain();
                        Object[] row = new Object[]{id, name, artist, content, major, total, remain};
                        defaultTableModel1.addRow(row);
                    }
                }
            }
        });
        thêmSáchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new AddNewBook(null);
            }
        });
        xuấtSáchRaFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String time = String.valueOf(new Timestamp(System.currentTimeMillis()));
                exportToCSV(table1, "/home/duong/IdeaProjects/QLyThuVien/ExportFile/AllBook"+time+".csv");
                JOptionPane.showMessageDialog(LibrarianForm.this, "Đã xuất file csv ra folder ExportFile!");
            }
        });
        xóaSáchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int bookId = Integer.parseInt(JOptionPane.showInputDialog("Mời bạn nhập Id sách muốn xóa:"));
                if (deleteBookById(bookId)){
                    JOptionPane.showMessageDialog(LibrarianForm.this, "Xóa sách thành công!");
                }
                else {
                    JOptionPane.showMessageDialog(LibrarianForm.this, "Không thể xóa sách do có bạn đọc đang mượn!");
                }
            }
        });
        thêmSáchBằngFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser jFileChooser = new JFileChooser();
                jFileChooser.showDialog(LibrarianForm.this, "Mời bạn chọn file csv!");
                File f = jFileChooser.getSelectedFile();
                String path = f.getAbsolutePath();
                List<Book> listBook = csvListBook(path);
                for (Book book:
                     listBook) {
                    insertBook(book);
                }
            }
        });
        DefaultTableModel defaultTableModel2 = new DefaultTableModel();
        table2.setModel(defaultTableModel2);
        defaultTableModel2.addColumn("Họ và tên");
        defaultTableModel2.addColumn("Ngày sinh");
        defaultTableModel2.addColumn("Địa chỉ");
        defaultTableModel2.addColumn("Chuyên ngành");
        defaultTableModel2.addColumn("Lớp");
        defaultTableModel2.addColumn("Username");
        List<Student> listStudent = getAllStudent();
        for (Student std:
                listStudent) {
            String fullname = std.getFullName();
            String date = std.getBirth().toString();
            String address = std.getAddress();
            String major = std.getMajor();
            String class_ = std.getClass_();
            String username = std.getUsername();
            Object[] row = new Object[]{fullname, date, address, major, class_, username};
            defaultTableModel2.addRow(row);
        }
        xuấtDanhSáchĐộcButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String time = String.valueOf(new Timestamp(System.currentTimeMillis()));
                exportToCSV(table2, "/home/duong/IdeaProjects/QLyThuVien/ExportFile/AllStudent"+time+".csv");
                JOptionPane.showMessageDialog(LibrarianForm.this, "Đã xuất file csv ra folder ExportFile!");
            }
        });
        thêmĐộcGiảBằngButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser jFileChooser = new JFileChooser();
                jFileChooser.showDialog(LibrarianForm.this, "Mời bạn chọn file csv!");
                File f = jFileChooser.getSelectedFile();
                String path = f.getAbsolutePath();
                List<Student> listStudent = csvListStudent(path);
                for (Student std:
                        listStudent) {
                    try{
                        insertAccount(new Account(std.getUsername(), "", "student"));
                        insertStudent(std);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String stdSearch = textField2.getText();
                List<Student> listStd = searchStudentByName(stdSearch);
                if (listStd.size()==0){
                    JOptionPane.showMessageDialog(LibrarianForm.this, "Không có độc giả bạn tìm kiếm!");
                }
                else{
                    int rows = defaultTableModel2.getRowCount();
                    for(int i = rows - 1; i >=0; i--)
                    {
                        defaultTableModel2.removeRow(i);
                    }
                    for (Student std:
                            listStd) {
                        String fullname = std.getFullName();
                        String date = std.getBirth().toString();
                        String address = std.getAddress();
                        String major = std.getMajor();
                        String class_ = std.getClass_();
                        String username = std.getUsername();
                        Object[] row = new Object[]{fullname, date, address, major, class_, username};
                        defaultTableModel2.addRow(row);
                    }
                }
            }
        });
        setVisible(true);
    }

    public static void main(String[] args) {
        Date date = Date.valueOf("2000-07-10");
        Librarian librarian = new Librarian("namnv", "Nguyễn Văn Nam", date);
        new LibrarianForm(null, librarian);
    }
}
