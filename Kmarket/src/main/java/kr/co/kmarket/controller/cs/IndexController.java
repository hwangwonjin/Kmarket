
package kr.co.kmarket.controller.cs;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.kmarket.service.CsService;
import kr.co.kmarket.vo.CsVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/cs/index.do")
public class IndexController extends HttpServlet{


	private static final long serialVersionUID = 1L;
	private CsService service = CsService.INSTANCE;
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void init() throws ServletException {
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		logger.info("doGet");
		
		// 최신글 리스트 5건 출력하기
		String notice = "notice";
		String faq = "faq";
		String qna = "qna";
		
		List<CsVO> latests = service.selectLatests(notice, faq, qna);
		
		if(latests.size() < 10) {
			CsVO vo = new CsVO();
			vo.setNo(0);
			vo.setCate("카테고리");
			vo.setCateType1("cateType1");
			vo.setCateType2("cateType2");
			vo.setTitle("test");
			vo.setUid("circle");
			vo.setRdate("00-00-00");
			
			for(int i=0; i<15; i++) {
				latests.add(vo);
			}
					
		}
		req.setAttribute("latests", latests);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/cs/index.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}
