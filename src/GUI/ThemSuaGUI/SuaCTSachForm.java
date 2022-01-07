
package GUI.ThemSuaGUI;

import librarymanagementsystem.BUS.QLChiTietSachBUS;
import librarymanagementsystem.BUS.QLLoaiSachBUS;
import librarymanagementsystem.DTO.QLChiTietSachDTO;
import librarymanagementsystem.GUI.*;
import librarymanagementsystem.Toolkit.DataProcessing;

import javax.swing.*;
import java.awt.*;


public class SuaCTSachForm extends JFrame{
    int x_Mouse, y_Mouse; // For Moving Window
    static String error_mess;
    DataProcessing dp = new DataProcessing();
    static QLChiTietSachBUS ctsBUS = new QLChiTietSachBUS();
    String idSach;
    QLChiTietSachDTO id;
    
    public SuaCTSachForm(String IDSach) {
        id = new QLChiTietSachBUS().getChiTietSach(IDSach);
        idSach = IDSach;
        initComponents(id.getIDSach(), id.getMaSach(), id.getTinhTrang());
        setSize(830, 261);
        setLocationRelativeTo(null);
        setBackground(new Color(0, 0, 0, 0));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents(String IDSach, String maSach, String tinhTrang) {
        
        jPanel1 = new JPanel();
        addButton = new JLabel();
        exitButton = new JLabel();
        movingWindow = new JLabel();
        tinhTrang_textField = new JTextField();
        maSach_Label = new JLabel();
        IDSach_Label = new JLabel();
        nhanVienForm = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(null);

        jPanel1.setOpaque(false);
        jPanel1.setLayout(null);

        addButton.setIcon(new ImageIcon(getClass().getResource("../../images/output-onlinepngtools - 2020-05-28T235936.194.png"))); // NOI18N
        addButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addButtonMouseClicked(evt);
            }
        });
        addButton.setBounds(10, 7, 35, 35);
        jPanel1.add(addButton);

        exitButton.setIcon(new ImageIcon(getClass().getResource("../../images/output-onlinepngtools (43).png"))); // NOI18N
        exitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitButtonMouseClicked(evt);
            }
        });
        exitButton.setBounds(790, 16, 20, 20);
        jPanel1.add(exitButton);

        movingWindow.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                movingWindowMouseDragged(evt);
            }
        });
        movingWindow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                movingWindowMousePressed(evt);
            }
        });
        movingWindow.setBounds(0, 0, 830, 60);
        jPanel1.add(movingWindow);
        
        
        IDSach_Label.setBackground(new Color(245, 247, 250));
        IDSach_Label.setFont(new Font("Tahoma", 0, 14)); // NOI18N
        IDSach_Label.setText(id.getIDSach());
        IDSach_Label.setBorder(null);
        IDSach_Label.setOpaque(false);
        IDSach_Label.setBounds(50, 128, 160, 30);
        jPanel1.add(IDSach_Label);

        maSach_Label.setBackground(new Color(245, 247, 250));
        maSach_Label.setFont(new Font("Tahoma", 0, 14)); // NOI18N
        maSach_Label.setText(id.getMaSach());
        maSach_Label.setBorder(null);
        maSach_Label.setOpaque(false);
        maSach_Label.setBounds(305, 128, 160, 30);
        jPanel1.add(maSach_Label);

        tinhTrang_textField.setBackground(new Color(245, 247, 250));
        tinhTrang_textField.setFont(new Font("Tahoma", 0, 14)); // NOI18N
        tinhTrang_textField.setText(tinhTrang);
        tinhTrang_textField.setBorder(null);
        tinhTrang_textField.setOpaque(false);
        tinhTrang_textField.setBounds(572, 128, 160, 30);
        jPanel1.add(tinhTrang_textField);

        nhanVienForm.setIcon(new ImageIcon(getClass().getResource("../../images/output-onlinepngtoolssuactsach.png"))); // NOI18N
        nhanVienForm.setBounds(0, 0, 830, 261);
        jPanel1.add(nhanVienForm);
        jPanel1.setBounds(0, 0, 830, 261);
        
        getContentPane().add(jPanel1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private boolean check_input(String IDSach, String maSach, String tinhTrang){
         if (IDSach.equals("")){
            error_mess = "ID Sách trống!!!";
            return false;
        }
         if (maSach.equals("")){
            error_mess = "Mã Sách trống!!!";
            return false;
        }
        if (tinhTrang.equals("")){
            error_mess = "Tình Trạng trống!!!";
            return false;
        }
        if (!dp.check_IDSach(IDSach)){
            error_mess = "ID Sách bị sai!!!" + IDSach;
            return false;
        }
        if (!dp.check_maSach(maSach)){
            error_mess = "Mã Sách bị sai!!!";
            return false;
        }
        if (new QLLoaiSachBUS(0).getLoaiSach(maSach) == null){
            error_mess = "Mã Sách không tồn tại!!!";
            return false;
        }
        return true;
    }
    
    private void movingWindowMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_movingWindowMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        
        setLocation(x - x_Mouse, y - y_Mouse);
    }//GEN-LAST:event_movingWindowMouseDragged

    
    private void movingWindowMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_movingWindowMousePressed
        x_Mouse = evt.getX();
        y_Mouse = evt.getY();
    }//GEN-LAST:event_movingWindowMousePressed

    private void exitButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButtonMouseClicked
        this.dispose();
    }//GEN-LAST:event_exitButtonMouseClicked

    private void addButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addButtonMouseClicked
        String maSach = maSach_Label.getText();
        String tinhTrang = tinhTrang_textField.getText();
        String IDSach = idSach;
        
        if (check_input(IDSach, maSach, tinhTrang)){
            int option = JOptionPane.showConfirmDialog(
                null, 
                "Bạn có muốn sửa Chi Tiết Sách " + IDSach + " ?", 
                "Xóa Chi Tiết Sách", 
                JOptionPane.YES_NO_OPTION);
            if(option == JOptionPane.YES_OPTION){
                System.out.println("Nhập Thành Công");
                if (ctsBUS.mod(IDSach, maSach, tinhTrang)){
                    new AlertGUI(3, "Success", "Sửa Chi Tiết Sách Thành Công!!!", "Quay Lại").setVisible(true);
                    this.dispose();
                }
            }
        }
        else{
            System.err.println(error_mess);
            System.out.println("Sửa Thất Bại");
            new AlertGUI(2, "Error", error_mess, "Quay Lại").setVisible(true);
            // Alert Form
        }
        
        System.out.println(IDSach);
        System.out.println(maSach);
        System.out.println(tinhTrang);
        
    }//GEN-LAST:event_addButtonMouseClicked

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
           /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SuaCTSachForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SuaCTSachForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SuaCTSachForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SuaCTSachForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SuaCTSachForm("IDS000079").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JLabel addButton;
    private JLabel exitButton;
    private JPanel jPanel1;
    private JLabel nhanVienForm;
    private JLabel movingWindow;
    private JTextField tinhTrang_textField;
    private JLabel maSach_Label;
    private JLabel IDSach_Label;
    // End of variables declaration//GEN-END:variables
}
