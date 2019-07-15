package view;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import view.car.CarList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Mai extends JFrame {

//    JPanel jPanel = new JPanel();
    private JMenuBar menuBar = new JMenuBar();

    private JMenu carMenu = new JMenu("车辆管理");
    private JMenuItem addCarItem = new JMenuItem("新增车辆");
    private JMenuItem queryCarItem = new JMenuItem("查询车辆");

    private JMenu userMenu = new JMenu("用户管理");
    private JMenuItem userInfoItem = new JMenuItem("个人信息");

    JPanel jPanel1 = new JPanel();
    JPanel jPanel2 = new CarList();

//    Container container = this.getContentPane();
//    Point point = container.getLocationOnScreen();
//    Dimension dimension = container.getSize();

    public Mai() {

        setTitle("欢迎使用车辆租赁系统");
        setSize(1600, 900);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());


//        jPanel.setSize(this.getSize());
//        jPanel.setLayout(new FlowLayout());

        ImageIcon imageIcon = new ImageIcon("/Users/cappuyang/IdeaProjects/CarRentalServiceSystem/src/source/main.jpg");
        JLabel bgLabel = new JLabel(imageIcon);
        this.getLayeredPane().add(bgLabel, new Integer(Integer.MIN_VALUE));
        bgLabel.setBounds(0, 0, imageIcon.getIconWidth(), imageIcon.getIconHeight());
        this.getContentPane().add(new JLabel());
        ((JPanel) getContentPane()).setOpaque(false);
        menuBar.setBounds(10, 10, this.getWidth()-100, 50);
        addCarItem.setBackground(Color.green);

        carMenu.add(addCarItem);
        carMenu.add(queryCarItem);

        userMenu.add(userInfoItem);

        menuBar.add(carMenu);
        menuBar.add(userMenu);

        jPanel1.setLayout(null);
        jPanel2.setLayout(null);
        jPanel1.setBounds(10,80,150,900);
        jPanel2.setBounds(10,80,1580,900);


//        carMenu.setMnemonic(KeyEvent.VK_F);
        add(menuBar, BorderLayout.NORTH);
        add(jPanel1, BorderLayout.CENTER);
        jPanel1.setVisible(true);
        add(jPanel2, BorderLayout.CENTER);
        jPanel2.add(bgLabel);
        jPanel2.setVisible(false);
        add(bgLabel);
//        add(jPanel);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        addCarItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jPanel1.setVisible(false);
                jPanel2.setVisible(true);
//                jPanel2.add(new CarList());
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        try {
//            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.frameBorderStyle.osLookAndFeelDecorated;
            UIManager.put("RootPane.setupButtonVisible", false);
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.frameBorderStyle.translucencyAppleLike;
//            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.frameBorderStyle.generalNoTranslucencyShadow;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
            UIManager.put("JMenuBar.font", new java.awt.Font("宋体", 0, 50));
        } catch (Exception e) {

        }
        new Mai();
    }
}
