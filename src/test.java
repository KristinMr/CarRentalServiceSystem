import javax.swing.*;
import java.awt.*;

public class test extends JFrame {

    private JPanel sidebar = new JPanel();

    private JMenuItem user = new JMenuItem("用户管理");
    private JPanel userPanel = new JPanel();
    private JMenuItem addUser = new JMenuItem("新增用户");
    private JMenuItem userList = new JMenuItem("用户列表");
    public test(){

        setSize(1600, 1000);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        user.setBounds(10,10,150,40);
        user.setFont(new java.awt.Font("楷体",2,20));

        userPanel.setBounds(10 + user.getX(),user.getY() + user.getHeight(),user.getWidth(),addUser.getHeight() + userList.getHeight());
        addUser.setBounds(10 + userPanel.getX(),userPanel.getY() + userPanel.getHeight(),userPanel.getWidth(),50);
        addUser.setFont(new java.awt.Font("楷体",2,18));
        userList.setBounds(10 + userPanel.getX(),addUser.getY()+addUser.getHeight(),userPanel.getWidth(),50);
        userList.setFont(new java.awt.Font("楷体",2,18));

        userPanel.setBackground(Color.magenta);
        sidebar.add(user);
        userPanel.add(addUser);
        userPanel.add(userList);
        sidebar.add(userPanel);
        add(sidebar);

        setVisible(true);
    }

    public static void main(String[] args) {
        new test();
    }
}
