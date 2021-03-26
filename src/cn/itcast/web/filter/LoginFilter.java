package cn.itcast.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 登陆验证的过滤器
 */
@WebFilter("/*")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        //强制转换
        HttpServletRequest request = (HttpServletRequest) req;

        //1、获取资源请求路径
        String uri = request.getRequestURI();
        //2、判断是否包含登陆相关资源路径  排除一些css、js、验证码之类的
        if(uri.contains("/login.jsp") || uri.contains("/LoginServlet") || uri.contains("/css/") || uri.contains("/js/") || uri.contains("/bootstrap/") || uri.contains("/CheckCodeServlet")){
            //包含,用户就是想登录，放行
            chain.doFilter(req, resp);
        }else {
            //不包含，需验证用户是否登录
            //3、从session中获取User
            Object user = request.getSession().getAttribute("user");
            //System.out.println(user);

            if(user!=null){
                //登陆成功了，放行
                chain.doFilter(req, resp);
            }else {
                //没有登录，
                request.getSession().setAttribute("login_error","您尚未登陆,请先登录");
                //跳转登录页面
                request.getRequestDispatcher("/login.jsp").forward(request,resp);
            }

        }


    }

    public void init(FilterConfig config) throws ServletException {

    }

}
