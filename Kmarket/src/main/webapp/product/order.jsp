<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../_header.jsp"></jsp:include>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="/Kmarket/product/js/zipcode.js"></script>
<script src="/Kmarket/product/js/order.js"></script>
            </aside>
            <section class="order">
                <nav>
                    <h1>주문결제</h1>
                    <p>
                        HOME > 
                        <span>장바구니</span>
                         > 
                        <strong>주문결제</strong>
                    </p>
                </nav>
                <!-- form 전송시 데이터가 필요해서 input hidden을 과하게 집어넣음... -->
                <form action="/Kmarket/product/complete.do" method="post">
                	<input type="hidden" name="uid" value="${sessUser.uid}">
                	<input type="hidden" name="prodNo" value="${prodNo}">
					<input type="hidden" name="ordCount" value="${vo.count}">
					<input type="hidden" name="ordPrice" value="${vo.price}">
					<input type="hidden" name="ordDiscount" value="${vo.discount}">
					<input type="hidden" name="ordDelivery" value="${vo.delivery}">
					<input type="hidden" name="ordsavePoint" value="${vo.point}">
					<input type="hidden" name="ordusedPoint" value="0">
					<input type="hidden" name="ordTotPrice" value="${vo.total}">
					<input type="hidden" name="currentPoint" value="${sessUser.point}">
					<input type="hidden" name="name" value="${sessUser.name}">
					<input type="hidden" name="name" value="${sessUser.hp}">
                    <table border="0">
                        <tr>
                            <th>상품명</th>
                            <th>총수량</th>
                            <th>판매가</th>
                            <th>할인</th>
                            <th>포인트</th>
                            <th>배송비</th>
                            <th>소계</th>
                        </tr>
                        <c:choose>
                        <c:when test="${prod eq null}">
                        <tr class="empty">
                            <td colspan="7">장바구니에 상품이 없습니다.</td>
                        </tr>
                        </c:when>
                        <c:otherwise>
                        <c:forEach var="cart" items="${prod}">
                        <tr class="${cart.cartNo}">
                            <td><article>
                                <a href="#">
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
                            <td>${cart.point}</td>
                            <td><fmt:formatNumber value="${cart.delivery}" pattern="#,###" /></td>
                            <td><fmt:formatNumber value="${cart.total}" pattern="#,###" /></td>
                        </tr>
                        </c:forEach>
                        </c:otherwise>
                        </c:choose>
                    </table>
                    <div class="final">
                        <h2>최종결제 정보</h2>
                        <table>
                            <tr>
                                <td>총</td>
                                <td>${vo.count} 건</td>
                            </tr>
                            <tr>
                                <td>상품금액</td>
                                <td><fmt:formatNumber value="${vo.price}" pattern="#,###" /></td>
                            </tr>
                            <tr>
                                <td>할인금액</td>
                                <td><fmt:formatNumber value="${vo.discount}" pattern="#,###" /></td>
                            </tr>
                            <tr>
                                <td>배송비</td>
                                <td class="point"><fmt:formatNumber value="${vo.delivery}" pattern="#,###" /></td>
                            </tr>
                            <tr>
                                <td>포인트 할인</td>
                                <td>0</td>
                            </tr>
                            <tr>
                                <td>전체주문금액</td>
                                <td><fmt:formatNumber value="${vo.total}" pattern="#,###" /></td>
                            </tr>
							<tr>
                                <td>적립 포인트</td>
                                <td><fmt:formatNumber value="${vo.point}" pattern="#,###" /></td>
                            </tr>
                        </table>
                        <input type="submit" value="결제하기">
                    </div>
                    <!-- 배송정보 -->
                    <article class="delivery">
                        <h1>배송정보</h1>
                        <table>
                            <tr>
                                <td>주문자</td>
                                <td><input type="text" name="orderer" value="${sessUser.name}"></td>
                            </tr>
                            <tr>
                                <td>휴대폰</td>
                                <td>
                                    <input type="text" name="hp" value="${sessUser.hp}">
                                    <span>- 포함 입력</span>
                                </td>
                            </tr>
                            <tr>
                            	<td>수취인</td>
                            	<td>
                            		<input type="text" name="receiver" value="${sessUser.name}">
                            	</td>
                            </tr>
                            <tr>
                                <td>우편번호</td>
                                <td>
                                    <input type="text" name="zip" id="zip" readonly="readonly" value="${sessUser.zip}">
                                    <button type="button" onclick="zipcode()">검색</button>
                                </td>
                            </tr>
                            <tr>
                                <td>기본주소</td>
                                <td>
                                    <input type="text" name="addr1" id="addr1" value="${sessUser.addr1}">
                                </td>
                            </tr>
                            <tr>
                                <td>상세주소</td>
                                <td><input type="text" name="addr2" id="addr2"></td>
                            </tr>
                        </table>
                    </article>
                    <!-- 할인정보 -->
                    <article class="discount">
                        <h1>할인정보</h1>
                        <div>
                            <p>
                                현재 포인트 : 
                                <span><fmt:formatNumber value="${sessUser.point}" pattern="#,###" /></span>점
                            </p>

                            <label>
                                <input type="text" name="point" value="0">점
                                <input type="button" class="addPoint" value="적용">
                            </label>
                            <span>
                            	포인트 5,000점 이상이면 현금처럼 사용 가능합니다.<br>
                            </span>
                        </div>
                    </article>
                    <!-- 결제방법 -->
                    <article class="payment">
                        <h1>결제방법</h1>
                        <div>
                            <span>신용카드</span>
                            <p>
                                <label>
                                    <input type="radio" name="payment" value="1">신용카드 결제
                                </label>
                                <label>
                                    <input type="radio" name="payment" value="2">체크카드 결제
                                </label>
                            </p>
                        </div>
                        <div>
                            <span>계좌이체</span>
                            <p>
                                <label>
                                    <input type="radio" name="payment" value="3">실시간 계좌이체
                                </label>
                                <label>
                                    <input type="radio" name="payment" value="4">무통장 입금
                                </label>
                            </p>
                        </div>
                        <div>
                            <span>기타</span>
                            <p>
                                <label>
                                    <input type="radio" name="payment" value="5">휴대폰 결제
                                </label>
                                <label>
                                    <input type="radio" name="payment" value="6">카카오페이
                                    <img src="../img/ico_kakaopay.gif" alt="카카오페이">
                                </label>
                            </p>
                        </div>
                    </article>
                    <!-- 경고 -->
                    <article class="alert">
                        <ul>
                            <li><span>케이마켓의 모든 판매자는 안전거래를 위해 구매금액, 결제수단에 상관없이 모든거래에 대하여 케이마켓 유한책임회사의 구매안전서비스(에스크로)를 제공하고 있습니다.
                            </span></li>
                            <li><span>케이마켓 유한책임회사의 전자금융거래법에 의해 결제대금예치업 등록번호는 02-006-00008 입니다.
                            </span></li>
                            <li><span>등록여부는 금융감독원 홈페이지(www.fss.or.kr)의 업무자료>인허가업무안내>전자금융업등록현황에서 확인하실수 있습니다.
                            </span></li>
                        </ul>
                    </article>
                </form>
            </section>
        </main>
<jsp:include page="../_footer.jsp"></jsp:include>