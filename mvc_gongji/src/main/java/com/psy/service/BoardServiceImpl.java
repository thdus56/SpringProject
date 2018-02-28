package com.psy.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.psy.dao.BoardDAO;
import com.psy.dto.BoardVO;
import com.psy.dto.Criteria;

@Service
public class BoardServiceImpl implements BoardService {

	@Inject
	private BoardDAO dao;
	
	@Override
	public void regist(BoardVO vo) throws Exception {
		dao.create(vo);
	}

	@Override
	public void remove(Integer id) throws Exception {
		dao.delete(id);
	}

	@Override
	public void modify(BoardVO vo) throws Exception {
		
		dao.update(vo);
	}
	
	@Override
	public List<BoardVO> listAll() throws Exception {
		return dao.listAll();
	}

	@Override
	public BoardVO read(Integer id) throws Exception {
		return dao.read(id);
	}

	@Override
	public List<BoardVO> listCriteria(Criteria cri) throws Exception {
		
		return dao.listCriteria(cri);
	}

	@Override
	public int listCountCriteria(Criteria cri) throws Exception {
		
		return dao.countPaging(cri);
	}

	@Override
	public void increaseViewcnt(int id) throws Exception {
		
		dao.increaseViewcnt(id);
		
	}

}
