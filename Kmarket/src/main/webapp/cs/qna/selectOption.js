/**
 * 문의하기::글쓰기 문의유형 1차 카테고리 선택 + 2차 카테고리 선택
 */
 
$(function(){
    areaSelectMaker("select[name=type]");
});

var areaSelectMaker = function(target){
    if(target == null || $(target).length == 0){
        console.warn("Unkwon Tag");
        return;
    }

    var area = {
        "회원" :{
            "가입" : [  ],
            "탈퇴" : [  ],
           "회원정보" : [  ],
            "로그인" : [  ]		
        },
        "쿠폰/이벤트" :{
            "쿠폰/할인혜택" : [  ],
            "포인트" : [  ],	
            "제휴" : [  ],	
            "이벤트" : [  ]
        },
        "주문/결제" :{
            "상품" : [  ],
            "결제" : [  ],
            "구매내역" : [  ],
            "영수증/증빙" : [  ]			
        },
        "배송" :{
            "배송상태/기간" : [  ],
            "배송정보확인/변경" : [  ],
            "해외배송" : [  ],	
            "당일배송" : [  ],	
            "해외직구" : [  ]
        },
        "취소/반품/교환" : {
            "반품신청/철회" : [  ],
            "반품정보확인/변경" : [  ],
            "교환 AS신청/철회" : [  ],
            "교환정보확인/변경" : [  ],
            "취소신청/철회" : [  ],	
            "취소확인/환불정보" : [  ]
        },
        "여행/숙박/항공" : {
            "여행/숙박" : [  ],
            "항공" : [  ]		
        },
        "안전거래" : {
            "서비스 이용규칙 위반" : [  ],			
            "지식재산권침해" : [  ],	
            "법령 및 정책위반 상품" : [  ],	
            "게시물 정책위반" : [  ],	
            "직거래/외부거래유도" : [  ],	
            "표시광고" : [  ],	
            "청소년 위해상품/이미지" : [  ]
        }
    };

    for(i=0; i<$(target).length; i++){
        (function(z){
            var a1 = $(target).eq(z);
            var a2 = a1.next();

            //초기화
            init(a1, true);

            //권역 기본 생성
            var areaKeys1 = Object.keys(area);
            areaKeys1.forEach(function(Region){
                a1.append("<option value="+Region+">"+Region+"</option>");
            });

            //변경 이벤트
            $(a1).on("change", function(){
                init($(this), false);
                var Region = $(this).val();
                var keys = Object.keys(area[Region]);
                keys.forEach(function(Do){
                    a2.append("<option value="+Do+">"+Do+"</option>");    
                });
                console.log("Region: " + Region);
                
            });

            
        })(i);        

        function init(t, first){
            first ? t.empty().append("<option value=''>선택</option>") : "";
            t.next().empty().append("<option value=''>선택</option>");
           
        }
    }
}