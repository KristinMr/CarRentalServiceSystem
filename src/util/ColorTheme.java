package util;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;

public class ColorTheme {


    public void setButtonTheme(Button button) {
        button.setBackground(Color.black);
        button.setForeground(Color.white);
    }
    public void setLabelTheme (JLabel label) {
        label.setBackground(Color.white);
        label.setForeground(Color.white);
    }
    public static JTextField setFieldTheme (JTextField field) {
        field.setBackground(Color.black);
        field.setForeground(Color.white);
        field.setBorder(javax.swing.BorderFactory.createLineBorder(Color.green));

        return field;
    }
}
