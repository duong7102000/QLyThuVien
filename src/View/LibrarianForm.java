package View;

import Model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import static Controller.AccountController.insertAccount;
import static Controller.BookController.*;
import static Controller.CallCardController.getAllCallCard;
import static Controller.CallCardController.updateCallCard;
import static Controller.CallCardDetailController.getAllCallCardDetail;
import static Controller.CallCardDetailController.updateCallCardDetail;
import static Controller.StudentController.*;
import static Util.ExportToCSV.exportToCSV;
import static Util.GetDate.getCurrentDate;
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
    private JTable table3;
    private JTable table4;
    private JTextField textField1;
    private JButton searchButton2;
    private JButton exportFileButton;

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
        DefaultTableModel defaultTableModel3 = new DefaultTableModel();
        table3.setModel(defaultTableModel3);
        defaultTableModel3.addColumn("Id");
        defaultTableModel3.addColumn("Ngày mượn");
        defaultTableModel3.addColumn("Ngày trả");
        defaultTableModel3.addColumn("Tiền cọc");
        defaultTableModel3.addColumn("Username độc giả");
        defaultTableModel3.addColumn("Username thủ thư");
        List<CallCard> listCallCard = getAllCallCard();
        for (CallCard callCard:
                listCallCard) {
            int id = callCard.getId();
            String start_date = callCard.getStartDate().toString();
            String end_date = callCard.getEndDate().toString();
            if (end_date.equals("2001-01-01")){
                end_date = null;
            }
            float deposit = callCard.getDeposit();
            String s = callCard.getStudentUsername();
            String l = callCard.getLibrarianUsername();
            Object[] row = new Object[]{id, start_date, end_date, deposit, s, l};
            defaultTableModel3.addRow(row);
        }
        DefaultTableModel defaultTableModel4 = new DefaultTableModel();
        table4.setModel(defaultTableModel4);
        defaultTableModel4.addColumn("Id phiếu mượn");
        defaultTableModel4.addColumn("Id sách");
        defaultTableModel4.addColumn("Tên sách");
        defaultTableModel4.addColumn("Ngày mượn");
        defaultTableModel4.addColumn("Ngày trả");
        defaultTableModel4.addColumn("Tiền phạt");
        List<CallCardDetail> listCallCardDetail = getAllCallCardDetail();
        for (CallCardDetail c:
                listCallCardDetail) {
            Book book = getBookById(c.getBookId());
            int callCardId = c.getCallCardId();
            int bookId = book.getId();
            String bookName = book.getName();
            String borrow_date = c.getBorrowDate().toString();
            String return_date = c.getReturnDate().toString();
            if (return_date.equals("2001-01-01")){
                return_date = null;
            }
            float forfeit = c.getForfeit();
            Object[] row = new Object[]{callCardId, bookId, bookName, borrow_date, return_date, forfeit};
            defaultTableModel4.addRow(row);
        }
        searchButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String studentSearch = textField1.getText();
                if(textField1.getText().equals("")){
                    int rows = defaultTableModel3.getRowCount();
                    for(int i = rows - 1; i >=0; i--)
                    {
                        defaultTableModel3.removeRow(i);
                    }
                    int rows1 = defaultTableModel4.getRowCount();
                    for(int i = rows1 - 1; i >=0; i--)
                    {
                        defaultTableModel4.removeRow(i);
                    }
                    List<CallCard> callCardList = getAllCallCard();
                    for (CallCard callCard:
                            callCardList) {
                        int id = callCard.getId();
                        String start_date = callCard.getStartDate().toString();
                        String end_date = callCard.getEndDate().toString();
                        if (end_date.equals("2001-01-01")){
                            end_date = null;
                        }
                        float deposit = callCard.getDeposit();
                        String s = callCard.getStudentUsername();
                        String l = callCard.getLibrarianUsername();
                        Object[] row = new Object[]{id, start_date, end_date, deposit, s, l};
                        defaultTableModel3.addRow(row);
                    }
                    List<CallCardDetail> cardDetailList = getAllCallCardDetail();
                    for (CallCardDetail c:
                            cardDetailList) {
                        Book book = getBookById(c.getBookId());
                        int callCardId = c.getCallCardId();
                        int bookId = book.getId();
                        String bookName = book.getName();
                        String borrow_date = c.getBorrowDate().toString();
                        String return_date = c.getReturnDate().toString();
                        if (return_date.equals("2001-01-01")){
                            return_date = null;
                        }
                        float forfeit = c.getForfeit();
                        Object[] row = new Object[]{callCardId, bookId, bookName, borrow_date, return_date, forfeit};
                        defaultTableModel4.addRow(row);

                    }
                }
                else{
                    List<Student> listStudent = searchStudentByName(studentSearch);
                    int rows = defaultTableModel3.getRowCount();
                    for(int i = rows - 1; i >=0; i--)
                    {
                        defaultTableModel3.removeRow(i);
                    }
                    int rows1 = defaultTableModel4.getRowCount();
                    for(int i = rows1 - 1; i >=0; i--)
                    {
                        defaultTableModel4.removeRow(i);
                    }
                    for (Student std:
                         listStudent) {
                        String stdUsername = std.getUsername();
                        for (CallCard callCard:
                             listCallCard) {
                            if(stdUsername.equals(callCard.getStudentUsername())){
                                int id = callCard.getId();
                                String start_date = callCard.getStartDate().toString();
                                String end_date = callCard.getEndDate().toString();
                                if (end_date.equals("2001-01-01")){
                                    end_date = null;
                                }
                                float deposit = callCard.getDeposit();
                                String s = callCard.getStudentUsername();
                                String l = callCard.getLibrarianUsername();
                                Object[] row = new Object[]{id, start_date, end_date, deposit, s, l};
                                defaultTableModel3.addRow(row);
                                for (CallCardDetail c:
                                        listCallCardDetail) {
                                    if (c.getCallCardId() == id){
                                        Book book = getBookById(c.getBookId());
                                        int callCardId = c.getCallCardId();
                                        int bookId = book.getId();
                                        String bookName = book.getName();
                                        String borrow_date = c.getBorrowDate().toString();
                                        String return_date = c.getReturnDate().toString();
                                        if (return_date.equals("2001-01-01")){
                                            return_date = null;
                                        }
                                        float forfeit = c.getForfeit();
                                        Object[] row1 = new Object[]{callCardId, bookId, bookName, borrow_date, return_date, forfeit};
                                        defaultTableModel4.addRow(row1);
                                    }
                                }
                            }
                        }
                    }
                }
                }
            });
        table3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JOptionPane.showMessageDialog(LibrarianForm.this, "Xác nhận phiếu mượn!");
                int id = Integer.parseInt(defaultTableModel3.getValueAt(table3.getSelectedRow(), 0).toString());
                Date ngay_muon = Date.valueOf(defaultTableModel3.getValueAt(table3.getSelectedRow(), 1).toString());
                Date ngay_tra = Date.valueOf(defaultTableModel3.getValueAt(table3.getSelectedRow(), 2).toString());
                String studentUsername = defaultTableModel3.getValueAt(table3.getSelectedRow(), 4).toString();
                String librarianUsername = librarian.getUsername();
                float deposit = Float.parseFloat(defaultTableModel3.getValueAt(table3.getSelectedRow(), 3).toString());
                CallCard callCard = new CallCard(id, studentUsername, librarianUsername, ngay_muon, ngay_tra, deposit);
                updateCallCard(callCard);
            }
        });
        table4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JOptionPane.showMessageDialog(LibrarianForm.this, "Xác nhận trả sách!");
                int id_phieumuon = Integer.parseInt(defaultTableModel4.getValueAt(table4.getSelectedRow(), 0).toString());
                int id_sach = Integer.parseInt(defaultTableModel4.getValueAt(table4.getSelectedRow(), 1).toString());
                String bookName = defaultTableModel4.getValueAt(table4.getSelectedRow(), 2).toString();
                String ngay_muon = defaultTableModel4.getValueAt(table4.getSelectedRow(), 3).toString();
                Date ngay_tra = getCurrentDate();
                float tien_phat = Float.parseFloat(defaultTableModel4.getValueAt(table4.getSelectedRow(), 5).toString());
                CallCardDetail callCardDetail = new CallCardDetail(id_phieumuon, id_sach, Date.valueOf(ngay_muon), ngay_tra, tien_phat);
                updateCallCardDetail(callCardDetail);
                giveBackBook(id_sach);
            }
        });
        exportFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String time = String.valueOf(new Timestamp(System.currentTimeMillis()));
                exportToCSV(table3, "/home/duong/IdeaProjects/QLyThuVien/ExportFile/AllCallCard"+time+".csv");
                exportToCSV(table4, "/home/duong/IdeaProjects/QLyThuVien/ExportFile/AllCallCardDetail"+time+".csv");
                JOptionPane.showMessageDialog(LibrarianForm.this, "Đã xuất file csv ra folder ExportFile!");
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
