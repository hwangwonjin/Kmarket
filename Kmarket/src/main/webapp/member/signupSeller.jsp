<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="./_header.jsp"></jsp:include>
<script>
	$(function(){
		
		$('.agree').click(function(){
			
			let isCheck1 = $('input[name=agree1]').is(':checked');
			let isCheck2 = $('input[name=agree2]').is(':checked');
			let isCheck3 = $('input[name=agree3]').is(':checked');
			
			if(isCheck1 && isCheck2 && isCheck3){
				return true;
			}else {
				//swal('동의 체크를 하셔야 합니다.');
				//alert('동의 체크를 하셔야 합니다.');
				swal(
			        "Check!",
			        "동의 체크를 하셔야 합니다.",
			        "warning"
			      )
				return false;
			}
		});
	});
</script>  
<main id="member">
    <div class="signup">
        <nav>
            <h1>약관동의</h1>
        </nav>
        <section>
            <h3>
                <span class="essential">(필수)</span>
                케이마켓 이용약관
            </h3>
            <textarea class="terms" readonly>${vo.tax}</textarea>
            <label>
                <input type="checkbox" name="agree1">동의합니다.
            </label>
            <h3>
                <span class="essential">(필수)</span>
                전자금융거래이용약관
            </h3>
            <textarea class="financial" readonly>${vo.finance}</textarea>
            <label>
                <input type="checkbox" name="agree2">동의합니다.
            </label>
            <h3>
                <span class="essential">(필수)</span>
                개인정보 수집동의
            </h3>
            <textarea class="privacy" readonly>${vo.privacy}</textarea>
            <label>
                <input type="checkbox" name="agree3">동의합니다.
            </label>
        </section>
        <section style="display:none;">
            <h3>
                <span class="optional">(선택)</span>
                위치정보 이용약관
            </h3>    
                <textarea class="location" readonly></textarea>
                <label>
                    <input type="checkbox" name="agree4">동의합니다.
                </label>
        </section>
        <div>
            <a href="/Kmarket/member/registerSeller.do">
            	<input type="button" class="agree" value="동의하기">
            </a>
        </div>
    </div>
</main>
<jsp:include page="./_footer.jsp"></jsp:include> 