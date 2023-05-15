<%@ page  contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<jsp:include page="../_header.jsp"></jsp:include>
        <section id="cs">
            <div class="notice">
                <nav>
                    <div>
                        <p>
                            홈
                            <span>></span>
                            공지사항
                        </p>
                    </div>
                </nav>
                <section class="view">
                    <aside>
                        <h2>공지사항</h2>
                        <ul>
                            <li class="${cateType1 == null ? 'on' : 'off'}">
                                <a href="/Kmarket/cs/notice/list.do?cate=notice">전체</a>
                            </li>
                            <li class="${cateType1 == '고객서비스' ? 'on' : 'off'}">
                                <a href="/Kmarket/cs/notice/list.do?cate=notice&cateType1=고객서비스">고객서비스</a>
                            </li>
                            <li class="${cateType1 == '안전거래' ? 'on' : 'off'}">
                                <a href="/Kmarket/cs/notice/list.do?cate=notice&cateType1=안전거래">안전거래</a>
                            </li>
                            <li class="${cateType1 == '위해상품' ? 'on' : 'off'}">
                                <a href="/Kmarket/cs/notice/list.do?cate=notice&cateType1=위해상품">위해상품</a>
                            </li>
                            <li class="${cateType1 == '이벤트당첨' ? 'on' : 'off'}">
                                <a href="/Kmarket/cs/notice/list.do?cate=notice&cateType1=이벤트당첨">이벤트당첨</a>
                            </li>
                        </ul>
                    </aside>
                    <article>
                        <nav>
                            <h2 class="title">[${vo.cateType2}] ${vo.title}</h2>
                            <span class="date">${vo.rdate.substring(0,10)}</span>
                        </nav>
                        <div class="content">
                        	<p>
                        		${vo.content}
                        	</p>
                        </div>
                        <a href="/Kmarket/cs/notice/list.do?cate=notice&cateType1=${cateType1}" class="btnList">목록보기</a>
                    </article>
                </section>
            </div>
        </section>
  <jsp:include page="../_footer.jsp"></jsp:include>