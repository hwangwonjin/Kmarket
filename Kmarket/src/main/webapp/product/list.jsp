<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../_header.jsp"></jsp:include>
<script>
	$(function(){
		
		let cate1 = ${category.c1Name};
		console.log(cate1);
		
	});
</script>
            </aside>
            <section class="list">
                <nav>
                    <h1>상품목록</h1>
                    <p>
                        HOME > 
                        <span>${category.c1Name}</span>
                         > 
                        <strong>${category.c2Name}</strong>
                    </p>
                </nav>
                <ul class="sort">
                    <li>
                        <a href="/Kmarket/product/list.do?cate1=${cate1}&cate2=${cate2}&sort=sold" class="${sort eq 'sold' ? 'on' : 'off'}">판매많은순</a>
                    </li>
                    <li>
                        <a href="/Kmarket/product/list.do?cate1=${cate1}&cate2=${cate2}&sort=ascSold" class="${sort eq 'ascSold' ? 'on' : 'off' }">낮은가격순</a>
                    </li>
                    <li>
                        <a href="/Kmarket/product/list.do?cate1=${cate1}&cate2=${cate2}&sort=descSold" class="${sort eq 'descSold' ? 'on' : 'off' }">높은가격순</a>
                    </li>
                    <li>
                        <a href="/Kmarket/product/list.do?cate1=${cate1}&cate2=${cate2}&sort=score" class="${sort eq 'score' ? 'on' : 'off' }">평점높은순</a>
                    </li>
                    <li>
                        <a href="/Kmarket/product/list.do?cate1=${cate1}&cate2=${cate2}&sort=review" class="${sort eq 'review' ? 'on' : 'off' }">후기많은순</a>
                    </li>
                    <li>
                        <a href="/Kmarket/product/list.do?cate1=${cate1}&cate2=${cate2}&sort=rdate" class="${sort eq 'rdate' ? 'on' : 'off' }">최근등록순</a>
                    </li>
                </ul>
                <table>
                	<c:forEach var="products" items="${products}">
                    <tr>
                        <td><a href="/Kmarket/product/view.do?prodNo=${products.prodNo}" class="thumb">
                            <img src="/home/prodImg/${products.thumb1}" alt="상품이미지">
                        </a></td>
                        <td>
                            <a href="/Kmarket/product/view.do?prodNo=${products.prodNo}" ><h3 class="name">${products.prodName}</h3></a>
                            <a href="#" class="desc">${products.descript}</a>
                        </td>
                        <td>
                            <ul>
                                <li>
                                	<ins class="dis-price">
                                		<%-- ${products.sellPrice} --%>
                                		<fmt:formatNumber value="${products.sellPrice}" pattern="#,###" />
                                	</ins>
                                </li>
                                <c:choose>
                                <c:when test="${products.discount ne 0}">
                                <li>
                                    <del class="org-price"><fmt:formatNumber value="${products.price}" pattern="#,###" /></del>
                                    <span class="discount">${products.discount}%</span>
                                </li>
                                </c:when>
                                </c:choose>
                                <c:if test="${products.delivery eq 0}">
                                <li><span class="free-delivery">배달비무료</span></li>
                                </c:if>
                                <c:if test="${products.delivery ne 0}">
                                <li><span>배송비<fmt:formatNumber value="${products.delivery}" pattern="#,###" />원</span></li>
                                </c:if>
                            </ul>
                        </td>
                         
                        <td>
                            <h4 class="seller"><i class="fas fa-home" aria-hidden="true"></i>${products.seller}
                            </h4>
                            <c:choose>
                            <c:when test="${products.level eq '3'}">
                            <h5 class="badge power" style="background-image: url(../img/ico_power_dealer.gif)">판매자등급</h5>
                            </c:when>
							<c:when test="${products.level eq '2'}">
                            <h5 class="badge power" style="background-image: url(../img/ico_great_seller.gif)">판매자등급</h5>
                            </c:when>
                            <c:otherwise>
                            <h5>일반 판매자</h5>
                            </c:otherwise>
                            </c:choose>
                            <c:choose>
                            <c:when test="${products.score eq '5'}">
                            <h6 class="rating star5">상품평</h6>
                            </c:when>
                            <c:when test="${products.score eq '4'}">
                            <h6 class="rating star4">상품평</h6>
                            </c:when>
							<c:when test="${products.score eq '3'}">
							<h6 class="rating star3">상품평</h6>
                            </c:when>
							<c:when test="${products.score eq '2'}">
							<h6 class="rating star2">상품평</h6>
                            </c:when>
                            <c:otherwise>
                            <h6 class="rating star1">상품평</h6>
                            </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                   </c:forEach>
                    
                </table>
                <div class="paging">
                	
                    <span class="prev">
                        <c:if test="">
                        	<a href="./list.do?cate1=${cate1}&cate2=${cate2}&pg=${pageGroupStart -1}">< 이전</a>
                    	</c:if>
                    </span>
                    
                    <span class="num">
                    	<c:forEach var="i" begin="${pageGroupStart}" end="${pageGroupEnd}">
                        	<a href="./list.do?cate1=${cate1}&cate2=${cate2}&pg=${i}" class="on${currentPage == i?'current':'off'}">${i}</a>
                     	</c:forEach>
                    </span>
                    <span class="next">
                    	<c:if test="${pageGroupEnd < lastPageNum}">
                        <a href="./list.do?cate1=${cate1}&cate2=${cate2}&pg=${pageGroupEnd + 1}">다음 ></a>
                        </c:if>
                    </span>
                </div>
            </section>
        </main>
<jsp:include page="../_footer.jsp"></jsp:include>