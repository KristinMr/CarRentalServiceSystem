package view;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import util.Admin;
import util.MyLayout;
import view.admin.AddAdmin;
import view.admin.AdminList;
import view.car.AddCar;
import view.car.CarList;
import view.carType.AddCarType;
import view.carType.CarTypeList;
import view.order.AddOrderUser;
import view.order.OrderList;
import view.pubilc.Footer;
import view.pubilc.Index;
import view.pubilc.UpdatePassword;
import view.rank.AddRank;
import view.rank.RankList;
import view.recharge.AddRecharge;
import view.recharge.RechargeList;
import view.recycleBin.*;
import view.state.AddState;
import view.state.StateList;
import view.user.AddUser;
import view.admin.AdminInfo;
import view.user.UserList;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

public class Main extends JFrame {


//        ------------------------顶栏------------------------------------
    private JPanel menuBar = new JPanel();

    private ImageIcon imageIcon = new ImageIcon("C:\\Users\\mrcap\\IdeaProjects\\CarRentalServiceSystem\\src\\source\\Index.png");
    private JMenuItem index = new JMenuItem("租车管理系统");
    private JLabel dateTimeLabel = new JLabel();

    private JMenuItem welcomeUser = new JMenuItem();
    private JLabel jLabel = new JLabel("|");
    private JMenuItem updatePassword = new JMenuItem("修改密码");
    private JLabel jLabel1 = new JLabel("|");
    private JMenuItem signOut = new JMenuItem("退出");



//        ------------------------侧边栏------------------------------------
    private JPanel sidebar = new JPanel();

    private JMenuItem userItem = new JMenuItem("用户管理");
    private JPanel userPanel = new JPanel();
    private JMenuItem addUser = new JMenuItem("新增用户");
    private JMenuItem userList = new JMenuItem("用户列表");

    private JMenuItem orderItem = new JMenuItem("订单管理");
    private JPanel orderPanel = new JPanel();
    private JMenuItem addOrder = new JMenuItem("新增订单");
    private JMenuItem orderList = new JMenuItem("订单列表");

    private JMenuItem carItem = new JMenuItem("车辆管理");
    private JPanel carPanel = new JPanel();
    private JMenuItem addCar = new JMenuItem("新增车辆");
    private JMenuItem carList = new JMenuItem("车辆列表");

    private JMenuItem carTypeItem = new JMenuItem("车型管理");
    private JPanel carTypePanel = new JPanel();
    private JMenuItem addCarType = new JMenuItem("新增车型");
    private JMenuItem carTypeList = new JMenuItem("车型列表");

    private JMenuItem rechargeItem = new JMenuItem("充值管理");
    private JPanel rechargePanel = new JPanel();
    private JMenuItem addRecharge = new JMenuItem("新增充值");
    private JMenuItem rechargeList = new JMenuItem("充值列表");

    private JMenuItem stateItem = new JMenuItem("状态管理");
    private JPanel statePanel = new JPanel();
    private JMenuItem addState = new JMenuItem("新增状态");
    private JMenuItem stateList = new JMenuItem("状态列表");


    private JMenuItem rankItem = new JMenuItem("权限管理");
    private JPanel rankPanel = new JPanel();
    private JMenuItem addRank = new JMenuItem("新增权限");
    private JMenuItem rankList = new JMenuItem("权限列表");

    private JMenuItem adminItem = new JMenuItem("管理员管理");
    private JPanel adminPanel = new JPanel();
    private JMenuItem addAdmin = new JMenuItem("新增管理员");
    private JMenuItem adminList = new JMenuItem("管理员列表");

