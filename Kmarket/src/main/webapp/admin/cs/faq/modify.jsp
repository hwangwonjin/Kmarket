<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/admin/_header.jsp"></jsp:include>
<script>
	$(function() {
		$('select[id=selectBox]').change(function() {

			let select = $(this).val();
			
			console.log("select : "+select);
			
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
					tags += "<option value='교환AS신청/철회'>교환 AS신청/철회</option>";
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
		
		$('#selectBox').change(function() {
			$('#selectBox2').change(function() {
				
				let cateType1 = $('#selectBox option:selected').val();
				let cateType2 = $('#selectBox2 option:selected').val();
				console.log("cateType1: " + cateType1);
				console.log("cateType2: " + cateType2);
				
			});
			
		});
		
		let p = $('textarea[name=content]').text();
		let rn = p.replaceAll('<br>', '\n');
		$('textarea[name=content]').text(rn);
		
		$('#cs > .faq > .modify > article > form').submit(function() {
			
			let cateType1 = $('#selectBox option:selected').val();
			let cateType2 = $('#selectBox2 option:selected').val();
			console.log("cateType1: " + cateType1);
			console.log("cateType2: " + cateType2);
			
			if(cateType1 == 0 || cateType2 == 0){
				//alert('카테고리를 선택하세요');
				swal(
			        "Check!",
			        "카테고리를 선택하세요.",
			        "warning"
			      ) 
				return false;
			}
			console.log("카테 오류");
			
			
			// 로그인이 풀렸을 경우
			if(Uid == ''){
				//alert('다시 로그인해주세요.');
				swal(
			        "Check!",
			        "다시 로그인해주세요.",
			        "warning"
			      ) 
				location.href = "/Kmarket/member/login.do";
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
	                <section class="modify">
						<article>
	                        <form action="/Kmarket/admin/cs/faq/modify.do?cate=faq" method="post">
	                        <input type="hidden" name="uid" value="${sessUser.uid}">
	                        <input type="hidden" name="cate" value="${cate}">
	                        <input type="hidden" name="no" value="${no}">
	                            <table>
	                                <tbody>
	                                    <tr>
	                                        <td>유형</td>
	                                        <td>
	                                            <select name="cateType1" id="selectBox">
	                                                <option value="0">1차 선택</option>
						                            <option value="회원">회원</option>
						                            <option value="쿠폰/이벤트">쿠폰/이벤트</option>
						                            <option value="주문/결제">주문/결제</option>
						                            <option value="배송">배송</option>
						                            <option value="취소/반품/교환">취소/반품/교환</option>
						                            <option value="여행/숙박/항공">여행/숙박/항공</option>
						                            <option value="안전거래">안전거래</option>
	                                            </select>
												<select name="cateType2" id="selectBox2" class="choose">
	                                                <option value="0">2차 선택</option>
	                                            </select>
	                                        </td>
	                                    </tr>
	                                    <tr>
	                                        <td>제목</td>
	                                        <td>
	                                            <input type="text" name="title" value="${vo.title}">
	                                        </td>
	                                    </tr>
	                                    <tr>
	                                        <td>내용</td>
	                                        <td>
	                                            <textarea name="content">${vo.content}</textarea>
	                                        </td>
	                                    </tr>
	                                </tbody>
	                            </table>
	                             <div>
	                                <a href="/Kmarket/admin/cs/faq/list.do?cate=faq" class="btnList">취소하기</a>
	                                <input type="submit" class="btnModify" value="등록하기">
	                            </div>
	                        </form>
	                    </article>
	                </section>
                </div>
            </section>
        </main>
<jsp:include page="/admin/_footer.jsp"></jsp:include>