package kr.co.kmarket.db;

public class CsSql {

	/*** cs - index ***/
	// notice + qna 최신글 리스트 5건 출력하기 
	public static final String SELECT_LATESTS = "(SELECT `no`, `cate`, `cateType1`, `cateType2`, `title`, `uid`, `rdate` FROM `cs_article` WHERE `cate`=? ORDER BY `no` DESC LIMIT 5) "
											  + " UNION "
											  + " (SELECT `no`, `cate`, `cateType1`, `cateType2`, `title`, `uid`, `rdate` FROM `cs_article` WHERE `cate`=? ORDER BY `no` DESC LIMIT 5) " 
											  + " UNION "
											  + " (SELECT `no`, `cate`, `cateType1`, `cateType2`, `title`, `uid`, `rdate` FROM `cs_article` WHERE `cate`=? ORDER BY `no` DESC LIMIT 5)";

	// id 마스킹 처리 위 SQL문에 추가할 것
	// select replace(uid, SUBSTRING(uid, 4), '**') uid from `km_member`
	// 날짜 변경할 것
	// select REPLACE(SUBSTRING(rdate, 3, 8), "-", ".") from `km_member`
	
	/*** cs - qna ***/
	// 문의글 작성하기
	public static final String INSERT_ARTICLE = "INSERT INTO `cs_article` SET "
											  + " `cate`=?, "
											  + " `cateType1`=?, "
											  + " `cateType2`=?, "
											  + " `title`=?, "
											  + " `content`=?, "
											  + " `uid`=?, "
											  + " `regip`=?, "
											  + " `rdate`=NOW()";
	public static final String SELECT_MAX_NO = "SELECT MAX(`no`) FROM `cs_article`";
	
	/*** cs - list ***/
	// 전체 게시물 개수 
	public static final String SELECT_COUNT_TOTAL = "SELECT COUNT(`no`) FROM `cs_article` WHERE `cate`=?";
	
	// 관리자 - 고객센터 카테고리 유형별 게시글 개수 
	public static final String SELECT_COUNT_TOTAL_WITH_CATE2 = "select count(`no`) from `cs_article` where `cate`='qna' and `cateType1`=? and `cateType2`=?";
	// 페이지 글 가져오기
	public static final String SELECT_ARTICLES = "SELECT * FROM `cs_article` WHERE `parent`=0 AND `cate`=? AND `cateType1`=? ORDER BY `no` DESC LIMIT ?,10";
	// 공지사항 전체글 보기
	public static final String SELECT_WHOLE_ARTICLES = "SELECT * FROM `cs_article` WHERE `parent`=0 AND `cate`='notice' ORDER BY `no` DESC LIMIT ?,10";
	
	
	
	/*** cs::faq list ***/
	/*
	public static final String SELECT_FAQ_ARTICLES = "(SELECT * FROM `cs_article` WHERE `cate`='faq' AND `cateType1`=? ORDER BY `no` DESC LIMIT 10) "
												  + " UNION "
												  + " (SELECT * FROM `cs_article` WHERE `cate`='faq' AND `cateType1`=? ORDER BY `no` DESC LIMIT 10) " 
												  + " UNION "
												  + " (SELECT * FROM `cs_article` WHERE `cate`='faq' AND `cateType1`=? ORDER BY `no` DESC LIMIT 10) "
												  + " UNION "
												  + " (SELECT * FROM `cs_article` WHERE `cate`='faq' AND `cateType1`=? ORDER BY `no` DESC LIMIT 10)";
	*/									
	
	//public static final String SELECT_FAQ_ARTICLES = "select * from `cs_article` where `cate`='faq' and `cateType1`=? order by `cateType2`, `rdate` desc";
	public static final String SELECT_FAQ_ARTICLES = "SELECT * FROM `cs_article` WHERE `parent`=0 AND `cate`=? AND `cateType1`=? ORDER BY `cateType2`, `rdate` DESC";
	
	
	/*** cs - view ***/
	public static final String SELECT_ARTICLE = "SELECT * FROM `cs_article` WHERE `no`=?";
	
