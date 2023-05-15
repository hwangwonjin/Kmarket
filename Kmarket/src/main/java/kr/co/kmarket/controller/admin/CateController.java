package kr.co.kmarket.controller.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kr.co.kmarket.service.IndexService;
import kr.co.kmarket.vo.CategoryVO;

@WebServlet("/admin/select.do")
public class CateController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private IndexService inservice = IndexService.INSTANCE;
	
	@Override
	public void init() throws ServletException {
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html;charset=UTF-8");
		
		String cate1 = req.getParameter("cate1");
		
		if(cate1 != null) {
		List<CategoryVO> cate2 = inservice.selectCate(Integer.parseInt(cate1));
			
		Gson gson = new Gson();
		String jsonData = gson.toJson(cate2);
				
		PrintWriter writer = resp.getWriter();
		writer.print(jsonData);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}

}
