<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/admin/_header.jsp"></jsp:include>
<script>
$(function(){
	$('select[name=type]').change(function(){
		cateType1 = $('select[name=type]').val();
		console.log("cateType1: "+cateType1);
		$('input[name=cateType1]').val($(this).val());
	});
	$('select[name=cateType2]').change(function(){
		cateType2 = $('select[name=cateType2]').val();
		console.log("cateType2: "+cateType2);
		$('input[name=cateType2]').val($(this).val());
	});
	// title 확인용 콘솔 로그 창 (삭제 가능)
	$('input[name=title]').focusout(function(){
		let title = $('input[name=title]').val();
		console.log("title: " + title);
	});
	// content 확인용 콘솔 로그 창 (삭제 가능)
	$('textarea[name=content]').focusout(function(){
		let content = $('textarea[name=content]').val();
		console.log("content: " + content);
	});
	
	
	$('.write > article > form').submit(function(){
		
		let cateType1 = $('select[name=type]').val();
		let cateType2 = $('select[name=cateType2]').val();
		
		if(cateType1 == '1차 유형 선택' || cateType2 == '2차 유형 선택'){
			//alert('유형을 선택해주세요');
			swal(
			        "Check!",
			        "유형을 선택해주세요.",
			        "warning"
			      )
			return false;
		}
	});
	
});
</script>
<!DOCTYPE html>
            <section id="cs">
            	<div class="notice">
	                <nav>
	                    <h3>공지사항 작성</h3>
	                    <p>
	                        HOME > 고객센터 >
	                        <strong>공지사항</strong>
	                    </p>
	                </nav>
	                <section class="write">
						<article>
	                        <form action="/Kmarket/admin/cs/notice/write.do" method="post">
	                        <input type="hidden" name="uid" value="${sessUser.uid}"/>	
	                        <input type="hidden" name="cate" value="${cate}"/>	
	                       	<input type="hidden" name="cateType1" />		
                     		<input type="hidden" name="cateType2" /> 		
	                            <table>
	                                <tbody>
	                                    <tr>
	                                        <td>유형</td>
	                                        <td>
	                                            <select name="type" required>
	                                            	<option>1차 유형 선택</option>
	                                            	<option>고객서비스</option>
	                                            	<option>안전거래</option>
	                                            	<option>위해상품</option>
	                                            	<option>이벤트당첨</option>
	                                            </select>
    											<select name="cateType2" required>
    												<option>2차 유형 선택</option>
    												<option>공지</option>
    												<option>안내</option>
    												<option>점검</option>
    												<option>주의</option>
    												<option>발표</option>
    												<option>위해상품정보</option>
    											</select>
	                                        </td>
	                                    </tr>
	                                    <tr>
	                                        <td>제목</td>
	                                        <td>
	                                            <input type="text" name="title" placeholder="제목을 입력하세요." required/>
	                                        </td>
	                                    </tr>
	                                    <tr>
	                                        <td>내용</td>
	                                        <td>
	                                            <textarea name="content" placeholder="내용을 입력하세요." required></textarea>
	                                        </td>
	                                    </tr>
	                                </tbody>
	                            </table>
	                             <div>
	                                <a href="/Kmarket/admin/cs/notice/list.do?cate=notice" class="btnList">취소하기</a>
	                                <input type="submit" class="btnSubmit" value="등록하기">
	                            </div>
	                        </form>
	                    </article>
	                </section>
                </div>
            </section>
        </main>
<jsp:include page="/admin/_footer.jsp"></jsp:include>