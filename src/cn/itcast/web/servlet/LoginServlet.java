package cn.itcast.web.servlet;

import cn.itcast.domain.User;
import cn.itcast.service.UserService;
import cn.itcast.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1、设置编码
        request.setCharacterEncoding("utf-8");

        //2、获取请求参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String checkCode = request.getParameter("CheckCode");


        //System.out.println(username);
        //System.out.println(password);
        System.out.println(checkCode);


        //3、先获取生成的验证码
        HttpSession session = request.getSession();
        String CheckCode_session = (String)session.getAttribute("CheckCode_Session");

        System.out.println(CheckCode_session);
        //确保验证码一次性
        session.removeAttribute("CheckCode_Session");

        //4、判断验证码是否正确
        if(CheckCode_session.equalsIgnoreCase(checkCode)){
            //验证码正确
            //判断用户名、密码是否正确

            //调用service查询
            UserService userService = new UserServiceImpl();
            User loginUser = userService.login(username,password);
            //判断是否登陆成功
            if(loginUser!=null){
                //登录成功
                //将用户信息存入cession
                request.getSession().setAttribute("user",loginUser);

                //System.out.println(request.getAttribute("user"));

                //跳转到Index.jsp
                response.sendRedirect(request.getContextPath()+"/index.jsp");
                //request.getRequestDispatcher("/index.jsp").forward(request,response);


            }else {
                //登录失败
                //提示信息
                request.setAttribute("login_error","用户名或密码错误");
                //跳转到登陆界面
                request.getRequestDispatcher("/login.jsp").forward(request,response);

            }


        }else {
            //验证码不正确
            request.setAttribute("login_error","验证码错误");
            //跳转到登陆界面
            request.getRequestDispatcher("/login.jsp").forward(request,response);


        }





    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.doPost(request,response);

    }
}
