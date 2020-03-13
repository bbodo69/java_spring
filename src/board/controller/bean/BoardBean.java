package board.controller.bean;

import java.util.Collections;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import board.model.dao.BoardDAOImpl;
import board.model.dao.Test;
import board.model.vo.BoardVO;

@Controller
@RequestMapping("/board/")
public class BoardBean {
	// Controller -> Xxxservice(interface) -> XxxserviceImpl -> XxxDAO -> XxxDAOImple -> SQL,xml -> DB
	@Autowired
	private BoardDAOImpl boardDAO = null;
	
	@Autowired
	private Test test = null;
			
	@RequestMapping("list.do")
	public String list(String pageNum, Model md) throws Exception {
		
		if(pageNum==null) {
			pageNum = "1";
		}
		int pageSize= 10;
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		int count = 0;
		int number = 0;
		
		List articleList = null;
		count = boardDAO.getArticleCount();	
		System.out.println(count+"bean");
		if(count > 0) {
			articleList = boardDAO.getArticles(startRow, endRow);
		}else {
			articleList = Collections.EMPTY_LIST;
		}
		number = count-(currentPage-1)*pageSize;
		
		md.addAttribute("currentPage", currentPage);
		md.addAttribute("startRow", startRow);
		md.addAttribute("endRow", endRow);
		md.addAttribute("count", count);
		md.addAttribute("pageSize", pageSize);
		md.addAttribute("articleList", articleList);
		md.addAttribute("number", number);
				
		return "board/list";
	}
	
	@RequestMapping("writeForm.do")
	public String writeForm(Model md, BoardVO vo) throws Exception {
		
		int num = boardDAO.getArticleCount() + 1;
		int readCount = 0;
		
//		int ref = 0;
//		int re_step = 0;
//		int re_level = 0;
		md.addAttribute("num", num);
		md.addAttribute("readCount", readCount);
		md.addAttribute("vo", vo);
//		md.addAttribute("ref", ref);
//		md.addAttribute("re_step", re_step);
//		md.addAttribute("re_level", re_level);
		
		System.out.println(num);
		
		return "board/writeForm";
	}
	
	
	
	
	@RequestMapping("writePro.do")
	public String writePro(BoardVO vo) throws Exception {
		
		boardDAO.insertArticle(vo);
		return "board/writePro";
	}
	
	@RequestMapping("content.do")
	public String content(BoardVO vo, int num, int pageNum, Model md) throws Exception {
				
		vo = boardDAO.getArticle(num);
		
		System.out.println(num+"=num");
		System.out.println(pageNum+"=pageNum");
		
		md.addAttribute("pageNum", pageNum);
		md.addAttribute("list", vo);
		
		return "board/content";
	}
	
	@RequestMapping("deleteForm.do")
	public String deleteForm(int num, int pageNum, Model md) throws Exception {
		
		System.out.println("BeanClass deleteForm");
		md.addAttribute("num", num);
		md.addAttribute("pageNum", pageNum);		
		
		return "board/deleteForm";
	}
	
	@RequestMapping("deletePro.do")
	public String deletePro(int num, String pw, Model md) throws Exception {
		
		System.out.println("BeanClass deletePro");
		int check = (Integer)boardDAO.deleteArticle(num, pw);
		System.out.println("BeanClass deletePro After" );
		
		md.addAttribute("result", check);
				
		return "board/deletePro";
	}
	
	@RequestMapping("modifyForm.do")
	public String modifyForm(BoardVO vo, Model md) throws Exception {
		
		BoardVO list = (BoardVO)boardDAO.getArticle(vo.getNum());
		
		md.addAttribute("list", list);		
		
		return "board/modifyForm";
	}
	
	@RequestMapping("modifyPro.do")
	public String modifyPro(BoardVO vo, Model md) throws Exception {
		
		int check = boardDAO.updateArticle(vo);
		md.addAttribute("check", check);
		
		return "board/modifyPro";
	}
	
	
	@RequestMapping("setRef.do")
	public String setRef() throws Exception {
		 		
		test.setRef();
		
		return "board/setRef";
	}
	
	
}
