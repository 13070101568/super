<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>����freemarker</title>
	
	<#assign path =����è��>
	<style type="text/css">
	div{
		background: url("../img/xiaobaixia.png") no-repeat;
	}
	.href{
		background:orange;
	}
	</style>
</head>
<body>
	����freemarker:
	<#if num0==19>19�꣬�����ˣ�</#if>
	
	<#-- ���� -->
	${path}
	<#if num0!=19>��û���ꡭ��</#if>
	<#if user=="С����">��ӭ��</#if><#-- �ַ� -->
	<a href="www.baidu.com" class="href">��ת</a>
	<br><br>
	<div></div>
</body>
</html>