package com.yunbocheng.controller;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class TwoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int jiaozi_money = 30;
        int gaifan_money = 15;
        int miantiao_money = 10;
        int money = 0;
        int xiaofei =0;
        int balance = 0;
        String food,userName = null;
        Cookie cookieArray[] = null;
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        Cookie newCard = null;
        // 1.读取请求头参数信息,得到用户点餐食物类型
        food = req.getParameter("food");
        // 2. 读取请求中Cookie
        cookieArray = req.getCookies();
        // 3.刷卡消费
        for (Cookie card : cookieArray){
            String key = card.getName();
            String value = card.getName();
            if("userName".equals(key)){
                userName = value;
            }else if("money".equals(key)){
                /*因为在map集合汇中存放的都是String类型的数值
                * 这里需要转换为int类型，上边声明的money为int类型。
                * */

                try{
                    money = Integer.valueOf(value);
                    if("饺子".equals(food)){
                        if (jiaozi_money > money){
                            out.print("用户" + userName + "余额不足，请充值");
                        }else {
                            newCard = new Cookie("money", (money-jiaozi_money) + "");
                            xiaofei = jiaozi_money;
                            balance = money - jiaozi_money;
                            System.out.println(1);
                        }
                    } else if("面条".equals(food)){
                        if(miantiao_money > money){
                            out.print("用户" + userName + "余额不足，请充值");
                        }else {
                            newCard = new Cookie("money",(money - miantiao_money) + "");
                            xiaofei = miantiao_money;
                            balance = money - miantiao_money;
                        }
                    }else if("盖饭".equals(food)){
                        if (gaifan_money > money){
                            out.print("用户" + userName + "余额不足，请充值");
                        }else {
                            newCard = new Cookie("money",((money-gaifan_money) + ""));
                            xiaofei = gaifan_money;
                            balance = money - gaifan_money;
                        }
                    }
                    // 4. 将用户会员卡还给用户
                    resp.addCookie(newCard);

                } catch (Exception e){
                    e.fillInStackTrace();
                }
            }
        }
        // 5. 将消费记录写入到响应体中
        out.print("用户 " + userName + "本次消费 "  + xiaofei + "余额：" + balance);
    }
}
