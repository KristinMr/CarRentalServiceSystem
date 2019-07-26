package util;

import java.awt.BorderLayout;
import java.awt.EventQueue;


import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class Test extends JFrame {


  private JPanel contentPane;


 
          public static void main(String[] args) {
          Test frame = new Test();
          frame.setVisible(true);
  }


 
          public Test() {
    setBounds(100, 100, 350, 200);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    contentPane.setLayout(new BorderLayout(0, 0));
    setContentPane(contentPane);
   
    JTextField button = new DateSelector();
    contentPane.add(button, BorderLayout.CENTER);
  }

}