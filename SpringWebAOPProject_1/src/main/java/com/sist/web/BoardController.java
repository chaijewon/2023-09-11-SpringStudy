package com.sist.web;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.sist.dao.*;

@Controller
@RequestMapping("board/")
public class BoardController {
	@Autowired
    private BoardDAO dao;
	
	// list.do
	@RequestMapping("list.do")
	public String board_list(String page,Model model)
	{
	   // 사용자 전송한 모든 데이터는 매개변수로 받는다 ..
	   if(page==null)
		   page="1";
	   int curpage=Integer.parseInt(page);
	   Map map=new HashMap();
	   map.put("start", (curpage*10)-9);
	   map.put("end", curpage*10);
	   List<BoardVO> list=dao.boardListData(map);
	   int count=dao.boardTotalPage();
	   int totalpage=(int)(Math.ceil(count/10.0));
	   count=count-((curpage*10)-10);
	   model.addAttribute("count", count);
	   model.addAttribute("list", list);
	   model.addAttribute("curpage", curpage);
	   model.addAttribute("totalpage", totalpage);
	   /*
	    *    class Model
	    *    {
	    *       HttpServletRequest request;
	    *       public Model(HttpServletRequest request)
	    *       {
	    *          this.request=request;
	    *       }
	    *    }
	    *    Model model=new Model(request)
	    *    public void addAttribute("totalpage", totalpage)
	    *    {
	    *       request.setAttribute()
	    *    }
	    */
	   // Model => 전송 객체 
	   
	   return "board/list"; // /board/list.jsp
	}
	// insert.do
	@RequestMapping("insert.do")
	public String board_insert()
	{
		return "board/insert";
	}
	// insert_ok.do => command객체
	@RequestMapping("insert_ok.do")
	public String board_insert_ok(BoardVO vo)
	{
		dao.boardInsert(vo);
		return "redirect:list.do";
	}
	// detail.do?no=${vo.no }
	@RequestMapping("detail.do")
	public String board_detail(int no,Model model)
	{
		BoardVO vo=dao.boardDetailData(no);
		model.addAttribute("vo", vo);
		return "board/detail";
	}
	
	@RequestMapping("update.do")
	public String board_update(int no,Model model)
	{
		BoardVO vo=dao.boardUpdateData(no);
		model.addAttribute("vo", vo);
		return "board/update";
	}
	// 답변
	@RequestMapping("reply.do")
	public String board_reply(int pno,Model model)
	{
		// JSP로 전송 => 전송객체 => request.setAttribute()
		model.addAttribute("pno", pno);
		return "board/reply";
	}
	@RequestMapping("reply_ok.do")
	public String board_reply_ok(int pno,BoardVO vo)
	{
		dao.boardReplyInsert(pno, vo);
		return "redirect:list.do";
	}
}








