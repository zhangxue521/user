package cn.itcast.web.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/CheckCodeServlet")
public class CheckCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



        int width = 80;
        int height = 40;

        //1、创建一对象，在内存中图片（验证码图片对象)

        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

        //2、美化图片

        //2.1  填充背景色
        Graphics g = image.getGraphics();//设置画笔对象
        g.setColor(Color.GRAY);//设置画笔颜色
        g.fillRect(0,0,width,height);

        //2.2 设置边框
        g.setColor(Color.YELLOW);
        //设置字体
        g.setFont(new Font("黑体",Font.BOLD,18));
        g.drawRect(0,0,width,height);


        //2.3 写验证码
        String str = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
        //生成随机字符
        Random random = new Random();

        StringBuilder sb = new StringBuilder();

        for(int i = 1;i<=4;i++){
            int index = random.nextInt(str.length());
            char c = str.charAt(index);

            sb.append(c);

            //写验证码
            g.drawString(c+"",width/5*i,height/2);


        }
        String CheckCode_Session = sb.toString();

        //将随机获取的验证码存入session
        HttpSession session = request.getSession();
        session.setAttribute("CheckCode_Session",CheckCode_Session);



        //3、将图片输出到页面展示

        ImageIO.write(image,"jpg", response.getOutputStream());

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.doPost(request,response);

    }
}