    private JMenuItem recycleBinItem = new JMenuItem("回收站管理");
    private JPanel recycleBinPanel = new JPanel();
    private JMenuItem userRecycleBinList = new JMenuItem("用户");
    private JMenuItem orderRecycleBinList = new JMenuItem("订单");
    private JMenuItem carRecycleBinList = new JMenuItem("车辆");
    private JMenuItem carBrandRecycleBinList = new JMenuItem("车辆品牌");
    private JMenuItem carModelRecycleBinList = new JMenuItem("车辆型号");
    private JMenuItem rechargeRecycleBinList = new JMenuItem("充值");
    private JMenuItem stateRecycleBinList = new JMenuItem("状态");
    private JMenuItem rankRecycleBinList = new JMenuItem("权限");
    private JMenuItem adminRecycleBinList = new JMenuItem("管理员");







    public static JPanel main = new JPanel();
    private JPanel mainPanel = new JPanel();
    private JButton borrowCarButton = new JButton("租车");
    private JButton returnCarButton = new JButton("还车");


    private JPanel footer = new JPanel();
    private JPanel footerPanel = new Footer();


    public Main(Admin admin) {
        setSize(1600, 1000);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        menuBar.setLayout(null);

        sidebar.setLayout(null);

        main.setLayout(null);
        mainPanel.setLayout(null);
        footer.setLayout(null);
//        indexButton.setBounds(0,0,100,40);
//        menuBar.setBackground(Color.yellow);
//        sidebar.setBackground(Color.pink);
//        footerPanel.setBackground(Color.green);

        menuBar.setBackground(Color.black);
        sidebar.setBackground(Color.gray);
        footerPanel.setBackground(Color.black);



//        ***************************顶栏***************************

        index.setBounds(200,6,180, 60);
        index.setFont(new java.awt.Font("楷体",1,24));
        index.setForeground(Color.white);
        dateTimeLabel.setBounds(1320,0,200,30);
//        indexButton.setForeground(Color.white);
//        indexButton.setBackground(Color.black);

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        String string = simpleDateFormat.format(date);
        dateTimeLabel.setText(string);
        dateTimeLabel.setFont(new java.awt.Font("楷体",1,13));
        dateTimeLabel.setForeground(Color.white);

//        welcomeUser.setText("你好，" + userName);
//        welcomeUserLabel.setBounds();

        welcomeUser.setText("您好，" + admin.getAdminName());
        welcomeUser.setBounds(1085,40,180,30);
        welcomeUser.setFont(new java.awt.Font("楷体",1,18));
        welcomeUser.setForeground(Color.white);
        jLabel.setBounds(1275,40,10,30);
        jLabel.setFont(new java.awt.Font("楷体",1,18));
        updatePassword.setBounds(1285,40,120,30);
        updatePassword.setFont(new java.awt.Font("楷体",1,18));
        updatePassword.setForeground(Color.white);
        jLabel1.setBounds(1415,40,10,30);
        jLabel1.setFont(new java.awt.Font("楷体",1,18));
        signOut.setBounds(15 + jLabel1.getX(),40,80,30);
        signOut.setFont(new java.awt.Font("楷体",1,18));
//        signOut.setForeground(Color.RED);
        signOut.setForeground(Color.green);

        menuBar.add(index);
        menuBar.add(dateTimeLabel);
        menuBar.add(welcomeUser);
        menuBar.add(jLabel);
        menuBar.add(updatePassword);
        menuBar.add(jLabel1);
        menuBar.add(signOut);

//        ***************************侧边栏***************************

//        ---------------用户管理------------------
        userItem.setBounds(10,10,150,40);
        userItem.setFont(new java.awt.Font("楷体",1,20));
        userItem.setForeground(Color.white);

        userPanel.setBackground(Color.gray);
        userPanel.setBounds(20,50,140,60);
        addUser.setBounds(0 ,0,140,30);
        addUser.setFont(new java.awt.Font("楷体",2,18));
        addUser.setForeground(Color.green);
        userList.setBounds(0,30,140,30);
        userList.setFont(new java.awt.Font("楷体",2,18));
        userList.setForeground(Color.green);

//        ---------------订单管理------------------
        orderItem.setBounds(10,60,150,40);
        orderItem.setFont(new java.awt.Font("楷体",1,20));
        orderItem.setForeground(Color.white);

        orderPanel.setBackground(Color.gray);
        orderPanel.setBounds(20,100,140,60);
        addOrder.setBounds(0 ,0,140,30);
        addOrder.setFont(new java.awt.Font("楷体",2,18));
        addOrder.setForeground(Color.green);
        orderList.setBounds(0,30,140,30);
        orderList.setFont(new java.awt.Font("楷体",2,18));
        orderList.setForeground(Color.green);

//        addOrderPanel = new AddOrderUser(admin);

//        ---------------车辆管理------------------
        carItem.setBounds(10,110,150,40);
        carItem.setFont(new java.awt.Font("楷体",1,20));
        carItem.setForeground(Color.white);

        carPanel.setBackground(Color.gray);
        carPanel.setBounds(20,150,140,60);
        addCar.setBounds(0 ,0,140,30);
        addCar.setFont(new java.awt.Font("楷体",2,18));
        addCar.setForeground(Color.green);
        carList.setBounds(0,30,140,30);
        carList.setFont(new java.awt.Font("楷体",2,18));
        carList.setForeground(Color.green);

//        ---------------车型管理------------------
        carTypeItem.setBounds(10,160,150,40);
        carTypeItem.setFont(new java.awt.Font("楷体",1,20));
        carTypeItem.setForeground(Color.white);

        carTypePanel.setBackground(Color.gray);
        carTypePanel.setBounds(20,200,140,60);
        addCarType.setBounds(0 ,0,140,30);
        addCarType.setFont(new java.awt.Font("楷体",2,18));
        addCarType.setForeground(Color.green);
        carTypeList.setBounds(0,30,140,30);
        carTypeList.setFont(new java.awt.Font("楷体",2,18));
        carTypeList.setForeground(Color.green);

//        ---------------充值管理------------------

        rechargeItem.setBounds(10,210,150,40);
        rechargeItem.setFont(new java.awt.Font("楷体",1,20));
        rechargeItem.setForeground(Color.white);

        rechargePanel.setBackground(Color.gray);
        rechargePanel.setBounds(20,250,140,60);
        addRecharge.setBounds(0 ,0,140,30);
        addRecharge.setFont(new java.awt.Font("楷体",2,18));
        addRecharge.setForeground(Color.green);
        rechargeList.setBounds(0,30,140,30);
        rechargeList.setFont(new java.awt.Font("楷体",2,18));
        rechargeList.setForeground(Color.green);

//        ---------------状态管理------------------

        stateItem.setBounds(10,260,150,40);
        stateItem.setFont(new java.awt.Font("楷体",1,20));
        stateItem.setForeground(Color.white);

        statePanel.setBackground(Color.gray);
        statePanel.setBounds(20,300,140,60);
        addState.setBounds(0 ,0,140,30);
        addState.setFont(new java.awt.Font("楷体",2,18));
        addState.setForeground(Color.green);
        stateList.setBounds(0,30,140,30);
        stateList.setFont(new java.awt.Font("楷体",2,18));
        stateList.setForeground(Color.green);

//        ---------------权限管理------------------

        rankItem.setBounds(10,310,150,40);
        rankItem.setFont(new java.awt.Font("楷体",1,20));
        rankItem.setForeground(Color.white);

        rankPanel.setBackground(Color.gray);
        rankPanel.setBounds(20,350,140,60);
        addRank.setBounds(0 ,0,140,30);
        addRank.setFont(new java.awt.Font("楷体",2,18));
        addRank.setForeground(Color.green);
        rankList.setBounds(0,30,140,30);
        rankList.setFont(new java.awt.Font("楷体",2,18));
        rankList.setForeground(Color.green);

//        ---------------管理员管理------------------

        adminItem.setBounds(10,360,150,40);
        adminItem.setFont(new java.awt.Font("楷体",1,20));
        adminItem.setForeground(Color.white);

        adminPanel.setBackground(Color.gray);
        adminPanel.setBounds(20,400,140,60);
        addAdmin.setBounds(0 ,0,140,30);
        addAdmin.setFont(new java.awt.Font("楷体",2,18));
        addAdmin.setForeground(Color.green);
        adminList.setBounds(0,30,140,30);
        adminList.setFont(new java.awt.Font("楷体",2,18));
        adminList.setForeground(Color.green);

//        ---------------回收站管理------------------

        recycleBinItem.setBounds(10,410,150,40);
        recycleBinItem.setFont(new java.awt.Font("楷体",1,20));
        recycleBinItem.setForeground(Color.white);

        recycleBinPanel.setBackground(Color.gray);
        recycleBinPanel.setBounds(20,450,140,270);

        userRecycleBinList.setBounds(0 ,0,140,30);
        userRecycleBinList.setFont(new java.awt.Font("楷体",2,18));
        userRecycleBinList.setForeground(Color.green);

        orderRecycleBinList.setBounds(0,30,140,30);
        orderRecycleBinList.setFont(new java.awt.Font("楷体",2,18));
        orderRecycleBinList.setForeground(Color.green);

        carRecycleBinList.setBounds(0,60,140,30);
        carRecycleBinList.setFont(new java.awt.Font("楷体",2,18));
        carRecycleBinList.setForeground(Color.green);

        carBrandRecycleBinList.setBounds(0 ,90,140,30);
        carBrandRecycleBinList.setFont(new java.awt.Font("楷体",2,18));
        carBrandRecycleBinList.setForeground(Color.green);

        carModelRecycleBinList.setBounds(0 ,120,140,30);
        carModelRecycleBinList.setFont(new java.awt.Font("楷体",2,18));
        carModelRecycleBinList.setForeground(Color.green);

        rechargeRecycleBinList.setBounds(0,150,140,30);
        rechargeRecycleBinList.setFont(new java.awt.Font("楷体",2,18));
        rechargeRecycleBinList.setForeground(Color.green);

        stateRecycleBinList.setBounds(0 ,180,140,30);
        stateRecycleBinList.setFont(new java.awt.Font("楷体",2,18));
        stateRecycleBinList.setForeground(Color.green);

        rankRecycleBinList.setBounds(0,210,140,30);
        rankRecycleBinList.setFont(new java.awt.Font("楷体",2,18));
        rankRecycleBinList.setForeground(Color.green);

        adminRecycleBinList.setBounds(0 ,240,140,30);
        adminRecycleBinList.setFont(new java.awt.Font("楷体",2,18));
        adminRecycleBinList.setForeground(Color.green);




        rankItem.setVisible(false);
        adminItem.setVisible(false);
        recycleBinItem.setVisible(false);
        if (admin.getAdminRank() == 1) {
            rankItem.setVisible(true);
            adminItem.setVisible(true);
            recycleBinItem.setVisible(true);
        }

//        userPanel.setBackground(Color.magenta);

//        state.setBounds(10,car.getY() + car.getHeight() + 10,this.getWidth(),40);
//        state.setFont(new java.awt.Font("楷体",3,20));
//
//        statePanel.setBounds(0,state.getY() + state.getHeight(),this.getWidth(),100);
//        addState.setBounds(0,statePanel.getY() + 10,100,40);
//        addState.setFont(new java.awt.Font("楷体",2,18));
//        stateList.setBounds(0,addState.getY() + 50,this.getWidth(),40);
//        stateList.setFont(new java.awt.Font("楷体",2,18));


        userPanel.setVisible(false);
        orderPanel.setVisible(false);
        carPanel.setVisible(false);
        carTypePanel.setVisible(false);
        rechargePanel.setVisible(false);
        statePanel.setVisible(false);
        rankPanel.setVisible(false);
        adminPanel.setVisible(false);
        recycleBinPanel.setVisible(false);

        sidebar.add(userItem);
        userPanel.add(addUser);
        userPanel.add(userList);
        sidebar.add(userPanel);

        sidebar.add(orderItem);
        orderPanel.add(addOrder);
        orderPanel.add(orderList);
        sidebar.add(orderPanel);

        sidebar.add(carItem);
        carPanel.add(addCar);
        carPanel.add(carList);
        sidebar.add(carPanel);

        sidebar.add(carTypeItem);
        carTypePanel.add(addCarType);
        carTypePanel.add(carTypeList);
        sidebar.add(carTypePanel);

        sidebar.add(rechargeItem);
        rechargePanel.add(addRecharge);
        rechargePanel.add(rechargeList);
        sidebar.add(rechargePanel);

        sidebar.add(stateItem);
        statePanel.add(addState);
        statePanel.add(stateList);
        sidebar.add(statePanel);

        sidebar.add(rankItem);
        rankPanel.add(addRank);
        rankPanel.add(rankList);
        sidebar.add(rankPanel);

        sidebar.add(adminItem);
        adminPanel.add(addAdmin);
        adminPanel.add(adminList);
        sidebar.add(adminPanel);

        sidebar.add(recycleBinItem);
        recycleBinPanel.add(userRecycleBinList);
        recycleBinPanel.add(orderRecycleBinList);
        recycleBinPanel.add(carRecycleBinList);
        recycleBinPanel.add(carBrandRecycleBinList);
        recycleBinPanel.add(carModelRecycleBinList);
        recycleBinPanel.add(rechargeRecycleBinList);
        recycleBinPanel.add(stateRecycleBinList);
        recycleBinPanel.add(rankRecycleBinList);
        recycleBinPanel.add(adminRecycleBinList);
        sidebar.add(recycleBinPanel);

//        ***************************主面板***************************



//        ImageIcon imageIcon = new ImageIcon("C:\\Users\\mrcap\\IdeaProjects\\CarRentalServiceSystem\\src\\source\\main.jpg");
//        JLabel bgLabel = new JLabel(imageIcon);
//        this.getLayeredPane().add(bgLabel, new Integer(Integer.MIN_VALUE));
//        bgLabel.setBounds(0, 0, imageIcon.getIconWidth(), imageIcon.getIconHeight());
//        this.getContentPane().add(new JLabel());
//        ((JPanel) getContentPane()).setOpaque(false);


//        borrowCarButton.setBounds(200,350,200,50);
//        borrowCarButton.setFont(new java.awt.Font("楷体",1,20));
//        returnCarButton.setBounds(600,350,200,50);
//        returnCarButton.setFont(new java.awt.Font("楷体",1,20));
//
//        mainPanel.add(borrowCarButton);
//        mainPanel.add(returnCarButton);

//        mainPanel.add(bgLabel);
        mainPanel = new Index(admin);
        main.add(mainPanel);

        footer.add(footerPanel);



        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int n = JOptionPane.showConfirmDialog(null, "退出", "真的要退出吗？", JOptionPane.YES_NO_OPTION);
                if (n == 0) {
                    System.exit(0);
                }
            }
        });

        index.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.removeAll();
                main.repaint();
                main.updateUI();

                main.add(mainPanel);
            }
        });

        welcomeUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.removeAll();
                main.repaint();
                main.updateUI();

                main.add(new AdminInfo(admin));
            }
        });

        updatePassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UpdatePassword(admin);
            }
        });

        signOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int n = JOptionPane.showConfirmDialog(null, "退出", "真的要退出吗？", JOptionPane.YES_NO_OPTION);
                if (n == 0) {
                    System.exit(0);
                }
            }
        });

        userItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                orderPanel.setVisible(false);
                carPanel.setVisible(false);
                carTypePanel.setVisible(false);
                rechargePanel.setVisible(false);
                statePanel.setVisible(false);
                rankPanel.setVisible(false);
                adminPanel.setVisible(false);
                recycleBinPanel.setVisible(false);
                orderItem.setBounds(10,120,150,40);
                carItem.setBounds(10,170,150,40);
                carTypeItem.setBounds(10,220,150,40);
                rechargeItem.setBounds(10,270,150,40);
                stateItem.setBounds(10,320,150,40);
                rankItem.setBounds(10,370,150,40);
                adminItem.setBounds(10,420,150,40);
                recycleBinItem.setBounds(10,470,150,40);
                userPanel.setVisible(true);
            }
        });

        addUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.removeAll();
                main.repaint();
                main.updateUI();

                main.add(new AddUser(admin));
            }
        });
        userList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.removeAll();
                main.repaint();
                main.updateUI();

                main.add(new UserList(admin));
            }
        });

        orderItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userPanel.setVisible(false);
                carPanel.setVisible(false);
                carTypePanel.setVisible(false);
                rechargePanel.setVisible(false);
                statePanel.setVisible(false);
                rankPanel.setVisible(false);
                adminPanel.setVisible(false);
                recycleBinPanel.setVisible(false);
                orderItem.setBounds(10,60,150,40);
                carItem.setBounds(10,170,150,40);
                carTypeItem.setBounds(10,220,150,40);
                rechargeItem.setBounds(10,270,150,40);
                stateItem.setBounds(10,320,150,40);
                rankItem.setBounds(10,370,150,40);
                adminItem.setBounds(10,420,150,40);
                recycleBinItem.setBounds(10,470,150,40);
                orderPanel.setVisible(true);
            }
        });

        addOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.removeAll();
                main.repaint();
                main.updateUI();

                main.add(new AddOrderUser(admin));
            }
        });
        orderList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.removeAll();
                main.repaint();
                main.updateUI();

                main.add(new OrderList());
            }
        });


