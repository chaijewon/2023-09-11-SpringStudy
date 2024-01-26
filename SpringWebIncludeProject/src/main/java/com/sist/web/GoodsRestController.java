package com.sist.web;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.dao.*;
import com.sist.vo.*;
@RestController
public class GoodsRestController {
	@Autowired
    private GoodsDAO dao;
	
	@RequestMapping("goods/find_vue.do")
	public String goods_find(String page,String ss) throws Exception
	{
		   if(ss==null)
			   ss="특가";
		   
		   if(page==null)
			   page="1";
		   int curpage=Integer.parseInt(page);
		   int rowSize=12;
		   int start=(rowSize*curpage)-(rowSize-1);
		   int end=rowSize*curpage;
		   Map map=new HashMap();
		   map.put("ss", ss);
		   map.put("start", start);
		   map.put("end", end);
		   List<GoodsVO> list=dao.goodsFindData(map);
		   
		   ObjectMapper mapper=new ObjectMapper();
		   String json=mapper.writeValueAsString(list);
		return json;
	}
	@RequestMapping("goods/page_vue.do")
	public String goods_page(String page,String ss)
	{
		return "";
	}
}
