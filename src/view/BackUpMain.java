//package view;
//
//import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
//import util.Admin;
//import util.MyLayout;
//import view.car.AddCar;
//import view.car.CarList;
//import view.carType.AddCarType;
//import view.carType.CarTypeList;
//import view.order.AddOrderUser;
//import view.order.OrderList;
//import view.pubilc.Footer;
//import view.pubilc.UpdatePassword;
//import view.rank.AddRank;
//import view.rank.RankList;
//import view.recharge.AddRecharge;
//import view.recharge.RechargeList;
//import view.state.AddState;
//import view.state.StateList;
//import view.user.AddUser;
//import view.user.UserInfo;
//import view.user.UserList;
//
//import javax.swing.*;
//import javax.swing.plaf.FontUIResource;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.WindowAdapter;
//import java.awt.event.WindowEvent;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Enumeration;
//
//public class BackUpMain {
//    package view;
//
//import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
//import util.Admin;
//import util.MyLayout;
//import view.car.AddCar;
//import view.car.CarList;
//import view.carType.AddCarType;
//import view.carType.CarTypeList;
//import view.recharge.AddRecharge;
//import view.recharge.RechargeList;
//import view.order.AddOrderUser;
//import view.order.OrderList;
//import view.pubilc.Footer;
//import view.pubilc.UpdatePassword;
//import view.rank.AddRank;
//import view.rank.RankList;
//import view.state.AddState;
//import view.state.StateList;
//import view.user.AddUser;
//import view.user.UserInfo;
//import view.user.UserList;
//
//import javax.swing.*;
//import javax.swing.plaf.FontUIResource;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.WindowAdapter;
//import java.awt.event.WindowEvent;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Enumeration;
//
//    public class Main extends JFrame {
////    private JPanel menuBar = new JPanel();
////    private JPanel menuBar = new AdminMenuBar("Kristin");
//
//
//        //        ------------------------顶栏------------------------------------
//        private JPanel menuBar = new JPanel();
//
//        private ImageIcon imageIcon = new ImageIcon("C:\\Users\\mrcap\\IdeaProjects\\CarRentalServiceSystem\\src\\source\\Index.png");
//        private JMenuItem index = new JMenuItem("租车管理系统");
//        private JLabel dateTimeLabel = new JLabel();
//
//        //    private JMenuBar userMenuBar = new JMenuBar();
//        private JMenuItem welcomeUser = new JMenuItem();
//        private JLabel jLabel = new JLabel("|");
//        private JMenuItem updatePassword = new JMenuItem("修改密码");
//        private JLabel jLabel1 = new JLabel("|");
//        private JMenuItem signOut = new JMenuItem("退出");
//
//        private JPanel userInfoPanel = new JPanel();
//
//
//        //        ------------------------侧边栏------------------------------------
//        private JPanel sidebar = new JPanel();
//
//        private JMenuItem user = new JMenuItem("用户管理");
//        private JPanel userPanel = new JPanel();
//        private JMenuItem addUser = new JMenuItem("新增用户");
//        private JMenuItem userList = new JMenuItem("用户列表");
//
//        private JMenuItem order = new JMenuItem("订单管理");
//        private JPanel orderPanel = new JPanel();
//        private JMenuItem addOrder = new JMenuItem("新增订单");
//        private JMenuItem orderList = new JMenuItem("订单列表");
//
//        private JMenuItem car = new JMenuItem("车辆管理");
//        private JPanel carPanel = new JPanel();
//        private JMenuItem addCar = new JMenuItem("新增车辆");
//        private JMenuItem carList = new JMenuItem("车辆列表");
//
//        private JMenuItem carType = new JMenuItem("车型管理");
//        private JPanel carTypePanel = new JPanel();
//        private JMenuItem addCarType = new JMenuItem("新增车型");
//        private JMenuItem carTypeList = new JMenuItem("车型列表");
//
//        private JMenuItem recharge = new JMenuItem("充值管理");
//        private JPanel rechargePanel = new JPanel();
//        private JMenuItem addRecharge = new JMenuItem("新增充值");
//        private JMenuItem rechargeList = new JMenuItem("充值列表");
//
//        private JMenuItem state = new JMenuItem("状态管理");
//        private JPanel statePanel = new JPanel();
//        private JMenuItem addState = new JMenuItem("新增状态");
//        private JMenuItem stateList = new JMenuItem("状态列表");
//
//
//        private JMenuItem rank = new JMenuItem("权限管理");
//        private JPanel rankPanel = new JPanel();
//        private JMenuItem addRank = new JMenuItem("新增权限");
//        private JMenuItem rankList = new JMenuItem("权限列表");
////    private JPanel addRankPanel = new
//
//
//
//
//
//
//
//        private JPanel main = new JPanel();
//        private JPanel mainPanel = new JPanel();
//        private JButton borrowCar = new JButton("租车");
//        private JButton returnCar = new JButton("还车");
//
//        private JPanel addUserPanel = new AddUser();
//        private JPanel userListPanel = new UserList();
//
//        private JPanel addOrderPanel = new JPanel();
//        private JPanel orderListPanel = new OrderList();
//
//        private JPanel addCarPanel = new AddCar();
//        private JPanel carListPanel = new CarList();
//
//        private JPanel addCarTypePanel = new AddCarType();
//        private JPanel carTypeListPanel = new CarTypeList();
//
//        private JPanel addRechargePanel;
//        private JPanel rechargeListPanel = new RechargeList();
//
//        private JPanel addStatePanel = new AddState();
//        private JPanel stateListPanel = new StateList();
//
//        private JPanel addRankPanel = new AddRank();
//        private JPanel rankListPanel = new RankList();
//
//
//        private JPanel footer = new JPanel();
//        private JPanel footerPanel = new Footer();
//
////    private JButton indexButton = new JButton("首页");
//
//        private JButton jButton = new JButton("car");
//        private JButton jButton1 = new JButton("car");
//        private JButton jButton2 = new JButton("car");
//        private JButton loginButton = new JButton("登录");
//
//        public Main(Admin admin) {
//            setSize(1600, 1000);
//            setLocationRelativeTo(null);
//            setLayout(new GridBagLayout());
//
//            menuBar.setLayout(null);
//
//            sidebar.setLayout(null);
////        userPanel.setLayout(null);
////        orderPanel.setLayout(null);
////        carPanel.setLayout(null);
////        carTypePanel.setLayout(null);
////        rechargePanel.setLayout(null);
////        statePanel.setLayout(null);
////        rankPanel.setLayout(null);
//
//            main.setLayout(null);
//            footer.setLayout(null);
////        indexButton.setBounds(0,0,100,40);
//            menuBar.setBackground(Color.yellow);
//            sidebar.setBackground(Color.pink);
////        main.setBackground(Color.blue);
//            footerPanel.setBackground(Color.green);
//
////        menuBar.setBounds(0,0,1600,80);
////        sidebar.setBounds(0,80,200,900);
////        main.setBounds(200,72,1450,900);
////        footer.setBounds(0,980,1600,20);
//
//
////        ***************************顶栏***************************
//
//            index.setBounds(200,6,180, 60);
//            index.setFont(new java.awt.Font("楷体",1,24));
////        index.setForeground(Color.white);
//            dateTimeLabel.setBounds(1320,0,200,30);
////        indexButton.setForeground(Color.white);
////        indexButton.setBackground(Color.black);
//
//            Date date = new Date();
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
//            String string = simpleDateFormat.format(date);
//            dateTimeLabel.setText(string);
//            dateTimeLabel.setFont(new java.awt.Font("楷体",1,13));
////        dateTimeLabel.setForeground(Color.white);
//
////        welcomeUser.setText("你好，" + userName);
////        welcomeUserLabel.setBounds();
//
//            welcomeUser.setText("您好，" + admin.getAdminName());
//            welcomeUser.setBounds(1085,40,180,30);
//            welcomeUser.setFont(new java.awt.Font("楷体",1,18));
////        welcomeUser.setForeground(Color.white);
//            jLabel.setBounds(1275,40,10,30);
//            jLabel.setFont(new java.awt.Font("楷体",1,18));
//            updatePassword.setBounds(1285,40,120,30);
//            updatePassword.setFont(new java.awt.Font("楷体",1,18));
////        updatePassword.setForeground(Color.white);
//            jLabel1.setBounds(1415,40,10,30);
//            jLabel1.setFont(new java.awt.Font("楷体",1,18));
//            signOut.setBounds(15 + jLabel1.getX(),40,80,30);
//            signOut.setFont(new java.awt.Font("楷体",1,18));
//            signOut.setForeground(Color.RED);
//
//            userInfoPanel = new UserInfo(admin);
//
//            menuBar.add(index);
//            menuBar.add(dateTimeLabel);
//            menuBar.add(welcomeUser);
//            menuBar.add(jLabel);
//            menuBar.add(updatePassword);
//            menuBar.add(jLabel1);
//            menuBar.add(signOut);
//
////        ***************************侧边栏***************************
//
////        ---------------用户管理------------------
//            user.setBounds(10,10,150,40);
//            user.setFont(new java.awt.Font("楷体",1,20));
////        user.setForeground(Color.white);
//
//            userPanel.setBackground(Color.pink);
//            userPanel.setBounds(20,50,140,60);
//            addUser.setBounds(0 ,0,140,30);
//            addUser.setFont(new java.awt.Font("楷体",2,18));
////        addUser.setForeground(Color.white);
//            userList.setBounds(0,30,140,30);
//            userList.setFont(new java.awt.Font("楷体",2,18));
////        userList.setForeground(Color.white);
//
////        ---------------订单管理------------------
//            order.setBounds(10,60,150,40);
//            order.setFont(new java.awt.Font("楷体",1,20));
//
//            orderPanel.setBackground(Color.pink);
//            orderPanel.setBounds(20,100,140,60);
//            addOrder.setBounds(0 ,0,140,30);
//            addOrder.setFont(new java.awt.Font("楷体",2,18));
//            orderList.setBounds(0,30,140,30);
//            orderList.setFont(new java.awt.Font("楷体",2,18));
//
//            addOrderPanel = new AddOrderUser(admin);
//
////        ---------------车辆管理------------------
//            car.setBounds(10,110,150,40);
//            car.setFont(new java.awt.Font("楷体",1,20));
//
//            carPanel.setBackground(Color.pink);
//            carPanel.setBounds(20,150,140,60);
//            addCar.setBounds(0 ,0,140,30);
//            addCar.setFont(new java.awt.Font("楷体",2,18));
//            carList.setBounds(0,30,140,30);
//            carList.setFont(new java.awt.Font("楷体",2,18));
//
////        ---------------车型管理------------------
//            carType.setBounds(10,160,150,40);
//            carType.setFont(new java.awt.Font("楷体",1,20));
//
//            carTypePanel.setBackground(Color.pink);
//            carTypePanel.setBounds(20,200,140,60);
//            addCarType.setBounds(0 ,0,140,30);
//            addCarType.setFont(new java.awt.Font("楷体",2,18));
//            carTypeList.setBounds(0,30,140,30);
//            carTypeList.setFont(new java.awt.Font("楷体",2,18));
//
////        ---------------充值管理------------------
//
//            recharge.setBounds(10,210,150,40);
//            recharge.setFont(new java.awt.Font("楷体",1,20));
//
//            rechargePanel.setBackground(Color.pink);
//            rechargePanel.setBounds(20,250,140,60);
//            addRecharge.setBounds(0 ,0,140,30);
//            addRecharge.setFont(new java.awt.Font("楷体",2,18));
//            rechargeList.setBounds(0,30,140,30);
//            rechargeList.setFont(new java.awt.Font("楷体",2,18));
//
//            addRechargePanel = new AddRecharge(admin);
////        ---------------状态管理------------------
//
//            state.setBounds(10,260,150,40);
//            state.setFont(new java.awt.Font("楷体",1,20));
//
//            statePanel.setBackground(Color.pink);
//            statePanel.setBounds(20,300,140,60);
//            addState.setBounds(0 ,0,140,30);
//            addState.setFont(new java.awt.Font("楷体",2,18));
//            stateList.setBounds(0,30,140,30);
//            stateList.setFont(new java.awt.Font("楷体",2,18));
//
////        ---------------权限管理------------------
//
//
//            rank.setBounds(10,310,150,40);
//            rank.setFont(new java.awt.Font("楷体",1,20));
//
//            rankPanel.setBackground(Color.pink);
//            rankPanel.setBounds(20,350,140,60);
//            addRank.setBounds(0 ,0,140,30);
//            addRank.setFont(new java.awt.Font("楷体",2,18));
//            rankList.setBounds(0,30,140,30);
//            rankList.setFont(new java.awt.Font("楷体",2,18));
//
//            rank.setVisible(false);
//            if (admin.getAdminRank() == 1) {
//                rank.setVisible(true);
//            }
////        userPanel.setBackground(Color.magenta);
//
////        state.setBounds(10,car.getY() + car.getHeight() + 10,this.getWidth(),40);
////        state.setFont(new java.awt.Font("楷体",3,20));
////
////        statePanel.setBounds(0,state.getY() + state.getHeight(),this.getWidth(),100);
////        addState.setBounds(0,statePanel.getY() + 10,100,40);
////        addState.setFont(new java.awt.Font("楷体",2,18));
////        stateList.setBounds(0,addState.getY() + 50,this.getWidth(),40);
////        stateList.setFont(new java.awt.Font("楷体",2,18));
//
//
//            userPanel.setVisible(false);
//            orderPanel.setVisible(false);
//            carPanel.setVisible(false);
//            carTypePanel.setVisible(false);
//            rechargePanel.setVisible(false);
//            statePanel.setVisible(false);
//            rankPanel.setVisible(false);
//
//            sidebar.add(user);
//            userPanel.add(addUser);
//            userPanel.add(userList);
//            sidebar.add(userPanel);
//
//            sidebar.add(order);
//            orderPanel.add(addOrder);
//            orderPanel.add(orderList);
//            sidebar.add(orderPanel);
//
//            sidebar.add(car);
//            carPanel.add(addCar);
//            carPanel.add(carList);
//            sidebar.add(carPanel);
//
//            sidebar.add(carType);
//            carTypePanel.add(addCarType);
//            carTypePanel.add(carTypeList);
//            sidebar.add(carTypePanel);
//
//            sidebar.add(recharge);
//            rechargePanel.add(addRecharge);
//            rechargePanel.add(rechargeList);
//            sidebar.add(rechargePanel);
//
//            sidebar.add(state);
//            statePanel.add(addState);
//            statePanel.add(stateList);
//            sidebar.add(statePanel);
//
//            sidebar.add(rank);
//            rankPanel.add(addRank);
//            rankPanel.add(rankList);
//            sidebar.add(rankPanel);
//
////        sidebar.add(car);
////        carPanel.add(addCar);
////        carPanel.add(carList);
////        sidebar.add(carPanel);
////        sidebar.add(state);
////        statePanel.add(addState);
////        statePanel.add(stateList);
////        sidebar.add(statePanel);
//
////        ***************************主面板***************************
//
//
//
//            ImageIcon imageIcon = new ImageIcon("C:\\Users\\mrcap\\IdeaProjects\\CarRentalServiceSystem\\src\\source\\main.jpg");
//            JLabel bgLabel = new JLabel(imageIcon);
//            this.getLayeredPane().add(bgLabel, new Integer(Integer.MIN_VALUE));
//            bgLabel.setBounds(0, 0, imageIcon.getIconWidth(), imageIcon.getIconHeight());
////        this.getContentPane().add(new JLabel());
//            ((JPanel) getContentPane()).setOpaque(false);
//
//            mainPanel.add(bgLabel);
//            main.add(mainPanel);
////        main.add(userInfoPanel);
//
//            addUserPanel.setVisible(false);
//            userListPanel.setVisible(false);
//
//            addOrderPanel.setVisible(false);
//            orderListPanel.setVisible(false);
//
//            addCarPanel.setVisible(false);
//            carListPanel.setVisible(false);
//
//            addCarTypePanel.setVisible(false);
//            carTypeListPanel.setVisible(false);
//
//            addRechargePanel.setVisible(false);
//            rechargeListPanel.setVisible(false);
//
//            addStatePanel.setVisible(false);
//            stateListPanel.setVisible(false);
//
//            addRankPanel.setVisible(false);
//            rankListPanel.setVisible(false);
//
//
//            main.add(addUserPanel);
//            main.add(userListPanel);
//            main.add(addOrderPanel);
//            main.add(orderListPanel);
//            main.add(addCarPanel);
//            main.add(carListPanel);
//            main.add(addCarTypePanel);
//            main.add(carTypeListPanel);
//            main.add(addRechargePanel);
//            main.add(rechargeListPanel);
//            main.add(addStatePanel);
//            main.add(stateListPanel);
//            main.add(addRankPanel);
//            main.add(rankListPanel);
//
//
////        add(indexButton);
////        main.add(carList);
//
//            footer.add(footerPanel);
//            add(menuBar, MyLayout.setValues(0,0,2,1,1,0.08));
//            add(sidebar, MyLayout.setValues(0,1,1,1,0.12,0.9));
//            add(main, MyLayout.setValues(1,1,1,1,0.88,0.9));
//            add(footer, MyLayout.setValues(0,2,2,1,1,0.02));
//
////        add(menuBar);
////        add(sidebar);
////        add(main);
////        add(footer);
//            setVisible(true);
//
//
//            addWindowListener(new WindowAdapter() {
//                @Override
//                public void windowClosing(WindowEvent e) {
//                    int n = JOptionPane.showConfirmDialog(null, "退出", "真的要退出吗？", JOptionPane.YES_NO_OPTION);
//                    if (n == 0) {
//                        System.exit(0);
//                    }
//                }
//            });
//
//            index.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    addUserPanel.setVisible(false);
//                    userListPanel.setVisible(false);
//
//                    addOrderPanel.setVisible(false);
//                    orderListPanel.setVisible(false);
//
//                    addCarPanel.setVisible(false);
//                    carListPanel.setVisible(false);
//
//                    addCarTypePanel.setVisible(false);
//                    carTypeListPanel.setVisible(false);
//
//                    addRechargePanel.setVisible(false);
//                    rechargeListPanel.setVisible(false);
//
//                    addStatePanel.setVisible(false);
//                    stateListPanel.setVisible(false);
//
//                    addRankPanel.setVisible(false);
//                    rankListPanel.setVisible(false);
//
//                    mainPanel.setVisible(true);
//                }
//            });
//
//            welcomeUser.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//
//                    mainPanel.setVisible(false);
//
//                    addUserPanel.setVisible(false);
//                    userListPanel.setVisible(false);
//
//                    addOrderPanel.setVisible(false);
//                    orderListPanel.setVisible(false);
//
//                    addCarPanel.setVisible(false);
//                    carListPanel.setVisible(false);
//
//                    addCarTypePanel.setVisible(false);
//                    carTypeListPanel.setVisible(false);
//
//                    addRechargePanel.setVisible(false);
//                    rechargeListPanel.setVisible(false);
//
//                    addStatePanel.setVisible(false);
//                    stateListPanel.setVisible(false);
//
//                    addRankPanel.setVisible(false);
//                    rankListPanel.setVisible(false);
//
//                    userInfoPanel.setVisible(true);
//                }
//            });
//
//            updatePassword.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    new UpdatePassword(admin);
//                }
//            });
//
//            signOut.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    int n = JOptionPane.showConfirmDialog(null, "退出", "真的要退出吗？", JOptionPane.YES_NO_OPTION);
//                    if (n == 0) {
//                        System.exit(0);
//                    }
//                }
//            });
//
//            user.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    orderPanel.setVisible(false);
//                    carPanel.setVisible(false);
//                    carTypePanel.setVisible(false);
//                    rechargePanel.setVisible(false);
//                    statePanel.setVisible(false);
//                    rankPanel.setVisible(false);
//                    order.setBounds(10,120,150,40);
//                    car.setBounds(10,170,150,40);
//                    carType.setBounds(10,220,150,40);
//                    recharge.setBounds(10,270,150,40);
//                    state.setBounds(10,320,150,40);
//                    rank.setBounds(10,370,150,40);
//                    userPanel.setVisible(true);
//                }
//            });
//
//            addUser.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//
//                    mainPanel.setVisible(false);
//
//                    addUserPanel.setVisible(true);
//                    userListPanel.setVisible(false);
//
//                    addOrderPanel.setVisible(false);
//                    orderListPanel.setVisible(false);
//
//                    addCarPanel.setVisible(false);
//                    carListPanel.setVisible(false);
//
//                    addCarTypePanel.setVisible(false);
//                    carTypeListPanel.setVisible(false);
//
//                    addRechargePanel.setVisible(false);
//                    rechargeListPanel.setVisible(false);
//
//                    addStatePanel.setVisible(false);
//                    stateListPanel.setVisible(false);
//
//                    addRankPanel.setVisible(false);
//                    rankListPanel.setVisible(false);
//                }
//            });
//            userList.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//
//                    mainPanel.setVisible(false);
//
//                    addUserPanel.setVisible(false);
//                    userListPanel.setVisible(true);
//
//                    addOrderPanel.setVisible(false);
//                    orderListPanel.setVisible(false);
//
//                    addCarPanel.setVisible(false);
//                    carListPanel.setVisible(false);
//
//                    addCarTypePanel.setVisible(false);
//                    carTypeListPanel.setVisible(false);
//
//                    addRechargePanel.setVisible(false);
//                    rechargeListPanel.setVisible(false);
//
//                    addStatePanel.setVisible(false);
//                    stateListPanel.setVisible(false);
//
//                    addRankPanel.setVisible(false);
//                    rankListPanel.setVisible(false);
//                }
//            });
//
//            order.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    userPanel.setVisible(false);
//                    carPanel.setVisible(false);
//                    carTypePanel.setVisible(false);
//                    rechargePanel.setVisible(false);
//                    statePanel.setVisible(false);
//                    rankPanel.setVisible(false);
//                    order.setBounds(10,60,150,40);
//                    car.setBounds(10,170,150,40);
//                    carType.setBounds(10,220,150,40);
//                    recharge.setBounds(10,270,150,40);
//                    state.setBounds(10,320,150,40);
//                    rank.setBounds(10,370,150,40);
//                    orderPanel.setVisible(true);
//                }
//            });
//
//            addOrder.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//
//                    mainPanel.setVisible(false);
//
//                    addUserPanel.setVisible(false);
//                    userListPanel.setVisible(false);
//
//                    addOrderPanel.setVisible(true);
//                    orderListPanel.setVisible(false);
//
//                    addCarPanel.setVisible(false);
//                    carListPanel.setVisible(false);
//
//                    addCarTypePanel.setVisible(false);
//                    carTypeListPanel.setVisible(false);
//
//                    addRechargePanel.setVisible(false);
//                    rechargeListPanel.setVisible(false);
//
//                    addStatePanel.setVisible(false);
//                    stateListPanel.setVisible(false);
//
//                    addRankPanel.setVisible(false);
//                    rankListPanel.setVisible(false);
//                }
//            });
//            orderList.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//
//                    mainPanel.setVisible(false);
//
//                    addUserPanel.setVisible(false);
//                    userListPanel.setVisible(false);
//
//                    addOrderPanel.setVisible(false);
//                    orderListPanel.setVisible(true);
//
//                    addCarPanel.setVisible(false);
//                    carListPanel.setVisible(false);
//
//                    addCarTypePanel.setVisible(false);
//                    carTypeListPanel.setVisible(false);
//
//                    addRechargePanel.setVisible(false);
//                    rechargeListPanel.setVisible(false);
//
//                    addStatePanel.setVisible(false);
//                    stateListPanel.setVisible(false);
//
//                    addRankPanel.setVisible(false);
//                    rankListPanel.setVisible(false);
//                }
//            });
//
//
////        ---------------车辆管理------------------
//            car.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    userPanel.setVisible(false);
//                    orderPanel.setVisible(false);
//                    carTypePanel.setVisible(false);
//                    rechargePanel.setVisible(false);
//                    statePanel.setVisible(false);
//                    rankPanel.setVisible(false);
//                    order.setBounds(10,60,150,40);
//                    car.setBounds(10,110,150,40);
//                    carType.setBounds(10,220,150,40);
//                    recharge.setBounds(10,270,150,40);
//                    state.setBounds(10,320,150,40);
//                    rank.setBounds(10,370,150,40);
//                    carPanel.setVisible(true);
//                }
//            });
//
//            addCar.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//
//                    mainPanel.setVisible(false);
//
//                    addUserPanel.setVisible(false);
//                    userListPanel.setVisible(false);
//
//                    addOrderPanel.setVisible(false);
//                    orderListPanel.setVisible(false);
//
//                    addCarPanel.setVisible(true);
//                    carListPanel.setVisible(false);
//
//                    addCarTypePanel.setVisible(false);
//                    carTypeListPanel.setVisible(false);
//
//                    addRechargePanel.setVisible(false);
//                    rechargeListPanel.setVisible(false);
//
//                    addStatePanel.setVisible(false);
//                    stateListPanel.setVisible(false);
//
//                    addRankPanel.setVisible(false);
//                    rankListPanel.setVisible(false);
//                }
//            });
//            carList.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//
//                    mainPanel.setVisible(false);
//
//                    addUserPanel.setVisible(false);
//                    userListPanel.setVisible(false);
//
//                    addOrderPanel.setVisible(false);
//                    orderListPanel.setVisible(false);
//
//                    addCarPanel.setVisible(false);
//                    carListPanel.setVisible(true);
//
//                    addCarTypePanel.setVisible(false);
//                    carTypeListPanel.setVisible(false);
//
//                    addRechargePanel.setVisible(false);
//                    rechargeListPanel.setVisible(false);
//
//                    addStatePanel.setVisible(false);
//                    stateListPanel.setVisible(false);
//
//                    addRankPanel.setVisible(false);
//                    rankListPanel.setVisible(false);
//                }
//            });
//
////        ---------------车型管理------------------
//            carType.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    userPanel.setVisible(false);
//                    orderPanel.setVisible(false);
//                    carPanel.setVisible(false);
//                    rechargePanel.setVisible(false);
//                    statePanel.setVisible(false);
//                    rankPanel.setVisible(false);
//                    order.setBounds(10,60,150,40);
//                    car.setBounds(10,110,150,40);
//                    carType.setBounds(10,160,150,40);
//                    recharge.setBounds(10,270,150,40);
//                    state.setBounds(10,320,150,40);
//                    rank.setBounds(10,370,150,40);
//                    carTypePanel.setVisible(true);
//                }
//            });
//
//            addCarType.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//
//                    mainPanel.setVisible(false);
//
//                    addUserPanel.setVisible(false);
//                    userListPanel.setVisible(false);
//
//                    addOrderPanel.setVisible(false);
//                    orderListPanel.setVisible(false);
//
//                    addCarPanel.setVisible(false);
//                    carListPanel.setVisible(false);
//
//                    addCarTypePanel.setVisible(true);
//                    carTypeListPanel.setVisible(false);
//
//                    addRechargePanel.setVisible(false);
//                    rechargeListPanel.setVisible(false);
//
//                    addStatePanel.setVisible(false);
//                    stateListPanel.setVisible(false);
//
//                    addRankPanel.setVisible(false);
//                    rankListPanel.setVisible(false);
//                }
//            });
//            carTypeList.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//
//                    mainPanel.setVisible(false);
//
//                    addUserPanel.setVisible(false);
//                    userListPanel.setVisible(false);
//
//                    addOrderPanel.setVisible(false);
//                    orderListPanel.setVisible(false);
//
//                    addCarPanel.setVisible(false);
//                    carListPanel.setVisible(false);
//
//                    addCarTypePanel.setVisible(false);
//                    carTypeListPanel.setVisible(true);
//
//                    addRechargePanel.setVisible(false);
//                    rechargeListPanel.setVisible(false);
//
//                    addStatePanel.setVisible(false);
//                    stateListPanel.setVisible(false);
//
//                    addRankPanel.setVisible(false);
//                    rankListPanel.setVisible(false);
//                }
//            });
//
////        ---------------地点管理------------------
//            recharge.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    userPanel.setVisible(false);
//                    orderPanel.setVisible(false);
//                    carPanel.setVisible(false);
//                    carTypePanel.setVisible(false);
//                    statePanel.setVisible(false);
//                    rankPanel.setVisible(false);
//                    order.setBounds(10,60,150,40);
//                    car.setBounds(10,110,150,40);
//                    carType.setBounds(10,160,150,40);
//                    recharge.setBounds(10,210,150,40);
//                    state.setBounds(10,320,150,40);
//                    rank.setBounds(10,370,150,40);
//                    rechargePanel.setVisible(true);
//                }
//            });
//
//            addRecharge.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//
//                    mainPanel.setVisible(false);
//
//                    addUserPanel.setVisible(false);
//                    userListPanel.setVisible(false);
//
//                    addOrderPanel.setVisible(false);
//                    orderListPanel.setVisible(false);
//
//                    addCarPanel.setVisible(false);
//                    carListPanel.setVisible(false);
//
//                    addCarTypePanel.setVisible(false);
//                    carTypeListPanel.setVisible(false);
//
//                    addRechargePanel.setVisible(true);
//                    rechargeListPanel.setVisible(false);
//
//                    addStatePanel.setVisible(false);
//                    stateListPanel.setVisible(false);
//
//                    addRankPanel.setVisible(false);
//                    rankListPanel.setVisible(false);
//                }
//            });
//            rechargeList.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//
//                    mainPanel.setVisible(false);
//
//                    addUserPanel.setVisible(false);
//                    userListPanel.setVisible(false);
//
//                    addOrderPanel.setVisible(false);
//                    orderListPanel.setVisible(false);
//
//                    addCarPanel.setVisible(false);
//                    carListPanel.setVisible(false);
//
//                    addCarTypePanel.setVisible(false);
//                    carTypeListPanel.setVisible(false);
//
//                    addRechargePanel.setVisible(false);
//                    rechargeListPanel.setVisible(true);
//
//                    addStatePanel.setVisible(false);
//                    stateListPanel.setVisible(false);
//
//                    addRankPanel.setVisible(false);
//                    rankListPanel.setVisible(false);
//                }
//            });
//
////        ---------------状态管理------------------
//            state.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    userPanel.setVisible(false);
//                    orderPanel.setVisible(false);
//                    carPanel.setVisible(false);
//                    carTypePanel.setVisible(false);
//                    rechargePanel.setVisible(false);
//                    rankPanel.setVisible(false);
//                    order.setBounds(10,60,150,40);
//                    car.setBounds(10,110,150,40);
//                    carType.setBounds(10,160,150,40);
//                    recharge.setBounds(10,210,150,40);
//                    state.setBounds(10,260,150,40);
//                    rank.setBounds(10,370,150,40);
//                    statePanel.setVisible(true);
//                }
//            });
//
//            addState.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//
//                    mainPanel.setVisible(false);
//
//                    addUserPanel.setVisible(false);
//                    userListPanel.setVisible(false);
//
//                    addOrderPanel.setVisible(false);
//                    orderListPanel.setVisible(false);
//
//                    addCarPanel.setVisible(false);
//                    carListPanel.setVisible(false);
//
//                    addCarTypePanel.setVisible(false);
//                    carTypeListPanel.setVisible(false);
//
//                    addRechargePanel.setVisible(false);
//                    rechargeListPanel.setVisible(false);
//
//                    addStatePanel.setVisible(true);
//                    stateListPanel.setVisible(false);
//
//                    addRankPanel.setVisible(false);
//                    rankListPanel.setVisible(false);
//                }
//            });
//            stateList.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//
//                    mainPanel.setVisible(false);
//
//                    addUserPanel.setVisible(false);
//                    userListPanel.setVisible(false);
//
//                    addOrderPanel.setVisible(false);
//                    orderListPanel.setVisible(false);
//
//                    addCarPanel.setVisible(false);
//                    carListPanel.setVisible(false);
//
//                    addCarTypePanel.setVisible(false);
//                    carTypeListPanel.setVisible(false);
//
//                    addRechargePanel.setVisible(false);
//                    rechargeListPanel.setVisible(false);
//
//                    addStatePanel.setVisible(false);
//                    stateListPanel.setVisible(true);
//
//                    addRankPanel.setVisible(false);
//                    rankListPanel.setVisible(false);
//                }
//            });
//
////        ---------------权限管理------------------
//            rank.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    userPanel.setVisible(false);
//                    orderPanel.setVisible(false);
//                    carPanel.setVisible(false);
//                    carTypePanel.setVisible(false);
//                    rechargePanel.setVisible(false);
//                    statePanel.setVisible(false);
//                    order.setBounds(10,60,150,40);
//                    car.setBounds(10,110,150,40);
//                    carType.setBounds(10,160,150,40);
//                    recharge.setBounds(10,210,150,40);
//                    state.setBounds(10,260,150,40);
//                    rank.setBounds(10,310,150,40);
//                    rankPanel.setVisible(true);
//                }
//            });
//
//            addRank.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//
//                    mainPanel.setVisible(false);
//
//                    addUserPanel.setVisible(false);
//                    userListPanel.setVisible(false);
//
//                    addOrderPanel.setVisible(false);
//                    orderListPanel.setVisible(false);
//
//                    addCarPanel.setVisible(false);
//                    carListPanel.setVisible(false);
//
//                    addCarTypePanel.setVisible(false);
//                    carTypeListPanel.setVisible(false);
//
//                    addRechargePanel.setVisible(false);
//                    rechargeListPanel.setVisible(false);
//
//                    addStatePanel.setVisible(false);
//                    stateListPanel.setVisible(false);
//
//                    addRankPanel.setVisible(true);
//                    rankListPanel.setVisible(false);
//                }
//            });
//            rankList.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//
//                    mainPanel.setVisible(false);
//
//                    addUserPanel.setVisible(false);
//                    userListPanel.setVisible(false);
//
//                    addOrderPanel.setVisible(false);
//                    orderListPanel.setVisible(false);
//
//                    addCarPanel.setVisible(false);
//                    carListPanel.setVisible(false);
//
//                    addCarTypePanel.setVisible(false);
//                    carTypeListPanel.setVisible(false);
//
//                    addRechargePanel.setVisible(false);
//                    rechargeListPanel.setVisible(false);
//
//                    addStatePanel.setVisible(false);
//                    stateListPanel.setVisible(false);
//
//                    addRankPanel.setVisible(false);
//                    rankListPanel.setVisible(true);
//                }
//            });
//
//        }
//
//        /**
//         * 统一设置字体，父界面设置之后，所有由父界面进入的子界面都不需要再次设置字体
//         */
//        private static void InitGlobalFont(Font font) {
//            FontUIResource fontRes = new FontUIResource(font);
//            for (Enumeration<Object> keys = UIManager.getDefaults().keys();
//                 keys.hasMoreElements(); ) {
//                Object key = keys.nextElement();
//                Object value = UIManager.get(key);
//                if (value instanceof FontUIResource) {
//                    UIManager.put(key, fontRes);
//                }
//            }
//        }
//
//        public static void main(String[] args) {
//            try {
////            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.frameBorderStyle.osLookAndFeelDecorated;
//                UIManager.put("RootPane.setupButtonVisible", false);
//                BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.frameBorderStyle.translucencyAppleLike;
////            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.frameBorderStyle.generalNoTranslucencyShadow;
//                org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
//                InitGlobalFont(new Font("楷体", 1, 15));
//            } catch (Exception e) {
//
//            }
//
//            new view.Main(new Admin());
//        }
//    }
//
//}