//        ---------------车辆管理------------------
        carItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userPanel.setVisible(false);
                orderPanel.setVisible(false);
                carTypePanel.setVisible(false);
                rechargePanel.setVisible(false);
                statePanel.setVisible(false);
                rankPanel.setVisible(false);
                adminPanel.setVisible(false);
                recycleBinPanel.setVisible(false);
                orderItem.setBounds(10,60,150,40);
                carItem.setBounds(10,110,150,40);
                carTypeItem.setBounds(10,220,150,40);
                rechargeItem.setBounds(10,270,150,40);
                stateItem.setBounds(10,320,150,40);
                rankItem.setBounds(10,370,150,40);
                adminItem.setBounds(10,420,150,40);
                recycleBinItem.setBounds(10,470,150,40);
                carPanel.setVisible(true);
            }
        });

        addCar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.removeAll();
                main.repaint();
                main.updateUI();

                main.add(new AddCar());
            }
        });
        carList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.removeAll();
                main.repaint();
                main.updateUI();

                main.add(new CarList());
            }
        });

//        ---------------车型管理------------------
        carTypeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userPanel.setVisible(false);
                orderPanel.setVisible(false);
                carPanel.setVisible(false);
                rechargePanel.setVisible(false);
                statePanel.setVisible(false);
                rankPanel.setVisible(false);
                adminPanel.setVisible(false);
                recycleBinPanel.setVisible(false);
                orderItem.setBounds(10,60,150,40);
                carItem.setBounds(10,110,150,40);
                carTypeItem.setBounds(10,160,150,40);
                rechargeItem.setBounds(10,270,150,40);
                stateItem.setBounds(10,320,150,40);
                rankItem.setBounds(10,370,150,40);
                adminItem.setBounds(10,420,150,40);
                recycleBinItem.setBounds(10,470,150,40);
                carTypePanel.setVisible(true);
            }
        });

        addCarType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.removeAll();
                main.repaint();
                main.updateUI();

                main.add(new AddCarType());
            }
        });
        carTypeList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.removeAll();
                main.repaint();
                main.updateUI();

                main.add(new CarTypeList());
            }
        });

