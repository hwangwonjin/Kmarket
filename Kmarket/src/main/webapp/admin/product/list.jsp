<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="./_header.jsp"></jsp:include>
<script>
	$(function(){
		
		let chkNo = document.getElementsByName("prodNo");
		let rowCnt = chkNo.length;
		
		console.log(chkNo.length);
		
		$('input[name=deleteButton]').click(function(){
			let list = $('input[name=prodNo]');
			let checkbox = $('input[name=prodNo]:checked');
			
			if(checkbox.length == 0){
				//alert('삭제할 게시글을 선택하세요.');
				swal(
			        "Check!",
			        "삭제할 게시글을 선택하세요.",
			        "warning"
			      ) 
				return;
			}
			
			let chkArr = new Array();
			
			for(let i=0; i<list.length; i++){
				if(list[i].checked){
					chkArr.push(list[i].value);
				}
			}
			
			$.ajax({
				url : '/Kmarket/admin/product/deleteProduct.do',
				method: 'POST',
				traditional: true,
				dataType : 'json',
				data : {"chkArr" : chkArr},
				success:function(data){
					if(data.result == 1){
						swal(
							"Check!",	
							"삭제되었습니다.",	
							"success"
						).then(function(){
							location.reload();
						});
						//alert('삭제되었습니다.');
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
		});
		
		$('input[name=all]').change(function(){
			if($('input[name=all]').is(":checked")){
				$('input[name=prodNo]').prop("checked", true);
			}else{
				$('input[name=prodNo]').prop("checked", false);
			}
		});
		
		$('input[name=prodNo]').change(function(){
			if($('input[name=prodNo]:checked').length == $('input[name=prodNo]')){
				$('input[name=all]').prop("checked", true);
			}else{
				$('input[name=all]').prop("checked", false);
			}
		});
		
		$('input[type=submit]').click(function(){
			let search = $('select[name=search]').val();
			let text = $('input[name=search]').val();
			let level = $('input[name=level]').val();
			let uid = $('input[name=uid]').val();
			
			location.href = "/Kmarket/admin/product/list.do?search="+search+"&text="+text+"&level="+level+"&uid="+uid;
		});
	});
</script>
            <section id="admin-product-list">
                <nav>
                    <h3>상품목록</h3>
                    <p>
                        HOME > 상품관리 >
                        <strong>상품목록</strong>
                    </p>
                </nav>
                <section>
                    <div>
                        <select name="search">
                            <option value="prodName">상품명</option>
                            <option value="prodNo">상품코드</option>
                            <option value="company">제조사</option>
                            <option value="seller">판매자</option>
                        </select>
                       	<input type="hidden" name="level" value="${sessUser.level}" >
                       	<input type="hidden" name="uid" value="${sessUser.uid}" >
                        <input type="text" name="search" placeholder="검색할 단어">
                        <input type="submit" value="검색">
                    </div>
                    <table>
                        <tr>
                            <th><input type="checkbox" name="all" id="allCk"></th>
                            <th>이미지</th>
                            <th>상품코드</th>
                            <th>상품명</th>
                            <th>판매가격</th>
                            <th>할인율</th>
                            <th>포인트</th>
                            <th>재고</th>
                            <th>판매자</th>
                            <th>조회</th>
                            <th>관리</th>
                        </tr>
                        <c:choose>
                        <c:when test="${empty Product}">
                        <tr>
                        	<td colspan="11" style="color:red">등록된 상품이 없습니다.</td>
                        </tr>
                        </c:when>
                        <c:otherwise>
						<c:forEach var="Product" items="${Product}">
                        <tr>
                            <td><input type="checkbox" name="prodNo" id="prodNo" value="${Product.prodNo}"></td>
                            <td><img src="/home/prodImg/${Product.thumb1}" class="thumb"></td>
                            <td>${Product.prodNo}</td>
                            <td>${Product.prodName}</td>
                            <td>${Product.price}</td>
                            <td>${Product.discount}</td>
                            <td>${Product.point}</td>
                            <td>${Product.stock}</td>
                           	<td>${Product.seller}</td>
                            <td>${Product.hit}</td>
                            <td>
                                <a href="#">[삭제]</a>
                                <a href="#">[수정]</a>
                            </td>
                        </tr>
                        </c:forEach>
                        </c:otherwise>
                        </c:choose>
                    </table>
                    	<input type="button" value="선택삭제" class="delete" name="deleteButton" style="cursor:pointer; width:80px"/>
                    	<a href="/Kmarket/admin/register.do"><input type="button" value="상품등록" 
                    	style="float: right; width: 80px; cursor:pointer"/></a>
                    <div class="paging">
                        <span class="prev"> 
                        	<c:if test="${pageGroupStart > 1}">
	                        	<a href="/Kmarket/admin/product/list.do?uid=${sessUser.uid}&pg=${pageGroupStart - 10}&level=${sessUser.level}&text=${text}&search=${search}"><&nbsp;이전</a>
	                        </c:if>
                        </span>
                        <span class="num">
                        	<c:forEach var="i" begin="${pageGroupStart}" end="${pageGroupEnd}">
                            <a href="/Kmarket/admin/product/list.do?uid=${sessUser.uid}&pg=${i}&level=${sessUser.level}&text=${text}&search=${search}" class="num ${currentPage eq i? 'current':'off'}">${i}</a>
                            </c:forEach>
                        </span>
                        <span class="next">
                        	<c:if test="${pageGroupEnd < lastPageNum}">
                            	<a href="/Kmarket/admin/product/list.do?uid=${sessUser.uid}&pg=${pageGroupEnd + 1}&level=${sessUser.level}&text=${text}&search=${search}">다음&nbsp;></a>
                            </c:if>
                        </span>
                    </div>
                </section>
                <p class="ico info">
                    <strong>Tip!</strong>
                    전자상거래 등에서의 상품 등의 정보제공에 관한 고시에 따라 
                    총 34개 상품군에 대해 상품 특성 등을 양식에 따라 입력할 수 
                    있습니다.
                </p>
            </section>
        </main>
<jsp:include page="./_footer.jsp"></jsp:include>