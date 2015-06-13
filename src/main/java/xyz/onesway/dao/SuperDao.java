package xyz.onesway.dao;

import java.sql.*;

import xyz.onesway.tool.DB_manager;

/**
 * @author Ben Li
 * @version Date：2015年5月31日 下午5:06:19
 */
public class SuperDao {
    public boolean  exe_Update(String sql,Object... args){
        //获取连接对象，并判断是否可用
        Connection con = DB_manager.getConnection();
        if (con == null)
            return false;
        PreparedStatement ps=null;
        try {
            //通过链接对象获取 预处理 ps 装置
            //为 ps 准备 sql 语句
            ps=con.prepareStatement(sql);
            //如果有占位符‘？’，则根据 数组需要（位置）赋值
            if(args!=null&&args.length>0){
                for (int i = 0; i < args.length; i++) {
                    ps.setObject(i+1, args[i]);
                }
            }
            //执行操作返回 结果
            return ps.executeUpdate()>0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //关闭相关 jdbc 资源
            DB_manager.closeJDBC(null, ps, con);
        }
        return false;
    }
    
}
