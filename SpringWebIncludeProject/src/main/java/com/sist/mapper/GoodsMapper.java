package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.*;
public interface GoodsMapper {
	// 검색 => 업체명 / 주소 / 음식 종류 
	  @Select("SELECT no,goods_name,goods_poster,num "
				 +"FROM (SELECT no,goods_name,goods_poster,rownum as num "
				 +"FROM (SELECT no,goods_name,goods_poster "
				 +"FROM goods_all "
				 +"WHERE goods_name LIKE '%'||#{ss}||'%'"
				 +"ORDER BY no ASC)) "
				 +"WHERE num BETWEEN #{start} AND #{end}")
	  public List<GoodsVO> goodsFindData(Map map);
	  @Select("SELECT CEIL(COUNT(*)/12.0) FROM goods_all "
			 +"WHERE goods_all LIKE '%'||#{ss}||'%'")
	  public int goodsFindTotalPage(String ss);
}
