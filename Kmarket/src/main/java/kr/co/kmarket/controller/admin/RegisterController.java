package kr.co.kmarket.controller.admin;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.oreilly.servlet.MultipartRequest;

import kr.co.kmarket.service.IndexService;
import kr.co.kmarket.service.ProductService;
import kr.co.kmarket.vo.CategoryVO;
import kr.co.kmarket.vo.ProductVO;

@WebServlet("/admin/register.do")
public class RegisterController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private ProductService service = ProductService.INSTANCE;
	private IndexService inservice = IndexService.INSTANCE;

	@Override
	public void init() throws ServletException {

	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//test
		List<CategoryVO> cate = inservice.selectCate1();
		req.setAttribute("cate", cate);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/register.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 로컬 경로 저장
		ServletContext ctx = req.getServletContext();
		String path = ctx.getRealPath("/home/prodImg");
		File Dir = new File(path);
		
		/*
		String path = "/home/prodImg";
		File Dir = new File(path);
		*/
		
		// 폴더가 없으면~ 생성 이게 없으면 직접 생성하고 서버리스트에서 빼고 다시 서버 재시작 해야함.
		if(!Dir.exists()) {
			Dir.mkdirs();
		}
		
		MultipartRequest mr = service.fileUpload(req, path);
		
		req.setCharacterEncoding("UTF-8");
		String prodCate1 = mr.getParameter("prodCate1");
		String prodCate2 = mr.getParameter("prodCate2");
		String prodName  = mr.getParameter("prodName");
		String descript  = mr.getParameter("descript");
		String company   = mr.getParameter("company");
		String price     = mr.getParameter("price");
		String discount  = mr.getParameter("discount");
		String sellPrice = mr.getParameter("sellPrice");
		String point     = mr.getParameter("point");
		String stock     = mr.getParameter("stock");
		String delivery  = mr.getParameter("delivery");
		String status    = mr.getParameter("status");
		String duty      = mr.getParameter("duty");
		String receipt   = mr.getParameter("receipt");
		String bizType   = mr.getParameter("bizType");
		String origin    = mr.getParameter("origin");
		String thumb1    = mr.getFilesystemName("thumb1");
		String thumb2    = mr.getFilesystemName("thumb2");
		String thumb3    = mr.getFilesystemName("thumb3");
		String detail    = mr.getFilesystemName("detail");
		String seller    = mr.getParameter("seller");
		String regip     = req.getRemoteAddr();
		
		int i = thumb1.lastIndexOf(".");
		String ext = thumb1.substring(i);
		
		int i1 = thumb2.lastIndexOf(".");
		String ext1 = thumb2.substring(i1);
		
		int i2 = thumb3.lastIndexOf(".");
		String ext2 = thumb3.substring(i2);
		
		int i3 = detail.lastIndexOf(".");
		String ext3 = detail.substring(i3);
		
		UUID uThumb1 = UUID.randomUUID();
		UUID uThumb2 = UUID.randomUUID();
		UUID uThumb3 = UUID.randomUUID();
		UUID uDetail = UUID.randomUUID();
		
		ProductVO vo = new ProductVO();
		vo.setProdCate1(prodCate1);
		vo.setProdCate2(prodCate2);
		vo.setProdName(prodName);
		vo.setDescript(descript);
		vo.setCompany(company);
		vo.setPrice(price);
		vo.setDiscount(discount);
		vo.setSellPrice(sellPrice);
		vo.setPoint(point);
		vo.setStock(stock);
		vo.setDelivery(delivery);
		vo.setStatus(status);
		vo.setDuty(duty);
		vo.setReceipt(receipt);
		vo.setBizType(bizType);
		vo.setOrigin(origin);
		vo.setRegip(regip);
		vo.setSeller("admin");
		vo.setThumb1(prodCate1+"-"+prodCate2+"-"+uThumb1.toString()+ext);
		vo.setThumb2(prodCate1+"-"+prodCate2+"-"+uThumb2.toString()+ext1);
		vo.setThumb3(prodCate1+"-"+prodCate2+"-"+uThumb3.toString()+ext2);
		vo.setDetail(prodCate1+"-"+prodCate2+"-"+uDetail.toString()+ext3);
		
		int prodNo = service.insertProduct(vo);
		
		// UUID 넣음으로서 문제 해결 된 듯 합니다. 각자 로컬서버에서 파일 등록하고 파일 압축하고
		// 리스트 불러올땐 압축된 파일 이미지 경로에 푼다음 하면 될 것 같아요. 아니면 각자 seller 이름으로만 작업을 하던지.
		
		/* 해결?
		사실 rename하지 않아도 파일명이 중복되는 일은 절대 없습니다. 저장될때 중복되는 이름은 알아서 번호를 부여해서 피하기 때문입니다.
		a.jpg a.jpg a.jpg a.jpg a.jpg 삽입 시 => a.jpg a1.jpg a2.jpg a3.jpg a4.jpg a5.jpg.... 이런식으로 db에 저장됩니다. 
		이 부분을 넣을지 말지는 팀원분들과 토론을 더 해보고 결정하겠습니다.
		*/
		
		/* 미해결
		4줄로 적은 이유는 do페이지 불러올때 최초 한번 실행인데... 한줄로 깔끔하게 4번 실행할 방법이 생각이 나지않습니다.. 좋은 의견 있으시면 주세요
		*/
		
		service.renameFile(thumb1, path, uThumb1.toString(), prodCate1, prodCate2);
		
		service.renameFile(thumb2, path, uThumb2.toString(), prodCate1, prodCate2);
		
		service.renameFile(thumb3, path, uThumb3.toString(), prodCate1, prodCate2);
		
		service.renameFile(detail, path, uDetail.toString(), prodCate1, prodCate2);
		
		/* 해결?
		문제는... list에서 이미지 파일을 불러오는 문제입니다.
		이미지 파일을 저장하는 방법에는 DB에 직접 저장하는 방법과 파일의 경로를 저장하는 방법이 있습니다.
		파일을 DB에 저장하는 방법을 고려했으나 62개의 카테고리, 카테고리당 10개의 품목, 품목당 3개의 이미지... 즉 약 1800개 가량의 이미지를 DB에 저장하게 되고
		DB에 무리가와서 많이 부하가 올까봐 하지않았습니다.
		파일의 경로를 저장하는 경우 경로는 /Kmarket/prodImg/파일명 이고
		DB에서 select하여 img src="/kmarket/prodImg/${product.tumb1}" 이런식으로 이미지를 불러올 계획이였습니다.
		하지만 4명이서 따로 이러한 작업을 진행하면 이미지파일은 각자 로컬서버에 저장이되고 이미지 파일의 정보는 AWS 팀장 서버에 통합이 됩니다.
		즉 각자 컴퓨터에서 ListController를 구현하고 실행했을때 db에서 불러온 정보와 각자 로컬서버에 저장된 이미지의 정보가 틀리기 때문에
		오류가 나거나 틀린 db데이터로 list가 출력이 됩니다.
		이부분은... 잘모르겠네요 한분이 책임지시고 싹 다 올리실동안 나머지 3분은 다른 기능구현하시고
		다 올린 이미지 파일 압축해서 각자 로컬서버에 압축을 풀면 될지.. 이것도 한번 얘기 나눠보면 좋을 것 같습니다.
		*/
		
		resp.sendRedirect("/Kmarket/admin/register.do");
		
	}
}
