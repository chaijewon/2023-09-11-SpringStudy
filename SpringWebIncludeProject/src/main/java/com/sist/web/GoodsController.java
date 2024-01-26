package com.sist.web;
import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GoodsController {
   @RequestMapping("goods/find.do")
   public String goods_find(Model model)
   {
	   model.addAttribute("main_jsp", "../goods/find.jsp");
	   return "main/main";
   }
}
