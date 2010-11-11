<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Payment Canceled</title>
</head>
<body>

<a href="/">Home</a>
<br></br>
<h3>uh ho - looks like you've canceled the payment - unfortunately (<%= request.getParameter("businessName") %>) can't Sponsor User Goals  unless you pay!</h3>

</body>
</html>