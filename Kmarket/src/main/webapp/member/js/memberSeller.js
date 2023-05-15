/**
 *  판매자 회원가입
 */
 
 	// 데이터 검증에 사용하는 정규표현식
	let reUid   	= /^[a-z]+[a-z0-9]{5,19}$/g;
	let rePass  	= /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{5,16}$/;
	let reCompany  	= /^[ㄱ-힣]+$/;	
	let reName  	= /^[ㄱ-힣]+$/;
	let reNick  	= /^[a-zA-Zㄱ-힣0-9][a-zA-Zㄱ-힣0-9]*$/;
	let reEmail 	= /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
	let reHp    	= /^01(?:0|1|[6-9])-(?:\d{4})-\d{4}$/;
	let reAuth  	= /^[0-9]+$/;
	let reCorp		= /^(\d{3,3})+[-]+(\d{2,2})+[-]+(\d{5,5})$/;
	let reTel 		= /^\d{2,3}-\d{3,4}-\d{4}$/;
	let reCeo  		= /^[a-zA-Zㄱ-힣0-9][a-zA-Zㄱ-힣0-9]*$/;
	let reOnline 	= /^([ㄱ-힣]{2,2})-(\d{5,5})$/;
	
	
	// 폼 데이터 검증 결과 상태변수
	let isUidok = false;
	let isPassok = false;
	let isCompanyok = false;
	let isNameok = false;
	let isNickok = false;
	let isEmailok = false;
	let isEmailAuthOk = false;
	let isHpok = false;
	let isCorpok = false;
	let isTelok = false;
	let isCeook = false;
	let isZipok	= false;
	let isaddr1ok = false;
	let isaddr2ok = false;
	let isOnlineok = false;
	
	
	$(function(){
		
		//아이디 체크
		$('input[name=km_uid]').keydown(function(){
			isUidok = false;
		});
		
		
		$('input[name=km_uid]').focusout(function(){
			
			let uid = $('input[name=km_uid]').val();
			
			if(isUidok){
				return;
			}
			
			console.log('1');
			
			if(!uid.match(reUid)){
				$('.msgId').hide();
				$('.uidResult').css('color','red').text('유효한아이디가 아닙니다');
				isUidok = false;
				return;
			}
			
			console.log('2');
			
			setTimeout(function(){
				$.ajax({
					url : '/Kmarket/member/checkSellerUid.do',
					method : 'get',
					data : { "uid":uid },
					dataType: 'json',
					success : function(data){
						if(data.result == 0){
							isUidok = true;
							$('.msgId').hide();
							$('.uidResult').css('color','green').text('사용 가능한 아이디 입니다.');

						}else{
							isUidok = false;
							$('.msgId').hide();
							$('.uidResult').css('color','red').text('이미 사용중인 아이디 입니다.');
							
						}
					}
				});
				
			},100);
		});
		
		//비밀번호 체크
		$('input[name=km_pass1], input[name=km_pass2]').focusout(function(){
			
			let pass1 = $('input[name=km_pass1]').val();
			let pass2 = $('input[name=km_pass2]').val();
			 
			 console.log('pass1: ' +pass1);
			 console.log('pass2: ' +pass2);
			 
			if(pass1.match(rePass)){
				
				$('.msgPass1').hide();
				
				if(pass1 == pass2){
					isPassok = true;
					$('.msgPass1').css('color','black');
					$('.msgPass2').hide();
					$('.passResult2').css('color', 'green').text('사용할 수 있는 비밀번호입니다.');
				
				}else{
					isPassok = false;
					$('.passResult2').css('color', 'red').text('비밀번호가 일치하지 않습니다.');	
				}
			}else{
				isPassok = false;
				$('.msgPass1').css('color', 'red');
			}
			
		});
		
		//회사명 확인
		$('input[name=kms_company]').focusout(function(){
			let company = $('input[name=kms_company]').val();
			
			if(company.match(reCompany)){
				isCompanyok = true;
				$('.msgCompany').text('');
			}else{
				isCompanyok = false;
				$('.msgCompany').css('color', 'red').text('유효한 회사명이 아닙니다.');
			}
		
		});
		
		//대표 확인
		$('input[name=kms_ceo]').focusout(function(){
			let ceo = $('input[name=kms_ceo]').val();
			
			if(ceo.match(reName)){
				isCeook = true;
				$('.msgCeo').text('');
			}else{
				isCeook = false;
				$('.msgCeo').css('color', 'red').text('유효한 대표명이 아닙니다.');
			}
			
		});
		
		//사업자등록번호
		$('input[name=kms_corp_reg]').focusout(function(){
			let crop = $('input[name=kms_corp_reg]').val();
			
			if(crop.match(reCorp)){
				isCorpok = true;
				$('.msgCorp').text('');
			}else{
				isCorpok = false;
				$('.msgCorp').css('color', 'red').text('유효한 사업자번호가 아닙니다.');
			}
		
		});
		
		//통신판매업신고번호
		$('input[name=kms_online_reg]').focusout(function(){
			let online = $('input[name=kms_online_reg]').val();
			
			if(online.match(reOnline)){
				isOnlineok = true;
				$('.msgOnline').text('');
			}else{
				isCorpok = false;
				$('.msgOnline').css('color', 'red').text('유효한 통신판매업번호가 아닙니다.');
			}
		
		});
		
		//전화번호
		$('input[name=kms_tel]').focusout(function(){
			let tel = $('input[name=kms_tel]').val();
			
			if(tel.match(reTel)){
				isTelok = true;
				$('.msgTel').text('');
			}else{
				isTelok = false;
				$('.msgTel').css('color', 'red').text('유효한 전화번호가 아닙니다.');
			}
		
		});
		
		
		//회사주소
		
		let zip = $('input[name=kms_zip]').val();
		let zip1 = $('input[name=kms_addr1]').val();
		let zip2 = $('input[name=kms_addr2]').val();
		
		if(zip == '' && zip1 == '' && zip2 == ''){
			console.log('test');
			isZipok = false;
			isaddr1ok = false;
			isaddr2ok = false;
		}else{
			isZipok = true;
			isaddr1ok = true;
			isaddr2ok = true;
		}
		
		//담당자 이름
		$('input[name=km_name]').focusout(function(){
			let name = $('input[name=km_name]').val();
			
			if(name.match(reName)){
				isNameok = true;
				$('.msgName').text('');
			}else{
				isNameok = false;
				$('.msgName').css('color', 'red').text('유효한 이름이 아닙니다.');
			}
		
		});
		
		//담당자 휴대폰
		$('input[name=kms_hp]').focusout(function(){
			let hp = $('input[name=kms_hp]').val();
			
			if(hp.match(reHp)){
				isHpok = true;
				$('.msgHp').text('');
			}else{
				isHpok = false;
				$('.msgHp').css('color', 'red').text('유효한 번호가 아닙니다.');
			}
		
		});
		
		$('.register > form').submit(function(){
			
			//아이디 검증
			if(!isUidok){
				alert('아이디를 확인하십시오');
				return false;
			}
			
			//비밀번호검증
			if(!isPassokok){
				alert('비밀번호를 확인하십시오');
				return false;
			}
			
			//회사명
			if(!isCompanyok){
				alert('회사명을 확인하십시오');
				return false;
			}
			
			//대표자
			if(!isCeook){
				alert('대표자를 확인하십시오');
				return false;
			}
			
			//사업자등록번호
			if(!isCorpok){
				alert('사업자등록번호를 확인하십시오');
				return false;
			}
			
			//통신판매업신고
			if(!isOnlineok){
				alert('통신판매업신고를 확인하십시오');
				return false;
			}
			
			//전화번호
			if(!isTelok){
				alert('전화번호을 확인하십시오');
				return false;
			}
			
			//회사주소
			if(!isZipok){
				alert('주소를 확인하십시오');
				return false;
			}
			
			if(!isaddr1ok){
				alert('주소를 확인하십시오');
				return false;
			}
			
			if(!isaddr2ok){
				alert('주소를 확인하십시오');
				return false;
			}
			
			//담당자
			if(!isNameok){
				alert('담당자를 확인하십시오');
				return false;
			}
			
			//휴대폰
			if(!isHpok){
				alert('휴대폰을 확인하십시오');
				return false;
			}
		});
				
	});