package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.sist.dao.*;

import java.io.File;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
//Model
@Controller
@RequestMapping("databoard/") // 공통 경로 저장 
// GetMapping / PostMapping => ajax,axios,form
// @RequestMapping => GET/POST 
// GET => @GetMapping / POST => PostMapping
// => PathValiable /databoard/detail/1
public class DataBoardController {
	@Autowired
    private DataBoardDAO dao;
    
	@Autowired
	private BCryptPasswordEncoder encoder;
    // 사용요청 처리 
    @GetMapping("list.do")
    /*
     *   매개변수 
     *   모든 데이터는 String , 해당 데이터형으로 설정
     *   Model => 전송객체 
     *   addAttribute(String key,Object obj)
     *   {
     *      request.setAttribute(key,obj)
     *   } 
     *   내장 객체 
     *   1. HttpServletRequest
     *   2. HttpServletResponse 
     *   3. HttpSession
     *   4. RedirectAttributes
     *   5. 커맨드 객체 => VO단위 
     *   6. String[]
     *   7. List 
     *      => name="file[0]"
     *      => 400 bad request
     *      => 404 , 500 
     */
    public String databoard_list(String page,Model model)
    {
    	if(page==null)
    		page="1";
    	int curpage=Integer.parseInt(page);
    	int rowSize=10;
    	int start=(rowSize*curpage)-(rowSize-1);
    	int end=rowSize*curpage;
    	List<DataBoardVO> list=dao.databoardListData(start, end);
    	int totalpage=dao.databoardTotalPage();
    	model.addAttribute("curpage", curpage);
    	model.addAttribute("totalpage", totalpage);
    	model.addAttribute("list", list);
    	
    	return "databoard/list";
    }
    @GetMapping("insert.do")
    public String databoard_insert()
    {
    	return "databoard/insert";
    }
    @PostMapping("insert_ok.do")
    public String databoard_insert_ok(DataBoardVO vo,HttpServletRequest request)
    {
    	String path=request.getSession().getServletContext().getRealPath("/")+"upload\\";
    	path=path.replace("\\", File.separator);
    	File dir=new File(path);
    	if(!dir.exists())
    	{
    	   dir.mkdir();	
    	}
    	List<MultipartFile> list=vo.getFiles();
    	if(list==null) // 업로드가 없는 상태
    	{
    		vo.setFilename("");
    		vo.setFilesize("");
    		vo.setFilecount(0);
    	}
    	else // 업로드가 된 상태 
    	{
    		String filename="";
    		String filesize="";
    		for(MultipartFile mf:list)
    		{
    			String name=mf.getOriginalFilename();
    			//              사용자가 보낸 파일명
    			File file=new File(path+name);
    			try
    			{
    				mf.transferTo(file);// 업로드
    			}catch(Exception ex){}
    			filename+=name+",";
    			filesize+=file.length()+",";
    		}
    		filename=filename.substring(0,filename.lastIndexOf(","));
    		filesize=filesize.substring(0,filesize.lastIndexOf(","));
    		vo.setFilename(filename);
    		vo.setFilesize(filesize);
    		vo.setFilecount(list.size());
    	}
    	String enPwd=encoder.encode(vo.getPwd());
    	vo.setPwd(enPwd);
    	dao.databoardInsert(vo);
    	return "redirect:list.do";
    }
    @GetMapping("detail.do")
    public String databoard_detail(int no,Model model)
    {
    	DataBoardVO vo=dao.databoardDetailData(no);
    	if(vo.getFilecount()>0)
    	{
    		String[] filenames=vo.getFilename().split(",");
    		String[] filesizes=vo.getFilesize().split(",");
    		
    		List<String> fList=Arrays.asList(filenames);
    		List<String> sList=Arrays.asList(filesizes);
    		
    		model.addAttribute("fList", fList);
    		model.addAttribute("sList", sList);
    		
    	}
    	model.addAttribute("vo", vo);
    	return "databoard/detail";
    }
    
}
