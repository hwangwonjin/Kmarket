$(function(){
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
				tags += "<option value='교환AS신청/철회'>교환AS신청/철회</option>";
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
});