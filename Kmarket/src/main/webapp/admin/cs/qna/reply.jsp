<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/admin/_header.jsp"></jsp:include>
<script>
	//replyContent 확인용 콘솔 로그 출력
	$(function(){
		$('textarea[name=replyContent]').focusout(function(){
			let replyContent = $('textarea[name=replyContent]').val();
			console.log("replyContent: " + replyContent);
		});
		
		
		$('.btnSubmit').click(function(){
			//alert('답변이 작성되었습니다.');
			swal(
			        "Check!",
			        "답변이 작성되었습니다.",
			        "warning"
			      )
		});
		
		// 글 삭제하기
		$('.btnDelete').click(function(){
			
			let no = $('input[name=no]').val();
			console.log("no: "+no);
			
			let rm = confirm('문의글을 삭제하시겠습니까?');
			
			if(rm){
				$.ajax({
					url: '/Kmarket/admin/cs/qna/delete.do',
					method: 'get',
					data : {"no":no},
					dataType : 'json',
					success:function(data){
						console.log("data : "+data.result);
						if(data.result == 1){
							alert('삭제되었습니다.');
							location.replace('/Kmarket/admin/cs/qna/list.do');
							return true;
						}else{
							//alert('실패하였습니다.');
							swal(
						        "Check!",
						        "실패하였습니다.",
						        "warning"
						      )
							return false;
						}
					}
				});
			}else{
				return false;
			}
		});
		
		
	});
</script>
<!DOCTYPE html>
            <section id="cs">
            	<div class="qna">
	                <nav>
	                    <h3>문의하기 답변</h3>
	                    <p>
	                        HOME > 고객센터 >
	                        <strong>문의하기</strong>
	                    </p>
	                </nav>
	                <section class="reply">
						<article>
	                        <form action="/Kmarket/admin/cs/qna/reply.do" method="post">
	                        <input type="hidden" name="uid" value="${sessUser.uid}"/>
	                        <input type="hidden" name="cate" value="${cate}"/>
	                        <input type="hidden" name="cateType1" value="${vo.cateType1}"/>
	                        <input type="hidden" name="cateType2" value="${vo.cateType2}"/>
	                        <input type="hidden" name="no" value="${no}"/>
	                            <table>
	                                <tbody>
	                                    <tr>
	                                        <td>유형</td>
	                                        <td>
	                                        	<p>${vo.cateType1} - ${vo.cateType2}</p>
	                                        </td>
	                                    </tr>
	                                    <tr>
	                                        <td>제목</td>
	                                        <td>
	                                            <p>${vo.title}</p> 
	                                        </td>
	                                    </tr>
	                                    <tr>
	                                        <td>내용</td>
	                                        <td>
	                                           <p>${vo.content}</p> 
	                                        </td>
	                                    </tr>
	                                    <tr>
	                                        <td>답변</td>
	                                        <td>
	                                            <textarea name="replyContent" placeholder="내용을 입력하세요." required>${vo.replyContent}</textarea>
	                                        </td>
	                                    </tr>
	                                </tbody>
	                            </table>
	                             <div>
	                             	<a href="#" class="btnDelete" id="remove">삭제</a>
	                                <input type="submit" class="btnSubmit" value="답변등록">
	                                <a href="/Kmarket/admin/cs/qna/list.do?cate=qna" class="btnList">목록</a>
	                            </div>
	                        </form>
	                    </article>
	                </section>
                </div>
            </section>
        </main>
<jsp:include page="/admin/_footer.jsp"></jsp:include>