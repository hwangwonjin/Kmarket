$.Commas = function (x) { return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ','); }

$(function(){
		let Uid = $('input[class=uid]').val();
		
		if(Uid == ''){
			alert('먼저 로그인 하세요');
			location.href = "/Kmarket/member/login.do";
			return false;
		}
	
		let count = 0;
		let costPrice = 0;
		let totalSalePrice = 0;
		let totalDelivery = 0;
		let totalPoint = 0;
		let totalPrice = 0;
		let idx = 0;
		
		let totalNo = new Array();
		
		// 선택 삭제 클릭
		$('.del').click(function() {
			
		let cartNo = $('input:checkbox:checked').val();
		let checkbox = $('input:checkbox:checked');
		
		if(cartNo == null || totalNo.length == 0){
			//alert('선택된 상품이 없습니다.');
			swal(
			        "Check!",
			        "선택된 상품이 없습니다.",
			        "warning"
			      )
			return;
		}
		
		$.ajax({
			url : '/Kmarket/product/deleteCart.do',
			method : 'get',
			data : {"totalNo": JSON.stringify(totalNo)},
			dataType : 'json',
			success : function(data) {
				
				// data.result는 지운 갯수, tatalNo.length 랑 같음
				// 이 부분은 임시로 만든 방법이고 더 좋은 방법이 있으면 바꿔야할듯
				if(data.result == totalNo.length){
					alert('삭제되었습니다.');
					
					checkbox.parent().parent().remove();
					
					/*
					count -= 1;
					
					let price = $(this).next().val();
					costPrice -= parseInt(price)*count;
					
					let sellPrice = $(this).next().next().val();
					totalSalePrice -= parseInt(sellPrice);
					
					let delivery = $(this).next().next().next().val();
					totalDelivery -= parseInt(delivery);
					
					let point = $(this).next().next().next().next().val();
					totalPoint -= parseInt(point);
					
					let total = $(this).next().next().next().next().next().val();
					totalPrice -= parseInt(total);
					$('.productCount_span').text(count.toLocaleString());
					$('.costPrice_span').text(costPrice*count);
					$('.totalDelivery_span').text(totalDelivery);
					$('.totalPoint_span').text(totalPoint);
					$('.totalPrice_span').text(totalPrice);
					$('.totalSale_span').text(totalSalePrice);
					*/
					$('.productCount_span').text('0');
					$('.costPrice_span').text('0');
					$('.totalDelivery_span').text('0');
					$('.totalPoint_span').text('0');
					$('.totalPrice_span').text('0');
					$('.totalSale_span').text('0');
					
					
					if(cartNo == "on"){
						
						let table = "<tr>";
						   table += "<th><input type='checkbox' name='all' class='chk'></th>";
						   table += "<th>상품명</th>";
						   table += "<th>총수량</th>";
						   table += "<th>판매가</th>";
						   table += "<th>할인</th>";
						   table += "<th>포인트</th>";
						   table += "<th>배송비</th>";
						   table += "<th>소계</th>";
						   table += "</tr>";
						   table += "<tr class='empty'><td colspan='7'>장바구니에 상품이 없습니다.</td></tr>";
						
						$('.cart > form > table').append(table);
					}
					
					totalNo = [];
					return true;
				}else {
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
			
		// 전체 선택 체크박스
		$(document).on('click','.chk',function(){
			if($('input[name=all]').is(':checked')){
				$('input[name=cartNo]').prop('checked', true);
				
				totalNo = [];
				$('input[name=cartNo]').each(function(){
					totalNo.push($(this).val());
				});
				
				console.log(totalNo);
				
				let uid = $(this).next().val();
				
				$.ajax({
					url:'/Kmarket/product/cart.do',
					method:'post',
					data: { "uid":uid },
					dataType:"json",
					success: function(data){
						
						count = parseInt(data.totalCount);
						costPrice = parseInt(data.costPrice);
						totalSalePrice = data.totalSalePrice;
						totalDelivery = data.totalDelivery;
						totalPoint = data.totalPoint;
						totalPrice = data.totalPrice;
						
						$('.productCount_span').text($.Commas(count));
						$('.costPrice_span').text($.Commas(costPrice));
						$('.totalDelivery_span').text($.Commas(totalDelivery));
						$('.totalPoint_span').text($.Commas(totalPoint));
						$('.totalPrice_span').text($.Commas(totalPrice));
						$('.totalSale_span').text($.Commas(totalSalePrice));
					}
				});
				
			}else{
				$('input[name=cartNo]').prop('checked', false);
				
				count = 0; 
				costPrice = 0;				
				totalSalePrice = 0;
				totalDelivery = 0;
				totalPoint = 0;
				totalPrice = 0;
				
				$('.productCount_span').text(count);
				$('.costPrice_span').text(costPrice);
				$('.totalDelivery_span').text(totalDelivery);
				$('.totalPoint_span').text(totalPoint);
				$('.totalPrice_span').text(totalPrice);
				$('.totalSale_span').text(totalSalePrice);
				
				totalNo = [];
				
				console.log(totalNo);
			}
		});
	
		
		// 장바구니 품목 당 체크박스
		$('input[name=cartNo]').change(function(){
			if($(this).prop('checked')){
				
				count += 1;
				let cartNo = $(this).val();
				
				let price = $(this).next().val();
				costPrice += parseInt(price);
				
				let sellPrice = $(this).next().next().val();
				totalSalePrice += parseInt(sellPrice);
				
				let delivery = $(this).next().next().next().val();
				totalDelivery += parseInt(delivery);
				
				let point = $(this).next().next().next().next().val();
				totalPoint += parseInt(point);
				
				let total = $(this).next().next().next().next().next().val();
				totalPrice += parseInt(total);
				
				$('.productCount_span').text($.Commas(count));
				$('.costPrice_span').text($.Commas(costPrice));
				$('.totalDelivery_span').text($.Commas(totalDelivery));
				$('.totalPoint_span').text($.Commas(totalPoint));
				$('.totalPrice_span').text($.Commas(totalPrice));
				$('.totalSale_span').text($.Commas(totalSalePrice));
				
				totalNo.push(cartNo);
				console.log(totalNo);
			}else {

				count -= 1;
				let cartNo = $(this).val();
				
				let price = $(this).next().val();
				costPrice -= parseInt(price);
				
				let sellPrice = $(this).next().next().val();
				totalSalePrice -= parseInt(sellPrice);
				
				let delivery = $(this).next().next().next().val();
				totalDelivery -= parseInt(delivery);
				
				let point = $(this).next().next().next().next().val();
				totalPoint -= parseInt(point);
				
				let total = $(this).next().next().next().next().next().val();
				totalPrice -= parseInt(total);
				
				$('.productCount_span').text($.Commas(count));
				$('.costPrice_span').text($.Commas(costPrice));
				$('.totalDelivery_span').text($.Commas(totalDelivery));
				$('.totalPoint_span').text($.Commas(totalPoint));
				$('.totalPrice_span').text($.Commas(totalPrice));
				$('.totalSale_span').text($.Commas(totalSalePrice));
				
				idx = totalNo.indexOf(cartNo);
				
				totalNo.splice(idx, 1);
				
				console.log(totalNo);
				
				// count = 0 일때 전체 체크박스 해제
				if(count == 0){
					$('input[name=all]').prop('checked',false);
				}
			}
		});
		
		$('.cart > form').submit(function(){
			
			// 로그인이 풀렸을 경우
			if(Uid == ''){
				alert('다시 로그인해주세요.');
				location.href = "/Kmarket/member/login.do";
				return false;
			}
			
			let check = totalNo.length;
			
			console.log(check);
			
			if(totalNo == 0){
				//alert('상품을 선택하세요');
				swal(
			        "Check!",
			        "상품을 선택하세요.",
			        "warning"
			      )
				return false;
			}
			
			let result = confirm('선택하신 상품으로 주문 하시겠습니까?');
			
			if(result){
				return true;
			}else{
				return false;
			}
			
		});
})