<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../_header.jsp"></jsp:include>
<jsp:useBean id="now" class="java.util.Date"></jsp:useBean>
<style>
	#product > .view > .review > .paging .active {
		background: #333;
	  	color: #fff;
	}
	#product .off-screen {
		display: none;
	}
</style>
<script>

	$.Commas = function (x) { return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ','); }

	$(function() {
		
		
		let num = 1;
		
		$('button[class=increase]').click(function(){
			num++;
			$('input[name=num]').val(num);
			
			let totalPrice = ${product.sellPrice}*(num);
			
			$('.totalPrice').text($.Commas(totalPrice));
		});
		
		$('button[class=decrease]').click(function(){
			if(num == 1){
				return;
			}
			num--;
			$('input[name=num]').val(num);
			
			let totalPrice = ${product.sellPrice}*(num);
			
			$('.totalPrice').text($.Commas(totalPrice));
		});
		
		$('.cart').click(function() {
			
			let prodNo = $(this).attr('data-no');
			let uid = $(this).attr('data-uid');
			let count = $('input[name=num]').val();			
			
			if(uid == ''){
				//alert('비회원은 이용할 수 없습니다.');
				swal(
			        "Check!",
			        "비회원은 이용할 수 없습니다.",
			        "warning"
			      )
				return false;
			}else {
				$.ajax({
					url : '/Kmarket/product/updateCart.do',
					method : 'get',
					data : {"prodNo":prodNo, "uid":uid, "count":count },
					dataType : 'json',
					success : function(data) {
						if(data.result == 1){
							let message = confirm('상품이 장바구니에 추가되었습니다. 확인하시겠습니까?');
							if(message){
								location.href = "/Kmarket/product/cart.do?uid="+uid;
							}
						}else{
							//alert('장바구니에 담지 못했습니다.');
							swal(
						        "Check!",
						        "장바구니에 담지 못했습니다.",
						        "warning"
						      )
						}
					}
				});
			}
		});
		
		$('.order').click(function(){
			
			let uid = $(this).attr('data-uid');
			
			if(uid == ''){
				//alert('비회원은 구입할 수 없습니다.');
				swal(
			        "Check!",
			        "비회원은 구입할 수 없습니다.",
			        "warning"
			      )
				return false;
			}else {
				let prodNo = $(this).attr('data-no');
				let uid = $(this).attr('data-uid');
				let count = $('input[name=num]').val();
				
				location.href = "/Kmarket/product/order.do?prodNo="+prodNo+"&count="+count;	
			}
		});
		
		// 현재날짜
		let today = new Date();
		
		// 현재 년도
		let year = today.getFullYear();
		
		// 현재 달
		let month = today.getMonth()+1;
		
		// 3일 뒤 날짜 
		let date = today.getDate()+3;
		
		// 3일 뒤 요일
		let day = today.getDay()+3;
		let message;
		
		// 현재 달의 마지막 일
		let lastDay = new Date(today.getFullYear(), today.getMonth()+1, 0);
		
		// 3일 뒤 날짜가 현재 달 마지막 일보다 크면
		if(date > lastDay.getDate()){
			
			// 넘어가는 달 날짜는 = 3일 뒤 날짜 - 현재 달의 마지막 날짜
			// ex) 12월 -> 1월   =   33 - 31 = 2 => 1월 2일
			let nextDate = date-lastDay.getDate();
			
			// day는 연속성을 가짐으로 따로 건드리지않음. date는 30, 31, 28 불연속성이라 보정해줘야함
			
			// 현재 달이 12월이면
			if(today.getMonth()+1 == 12){
				let newDay = new Date(2023, 0);
				month = newDay.getMonth()+1;
				date = nextDate;
				
			// 12월이 아닌 달이면 ex)1월->2월, 2월->3월
			}else{
				let newDay = new Date(today.getFullYear(), today.getMonth()+2);
				month = newDay.getMonth()+1;
				date = nextDate;
			}
		}
		
		if(day == 1 || day == 8){
			message = '월';
		}else if(day == 2 || day == 9){
			message = '화';
		}else if(day == 3 || day == 10){
			message = '수';
		}else if(day == 4){
			message = '목';
		}else if(day == 5){
			message = '금';
		}else if(day == 6){
			message = '토';
		}else if(day == 0 || day == 7){
			message = '일';
		}
		
		
		$('.arrival').text('모레('+message+') '+month+'/'+date+" 도착예정");
    
		$('#scrollReview').click(function(){
			const offset = $(".review").offset();
        	$('html, body').animate({scrollTop: offset.top}, 500);
		});
		
		// review 항목 ajax 처리
		
		
		let prodNo = $('input[class=cart]').attr('data-no');
		
		$.ajax({
			url: '/Kmarket/product/view.do', 
			method: 'post',
			data: { "prodNo":prodNo },
			dataType:'json',
			success: function(data){
				
				if(data != ''){
				
					$('.review').empty();	// 테이블 비우기
					
					let rows = "<nav><h1>상품리뷰</h1></nav>";
					
					for(let vo of data){
						
						let rdate = vo.rdate.substring(2,10);
						
						rows += "<ul class='list'><li>";
						rows += "<div>";
						rows += "<h5 class='rating star"+vo.rating+"'>상품평</h5>";
						rows += "<span>"+vo.uid.substring(0,3)+"***  "+rdate+"</span>";
						rows += "</div>";
						rows += "<h3>"+vo.prodName+"</h3>";
						rows += "<p>"+vo.content+"</p>";
						rows += "</li></ul>"; 
					}
					
					rows += "<div class='paging'>";
					rows += "<span class='prev'></span>";
					rows += "<span class='num'></span>";
					rows += "<span class='next'></span>";
					rows += "</div>";
					
					$('.review').append(rows);
					
					// paging - 동적생성
					$('.paging > .num').empty();	// 기존 목록 페이지 번호 지우기
					
					let rowTotals = $('.list').length;	// 게시글 총 개수 (33)
					let rowPerPage = 5;				// 한 페이지당 게시글 개수
					let pageTotal = Math.ceil(rowTotals/ rowPerPage); // 페이지 번호 (4)
					let i = 0;
					
					for(i; i<pageTotal; i++){
						$('<a href="#"></a>').attr('rel',i).html(i+1).appendTo('.num');
					}
					
					$('.list').addClass('off-screen').slice(0, rowPerPage).removeClass('off-screen');
					
	
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
						
						$('.list').css('opacity', '0.0')
								.addClass('off-screen')
								.slice(startItem, endItem)
								.removeClass('off-screen')
								.animate({opacity: 1}, 200);
					}); 
				}else {
					
				}
			}
		}); 
		
		
	});
	
