package StartSys;
import SqlTran.SqlManage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NormalUserUI {
    public JFrame frame;
    public JFrame normal_frame;
    public JFrame train_frame;
    public JFrame numal_frame;
    private JPanel contentpane;
    private JTextField sele_ticketno_text;
    private JRadioButton personal_order;
    private JRadioButton group_order;
    private JTextField purchase_num_text;
    private int surplus_ticket_amounts;
    private JTextField deleorder_text_orderno;

    private JTextField ordersele_label_text;
    private JTextField numalsnodata_text;
    private JFrame ticket_frame;
    private JTextField purchase_number_text;
    private JFrame order_frame;
    private JTextField ordernodata_label;
    private JTextField orderticketnodata_label;
    private JTextField ordernumberdata_label;
    private JTextField orderamounodata_label;
    private JTextField orderticketno1data_label;
    private JTextField deleticketdata_label;
    private JTextField deleamounodata_label;

    public NormalUserUI(){
        init_normaiUI();
        FrameManage.NormalSelectframe=frame;
        frame.setVisible(true);
    }

    private void init_normaiUI()
    {
        frame=new JFrame();
        frame.setTitle("用户选择功能");
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(MainUI.class.getResource("/image/cust.png")));
        frame.setBounds(400,200,720,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentpane=new JPanel();
        frame.setContentPane(contentpane);
        contentpane.setLayout(null);

        JButton goback_button=new JButton("返回");
        goback_button.setBounds(500,480,120,40);
        goback_button.setBackground(Color.GREEN);
        contentpane.add(goback_button);
        goback_button.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                frame.dispose();
                FrameManage.Active_Mainframe();
            }
        });

        JLabel welcome_normal=new JLabel("尊敬的用户你好，请选择一个功能");
        welcome_normal.setBounds(200,50,400,40);
        welcome_normal.setFont(new Font("宋体",Font.BOLD,22));
        welcome_normal.setForeground(Color.BLUE);
        contentpane.add(welcome_normal);

        JButton normal_button=new JButton("用户购买车票页面");
        normal_button.setBounds(250,190,200,40);
        contentpane.add(normal_button);
        normal_button.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                frame.dispose();
                initialize();
                normal_frame.setVisible(true);
            }
        });

        JButton ticketqutui_button=new JButton("退票/改签窗口");
        ticketqutui_button.setBounds(250,260,200,40);
        contentpane.add(ticketqutui_button);
        ticketqutui_button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                init_ticketui();
                ticket_frame.setVisible(true);
            }
        });

        JButton train_button=new JButton("列车全部信息");
        train_button.setBounds(250,120,200,40);
        contentpane.add(train_button);
        train_button.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                frame.dispose();
                init_trainfoall();
                train_frame.setVisible(true);
            }
        });

        JButton numberorder_button=new JButton("查看已支付车票订单");
        numberorder_button.setBounds(250,330,200,40);
        contentpane.add(numberorder_button);
        numberorder_button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                init_orderall();
                order_frame.setVisible(true);
            }
        });

        JButton numal_button=new JButton("基本信息/注销");
        numal_button.setBounds(250,400,200,40);
        contentpane.add(numal_button);
        numal_button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                init_numalxinxi();
                numal_frame.setVisible(true);

            }
        });
    }


    //1.列车全部信息
    private void init_trainfoall()
    {
        train_frame=new JFrame();
        train_frame.setTitle("用户界面");
        train_frame.setIconImage(Toolkit.getDefaultToolkit().getImage(MainUI.class.getResource("/image/cust.png")));
        train_frame.setBounds(400,200,850,650);
        train_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentpane=new JPanel();
        train_frame.setContentPane(contentpane);
        contentpane.setLayout(null);
        JLabel anniu=new JLabel("查询全部车票信息：");
        anniu.setFont(new Font("宋体",Font.BOLD,20));
        anniu.setForeground(Color.BLUE);
        anniu.setBounds(20,10,350,30);
        contentpane.add(anniu);

        JButton gohui_button=new JButton("返回");
        gohui_button.setBounds(660,500,120,40);
        gohui_button.setBackground(Color.GREEN);
        contentpane.add(gohui_button);
        gohui_button.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                train_frame.dispose();
                FrameManage.Active_NormalUserUI();
            }
        });

        JButton sousuo_button=new JButton("查询");
        sousuo_button.setBounds(40,500,120,50);
        contentpane.add(sousuo_button);
        final JLabel selename_label=new JLabel("--------------------------------name--------------------------");
        selename_label.setFont(new Font("宋体",Font.PLAIN,18));
        selename_label.setBounds(40,80,800,40);
        contentpane.add(selename_label);
        final JLabel seledata_label=new JLabel("--------------------------data-------------------");
        seledata_label.setFont(new Font("宋体",Font.PLAIN,21));
        seledata_label.setBounds(40,120,600,300);
        contentpane.add(seledata_label);

        sousuo_button.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                System.out.println("正在查询数据....................."); // Changed print message to Chinese
                SqlWork ssw = new SqlWork();
                String name="没有找到您查询的数据";
                String data="没有找到您查询的数据";
                ssw.sql = "select * from Ticket ";
                System.out.println(ssw.sql);
                if(SqlManage.Select1(ssw) > 0)
                { // Changed condition to check if there are results
                    StringBuilder names = new StringBuilder();
                    for (String n : ssw.namelist)
                    {
                        names.append(n).append("\n"); // Append a newline after each column name
                    }
                    selename_label.setText(names.toString());
                    StringBuilder tableHtml = new StringBuilder("<html><table border='1'>");
                    for (int i=ssw.datalist.size()-1; i<ssw.datalist.size(); i++) {
                        tableHtml.append("<tr><td>").append(ssw.datalist.get(i).replace("\t", "</td><td>")).append("</td></tr>"); // Creating table rows and columns using HTML tags
                    }
                    tableHtml.append("</table></html>"); // Closing the HTML table
                    seledata_label.setText(tableHtml.toString()); // Setting the label text as the complete HTML table
                }
                else
                {
                    selename_label.setText(name);
                    seledata_label.setText(data);
                    JOptionPane.showMessageDialog(train_frame, "没有您查找的数据！");
                }
            }
        });
    }


    //2.用户购买车票页面
    private void initialize()
    {
        normal_frame = new JFrame();
        normal_frame.setTitle("用户界面");
        normal_frame.setIconImage(Toolkit.getDefaultToolkit().getImage(MainUI.class.getResource("/image/cust.png")));
        normal_frame.setBounds(400,200,760,660);
        normal_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentpane = new JPanel();
        //将contentpane对象设置为frame的内容面板
        normal_frame.setContentPane(contentpane);
        contentpane.setLayout(null);

        JLabel admi = new JLabel("客户购买车票页面");
        admi.setFont(new Font("宋体",Font.BOLD,20));
        admi.setForeground(Color.BLACK);
        admi.setBounds(250,10,350,30);
        contentpane.add(admi);

        //返回按键定位到登录界面
        JButton goback_button = new JButton("返回");
        goback_button.setBounds(560,520,120,40);
        goback_button.setBackground(Color.GREEN);
        contentpane.add(goback_button);
        goback_button.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                normal_frame.dispose();
                FrameManage.Active_NormalUserUI();

            }
        });

        //查询车票信息
        JLabel sele_data = new JLabel("1.查询车票信息:");
        sele_data.setFont(new Font("宋体",Font.BOLD,18));
        sele_data.setForeground(Color.BLUE);
        sele_data.setBounds(30,50,200,30);
        contentpane.add(sele_data);

        JLabel sele_label = new JLabel("查询车票号");
        sele_label.setFont(new Font("宋体",Font.PLAIN,14));
        sele_label.setBounds(70,90,100,30);
        contentpane.add(sele_label);

        sele_ticketno_text = new JTextField();
        sele_ticketno_text.setBounds(160,90,200,30);
        sele_ticketno_text.setColumns(40);
        contentpane.add(sele_ticketno_text);


        JLabel ticketno_label = new JLabel("车票号");
        ticketno_label.setBounds(40,130,100,20);
        ticketno_label.setFont(new Font("宋体",Font.PLAIN,14));
        contentpane.add(ticketno_label);

        final JLabel ticketnodata_label = new JLabel("***");
        ticketnodata_label.setFont(new Font("宋体",Font.PLAIN,14));
        ticketnodata_label.setBounds(40,160,100,20);
        contentpane.add(ticketnodata_label);

        JLabel trainno_label=new JLabel("列车号");
        trainno_label.setBounds(130,130,100,20);
        trainno_label.setFont(new Font("宋体",Font.PLAIN,14));
        contentpane.add(trainno_label);

        final JLabel trainnodata_label=new JLabel("***");
        trainnodata_label.setFont(new Font("宋体",Font.PLAIN,14));
        trainnodata_label.setBounds(130,160,100,20);
        contentpane.add(trainnodata_label);

        JLabel depasta_label = new JLabel("出发站");
        depasta_label.setBounds(210,130,100,20);
        depasta_label.setFont(new Font("宋体",Font.PLAIN,14));
        contentpane.add(depasta_label);

        final JLabel depastadata_label = new JLabel("***");
        depastadata_label.setFont(new Font("宋体",Font.PLAIN,14));
        depastadata_label.setBounds(210,160,100,20);
        contentpane.add(depastadata_label);

        JLabel deststa_label = new JLabel("目的站");
        deststa_label.setBounds(290,130,100,20);
        deststa_label.setFont(new Font("宋体",Font.PLAIN,14));
        contentpane.add(deststa_label);

        final JLabel deststadata_label = new JLabel("***");
        deststadata_label.setFont(new Font("宋体",Font.PLAIN,14));
        deststadata_label.setBounds(290,160,100,20);
        contentpane.add(deststadata_label);

        JLabel gotime_label=new JLabel("出发时间");
        gotime_label.setBounds(370,130,100,20);
        gotime_label.setFont(new Font("宋体",Font.PLAIN,14));
        contentpane.add(gotime_label);

        final JLabel gotimedata_label=new JLabel("***");
        gotimedata_label.setFont(new Font("宋体",Font.PLAIN,14));
        gotimedata_label.setBounds(370,160,100,20);
        contentpane.add(gotimedata_label);

        JLabel arrivetime_label=new JLabel("到达时间");
        arrivetime_label.setBounds(450,130,100,20);
        arrivetime_label.setFont(new Font("宋体",Font.PLAIN,14));
        contentpane.add(arrivetime_label);

        final JLabel arrivetimedata_label=new JLabel("***");
        arrivetimedata_label.setFont(new Font("宋体",Font.PLAIN,14));
        arrivetimedata_label.setBounds(450,160,100,20);
        contentpane.add(arrivetimedata_label);

        JLabel price_label = new JLabel("价格");
        price_label.setBounds(530,130,100,20);
        price_label.setFont(new Font("宋体",Font.PLAIN,14));
        contentpane.add(price_label);

        final JLabel pricedata_label = new JLabel("***");
        pricedata_label.setFont(new Font("宋体",Font.PLAIN,14));
        pricedata_label.setBounds(530,160,100,20);
        contentpane.add(pricedata_label);

        JLabel amou_label = new JLabel("余票量");
        amou_label.setBounds(610,130,100,20);
        amou_label.setFont(new Font("宋体",Font.PLAIN,14));
        contentpane.add(amou_label);

        final JLabel amoudata_label = new JLabel("***");
        amoudata_label.setFont(new Font("宋体",Font.PLAIN,14));
        amoudata_label.setBounds(610,160,100,20);
        contentpane.add(amoudata_label);

        JButton sele_button = new JButton("查询");
        sele_button.setBounds(420,90,120,30);
        contentpane.add(sele_button);

        sele_button.addMouseListener(new MouseAdapter()
        {

            @Override
            public void mouseClicked(MouseEvent e)
            {
                SqlWork ssw = new SqlWork();
                String[] data = new String[7];
                String ticketno = sele_ticketno_text.getText();
                ssw.sql="select Tick_no,Train_no,Depa_sta,Dest_sta,Depa_time,Dest_time,Price " +
                        "from Ticket where Tick_no=\'"+ticketno+"\'";
                if(SqlManage.Select2(ssw) >= 1)
                {
                    data = ssw.datalist.get(0).split(" ");
                    ticketnodata_label.setText(data[0]);
                    trainnodata_label.setText(data[1]);
                    depastadata_label.setText(data[2]);
                    deststadata_label.setText(data[3]);
                    gotimedata_label.setText(data[4]);
                    arrivetimedata_label.setText(data[5]);
                    pricedata_label.setText(data[6]);
                    //
                    SqlWork ssw2 = new SqlWork();
                    ssw2.sql = "select Amou from TicketRefer where Tick_no=\'"+ticketno+"\'";
                    System.out.println(ssw2.sql);
                    SqlManage.Select2(ssw2);
                    String amount=ssw2.datalist.get(0);
                    System.out.println(amount);
                    //在SqlWork类中datalist中的字符串末尾都有一个空格
                    //一定要对amount进行trim(),否则在转换为int时会抛出NumberFormatException异常
                    surplus_ticket_amounts = Integer.valueOf(amount.trim()).intValue();
                    System.out.println(surplus_ticket_amounts);
                    amoudata_label.setText(amount);
                }
                else{
                    String temp = "***";
                    ticketnodata_label.setText(temp);
                    trainnodata_label.setText(temp);
                    depastadata_label.setText(temp);
                    deststadata_label.setText(temp);
                    gotimedata_label.setText(temp);
                    arrivetimedata_label.setText(temp);
                    pricedata_label.setText(temp);
                    amoudata_label.setText(temp);
                    JOptionPane.showMessageDialog(normal_frame, "不存在您要找的车票号");
                }

            }
        });

        JLabel createorder_label = new JLabel("2.创建订单:");
        createorder_label.setBounds(30,210,200,30);
        createorder_label.setFont(new Font("宋体",Font.BOLD,20));
        createorder_label.setForeground(Color.BLUE);
        contentpane.add(createorder_label);

        personal_order = new JRadioButton("个人订单");
        personal_order.setBounds(90,300,120,30);
        personal_order.setFont(new Font("宋体",Font.PLAIN,14));
        contentpane.add(personal_order);

        group_order = new JRadioButton("团体订单");
        group_order.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e){
                personal_order.setSelected(false);
            }
        });
        group_order.setBounds(400,300,120,30);
        group_order.setFont(new Font("宋体",Font.PLAIN,14));
        contentpane.add(group_order);
        personal_order.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e){
                group_order.setSelected(false);
            }
        });

        JLabel purchase_num_label = new JLabel("购买数量");
        purchase_num_label.setBounds(70,250,100,30);
        purchase_num_label.setFont(new Font("宋体",Font.PLAIN,14));
        contentpane.add(purchase_num_label);

        purchase_num_text = new JTextField();
        purchase_num_text.setBounds(140,250,150,30);
        contentpane.add(purchase_num_text);

        JLabel purchase_number_label=new JLabel("乘坐人");
        purchase_number_label.setBounds(350,250,100,30);
        purchase_number_label.setFont(new Font("宋体",Font.PLAIN,14));
        contentpane.add(purchase_number_label);

        purchase_number_text=new JTextField();
        purchase_number_text.setBounds(420,250,150,30);
        contentpane.add(purchase_number_text);


        final JLabel addto_tip=new JLabel("订单详情：");
        addto_tip.setFont(new Font("宋体",Font.BOLD,14));
        addto_tip.setBounds(40,400,100,30);
        addto_tip.setForeground(Color.BLUE);
        contentpane.add(addto_tip);
        final JLabel addorder_tip=new JLabel("tip:----------------------------nothing----------------------");
        addorder_tip.setFont(new Font("宋体",Font.PLAIN,14));
        addorder_tip.setBounds(70,440,600,30);
        contentpane.add(addorder_tip);


        JButton confirm_order_button = new JButton("确认订单");
        confirm_order_button.setBounds(420,360,120,30);
        contentpane.add(confirm_order_button);
        confirm_order_button.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                int purchasenum = Integer.parseInt(purchase_num_text.getText());
                String ticketno = sele_ticketno_text.getText();
                String numbersname=purchase_number_text.getText();
                if(surplus_ticket_amounts-purchasenum<0){
                    JOptionPane.showMessageDialog(normal_frame,"车票剩余量不足:"+surplus_ticket_amounts);
                }
                else{
                    //int tempsur = surplus_ticket_amounts-purchasenum;
                    SqlWork psw = new SqlWork();
                    psw.sql = "exec purchase_ticket \'"+ticketno+"\',\'"+numbersname+"\',"+purchasenum;;
                    if(SqlManage.ExecPro(psw)){
                        SqlWork ssw = new SqlWork();
                        ssw.sql="select order_code from SerialNumCode";
                        SqlManage.Select2(ssw);
                        int tempordernum = Integer.parseInt(ssw.datalist.get(0).trim())-1;
                        String temporderno = "order-"+tempordernum;
                        SqlWork ssw2 = new SqlWork();
                        ssw2.sql="select * from MakeUp where Order_no=\'"+temporderno+"\'";
                        SqlManage.Select2(ssw2);
                        String result=ssw2.datalist.get(0);
                        String result1="订单详情："+result;
                        //order_showdata_label.setText(result);
                        addorder_tip.setText(result1);
                        JOptionPane.showMessageDialog(normal_frame, "订单创建成功！");
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(normal_frame,"订单创建失败！");
                    }

                }
            }
        });


    }


    //3.退票/改签窗口
    private  void init_ticketui()
    {
        ticket_frame=new JFrame();
        ticket_frame.setTitle("用户界面");
        ticket_frame.setIconImage(Toolkit.getDefaultToolkit().getImage(MainUI.class.getResource("/image/cust.png")));
        ticket_frame.setBounds(400,200,960,720);
        ticket_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentpane=new JPanel();
        ticket_frame.setContentPane(contentpane);
        ticket_frame.setLayout(null);

        JButton comeback_button = new JButton("返回");
        comeback_button.setBounds(720,580,120,40);
        comeback_button.setBackground(Color.GREEN);
        contentpane.add(comeback_button);
        comeback_button.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                ticket_frame.dispose();
                FrameManage.Active_NormalUserUI();

            }
        });


        JLabel ordersele_data=new JLabel("1.已购车票信息：");
        ordersele_data.setFont(new Font("宋体",Font.BOLD,20));
        ordersele_data.setBounds(20,10,200,30);
        ordersele_data.setForeground(Color.BLUE);
        contentpane.add(ordersele_data);

        JLabel ordersele_label=new JLabel("查询订单号");
        ordersele_label.setFont(new Font("宋体",Font.PLAIN,14));
        ordersele_label.setBounds(40,50,100,30);
        contentpane.add(ordersele_label);

        ordersele_label_text=new JTextField();
        ordersele_label_text.setBounds(150,50,200,30);
        ordersele_label_text.setColumns(40);
        contentpane.add(ordersele_label_text);

        JLabel order_label=new JLabel("订单号");
        order_label.setBounds(30,90,100,20);
        order_label.setFont(new Font("宋体",Font.BOLD,14));
        order_label.setForeground(Color.BLUE);
        contentpane.add(order_label);

        final JLabel orderdata_label=new JLabel("***");
        orderdata_label.setFont(new Font("宋体",Font.PLAIN,14));
        orderdata_label.setBounds(30,120,100,20);
        contentpane.add(orderdata_label);

        JLabel orderticket_label=new JLabel("车票号");
        orderticket_label.setFont(new Font("宋体",Font.BOLD,14));
        orderticket_label.setBounds(130,90,100,20);
        orderticket_label.setForeground(Color.BLUE);
        contentpane.add(orderticket_label);

        final JLabel orderticketdata_label=new JLabel("***");
        orderticketdata_label.setFont(new Font("宋体",Font.PLAIN,14));
        orderticketdata_label.setBounds(130,120,100,20);
        contentpane.add(orderticketdata_label);

        JLabel ordertrain_label=new JLabel("列车号");
        ordertrain_label.setFont(new Font("宋体",Font.BOLD,14));
        ordertrain_label.setBounds(230,90,100,20);
        ordertrain_label.setForeground(Color.BLUE);
        contentpane.add(ordertrain_label);

        final JLabel ordertraindata_label=new JLabel("***");
        ordertraindata_label.setFont(new Font("宋体",Font.PLAIN,14));
        ordertraindata_label.setBounds(230,120,100,20);
        contentpane.add(ordertraindata_label);

        JLabel ordername_label=new JLabel("乘车人");
        ordername_label.setFont(new Font("宋体",Font.BOLD,14));
        ordername_label.setBounds(330,90,100,20);
        ordername_label.setForeground(Color.BLUE);
        contentpane.add(ordername_label);

        final JLabel ordernamedata_label=new JLabel("***");
        ordernamedata_label.setFont(new Font("宋体",Font.PLAIN,14));
        ordernamedata_label.setBounds(330,120,200,20);
        contentpane.add(ordernamedata_label);

        JLabel orderseat_label=new JLabel("座位号");
        orderseat_label.setFont(new Font("宋体",Font.BOLD,14));
        orderseat_label.setBounds(530,90,100,20);
        orderseat_label.setForeground(Color.BLUE);
        contentpane.add(orderseat_label);

        final JLabel orderseatdata_label=new JLabel("***");
        orderseatdata_label.setFont(new Font("宋体",Font.PLAIN,14));
        orderseatdata_label.setBounds(530,120,100,20);
        contentpane.add(orderseatdata_label);

        JLabel ordersta_label=new JLabel("出发站");
        ordersta_label.setBounds(30,150,100,20);
        ordersta_label.setFont(new Font("宋体",Font.BOLD,14));
        ordersta_label.setForeground(Color.BLUE);
        contentpane.add(ordersta_label);

        final JLabel orderstadata_label=new JLabel("***");
        orderstadata_label.setFont(new Font("宋体",Font.PLAIN,14));
        orderstadata_label.setBounds(30,180,100,20);
        contentpane.add(orderstadata_label);

        JLabel orderdest_label=new JLabel("目的站");
        orderdest_label.setFont(new Font("宋体",Font.BOLD,14));
        orderdest_label.setBounds(130,150,100,20);
        orderdest_label.setForeground(Color.BLUE);
        contentpane.add(orderdest_label);

        final JLabel orderdestdata_label=new JLabel("***");
        orderdestdata_label.setFont(new Font("宋体",Font.PLAIN,14));
        orderdestdata_label.setBounds(130,180,100,20);
        contentpane.add(orderdestdata_label);

        JLabel orderstatime_label=new JLabel("出发时间");
        orderstatime_label.setFont(new Font("宋体",Font.BOLD,14));
        orderstatime_label.setBounds(230,150,100,20);
        orderstatime_label.setForeground(Color.BLUE);
        contentpane.add(orderstatime_label);

        final JLabel orderstatimedata_label=new JLabel("***");
        orderstatimedata_label.setFont(new Font("宋体",Font.PLAIN,14));
        orderstatimedata_label.setBounds(230,180,100,20);
        contentpane.add(orderstatimedata_label);

        JLabel ordergotime_label=new JLabel("到达时间");
        ordergotime_label.setFont(new Font("宋体",Font.BOLD,14));
        ordergotime_label.setBounds(330,150,100,20);
        ordergotime_label.setForeground(Color.BLUE);
        contentpane.add(ordergotime_label);

        final JLabel ordergotimedata_label=new JLabel("***");
        ordergotimedata_label.setBounds(330,180,100,20);
        ordergotimedata_label.setFont(new Font("宋体",Font.PLAIN,14));
        contentpane.add(ordergotimedata_label);

        JLabel orderprice_label=new JLabel("价格");
        orderprice_label.setFont(new Font("宋体",Font.BOLD,14));
        orderprice_label.setBounds(430,150,100,20);
        orderprice_label.setForeground(Color.BLUE);
        contentpane.add(orderprice_label);

        final JLabel orderpricedata_label=new JLabel("***");
        orderpricedata_label.setFont(new Font("宋体",Font.PLAIN,14));
        orderpricedata_label.setBounds(430,180,100,20);
        contentpane.add(orderpricedata_label);

        JLabel orderamou_label=new JLabel("购票数量");
        orderamou_label.setBounds(530,150,100,20);
        orderamou_label.setFont(new Font("宋体",Font.BOLD,14));
        orderamou_label.setForeground(Color.BLUE);
        contentpane.add(orderamou_label);

        final JLabel orderamoudata_label=new JLabel("***");
        orderamoudata_label.setFont(new Font("宋体",Font.PLAIN,14));
        orderamoudata_label.setBounds(530,180,100,20);
        contentpane.add(orderamoudata_label);

        JButton ordersele_button=new JButton("查询");
        ordersele_button.setBounds(500,50,120,30);
        ordersele_button.setFont(new Font("宋体",Font.PLAIN,14));
        contentpane.add(ordersele_button);

        ordersele_button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SqlWork sqw=new SqlWork();
                String[] data =new String[11];
                String ordersno=ordersele_label_text.getText();
                sqw.sql="select MakeUp.Order_no,MakeUp.Tick_no,Ticket.Train_no,MakeUp.number_no,Ticket.Seat_no,Ticket.Depa_sta,Ticket.Dest_sta,Ticket.Depa_time,Ticket.Dest_time,Ticket.price,MakeUp.Amou from MakeUp,Ticket,CustOrder where MakeUp.Order_no=\'"+ordersno+"\' and MakeUp.Tick_no=Ticket.Tick_no ";
                if(SqlManage.Select2(sqw)>=1){
                    data=sqw.datalist.get(0).split(" ");
                    orderdata_label.setText(data[0]);
                    orderticketdata_label.setText(data[1]);
                    ordertraindata_label.setText(data[2]);
                    ordernamedata_label.setText(data[3]);
                    orderseatdata_label.setText(data[4]);
                    orderstadata_label.setText(data[5]);
                    orderdestdata_label.setText(data[6]);
                    orderstatimedata_label.setText(data[7]);
                    ordergotimedata_label.setText(data[8]);
                    orderpricedata_label.setText(data[9]);
                    orderamoudata_label.setText(data[10]);

                }
                else {
                    String temp1="***";
                    orderdata_label.setText(temp1);
                    orderticketdata_label.setText(temp1);
                    ordertraindata_label.setText(temp1);
                    ordernamedata_label.setText(temp1);
                    orderseatdata_label.setText(temp1);
                    orderstadata_label.setText(temp1);
                    orderdestdata_label.setText(temp1);
                    orderstatimedata_label.setText(temp1);
                    ordergotimedata_label.setText(temp1);
                    orderpricedata_label.setText(temp1);
                    orderamoudata_label.setText(temp1);
                    JOptionPane.showMessageDialog(ticket_frame,"不存在您要找的订单！");
                }
            }
        });


        JLabel uporder_label=new JLabel("2.改签车票信息：");
        uporder_label.setFont(new Font("宋体",Font.BOLD,20));
        uporder_label.setBounds(20,240,200,30);
        uporder_label.setForeground(Color.BLUE);
        contentpane.add(uporder_label);

        JLabel orderno_label=new JLabel("订单号");
        orderno_label.setFont(new Font("宋体",Font.PLAIN,14));
        orderno_label.setBounds(40,280,100,30);
        contentpane.add(orderno_label);

        ordernodata_label=new JTextField();
        ordernodata_label.setBounds(150,280,200,30);
        ordernodata_label.setColumns(20);
        contentpane.add(ordernodata_label);

        JLabel orderticketno_label=new JLabel("更新前车票号");
        orderticketno_label.setBounds(40,320,100,30);
        orderticketno_label.setFont(new Font("宋体",Font.PLAIN,14));
        contentpane.add(orderticketno_label);

        orderticketnodata_label=new JTextField();
        orderticketnodata_label.setBounds(150,320,200,30);
        orderticketnodata_label.setColumns(20);
        contentpane.add(orderticketnodata_label);

        JLabel orderticketno1_label=new JLabel("改签车票号");
        orderticketno1_label.setBounds(40,360,100,30);
        orderticketno1_label.setFont(new Font("宋体",Font.PLAIN,14));
        contentpane.add(orderticketno1_label);

        orderticketno1data_label=new JTextField();
        orderticketno1data_label.setBounds(150,360,200,30);
        orderticketno1data_label.setColumns(20);
        contentpane.add(orderticketno1data_label);

        JLabel ordernumber_label=new JLabel("乘车人");
        ordernumber_label.setFont(new Font("宋体",Font.PLAIN,14));
        ordernumber_label.setBounds(40,400,100,30);
        contentpane.add(ordernumber_label);

        ordernumberdata_label=new JTextField();
        ordernumberdata_label.setBounds(150,400,200,30);
        ordernumberdata_label.setColumns(20);
        contentpane.add(ordernumberdata_label);

        JLabel orderamouno_label=new JLabel("车票数量");
        orderamouno_label.setBounds(40,440,100,30);
        orderamouno_label.setFont(new Font("宋体",Font.PLAIN,14));
        contentpane.add(orderamouno_label);

        orderamounodata_label=new JTextField();
        orderamounodata_label.setBounds(150,440,200,30);
        orderamounodata_label.setColumns(20);
        contentpane.add(orderamounodata_label);

        JLabel deleno_tip_label=new JLabel("提示:");
        deleno_tip_label.setFont(new Font("宋体",Font.PLAIN,14));
        deleno_tip_label.setBounds(20,480,40,30);
        contentpane.add(deleno_tip_label);

        final JLabel delesno_tip=new JLabel("原订单信息:");
        delesno_tip.setFont(new Font("宋体",Font.PLAIN,14));
        delesno_tip.setBounds(20,510,80,30);
        contentpane.add(delesno_tip);

        final JLabel deleno_tip=new JLabel("------------nothing to show------------------");
        deleno_tip.setFont(new Font("宋体",Font.PLAIN,14));
        deleno_tip.setBounds(100,510,400,30);
        contentpane.add(deleno_tip);

        final JLabel delesno1_tip=new JLabel("预改签信息:");
        delesno1_tip.setBounds(20,540,80,30);
        delesno1_tip.setFont(new Font("宋体",Font.PLAIN,14));
        contentpane.add(delesno1_tip);

        final JLabel deleno1_tip=new JLabel("------------nothing to show------------------");
        deleno1_tip.setFont(new Font("宋体",Font.PLAIN,14));
        deleno1_tip.setBounds(100,540,400,30);
        contentpane.add(deleno1_tip);

        JButton ordertip_button=new JButton("查看提示");
        ordertip_button.setBounds(30,580,120,30);
        contentpane.add(ordertip_button);

        ordertip_button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String orderno=ordernodata_label.getText();
                String orderticketsno= orderticketno1data_label.getText();
                String ordertip="";
                String ordertip1="";
                SqlWork osw=new SqlWork();
                SqlWork dew=new SqlWork();
                dew.sql="select TicketRefer.Tick_no,TicketRefer.Train_no,Pass_sta,Go_sta,Come_time,Go_time,Till_pri,TicketRefer.Amou " +
                        "from TrainInfo,TicketRefer where TicketRefer.Tick_no=\'"+orderticketsno+"\' and TicketRefer.Train_no=TrainInfo.Train_no";
                osw.sql="select * from MakeUp where Order_no=\'"+orderno+"\'";
                int count1=SqlManage.Select1(dew);
                int count=SqlManage.Select1(osw);
                System.out.println("querying the tip information..................");
                if(count==0||count1==0){
                    deleno_tip.setText("没有找到所有修改的车票号！");

                }
                else{
                    ordertip=osw.datalist.get(0);
                    ordertip1=dew.datalist.get(0);
                    deleno_tip.setText(ordertip);
                    deleno1_tip.setText(ordertip1);
                }
                System.out.println("query ok..............");

            }
        });

        JButton orderxiugai_button=new JButton("确认改签");
        orderxiugai_button.setBounds(210,580,120,30);
        contentpane.add(orderxiugai_button);

        orderxiugai_button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("updating data................");
                SqlWork ouw=new SqlWork();
                SqlWork onw=new SqlWork();
                SqlWork ovw=new SqlWork();
                String ordersno= ordernodata_label.getText();
                String orderticketsno=orderticketnodata_label.getText();
                String orderticketsno1=orderticketno1data_label.getText();
                String ordernumbersno= ordernumberdata_label.getText();
                int  orderamousno= Integer.parseInt(orderamounodata_label.getText());

                onw.sql="update TicketRefer set Amou=Amou+"+orderamousno+ "where Tick_no=\'"+orderticketsno+"\'";
                ovw.sql="update TicketRefer set Amou=Amou-"+orderamousno+ "where Tick_no=\'"+orderticketsno1+"\'";
                ouw.sql="update MakeUp set Tick_no=\'"+orderticketsno1+"\',number_no=\'"+ordernumbersno+"\',Amou="+orderamousno+" where Order_no=\'"+ordersno+"\'";
                System.out.println(ouw.sql);
                if(true==SqlManage.Update(ovw)&&true==SqlManage.Update(onw)&&true==SqlManage.Update(ouw)){
                    JOptionPane.showMessageDialog(ticket_frame,"改签成功！");
                }
                else{
                    JOptionPane.showMessageDialog(ticket_frame,"改签失败！");
                }
            }
        });


        JLabel deleteno_label=new JLabel("3.退票操作：");
        deleteno_label.setBounds(500,240,200,30);
        deleteno_label.setFont(new Font("宋体",Font.BOLD,20));
        deleteno_label.setForeground(Color.BLUE);
        contentpane.add(deleteno_label);

        JLabel dele_label=new JLabel("订单号");
        dele_label.setFont(new Font("宋体",Font.PLAIN,14));
        dele_label.setBounds(540,280,100,30);
        contentpane.add(dele_label);

        deleorder_text_orderno=new JTextField();
        deleorder_text_orderno.setBounds(620,280,200,30);
        deleorder_text_orderno.setColumns(20);
        contentpane.add(deleorder_text_orderno);

        JLabel deleticket_label=new JLabel("车票号");
        deleticket_label.setBounds(540,320,100,30);
        deleticket_label.setFont(new Font("宋体",Font.PLAIN,14));
        contentpane.add(deleticket_label);

        deleticketdata_label=new JTextField();
        deleticketdata_label.setBounds(620,320,200,30);
        deleticketdata_label.setColumns(20);
        contentpane.add(deleticketdata_label);

        JLabel deleamouno_label=new JLabel("车票数量");
        deleamouno_label.setBounds(540,360,100,30);
        deleamouno_label.setFont(new Font("宋体",Font.PLAIN,14));
        contentpane.add(deleamouno_label);

        deleamounodata_label=new JTextField();
        deleamounodata_label.setBounds(620,360,200,30);
        deleamounodata_label.setColumns(20);
        contentpane.add(deleamounodata_label);


        JLabel dele_tip_label=new JLabel("提示:");
        dele_tip_label.setFont(new Font("宋体",Font.PLAIN,14));
        dele_tip_label.setBounds(500,410,40,30);
        contentpane.add(dele_tip_label);

        final JLabel dele_tip=new JLabel("--------------nothing to show------------------");
        dele_tip.setFont(new Font("宋体",Font.PLAIN,14));
        dele_tip.setBounds(560,410,560,30);
        contentpane.add(dele_tip);

        JButton tip_button=new JButton("查看提示");
        tip_button.setBounds(540,470,120,30);
        contentpane.add(tip_button);

        tip_button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String orderno=deleorder_text_orderno.getText();
                String tipstr="";
                SqlWork sqw =new SqlWork();
                sqw.sql="select MakeUp.Order_no,MakeUp.Tick_no,MakeUp.number_no,MakeUp.Amou,CustOrder.Create_time from MakeUp,CustOrder where MakeUp.Order_no=\'"+orderno+"\' and MakeUp.Order_no=CustOrder.Order_no";
                System.out.println(sqw.sql);
                int count=SqlManage.Select2(sqw);
                System.out.println("querying the tip infomation...............");
                if(count==0){
                    dele_tip.setText("没有找到你想要删除的订单号！");
                }
                else {
                    tipstr=sqw.datalist.get(0);
                    dele_tip.setText(tipstr);
                }
                System.out.println("query ok...................");
            }
        });

        JButton dele_button=new JButton("确认删除");
        dele_button.setBounds(720,470,120,30);
        contentpane.add(dele_button);

        dele_button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("deleting date.....................");
                SqlWork dsw = new SqlWork();
                SqlWork dnw=new SqlWork();
                SqlWork dvw=new SqlWork();
                String ordersno = deleorder_text_orderno.getText();
                String orderticket1=deleticketdata_label.getText();
                String orderamousno2=deleamounodata_label.getText();
                dsw.sql = "delete MakeUp where Order_no=\'"+ordersno+"\'";
                dnw.sql = "delete CustOrder where Order_no=\'"+ordersno+"\'";
                dvw.sql="update TicketRefer set Amou=Amou+"+orderamousno2+ "where Tick_no=\'"+orderticket1+"\'";
                System.out.println(dsw.sql);
                if(true == SqlManage.Delete(dsw)&&true==SqlManage.Delete((dnw))&&true==SqlManage.Update(dvw)){
                    JOptionPane.showMessageDialog(ticket_frame, "退票成功！");
                }
                else{
                    JOptionPane.showMessageDialog(ticket_frame, "退票失败！");
                }
            }
        });

    }

    //4.查看已支付车票信息
    private  void init_orderall()
    {
        order_frame=new JFrame();
        order_frame.setTitle("用户界面");
        order_frame.setIconImage(Toolkit.getDefaultToolkit().getImage(MainUI.class.getResource("/image/cust.png")));
        order_frame.setBounds(400,200,760,560);
        order_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentpane=new JPanel();
        order_frame.setContentPane(contentpane);
        contentpane.setLayout(null);

        JButton fanhui_button=new JButton("返回");
        fanhui_button.setFont(new Font("宋体",Font.PLAIN,16));
        fanhui_button.setBounds(550,400,120,40);
        fanhui_button.setBackground(Color.GREEN);
        contentpane.add(fanhui_button);
        fanhui_button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                order_frame.dispose();
                FrameManage.Active_NormalUserUI();
            }
        });


        JLabel ordersele_data=new JLabel("已购车票信息：");
        ordersele_data.setFont(new Font("宋体",Font.BOLD,18));
        ordersele_data.setBounds(20,20,200,30);
        ordersele_data.setForeground(Color.BLUE);
        contentpane.add(ordersele_data);

        JLabel ordersele_label=new JLabel("查询订单号");
        ordersele_label.setFont(new Font("宋体",Font.PLAIN,14));
        ordersele_label.setBounds(40,60,100,30);
        contentpane.add(ordersele_label);

        ordersele_label_text=new JTextField();
        ordersele_label_text.setBounds(150,60,200,30);
        ordersele_label_text.setColumns(40);
        contentpane.add(ordersele_label_text);

        JLabel order_label=new JLabel("订单号");
        order_label.setBounds(30,100,100,20);
        order_label.setFont(new Font("宋体",Font.PLAIN,14));
        order_label.setForeground(Color.BLUE);
        contentpane.add(order_label);

        final JLabel orderdata_label=new JLabel("***");
        orderdata_label.setFont(new Font("宋体",Font.PLAIN,14));
        orderdata_label.setBounds(30,130,100,20);
        contentpane.add(orderdata_label);

        JLabel orderticket_label=new JLabel("车票号");
        orderticket_label.setFont(new Font("宋体",Font.PLAIN,14));
        orderticket_label.setBounds(130,100,100,20);
        orderticket_label.setForeground(Color.BLUE);
        contentpane.add(orderticket_label);

        final JLabel orderticketdata_label=new JLabel("***");
        orderticketdata_label.setFont(new Font("宋体",Font.PLAIN,14));
        orderticketdata_label.setBounds(130,130,100,20);
        contentpane.add(orderticketdata_label);

        JLabel ordertrain_label=new JLabel("列车号");
        ordertrain_label.setFont(new Font("宋体",Font.PLAIN,14));
        ordertrain_label.setBounds(230,100,100,20);
        ordertrain_label.setForeground(Color.BLUE);
        contentpane.add(ordertrain_label);

        final JLabel ordertraindata_label=new JLabel("***");
        ordertraindata_label.setFont(new Font("宋体",Font.PLAIN,14));
        ordertraindata_label.setBounds(230,130,100,20);
        contentpane.add(ordertraindata_label);

        JLabel ordername_label=new JLabel("乘车人");
        ordername_label.setFont(new Font("宋体",Font.PLAIN,14));
        ordername_label.setBounds(360,100,100,20);
        ordername_label.setForeground(Color.BLUE);
        contentpane.add(ordername_label);

        final JLabel ordernamedata_label=new JLabel("***");
        ordernamedata_label.setFont(new Font("宋体",Font.PLAIN,14));
        ordernamedata_label.setBounds(340,130,200,20);
        contentpane.add(ordernamedata_label);

        JLabel orderseat_label=new JLabel("座位号");
        orderseat_label.setFont(new Font("宋体",Font.PLAIN,14));
        orderseat_label.setBounds(530,100,100,20);
        orderseat_label.setForeground(Color.BLUE);
        contentpane.add(orderseat_label);

        final JLabel orderseatdata_label=new JLabel("***");
        orderseatdata_label.setFont(new Font("宋体",Font.PLAIN,14));
        orderseatdata_label.setBounds(530,130,100,20);
        contentpane.add(orderseatdata_label);

        JLabel ordersta_label=new JLabel("出发站");
        ordersta_label.setBounds(30,170,100,20);
        ordersta_label.setFont(new Font("宋体",Font.PLAIN,14));
        ordersta_label.setForeground(Color.BLUE);
        contentpane.add(ordersta_label);

        final JLabel orderstadata_label=new JLabel("***");
        orderstadata_label.setFont(new Font("宋体",Font.PLAIN,14));
        orderstadata_label.setBounds(30,200,100,20);
        contentpane.add(orderstadata_label);

        JLabel orderdest_label=new JLabel("目的站");
        orderdest_label.setFont(new Font("宋体",Font.PLAIN,14));
        orderdest_label.setBounds(130,170,100,20);
        orderdest_label.setForeground(Color.BLUE);
        contentpane.add(orderdest_label);

        final JLabel orderdestdata_label=new JLabel("***");
        orderdestdata_label.setFont(new Font("宋体",Font.PLAIN,14));
        orderdestdata_label.setBounds(130,200,100,20);
        contentpane.add(orderdestdata_label);

        JLabel orderstatime_label=new JLabel("出发时间");
        orderstatime_label.setFont(new Font("宋体",Font.PLAIN,14));
        orderstatime_label.setBounds(230,170,100,20);
        orderstatime_label.setForeground(Color.BLUE);
        contentpane.add(orderstatime_label);

        final JLabel orderstatimedata_label=new JLabel("***");
        orderstatimedata_label.setFont(new Font("宋体",Font.PLAIN,14));
        orderstatimedata_label.setBounds(230,200,100,20);
        contentpane.add(orderstatimedata_label);

        JLabel ordergotime_label=new JLabel("到达时间");
        ordergotime_label.setFont(new Font("宋体",Font.PLAIN,14));
        ordergotime_label.setBounds(330,170,100,20);
        ordergotime_label.setForeground(Color.BLUE);
        contentpane.add(ordergotime_label);

        final JLabel ordergotimedata_label=new JLabel("***");
        ordergotimedata_label.setBounds(330,200,100,20);
        ordergotimedata_label.setFont(new Font("宋体",Font.PLAIN,14));
        contentpane.add(ordergotimedata_label);

        JLabel orderprice_label=new JLabel("价格");
        orderprice_label.setFont(new Font("宋体",Font.PLAIN,14));
        orderprice_label.setBounds(430,170,100,20);
        orderprice_label.setForeground(Color.BLUE);
        contentpane.add(orderprice_label);

        final JLabel orderpricedata_label=new JLabel("***");
        orderpricedata_label.setFont(new Font("宋体",Font.PLAIN,14));
        orderpricedata_label.setBounds(430,200,100,20);
        contentpane.add(orderpricedata_label);

        JLabel orderamou_label=new JLabel("购票数量");
        orderamou_label.setBounds(530,170,100,20);
        orderamou_label.setFont(new Font("宋体",Font.PLAIN,14));
        orderamou_label.setForeground(Color.BLUE);
        contentpane.add(orderamou_label);

        final JLabel orderamoudata_label=new JLabel("***");
        orderamoudata_label.setFont(new Font("宋体",Font.PLAIN,14));
        orderamoudata_label.setBounds(530,200,100,20);
        contentpane.add(orderamoudata_label);

        JButton ordersele_button=new JButton("查询");
        ordersele_button.setBounds(550,60,120,30);
        ordersele_button.setFont(new Font("宋体",Font.PLAIN,14));
        contentpane.add(ordersele_button);

        ordersele_button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SqlWork sqw=new SqlWork();
                String[] data =new String[11];
                String ordersno=ordersele_label_text.getText();
                sqw.sql="select MakeUp.Order_no,MakeUp.Tick_no,Ticket.Train_no,MakeUp.number_no,Ticket.Seat_no,Ticket.Depa_sta,Ticket.Dest_sta,Ticket.Depa_time,Ticket.Dest_time,Ticket.price,MakeUp.Amou from MakeUp,Ticket where MakeUp.Order_no=\'"+ordersno+"\' and MakeUp.Tick_no=Ticket.Tick_no ";
                if(SqlManage.Select2(sqw)>=1){
                    data=sqw.datalist.get(0).split(" ");
                    orderdata_label.setText(data[0]);
                    orderticketdata_label.setText(data[1]);
                    ordertraindata_label.setText(data[2]);
                    ordernamedata_label.setText(data[3]);
                    orderseatdata_label.setText(data[4]);
                    orderstadata_label.setText(data[5]);
                    orderdestdata_label.setText(data[6]);
                    orderstatimedata_label.setText(data[7]);
                    ordergotimedata_label.setText(data[8]);
                    orderpricedata_label.setText(data[9]);
                    orderamoudata_label.setText(data[10]);

                }
                else {
                    String temp1="***";
                    orderdata_label.setText(temp1);
                    orderticketdata_label.setText(temp1);
                    ordertraindata_label.setText(temp1);
                    ordernamedata_label.setText(temp1);
                    orderseatdata_label.setText(temp1);
                    orderstadata_label.setText(temp1);
                    orderdestdata_label.setText(temp1);
                    orderstatimedata_label.setText(temp1);
                    ordergotimedata_label.setText(temp1);
                    orderpricedata_label.setText(temp1);
                    orderamoudata_label.setText(temp1);
                    JOptionPane.showMessageDialog(order_frame,"不存在您要找的订单！");
                }
            }
        });


    }

    //5.基本信息/注销
    private void init_numalxinxi()
    {
         numal_frame=new JFrame();
         numal_frame.setTitle("用户界面");
         numal_frame.setIconImage(Toolkit.getDefaultToolkit().getImage(MainUI.class.getResource("/image/cust.png")));
         numal_frame.setBounds(400,200,750,550);
         numal_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         contentpane=new JPanel();
         numal_frame.setContentPane(contentpane);
         numal_frame.setLayout(null);
         JLabel numaljiben_label=new JLabel("用户个人基本信息：");
         numaljiben_label.setFont(new Font("宋体",Font.BOLD,18));
         numaljiben_label.setBounds(20,10,350,30);
         numaljiben_label.setForeground(Color.BLUE);
         contentpane.add(numaljiben_label);

         JButton numalfanhui_button=new JButton("返回");
         numalfanhui_button.setBounds(550,400,120,40);
         numalfanhui_button.setBackground(Color.GREEN);
         contentpane.add(numalfanhui_button);
         numalfanhui_button.addMouseListener(new MouseAdapter() {
             @Override
             public void mouseClicked(MouseEvent e) {
                 numal_frame.dispose();
                 FrameManage.Active_NormalUserUI();
             }
         });

         JButton numalzhuxiao_button=new JButton("注销");
         numalzhuxiao_button.setBounds(50,400,120,40);
         contentpane.add(numalzhuxiao_button);

         numalzhuxiao_button.addMouseListener(new MouseAdapter() {
             @Override
             public void mouseClicked(MouseEvent e) {
                 System.out.println("deleting data........................");
                 SqlWork dqw=new SqlWork();
                 String numalsno1=numalsnodata_text.getText();
                 dqw.sql="delete  Customer where Cust_id=\'"+numalsno1+"\'";
                 System.out.println(dqw.sql);
                 if(SqlManage.Delete(dqw)){
                     JOptionPane.showMessageDialog(numal_frame,"注销成功！");
                     numal_frame.dispose();
                     FrameManage.Active_Mainframe();
                 }
                 else{
                     JOptionPane.showMessageDialog(numal_frame,"注销失败！");
                 }

             }
         });
         JLabel numalsno_label=new JLabel("注册名：");
         numalsno_label.setFont(new Font("宋体",Font.PLAIN,14));
         numalsno_label.setBounds(240,10,100,30);
         contentpane.add(numalsno_label);
         
         numalsnodata_text=new JTextField();
         numalsnodata_text.setBounds(320,10,120,30);
         contentpane.add(numalsnodata_text);


         JLabel numalno_label=new JLabel("用户名:");
         numalno_label.setFont(new Font("宋体",Font.BOLD,16));
         numalno_label.setBounds(260,140,100,30);
         contentpane.add(numalno_label);
         
         final JLabel numalnodata_text=new JLabel("*****");
         numalnodata_text.setFont(new Font("宋体",Font.PLAIN,14));
         numalnodata_text.setBounds(380,140,200,30);
         contentpane.add(numalnodata_text);

         JLabel numalpwd_label=new JLabel("密码：");
         numalpwd_label.setFont(new Font("宋体",Font.BOLD,16));
         numalpwd_label.setBounds(260,200,100,30);
         contentpane.add(numalpwd_label);
         
         final JLabel numalpwddata_text=new JLabel("*****");
         numalpwddata_text.setFont(new Font("宋体",Font.PLAIN,14));
         numalpwddata_text.setBounds(380,200,200,30);
         contentpane.add(numalpwddata_text);
         
         JLabel numalid_label=new JLabel("身份证号：");
         numalid_label.setFont(new Font("宋体",Font.BOLD,16));
         numalid_label.setBounds(260,260,100,30);
         contentpane.add(numalid_label);
         
         final JLabel numaliddata_text=new JLabel("*****");
         numaliddata_text.setFont(new Font("宋体",Font.PLAIN,14));
         numaliddata_text.setBounds(380,260,200,30);
         contentpane.add(numaliddata_text);

         JButton numalsele_button=new JButton("查询");
         numalsele_button.setBounds(550,10,120,40);
         contentpane.add(numalsele_button);

         numalsele_button.addMouseListener(new MouseAdapter() {
             @Override
             public void mouseClicked(MouseEvent e) {
                 SqlWork ssqw=new SqlWork();
                 String numalsno=numalsnodata_text.getText();

                 String[] data2=new String[3];
                 ssqw.sql="select Cust_id,Cust_pwd,ID_no from Customer where Cust_id=\'"+numalsno+"\'";
                 if(SqlManage.Select2(ssqw)>=1){
                     data2=ssqw.datalist.get(0).split(" ");
                     numalnodata_text.setText(data2[0]);
                     numalpwddata_text.setText(data2[1]);
                     numaliddata_text.setText(data2[2]);

                 }
                 else {
                     String temp2="*****";
                     numalnodata_text.setText(temp2);
                     numalpwddata_text.setText(temp2);
                     numaliddata_text.setText(temp2);
                     JOptionPane.showMessageDialog(numal_frame,"系统出错了，请稍后重试！");
                 }
             }
         });

    }



}