//        ---------------充值管理------------------
        rechargeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userPanel.setVisible(false);
                orderPanel.setVisible(false);
                carPanel.setVisible(false);
                carTypePanel.setVisible(false);
                statePanel.setVisible(false);
                rankPanel.setVisible(false);
                adminPanel.setVisible(false);
                recycleBinPanel.setVisible(false);
                orderItem.setBounds(10,60,150,40);
                carItem.setBounds(10,110,150,40);
                carTypeItem.setBounds(10,160,150,40);
                rechargeItem.setBounds(10,210,150,40);
                stateItem.setBounds(10,320,150,40);
                rankItem.setBounds(10,370,150,40);
                adminItem.setBounds(10,420,150,40);
                recycleBinItem.setBounds(10,470,150,40);
                rechargePanel.setVisible(true);
            }
        });

        addRecharge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.removeAll();
                main.repaint();
                main.updateUI();

                main.add(new AddRecharge(admin));
            }
        });
        rechargeList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.removeAll();
                main.repaint();
                main.updateUI();

                main.add(new RechargeList());
            }
        });

//        ---------------状态管理------------------
        stateItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userPanel.setVisible(false);
                orderPanel.setVisible(false);
                carPanel.setVisible(false);
                carTypePanel.setVisible(false);
                rechargePanel.setVisible(false);
                rankPanel.setVisible(false);
                adminPanel.setVisible(false);
                recycleBinPanel.setVisible(false);
                orderItem.setBounds(10,60,150,40);
                carItem.setBounds(10,110,150,40);
                carTypeItem.setBounds(10,160,150,40);
                rechargeItem.setBounds(10,210,150,40);
                stateItem.setBounds(10,260,150,40);
                rankItem.setBounds(10,370,150,40);
                adminItem.setBounds(10,420,150,40);
                recycleBinItem.setBounds(10,470,150,40);
                statePanel.setVisible(true);
            }
        });

        addState.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.removeAll();
                main.repaint();
                main.updateUI();

                main.add(new AddState());
            }
        });
        stateList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.removeAll();
                main.repaint();
                main.updateUI();

                main.add(new StateList());
            }
        });

