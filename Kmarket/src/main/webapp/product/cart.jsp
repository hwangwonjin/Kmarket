<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../_header.jsp"></jsp:include>
<script src="/Kmarket/product/js/Cart.js"></script>
            </aside>
            <section class="cart">
                <nav>
                    <h1>장바구니</h1>
                    <p>
                        HOME > 
                        <strong>장바구니</strong>
                    </p>
                </nav>
               <form action="/Kmarket/product/order.do" method="post">
                    <table border="0">
                        <tr>
                            <th>
                            	<input type="checkbox" name="all" class="chk">
                            	<input type="hidden" class="uid" value="${sessUser.uid}">
                            </th>
                            <th>상품명</th>
                            <th>총수량</th>
                            <th>판매가</th>
                            <th>할인</th>
                            <th>포인트</th>
                            <th>배송비</th>
                            <th>소계</th>
                        </tr>
                        <c:choose>
                        <c:when test="${empty cart}">
                        <tr class="empty">
                            <td colspan="7">장바구니에 상품이 없습니다.</td>
                        </tr>
                        </c:when>
                        <c:otherwise>
                        <c:forEach var="cart" items="${cart}">
                        <tr class="${cart.cartNo}">
                            <td>
                            	<input type="checkbox" name="cartNo" id="cartNo" value="${cart.cartNo}">
                            	<input type="hidden" class="price" value="${cart.price}">
                            	<input type="hidden" class="salePrice" value="${cart.price-cart.sellPrice}">
                            	<input type="hidden" id="delivery" value="${cart.delivery}">
                            	<input type="hidden" id="point" value="${cart.point}">
                            	<input type="hidden" id="total" value="${cart.total}">
                           	</td>
                            <td><article>
                                <a href="/Kmarket/product/view.do?prodNo=${cart.prodNo}">
                                    <img src="/home/prodImg/${cart.thumb1}" alt="1">
                                </a>
                                <div>
                                    <h2><a href="/Kmarket/product/view.do?prodNo=${cart.prodNo}">${cart.prodName}</a></h2>
                                    <p>${cart.descript}</p>
                                </div>
                            </article></td>
                            <td>${cart.count}</td>
                            <td><fmt:formatNumber value="${cart.price}" pattern="#,###" /></td>
                            <td>${cart.discount}%</td>
                            <td>${cart.point}p</td>
                            <c:if test="${cart.delivery eq 0}">
                            <td>무료배송</td>
                            </c:if>
                            <c:if test="${cart.delivery ne 0}">
                            <td><fmt:formatNumber value="${cart.delivery}" pattern="#,###" />원</td>
                            </c:if>
                            <td><fmt:formatNumber value="${cart.total}" pattern="#,###" /></td>
                        </tr>
                        </c:forEach>
                        </c:otherwise>
                        </c:choose>
                     
                    </table>
                    <input type="button" name="del" class="del" value="선택삭제">
                    <div class="total">
                        <h2>전체합계</h2>
                        <table>
                            <tr>
                                <td>상품수</td>
                                <td class="productCount_span">0</td>
                            </tr>
                            <tr>
                                <td>상품금액</td>
                                <td class="costPrice_span">0</td>
                            </tr>
                            <tr>
                                <td>할인금액</td>
                                <td class="totalSale_span">0</td>
                            </tr>
                            <tr>
                                <td>배송비</td>
                                <td class="totalDelivery_span">0</td>
                            </tr>
                            <tr>
                                <td>전체주문금액</td>
                                <td class="totalPrice_span">0</td>
                            </tr>
							<tr>
                                <td>적립포인트</td>
                                <td class="totalPoint_span">0</td>
                            </tr>
                        </table>
                        <input type="submit" class="submit" value="주문하기">
                    </div>
               </form>
            </section>
        </main>
<jsp:include page="../_footer.jsp"></jsp:include>