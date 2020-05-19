package com.uclass.bm.controller;

import java.util.Map;

import com.uclass.bm.model.service.RentBookService;
import com.uclass.bm.view.ErroMsg;

public class RentBookController {

	private RentBookService rbs = new RentBookService();
	private ErroMsg err = new ErroMsg();

	public Map<String, Object> updateExtendRentBook(int rb_idx) {

		Map<String, Object> resultMap = rbs.updateExtendRentBook(rb_idx);

		if ((boolean) resultMap.get("isSuccess")) {
			return resultMap;
		} else {
			err.printErr(resultMap);
			resultMap.put("res", "");
		}
		
		return resultMap;

	}

	public Map<String, Object> updateReturnRentBook(int rm_idx, int rb_idx, int rb_state) {

		Map<String, Object> resultMap = rbs.updateReturnRentBook(rm_idx, rb_idx, rb_state);

		if ((boolean) resultMap.get("isSuccess")) {
			return resultMap;
		} else {
			err.printErr(resultMap);
			resultMap.put("res", "");
		}

		return resultMap;

	}

}
