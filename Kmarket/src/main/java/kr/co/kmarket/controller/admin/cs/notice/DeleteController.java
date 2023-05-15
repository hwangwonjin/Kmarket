package kr.co.kmarket.controller.admin.cs.notice;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;

import kr.co.kmarket.service.CsService;
import kr.co.kmarket.vo.CsVO;

@WebServlet("/admin/cs/notice/delete.do")
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
		
		logger.info("no: " + no);
		
		int result = service.deleteArticle(no);
		
		resp.sendRedirect("/Kmarket/admin/cs/notice/list.do?cate=notice");
		
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
		//진행 중
		
		
//		int size = arrChk.length;
//		
//		for(int i=0; i<size; i++) {
//			System.out.println("JSP에서 받은 배열: " + arrChk);
//		}
		
		
		
		
	}
}
