package StartSys;

import SqlTran.SqlManage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdministratorUI {
    public JFrame select_frame;
    public JFrame traininfo_frame;
    public JFrame ticketinfo_frame;
    public JFrame admin_frame;
    private JPanel contentpane;
    private JTextField addtext_startsta;
    private JTextField addtext_endsta;
    private JTextField addtext_price;
    private JTextField addtext_trainsno;
    private JTextField addtext_trainarrive;
    private JTextField addtext_traingo;
    private JTextField deletext_trainno;
    private JTextField seletext_trainno;
    private JTextField updatext_trainno;
    private JTextField updatext_startsta;
    private JTextField updatext_endsta;
    private JTextField updatext_price;
    private JTextField addticket_text_trainno;
    private JTextField deleticket_text_ticketno;
    private JTextField seleticket_text_ticketno;
    private JTextField updaticket_text_ticketno;
    private JTextField updaticket_text_depasta;
    private JTextField updaticket_text_diststa;
    private JTextField updaticket_text_price;
    private JTextField updatext_arrive;
    private JTextField updatext_gotime;
    private JTextField deleticket_text_ticketnum;
    private JTextField deleticket_text_ticketnumber;
    private JTextField adminsnodata_text;

    public AdministratorUI(){
        init_selectui();
        FrameManage.AdmiSelectframe = select_frame;
        select_frame.setVisible(true);
    }

    private void init_selectui(){
        select_frame = new JFrame();
        select_frame.setTitle("管理员选择功能");
        select_frame.setIconImage(Toolkit.getDefaultToolkit().getImage(MainUI.class.getResource("/image/admi.png")));
        select_frame.setBounds(400,200,760,600);
        select_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentpane = new JPanel();
        //将contentpane对象设置为frame的内容面板
        select_frame.setContentPane(contentpane);
        contentpane.setLayout(null);

        JButton goback_button = new JButton("返回");
        goback_button.setBounds(500,480,120,40);
        goback_button.setBackground(Color.GREEN);
        contentpane.add(goback_button);
        goback_button.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                select_frame.dispose();
                FrameManage.Active_Mainframe();
            }
        });

        JLabel welcome_label = new JLabel("管理员你好，请选择一个功能");
        welcome_label.setBounds(200,50,400,40);
        welcome_label.setFont(new Font("宋体",Font.BOLD,24));
        welcome_label.setForeground(Color.BLUE);
        contentpane.add(welcome_label);

        JButton traininfo_button = new JButton("管理列车信息");
        traininfo_button.setBounds(250,120,200,40);
        contentpane.add(traininfo_button);
        traininfo_button.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                select_frame.dispose();
                init_traininfoui();
                traininfo_frame.setVisible(true);
            }
        });

        JButton ticketinfo_button = new JButton("管理车票信息");
        ticketinfo_button.setBounds(250,200,200,40);
        contentpane.add(ticketinfo_button);
        ticketinfo_button.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                select_frame.dispose();
                init_ticketinfoui();
                ticketinfo_frame.setVisible(true);
            }
        });

        JButton ticketinfo_all = new JButton("查询全部车票信息");
        ticketinfo_all.setBounds(250,280,200,40);
        contentpane.add(ticketinfo_all);
        ticketinfo_all.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                select_frame.dispose();
                init_ticketinfoall();
                ticketinfo_frame.setVisible(true);
            }
        });

        JButton ticketadmin_all=new JButton("基本信息/注销");
        ticketadmin_all.setBounds(250,360,200,40);
        contentpane.add(ticketadmin_all);
        ticketadmin_all.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                select_frame.dispose();
                init_adminxinxi();
                admin_frame.setVisible(true);
            }
        });
    }


    //1。管理列车信息
    private void init_traininfoui(){
        traininfo_frame = new JFrame();
        traininfo_frame.setTitle("管理员页面");
        traininfo_frame.setIconImage(Toolkit.getDefaultToolkit().getImage(MainUI.class.getResource("/image/admi.png")));
        traininfo_frame.setBounds(400,200,1000,800);
        traininfo_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentpane = new JPanel();
        //将contentpane对象设置为frame的内容面板
        traininfo_frame.setContentPane(contentpane);
        contentpane.setLayout(null);

        JLabel admi = new JLabel("管理员操作火车票数据库：");
        admi.setFont(new Font("宋体",Font.BOLD,20));
        admi.setBounds(20,10,350,30);
        contentpane.add(admi);

        JButton goback_button = new JButton("返回");
        goback_button.setBounds(790,670,120,40);
        goback_button.setBackground(Color.GREEN);
        contentpane.add(goback_button);
        goback_button.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                traininfo_frame.dispose();
                FrameManage.Active_AdmiSelectUI();
            }
        });


        JLabel add_data = new JLabel("1.增加列车信息：");
        add_data.setFont(new Font("宋体",Font.BOLD,18));
        add_data.setForeground(Color.BLUE);
        add_data.setBounds(20,50,200,30);
        contentpane.add(add_data);
        //增加列车号信息
        JLabel add_trainsno = new JLabel("列车号");
        add_trainsno.setFont(new Font("宋体",Font.PLAIN,14));
        add_trainsno.setBounds(20,90,100,30);
        contentpane.add(add_trainsno);


        addtext_trainsno = new JTextField();
        addtext_trainsno.setBounds(90,90,200,30);
        addtext_trainsno.setColumns(10);
        contentpane.add(addtext_trainsno);

        //增加始发站信息
        JLabel add_startsta = new JLabel("始发站");
        add_startsta.setFont(new Font("宋体",Font.PLAIN,14));
        add_startsta.setBounds(20,130,100,30);
        contentpane.add(add_startsta);

        addtext_startsta = new JTextField();
        addtext_startsta.setBounds(90,130,200,30);
        addtext_startsta.setColumns(10);
        contentpane.add(addtext_startsta);

        //增加终点站信息
        JLabel add_endsta = new JLabel("终点站");
        add_endsta.setFont(new Font("宋体",Font.PLAIN,14));
        add_endsta.setBounds(20,170,100,30);
        contentpane.add(add_endsta);

        addtext_endsta = new JTextField();
        addtext_endsta.setBounds(90,170,200,30);
        addtext_endsta.setColumns(10);
        contentpane.add(addtext_endsta);

        //增加价格信息
        JLabel add_price = new JLabel("价格");
        add_price.setFont(new Font("宋体",Font.PLAIN,14));
        add_price.setBounds(20,210,100,30);
        contentpane.add(add_price);

        addtext_price = new JTextField();
        addtext_price.setBounds(90,210,200,30);
        addtext_price.setColumns(10);
        contentpane.add(addtext_price);
        //增加出发时刻信息
        JLabel add_arrivetime = new JLabel("出发时刻");
        add_arrivetime.setFont(new Font("宋体",Font.PLAIN,14));
        add_arrivetime.setBounds(20,250,100,30);
        contentpane.add(add_arrivetime);

        addtext_trainarrive = new JTextField();
        addtext_trainarrive.setBounds(90,250,200,30);
        addtext_trainarrive.setColumns(10);
        contentpane.add(addtext_trainarrive);
        //增加到达时刻信息
        JLabel add_gotime = new JLabel("到达时刻");
        add_gotime.setFont(new Font("宋体",Font.PLAIN,14));
        add_gotime.setBounds(20,290,100,30);
        contentpane.add(add_gotime);

        addtext_traingo = new JTextField();
        addtext_traingo.setBounds(90,290,200,30);
        addtext_traingo.setColumns(10);
        contentpane.add(addtext_traingo);
        //add_button
        JButton add_button = new JButton("确认添加");
        add_button.setBounds(80,350,120,30);
        contentpane.add(add_button);
        add_button.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                SqlWork isw = new SqlWork();
                String trainsno=addtext_trainsno.getText();
                String start=addtext_startsta.getText();
                String end = addtext_endsta.getText();
                String price = addtext_price.getText();
                String arrive=addtext_trainarrive.getText();
                String gotime=addtext_traingo.getText();
                isw.sql="insert TrainInfo values (\'"+trainsno+"\',\'"+start+"\',\'"+end+"\',\'"+price+"\',\'"+arrive+"\',\'"+gotime+"\')";
                System.out.println(isw.sql);
                if(SqlManage.ExecPro(isw)){
                    JOptionPane.showMessageDialog(traininfo_frame, "添加成功！");
                }
                else{
                    JOptionPane.showMessageDialog(traininfo_frame, "添加失败");
                }
            }

        });


         //查询列车信息
        JLabel sele_data = new JLabel("2.查询列车信息：");
        sele_data.setFont(new Font("宋体",Font.BOLD,18));
        sele_data.setForeground(Color.BLUE);
        sele_data.setBounds(20,400,200,30);
        contentpane.add(sele_data);

        JLabel sele_label = new JLabel("查询列车号");
        sele_label.setFont(new Font("宋体",Font.PLAIN,14));
        sele_label.setBounds(20,440,100,30);
        contentpane.add(sele_label);

        seletext_trainno = new JTextField();
        seletext_trainno.setBounds(90,440,150,30);
        seletext_trainno.setColumns(20);
        contentpane.add(seletext_trainno);

        final JLabel selename_label = new JLabel("--------------------------------name---------------------------------");
        selename_label.setFont(new Font("宋体",Font.PLAIN,12));
        selename_label.setBounds(20,490,500,40);
        contentpane.add(selename_label);

        final JLabel seledata_label = new JLabel("--------------------------------data---------------------------------");
        seledata_label.setFont(new Font("宋体",Font.PLAIN,12));
        seledata_label.setBounds(20,550,500,40);
        contentpane.add(seledata_label);

        JButton sele_button = new JButton("查询");
        sele_button.setBounds(300,440,120,30);
        contentpane.add(sele_button);

        sele_button.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                System.out.println("querying data.....................");
                SqlWork ssw = new SqlWork();
                String trainno = seletext_trainno.getText();
                String name="没有找到您查询的数据";
                String data="没有找到您查询的数据";
                ssw.sql = "select * from TrainInfo where Train_no=\'"+trainno+"\'";
                System.out.println(ssw.sql);
                if(0 == SqlManage.Select2(ssw)){
                    selename_label.setText(name);
                    seledata_label.setText(data);
                    JOptionPane.showMessageDialog(traininfo_frame, "没有您查找的数据！");
                }
                else{
                    name=ssw.namelist.get(0);
                    data=ssw.datalist.get(0);
                    selename_label.setText(name);
                    seledata_label.setText(data);
                }
            }
        });


        JLabel upda_data = new JLabel("3.修改列车信息:");
        upda_data.setFont(new Font("宋体",Font.BOLD,18));
        upda_data.setForeground(Color.BLUE);
        upda_data.setBounds(520,50,200,30);
        contentpane.add(upda_data);

        JLabel updatrainno_label = new JLabel("列车号");
        updatrainno_label.setBounds(560,90,100,30);
        updatrainno_label.setFont(new Font("宋体",Font.PLAIN,14));
        contentpane.add(updatrainno_label);

        updatext_trainno = new JTextField();
        updatext_trainno.setBounds(640,90,200,30);
        updatext_trainno.setColumns(20);
        contentpane.add(updatext_trainno);

        JLabel updastartsta_label = new JLabel("始发站");
        updastartsta_label.setBounds(560,130,100,30);
        updastartsta_label.setFont(new Font("宋体",Font.PLAIN,14));
        contentpane.add(updastartsta_label);

        updatext_startsta = new JTextField();
        updatext_startsta.setBounds(640,130,200,30);
        updatext_startsta.setColumns(20);
        contentpane.add(updatext_startsta);

        JLabel updaendsta_label = new JLabel("终点站");
        updaendsta_label.setBounds(560,170,100,30);
        updaendsta_label.setFont(new Font("宋体",Font.PLAIN,14));
        contentpane.add(updaendsta_label);

        updatext_endsta = new JTextField();
        updatext_endsta.setBounds(640,170,200,30);
        updatext_endsta.setColumns(20);
        contentpane.add(updatext_endsta);

        JLabel updaprice_label = new JLabel("价格");
        updaprice_label.setBounds(560,210,100,30);
        updaprice_label.setFont(new Font("宋体",Font.PLAIN,14));
        contentpane.add(updaprice_label);

        updatext_price = new JTextField();
        updatext_price.setBounds(640,210,200,30);
        updatext_price.setColumns(20);
        contentpane.add(updatext_price);

        JLabel updaarrive = new JLabel("出发时刻");
        updaarrive.setBounds(560,250,100,30);
        updaarrive.setFont(new Font("宋体",Font.PLAIN,14));
        contentpane.add(updaarrive);

        updatext_arrive = new JTextField();
        updatext_arrive.setBounds(640,250,200,30);
        updatext_arrive.setColumns(20);
        contentpane.add(updatext_arrive);

        JLabel updagotime = new JLabel("到达时刻");
        updagotime.setBounds(560,290,100,30);
        updagotime.setFont(new Font("宋体",Font.PLAIN,14));
        contentpane.add(updagotime);

        updatext_gotime = new JTextField();
        updatext_gotime.setBounds(640,290,200,30);
        updatext_gotime.setColumns(20);
        contentpane.add(updatext_gotime);

        JButton update_button = new JButton("更新");
        update_button.setBounds(620,350,120,30);
        contentpane.add(update_button);
        update_button.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                System.out.println("updating data.....................");
                SqlWork usw = new SqlWork();
                String trainno = updatext_trainno.getText();
                String start = updatext_startsta.getText();
                String end = updatext_endsta.getText();
                String price = updatext_price.getText();
                String arrivetime=updatext_arrive.getText();
                String go=updatext_gotime.getText();

                usw.sql = "update TrainInfo "
                        + "set Pass_sta=\'"+start+"\',Go_sta=\'"+end
                        +"\',Till_pri=\'"+price+"\',Come_time=\'"+arrivetime
                        +"\',Go_time=\'"+go+"\' where Train_no=\'"+trainno+"\'";
                System.out.println(usw.sql);
                if(SqlManage.Update(usw)){
                    JOptionPane.showMessageDialog(traininfo_frame, "更新成功");
                }
                else{
                    JOptionPane.showMessageDialog(traininfo_frame, "更新失败");
                }
            }

        });


        JLabel dele_data = new JLabel("4.删除列车信息：");
        dele_data.setFont(new Font("宋体",Font.BOLD,18));
        dele_data.setForeground(Color.BLUE);
        dele_data.setBounds(520,400,200,30);
        contentpane.add(dele_data);

        JLabel dele_label = new JLabel("删除的列车号");
        dele_label.setFont(new Font("宋体",Font.PLAIN,14));
        dele_label.setBounds(560,440,100,30);
        contentpane.add(dele_label);

        deletext_trainno = new JTextField();
        deletext_trainno.setBounds(680,440,200,30);
        deletext_trainno.setColumns(20);
        contentpane.add(deletext_trainno);

        //删除提示信息（列车号对应的相关信息）
        JLabel tip_label=new JLabel("tip:");
        tip_label.setFont(new Font("宋体",Font.PLAIN,12));
        tip_label.setBounds(540,550,30,30);
        contentpane.add(tip_label);

        final JLabel dele_tip = new JLabel("-------------------nothing to show----------------------");
        dele_tip.setFont(new Font("宋体",Font.PLAIN,12));
        dele_tip.setBounds(580,550,360,30);
        contentpane.add(dele_tip);


        JButton tip_button = new JButton("查看提示");
        tip_button.setBounds(560,495,120,30);
        contentpane.add(tip_button);

        tip_button.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                String trainno = deletext_trainno.getText();
                String tipstr = "";
                SqlWork ssw = new SqlWork();
                ssw.sql="select * from TrainInfo where Train_no=\'"+trainno+"\'";
                int count = SqlManage.Select2(ssw);
                System.out.println("querying the tip infomation...............");
                if(count == 0){
                    dele_tip.setText("没有找到您要删除的列车号");
                }
                else{
                    tipstr=ssw.datalist.get(0);
                    dele_tip.setText(tipstr);
                }
                System.out.println("query ok...............");
            }
        });

        JButton dele_button = new JButton("确认删除");
        dele_button.setBounds(800,495,120,30);
        contentpane.add(dele_button);

        dele_button.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                System.out.println("deleting date.....................");
                SqlWork dsw = new SqlWork();
                String trainno = deletext_trainno.getText();
                dsw.sql = "delete TrainInfo where Train_no=\'"+trainno+"\'";
                System.out.println(dsw.sql);
                if(SqlManage.Delete(dsw)){
                    JOptionPane.showMessageDialog(traininfo_frame, "删除成功！");
                }
                else{
                    JOptionPane.showMessageDialog(traininfo_frame, "删除失败");
                }
            }

        });



    }


    //2.管理车票信息
    private void init_ticketinfoui(){
        ticketinfo_frame = new JFrame();
        ticketinfo_frame.setTitle("管理员页面");
        ticketinfo_frame.setIconImage(Toolkit.getDefaultToolkit().getImage(MainUI.class.getResource("/image/admi.png")));
        ticketinfo_frame.setBounds(400,200,1000,800);
        ticketinfo_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentpane = new JPanel();
        //将contentpane对象设置为frame的内容面板
        ticketinfo_frame.setContentPane(contentpane);
        contentpane.setLayout(null);

        JLabel admi = new JLabel("管理员操作火车票数据库：");
        admi.setFont(new Font("宋体",Font.BOLD,20));
        admi.setForeground(Color.BLUE);
        admi.setBounds(20,10,350,30);
        contentpane.add(admi);

        JButton goback_button = new JButton("返回");
        goback_button.setBounds(790,670,120,40);
        goback_button.setBackground(Color.GREEN);
        contentpane.add(goback_button);
        goback_button.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                ticketinfo_frame.dispose();
                FrameManage.Active_AdmiSelectUI();
            }
        });
        //1.增加车票信息
        JLabel add_data = new JLabel("1.增加车票信息:");
        add_data.setFont(new Font("宋体",Font.BOLD,18));
        add_data.setForeground(Color.BLUE);
        add_data.setBounds(20,50,200,30);
        contentpane.add(add_data);

        //列车号
        JLabel addticket_trainno = new JLabel("对应列车号");
        addticket_trainno.setFont(new Font("宋体",Font.PLAIN,14));
        addticket_trainno.setBounds(40,90,100,30);
        contentpane.add(addticket_trainno);

        addticket_text_trainno = new JTextField();
        addticket_text_trainno.setBounds(140,90,200,30);
        addticket_text_trainno.setColumns(10);
        contentpane.add(addticket_text_trainno);


        //列车信息
        final JLabel addticket_traininfo_tip = new JLabel("train:------------------nothing-------------------");
        addticket_traininfo_tip.setFont(new Font("宋体",Font.PLAIN,14));
        addticket_traininfo_tip.setBounds(30,140,700,40);
        contentpane.add(addticket_traininfo_tip);

        //车票信息
        final JLabel addticket_ticketinfo_tip = new JLabel("结果:-------------------nothing-------------------");
        addticket_ticketinfo_tip.setFont(new Font("宋体",Font.PLAIN,14));
        addticket_ticketinfo_tip.setBounds(30,190,700,40);
        contentpane.add(addticket_ticketinfo_tip);

        JButton addticket_tipbutton = new JButton("查看提示");
        addticket_tipbutton.setBounds(30,240,120,30);
        contentpane.add(addticket_tipbutton);
        addticket_tipbutton.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                SqlWork isw = new SqlWork();
                String trainno=addticket_text_trainno.getText();
                isw.sql="select * from TrainInfo where Train_no=\'"+trainno+"\'";
                System.out.println(isw.sql);
                if(1 <= SqlManage.Select2(isw)){
                    addticket_traininfo_tip.setText(isw.datalist.get(0));
                    //addticket_ticketinfo_tip.setText
                    //JOptionPane.showMessageDialog(ticketinfo_frame, "添加成功！");
                }
                else{
                    JOptionPane.showMessageDialog(ticketinfo_frame, "列车信息不存在");
                }
            }

        });
        //add_button
        JButton addticket_addbutton = new JButton("确认添加");
        addticket_addbutton.setBounds(260,240,120,30);
        contentpane.add(addticket_addbutton);
        addticket_addbutton.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                SqlWork isw = new SqlWork();
                SqlWork ssw = new SqlWork();
                String trainno=addticket_text_trainno.getText();
                isw.sql="exec add_ticket \'"+trainno+"\'";
                ssw.sql="select * from Ticket where Train_no=\'"+trainno+"\'";
                System.out.println(isw.sql);
                if(SqlManage.ExecPro(isw)){
                    SqlManage.Select2(ssw);
                    addticket_ticketinfo_tip.setText(ssw.datalist.get(0));
                    JOptionPane.showMessageDialog(ticketinfo_frame, "添加成功");

                }
                else{
                    JOptionPane.showMessageDialog(ticketinfo_frame, "添加失败");
                }
            }

        });

        //2.修改车票信息
        JLabel upda_data = new JLabel("2.修改车票信息:");
        upda_data.setFont(new Font("宋体",Font.BOLD,18));
        upda_data.setForeground(Color.BLUE);
        upda_data.setBounds(20,300,200,30);
        contentpane.add(upda_data);

        JLabel upda_ticketno = new JLabel("更新车票号");
        upda_ticketno.setBounds(40,350,100,30);
        upda_ticketno.setFont(new Font("宋体",Font.PLAIN,14));
        contentpane.add(upda_ticketno);

        updaticket_text_ticketno = new JTextField();
        updaticket_text_ticketno.setBounds(140,350,200,30);
        updaticket_text_ticketno.setColumns(20);
        contentpane.add(updaticket_text_ticketno);

        JLabel upda_depasta = new JLabel("出发站");
        upda_depasta.setBounds(40,400,100,30);
        upda_depasta.setFont(new Font("宋体",Font.PLAIN,14));
        contentpane.add(upda_depasta);

        updaticket_text_depasta = new JTextField();
        updaticket_text_depasta.setBounds(140,400,200,30);
        updaticket_text_depasta.setColumns(20);
        contentpane.add(updaticket_text_depasta);

        JLabel upda_diststa = new JLabel("终点站");
        upda_diststa.setBounds(40,450,100,30);
        upda_diststa.setFont(new Font("宋体",Font.PLAIN,14));
        contentpane.add(upda_diststa);

        updaticket_text_diststa = new JTextField();
        updaticket_text_diststa.setBounds(140,450,200,30);
        updaticket_text_diststa.setColumns(20);
        contentpane.add(updaticket_text_diststa);

        JLabel upda_price = new JLabel("价格");
        upda_price.setBounds(40,500,100,30);
        upda_price.setFont(new Font("宋体",Font.PLAIN,14));
        contentpane.add(upda_price);

        updaticket_text_price = new JTextField();
        updaticket_text_price.setBounds(140,500,200,30);
        updaticket_text_price.setColumns(20);
        contentpane.add(updaticket_text_price);

        JButton update_button = new JButton("更新");
        update_button.setBounds(260,300,120,30);
        contentpane.add(update_button);
        update_button.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                System.out.println("updating data.....................");
                SqlWork usw = new SqlWork();
                String ticketno = updaticket_text_ticketno.getText();
                String depa = updaticket_text_depasta.getText();
                String dest = updaticket_text_diststa.getText();
                String price = updaticket_text_price.getText();

                usw.sql = "update Ticket "
                        + "set Depa_sta=\'"+depa+"\',Dest_sta=\'"+dest
                        +"\',Price=\'"+price+"\' where Tick_no=\'"+ticketno+"\'";
                System.out.println(usw.sql);
                if(SqlManage.Update(usw)){
                    JOptionPane.showMessageDialog(ticketinfo_frame, "更新成功");
                }
                else{
                    JOptionPane.showMessageDialog(ticketinfo_frame, "更新失败");
                }
            }

        });

        //3修改车票数量
        JLabel xiugai_data=new JLabel("3.修改车票数量：");
        xiugai_data.setFont(new Font("宋体",Font.BOLD,18));
        xiugai_data.setForeground(Color.BLUE);
        xiugai_data.setBounds(20,550,150,30);
        contentpane.add(xiugai_data);

        JLabel xiu_label=new JLabel("车票号：");
        xiu_label.setFont(new Font("宋体", Font.PLAIN,14));
        xiu_label.setBounds(190,550,80,30);
        contentpane.add(xiu_label);

        deleticket_text_ticketnum=new JTextField();
        deleticket_text_ticketnum.setBounds(260,550,120,30);
        deleticket_text_ticketnum.setColumns(20);
        contentpane.add(deleticket_text_ticketnum);


        JLabel xiu_number=new JLabel("车票数量");
        xiu_number.setFont(new Font("宋体",Font.PLAIN,14));
        xiu_number.setBounds(40,590,100,30);
        contentpane.add(xiu_number);

        deleticket_text_ticketnumber=new JTextField();
        deleticket_text_ticketnumber.setBounds(140,590,200,30);
        deleticket_text_ticketnumber.setColumns(20);
        contentpane.add(deleticket_text_ticketnumber);

        JLabel dele_tip_num=new JLabel("tip:");
        dele_tip_num.setFont(new Font("宋体",Font.PLAIN,12));
        dele_tip_num.setBounds(30,640,30,40);
        contentpane.add(dele_tip_num);

        final JLabel dele_num=new JLabel("--------------------nothing to show--------------------");
        dele_num.setFont(new Font("宋体",Font.PLAIN,12));
        dele_num.setBounds(70,640,500,40);
        contentpane.add(dele_num);

        JButton tip_num=new JButton("查看提示");
        tip_num.setBounds(30,690,120,30);
        contentpane.add(tip_num);

        tip_num.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String ticketno=deleticket_text_ticketnum.getText();
                String tipstr="";
                SqlWork svw=new SqlWork();
                svw.sql="select * from TicketRefer where Tick_no=\'"+ticketno+"\'";
                int count=SqlManage.Select1(svw);
                System.out.println("querying the tip infomation...............");
                if(count == 0){
                    dele_num.setText("没有找到您要查找的车票号");
                }
                else
                {
                    tipstr=svw.datalist.get(0);
                    dele_num.setText(tipstr);
                }
                System.out.println("query ok...............");

            }
        });

        JButton xiugai_button=new JButton("确认修改");
        xiugai_button.setBounds(260,690,120,30);
        contentpane.add(xiugai_button);

        xiugai_button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("updating date.....................");
                SqlWork dvw = new SqlWork();
                String ticket = deleticket_text_ticketnum.getText();
                String numbers=deleticket_text_ticketnumber.getText();
                dvw.sql = "update TicketRefer "
                        + "set Amou=\'"+numbers+"\' where Tick_no=\'"+ticket+"\'";
                System.out.println(dvw.sql);
                if(SqlManage.Update(dvw)){
                    JOptionPane.showMessageDialog(ticketinfo_frame, "修改成功！");
                }
                else{
                    JOptionPane.showMessageDialog(ticketinfo_frame, "修改失败");
                }
            }
        });

        //4.查询列车信息
        JLabel sele_data = new JLabel("4.查询车票信息:");
        sele_data.setFont(new Font("宋体",Font.BOLD,18));
        sele_data.setForeground(Color.BLUE);
        sele_data.setBounds(520,50,200,30);
        contentpane.add(sele_data);

        JLabel sele_label = new JLabel("查询车票号");
        sele_label.setFont(new Font("宋体",Font.PLAIN,14));
        sele_label.setBounds(540,90,100,30);
        contentpane.add(sele_label);

        seleticket_text_ticketno = new JTextField();
        seleticket_text_ticketno.setBounds(640,90,200,30);
        seleticket_text_ticketno.setColumns(20);
        contentpane.add(seleticket_text_ticketno);

        final JLabel selename_label = new JLabel("----------------------------name----------------------");
        selename_label.setFont(new Font("宋体",Font.PLAIN,12));
        selename_label.setBounds(530,140,700,40);
        contentpane.add(selename_label);

        final JLabel seledata_label = new JLabel("----------------------------data----------------------");
        seledata_label.setFont(new Font("宋体",Font.PLAIN,12));
        seledata_label.setBounds(530,190,700,40);
        contentpane.add(seledata_label);

        JButton sele_button = new JButton("查询");
        sele_button.setBounds(760,50,120,30);
        contentpane.add(sele_button);

        sele_button.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                System.out.println("querying data.....................");
                SqlWork ssw = new SqlWork();
                String ticketno = seleticket_text_ticketno.getText();
                String name="没有找到您查询的数据";
                String data="没有找到您查询的数据";
                ssw.sql = "select * from Ticket where Tick_no=\'"+ticketno+"\'";
                System.out.println(ssw.sql);
                if(0 == SqlManage.Select2(ssw)){
                    selename_label.setText(name);
                    seledata_label.setText(data);
                    JOptionPane.showMessageDialog(ticketinfo_frame, "没有您查找的数据！");
                }
                else{
                    name=ssw.namelist.get(0);
                    data=ssw.datalist.get(0);
                    selename_label.setText(name);
                    seledata_label.setText(data);
                }
            }
        });

        //5.删除车票信息
        JLabel dele_data = new JLabel("5.删除车票信息:");
        dele_data.setFont(new Font("宋体",Font.BOLD,18));
        dele_data.setForeground(Color.BLUE);
        dele_data.setBounds(520,250,200,30);
        contentpane.add(dele_data);

        JLabel dele_label = new JLabel("删除的车票号");
        dele_label.setFont(new Font("宋体",Font.PLAIN,14));
        dele_label.setBounds(540,300,100,30);
        contentpane.add(dele_label);

        deleticket_text_ticketno = new JTextField();
        deleticket_text_ticketno.setBounds(640,300,200,30);
        deleticket_text_ticketno.setColumns(20);
        contentpane.add(deleticket_text_ticketno);

        //删除提示信息（车票号对应的相关信息）
        JLabel dele_tip_label=new JLabel("tip:");
        dele_tip_label.setFont(new Font("宋体",Font.PLAIN,12));
        dele_tip_label.setBounds(520,350,30,40);
        contentpane.add(dele_tip_label);

        final JLabel dele_tip = new JLabel("--------------------nothing to show--------------------");
        dele_tip.setFont(new Font("宋体",Font.PLAIN,12));
        dele_tip.setBounds(560,350,500,40);
        contentpane.add(dele_tip);


        JButton tip_button = new JButton("查看提示");
        tip_button.setBounds(530,400,120,30);
        contentpane.add(tip_button);

        tip_button.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                String ticketno = deleticket_text_ticketno.getText();
                String tipstr = "";
                String tipstr1="";
                SqlWork ssw = new SqlWork();
                SqlWork suw=new SqlWork();
                suw.sql="select * from TicketRefer where Tick_no=\'"+ticketno+"\'";

                ssw.sql="select * from Ticket where Tick_no=\'"+ticketno+"\'";
                int count = SqlManage.Select2(ssw);
                int count1=SqlManage.Select2(suw);
                System.out.println("querying the tip infomation...............");
                if(count == 0){
                    if(count1==0) {
                        dele_tip.setText("没有找到您要删除的车票号！");
                    }
                    else {
                        dele_tip.setText("该车票号设置的票数没有一起删除哟！");
                        tipstr1=suw.datalist.get(0);
                        dele_tip.setText(tipstr1);
                    }
                }
                else{
                    tipstr=ssw.datalist.get(0);
                    dele_tip.setText(tipstr);
                }
                System.out.println("query ok...............");
            }
        });

        JButton dele_button = new JButton("确认删除");
        dele_button.setBounds(760,400,120,30);
        contentpane.add(dele_button);

        dele_button.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                System.out.println("deleting date.....................");
                SqlWork dsw = new SqlWork();
                SqlWork duw=new SqlWork();
                String ticket = deleticket_text_ticketno.getText();
                dsw.sql = "delete Ticket where Tick_no=\'"+ticket+"\'";
                duw.sql="delete TicketRefer where Tick_no=\'"+ticket+"\'";
                System.out.println(dsw.sql);
                System.out.println(duw.sql);
                if(SqlManage.Delete(dsw)&&SqlManage.Delete((duw))){
                    JOptionPane.showMessageDialog(ticketinfo_frame, "删除成功！");
                }
                else{
                    JOptionPane.showMessageDialog(ticketinfo_frame, "删除失败");
                }
            }

        });





    }

    //3.查询全部列车信息
    private void init_ticketinfoall(){
        ticketinfo_frame=new JFrame();
        ticketinfo_frame.setTitle("管理员页面");
        ticketinfo_frame.setIconImage(Toolkit.getDefaultToolkit().getImage(MainUI.class.getResource("/image/admi.png")));
        ticketinfo_frame.setBounds(400,200,1000,800);
        ticketinfo_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentpane = new JPanel();
        //将contentpane对象设置为frame的内容面板
        ticketinfo_frame.setContentPane(contentpane);
        contentpane.setLayout(null);
        JLabel admi1 = new JLabel("查询全部的车票：");
        admi1.setFont(new Font("宋体",Font.BOLD,20));
        admi1.setForeground(Color.BLUE);
        admi1.setBounds(20,10,350,30);
        contentpane.add(admi1);

        JButton goback1_button = new JButton("返回");
        goback1_button.setBounds(790,670,120,40);
        goback1_button.setBackground(Color.GREEN);
        contentpane.add(goback1_button);
        goback1_button.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                ticketinfo_frame.dispose();
                FrameManage.Active_AdmiSelectUI();
            }
        });
        JButton sele1_button = new JButton("查询");
        sele1_button.setBounds(260,30,120,50);
        contentpane.add(sele1_button);
        final JLabel selename_label = new JLabel("--------------------------------name--------------------------");
        selename_label.setFont(new Font("宋体",Font.PLAIN,18));
        selename_label.setBounds(40,140,800,40);
        contentpane.add(selename_label);

        final JLabel seledata_label = new JLabel("--------------------------data-------------------");
        seledata_label.setFont(new Font("宋体",Font.PLAIN,21));
        seledata_label.setBounds(40,160,600,300);
        contentpane.add(seledata_label);

        sele1_button.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                System.out.println("正在查询数据....................."); // Changed print message to Chinese
                SqlWork ssw = new SqlWork();
                String name="没有找到您查询的数据";
                String data="没有找到您查询的数据";
                ssw.sql = "select * from Ticket ";
                System.out.println(ssw.sql);
                if(SqlManage.Select1(ssw) > 0){ // Changed condition to check if there are results
                    StringBuilder names = new StringBuilder();
                    for (String n : ssw.namelist) {
                        names.append(n).append("\n"); // Append a newline after each column name
                    }
                    selename_label.setText(names.toString());
                    StringBuilder tableHtml = new StringBuilder("<html><table border='1'>");
                    for (int i=ssw.datalist.size()-1; i<ssw.datalist.size(); i++) {
                        tableHtml.append("<tr><td>").append(ssw.datalist.get(i).replace("\t", "</td><td>")).append("</td></tr>"); // Creating table rows and columns using HTML tags
                    }
                    tableHtml.append("</table></html>"); // Closing the HTML table
                    seledata_label.setText(tableHtml.toString()); // Setting the label text as the complete HTML table
                } else {
                    selename_label.setText(name);
                    seledata_label.setText(data);
                    JOptionPane.showMessageDialog(ticketinfo_frame, "没有您查找的数据！");
                }
            }
        });
    }

    //4.基本信息/注销
    private void init_adminxinxi()
    {
        admin_frame=new JFrame();
        admin_frame.setTitle("管理员界面");
        admin_frame.setIconImage(Toolkit.getDefaultToolkit().getImage(MainUI.class.getResource("/image/cust.png")));
        admin_frame.setBounds(400,200,750,550);
        admin_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentpane=new JPanel();
        admin_frame.setContentPane(contentpane);
        admin_frame.setLayout(null);
        JLabel numaljiben_label=new JLabel("管理员个人基本信息：");
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
                admin_frame.dispose();
                FrameManage.Active_AdmiSelectUI();
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
                String adminsno1=adminsnodata_text.getText();
                dqw.sql="delete  Administrator where Admi_id=\'"+adminsno1+"\'";
                System.out.println(dqw.sql);
                if(SqlManage.Delete(dqw)){
                    JOptionPane.showMessageDialog(admin_frame,"注销成功！");
                    admin_frame.dispose();
                    FrameManage.Active_Mainframe();
                }
                else{
                    JOptionPane.showMessageDialog(admin_frame,"注销失败！");
                }

            }
        });
        JLabel adminsno_label=new JLabel("注册名：");
        adminsno_label.setFont(new Font("宋体",Font.BOLD,14));
        adminsno_label.setBounds(240,10,100,30);
        contentpane.add(adminsno_label);

        adminsnodata_text=new JTextField();
        adminsnodata_text.setBounds(380,10,120,30);
        contentpane.add(adminsnodata_text);


        JLabel adminno_label=new JLabel("管理员名:");
        adminno_label.setFont(new Font("宋体",Font.BOLD,16));
        adminno_label.setBounds(260,140,100,30);
        contentpane.add(adminno_label);

        final JLabel adminnodata_text=new JLabel("*****");
        adminnodata_text.setFont(new Font("宋体",Font.PLAIN,14));
        adminnodata_text.setBounds(380,140,200,30);
        contentpane.add(adminnodata_text);

        JLabel adminpwd_label=new JLabel("密码：");
        adminpwd_label.setFont(new Font("宋体",Font.BOLD,16));
        adminpwd_label.setBounds(260,200,100,30);
        contentpane.add(adminpwd_label);

        final JLabel adminpwddata_text=new JLabel("*****");
        adminpwddata_text.setFont(new Font("宋体",Font.PLAIN,14));
        adminpwddata_text.setBounds(380,200,200,30);
        contentpane.add(adminpwddata_text);

        JLabel adminido_label=new JLabel("管理员编号：");
        adminido_label.setFont(new Font("宋体",Font.BOLD,16));
        adminido_label.setBounds(260,260,100,30);
        contentpane.add(adminido_label);

        final JLabel adminidodata_text=new JLabel("*****");
        adminidodata_text.setFont(new Font("宋体",Font.PLAIN,14));
        adminidodata_text.setBounds(380,260,200,30);
        contentpane.add(adminidodata_text);

        JLabel adminid_label=new JLabel("联系电话：");
        adminid_label.setFont(new Font("宋体",Font.BOLD,16));
        adminid_label.setBounds(260,320,100,30);
        contentpane.add(adminid_label);

        final JLabel adminiddata_text=new JLabel("*****");
        adminiddata_text.setFont(new Font("宋体",Font.PLAIN,14));
        adminiddata_text.setBounds(380,320,200,30);
        contentpane.add(adminiddata_text);

        JButton adminsele_button=new JButton("查询");
        adminsele_button.setBounds(550,10,120,40);
        contentpane.add(adminsele_button);

        adminsele_button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SqlWork saqw=new SqlWork();
                String adminsno=adminsnodata_text.getText();
                String[] data2=new String[4];
                saqw.sql="select Admi_id,Admi_pwd,Admi_no,Admi_phone from Administrator where Admi_id=\'"+adminsno+"\'";
                if(SqlManage.Select2(saqw)>=1){
                    data2=saqw.datalist.get(0).split(" ");
                    adminnodata_text.setText(data2[0]);
                    adminpwddata_text.setText(data2[1]);
                    adminidodata_text.setText(data2[2]);
                    adminiddata_text.setText(data2[3]);

                }
                else {
                    String temp2="*****";
                    adminnodata_text.setText(temp2);
                    adminpwddata_text.setText(temp2);
                    adminidodata_text.setText(temp2);
                    adminiddata_text.setText(temp2);
                    JOptionPane.showMessageDialog(admin_frame,"系统出错了，请稍后重试！");
                }
            }
        });

    }
}