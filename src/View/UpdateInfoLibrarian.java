package View;

import javax.swing.*;
import java.awt.*;

public class UpdateInfoLibrarian extends JDialog{
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton OKButton;
    private JButton cancelButton;
    private JPanel j1;

    public UpdateInfoLibrarian(JFrame parent){
        setTitle("Update info");
        setContentPane(j1);
        setMinimumSize(new Dimension(500, 600));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
