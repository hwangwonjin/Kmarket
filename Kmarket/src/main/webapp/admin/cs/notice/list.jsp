<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/admin/_header.jsp"></jsp:include>
<style>
	#admin-product-list > section > .paging .active {
		background: #333;
	  	color: #fff;
	}
	#admin-product-list .off-screen {
		display: none;
	}
</style>
<script>
$(function(){
	
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
	

	// 카테고리 선택에 따른 테이블 출력
	$('#selectBox').change(function(){
			
		let cateType1 = $('#selectBox option:selected').val();
		
		let jsonData = {
				"pg" : "1",
				"cateType1":cateType1,
				"ajax":"ajax"
		}
		
		$.ajax({
			url: '/Kmarket/admin/cs/notice/select.do', 
			method: 'post',
			data:jsonData,
			dataType:'json',
			success: function(data){
				
				$('.row').remove();	// 테이블 비우기 
				let no = 1;
				
				let totalCount = data.length;
				console.log(totalCount);
				
				for(let vo of data){
					
					let rdate = vo.rdate.substring(2,10);
					
					let rows = "<tr class='row'>";
					rows += "<td><input type='checkbox' name='articleNo' value='"+vo.no+"'></td>";
					rows += "<td>"+totalCount+"</td>";
					rows += "<td>"+vo.cateType1+"</td>";
					rows += "<td><a href='/Kmarket/admin/cs/notice/view.do?cate=notice&cateType1="+vo.cateType1+"&no="+vo.no+"'>["+vo.cateType2+"]  "+vo.title+"</a></td>";
					rows += "<td>"+vo.hit+"</td>";
					rows += "<td>"+rdate+"</td>";
					rows += "<td>";
					rows += "<a href='/Kmarket/admin/cs/notice/delete.do?no="+vo.no+"' id='remove'>[삭제]</a><br>";
					rows += "<a href='/Kmarket/admin/cs/notice/modify.do?cate=notice&cateType1="+vo.cateType1+"&no="+vo.no+"'>[수정]</a><br>";
					rows += "</td>";
					rows += "</tr>"; 
					
					$('#tb').append(rows);
					
					no++;
					totalCount--;
				} 
				
				// paging - 동적생성
				$('.paging > .num').empty();	// 기존 목록 페이지 번호 지우기
				
				let rowTotals = $('.row').length;	// 게시글 총 개수 (33)
				let rowPerPage = 10;				// 한 페이지당 게시글 개수
				let pageTotal = Math.ceil(rowTotals/ rowPerPage); // 페이지 번호 (4)
				let i = 0;
				
				for(i; i<pageTotal; i++){
					$('<a href="#"></a>').attr('rel',i).html(i+1).appendTo('.num');
				}
				
				$('.row').addClass('off-screen').slice(0, rowPerPage).removeClass('off-screen');
				

				// 페이지 번호 클릭 시 
				let pagingLink = $('.num > a');
				pagingLink.on('click', function(e){
					e.preventDefault();
					
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
				
				
				
				/*
				$('.paging > .prev').empty();
				$('.paging > .num').empty();	
				$('.paging > .next').empty();	
				
				// paging - 이전 버튼
				if(data.pageGroupStart > 1){
					
					let pgPrev = "<a href='/Kmarket/admin/cs/notice/select.do?&pg="+(data.pageGroupStart-1)+"&cateType1="+cateType1+"' class='num'>이전</a>";
					
					$('.paging > .prev').append(pgPrev);
				}
				
				// paing - 현재 페이지
				for(let i=data.pageGroupStart; i<=data.pageGroupEnd; i++){
					
					let param1 = "cate=notice&";
					let param2 = "pg="+i+"&";
					let param3 = "cateType1="+cateType1+"&";
					
					let currentPage = "off";
					
					if(data.currentPage == i){
						currentPage = "current";
					}
					
					let pgNum = "<a href='/Kmarket/admin/cs/notice/select.do?"+param1+param2+param3+"' class='num "+currentPage+"'>"+i+"</a>";
					
					$('.paging > .num').append(pgNum);
				}
				
				// paging - 다음 버튼
				if(data.pageGroupEnd < data.lastPageNum){
					
					let pgPrev = "<a href='/Kmarket/admin/cs/notice/select.do?&pg="+(data.pageGroupStart+1)+"&cateType1="+cateType1+"' class='num'>다음</a>";
					
					$('.paging > .next').append(pgPrev);
				}
				*/
			}
		}); 
	});
	
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
			url: '/Kmarket/admin/cs/notice/delete.do',
			method: 'POST',
			traditional: true,
			dataType:'json',
			data: {"chkArr" : chkArr},
			success:function(data){
				console.log("data : "+data.result);	
				if(data.result == 1){
					alert('삭제되었습니다.');
					checkbox.parent().parent().remove(); 
					return true;
				}else{
					//alert('실패하였습니다.');
					swal(
				        "Check!",
				        "동의 체크를 하셔야 합니다.",
				        "warning"
				      )
					return false;
				}
			}
		});
		
	});
	
	/*
	// [관리] - [삭제] 버튼 클릭 시 ( 개별 게시글 삭제 : ajax 수정 예정 )
	$('#remove').click(function(e){
		let isDelete = confirm('정말 삭제하시겠습니까?');
		
		if(isDelete){
			return true;
		}else{
			return false;
		}
	});
	*/
	/*
    // [선택삭제] 버튼 클릭 시 ( 선택 게시글 단독 삭제 ) 
    $('.delete').click(function(e){

		let isDelete = confirm('정말 삭제하시겠습니까?');
		
		if(isDelete){
    	
	    	let uid = $('#uid').val();
	    	let no = $('input:checkbox:checked').val();
			let checkbox = $('input:checkbox:checked');
			
			if(no == null){
				alert('삭제할 게시물을 선택하십시오.')
				return;
			}
			
			$.ajax({
				url : '/Kmarket/admin/cs/notice/delete.do',
				method : 'get',
				data : {"no":no},
				dataType : 'json',
				success : function(data){
					console.log("data :"+ data.result);
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
			return true;
		}else{
			return false;
		}
    	
    });
   */ 
   /*
   //  [선택삭제] 버튼 클릭 시 ( 선택 게시글 다중 삭제 ) 
    
   $('.delete').click(function(){
	   
	   
	   console.log('test3');
	   let chk_arr = [];	
	   
	   let chk = $('input:checkbox:checked').val();
	   let checkbox = $('input:checkbox:checked');
	   if(chk == null){
		   alert('삭제할 게시글을 선택하십시오.');
		   return;
	   }
	   
	   let isDelete = confirm('정말 삭제하시겠습니까?');
	   if(isDelete){
		   
		   console.log('test4');
		   
		   $.ajax({
			   url: '/Kmarket/admin/cs/notice/delete.do',
			   method: 'post',
			   data: {"chk_arr" : JSON.stringify(chk_arr)},
			   dateType: 'json',
			   success:function(date){
				   console.log('test5');
				   
				   if(data.result == 1){
				   		console.log("컨트롤러에서 받은 chk_arr: " + data);
					 	alert('삭제되었습니다.');
						checkbox.parent().parent().remove();
						return true;
					}else{
						alert('실패하였습니다.');
						return false; 
					}
			   }
		   }); 
	  
	   		return true;
	   }else{
		   return false;
	   }
   });
   */
	    
	
});
</script>
<!DOCTYPE html>
            <section id="admin-product-list">
                <nav>
                    <h3>공지사항 목록</h3>
                    <p>
                        HOME > 고객센터 >
                        <strong>공지사항</strong>
                    </p>
                </nav>
                <section>
                    <div>
                        <select name="search" id="selectBox">
                            <option value="">유형선택</option>
                            <!-- <option value="option1">고객서비스</option> -->
                            <option value="고객서비스" <c:if test="${cateType1 eq '고객서비스'}">selected="selected"</c:if>>고객서비스</option>
                            <option value="안전거래" <c:if test="${cateType1 eq '안전거래'}">selected="selected"</c:if>>안전거래</option>
                            <option value="위해상품" <c:if test="${cateType1 eq '위해상품'}">selected="selected"</c:if>>위해상품</option>
                            <option value="이벤트당첨" <c:if test="${cateType1 eq '이벤트당첨'}">selected="selected"</c:if>>이벤트당첨</option>
                        </select>
                        <input type="hidden" value="${cate}"/>
                        <input type="hidden" value="${cateType1}" placeholder="cateType1"/>
                        <input type="hidden" id="uid" value="${sessUser.uid}"/>
                    </div>
                    <table id="tb">
                        <tr>
                            <th><input type="checkbox" name="all" id="allCk"></th>
                            <th>번호</th>
                            <th>유형</th>
                            <th>제목</th>
                            <th>조회</th>
                            <th>날짜</th>
                            <th>관리</th>
                        </tr>
						<c:forEach var="article" items="${articles}">
						<c:set var="i" value="${i+1}"/>
							<tr class="row">
	                        	<td><input type="checkbox" name="articleNo" value="${article.no}"></td>
	                            <td>${pageStartNum - i}</td>
	                            <td>${article.cateType1}</td>
	                            <td><a href="/Kmarket/admin/cs/notice/view.do?cate=notice&cateType1=${article.cateType1}&no=${article.no}">[${article.cateType2}] ${article.title}</a></td>
	                            <td>${article.hit}</td>
	                            <td>${article.rdate.substring(2,10)}</td>
	                            <td>
	                                <a href="/Kmarket/admin/cs/notice/delete.do?no=${article.no}" id="remove">[삭제]</a><br>
	                                <a href="/Kmarket/admin/cs/notice/modify.do?cate=notice&cateType1=${article.cateType1}&no=${article.no}">[수정]</a>
	                            </td>
                        	</tr>
						</c:forEach>
                        
                    </table>
                    <input type="button" class="delete" name="deleteButton" value="선택삭제">
                    <a href="/Kmarket/admin/cs/notice/write.do?cate=notice" class="write">작성하기</a>
                    <div class="paging">
                        <span class="prev">
                            <c:if test="${pageGroupStart gt 1}">
	                            <a href="/Kmarket/admin/cs/notice/list.do?cate=notice&pg=${pageGroupStart-1}" class="prev">&nbsp;이전</a>
	                        </c:if>
                        </span>
                        <span class="num">
                        <c:choose>
                        	<c:when test="${cateType1 eq null && cateType2 eq null}">
                            <c:forEach var="i" begin="${pageGroupStart}" end="${pageGroupEnd}">
	                            <a href="/Kmarket/admin/cs/notice/list.do?pg=${i}&cate=${cate}" class="num ${currentPage eq i? 'active':'off'}">${i}</a>
	                        </c:forEach>
                        	</c:when>
                        	<c:otherwise>
                        		<c:forEach var="i" begin="${pageGroupStart}" end="${pageGroupEnd}">
	                            <a href="/Kmarket/admin/cs/notice/list.do?pg=${i}&cate=${cate}&cateType1=${cateType1}&cateType2=${cateType2}" class="num ${currentPage eq i? 'active':'off'}">${i}</a>
	                       		 </c:forEach>
                        	</c:otherwise>
                        </c:choose>
                        </span>
                        <span class="next">
                            <c:if test="${pageGroupEnd lt lastPageNum}">
	                            <a href="/Kmarket/admin/cs/notice/list.do?cate=notice&pg=${pageGroupEnd+1}" class="next">다음&nbsp;</a>
	                        </c:if>
                        </span>
                    </div>
                </section>
            </section>
        </main>
<jsp:include page="/admin/_footer.jsp"></jsp:include>