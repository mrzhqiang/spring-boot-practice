<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>SSE Demo</title>
</head>
<body>
<div id="msgFromPush"></div>
<script type="text/javascript" src="<c:url value="assets/js/jquery-3.4.1.min.js"/>"></script>
<script type="text/javascript">
  if (!!window.EventSource) {
    let source = new EventSource('push');
    s = '';
    source.addEventListener('message', function (e) {
      s += e.data + `<br/>`
      $("#msgFromPush").html(s);
    });

    source.addEventListener('open', function (e) {
      console.log('连接打开.');
    });

    source.addEventListener('error', function (e) {
      if (e.readyState === EventSource.CLOSED) {
        console.log('连接关闭');
      } else {
        console.error(e.readyState);
      }
    }, false);
  } else {
    console.log('你的浏览器不支持 SSE');
  }
</script>
</body>
</html>