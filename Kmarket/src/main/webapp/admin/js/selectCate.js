$(function(){
$('select[name=prodCate1]').change(function(){
	
	let select = $(this).val();
	
	if(select == '10'){
		$('select[name=prodCate2]').empty();
		
		let tags = "<option value='0'>2차 분류 선택</option>";
		   tags += "<option value='10'>브랜드 여성의류</option>";
		   tags += "<option value='11'>브랜드 남성의류</option>";
		   tags += "<option value='12'>브랜드 진/캐쥬얼</option>";
		   tags += "<option value='13'>브랜드 신발/가방</option>";
		   tags += "<option value='14'>브랜드 쥬얼리/시계</option>";
		   tags += "<option value='15'>브랜드 아웃도어</option>";
		
		$('select[name=prodCate2]').append(tags);
	}else if(select == '11'){
		$('select[name=prodCate2]').empty();
		
		let tags = "<option value='0'>2차 분류 선택</option>";
		   tags += "<option value='10'>여성의류</option>";
		   tags += "<option value='11'>남성의류</option>";
		   tags += "<option value='12'>언더웨어</option>";
		   tags += "<option value='13'>신발</option>";
		   tags += "<option value='14'>가방/잡화</option>";
		   tags += "<option value='15'>쥬얼리/시계</option>";
		   tags += "<option value='16'>화장품/향수</option>";
		   tags += "<option value='17'>바디/헤어</option>";
		
		$('select[name=prodCate2]').append(tags);
		
	}else if(select == '12'){
		$('select[name=prodCate2]').empty();
		
		let tags = "<option value='0'>2차 분류 선택</option>";
		   tags += "<option value='10'>출산/육아</option>";
		   tags += "<option value='11'>장난감/완구</option>";
		   tags += "<option value='12'>유아동 의류</option>";
		   tags += "<option value='13'>유아동 신발/잡화</option>";
		
		$('select[name=prodCate2]').append(tags);
		
	}else if(select == '13'){
		$('select[name=prodCate2]').empty();
		
		let tags = "<option value='0'>2차 분류 선택</option>";
		   tags += "<option value='10'>신선식품</option>";
		   tags += "<option value='11'>가공식품</option>";
		   tags += "<option value='12'>건강식품</option>";
		   tags += "<option value='13'>커피/음료</option>";
		   tags += "<option value='14'>생필품</option>";
		   tags += "<option value='15'>바디/헤어</option>";
		
		$('select[name=prodCate2]').append(tags);
	}else if(select == '14'){
		$('select[name=prodCate2]').empty();
		
		let tags = "<option value='0'>2차 분류 선택</option>";
		   tags += "<option value='10'>가구/DIY</option>";
		   tags += "<option value='11'>침구/커튼</option>";
		   tags += "<option value='12'>조명/인테리어</option>";
		   tags += "<option value='13'>생활용품</option>";
		   tags += "<option value='14'>주방용품</option>";
		   tags += "<option value='15'>문구/사무용품</option>";
		   tags += "<option value='16'>사무기기</option>";
		   tags += "<option value='17'>악기/취미</option>";
		   tags += "<option value='18'>반려동물용품</option>";
		
		$('select[name=prodCate2]').append(tags);
		
	}else if(select == '15'){
		$('select[name=prodCate2]').empty();
		
		let tags = "<option value='0'>2차 분류 선택</option>";
		   tags += "<option value='10'>노트북/PC</option>";
		   tags += "<option value='11'>모니터/프린터</option>";
		   tags += "<option value='12'>PC주변기기</option>";
		   tags += "<option value='13'>모바일/태블릿</option>";
		   tags += "<option value='14'>카메라</option>";
		   tags += "<option value='15'>게임</option>";
		   tags += "<option value='16'>영상가전</option>";
		   tags += "<option value='17'>주방가전</option>";
		   tags += "<option value='18'>계절가전</option>";
		   tags += "<option value='19'>생활/미용가전</option>";
		   tags += "<option value='20'>음향가전</option>";
		   tags += "<option value='21'>건강가전</option>";
		
		$('select[name=prodCate2]').append(tags);
		
	}else if(select == '16'){
		$('select[name=prodCate2]').empty();
		
		let tags = "<option value='0'>2차 분류 선택</option>";
		   tags += "<option value='10'>스포츠의류/운동화</option>";
		   tags += "<option value='11'>휘트니스/수영</option>";
		   tags += "<option value='12'>구기/라켓</option>";
		   tags += "<option value='13'>골프</option>";
		   tags += "<option value='14'>자전거/보드/기타레저</option>";
		   tags += "<option value='15'>캠핑/낚시</option>";
		   tags += "<option value='16'>등산/아웃도어</option>";
		   tags += "<option value='17'>건강/의료용품</option>";
		   tags += "<option value='18'>건강식품</option>";
		   tags += "<option value='19'>렌탈서비스</option>";
		
		$('select[name=prodCate2]').append(tags);
	}else if(select == '17'){
		$('select[name=prodCate2]').empty();
		
		let tags = "<option value='0'>2차 분류 선택</option>";
		   tags += "<option value='10'>자동차용품</option>";
		   tags += "<option value='11'>공구/안전/산업용품</option>";
		
		$('select[name=prodCate2]').append(tags);
	}else if(select == '18'){
		$('select[name=prodCate2]').empty();
		
		let tags = "<option value='0'>2차 분류 선택</option>";
		   tags += "<option value='10'>여행/항공권</option>";
		   tags += "<option value='11'>도서/음반/e교육</option>";
		   tags += "<option value='12'>공연티켓</option>";
		   tags += "<option value='13'>e쿠폰</option>";
		   tags += "<option value='14'>상품권</option>";
		
		$('select[name=prodCate2]').append(tags);
	}
	
});
});