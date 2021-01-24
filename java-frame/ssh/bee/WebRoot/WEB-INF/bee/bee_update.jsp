<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xv" uri="/struts-dojo-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'bee_update.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  <xv:head/>
  
  </head>
  
  <body>
    <s:form action="/bees/bee!modify.action"  method="post" enctype="multipart/form-data">
    	<s:textfield name="name">蜜蜂名：</s:textfield><br><br>
    	<s:radio name="sex" list="{'男','女','不祥'}" label="性别"></s:radio><br><br>
    	
    	<s:textfield name="hobbyList">蜜蜂：</s:textfield><br><br>
    	
    	<s:checkboxlist value="hobbyList" name="hobby"
    		 list="{'吃饭','睡觉','打豆豆','沐浴'}"
 			 label="爱好"></s:checkboxlist><br><br>

<%--	<s:select list="#{'财务部':'财务部','销售部':'销售部','人事部':'人事部'}" name="name" label="姓名"></s:select>--%>
   		<xv:datetimepicker name="birthday" displayFormat="yyyy-MM-dd">日期：</xv:datetimepicker>
    	
    	<s:select name="kindOfBee.kid" list="list" listKey="kid" listValue="kind" 
    		headerKey="aa" headerValue="-请选择-"></s:select><br><br>
    	<s:file name="filepath">头像：</s:file><br><br>
    	<img alt="小白侠" src="<%=path%>/upload/<s:property value="filepath"/>">
    	<s:hidden name="cid"></s:hidden>
    	<s:submit value="保存"></s:submit>
    </s:form>
  </body>
</html>