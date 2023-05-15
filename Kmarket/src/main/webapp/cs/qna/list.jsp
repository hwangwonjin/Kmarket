<%@ page  contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="../_header.jsp"></jsp:include>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script>
	$(function() {
		$('#cs > .qna > .list > article > .btnWrite').click(function() {
			
			console.log("btnWrite");
			
			// 로그인이 풀렸을 경우
			let sessUid = $('#sessUid').val();
			
			console.log(sessUid);
			
			if(sessUid == ''){
				//alert('다시 로그인해주세요.');
				swal(
			        "Check!",
			        "다시 로그인해주세요.",
			        "warning"
			      )
				location.href = "/Kmarket/member/login.do";
				return false;
			}
			
			
			
		});
	});

</script>
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
                <section class="list">
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
                            <h1>${cateType1}</h1>
                            <h2>${cateType1} 관련 문의 내용입니다.</h2>
                            <input type="hidden" value="${sessUser.uid}" id="sessUid"/>
                        </nav>
                        <table>
                            <tbody>
                            	<c:forEach var="article" items="${articles}">
                            		<tr id="articleList">
                            			<td>
                            				<a href="/Kmarket/cs/qna/view.do?cate=qna&cateType1=${article.cateType1}&no=${article.no}">[${article.cateType2}] ${article.title}</a>
                            			</td>
                            			<td>
											<c:choose>
			                            		<c:when test="${article.replyContent ne null}">
			                            			<p style="color: #35A2EB;">답변완료</p>
			                            		</c:when>
			                            		<c:otherwise>
			                            			검토중
			                            		</c:otherwise>
			                            	</c:choose>
										</td>
                            			<td>${article.uid.substring(0,3)}**</td>
                                    	<td>
                                    		<fmt:parseDate value="${article.rdate}" var="time" pattern="yyyy-MM-dd HH:mm:ss"/>
		                            		<fmt:formatDate value="${time}" pattern="yy.MM.dd"/>
                                    	</td>
                            		</tr>
                            	</c:forEach>
                            </tbody>
                        </table>
                        <div class="page">
                        	<c:if test="${pageGroupStart gt 1}">
                    			<a href="/Kmarket/cs/qna/list.do?cate=qna&cateType1=${cateType1}&pg=${pageGroupStart-1}" class="prev">이전</a>
                        	</c:if>
                            <c:forEach var="i" begin="${pageGroupStart}" end="${pageGroupEnd}">
                            	<a href="/Kmarket/cs/qna/list.do?cate=qna&cateType1=${cateType1}&pg=${i}" class="num ${currentPage eq i? 'current':'off'}">${i}</a>
                            </c:forEach>
                            <c:if test="${pageGroupEnd lt lastPageNum}">
                            	<a href="/Kmarket/cs/qna/list.do?cate=qna&cateType1=${cateType1}&pg=${pageGroupEnd+1}" class="next">다음</a>
                            </c:if>
                        </div>
                        <a href="/Kmarket/cs/qna/write.do?cate=${cate}&cateType1=${cateType1}" class="btnWrite">문의하기</a>
                    </article>
                </section>
            </div>
        </section>
  <jsp:include page="../_footer.jsp"></jsp:include>