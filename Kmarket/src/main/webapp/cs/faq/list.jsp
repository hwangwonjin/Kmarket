<%@ page  contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../_header.jsp"></jsp:include>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<style>
   #cs > .faq > .list > article > div > ul > li:nth-child(n+4) {
    	display: none;
	}   
</style>
<script>
   $(function(){
	   
       $('.more').click(function(e){
           e.preventDefault();
           
           let item = $(this).parent().find('> li:nth-child(n+4)');
           let isVisible = item.is(':visible');
           
           console.log('isVisible : ' + isVisible);
           
           if(isVisible && item.css('display') == 'none'){
        	   item.slideDown(300);
        	   $(this).find('> a').text('간단히보기');
           }else if(isVisible && item.css('display') == 'list-item'){
        	   item.slideUp(300);
        	   $(this).find('> a').text('더보기');
   			}
           
       });
       
		
   });
</script>
            <section id="cs">
                <div class="faq">
                    <nav>
                        <div>
                            <p>
                                홈
                                <span>></span>
                                자주묻는 질문
                            </p>
                        </div>
                    </nav>
                    <section class="list">
                        <aside>
                            <h2>자주묻는 질문</h2>
                            <ul>
	                            <li class="${cateType1 == '회원' ? 'on' : 'off'}">
	                                <a href="/Kmarket/cs/faq/list.do?cate=faq&cateType1=회원">회원</a>
	                            </li>
	                            <li class="${cateType1 == '쿠폰/이벤트' ? 'on' : 'off'}">
	                                <a href="/Kmarket/cs/faq/list.do?cate=faq&cateType1=쿠폰/이벤트">쿠폰/이벤트</a>
	                            </li>
	                            <li class="${cateType1 == '주문/결제' ? 'on' : 'off'}">
	                                <a href="/Kmarket/cs/faq/list.do?cate=faq&cateType1=주문/결제">주문/결제</a>
	                            </li>
	                            <li class="${cateType1 == '배송' ? 'on' : 'off'}">
	                                <a href="/Kmarket/cs/faq/list.do?cate=faq&cateType1=배송">배송</a>
	                            </li>
	                            <li class="${cateType1 == '취소/반품/교환' ? 'on' : 'off'}"> 
	                                <a href="/Kmarket/cs/faq/list.do?cate=faq&cateType1=취소/반품/교환">취소/반품/교환</a>
	                            </li>
	                            <li class="${cateType1 == '여행/숙박/항공' ? 'on' : 'off'}">
	                                <a href="/Kmarket/cs/faq/list.do?cate=faq&cateType1=여행/숙박/항공">여행/숙박/항공</a>
	                            </li>
	                            <li class="${cateType1 == '안전거래' ? 'on' : 'off'}">
	                                <a href="/Kmarket/cs/faq/list.do?cate=faq&cateType1=안전거래">안전거래</a>
	                            </li>
	                        </ul>
                        </aside>
                        
                        <article>
                        	<nav>
                                <h1>${cateType1}</h1>
                                <h2>가장 자주 묻는 질문입니다.</h2>
                            </nav>
	                         <c:set var="i" value="0"/>
                             <c:forEach var="article" items="${articles}" begin="${i}" step="${i+10}" varStatus="status" >
	                            <div>
	                                <h3>${article.cateType2}</h3>
	                                <c:choose>
	                                	<c:when test="${status.index eq 0}">
                                			<ul>
                                				<c:forEach var="article" items="${articles}" begin="0" end="9">
                                				<li>
                                					<a href="/Kmarket/cs/faq/view.do?cate=faq&cateType1=${article.cateType1}&no=${article.no}"><span>Q.</span>
                                						${article.title}
                                					</a>
                                				</li>
                                				</c:forEach>
                                				<li class="more">
                                					<a href="#">더보기</a>
                                				</li>
                                			</ul>
	                                	</c:when>
	                                	<c:when test="${status.index eq 10}">
                                			<ul>
                                				<c:forEach var="article" items="${articles}" begin="10" end="19">
                                				<li>
                                					<a href="/Kmarket/cs/faq/view.do?cate=faq&cateType1=${article.cateType1}&no=${article.no}"><span>Q.</span>
                                						${article.title}
                                					</a>
                                				</li>
                                				</c:forEach>
                                				<li class="more">
                                					<a href="#" >더보기</a>
                                				</li>
                                			</ul>
	                                	</c:when>
	                                	<c:when test="${status.index eq 20}">
                                			<ul>
                                				<c:forEach var="article" items="${articles}" begin="20" end="29">
                                				<li>
                                					<a href="/Kmarket/cs/faq/view.do?cate=faq&cateType1=${article.cateType1}&no=${article.no}"><span>Q.</span>
                                						${article.title}
                                					</a>
                                				</li>
                                				</c:forEach>
                                				<li class="more">
                                					<a href="#">더보기</a>
                                				</li>
                                			</ul>
	                                	</c:when>
	                                	<c:when test="${status.index eq 30}">
                                			<ul>
                                				<c:forEach var="article" items="${articles}" begin="30" end="39">
                                				<li>
                                					<a href="/Kmarket/cs/faq/view.do?cate=faq&cateType1=${article.cateType1}&no=${article.no}"><span>Q.</span>
                                						${article.title}
                                					</a>
                                				</li>
                                				</c:forEach>
                                				<li class="more">
                                					<a href="#">더보기</a>
                                				</li>
                                			</ul>
	                                	</c:when>
	                                	<c:when test="${status.index eq 40}">
                                			<ul>
                                				<c:forEach var="article" items="${articles}" begin="40" end="49">
                                				<li>
                                					<a href="/Kmarket/cs/faq/view.do?cate=faq&cateType1=${article.cateType1}&no=${article.no}"><span>Q.</span>
                                						${article.title}
                                					</a>
                                				</li>
                                				</c:forEach>
                                				<li class="more">
                                					<a href="#">더보기</a>
                                				</li>
                                			</ul>
	                                	</c:when>
	                                	<c:when test="${status.index eq 50}">
                                			<ul>
                                				<c:forEach var="article" items="${articles}" begin="50" end="59">
                                				<li>
                                					<a href="/Kmarket/cs/faq/view.do?cate=faq&cateType1=${article.cateType1}&no=${article.no}"><span>Q.</span>
                                						${article.title}
                                					</a>
                                				</li>
                                				</c:forEach>
                                				<li class="more">
                                					<a href="#">더보기</a>
                                				</li>
                                			</ul>
	                                	</c:when>
	                                	<c:when test="${status.index eq 60}">
                                			<ul>
                                				<c:forEach var="article" items="${articles}" begin="60" end="69">
                                				<li>
                                					<a href="/Kmarket/cs/faq/view.do?cate=faq&cateType1=${article.cateType1}&no=${article.no}"><span>Q.</span>
                                						${article.title}
                                					</a>
                                				</li>
                                				</c:forEach>
                                				<li class="more">
                                					<a href="#">더보기</a>
                                				</li>
                                			</ul>
	                                	</c:when>
	                                </c:choose>
	                            </div>
                            </c:forEach>
                        
                            <%-- <nav>
                                <h1>cateType1: ${cateType1}</h1>
                                <h2>가장 자주 묻는 질문입니다.</h2>
                            </nav>
                            <c:set var="i" value="0"/>
                             <c:forEach var="article" items="${articles}" begin="0" step="3">	<!-- 나중에 step="3" 고칠 것 -->
	                            <div>
	                                <h3>${article.cateType2}</h3>
	                                <ul>
	                                	<c:forEach var="article" items="${articles}" varStatus="status" begin="0">
                               				<li>
		                                        <a href="#">
		                                            <span>Q.</span>
		                                          	[${status.index}] ${article.title}  (${article.cateType1}, ${article.cateType2}) 
		                                        </a>
		                                    </li>
		                                    <c:set var="i" value="${i+1}"/>
	                                    </c:forEach>
	                                    <li class="more">
	                                        <a href="#">더보기</a>
	                                    </li>
	                                </ul>
	                            </div>
                            </c:forEach>   --%>
                           
                          <%-- <c:forEach var="article" items="${articles}" begin="0" step="10">
	                            <div>
	                                <h3>${article.cateType2}</h3>
	                                <ul>
	                                	<c:forEach var="article" items="${articles}" begin="0" end="9">
		                                    <li>
		                                        <a href="#">
		                                            <span>Q.</span>
		                                             ${article.cate} , ${article.cateType1}, ${article.cateType2} //${article.title} 
		                                        </a>
		                                    </li>
	                                    </c:forEach>
	                                    <li class="more">
	                                        <a href="#">더보기</a>
	                                    </li>
	                                </ul>
	                            </div>
                            </c:forEach>   --%>
                           
                             
                                                
                        </article>
                    </section>
                </div>
            </section>
   <jsp:include page="../_footer.jsp"></jsp:include>