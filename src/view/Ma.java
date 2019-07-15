package view;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import view.car.AddCar;
import view.car.CarList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Ma extends JFrame {

    private JMenuBar menuBar = new JMenuBar();

    private JMenu carMenu = new JMenu("车辆管理");
    private JMenuItem addCarItem = new JMenuItem("新增车辆");
    private JMenuItem queryCarItem = new JMenuItem("查询车辆");

    private JMenu userMenu = new JMenu("用户管理");
    private JMenuItem userInfoItem = new JMenuItem("个人信息");

    public Ma() {


//        JPanel panel = new JPanel() {
//
//            public void paintComponent(Graphics g) {
//
//                ImageIcon icon = new ImageIcon("C:\\Users\\mrcap\\IdeaProjects\\CarRentalServiceSystem\\src\\source\\main.jpg");
//
//                // 图片随窗体大小而变化
//
//                g.drawImage(icon.getImage(), 0, 0, this.getSize().width, this.getSize().height,this);
//            }
//        };

//        panel.setOpaque(false); //设置透明。

        setTitle("欢迎使用车辆租赁系统");
        setSize(1200,900);
        setLocationRelativeTo(null);
        setLayout(null);

        ImageIcon imageIcon = new ImageIcon("C:\\Users\\mrcap\\IdeaProjects\\CarRentalServiceSystem\\src\\source\\main.jpg");
        JLabel bgLabel = new JLabel(imageIcon);
        this.getLayeredPane().add(bgLabel, new Integer(Integer.MIN_VALUE));
        bgLabel.setBounds(0, 0, imageIcon.getIconWidth(), imageIcon.getIconHeight());
        this.getContentPane().add(new JLabel());
        ((JPanel)getContentPane()).setOpaque(false);
        menuBar.setBounds(10,10,1180,30);

        carMenu.setBounds(200,0,80,50);
        addCarItem.setBackground(Color.green);

        carMenu.add(addCarItem);
        carMenu.add(queryCarItem);

        userMenu.add(userInfoItem);

        menuBar.add(carMenu);
        menuBar.add(userMenu);


//        add(panel);
        add(menuBar);
        add(bgLabel);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int n = JOptionPane.showConfirmDialog(null, "退出","真的要退出么？",JOptionPane.YES_NO_OPTION);
                if (n == 0) {
                    System.exit(0);
                }
            }
        });

        addCarItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddCar();
            }
        });

        queryCarItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CarList();
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
        } catch (Exception e) {

        }
        new Ma();
    }
}
