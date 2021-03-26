package cn.itcast.service;

import cn.itcast.domain.PageBean;
import cn.itcast.domain.User;

import java.util.List;
import java.util.Map;

/**
 *  用户管理的业务接口
 */
public interface UserService {


    /**
     * 查询所有用户信息
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
     * @param user
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
     * 批量删除
     * @param ids
     */
    void dels(String[] ids);

    /**
     * 分页条件查询
     * @param _currentPage
     * @param _rows
     * @param condition
     * @return
     */
    PageBean<User> findUserByPage(String _currentPage, String _rows, Map<String, String[]> condition);


}