</script>
            </aside>
            <section class="view">
                <nav>
                    <h1>상품보기</h1>
                    <p>
                        HOME >
                        <span>${product.c1Name}</span>
                         > 
                        <strong>${product.c2Name}</strong>
                    </p>
                </nav>
                <article class="info">
                    <div class="image">
                        <img src="/home/prodImg/${product.thumb2}" alt="상품이미지thumb2">
                    </div>
                    <div class="summary">
                        <nav>
                            <h1>${product.company}</h1>
                            <h2>
                                상품번호 : 
                                <span>${product.prodNo}</span>
                            </h2>
                        </nav>
                        <nav>
                            <h3>${product.prodName}</h3>
                            <p>${product.descript}</p>
                            <h5 class="rating star4">
                                <a href="#" id="scrollReview">상품평보기</a>
                            </h5>
                        </nav>
                        <nav>
                        	<c:choose>
                        	<c:when test="${product.discount ne 0}">
                            <div class="org_price">
                                <del><fmt:formatNumber value="${product.price}" pattern="#,###" /></del>
                                <span>${product.discount}%</span>
                            </div>
                            <div class="dis_price">
                                <ins>
                                	<fmt:formatNumber value="${product.sellPrice}" pattern="#,###" />
                                </ins>
                            </div>
                            </c:when>
                            <c:otherwise>
                            <div class="dis_price">
                                <ins>
                               		<fmt:formatNumber value="${product.sellPrice}" pattern="#,###" />
                                </ins>
                            </div>
                            </c:otherwise>
                            </c:choose>
                        </nav>
                        <nav>
                        	<c:choose>
                        	<c:when test="${product.delivery eq 0}">
                            <span class="freeDelivery">무료배송</span>
                            </c:when>
                            <c:otherwise>
                            <span class="delivery">배송비<fmt:formatNumber value="${product.delivery}" pattern="#,###" />원</span>
                            </c:otherwise>
                            </c:choose>
                            
                            <span class="arrival">
                            <fmt:formatDate value="${now}"/>
                            </span>
                            <span class="desc">본 상품은 국내배송만 가능합니다.</span>
                        </nav>
                        <nav>
                            <span class="card cardfree">
                                <i>아이콘</i>무이자할부
                            </span>
                            <span class="card cardadd">
                                <i>아이콘</i>&nbsp&nbsp카드추가혜택
                            </span>
                        </nav>
                        <nav>
                            <span class="origin">원산지-상세설명 참조</span>
                        </nav>
                        <img src="../img/vip_plcc_banner.png" alt="적립!" class="banner">
                        <div class="count">
                        	<span class="count_updown" style="font-size: 16px;">수량</span>
                            <button class="decrease">-</button>
                            <input type="text" name="num" value="1" readonly>
                            <button class="increase">+</button>
                        </div>
                        <div class="total">
                            <span class="totalPrice"><fmt:formatNumber value="${product.sellPrice}" pattern="#,###" /></span>
                            <em>총 상품금액</em>
                        </div>
                        <div class="button">
                            <input type="button" class="cart" data-no="${product.prodNo}" data-uid="${sessUser.uid}" value="장바구니">
                            <input type="button" class="order" data-no="${product.prodNo}" data-uid="${sessUser.uid}" value="구매하기">
                        </div>
                    </div>
                </article>
                <article class="detail">
                    <nav><h1>상품정보</h1></nav>
                    <img src="/home/prodImg/${product.thumb3}" alt="thumb3">
                    <img src="/home/prodImg/${product.thumb3}" alt="detail">
                    <img src="/home/prodImg/${product.thumb3}" alt="detail">
                </article>
                <article class="notice">
                    <nav>
                        <h1>상품 정보 제공 고시</h1>
                        <p>[전자상거래에 관한 상품정보 제공에 관한 고시] 항목에 의거 등록된 정보입니다.</p>
                    </nav>
                    <table>
                        <tr>
                            <td>상품번호</td>
                            <td>${product.prodNo}</td>
                        </tr>
                        <tr>
                            <td>상품상태</td>
                            <td>${product.status}</td>
                        </tr>
                        <tr>
                            <td>부가세 면세여부</td>
                            <td>${product.duty}</td>
                        </tr>
                        <tr>
                            <td>영수증발행</td>
                            <td>${product.receipt}</td>
                        </tr>
                        <tr>
                            <td>사업자구분</td>
                            <td>${product.bizType}</td>
                        </tr>
                        <tr>
                            <td>브랜드</td>
                            <td>${product.company}</td>
                        </tr>
                        <tr>
                            <td>원산지</td>
                            <td>${product.origin}</td>
                        </tr>
                    </table>
                    <table>
                        <tr>
                            <td>제품소재</td>
                            <td>상세정보 직접입력</td>
                        </tr>
                        <tr>
                            <td>색상</td>
                            <td>상세정보 직접입력</td>
                        </tr>
                        <tr>
                            <td>치수</td>
                            <td>상세정보 직접입력</td>
                        </tr>
                        <tr>
                            <td>제소사/수입국</td>
                            <td>상세정보 직접입력</td>
                        </tr>
                        <tr>
                            <td>제조국</td>
                            <td>상세정보 직접입력</td>
                        </tr>
                        <tr>
                            <td>취급시 주의사항</td>
                            <td>상세정보 직접입력</td>
                        </tr>
                        <tr>
                            <td>제조연월</td>
                            <td>상세정보 직접입력</td>
                        </tr>
                        <tr>
                            <td>품질보증기준</td>
                            <td>상세정보 직접입력</td>
                        </tr>
                        <tr>
                            <td>A/S 책임자와 전화번호</td>
                            <td>상세정보 직접입력</td>
                        </tr>
                        <tr>
                            <td>주문후 예상 배송기간</td>
                            <td>상세정보 직접입력</td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                구매, 교환, 반품, 배송, 설치 등과 관련하여 추가비용, 제한조건 등의 특이사항이 있는 경우
                            </td>
                        </tr>
                    </table>
                    <p class="notice">
                        소비자가 전자상거래등에서 소비자 보호에 관한 법률 제 17조 제1항 또는 제3항에 따라 청약철회를 하고
                        동법 제 18조 제1항 에 따라 청약철회한 물품을 판매자에게 반환하였음에도 불구 하고 결제 대금의
                        환급이 3영업일을 넘게 지연된 경우, 소비자 는 전자상거래등에서 소비자보호에 관한 법률 제18조
                        제2항 및 동법 시행령 제21조 2에 따라 지연일수에 대하여 전상법 시행령으로 정하는 이율을 곱하여
                        산정한 지연이자(“지연배상금”)를 신청할 수 있습니다. 아울러, 교환∙반품∙보증 및 결제대금의
                        환급신청은 [나의쇼핑정보]에서 하실 수 있으며, 자세한 문의는 개별 판매자에게 연락하여 주시기 바랍니다.
                    </p>
                </article>
                <article class="review">
                    <nav><h1>상품리뷰</h1></nav>
                    <h4 style="font-size: 19px; font-weight: bold; text-align: center; display: blocked; height: 100px; line-height: 100px;">등록된 리뷰가 없습니다.</h4>
                </article>
            </section>
        </main>
<jsp:include page="../_footer.jsp"></jsp:include>