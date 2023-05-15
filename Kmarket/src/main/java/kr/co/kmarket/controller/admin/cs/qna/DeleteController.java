package kr.co.kmarket.controller.admin.cs.qna;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;

import kr.co.kmarket.service.CsService;

@WebServlet("/admin/cs/qna/delete.do")
public class DeleteController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private CsService service = CsService.INSTANCE;
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void init() throws ServletException {
		
	} 
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		logger.info("doGet");
		
		String no = req.getParameter("no");
		
		logger.debug("no : " +no);
		
		int result =service.deleteArticle(no);
		
		logger.debug("result : "+result);
		
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		
		PrintWriter writer = resp.getWriter();
		writer.print(json.toString());
		
		//resp.sendRedirect("/Kmarket/admin/cs/qna/list.do");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		logger.info("doPost");
		
		String[] chkList = req.getParameterValues("chkArr");	
		int length = chkList.length;
		int result = 0;
		
		for(int k=0; k<length; k++) {
			logger.info("chkList: "+chkList[k]);
			result = service.deleteArticle(chkList[k]);
			
			logger.debug("result : "+result);	// 삭제 시 result:1
		}
		
		resp.setCharacterEncoding("UTF-8");
		
		PrintWriter writer = resp.getWriter();
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		writer.print(json.toString());
			
		
		
// 개별 다중 선택 시 게시글 삭제 방법 2 	
//		for(int i=0; i<length; i++) {
//			service.deleteArticle(chkList[i]);
//		}
		
		
		/*
		// JSON.stringify로 받은 데이터 (문자 형태)
		String noArrString = req.getParameter("noArr");
		System.out.println("noArrString: " + noArrString);	// ["454","445"]
		
		// ,기준으로 나누기
		String noArr[] = noArrString.split(",");
		
		// 길이 구하기
		int length = noArr.length;
		System.out.println("length: " + length);
		
		// 값을 저장할 List 생성
		List<String> ArticleArr = new ArrayList<>();
		
		for(int i=0; i<length; i++) {
			System.out.println("noArr: "+noArr[i]);		//["443"   "442"]
			// 숫자를 제외한 문자를 없앤 후 저장
			ArticleArr.add(noArr[i].replaceAll("[^\\d]", ""));
		}
		
		System.out.println("list0: "+ArticleArr.get(0));	// list0: 508
		System.out.println("list1: "+ArticleArr.get(1));	// list1: 507
		
		int result = service.deleteArticles(ArticleArr);
		
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		
		PrintWriter writer = resp.getWriter();
		writer.print(json.toString());
		
		
		*/
	}
}
