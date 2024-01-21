package com.sist.web;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.sist.dao.*;
@RestController
public class FoodRestController {
   @Autowired
   private FoodDAO dao;
   
   @RequestMapping(value="food/find_vue.do",produces = "text/plain;charset=UTF-8")
   public String find_vue(String page,String fd)
   {
	   if(page=="")
		   page="1";
	   int curpage=Integer.parseInt(page);
	   int rowSize=12;
	   int start=(rowSize*curpage)-(rowSize-1);
	   int end=rowSize*curpage;
	   Map map=new HashMap();
	   map.put("start", start);
	   map.put("end", end);
	   map.put("address", fd);
	   List<FoodVO> list=dao.foodFindData(map);
	   JSONArray arr=new JSONArray();
	   for(FoodVO vo:list)
	   {
		   JSONObject obj=new JSONObject();
		   obj.put("fno", vo.getFno());
		   obj.put("name", vo.getName());
		   obj.put("poster", vo.getPoster());
		   arr.add(obj);
	   }
	   return arr.toJSONString();
   }
   // fno,name,type,address,price,time,content,poster
   @RequestMapping(value="food/detail_vue.do",produces = "text/plain;charset=UTF-8")
   public String food_detail(int fno)
   {
	   FoodVO vo=dao.foodDetailData(fno);
	   JSONObject obj=new JSONObject();
	   obj.put("fno", vo.getFno());
	   obj.put("name", vo.getName());
	   obj.put("type", vo.getType());
	   obj.put("address", vo.getAddress());
	   obj.put("price", vo.getPrice());
	   obj.put("time", vo.getTime());
	   obj.put("content", vo.getContent());
	   obj.put("poster", vo.getPoster());
	   return obj.toJSONString();
   }
}
