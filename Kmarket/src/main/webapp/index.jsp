<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="./_header.jsp"></jsp:include>

                <article class="best">
                    <h1>
                        <i class="fas fa-crown" aria-hidden="true"></i>
                        베스트상품
                    </h1>
                    <ol>
                    	<c:forEach var="best" items="${index}" begin="0" end="4">
                        <li>
                            <a href="/Kmarket/product/view.do?&prodNo=${best.prodNo}">
                                <div class="thumb">
                                    <i>1</i>
                                    <img src="/home/prodImg/${best.thumb1}" alt="item1">
                                </div>
                                <article>
	                                <h2>${best.prodName}</h2>
	                                <c:choose>
	                                <c:when test="${best.discount ne 0}">
	                                <div class="org_price">
	                                    <del><fmt:formatNumber value="${best.price}" pattern="#,###" /></del>
	                                    <span>
	                                        ${best.discount}%
	                                    </span>
	                                </div>
	                                <div class="dis_price">
	                                    <ins>
	                                    	<fmt:formatNumber value="${best.sellPrice}" pattern="#,###" />
	                                    </ins>
	                                </div>
	                                </c:when>
	                                <c:otherwise>
									<div class="dis_price">
	                                    <ins>
	                                    	<fmt:formatNumber value="${best.sellPrice}" pattern="#,###" />
	                                    </ins>
	                                </div>
	                                </c:otherwise>
	                                </c:choose>
                                </article>
                            </a>
                        </li>
                        </c:forEach>
                    </ol>
                </article>
            </aside>
            <section class="mainsection">
                <!-- 슬라이더 영역 -->
                <section class="slider">
                    <ul>
                        <li>
                        <a href="#"
                            ><img src="/Kmarket/img/slider_item1.jpg" alt="item1"
                        /></a>
                        </li>
                        <li>
                        <a href="#"
                            ><img src="/Kmarket/img/slider_item2.jpg" alt="item2"
                        /></a>
                        </li>
                        <li>
                        <a href="#"
                            ><img src="/Kmarket/img/slider_item3.jpg" alt="item3"
                        /></a>
                        </li>
                        <li>
                        <a href="#"
                            ><img src="/Kmarket/img/slider_item4.jpg" alt="item4"
                        /></a>
                        </li>
                        <li>
                        <a href="#"
                            ><img src="/Kmarket/img/slider_item5.jpg" alt="item5"
                        /></a>
                        </li>
                    </ul>
                </section>
                <!-- 히트상품 영역 -->
                <section class="hit">
                    <h3>
                        <span>히트상품</span>
                    </h3>
                    <c:forEach var="hit" items="${index}" begin="5" end="12">
                    <article>
                        <a href="/Kmarket/product/view.do?&prodNo=${hit.prodNo}">
                            <div class="thumb">
                                <img src="/home/prodImg/${hit.thumb1}" alt="t1">
                            </div>
                            <h2>${hit.prodName}</h2>
                            <p>${hit.descript}</p>
                            <c:choose>
                            <c:when test="${hit.discount ne 0}">
                            <div class="org_price">
                                <del><fmt:formatNumber value="${hit.price}" pattern="#,###" /></del>
                                <span>
                                    ${hit.discount}%
                                </span>
                            </div>
                            <div class="dis_price">
                                <ins>
                                    <fmt:formatNumber value="${hit.sellPrice}" pattern="#,###" />
                                </ins>
								<c:choose>
                                <c:when test="${hit.delivery ne 0}">
                                <span>배송비<fmt:formatNumber value="${hit.delivery}" pattern="#,###" />원</span>
                                </c:when>
                                <c:otherwise>
                                <span class="free">무료배송</span>
                                </c:otherwise>
                                </c:choose>
                            </div>
                            </c:when>
                            <c:otherwise>
							<div class="dis_price">
                                <ins>
                                    <fmt:formatNumber value="${hit.sellPrice}" pattern="#,###" />
                                </ins>
								<c:choose>
                                <c:when test="${hit.delivery ne 0}">
                                <span>배송비<fmt:formatNumber value="${hit.delivery}" pattern="#,###" />원</span>
                                </c:when>
                                <c:otherwise>
                                <span class="free">무료배송</span>
                                </c:otherwise>
                                </c:choose>
                            </div>
                            </c:otherwise>
                            </c:choose>
                        </a>
                    </article>
                    </c:forEach>
                </section>
                <!-- 추천상품 영역 -->
                <section class="recommend">
                    <h3>
                        <span>추천상품</span>
                    </h3>
                    <c:forEach var="score" items="${index}" begin="13" end="20">
                    <article>
                        <a href="/Kmarket/product/view.do?&prodNo=${score.prodNo}">
                            <div class="thumb">
                                <img src="/home/prodImg/${score.thumb1}" alt="t1">
                            </div>
                            <h2>${score.prodName}</h2>
                            <p>${score.descript }</p>
                            <c:choose>
                            <c:when test="${score.discount ne 0}">
                            <div class="org_price">
                                <del><fmt:formatNumber value="${score.price}" pattern="#,###" /></del>
                                <span>
                                    ${score.discount}%
                                </span>
                            </div>
                            <div class="dis_price">
                                <ins>
                                    <fmt:formatNumber value="${score.sellPrice}" pattern="#,###" />
                                </ins>
                                <c:choose>
                                <c:when test="${score.delivery ne 0}">
                                <span>배송비<fmt:formatNumber value="${score.delivery}" pattern="#,###" />원</span>
                                </c:when>
                                <c:otherwise>
                                <span class="free">무료배송</span>
                                </c:otherwise>
                                </c:choose>
                            </div>
							</c:when>
							<c:otherwise>
							<div class="dis_price">
                                <ins>
                                    <fmt:formatNumber value="${score.sellPrice}" pattern="#,###" />
                                </ins>
                                <c:choose>
                                <c:when test="${score.delivery ne 0}">
                                <span>배송비<fmt:formatNumber value="${score.delivery}" pattern="#,###" />원</span>
                                </c:when>
                                <c:otherwise>
                                <span class="free">무료배송</span>
                                </c:otherwise>
                                </c:choose>
                            </div>
							</c:otherwise>
                            </c:choose>
                        </a>
                    </article>
                    </c:forEach>
                </section>
                <section class="new">
                    <h3>
                        <span>최신상품</span>
                    </h3>
                    <c:forEach var="newProd" items="${index}" begin="21" end="28">
                    <article>
                        <a href="/Kmarket/product/view.do?&prodNo=${newProd.prodNo}">
                            <div class="thumb">
                                <img src="/home/prodImg/${newProd.thumb1}" alt="t1">
                            </div>
                            <h2>${newProd.prodName}</h2>
                            <p>${newProd.descript }</p>
							<c:choose>
                            <c:when test="${newProd.discount ne 0}">
                            <div class="org_price">
                                <del><fmt:formatNumber value="${newProd.price}" pattern="#,###" /></del>
                                <span>
                                    ${newProd.discount}%
                                </span>
                            </div>
                            <div class="dis_price">
                                <ins>
                                   <fmt:formatNumber value="${newProd.sellPrice}" pattern="#,###" /> 
                                </ins>
								<c:choose>
                                <c:when test="${newProd.delivery ne 0}">
                                <span>배송비<fmt:formatNumber value="${newProd.delivery}" pattern="#,###" />원</span>
                                </c:when>
                                <c:otherwise>
                                <span class="free">무료배송</span>
                                </c:otherwise>
                                </c:choose>
                            </div>
							</c:when>
							<c:otherwise>
							<div class="dis_price">
                                <ins>
                                    <fmt:formatNumber value="${newProd.sellPrice}" pattern="#,###" /> 
                                </ins>
								<c:choose>
                                <c:when test="${newProd.delivery ne 0}">
                                <span>배송비<fmt:formatNumber value="${newProd.delivery}" pattern="#,###" />원</span>
                                </c:when>
                                <c:otherwise>
                                <span class="free">무료배송</span>
                                </c:otherwise>
                                </c:choose>
                            </div>
							</c:otherwise>
                            </c:choose>
                        </a>
                    </article>
                    </c:forEach>
                </section>
                <section class="discount">
                    <h3>
                        <span>할인상품</span>
                    </h3>
                    <c:forEach var="discount" items="${index}" begin="29" end="36">
                    <c:choose>
                    <c:when test="${not empty discount}">
                    <article>
                        <a href="/Kmarket/product/view.do?&prodNo=${discount.prodNo}">
                            <div class="thumb">
                                <img src="/home/prodImg/${discount.thumb1}" alt="t1">
                            </div>
                            <h2>${discount.prodName}</h2>
                            <p>${discount.descript}</p>
                            <c:choose>
                            <c:when test="${discount.discount ne 0}">
                            <div class="org_price">
                                <del><fmt:formatNumber value="${discount.price}" pattern="#,###" /></del>
                                <span>
                                    ${discount.discount}%
                                </span>
                            </div>
                            <div class="dis_price">
                                <ins>
                                    <fmt:formatNumber value="${discount.sellPrice}" pattern="#,###" />
                                </ins>
								<c:choose>
                                <c:when test="${discount.delivery ne 0}">
                                <span>배송비<fmt:formatNumber value="${discount.delivery}" pattern="#,###" />원</span>
                                </c:when>
                                <c:otherwise>
                                <span class="free">무료배송</span>
                                </c:otherwise>
                                </c:choose>
                            </div>
                            </c:when>
                            <c:otherwise>
							<div class="dis_price">
                                <ins>
                                   <fmt:formatNumber value="${discount.sellPrice}" pattern="#,###" />
                                </ins>
								<c:choose>
                                <c:when test="${discount.delivery ne 0}">
                                <span>배송비<fmt:formatNumber value="${discount.delivery}" pattern="#,###" />원</span>
                                </c:when>
                                <c:otherwise>
                                <span class="free">무료배송</span>
                                </c:otherwise>
                                </c:choose>
                            </div>
                            </c:otherwise>
                            </c:choose>
                        </a>
                    </article>
                    </c:when>
                    <c:otherwise>
                    등록된 상품이 없습니다.
                    </c:otherwise>
                    </c:choose>
                    </c:forEach>
                </section>
            </section>
        </main>
<jsp:include page="./_footer.jsp"></jsp:include>