package kr.co.kmarket.controller.product;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.kmarket.service.IndexService;
import kr.co.kmarket.service.ProductService;
import kr.co.kmarket.vo.CompleteVO;

@WebServlet("/product/complete.do")
public class CompleteController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private ProductService service = ProductService.INSTANCE;
	
	@Override
	public void init() throws ServletException {
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Map<String, Object> cate = service.selectCategory();
		req.setAttribute("cate", cate);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/product/complete.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("MMddHHmmss");
		String now = sdf.format(new Date());
		
		Random random = new Random();
		int rand = random.nextInt(1000);
		
		int ordNo           = rand+Integer.parseInt(now);
		String uid          = req.getParameter("uid");
		String ordCount     = req.getParameter("ordCount");
		String ordPrice     = req.getParameter("ordPrice");
		String ordDiscount  = req.getParameter("ordDiscount");
		String ordDelivery  = req.getParameter("ordDelivery");
		String ordsavePoint = req.getParameter("ordsavePoint");
		String ordusedPoint = req.getParameter("ordusedPoint");
		String ordTotPrice  = req.getParameter("ordTotPrice");
		String orderer      = req.getParameter("orderer");
		String hp 			= req.getParameter("hp");
		String zip 			= req.getParameter("zip");
		String addr1 		= req.getParameter("addr1");
		String addr2 		= req.getParameter("addr2");
		String payment 		= req.getParameter("payment");
		String receiver     = req.getParameter("receiver");
		String currentPoint = req.getParameter("currentPoint");
		String prodNo       = req.getParameter("prodNo");
		
		CompleteVO vo = new CompleteVO();
		vo.setOrdNo(ordNo);
		vo.setOrdUid(uid);
		vo.setOrdCount(ordCount);
		vo.setOrdPrice(ordPrice);
		vo.setOrdDiscount(ordDiscount);
		vo.setOrdDelivery(ordDelivery);
		vo.setOrdSavePoint(ordsavePoint);
		vo.setOrdUsedPoint(ordusedPoint);
		vo.setOrdTotPrice(ordTotPrice);
		vo.setOrdRecipHp(hp);
		vo.setOrdRecipName(orderer);
		vo.setOrdRecipZip(zip);
		vo.setOrdRecipAddr1(addr1);
		vo.setOrdRecipAddr2(addr2);
		vo.setOrdPayment(payment);
		vo.setOrdCompelete("1");
		vo.setOrdRecipReceiver(receiver);
		
		CompleteVO order = service.insertOrder(vo);
		
		HttpSession session = req.getSession();
		session.setAttribute("order", order);
		
		String[] cartNo = (String[]) session.getAttribute("cartNo");
		
		if(cartNo != null) {
			service.insertSelectDeleteCartPoint(cartNo, ordNo, uid, ordsavePoint, currentPoint, ordusedPoint);
		}else {
			service.insertOneSelectDeleteCartPoint(prodNo, vo, ordNo, uid, ordsavePoint, currentPoint, ordusedPoint);
		}
		
		resp.sendRedirect("/Kmarket/product/complete.do");
	}

}
