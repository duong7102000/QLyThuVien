package View;

import Model.Book;
import Model.CallCard;
import Model.CallCardDetail;
import Model.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.util.List;

import static Controller.BookController.*;
import static Controller.CallCardController.getAllCallCard;
import static Controller.CallCardController.insertCallCard;
import static Controller.CallCardDetailController.insertCallCardDetail;
import static Util.GetDate.getCurrentDate;

public class StudentForm extends JDialog{
    private JPanel studentForm;
    private JTabbedPane tabbedPane1;
    private JButton thayĐổiThôngTinButton;
    private JButton đăngXuấtButton;
    private JLabel welCome;
    private JLabel studentName;
    private JTable table1;
    private JTextField textField1;
    private JButton searchButton;
    private JTable table2;
    private JButton borrowButton;
    private JTable table3;
    private JButton clearButton;

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
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        table1.setModel(defaultTableModel);
        defaultTableModel.addColumn("Id");
        defaultTableModel.addColumn("Tên sách");
        defaultTableModel.addColumn("Tác giả");
        defaultTableModel.addColumn("Nội dung");
        defaultTableModel.addColumn("Chuyên ngành");
        List<Book> listBook = getAllBook();
        for (Book book:
             listBook) {
            int id = book.getId();
            String name = book.getName();
            String artist = book.getArtist();
            String content = book.getContent();
            String major = book.getMajor();
            Object[] row = new Object[]{id, name, artist, content, major};
            defaultTableModel.addRow(row);
        }
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String bookSearch = textField1.getText();
                List<Book> listBook = searchBookByName(bookSearch);
                if (listBook.size()==0){
                    JOptionPane.showMessageDialog(StudentForm.this, "Không có sách bạn tìm kiếm!");
                }
                else{
                    int rows = defaultTableModel.getRowCount();
                    for(int i = rows - 1; i >=0; i--)
                    {
                        defaultTableModel.removeRow(i);
                    }
                    for (Book book:
                            listBook) {
                        int id = book.getId();
                        String name = book.getName();
                        String artist = book.getArtist();
                        String content = book.getContent();
                        String major = book.getMajor();
                        Object[] row = new Object[]{id, name, artist, content, major};
                        defaultTableModel.addRow(row);
                    }
                }
            }
        });
        DefaultTableModel defaultTableModel2 = new DefaultTableModel();
        table2.setModel(defaultTableModel2);
        defaultTableModel2.addColumn("Id");
        defaultTableModel2.addColumn("Tên sách");
        defaultTableModel2.addColumn("Tác giả");
        defaultTableModel2.addColumn("Nội dung");
        defaultTableModel2.addColumn("Chuyên ngành");
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                DefaultTableModel defaultTableModel1 = (DefaultTableModel) table1.getModel();
                int bookId = Integer.parseInt(defaultTableModel1.getValueAt(table1.getSelectedRow(), 0).toString());
                String bookName = defaultTableModel1.getValueAt(table1.getSelectedColumn(), 1).toString();
                String artist = defaultTableModel1.getValueAt(table1.getSelectedRow(), 2).toString();
                String content = defaultTableModel1.getValueAt(table1.getSelectedRow(), 3).toString();
                String major = defaultTableModel1.getValueAt(table1.getSelectedRow(), 4).toString();
                Book book = getBookById(bookId);
                if (book.getRemain() > 0){
                    String[] options = {"Yes", "No"};
                    int result = JOptionPane.showOptionDialog(
                            StudentForm.this,
                            "Bạn có muốn mượn "+bookName+" không?",
                            "Warning!",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            options,
                            options[0]
                    );
                    if(result == JOptionPane.YES_OPTION){
                        Object[] row = new Object[]{bookId, bookName, artist, content, major};
                        defaultTableModel2.addRow(row);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(StudentForm.this, "Sách hiện tại đã hết!");
                }
            }
        });
        DefaultTableModel defaultTableModel3 = new DefaultTableModel();
        table3.setModel(defaultTableModel3);
        defaultTableModel3.addColumn("Id");
        defaultTableModel3.addColumn("Ngày mượn");
        defaultTableModel3.addColumn("Ngày trả");
        defaultTableModel3.addColumn("Tiền cọc");
        List<CallCard> listCallCard = getAllCallCard();
        for (CallCard callCard:
             listCallCard) {
            if(student.getUsername().equals(callCard.getStudentUsername())){
                Integer id = callCard.getId();
                String start_date = callCard.getStartDate().toString();
                String end_date = callCard.getEndDate().toString();
                Float deposit = callCard.getDeposit();
                Object[] row = new Object[]{id, start_date, end_date, deposit};
                defaultTableModel3.addRow(row);
            }
        }
        table3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                DefaultTableModel defaultTableModel31 = (DefaultTableModel) table3.getModel();
                int callCardId = Integer.parseInt(defaultTableModel31.getValueAt(table3.getSelectedRow(), 0).toString());
                new CallCardDetailForm(null, callCardId);
            }
        });
        borrowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String returnDate = JOptionPane.showInputDialog("Mời bạn nhập ngày hẹn trả (yyyy-mm-dd):");
                Date date = Date.valueOf(returnDate);
                CallCard callCard = new CallCard(student.getUsername(), "", getCurrentDate(), date, 10000);
                int id = insertCallCard(callCard);
                for(int count = 0; count < defaultTableModel2.getRowCount(); count++){
                    int bookId = (int) defaultTableModel2.getValueAt(count, 0);
                    CallCardDetail callCardDetail = new CallCardDetail(id, bookId, getCurrentDate(), date, 0);
                    insertCallCardDetail(callCardDetail);
                }
            }
        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int rows = defaultTableModel2.getRowCount();
                for(int i = rows - 1; i >=0; i--)
                {
                    defaultTableModel2.removeRow(i);
                }
            }
        });
        setVisible(true);
    }

    public static void main(String[] args) {
        Student student = new Student("duongdt", "Duong", Date.valueOf("2000-10-07"), "HD", "M1", "C1");
        new StudentForm(null, student);
    }
}
