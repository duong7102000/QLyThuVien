package View;

import Model.Book;
import Model.CallCardDetail;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

import static Controller.BookController.getBookById;
import static Controller.CallCardDetailController.getAllCallCardDetail;

public class CallCardDetailForm extends JDialog{
    private JPanel panel1;
    private JTable table1;

    public CallCardDetailForm(JFrame parent, int callCardId){
        super(parent);
        setTitle("Call Card Detail");
        setContentPane(panel1);
        setMinimumSize(new Dimension(500, 600));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        table1.setModel(defaultTableModel);
        defaultTableModel.addColumn("Tên sách");
        defaultTableModel.addColumn("Ngày mượn");
        defaultTableModel.addColumn("Ngày trả");
        defaultTableModel.addColumn("Tiền phạt");
        List<CallCardDetail> listCallCardDetail = getAllCallCardDetail();
        for (CallCardDetail c:
             listCallCardDetail) {
            if(c.getCallCardId()==callCardId){
                Book book = getBookById(c.getBookId());
                String bookName = book.getName();
                String borrow_date = c.getBorrowDate().toString();
                String return_date = c.getReturnDate().toString();
                Float forfeit = c.getForfeit();
                Object[] row = new Object[]{bookName, borrow_date, return_date, forfeit};
                defaultTableModel.addRow(row);
            }
        }
        setVisible(true);
    }
}
