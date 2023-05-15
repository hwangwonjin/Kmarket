<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/admin/_header.jsp"></jsp:include>
<script>
	$(function() {
		$('#remove').click(function() {
			
			let isDelete = confirm('정말 삭제하시겠습니까?');
			
			if(isDelete){
				
				let no = $('input:checkbox:checked').val();
				let checkbox = $('input:checkbox:checked');
				
				console.log("no : "+no);
				
				$.ajax({
					url : '/Kmarket/admin/cs/faq/delete.do',
					method : 'get',
					data : {"no":no},
					dataType : 'json',
					success : function(data) {
						console.log("data : "+data.result);
						if(data.result == 1){
							alert('삭제되었습니다.');
							checkbox.parent().parent().remove();
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
				return true;
			}else{
				return false;
			}
			
		});
	});
</script>
<!DOCTYPE html>
            <section id="cs">
            	<div class="faq">
	                <nav>
	                    <h3>자주묻는질문 보기</h3>
	                    <p>
	                        HOME > 고객센터 >
	                        <strong>자주묻는질문</strong>
	                    </p>
	                </nav>
	                <section class="view">
						<article>
							<table>
							    <tbody>
							        <tr>
							            <td>
							            	유형
							            </td>
							            <td>${vo.cateType1} - ${vo.cateType2}</td>
							        </tr>
							        <tr>
							            <td>
							           		제목
							            </td>
							            <td>${vo.title}</td>
							        </tr>
							        <tr>
							            <td>
							            	내용
							            </td>
							            <td>${vo.content}</td>
							        </tr>
							    </tbody>
							</table>
							 <div>
							   <a href="/Kmarket/admin/cs/faq/delete.do?no=${no}" class="btnDelete" id="remove">삭제</a>
	                           <a href="/Kmarket/admin/cs/faq/modify.do?cate=faq&cateType1=${vo.cateType1}&cateType2=${vo.cateType2}&no=${no}" class="btnModify">수정</a>
	                           <a href="/Kmarket/admin/cs/faq/list.do?cate=faq" class="btnList">목록</a>
							</div>
						</article>
	                </section>
                </div>
            </section>
        </main>
<jsp:include page="/admin/_footer.jsp"></jsp:include>