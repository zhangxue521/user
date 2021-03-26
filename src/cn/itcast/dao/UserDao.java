package cn.itcast.dao;

import cn.itcast.domain.User;

import java.util.List;
import java.util.Map;

/**
 * 用户操作的DAO
 */
public interface UserDao {

    /**
     * 查询所有用户
     * @return
     */
     List<User> findAll();

    /**
     *
     * @param username
     * @param password
     * @return
     */
     User login(String username,String password);

    /**
     * 添加用户
     * @para user
     */
     void add(User user);

    /**
     * 根据id删除
     * @param id
     */
    void delete(int id);

    /**
     * 数据回显
     * @param id
     * @return
     */
    User findById(int id);

    /**
     * 修改用户信息
     * @param user
     */
    void update(User user);

    /**
     * 查询总记录数
     * @return
     * @param condition
     */
    int findTotalCount(Map<String, String[]> condition);

    /**
     * 分页查询每页记录
     * @param start
     * @param rows
     * @param condition
     * @return
     */
    List<User> findByPage(int start, int rows, Map<String, String[]> condition);
}
