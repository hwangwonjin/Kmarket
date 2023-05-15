<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/admin/_header.jsp"></jsp:include>
<style>
	#admin-faq > section > .paging .active {
		background: #333;
	  	color: #fff;
	}
	#admin-faq .off-screen {
		display: none;
	}
</style>
<script>
	$(function() {
		
		
		// 글 삭제하기 (단독) 실행됨 삭제X
		/* $('.delete').click(function() {

			let no = $('input:checkbox:checked').val();
			let checkbox = $('input:checkbox:checked');
			
			console.log("no : "+no);
			
			if(no == null){
				alert('선택된 사항이 없습니다.');
				return;
			}
			
			$.ajax({
				url : '/Kmarket/admin/cs/qna/delete.do',
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
						alert('실패하였습니다.');
						return false;
					}
				}
			}); 
			
		}); */
		
		
		// 글 삭제하기 (다중 선택) 실행됨 삭제X
		/*
		$('.delete').click(function(){
			
			let noArr = [];
			let checkbox = $('input:checkbox:checked');
			
			$('input[name=articleNo]:checked').each(function(){
				noArr.push($(this).val());
			});
			console.log(noArr);
			console.log(noArr.length);
			
			
			$.ajax({
				url: '/Kmarket/admin/cs/qna/delete.do',
				method: 'post',
				data: {"noArr":JSON.stringify(noArr)},
				dataType:'json',
				success:function(data){
					console.log("data : "+data.result);
					if(data.result==noArr.length){
						alert('삭제되었습니다.');
						checkbox.parent().parent().remove();
						return true;
					}else{
						alert('실패하였습니다.');
						return false;
					}
					
				}
			});
		}); 
		*/
		
		// 단독삭제, 개별선택삭제, 전체삭제 통합
		let chkNo = document.getElementsByName("articleNo");
		let rowCnt = chkNo.length;
		
		console.log(chkNo.length);		// 페이지 내 게시글 개수 확인
		
		$('input[name=deleteButton]').click(function(){
			
			let list = $('input[name=articleNo]');
			let checkbox = $('input[name=articleNo]:checked');
			
			console.log(checkbox.length);	// 선택된 게시글 개수 확인
			
			if(checkbox.length == 0){
				//alert('삭제할 게시글을 선택하십시오.');
				swal(
			        "Check!",
			        "삭제할 게시글을 선택하십시오.",
			        "warning"
			      )
				return;
			}
			
			let chkArr = new Array();
			
			console.log("list length: "+list.length);	// 페이지 내 게시글 개수 확인2
			
			for(let i=0; i<list.length; i++){	// 페이지 내 게시글 개수동안 체크된 게시글 배열 생성
				if(list[i].checked){
					chkArr.push(list[i].value);
				}
			}
			
			console.log(chkArr);	// ['513', '512']    0:"532"   1:"531"
			
			$.ajax({
				url: '/Kmarket/admin/cs/qna/delete.do',
				method: 'POST',
				traditional: true,
				dataType:'json',
				data: {"chkArr" : chkArr},
				success:function(data){
					console.log("data : "+data.result);	
					if(data.result == 1){
						//alert('삭제되었습니다.');
						swal(
					        "Success!",
					        "삭제되었습니다.",
					      )
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
		
		// 전체 체크박스 체크 여부에 따른 하위 체크박스 상태
		$('input[name=all]').change(function(){
			if($('input[name=all]').is(":checked")){
				$('input[name=articleNo]').prop("checked", true);
			}else{
				$('input[name=articleNo]').prop("checked", false);
			}
		});
		// 하위 체크박스 체크 여부에 따른 전체 체크박스 상태
		// (선택된 하위 체크박스 개수 = 전체 체크박스 개수 -> 전체체크 true)
		$('input[name=articleNo]').change(function(){
			if($('input[name=articleNo]:checked').length == $('input[name=articleNo]')){
				$('input[name=all]').prop("checked", true);
			}else{
				$('input[name=all]').prop("checked", false);
			}
		});
		
		// 카테고리 1차 선택에 따른 2차 선택문 
		$('select[id=selectBox]').change(function() {
			let select = $(this).val();
			
			
			if(select == '회원'){
				$('.choose').empty();
				
				let tags = "<option value='0'>2차 선택</option>";
				 	tags += "<option value='가입'>가입</option>";
				 	tags += "<option value='탈퇴'>탈퇴</option>";
					tags += "<option value='회원정보'>회원정보</option>";
					tags += "<option value='로그인'>로그인</option>";
					
				$('.choose').append(tags);
				
			}else if(select == '쿠폰/이벤트'){
				$('.choose').empty();
				
				let tags = "<option value='0'>2차 선택</option>";
				 	tags += "<option value='쿠폰/할인혜택'>쿠폰/할인혜택</option>";
				 	tags += "<option value='포인트'>포인트</option>";
					tags += "<option value='제휴'>제휴</option>";
					tags += "<option value='이벤트'>이벤트</option>";
					
				$('.choose').append(tags);	
				
			}else if(select == '주문/결제'){
				$('.choose').empty();
				
				let tags = "<option value='0'>2차 선택</option>";
				 	tags += "<option value='상품'>상품</option>";
				 	tags += "<option value='결제'>결제</option>";
					tags += "<option value='구매내역'>구매내역</option>";
					tags += "<option value='영수증/증빙'>영수증/증빙</option>";
					
				$('.choose').append(tags);
			}else if(select == '배송'){
				$('.choose').empty();
				
				let tags = "<option value='0'>2차 선택</option>";
				 	tags += "<option value='배송상태/기간'>배송상태/기간</option>";
				 	tags += "<option value='배송정보확인/변경'>배송정보확인/변경</option>";
					tags += "<option value='해외배송'>해외배송</option>";
					tags += "<option value='당일배송'>당일배송</option>";
					tags += "<option value='해외직구'>해외직구</option>";
					
				$('.choose').append(tags);
				
			}else if(select == '취소/반품/교환'){
				$('.choose').empty();
				
				let tags = "<option value='0'>2차 선택</option>";
				 	tags += "<option value='반품신청/철회'>반품신청/철회</option>";
				 	tags += "<option value='반품정보확인/변경'>반품정보확인/변경</option>";
					tags += "<option value='교환 AS신청/철회'>교환AS신청/철회</option>";
					tags += "<option value='교환정보확인/변경'>교환정보확인/변경</option>";
					tags += "<option value='취소신청/철회'>취소신청/철회</option>";
					tags += "<option value='취소확인/환불정보'>취소확인/환불정보</option>";
					
				$('.choose').append(tags);
				
			}else if(select == '여행/숙박/항공'){
				$('.choose').empty();
				
				let tags = "<option value='0'>2차 선택</option>";
				 	tags += "<option value='여행/숙박'>여행/숙박</option>";
				 	tags += "<option value='항공'>항공</option>";
					
				$('.choose').append(tags);
				
			}else if(select == '안전거래'){
				$('.choose').empty();
				
				let tags = "<option value='0'>2차 선택</option>";
				 	tags += "<option value='서비스 이용규칙 위반'>서비스 이용규칙 위반</option>";
				 	tags += "<option value='지식재산권침해'>지식재산권침해</option>";
					tags += "<option value='법령 및 정책위반 상품'>법령 및 정책위반 상품</option>";
					tags += "<option value='게시물 정책위반'>게시물 정책위반</option>";
					tags += "<option value='직거래/외부거래유도'>직거래/외부거래유도</option>";
					tags += "<option value='표시광고'>표시광고</option>";
					tags += "<option value='청소년 위해상품/이미지'>청소년 위해상품/이미지</option>";
					
				$('.choose').append(tags);
			}
		});
		
		// 카테고리 선택에 따른 테이블 출력 
		$('#selectBox').change(function(){
			$('#selectBox2').change(function(){
				
				let cateType1 = $('#selectBox option:selected').val();
				let cateType2 = $('#selectBox2 option:selected').val();
				console.log("cateType1: " + cateType1); 
				console.log("cateType2: " + cateType2); 
				
				let jsonData = {
						"cateType1":cateType1,
						"cateType2":cateType2,
				}
				console.log("컨트롤러로 보내는 jsonData: "+ Object.values(jsonData));
				
				
				$.ajax({
					url: '/Kmarket/admin/cs/qna/select.do', 
					method: 'POST',
					data:jsonData,
					dataType:'json',
					success: function(data){
						
						$('.row').remove();	// 테이블 비우기 
						
						let totalCount = data.length;
						console.log(totalCount);
						
						for(let vo of data){
							
							let uid = vo.uid.substring(0,3);
							let rdate = vo.rdate.substring(2,10);
							
							let rows = "<tr class='row'>";
							rows += "<td><input type='checkbox' name='articleNo' value='"+vo.no+"'></td>";
							rows += "<td>"+totalCount+"</td>";
							rows += "<td>"+vo.cateType1+"</td>";
							rows += "<td>"+vo.cateType2+"</td>";
							rows += "<td><a href='/Kmarket/admin/cs/qna/reply.do?cate=qna&cateType1="+vo.cateType1+"&cateType2="+vo.cateType2+"&no="+vo.no+"'>"+vo.title+"</a></td>";
							rows += "<td>"+uid+"**</td>";
							rows += "<td>"+rdate+"</td>";
							rows += "<td>"+(vo.replyContent != null? '<p style="color: #35A2EB;">답변완료</p>' : '검토중')+"</td>"; 
							rows += "</tr>"; 
							 
							$('#tb').append(rows);
							totalCount--;
						} 
						
						// paging - 동적생성 
						$('.paging > .num').empty();	// 기존 목록 페이지 번호 지우기
						
						let rowTotals = $('.row').length;	// 게시글 총 개수 (33)
						let rowPerPage = 10;				// 한 페이지당 게시글 개수
						let pageTotal = Math.ceil(rowTotals/ rowPerPage); // 페이지 번호 (4)
						let pageGroupStart = (pageTotal-1)*10+1; //해당 페이지 첫 번째 글 번호 
						let pageGroupEnd = pageTotal *10;	// 해당 페이지 마지막 글 번호 
						
						console.log("rowTotals:"+rowTotals);
						console.log("rowPerPage:"+rowPerPage);
						console.log("pageTotal:"+pageTotal);
						
						
						let i = 0;
						
						if(pageGroupEnd > pageTotal){
							pageGroupEnd = pageTotal;
						}
						
						// 현재 페이지 버튼 
						for(i; i<pageTotal; i++){
							$('<a href="#"></a>').attr('rel',i).html(i+1).appendTo('.num');
						}
						
						
						
						$('.row').addClass('off-screen')
								.slice(0, rowPerPage)
								.removeClass('off-screen');
						$('.num > a:nth-of-type(1)').addClass('active');
						
						// 다음 버튼 
						

						// 페이지 번호 클릭 시 
						let pagingLink = $('.num > a');
						pagingLink.on('click', function(e){
							e.preventDefault();
							
							console.log("pageGroupStart:"+pageGroupStart);
							console.log("pageGroupEnd:"+pageGroupEnd);
							
							$('.num > a').removeClass('active');
							$(this).addClass('active');
								
							let currPage = $(this).attr('rel');
							console.log("currPage: "+currPage);	// 페이지 번호가 1일 때 currPage=0
							
							let startItem = currPage * rowPerPage;	// 시작 행 = 페이지 번호 * 페이지당 행수
							let endItem = startItem + rowPerPage;	// 끝 행 = 시작 행 + 페이지당 행수
							
							$('.row').css('opacity', '0.0')
									.addClass('off-screen')
									.slice(startItem, endItem)
									.removeClass('off-screen')
									.animate({opacity: 1}, 200);
						}); 
					} 
				});
				
			});
		});
	
});
</script>
<!DOCTYPE html>
            <section id="admin-faq">
                <nav>
                    <h3>문의하기 목록</h3>
                    <p>
                        HOME > 고객센터 >
                        <strong>공지사항</strong>
                    </p>
                </nav>
                <section>
                    <div>
                        <select name="search" id="selectBox">
                            <option value="0">1차 선택</option>
                            <option value="회원">회원</option>
                            <option value="쿠폰/이벤트">쿠폰/이벤트</option>
                            <option value="주문/결제">주문/결제</option>
                            <option value="배송">배송</option>
                            <option value="취소/반품/교환">취소/반품/교환</option>
                            <option value="여행/숙박/항공">여행/숙박/항공</option>
                            <option value="안전거래">안전거래</option>
                        </select>
                        <select name="search" id="selectBox2" class="choose">
                            <option value="search1">2차 선택</option>
                        </select>
                         <input type="hidden" id="uid" value="${sessUser.uid}"/>
                    </div>
                    <table id="tb">
                    	<tr>
                            <th><input type="checkbox" name="all"></th>
                            <th>번호</th>
                            <th>1차 유형</th>
                            <th>2차 유형</th>
                            <th>제목</th>
                            <th>작성자</th>
                            <th>작성일</th>
                            <th>상태</th>
                        </tr>
						<c:forEach var="article" items="${articles}">
						<c:set var="i" value="${i+1}"/>
							<tr class="row">
	                        	<td><input type="checkbox" name="articleNo" value="${article.no}"></td>
	                            <td>${pageStartNum - i}</td>
	                            <td>${article.cateType1}</td>
	                            <td>${article.cateType2}</td>
	                            <td><a href="/Kmarket/admin/cs/qna/reply.do?cate=qna&cateType1=${article.cateType1}&cateType2=${article.cateType2}&no=${article.no}">${article.title} </a></td>
	                            <td>${article.uid.substring(0,3)}**</td>
	                            <td>
	                            	<fmt:parseDate value="${article.rdate}" var="time" pattern="yyyy-MM-dd HH:mm:ss"/>
					                <fmt:formatDate value="${time}" pattern="yy.MM.dd"/>
	                            </td>
	                            <td>
	                            	<c:choose>
	                            		<c:when test="${article.replyContent ne null}">
	                            			<p style="color: #35A2EB;">답변완료</p>
	                            		</c:when>
	                            		<c:otherwise>
	                            			검토중
	                            		</c:otherwise>
	                            	</c:choose>
	                            </td>
                        	</tr>
						</c:forEach>
                        
                    </table>
                    <input type="button" class="delete" name="deleteButton" value="선택삭제">
                   		 <div class="paging">
	                        <span class="prev">
	                            <c:if test="${pageGroupStart > 1}">
		                            <a href="/Kmarket/admin/cs/qna/list.do?cate=qna&pg=${pageGroupStart-10}" class="prev">&nbsp;이전</a>
		                        </c:if>
	                        </span>
	                        <span class="num">
	                            <c:forEach var="i" begin="${pageGroupStart}" end="${pageGroupEnd}">
		                            <a href="/Kmarket/admin/cs/qna/list.do?cate=qna&pg=${i}" class="num ${currentPage eq i? 'active':'off'}">${i}</a>
		                        </c:forEach>
	                        </span>
	                        <span class="next">
	                            <c:if test="${pageGroupEnd < lastPageNum}">
		                            <a href="/Kmarket/admin/cs/qna/list.do?cate=qna&pg=${pageGroupStart+10}" class="next">다음&nbsp;</a>
		                        </c:if>
	                        </span>
	                    </div>
                </section>
            </section>
        </main>
<jsp:include page="/admin/_footer.jsp"></jsp:include>