# cookie接口

1. 介绍：

    1. cookie来自于Servlet规范中一个工具类，存在于Tomcat提供servlet-api.jar中

    2. 如果两个Servlet来自于同一个网站，并且为同一个浏览器/用户提供服务，此时

       借助cookie对象进行数据共享。

    3. Cookie存放当前用户的私人数据，在共享数据过程中提高服务质量

    4. 在现实生活场景中，cookie相当于用户在服务端得到【会员卡】

2. 工作原理：![](https://gitee.com/YunboCheng/imageBad/raw/master/image/20210720165420.png)

   注意：cookie只存在于请求包的请求头中，与响应包的响应头中。

   ​

   <img src="https://gitee.com/YunboCheng/imageBad/raw/master/image/20210720164322.png" style="zoom: 50%;" />

3. 实现命令：同一个网站 OneServlet 与 TwoServlet 借助于cookie实现数据共享

   <img src="https://gitee.com/YunboCheng/imageBad/raw/master/image/20210720170919.png" style="zoom:80%;" />

   <img src="https://gitee.com/YunboCheng/imageBad/raw/master/image/20210720171010.png" style="zoom:80%;" />

<img src="https://gitee.com/YunboCheng/imageBad/raw/master/image/20210720171302.png" style="zoom:80%;" />

# Cookie销毁时机

1. 在默认情况下，Cookie对象存放在浏览器的缓存中，

   因此只要浏览器关闭，COokie对象就被销毁。

2. 在手动设置情况下，可以要求浏览器讲接收的Cookie

   存放在客户端计算机硬盘上，同时需要指定Cookie在

   硬盘上存活时间。在存活时间范围内，关闭浏览器关闭

   客户端计算机，关闭服务，都不会导致Cookie被销毁。

   在存活时间到达时，Cookie自动从硬盘上被删除

   cookie.setMaxAge(60);   // cookie在硬盘上存活一分钟。

3. 注意：设置上边的存活时间很重要，原理：到达规定的这个时间，Cookie会自动注销，用户信息消失。比如会员卡什么时候到期，到期之后就不可以在进行使用了，会员卡过期，其实就是里边的Cookie自动销毁了，此时查询不到用户的信息。失效的卡。