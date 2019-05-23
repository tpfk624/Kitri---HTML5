package com.kitri.service;

import com.kitri.dao.RepBoardDAO;
import com.kitri.dto.RepBoard;
import com.kitri.exception.AddException;

public class RepBoardService {
	private RepBoardDAO dao;
	
	public RepBoardService() {
		dao = new RepBoardDAO();
	}
	
	public void write(RepBoard repBoard) throws AddException {
		repBoard.setParent_seq(0); //이상한걸해도 부모를 여기서 무조건 0으로 지정가능하다
		dao.insert(repBoard);
	}
	
	public void reply(RepBoard repBoard) throws AddException {
		if (repBoard.getParent_seq() == 0 ) {
			throw new AddException("부모글번호가 없는 답글입니다.");
		}
		dao.insert(repBoard);
	}
}
