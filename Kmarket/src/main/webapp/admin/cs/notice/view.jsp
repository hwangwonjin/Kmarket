<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/admin/_header.jsp"></jsp:include>
<script>
	$(function(){
		$('#remove').click(function(e){
			let isDelete = confirm('정말 삭제하시겠습니까?');
			
			if(isDelete){
				return true;
			}else{
				return false;
			}
		});
	});
</script>
<!DOCTYPE html>
            <section id="cs">
            	<div class="notice">
	                <nav>
	                    <h3>공지사항 보기</h3>
	                    <p>
	                        HOME > 고객센터 >
	                        <strong>공지사항</strong>
	                    </p>
	                </nav>
	                <section class="view">
	                    <article>
	                        <form action="#">
	                        	<table>
	                        		<tr>
                                        <td>유형</td>
                                        <td>
                                            <h2>${vo.cateType1}</h2>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>제목</td>
                                        <td>
                                           <h2>[${vo.cateType2}] ${vo.title}</h2>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>내용</td>
                                        <td>
                                        	<p>
                                        		${vo.content}
                                        	</p>
                                        </td>
                                    </tr>
	                        	</table>
	                        	<div>
	                                <a href="/Kmarket/admin/cs/notice/delete.do?no=${no}" class="btnDelete" id="remove">삭제</a>
	                                <a href="/Kmarket/admin/cs/notice/modify.do?cate=notice&cateType1=${article.cateType1}&no=${no}" class="btnModify">수정</a>
	                                <a href="/Kmarket/admin/cs/notice/list.do?cate=notice" class="btnList">목록</a>
	                            </div>
	                        </form>
	                    </article>
	                </section>
                </div>
            </section>
        </main>
<jsp:include page="/admin/_footer.jsp"></jsp:include>