	/*** admin - cs - index - main  ***/
	public static final String SELECT_ADMIN_MAIN = "SELECT COUNT(`ordNo`), SUM(`ordPrice`), (SELECT COUNT(`uid`) FROM `km_member`), (SELECT COUNT(`prodNo`) FROM `km_product`) FROM `km_product_order`";
	
	public static final String SELECT_ADMIN_MAIN_USER = "SELECT COUNT(`ordNo`), SUM(`ordPrice`) FROM `km_product_order_item` WHERE uid=``";
	
	
	/*** admin - cs - index - qna, notice ***/
	public static final String SELECT_QNA_NOTICE = "SELECT * FROM `cs_article` WHERE `cate`='notice' OR `cate`='qna'";
	
	/*** admiin - cs - notice - modify ***/
	public static final String UPDATE_ARTICLE = "UPDATE `cs_article` SET "
											  + " `cate`=?, "
											  + " `cateType1`=?, "
											  + " `cateType2`=?, "
											  + " `title`=?, "
											  + " `content`=?, "
											  + " `rdate`=NOW()"
											  + " WHERE `no`=?";
	
	
	/*** admin - cs - qna - 답변 ***/
	public static final String UPDATE_REPLY = "UPDATE `cs_article` SET "
											+ " `replyContent`=? "
											+ " WHERE `no`=?";
	
	
	/*** 글 삭제 ***/
	public static final String DELETE_ARTICLE = "DELETE FROM `cs_article` WHERE `no`=?";
											  
			
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*** cs - faq ***/
	public static final String SELECT_WHOLE_FAQ_ARTICLES = "SELECT * FROM `cs_article` WHERE `parent`=0 AND `cate`='faq' ORDER BY `no` DESC LIMIT ?,10";
	/*** cs - qna ***/
	public static final String SELECT_WHOLE_QNA_ARTICLES = "SELECT * FROM `cs_article` WHERE `parent`=0 AND `cate`='qna' ORDER BY `no` DESC LIMIT ?,10";
	/*** cs - 조회수 up  ***/
	public static final String UPDATE_HIT = "UPDATE `cs_article` SET `hit`=`hit`+1 WHERE `no`=?";
	// 문의하기 유형1 글 가져오기
	public static final String SELECT_CATETYPE1 = "SELECT * FROM `cs_article` WHERE `cate`='qna' and `cateType1`=?";
	// 문의하기 유형2 글 가져오기
	public static final String SELECT_CATETYPE2 = "SELECT * FROM `cs_article` WHERE `cate`='qna' and `cateType1`=? and `cateType2`=? ORDER BY `no` DESC LIMIT ?,10";
	
	// 문의하기 유형2 글 가져오기 
	public static final String SELECT_CATETYPE_LIST = "SELECT * FROM `cs_article` WHERE `cate`='qna' and `cateType1`=? and `cateType2`=? ORDER BY `no` DESC";
	
	//자주묻는 질문 유형2 글 가져오기 
	public static final String SELECT_FAQ_CATETYPE2="SELECT * FROM `cs_article` WHERE `cate`='faq' and `cateType1`=? and `cateType2`=? ORDER BY `no` DESC LIMIT ?,10";
	
	//자주묻는 질문 유형2 글 가져오기
	public static final String SELECT_FAQ_CATETYPE_LIST = "SELECT * FROM `cs_article` WHERE `cate`='faq' and `cateType1`=? and `cateType2`=? ORDER BY `no` DESC";
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// 공지사항 카데고리 별 글 개수
	public static final String COUNT_NOTICE_ARTICLE = "SELECT COUNT(`no`) FROM `cs_article` WHERE `cate`='notice' AND `cateType1`=?";
	// 문의사항 - 페이지 글 카테고리 별 개수 가져오기
	public static final String COUNT_QNA_ARTICLE = "SELECT COUNT(`no`) FROM `cs_article` WHERE `cate`='qna' and `cateType1`=?";
	// 공지사항 카테고리 별 글 보기
	public static final String SELECT_NOTICE_ARTICLE = "SELECT * FROM `cs_article` WHERE `cate`='notice' AND `cateType1`=? ORDER BY `no` DESC";
	
}
