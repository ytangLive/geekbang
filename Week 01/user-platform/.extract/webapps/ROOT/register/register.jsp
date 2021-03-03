<head>
    <jsp:directive.include
            file="/WEB-INF/jsp/prelude/include-head-meta.jspf" />
    <title>注册</title>
</head>

<style type="text/css">
    * { margin: 0; padding: 0; list-style-type: none; }

    a, img { border: 0; }

    body { font: 16/180% Arial, Helvetica, sans-serif, "微软雅黑", "黑体"; background: #eee; text-align: center; }

    table { border-collapse: collapse; border-spacing: 0; }

    td, th { padding: 0; }

    .clear { clear: both; }

    .clear:before, .clear:after { content: ""; display: table; }

    .clear:after { clear: both; }

    .form-div { background-color: rgba(255,255,255,0.27); border-radius: 10px; border: 1px solid #aaa; width: 424px; margin: 0 auto; padding: 30px 0 20px 0px; font-size: 12px; box-shadow: inset 0px 0px 10px rgba(255,255,255,0.5), 0px 0px 15px rgba(75,75,75,0.3); text-align: left; }

    .form-div input[type="text"], .form-div input[type="password"], .form-div input[type="email"] { width: 268px; margin: 10px; line-height: 20px; font-size: 16px; }

    .form-div input[type="checkbox"] { margin: 20px 0 20px 10px; }

    .form-div input[type="button"], .form-div input[type="submit"] { margin: 10px 20px 0 0; }

    .form-div table { margin: 0 auto; text-align: right; color: rgba(64,64,64,1.00); }

    .form-div table img { vertical-align: middle; margin: 0 0 5px 0; }

    .footer { color: rgba(64,64,64,1.00); font-size: 12px; margin-top: 30px; }

    .form-div .buttons { float: right; }

    input[type="text"], input[type="password"], input[type="email"] { border-radius: 8px; box-shadow: inset 0 2px 5px #eee; padding: 10px; border: 1px solid #D4D4D4; color: #333333; margin-top: 5px; }

    input[type="text"]:focus, input[type="password"]:focus, input[type="email"]:focus { border: 1px solid #50afeb; outline: none; }

    input[type="button"], input[type="submit"] { padding: 7px 15px; background-color: #3c6db0; text-align: center; border-radius: 5px; overflow: hidden; min-width: 80px; border: none; color: #FFF; box-shadow: 1px 1px 1px rgba(75,75,75,0.3); }

    input[type="button"]:hover, input[type="submit"]:hover { background-color: #5a88c8; }

    input[type="button"]:active, input[type="submit"]:active { background-color: #5a88c8; }


</style>

<body>
<div class="form-div">
    <form id="reg-form" action="action" method="post">
        <table>
            <tr>
                <td>用户名</td>
                <td><input name="uid" type="text" id="uid" easyform="length:4-16;char-normal;real-time;" message="用户名必须为4—16位的英文字母或数字" easytip="disappear:lost-focus;theme:blue;" ajax-message="用户名已存在!"></td>
            </tr>
            <tr>
                <td>密码</td>
                <td><input name="psw" type="password" id="psw" easyform="length:6-16;" message="密码必须为6—16位" easytip="disappear:lost-focus;theme:blue;"></td>
            </tr>
            <tr>
                <td>email</td>
                <td><input name="email" type="text" id="email" easyform="email;real-time;" message="Email格式要正确" easytip="disappear:lost-focus;theme:blue;" ajax-message="这个Email地址已经被注册过，请换一个吧!"></td>
            </tr>
            <tr>
                <td>手机号</td>
                <td><input name="phone" type="text" id="phone" easyform="length:0-11;" message="手机号不能为空" easytip="disappear:lost-focus;theme:blue;" ></td>
            </tr>
        </table>
        <div class="buttons">
            <input value="注 册" type="submit" style="margin-right:20px; margin-top:20px;">
        </div>
        <br class="clear">
    </form>
</div>
</body>