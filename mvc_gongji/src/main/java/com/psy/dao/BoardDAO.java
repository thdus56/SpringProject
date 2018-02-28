package com.psy.dao;

import java.util.List;

import com.psy.dto.BoardVO;
import com.psy.dto.Criteria;

public interface BoardDAO {

	public void create(BoardVO vo) throws Exception;
	
	public BoardVO read(Integer id) throws Exception;
	
	public void delete(Integer id) throws Exception;
	
	public void update(BoardVO vo) throws Exception;
	
	public List<BoardVO> listAll() throws Exception;
	
	public List<BoardVO> listPage(int page) throws Exception;
	
	public List<BoardVO> listCriteria(Criteria cri) throws Exception;
	
	public int countPaging(Criteria cri) throws Exception;
	
	public void increaseViewcnt(int id) throws Exception;
	
}
