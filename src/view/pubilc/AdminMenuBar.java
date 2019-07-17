package view.pubilc;

import view.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AdminMenuBar extends JPanel {
    private ImageIcon imageIcon = new ImageIcon("C:\\Users\\mrcap\\IdeaProjects\\CarRentalServiceSystem\\src\\source\\Index.png");
    private JLabel indexLabel = new JLabel("租车管理系统");
    private JLabel dateTimeLabel = new JLabel();
    private JLabel welcomeUserLabel = new JLabel();


//    private JMenuBar userMenuBar = new JMenuBar();
    private JMenu welcomeUser = new JMenu();
    private JMenuItem userInfo = new JMenuItem();
    private JMenuItem updatePassword = new JMenuItem("修改密码");
    public AdminMenuBar(String userName) {
        setSize(1600,72);
        setLayout(null);
        indexLabel.setBounds(200,6,180, 60);
        indexLabel.setFont(new java.awt.Font("楷体",3,24));
        dateTimeLabel.setBounds(1320,0,200,30);
//        indexButton.setForeground(Color.white);
//        indexButton.setBackground(Color.black);

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        String string = simpleDateFormat.format(date);
        dateTimeLabel.setText(string);
        dateTimeLabel.setFont(new java.awt.Font("楷体",1,13));
//        dateTimeLabel.setForeground(Color.green);

        welcomeUser.setText("你好，" + userName);
//        welcomeUserLabel.setBounds();

        userInfo.setText("您好，" + userName);
        userInfo.setBounds(1200,40,100,30);
        userInfo.setFont(new java.awt.Font("楷体",1,13));
        updatePassword.setBounds(1320,40,150,30);
        updatePassword.setFont(new java.awt.Font("楷体",1,13));

        add(userInfo);
        add(updatePassword);



        add(indexLabel);
        add(dateTimeLabel);
        setVisible(true);

        userInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Login();
            }
        });
    }
}
