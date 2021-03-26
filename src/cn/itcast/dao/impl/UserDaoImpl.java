package cn.itcast.dao.impl;

import cn.itcast.dao.UserDao;
import cn.itcast.domain.User;
import cn.itcast.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserDaoImpl implements UserDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 查询所有用户信息
     * @return
     */

    @Override
    public List<User> findAll() {

        //使用JDBC操作数据库
        //1、定义sql
        String sql = "select * from user_information";
        //2、执行
        List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class));

        return users;
    }

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */

    @Override
    public User login(String username, String password) {
        try {
            //定义sql
            String sql = "select * from user_information where username= ? and password= ? ";
            //执行
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username,password);

            return user;

        }catch (Exception e){

            e.printStackTrace();

            return null;
        }
    }

    /**
     * 添加用户信息
     * @param user
     */

    @Override
    public void add(User user) {

        //定义sql语句
        String sql = "insert into user_information values(default,null,null,?,?,?,?,?)";

        //执行sql
        template.update(sql,user.getName(),user.getGender(),user.getAge(),user.getAddress(),user.getEmail());

    }

    /**
     * 删除某条用户信息
     * @param id
     */

    @Override
    public void delete(int id) {
        //sql语句
        String sql = "delete from user_information where id = ? ";
        //执行sql
        template.update(sql,id);
    }


    /**
     * 数据回显，按用户id查询
     * @param id
     * @return
     */
    @Override
    public User findById(int id) {
        //定义sql语句
        String sql = "select * from user_information where id = ? ";
        //执行
        User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), id);

        return user;

    }

    /**
     * 修改用户信息
     * @param user
     */
    @Override
    public void update(User user) {
        //定义sql语句
        String sql = "update user_information set name=?,gender=?,age=?,address=?,email=? where id=?";
        //执行
        template.update(sql,user.getName(),user.getGender(),user.getAge(),user.getAddress(),user.getEmail(),user.getId());



    }

    /**
     * 获取总记录数
     * @param condition
     * @return
     */

    @Override
    public int findTotalCount(Map<String, String[]> condition) {
        //定义模板初始化sql
        String sql = "select count(*) from user_information where 1=1  ";

        StringBuilder sb = new StringBuilder(sql);
        //遍历map集合
        Set<String> keySet = condition.keySet();

        //定义参数集合
        List<Object> parms = new ArrayList<Object>();

        for (String key : keySet) {

            //排除currentPage和rows
            if("currentPage".equals(key) || "rows".equals(key)){
                continue;
            }
            //获取value
            String value = condition.get(key)[0];
            //判断value是否有值
            if(value!=null && !"".equals(value)){
                //有值
                sb.append(" and " + key + " like ? ");
                //？条件的值
                parms.add("%"+value+"%");
            }

        }
        System.out.println(sb.toString());
        System.out.println(parms);

        //执行
        Integer totalCount = template.queryForObject(sb.toString(), Integer.class,parms.toArray());

        return totalCount;

    }

    /**
     *
     * @param start
     * @param rows
     * @param condition
     * @return
     */
    @Override
    public List<User> findByPage(int start, int rows, Map<String, String[]> condition) {
        //定义sql语句
        String sql = "select * from user_information where 1 = 1 ";

        StringBuilder sb = new StringBuilder(sql);
        //遍历map集合
        Set<String> keySet = condition.keySet();

        //定义参数集合
        List<Object> parms = new ArrayList<Object>();

        for (String key : keySet) {

            //排除currentPage和rows
            if("currentPage".equals(key) || "rows".equals(key)){
                continue;
            }
            //获取value
            String value = condition.get(key)[0];
            //判断value是否有值
            if(value!=null && !"".equals(value)){
                //有值
                sb.append(" and " + key + " like ? ");
                //？条件的值
                parms.add("%"+value+"%");
            }

        }

        //添加分页
        sb.append(" limit ? , ? ");
        //添加分页参数
        parms.add(start);
        parms.add(rows);


        //执行
        List<User> list = template.query(sb.toString(), new BeanPropertyRowMapper<User>(User.class),parms.toArray());

        return list;
    }


}
