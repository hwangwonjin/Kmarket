package kr.co.kmarket.controller.product;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;

import kr.co.kmarket.service.ProductService;

@WebServlet("/product/deleteCart.do")
public class DeleteCartController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private ProductService service = ProductService.INSTANCE;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@Override
	public void init() throws ServletException {
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// JSON.stringify(totalNo) 를 통해 받은 데이터는 "문자" 로 넘어옴
		String cartNo = req.getParameter("totalNo");
		
		// 문자를 "," 나눈후 저장
		String cart[] = cartNo.split(",");
		
		// 길이를 구한다.
		int length = cart.length;
		
		// 값을 저장할 List를 생성한다.
		List<String> totalNo = new ArrayList<>(); 
		
		// cart[] 길이만큼 반복
		for(int i = 0; i<length; i++) {
			// 숫자를 제외한 문자를 없애고 저장
			totalNo.add(cart[i].replaceAll("[^\\d]", ""));
		}
		
		int result = service.deleteCart(totalNo);
		
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		
		PrintWriter writer = resp.getWriter();
		writer.print(json.toString());
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}
