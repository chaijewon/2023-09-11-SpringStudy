package com.sist.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.sist.dao.FoodVO;

public interface FoodMapper {
	  @Select("SELECT fno,poster,name,num "
			 +"FROM (SELECT fno,poster,name,rownum as num "
			 +"FROM (SELECT fno,poster,name "
			 +"FROM food_menu_house ORDER BY fno ASC)) "
			 +"WHERE num BETWEEN #{start} AND #{end}")
	  public List<FoodVO> foodListData(@Param("start") int start,
			  @Param("end") int end);
	  
	  @Select("SELECT CEIL(COUNT(*)/12.0) FROM food_menu_house")
	  public int foodTotalPage();
	  @Select("SELECT fno,name,type,address,price,time,content,poster "
			  +"FROM food_menu_house "
			  +"WHERE fno=#{fno}")
	  public FoodVO foodDetailData(int fno);
}
