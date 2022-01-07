
package GUI.ThemSuaGUI;

import librarymanagementsystem.BUS.QLNhaCungCapBUS;
import librarymanagementsystem.DTO.QLNhaCungCapDTO;
import librarymanagementsystem.GUI.*;
import librarymanagementsystem.Toolkit.DataProcessing;

import javax.swing.*;
import java.awt.*;

public class SuaNhaCungCapForm extends JFrame{
    
    int x_Mouse, y_Mouse; // For Moving Window
    static String error_mess;
    DataProcessing dp = new DataProcessing();
    static QLNhaCungCapBUS nccBUS = new QLNhaCungCapBUS();
    String mncc;
    
    public SuaNhaCungCapForm(String maNCC) {
        QLNhaCungCapDTO ncc = new QLNhaCungCapBUS().getNhaCungCap(maNCC);
        mncc = maNCC;
        initComponents(ncc.getTenNCC(), ncc.getDiaChi(), ncc.getSdt(), ncc.getEmail());
        setSize(550, 402);
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
    private void initComponents(String tenNCC, String diaChi, String sdt, String email) {

        jPanel1 = new JPanel();
        addButton = new JLabel();
        exitButton = new JLabel();
        movingWindow = new JLabel();
        tenNCC_textField = new JTextField();
        sdt_textField = new JTextField();
        email_textField = new JTextField();
        jScrollPane1 = new JScrollPane();
        diaChi_textField = new JTextField();
        nhaCungCapForm = new JLabel();

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
        exitButton.setBounds(510, 16, 20, 20);
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
        movingWindow.setBounds(0, 0, 550, 60);
        jPanel1.add(movingWindow);

        tenNCC_textField.setBackground(new Color(245, 247, 250));
        tenNCC_textField.setFont(new Font("Tahoma", 0, 14)); // NOI18N
        tenNCC_textField.setText(tenNCC);
        tenNCC_textField.setBorder(null);
        tenNCC_textField.setOpaque(false);
        tenNCC_textField.setBounds(40, 123, 190, 30);
        jPanel1.add(tenNCC_textField);

        diaChi_textField.setBackground(new Color(245, 247, 250));
        diaChi_textField.setFont(new Font("Tahoma", 0, 14)); // NOI18N
        diaChi_textField.setText(diaChi);
        diaChi_textField.setBorder(null);
        diaChi_textField.setOpaque(false);
        diaChi_textField.setBounds(40, 246, 190, 30);
        jPanel1.add(diaChi_textField);

        sdt_textField.setBackground(new Color(245, 247, 250));
        sdt_textField.setFont(new Font("Tahoma", 0, 14)); // NOI18N
        sdt_textField.setText(sdt);
        sdt_textField.setBorder(null);
        sdt_textField.setOpaque(false);
        sdt_textField.setBounds(312, 123, 157, 30);
        jPanel1.add(sdt_textField);

        email_textField.setBackground(new Color(245, 247, 250));
        email_textField.setFont(new Font("Tahoma", 0, 14)); // NOI18N
        email_textField.setText(email);
        email_textField.setBorder(null);
        email_textField.setOpaque(false);
        email_textField.setBounds(315, 246, 200, 30);
        jPanel1.add(email_textField);

        nhaCungCapForm.setIcon(new ImageIcon(getClass().getResource("../../images/output-onlinepngtoolssuancc.png"))); // NOI18N
        nhaCungCapForm.setBounds(0, 0, 550, 402);
        jPanel1.add(nhaCungCapForm);
        jPanel1.setBounds(0, 0, 550, 402);
        
        getContentPane().add(jPanel1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private boolean check_input(String tenNCC, String diaChi, String sdt, String email){
        if (tenNCC.equals("")){
            error_mess = "Tên Nhà Cung Cấp trống!!!";
            return false;
        }
        else if (diaChi.equals("")){
            error_mess = "Địa Chỉ trống!!!";
            return false;
        }
        else if (sdt.equals("")){
            error_mess = "Số Điện Thoại trống!!!";
            return false;
        }
        else if (email.equals("")){
            error_mess = "Email trống!!!";
            return false;
        }
        else if (!dp.check_sdt(sdt)){
            error_mess = "Số Điện Thoại nhập sai!!!";
            return false;
        }
        else if (!dp.check_email(email)){
            error_mess = "Email nhập sai!!!";
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
        String tenNCC = tenNCC_textField.getText();
        String diaChi = diaChi_textField.getText();
        String sdt = sdt_textField.getText();
        String email = email_textField.getText();
        String maNCC = mncc;
        
        if (check_input(tenNCC, diaChi, sdt, email)){
            int option = JOptionPane.showConfirmDialog(
                null, 
                "Bạn có muốn sửa " + tenNCC + " ?", 
                "Xóa Nhà Cung Cấp", 
                JOptionPane.YES_NO_OPTION);
            if(option == JOptionPane.YES_OPTION){
                System.out.println("Nhập Thành Công");
                if (nccBUS.mod(maNCC, tenNCC, sdt, email, diaChi)){
                    new AlertGUI(3, "Success", "Sửa Nhà Cung Cấp Thành Công!!!", "Quay Lại").setVisible(true);
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
        
        System.out.println(maNCC);
        System.out.println(tenNCC);
        System.out.println(diaChi);
        System.out.println(sdt);
        System.out.println(email);
        
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
            java.util.logging.Logger.getLogger(SuaNhaCungCapForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SuaNhaCungCapForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SuaNhaCungCapForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SuaNhaCungCapForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SuaNhaCungCapForm("NCC000004").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JLabel addButton;
    private JLabel exitButton;
    private JPanel jPanel1;
    private JScrollPane jScrollPane1;
    private JLabel nhaCungCapForm;
    private JLabel movingWindow;
    private JTextField tenNCC_textField;
    private JTextField sdt_textField;
    private JTextField email_textField;
    private JTextField diaChi_textField;
    // End of variables declaration//GEN-END:variables
}
    
