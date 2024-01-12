package Info;

import SqlTran.SqlManage;
import StartSys.SqlWork;

public class AccountInfo {

    //用户注册
    public static boolean Regist(String account, String password, String fnumber){
        System.out.println("registing account.................");
        SqlWork registsw = new SqlWork();
        registsw.sql = "insert Customer values (\'"+account+"\',\'"+password+"\',\'"+fnumber+"\')";
        System.out.println(registsw.sql);

        if(!SqlManage.Insert(registsw)){
            return false;
        }
        else{
            System.out.println("regist successfully.................");
            return true;
        }
    }

    //用户登录
    public static boolean CheckAccount(String account, String password){
        System.out.println("checking account.................");
        SqlWork checksw = new SqlWork();
        checksw.sql = "select count(*) from Customer where Cust_id= \'"+account+"\' and Cust_pwd= \'"+password+"\'";
        System.out.println(checksw.sql);
        if(0 == SqlManage.Select(checksw)){
            return false;
        }
        else{
            System.out.println("find successfully");
            return true;
        }
    }

    //管理员注册
    public static boolean Regist1(String account, String password, String fnumber,String telephone){
        System.out.println("registing account.................");
        SqlWork registdw = new SqlWork();
        registdw.sql = "insert Administrator values (\'"+account+"\',\'"+password+"\',\'"+fnumber+"\',\'"+telephone+"\')";
        System.out.println(registdw.sql);
        if(!SqlManage.Insert(registdw)){
            return false;
        }
        else{
            System.out.println("regist1 successfully.................");
            return true;
        }
    }

    //管理员登录
    public static boolean CheckAccount1(String account, String password,String fnumber){
        System.out.println("checking account.................");
        SqlWork checkdw = new SqlWork();
        checkdw.sql = "select count(*) from Administrator where Admi_id= \'"+account+"\' and Admi_pwd= \'"+password+"\'and Admi_no=\'"+fnumber+"\'";
        System.out.println(checkdw.sql);
        if(0 == SqlManage.Select(checkdw)){
            return false;
        }
        else{
            System.out.println("find successfully");
            return true;
        }
    }
}