//        ---------------权限管理------------------
        rankItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userPanel.setVisible(false);
                orderPanel.setVisible(false);
                carPanel.setVisible(false);
                carTypePanel.setVisible(false);
                rechargePanel.setVisible(false);
                statePanel.setVisible(false);
                adminPanel.setVisible(false);
                recycleBinPanel.setVisible(false);
                orderItem.setBounds(10,60,150,40);
                carItem.setBounds(10,110,150,40);
                carTypeItem.setBounds(10,160,150,40);
                rechargeItem.setBounds(10,210,150,40);
                stateItem.setBounds(10,260,150,40);
                rankItem.setBounds(10,310,150,40);
                adminItem.setBounds(10,420,150,40);
                recycleBinItem.setBounds(10,470,150,40);
                rankPanel.setVisible(true);
            }
        });

        addRank.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.removeAll();
                main.repaint();
                main.updateUI();

                main.add(new AddRank());
            }
        });
        rankList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.removeAll();
                main.repaint();
                main.updateUI();

                main.add(new RankList());
            }
        });

//        ---------------管理员管理------------------
        adminItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userPanel.setVisible(false);
                orderPanel.setVisible(false);
                carPanel.setVisible(false);
                carTypePanel.setVisible(false);
                rechargePanel.setVisible(false);
                statePanel.setVisible(false);
                rankPanel.setVisible(false);
                recycleBinPanel.setVisible(false);
                orderItem.setBounds(10,60,150,40);
                carItem.setBounds(10,110,150,40);
                carTypeItem.setBounds(10,160,150,40);
                rechargeItem.setBounds(10,210,150,40);
                stateItem.setBounds(10,260,150,40);
                rankItem.setBounds(10,310,150,40);
                adminItem.setBounds(10,360,150,40);
                recycleBinItem.setBounds(10,470,150,40);
                adminPanel.setVisible(true);
            }
        });

        addAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.removeAll();
                main.repaint();
                main.updateUI();

                main.add(new AddAdmin(admin));
            }
        });
        adminList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.removeAll();
                main.repaint();
                main.updateUI();

                main.add(new AdminList(admin));
            }
        });

