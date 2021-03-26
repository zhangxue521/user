package cn.itcast.service.impl;

import cn.itcast.dao.UserDao;
import cn.itcast.dao.impl.UserDaoImpl;
import cn.itcast.domain.PageBean;
import cn.itcast.domain.User;
import cn.itcast.service.UserService;

import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {

    /**
     * 调用DAO完成查询
     * @return
     */
    private UserDao userDao = new UserDaoImpl();


    /**
     * 查询所有用户信息
     * @return
     */
    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */

    @Override
    public User login(String username, String password) {
        return userDao.login(username,password);
    }

    /**
     * 添加用户信息
     * @param user
     */

    @Override
    public void add(User user) {
        userDao.add(user);
    }


    /**
     * 删除某条记录
     * @param id
     */
    @Override
    public void delete(int id) {
        userDao.delete(id);
    }

    /**
     * 数据回显，按id查询
     * @param id
     * @return
     */

    @Override
    public User findById(int id) {
        return userDao.findById(id);
    }

    /**
     * 修改用户信息
     * @param user
     */
    @Override
    public void update(User user) {
        userDao.update(user);
    }

    /**
     * 批量删除
     * @param ids
     */
    @Override
    public void dels(String[] ids) {
        if(ids!=null && ids.length>0){
            //1、遍历数组
            for (String id : ids) {
                //2、调用dao删除
                userDao.delete(Integer.parseInt(id));

            }
        }

    }

    /**
     * 分页
     * @param _currentPage
     * @param _rows
     * @param condition
     * @return
     */
    @Override
    public PageBean<User> findUserByPage(String _currentPage, String _rows, Map<String, String[]> condition) {

        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);

        if(currentPage<=0){
            currentPage=1;
        }


        //1、创建空的PageBeam对象
        PageBean<User> pb = new PageBean<User>();


        //3、调用dao查询总记录数
        int totalCount = userDao.findTotalCount(condition);
        pb.setTotalCount(totalCount);

        //4、调用dao查询List集合
        //计算开始记录索引
        int start = (currentPage - 1)* rows;
        List<User> list = userDao.findByPage(start,rows,condition);
        pb.setList(list);

        //5、计算总页码
        int totalPage = (totalCount % rows) == 0 ? (totalCount/rows) : (totalCount/rows) + 1 ;
        pb.setTotalPage(totalPage);

        if(currentPage>totalPage){
            currentPage = totalPage;
        }

        //2、设置参数
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);

        return pb;

    }

}
