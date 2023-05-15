<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>케이마켓::대한민국 1등 온라인 쇼핑몰</title>
    <link rel="shortcut icon" type="image/x-icon" href="./img/favicon.ico" />
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css"/>
    <link rel="stylesheet" href="/Kmarket/css/common.css">
    <link rel="stylesheet" href="/Kmarket/css/style.css">
    <link rel="stylesheet" href="/Kmarket/product/css/product.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
    <script src="https://kit.fontawesome.com/20962f3e4b.js" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <script>
        $(document).ready(function () {
            $(".slider > ul").bxSlider({
                easing: "linear",
            });
            
            $('#scroll1').click(function(){
            	const offset = $(".hit").offset();
            	$('html, body').animate({scrollTop: offset.top}, 500);
            });
            $('#scroll2').click(function(){
            	const offset = $(".recommend").offset();
            	$('html, body').animate({scrollTop: offset.top}, 500);
            });
            $('#scroll3').click(function(){
            	const offset = $(".new").offset();
            	$('html, body').animate({scrollTop: offset.top}, 500);
            });
            $('#scroll5').click(function(){
            	const offset = $(".discount").offset();
            	$('html, body').animate({scrollTop: offset.top}, 500);
            });
            $('#top').click(function(){
            	$( 'html, body' ).animate( { scrollTop : 0 }, 500 );
            });
            
        });

        $(function () {
            var best = $("aside > .best");

            $(window).scroll(function () {
                var t = $(this).scrollTop();

                if (t > 620) {
                best.css({
                    position: "fixed",
                    top: "0",
                });
                } else {
                best.css({ position: "static" });
                }
            });
        });
        
        $(function() {
    		$('#wrapper > header > .menu > div > ul:nth-child(2) > li > .btnWrite').click(function() {
    			
    			console.log("btnWrite");
    			
    			// 로그인이 풀렸을 경우
    			let sessUid = $('#sessUid').val();
    			
    			console.log(sessUid);
    			
    			if(sessUid == ''){
    				alert('로그인이 필요한 항목입니다.');
    				/*
    				swal(
			        "Check!",
			        "로그인이 필요한 항목입니다.",
			        "warning"
			      )
			      */
    				location.href = "/Kmarket/member/login.do";
    				return false;
    			}
    			
    			
    			
    		});
    	});
        
       
    </script>
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
	                		<a href="/Kmarket/admin/">관리자</a>
	                	</c:if>
	                	<a href="/Kmarket/member/logout.do">로그아웃</a>
		                <a href="#">마이페이지</a>
		                <a href="/Kmarket/product/cart.do?uid=${sessUser.uid}">
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
                    <a href="/Kmarket/">
                        <img src="/Kmarket/img/header_logo.png" alt="헤더로고">
                    </a>
                    <form action="#">
                        <input type="text" name="search">
                        <button>
                            <i class="fa fa-search" aria-hidden="true"></i>
                        </button>
                    </form>
                </div>
            </div>
            <div class="menu">
            <input type="hidden" value="${sessUser.uid}" id="sessUid"/>
                <div>
                    <ul>
                        <li><a href="#" id="scroll1">히트상품</a></li> 
                        <li><a href="#" id="scroll2">추천상품</a></li>
                        <li><a href="#" id="scroll3">최신상품</a></li>
                        <li><a href="#" id="scroll5">할인상품</a></li>
                    </ul>
                    <ul>
                        <li><a href="/Kmarket/cs/notice/list.do?cate=notice">공지사항</a></li>
                        <li><a href="/Kmarket/cs/faq/list.do?cate=faq&cateType1=회원">자주묻는질문</a></li>
                        <li><a href="/Kmarket/cs/qna/write.do?cate=qna&cateType1=회원" class="btnWrite">문의하기</a></li>
                        <li><a href="/Kmarket/cs/index.do">고객센터</a></li>
                    </ul>
                </div>
            </div>
        </header>
        <main id="product">
            <aside>
                <ul class="category">
                    <li>
                        <i class="fa fa-bars" aria-hidden="true"></i>
                    </li>
                    <li>
                        <a href="#">
                            <i class="fas fa-shopping-bag" aria-hidden="true"></i>브랜드패션
                            <i class="fas fa-angle-right" aria-hidden="true">
                            </i>
                        </a>
                        <ol>
                        	<c:forEach var="cate10" items="${cate.cate10}">
                            <li>
                                <a href="/Kmarket/product/list.do?cate1=${cate10.cate1}&cate2=${cate10.cate2}">${cate10.c2Name}</a>
                            </li>
                            </c:forEach>
                        </ol>
                    </li>
					<li>
                        <a href="#">
                            <i class="fas fa-tshirt" aria-hidden="true"></i>패션·의류·뷰티
                            <i class="fas fa-angle-right" aria-hidden="true">
                            </i>
                        </a>
                        <ol>
                        	<c:forEach var="cate11" items="${cate.cate11}">
                            <li>
                                <a href="/Kmarket/product/list.do?cate1=${cate11.cate1}&cate2=${cate11.cate2}">${cate11.c2Name}</a>
                            </li>
                            </c:forEach>
                        </ol>
                    </li>
  					<li>
                        <a href="#">
                            <i class="fas fa-baby-carriage" aria-hidden="true"></i>유아동
                            <i class="fas fa-angle-right" aria-hidden="true">
                            </i>
                        </a>
                        <ol>
                        	<c:forEach var="cate12" items="${cate.cate12}">
                            <li>
                                <a href="/Kmarket/product/list.do?cate1=${cate12.cate1}&cate2=${cate12.cate2}">${cate12.c2Name}</a>
                            </li>
                            </c:forEach>
                        </ol>
                    </li>
					<li>
                        <a href="#">
                            <i class="fas fa-utensils" aria-hidden="true"></i>식품·생필품
                            <i class="fas fa-angle-right" aria-hidden="true">
                            </i>
                        </a>
                        <ol>
                        	<c:forEach var="cate13" items="${cate.cate13}">
                            <li>
                                <a href="/Kmarket/product/list.do?cate1=${cate13.cate1}&cate2=${cate13.cate2}">${cate13.c2Name}</a>
                            </li>
                            </c:forEach>
                        </ol>
                    </li>
					<li>
                        <a href="#">
                            <i class="fas fa-home" aria-hidden="true"></i>홈데코·문구·취미·반려
                            <i class="fas fa-angle-right" aria-hidden="true">
                            </i>
                        </a>
                        <ol>
                        	<c:forEach var="cate14" items="${cate.cate14}">
                            <li>
                                <a href="/Kmarket/product/list.do?cate1=${cate14.cate1}&cate2=${cate14.cate2}">${cate14.c2Name}</a>
                            </li>
                            </c:forEach>
                        </ol>
                    </li>
					<li>
                        <a href="#">
                            <i class="fas fa-tv" aria-hidden="true"></i>컴퓨터·디지털·가전
                            <i class="fas fa-angle-right" aria-hidden="true">
                            </i>
                        </a>
                        <ol>
                        	<c:forEach var="cate15" items="${cate.cate15}">
                            <li>
                                <a href="/Kmarket/product/list.do?cate1=${cate15.cate1}&cate2=${cate15.cate2}">${cate15.c2Name}</a>
                            </li>
                            </c:forEach>
                        </ol>
                    </li>
					<li>
                        <a href="#">
                            <i class="fas fa-running" aria-hidden="true"></i>스포츠·건강·렌탈
                            <i class="fas fa-angle-right" aria-hidden="true">
                            </i>
                        </a>
                        <ol>
                        	<c:forEach var="cate16" items="${cate.cate16}">
                            <li>
                                <a href="/Kmarket/product/list.do?cate1=${cate16.cate1}&cate2=${cate16.cate2}">${cate16.c2Name}</a>
                            </li>
                            </c:forEach>
                        </ol>
                    </li>
					<li>
                        <a href="#">
                            <i class="fas fa-car" aria-hidden="true"></i>자동차·공구
                            <i class="fas fa-angle-right" aria-hidden="true">
                            </i>
                        </a>
                        <ol>
                        	<c:forEach var="cate17" items="${cate.cate17}">
                            <li>
                                <a href="/Kmarket/product/list.do?cate1=${cate17.cate1}&cate2=${cate17.cate2}">${cate17.c2Name}</a>
                            </li>
                            </c:forEach>
                        </ol>
                    </li>
					<li>
                        <a href="#">
                            <i class="fas fa-book" aria-hidden="true"></i>여행·도서·티켓·e쿠폰
                            <i class="fas fa-angle-right" aria-hidden="true">
                            </i>
                        </a>
                        <ol>
                        	<c:forEach var="cate18" items="${cate.cate18}">
                            <li>
                                <a href="/Kmarket/product/list.do?cate1=${cate18.cate1}&cate2=${cate18.cate2}">${cate18.c2Name}</a>
                            </li>
                            </c:forEach>
                        </ol>
                    </li>
                </ul>