//        ---------------回收站管理------------------
        recycleBinItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userPanel.setVisible(false);
                orderPanel.setVisible(false);
                carPanel.setVisible(false);
                carTypePanel.setVisible(false);
                rechargePanel.setVisible(false);
                statePanel.setVisible(false);
                rankPanel.setVisible(false);
                adminPanel.setVisible(false);
                orderItem.setBounds(10,60,150,40);
                carItem.setBounds(10,110,150,40);
                carTypeItem.setBounds(10,160,150,40);
                rechargeItem.setBounds(10,210,150,40);
                stateItem.setBounds(10,260,150,40);
                rankItem.setBounds(10,310,150,40);
                adminItem.setBounds(10,360,150,40);
                recycleBinItem.setBounds(10,410,150,40);
                recycleBinPanel.setVisible(true);
            }
        });

        userRecycleBinList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.removeAll();
                main.repaint();
                main.updateUI();

                main.add(new UserRecycleBinList());
            }
        });
        orderRecycleBinList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.removeAll();
                main.repaint();
                main.updateUI();

                main.add(new OrderRecycleBinList());
            }
        });
        carRecycleBinList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.removeAll();
                main.repaint();
                main.updateUI();

                main.add(new CarRecycleBinList());
            }
        });
        carBrandRecycleBinList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.removeAll();
                main.repaint();
                main.updateUI();

                main.add(new CarBrandRecycleBinList());
            }
        });
        carModelRecycleBinList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.removeAll();
                main.repaint();
                main.updateUI();

                main.add(new CarModelRecycleBinList());
            }
        });
        rechargeRecycleBinList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.removeAll();
                main.repaint();
                main.updateUI();

                main.add(new RechargeRecycleBinList());
            }
        });
        stateRecycleBinList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.removeAll();
                main.repaint();
                main.updateUI();

                main.add(new StateRecycleBinList());
            }
        });
        rankRecycleBinList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.removeAll();
                main.repaint();
                main.updateUI();

                main.add(new RankRecycleBinList());
            }
        });
        adminRecycleBinList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.removeAll();
                main.repaint();
                main.updateUI();

                main.add(new AdminRecycleBinList(admin));
            }
        });




        add(menuBar, MyLayout.setValues(0,0,2,1,1,0.08));
        add(sidebar, MyLayout.setValues(0,1,1,1,0.12,0.9));
        add(main, MyLayout.setValues(1,1,1,1,0.88,0.9));
        add(footer, MyLayout.setValues(0,2,2,1,1,0.02));

        setVisible(true);

    }

    /**
     * 统一设置字体，父界面设置之后，所有由父界面进入的子界面都不需要再次设置字体
     */
    private static void InitGlobalFont(Font font) {
        FontUIResource fontRes = new FontUIResource(font);
        for (Enumeration<Object> keys = UIManager.getDefaults().keys();
             keys.hasMoreElements(); ) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                UIManager.put(key, fontRes);
            }
        }
    }

    public static void main(String[] args) {
        try {
//            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.frameBorderStyle.osLookAndFeelDecorated;
            UIManager.put("RootPane.setupButtonVisible", false);
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.frameBorderStyle.translucencyAppleLike;
//            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.frameBorderStyle.generalNoTranslucencyShadow;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
            InitGlobalFont(new Font("楷体", 1, 15));
        } catch (Exception e) {

        }

        new Main(new Admin());
    }
}
