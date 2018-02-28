package com.psy.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.psy.dto.BoardVO;
import com.psy.dto.Criteria;

@Repository
public class BoardDAOImpl implements BoardDAO {
	
	@Inject
	private SqlSession sqlSession;

	private static String namespace = "com.psy.mapper.boardMapper";
	
	@Override
	public void create(BoardVO vo) throws Exception {
		
		sqlSession.insert(namespace+".create", vo);
	
	}

	@Override
	public List<BoardVO> listAll() throws Exception {
		
		return sqlSession.selectList(namespace+".listAll");
	}

	@Override
	public BoardVO read(Integer id) throws Exception {
		
		return sqlSession.selectOne(namespace+".read", id);
	
	}

	@Override
	public void delete(Integer id) throws Exception {
		
		sqlSession.delete(namespace+".delete", id);
		
	}

	@Override
	public void update(BoardVO vo) throws Exception {
		
		sqlSession.update(namespace+".update", vo);
		
	}

	@Override
	public List<BoardVO> listPage(int page) throws Exception {
		
		if (page <= 0) {
			page = 1;
		}
		
		page = (page - 1) * 10;
		
		return sqlSession.selectList(namespace+".listPage", page);
	}

	@Override
	public List<BoardVO> listCriteria(Criteria cri) throws Exception {
		
		return sqlSession.selectList(namespace+".listCriteria", cri);
	}

	@Override
	public int countPaging(Criteria cri) throws Exception {
		
		return sqlSession.selectOne(namespace+".countPaging", cri);
	}

	@Override
	public void increaseViewcnt(int id) throws Exception {
		
		sqlSession.update(namespace+".increaseViewcnt", id);
		
	}
	
	

}
