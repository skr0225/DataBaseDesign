package StartSys;

import Info.AccountInfo;
import SqlTran.SqlManage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainUI{
    public JFrame frame;
    private JTextField textfield_1;
    private JTextField textfield_2;
    private JTextField textfield_3;
    private JTextField textfield_4;
    private JPanel contentpane;
    private JRadioButton normaluser;
    private JRadioButton administrator;

    public static void main(String[] args) {
        MainUI window = new MainUI();
        FrameManage.Mainframe = window.frame;
        window.frame.setVisible(true);
    }

    public MainUI(){
        //连接数据库“TicketMS”
        SqlManage.ConnectToServer();
        //初始化图形用户界面
        initialize();
    }

    private void initialize(){
        frame = new JFrame();
        frame.setTitle("登陆系统界面");
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(MainUI.class.getResource("/image/myicon.png")));
        frame.setBounds(400,200,800,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentpane = new JPanel() ;//将contentpane对象设置为frame的内容
        frame.setContentPane(contentpane);
        contentpane.setLayout(null);


        JLabel login_title = new JLabel("列车售票信息管理系统");
        login_title.setFont(new Font("宋体",Font.BOLD,26));
        login_title.setForeground(Color.BLUE);
        login_title.setBounds(260,10,400,30);
        contentpane.add(login_title);

        ImageIcon img = new ImageIcon("./src/image/train.png");//创建图片对象
        JLabel image_label = new JLabel();
        image_label.setBounds(320,10,400,400);
        image_label.setIcon(img);
        contentpane.add(image_label);

        JLabel account = new JLabel("*用户名");
        account.setFont(new Font("宋体",Font.PLAIN,16));
        account.setBounds(40,30,60,100);
        contentpane.add(account);

        JLabel password = new JLabel("*密码");
        password.setFont(new Font("宋体",Font.PLAIN,16));
        password.setBounds(40,90,60,100);
        contentpane.add(password);

        JLabel fnumber = new JLabel("*编号");
        fnumber.setFont(new Font("宋体",Font.PLAIN,16));
        fnumber.setBounds(40,150,60,100);
        contentpane.add(fnumber);

        JLabel telephone = new JLabel("电话");
        telephone.setFont(new Font("宋体",Font.PLAIN,16));
        telephone.setBounds(40,210,60,100);
        contentpane.add(telephone);

        textfield_1 = new JTextField();
        textfield_1.setBounds(120,60,140,30);
        textfield_1.setColumns(10);
        contentpane.add(textfield_1);

        textfield_2 = new JTextField();
        textfield_2.setBounds(120,120,140,30);
        textfield_2.setColumns(10);
        contentpane.add(textfield_2);

        textfield_3 = new JTextField();
        textfield_3.setBounds(120,180,140,30);
        textfield_3.setColumns(10);
        contentpane.add(textfield_3);

        textfield_4 = new JTextField();
        textfield_4.setBounds(120,240,140,30);
        textfield_4.setColumns(10);
        contentpane.add(textfield_4);

        normaluser = new JRadioButton("普通用户");
        normaluser.setBounds(50,280,100,50);
        contentpane.add(normaluser);

        administrator = new JRadioButton("售票管理员");
        administrator.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                normaluser.setSelected(false);
            }
        });
        administrator.setBounds(180,280,100,50);
        contentpane.add(administrator);
        normaluser.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                administrator.setSelected(false);
            }
        });
        //注册状态设置
        JButton regist = new JButton("注册");
        regist.setBounds(40,340,80,30);
        contentpane.add(regist);
        regist.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                String account = textfield_1.getText();
                String password = textfield_2.getText();
                String fnumber=textfield_3.getText();
                String telephone=textfield_4.getText();
                if (normaluser.isSelected()) {
                    // Register as a normal user
                    if (!AccountInfo.Regist(account, password, fnumber)) {
                        JOptionPane.showMessageDialog(frame, "注册失败！");
                    } else {
                        JOptionPane.showMessageDialog(frame, "恭喜你，注册成功！");
                        //frame.dispose();
                    }
                } else if (administrator.isSelected()) {
                    // Register as an administrator
                    if (!AccountInfo.Regist1(account, password, fnumber, telephone)) {
                        JOptionPane.showMessageDialog(frame, "注册失败！");
                    } else {
                        JOptionPane.showMessageDialog(frame, "恭喜你，注册成功!");
                        //frame.dispose();
                    }
                }
            }

        });

        //登陆状态设置
        JButton login = new JButton("登录");
        login.setBounds(180,340,80,30);
        contentpane.add(login);
        login.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                if(!(normaluser.isSelected()) && !(administrator.isSelected())){
                    JOptionPane.showMessageDialog(frame, "您好，请选择身份：普通用户/列车管理员");
                    return ;
                }
                String account = textfield_1.getText();
                String password = textfield_2.getText();
                String fnumber=textfield_3.getText();


                if(normaluser.isSelected())
                {
                    if(account.equals("")||account==null)
                    {
                        JOptionPane.showMessageDialog(frame, "您好，用户名不能为空");
                    }
                    else if(password.equals("")||password==null)
                    {
                        JOptionPane.showMessageDialog(frame, "您好，密码不能为空");
                    }
                    else
                    {
                        if(AccountInfo.CheckAccount(account, password))
                        {
                            JOptionPane.showMessageDialog(frame, "用户登陆成功！");
                            frame.dispose();
                            NormalUserUI normaluser_ui = new NormalUserUI();
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(frame, "普通用户或密码错误！");

                        }
                    }

                }
                else if(administrator.isSelected())
                {
                            if(account.equals("")||account==null)
                            {
                                JOptionPane.showMessageDialog(frame, "您好，用户名不能为空");
                            }
                            else if(password.equals("")||password==null)
                            {
                                JOptionPane.showMessageDialog(frame, "您好，密码不能为空");
                            }
                            else if (fnumber.equals("")||fnumber==null)
                            {
                                JOptionPane.showMessageDialog(frame,"您好，编号不能为空！");
                            }
                            else {
                                if (AccountInfo.CheckAccount1(account, password, fnumber)) {
                                    JOptionPane.showMessageDialog(frame, "管理员登陆成功！");
                                    frame.dispose();
                                    AdministratorUI administrator_ui = new AdministratorUI();
                                } else {
                                    JOptionPane.showMessageDialog(frame, "管理员用户或密码错误！");

                                }
                            }
                }
            }

        });

        //作者信息
        JLabel authorinfo = new JLabel();
        authorinfo.setBounds(490,380,300,30);
        authorinfo.setFont(new Font("宋体",Font.PLAIN,18));
        authorinfo.setText("项目成员：202126004王伊彬");
        contentpane.add(authorinfo);

        JLabel autherinfo1=new JLabel();
        autherinfo1.setBounds(580,420,300,30);
        autherinfo1.setFont(new Font("宋体",Font.PLAIN,18));
        autherinfo1.setText("202126002王欢");
        contentpane.add(autherinfo1);

        JLabel txt1 = new JLabel();
        txt1.setBounds(30,420,400,30);
        txt1.setFont(new Font("宋体",Font.PLAIN,14));
        txt1.setForeground(Color.RED);
        txt1.setText("注：(1)注册时普通用户只需填写加*的，管理员注册时全填。");
        contentpane.add(txt1);

        JLabel txt2 = new JLabel();
        txt2.setBounds(30,450,600,30);
        txt2.setFont(new Font("宋体",Font.PLAIN,14));
        txt2.setForeground(Color.RED);
        txt2.setText("    (2)注册时编号在普通用户中为身份证号，在管理员用户中为管理员编号。" );
        contentpane.add(txt2);

        JLabel txt3 = new JLabel();
        txt3.setBounds(30,480,600,30);
        txt3.setFont(new Font("宋体",Font.PLAIN,14));
        txt3.setForeground(Color.RED);
        txt3.setText("    (3)登陆时普通用户只需填写用户名和密码,管理员需要填写用户名、密码和编号。" );
        contentpane.add(txt3);

    }

}

