package StartSys;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static SqlTran.SqlManage.dbconn;

// 声明一个名为SqlWork的公共类
public class SqlWork {
    // 声明一个公共字符串变量sql，用于存储SQL查询语句
    public String sql;

    // 声明一个公共整型变量count并初始化为0，可能用于计数查询结果的数量
    public int count=0;

    // 声明一个公共的ResultSet对象rs并初始化为null，ResultSet是用于存储查询结果的
    public ResultSet rs = null;

    // 声明一个公共的字符串列表namelist并初始化为一个新的ArrayList，用于存储查询结果的列名
    public List<String> namelist = new ArrayList<String>();

    // 声明一个公共的字符串列表datalist并初始化为一个新的ArrayList，用于存储查询结果的每一行数据
    public List<String> datalist = new ArrayList<String>();

    // 声明一个公共方法addtolist，没有参数，没有返回值
    public void addtolist(){
        // 如果rs（ResultSet对象）为null，则直接返回，不执行后续代码
        if(rs == null)return;

        // 声明一个临时字符串变量tempstr并初始化为空字符串
        String tempstr="";

        try {
            // 通过rs（ResultSet对象）获取元数据并存储在变量rsmd中
            ResultSetMetaData rsmd = rs.getMetaData();

            // 从元数据中获取列数，并存储在整数变量columncount中
            int columncount = rsmd.getColumnCount();

            // 开始一个for循环，从1迭代到列数
            for(int j=1;j<=columncount;++j){
                // 将列名添加到临时字符串变量tempstr中，并添加一个空格
                tempstr=tempstr+rsmd.getColumnName(j).trim()+" ";
            }
            // 将包含所有列名的临时字符串添加到列名列表中
            namelist.add(tempstr);
            // 重置临时字符串变量为空字符串
            tempstr="";

            // 开始一个while循环，循环遍历查询结果的每一行
            while(rs.next()){
                // 增加计数器
                count++;
                // 再次开始一个for循环，从1迭代到列数
                for(int j=1;j<=columncount;++j){
                    // 将当前行的列值添加到临时字符串变量中，并添加一个空格
                    tempstr=tempstr+rs.getString(j).trim()+" ";
                }
                // 将包含当前行所有列值的临时字符串添加到数据列表中
                datalist.add(tempstr);
            } // 结束while循环
            // 关闭ResultSet对象，释放资源
            rs.close();
        } catch (SQLException e) {
            // 如果在try块中的代码抛出SQLException异常，则进入catch块处理
            // TODO: 这里可以添加处理异常的代码，例如记录日志或抛出自定义异常等。
            e.printStackTrace(); // 打印异常的堆栈跟踪信息。
        } // 结束catch块。
    }// 结束方法。

} // 结束类。


