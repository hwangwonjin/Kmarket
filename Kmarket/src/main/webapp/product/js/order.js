$.Commas = function (x) { return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ','); }

$(function(){
	let Uid = $('input[name=uid]').val();
		
	if(Uid == ''){
		alert('먼저 로그인 하세요');
		location.href = "/Kmarket/member/login.do";
		return false;
	}		
			
			
	let oriPoint = 0;
	let oriTotal = 0;
	
	$('input[class=addPoint]').click(function(){
		
		if($('input[name=point]').val() == ''){
			alert('포인트를 입력하세요');
			return;
		}
		
		let finalTotal = 0;
		
		// point 수정 시 정상 적용시키기
		if(oriTotal != 0){
			finalTotal = parseInt(oriTotal)+parseInt(oriPoint);
			
			// form으로 보낼 total값 변경
			$('input[name=ordTotPrice]').attr('value', finalTotal);
		}
		
		// total값 가져오기
		let total = $('input[name=ordTotPrice]').val();
		
		// 할인 받을 point값 가져오기
		let point = $('input[name=point]').val();
		oriPoint = point;
		
		// 최종결제 정보 전체주문금액 변경
		$('.order > form > .final > table tr:eq(5) > td:eq(1)').text($.Commas(total));
		
		// 현재 uid가 가지고있는 point값 가져오기 // 259번째 줄 input hidden 추가
		let currentPoint = $('input[name=currentPoint]').val();
		
		// 현재 가지고 있는 point보다 높게 입력시 alert, return;
		if(parseInt(point) > parseInt(currentPoint)){
			//alert('가지고 계신 포인트 보다 많습니다.');
		    	swal(
			        "Check!",
			        "가지고 계신 포인트보다 많습니다.",
			        "warning"
			        )
			return;
		}else if(parseInt(point) < 5000 && parseInt(point) > 0){
			//alert('5000점 이상부터 사용 가능합니다.');
		    	swal(
			        "Check!",
			        "5000점 이상부터 사용 가능합니다.",
			        "warning"
			        )
			return;
		}
		
		// 최종결제 정보 포인트 할인에 가져온 point 값 집어넣기 // 140번째 줄 아래 input hidden 추가
		$('.order > form > .final > table tr:eq(4) > td:eq(1)').text($.Commas(point));
		
		// input에 사용한 point값을 가져온 point 값으로 변경하기
		$('input[name=ordusedPoint]').attr('value', point);
		
		// total에서 할인받은 point값 빼기
		finalTotal = parseInt(total)-parseInt(point);
		oriTotal = finalTotal;
		
		// form으로 보낼 total값 변경
		$('input[name=ordTotPrice]').attr('value', finalTotal);
		
		// 최종결제 정보 전체주문금액 변경
		$('.order > form > .final > table tr:eq(5) > td:eq(1)').text($.Commas(finalTotal));
		
	});
	
	$('.order > form').submit(function(){
		
		let orderer = $('input[name=orderer]').val();
		let hp = $('input[name=hp]').val();
		let receiver = $('input[name=receiver]').val();
		let zip = $('input[name=zip]').val();
		let addr1 = $('input[name=addr1]').val();
		let addr2 = $('input[name=addr2]').val();
		let payment = $('input[name=payment]:checked').val();
		
		console.log(payment);
		
		if(orderer == '' || hp == '' || receiver == '' || zip == '' || addr1 == '' || addr2 == ''){
			//alert('배송정보를 입력하세요');
			swal(
			        "Check!",
			        "배송 정보를 입력하세요.",
			        "warning"
			      )
			return false;
		}else if(payment == null) {
			swal(
			        "Check!",
			        "결제 방법을 선택하세요.",
			        "warning"
			      )
			return false;			
		}
		
		let result = confirm('입력하신 정보가 맞습니까?');
		
		if(result){
			return true;
		}else{
			return false;
		}
		
	});
});