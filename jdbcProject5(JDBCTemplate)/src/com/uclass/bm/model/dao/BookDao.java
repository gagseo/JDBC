package com.uclass.bm.model.dao;

import java.util.HashMap;
import java.util.Map;

import com.uclass.common.JDBCTemplate;

public class BookDao {

	private JDBCTemplate jdt = JDBCTemplate.getInstance();
	
	public BookDao() {
		
	}
	
	//도서 검색
	public Map<String, Object> searchBook(){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		
		
		return resultMap;
		
		
	}
	
	
}
