<%@ page  contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<jsp:include page="../_header.jsp"></jsp:include>
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
                <section class="view">
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
                            <h2 class="title">
                                <span>Q.</span>
                                ${vo.title} 
                            </h2>
                        </nav>
                        <div class="content">
                            <p>
                                 ${vo.content}
                            </p>
                        </div>
                        <a href="/Kmarket/cs/faq/list.do?cate=faq&cateType1=${vo.cateType1}" class="btnList">목록보기</a>
                    </article>
                </section> 
            </div>
        </section>
  <jsp:include page="../_footer.jsp"></jsp:include>