
package GUI.HienThiGUI;

import javax.swing.*;
import java.awt.*;

public class dashboardCard{
    String title, value, image;
    public dashboardCard() {
        title = "SÁCH";
        value = "120";
        image = "../../images/output-onlinepngtools (85).png";
    }
    
    public dashboardCard(String title, String value, String image) {
        this.title = title;
        this.value = value;
        this.image = image;
    }
    
    public void setProperties(String title, String value, String image){
        this.title = title;
        this.value = value;
        this.image = image;
    }

    public void setLocat(int x, int y){
        jPanel1.setBounds(x, y, 220, 134);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    public JPanel getdashboardCard() {

        jPanel1 = new JPanel();
        title_Label = new JLabel();
        icon_Label = new JLabel();
        value_Label = new JLabel();
        statCard_Label = new JLabel();

        jPanel1.setLayout(null);

        title_Label.setFont(new Font("Arial", 1, 14)); // NOI18N
        title_Label.setForeground(new Color(127, 127, 127));
        title_Label.setText(title);
        title_Label.setBounds(40, 35, 100, 17);
        jPanel1.add(title_Label);

        icon_Label.setIcon(new ImageIcon(getClass().getResource(image))); // NOI18N
        icon_Label.setBounds(130, 14, 75, 75);
        jPanel1.add(icon_Label);

        value_Label.setFont(new Font("Consolas", 1, 24)); // NOI18N
        value_Label.setText(value);
        value_Label.setBounds(40, 65, 100, 29);
        jPanel1.add(value_Label);

        statCard_Label.setIcon(new ImageIcon(getClass().getResource("../../images/output-onlinepngtools (84).png"))); // NOI18N
        statCard_Label.setBounds(0, 0, 220, 134);
        jPanel1.add(statCard_Label);
        jPanel1.setBounds(0, 0, 220, 134);
        jPanel1.setBackground(new Color(0, 0, 0, 0));

        return jPanel1;
    }// </editor-fold>

    // Variables declaration - do not modify
    private JLabel icon_Label;
    private JPanel jPanel1;
    private JLabel statCard_Label;
    private JLabel title_Label;
    private JLabel value_Label;
    // End of variables declaration                   
}
