package cn.itcast.web.servlet;

import cn.itcast.domain.User;
import cn.itcast.service.UserService;
import cn.itcast.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AddUserServlet")
public class AddUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1、设置编码
        request.setCharacterEncoding("utf-8");

        //2、获取请求参数
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        //System.out.println(gender);
        String sage = request.getParameter("age");
        int age = Integer.parseInt(sage);
        String address = request.getParameter("address");
        String email = request.getParameter("email");

        //3、封装User对象
        User user = new User();
        user.setName(name);
        user.setGender(gender);
        user.setAge(age);
        user.setAddress(address);
        user.setEmail(email);

        //4、调用service
        UserService userService = new UserServiceImpl();
        userService.add(user);


        //5、跳转到UserListServlet
        response.sendRedirect(request.getContextPath()+"/FindUserByPageServlet");

        //request.getRequestDispatcher("/UserListServlet").forward(request,response);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.doPost(request,response);

    }
}
