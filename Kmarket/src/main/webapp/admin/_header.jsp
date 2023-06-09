<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>케이마켓::관리자</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
    <script src="https://kit.fontawesome.com/20962f3e4b.js" crossorigin="anonymous"></script>    
    <script src="/Kmarket/admin/js/gnb.js"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="/Kmarket/admin/css/admin.css">
</head>
<script>
	$(function(){
		
		let uid = $('input[name=uid]').val();
		let type = $('input[name=type]').val();
		let level = $('input[name=level]').val();
		
		if(uid == ''){
			alert('판매자 혹은 관리자만 이용할 수 있습니다.');
			location.href = "/Kmarket/member/login.do";
			return;
		}else if(type != 2){
			alert('판매자 혹은 관리자만 이용할 수 있습니다.');
			location.href = "/Kmarket/";
			return;
		}
	});
</script>
<body>
    <div id="wrapper">
        <header>
            <div>
                <a href="/Kmarket/admin/index.do" class="logo">
                    <img src="/Kmarket/admin/img/admin_logo.png" alt="admin_logo">
                </a>
                <input type="hidden" name="uid" value="${sessUser.uid}">
                <input type="hidden" name="type" value="${sessUser.type}">
                <input type="hidden" name="level" value="${sessUser.level}">
                <p>
                <c:choose>
                	<c:when test="${sessUser eq null}">
	                	<a href="/Kmarket/admin/login.do">로그인 |</a>
	                	<a href="/Kmarket/member/join.do">  회원가입</a>
                	</c:when>
                	<c:otherwise>
                		<a><span>${sessUser.manager}</span>님, 반갑습니다  |</a>
                		<a href="/Kmarket/">HOME |</a>
                		<a href="/Kmarket/admin/logout.do">  로그아웃  |</a>
                		<a href="#">고객센터</a>
                	</c:otherwise>
                </c:choose>
                </p>
            </div>
        </header>
        <main>
            <aside>
                 <!-- Global Navigation Bar -->
                <ul id="gnb">
                    <li>
                        <a href="#"><i class="fa fa-cogs" aria-hidden="true"></i>환경설정</a>
                        <ol>
                            <li><a href="#">기본환경설정</a></li>
                            <li><a href="#">배너관리</a></li>
                        </ol>
                    </li>
                    <li>
                        <a href="#"><i class="fas fa-store" aria-hidden="true"></i>상점관리</a>
                        <ol>
                            <li><a href="#">판매자현황</a></li>
                            <li><a href="#">재고관리</a></li>
                        </ol>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-users" aria-hidden="true"></i>회원관리</a>
                        <ol>
                            <li><a href="#">회원현황</a></li>
                            <li><a href="#">포인트관리</a></li>
                            <li><a href="#">비회원관리</a></li>
                            <li><a href="#">접속자집계</a></li>
                        </ol>
                    </li>
                    <li>
                        <a href="#"><i class="fas fa-box-open" aria-hidden="true"></i>상품관리</a>
                        <ol>
                            <li><a href="/Kmarket/admin/product/list.do?uid=${sessUser.uid}&level=${sessUser.level}">상품현황</a></li>
                            <li><a href="/Kmarket//admin/register.do">상품등록</a></li>
                            <li><a href="#">재고관리</a></li>
                        </ol>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-credit-card" aria-hidden="true"></i>주문관리</a>
                        <ol>
                            <li><a href="#">주문현황</a></li>
                            <li><a href="#">매출현황</a></li>
                            <li><a href="#">결제관리</a></li>
                            <li><a href="#">배송관리</a></li>
                        </ol>
                    </li>
                    <c:if test="${sessUser.level eq 7}">
                    <li>
                        <a href="#"><i class="fa fa-pencil-square-o" aria-hidden="true"></i>고객센터</a>
                        <ol>
                            <li><a href="/Kmarket/admin/cs/notice/list.do?cate=notice">공지사항</a></li>
                            <li><a href="/Kmarket/admin/cs/faq/list.do?cate=faq">자주묻는 질문</a></li>
                            <li><a href="/Kmarket/admin/cs/qna/list.do?cate=qna">문의하기</a></li>
                        </ol>
                    </li>
                    </c:if>
                </ul>
            </aside>