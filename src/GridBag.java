import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import util.MyLayout;
import view.car.CarList;
import view.rank.RankList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GridBag extends JFrame {
    private JPanel menuBar = new JPanel();
    private JButton index = new JButton("首页");
    private JButton loginButton = new JButton("登录");

    private JPanel carList = new CarList();
    private JPanel rankList = new RankList();

    private JPanel main = new JPanel();
    private JPanel footer = new JPanel();


    public GridBag() {
        setSize(1600, 900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        index.setSize(100,30);

        menuBar.setLayout(new GridBagLayout());


//        GridBagConstraints gridBagConstraints = new GridBagConstraints();
//        gridBagConstraints.fill = GridBagConstraints.BOTH;


        index.setBackground(Color.white);
        menuBar.add(index, MyLayout.setValues(0,0,1,1,1,0.1));


        menuBar.add(loginButton, MyLayout.setValues(1,0,1,1,1,0.1));

//        gridBagConstraints.gridx = 0;
//        gridBagConstraints.gridy = 0;
//        gridBagConstraints.gridwidth = 1;
//        gridBagConstraints.gridheight = 1;
//        gridBagConstraints.weightx = 1;
//        gridBagConstraints.weighty = 0.05;
        add(menuBar, MyLayout.setValues(0,0,1,1,1,0.05));

//        gridBagConstraints.gridx = 0;
//        gridBagConstraints.gridy = 1;
//        gridBagConstraints.gridwidth = 1;
//        gridBagConstraints.gridheight = 1;
//        gridBagConstraints.weightx = 1;
//        gridBagConstraints.weighty = 0.9;
        add(main, MyLayout.setValues(0,1,1,1,1,0.9));
        add(carList, MyLayout.setValues(0,1,1,1,1,0.9));
        add(rankList, MyLayout.setValues(0,1,1,1,1,0.9));
        carList.setVisible(false);
        rankList.setVisible(false);
//        main.setBackground(Color.yellow);

//        gridBagConstraints.gridx = 0;
//        gridBagConstraints.gridy = 2;
//        gridBagConstraints.gridwidth = 1;
//        gridBagConstraints.gridheight = 1;
//        gridBagConstraints.weightx = 1;
//        gridBagConstraints.weighty = 0.05;
        add(footer, MyLayout.setValues(0,2,1,1,1,0.05));
        footer.setBackground(Color.green);

        index.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                main.setVisible(false);
                rankList.setVisible(false);
                carList.setVisible(true);
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.setVisible(false);
                carList.setVisible(false);
                rankList.setVisible(true);
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
        new GridBag();

//        JFrame frame = new JFrame("GridWeightTest");
//        frame.setLocation(10, 10);
//        frame.setSize(600, 400);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setLayout(new BorderLayout());
//        JPanel centerPane = new JPanel();
//
//        frame.add(centerPane, BorderLayout.CENTER);
//
//        centerPane.setLayout(new GridBagLayout());
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.fill = GridBagConstraints.BOTH;
//
//        JButton btn1 = new JButton("方格1");
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        gbc.gridwidth = 2;
//        gbc.gridheight = 2;
//        gbc.weightx = 0.5;
//        gbc.weighty = 0.8;
//        centerPane.add(btn1, gbc);
//
//        JButton btn2 = new JButton("方格2");
//        gbc.gridx = 2;
//        gbc.gridy = 0;
//        gbc.gridwidth = 1;
//        gbc.gridheight = 3;
//        gbc.weightx = 0.5;
//        gbc.weighty = 1;
//        centerPane.add(btn2, gbc);
//
//        JButton btn3 = new JButton("方格3");
//        gbc.gridx = 0;
//        gbc.gridy = 2;
//        gbc.gridwidth = 2;
//        gbc.gridheight = 1;
//        gbc.weightx = 0.5;
//        gbc.weighty = 0.2;
//        centerPane.add(btn3, gbc);
//
//        frame.setVisible(true);
    }
}
