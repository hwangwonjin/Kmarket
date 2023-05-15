package kr.co.kmarket.controller.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;

import kr.co.kmarket.dao.AdminDAO;
import kr.co.kmarket.service.AdminService;
import kr.co.kmarket.vo.ProductVO;
@WebServlet("/admin/product/deleteProduct.do")
public class DeleteController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private AdminService service = AdminService.INSTANCE;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void init() throws ServletException {
	
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String prodNo = req.getParameter("prodNo");
		
		int result = service.delectProduct(prodNo);
		
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		
		PrintWriter writer = resp.getWriter();
		writer.print(json.toString());

		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String[] chkList = req.getParameterValues("chkArr");	
		int length = chkList.length;
		int result = 0;
		
		for(int k=0; k<length; k++) {
			result = service.delectProduct(chkList[k]);
			
		}
		
		resp.setCharacterEncoding("UTF-8");
		
		PrintWriter writer = resp.getWriter();
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		writer.print(json.toString());
	}

}
