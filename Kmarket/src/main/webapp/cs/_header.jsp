<%@ page  contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>케이마켓 고객센터</title>
    <link rel="shortcut icon" type="image/x-icon" href="../img/favicon.ico">
    <link rel="stylesheet" href="/Kmarket/cs/css/csstyle.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<body>
    <div id="wrapper">
        <header>
            <div class="top">
                <div>
                <c:choose>
                	<c:when test="${not empty sessUser}">
                		<c:if test="${sessUser.type eq '1'}">
	                		<a><span>${sessUser.name}</span> 님, 반갑습니다</a>
	                	</c:if>
	                	<c:if test="${sessUser.type eq '2'}">
	                		<a><span>${sessUser.manager}</span> 님, 반갑습니다</a>
	                	</c:if>
	                	<a href="/Kmarket/">HOME</a>
	                	<a href="/Kmarket/member/logout.do">로그아웃</a>
		                <a href="#">마이페이지</a>
		                <a href="/Kmarket/product/cart.do">
		                	<i class="fa fa-shopping-cart" aria-hidden="true"></i>장바구니
		                </a>
                	</c:when>
                	<c:otherwise>
                		<a href="/Kmarket/member/login.do">로그인</a>
	                    <a href="/Kmarket/member/join.do">회원가입</a>
	                    <a href="#">마이페이지</a>
	                    <a href="/Kmarket/product/cart.do">
	                        <i class="fa fa-shopping-cart" aria-hidden="true"></i>
	                        장바구니
	                    </a>
                	</c:otherwise>
                </c:choose>
                </div>
            </div>
            <div class="logo">
                <div>
                	<a href="/Kmarket/"><img src="/Kmarket/cs/img/logo.png" alt="로고"></a>
                    <a href="/Kmarket/cs/index.do">
                        고객센터
                    </a>
                </div>
            </div>
        </header>