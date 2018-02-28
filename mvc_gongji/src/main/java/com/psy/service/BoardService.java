package com.psy.service;

import java.util.List;

import com.psy.dto.BoardVO;
import com.psy.dto.Criteria;

public interface BoardService {
	
	public void regist(BoardVO vo) throws Exception;
	
	public BoardVO read(Integer id) throws Exception;
	
	public void remove(Integer id) throws Exception;
	
	public void modify(BoardVO vo) throws Exception;
	
	public List<BoardVO> listAll() throws Exception;
	
	public List<BoardVO> listCriteria(Criteria cri) throws Exception;
	
	public int listCountCriteria(Criteria cri) throws Exception;
	
	public void increaseViewcnt(int id) throws Exception;
}
