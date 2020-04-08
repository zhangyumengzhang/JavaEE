<%--
  Created by IntelliJ IDEA.
  User: zym
  Date: 2020/3/17
  Time: 8:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Homework System</title>
</head>

<style>

    body{

        background: #353f42;

    }



    *{

        padding: 0;

        margin: 0;

    }



    .main {

        margin: 0 auto;

        padding-left: 25px;

        padding-right: 25px;

        padding-top: 15px;

        width: 350px;

        height: 350px;

        background: #FFFFFF;

        /*以下css用于让登录表单垂直居中在界面,可删除*/

        position: absolute;

        top: 50%;

        left: 50%;

        margin-top: -175px;

        margin-left: -175px;

    }



    .title {

        width: 100%;

        height: 40px;

        line-height: 40px;

    }



    .title span {

        font-size: 18px;

        color: #353f42;

    }



    .title-msg {

        width: 100%;

        height: 64px;

        line-height: 64px;

    }



    .title:hover{

        cursor: default	;

    }



    .title-msg:hover{

        cursor: default	;

    }



    .title-msg span {

        font-size: 12px;

        color: #707472;

    }



    .input-content {

        width: 100%;

        height: 120px;

    }



    .input-content input {

        width: 330px;

        height: 40px;

        border: 1px solid #dad9d6;

        background: #ffffff;

        padding-left: 10px;

        padding-right: 10px;

    }



    .enter-btn {

        width: 350px;

        height: 40px;

        color: #fff;

        background: #0bc5de;

        line-height: 40px;

        text-align: center;

        border: 0px;

    }



    .foor{

        width: 100%;

        height: auto;

        color: #9b9c98;

        font-size: 12px;

        margin-top: 20px;

    }



    .enter-btn:hover {

        cursor:pointer;

        background: #1db5c9;

    }



    .foor div:hover {

        cursor:pointer;

        color: #484847;

        font-weight: 600;

    }



    .left{

        float: left;

    }

    .right{

        float: right;

    }



</style>

<body>

<div class="main">

    <div class="title">

        <span>密码登录</span>

    </div>

    <div class="title-msg">

        <span>请输入用户ID和密码</span>

    </div>

    <form class="login-form" action="login">

        <!--输入框-->

        <div class="input-content">

            <!--autoFocus-->

            <div>

                <input type="text" autocomplete="off"

                       placeholder="用户ID" name="id" required/>

            </div>

            <div style="margin-top: 10px">

                <input type="password"

                       autocomplete="off" placeholder="登录密码" name="password" required maxlength="32"/>

            </div>

        </div>

        <div style="margin-top: 2px">
            <tr>
                <td><input type=radio name=type value=teacher>教师
                    <input type=radio name=type value=student >学生 </td>
            </tr>
        </div>
        <br/>
        <!--登入按钮-->

        <div style="text-align: center">

            <button id="login_btn" class="enter-btn">登录</button>

        </div>

        <div class="foor">

            <div class="right"><span onclick="window.location.href='register.jsp'">注册账户</span></div>

        </div>

    </form>

</div>

</body>

</html>

