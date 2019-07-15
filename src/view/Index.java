package view;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;
import java.awt.*;

public class Index extends JFrame {
    private JPanel menuBar = new JPanel();
    private JButton index = new JButton("首页");
    private JButton loginButton = new JButton("登录");


    private JPanel main = new JPanel();


    private JPanel footer = new JPanel();
    private JLabel footerLabel = new JLabel("版权信息");



    public Index() {
        setSize(1600, 900);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        menuBar.setLayout(new BorderLayout());

        menuBar.setBounds(10,10,1580,100);
        index.setBounds(200,0,100,50);
        loginButton.setBounds(1400,0,100,50);
        footer.setBounds(10,1400,1580,100);
        footer.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.BLACK));




        menuBar.add(index);
        menuBar.add(loginButton);
        add(menuBar, BorderLayout.NORTH);
        add(main, BorderLayout.CENTER);
        footer.add(footerLabel);
        add(footer, BorderLayout.SOUTH);
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
        new Index();
    }
}
