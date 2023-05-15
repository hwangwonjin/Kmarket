<%@ page  contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../_header.jsp"></jsp:include>
        <section id="cs">
            <div class="qna">
                <nav>
                    <div>
                        <p>
                            홈
                            <span>></span>
                            문의하기
                        </p>
                    </div>
                </nav>
                <section class="view">
                    <aside>
                        <h2>문의하기</h2>
                        <ul>
                            <li class="${cateType1 == '회원' ? 'on' : 'off'}">
                                <a href="/Kmarket/cs/qna/list.do?cate=qna&cateType1=회원">회원</a>
                            </li>
                            <li class="${cateType1 == '쿠폰/이벤트' ? 'on' : 'off'}">
                                <a href="/Kmarket/cs/qna/list.do?cate=qna&cateType1=쿠폰/이벤트">쿠폰/이벤트</a>
                            </li>
                            <li class="${cateType1 == '주문/결제' ? 'on' : 'off'}">
                                <a href="/Kmarket/cs/qna/list.do?cate=qna&cateType1=주문/결제">주문/결제</a>
                            </li>
                            <li class="${cateType1 == '배송' ? 'on' : 'off'}">
                                <a href="/Kmarket/cs/qna/list.do?cate=qna&cateType1=배송">배송</a>
                            </li>
                            <li class="${cateType1 == '취소/반품/교환' ? 'on' : 'off'}"> 
                                <a href="/Kmarket/cs/qna/list.do?cate=qna&cateType1=취소/반품/교환">취소/반품/교환</a>
                            </li>
                            <li class="${cateType1 == '여행/숙박/항공' ? 'on' : 'off'}">
                                <a href="/Kmarket/cs/qna/list.do?cate=qna&cateType1=여행/숙박/항공">여행/숙박/항공</a>
                            </li>
                            <li class="${cateType1 == '안전거래' ? 'on' : 'off'}">
                                <a href="/Kmarket/cs/qna/list.do?cate=qna&cateType1=안전거래">안전거래</a>
                            </li>
                        </ul>
                    </aside>
                    <article>
                        <nav>
                            <h2 class="title">[${vo.cateType2}] ${vo.title}</h2>
                            <p>
                                <span>${vo.uid}</span>
                                <span>${vo.rdate.substring(0,10)}</span>
                            </p>
                        </nav>
                        <div class="content">
                        	<p>
                        		${vo.content}
                        	</p>
                        </div>
                        <c:choose>
                        	<c:when test="${vo.replyContent ne null}">
	                        	<div class="replyContent">
		                        	<h2>
		                        		[답변] ${vo.title}
		                        	</h2>
		                        	<p>
		                        		${vo.replyContent}
		                        	</p>
	                       		</div>
	                        </c:when>
	                        <c:otherwise>
	                        </c:otherwise>
                        </c:choose>
                        <a href="/Kmarket/cs/qna/list.do?cate=qna&cateType1=${vo.cateType1}" class="btnList">목록보기</a>
                    </article>
                </section>
            </div>
        </section>
  <jsp:include page="../_footer.jsp"></jsp:include>