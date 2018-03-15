<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ftt" tagdir="/WEB-INF/tags/"%>
<html>
<head>
<meta charset="utf-8">
<title>Error</title>
<style type="text/css">
html,body{overflow:hidden;width:100%;height:100%;color:#555;font-family:tahoma;font-size:15px;text-transform:uppercase;background:#dcdfdf;}
#wrap404{position:absolute;left:50%;top:50%;width:414px;height:343px;margin:-171px 0 0 -207px}
#wrap404 .err_number{overflow:hidden;height:142px;background:#88d4e3}
#wrap404 .err_number span{display:inline-block;width:127px;height:144px;color:#fff;font-size:120px;font-weight:bold;text-align:center;line-height:134px;background:#88d4e3}
#wrap404 .err_number .text2{position:relative;top:-2px;height:146px;border-left:10px solid #dbdddd;border-right:10px solid #dbdddd;
transform: skew(12deg, 1deg);
-webkit-transform: skew(12deg, 1deg);
-moz-transform: skew(12deg, 1deg);
-o-transform: skew(12deg, 1deg);
-ms-transform: skew(12deg, 1deg);
}
#wrap404 .err_number .text2 span{display:inline-block;width:120px;height:100%;background:none;
transform: skew(-12deg, 0deg);
-webkit-transform: skew(-12deg, 0deg);
-moz-transform: skew(-12deg, 0deg);
-o-transform: skew(-12deg, 0deg);
-ms-transform: skew(-12deg, 0deg) 
}
#wrap404 .tit{font-size:60px;font-weight:bold;text-align:center;font-weight:bold;line-height:60px;text-shadow:1px 1px #fff}
#wrap404 .tit .msg{display:block;color:#9699a6;font-size:30px;font-weight:normal;letter-spacing:1px;}
#wrap404 .tit .smsg{display:block;color:#9699a6;font-size:10px;font-weight:normal;letter-spacing:1px;}
#wrap404 .tit .ani{
  position:relative;
  top:0;
  animation-name:titAni;
  animation-duration: 1s;
  animation-iteration-count:infinite;
}
@keyframes titAni{
  50%{top:-5px}
  100%{top:0}
}
</style>
</head>

<body>
  <div id="wrap404">
    <div class="face">
    </div>
    <h1 class="tit">
      Error<span class="ani">!</span>
      <span class="msg">${forumException.getErrorMessage()} ${forumException.getAddMessage() ? "("+forumException.getAddMessage()+")" : ''}</span>
      <span class="smsg">${appConfig.getEnvironment().equals("Dev") ? forumException.getErrorCode() : ""}</span>
    </h1>
  </div>
</body>
</html>