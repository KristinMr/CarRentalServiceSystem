package view.pubilc;

import util.MyLayout;

import javax.swing.*;
import java.awt.*;

public class ShowInfo extends JDialog {

    private JTextArea infoArea = new JTextArea();
    public ShowInfo(String info) {
        setTitle("备注详情");
        setSize(500, 350);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        setModal(true);

//        infoArea.setBounds(0,0,450,300);
        infoArea.setText(info);
        infoArea.setEnabled(false);
        infoArea.setLineWrap(true);
        add(infoArea, MyLayout.setValues(0,0,1,1,1,1));
        setVisible(true);
    }
}
