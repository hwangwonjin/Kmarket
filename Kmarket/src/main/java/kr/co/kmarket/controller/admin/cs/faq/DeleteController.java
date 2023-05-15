package kr.co.kmarket.controller.admin.cs.faq;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;

import kr.co.kmarket.service.CsService;

@WebServlet("/admin/cs/faq/delete.do")
public class DeleteController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private CsService service = CsService.INSTANCE;
	Logger logger = LoggerFactory.getLogger(this.getClass());


	@Override
	public void init() throws ServletException {
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		logger.info("delete faq ");
		
		String no = req.getParameter("no");
		
		logger.info(no);
		
		int result = service.deleteArticle(no);
		
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		
		PrintWriter writer = resp.getWriter();
		writer.print(json.toString());
		
		resp.sendRedirect("/Kmarket/admin/cs/faq/list.do?cate=faq");
		
		logger.info("do");

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		logger.info("dopost");
		
		String[] chkList = req.getParameterValues("chkArr");
		logger.debug("chkList"+chkList);
		
		int length = chkList.length;
		int result = 0;
		
		for(int k=0; k<length; k++) {
			logger.info("chkList : "+chkList[k]);
			result = service.deleteArticle(chkList[k]);
			
			logger.debug("result : "+result);
		}
		
		resp.setCharacterEncoding("UTF-8");
		
		PrintWriter writer = resp.getWriter();
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		writer.print(json.toString());
	}
}
