package com.psy.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.psy.dto.Criteria;
import com.psy.dto.ReplyVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO {

	@Inject
	private SqlSession sqlSession;
	
	private static String namespace = "com.psy.mapper.replyMapper";
	
	@Override
	public List<ReplyVO> list(Integer id) throws Exception {
		
		return sqlSession.selectList(namespace+".list", id);
	}

	@Override
	public void create(ReplyVO vo) throws Exception {
		
		sqlSession.insert(namespace+".create", vo);
		
	}

	@Override
	public void update(ReplyVO vo) throws Exception {
		
		sqlSession.update(namespace+".update", vo);
		
	}

	@Override
	public void delete(Integer comment_id) throws Exception {
		
		sqlSession.delete(namespace+".delete", comment_id);
		
	}

	@Override
	public List<ReplyVO> listPage(Integer id, Criteria cri) throws Exception {
		
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("id", id);
		paramMap.put("cri", cri);
		
		return sqlSession.selectList(namespace+".listPage", paramMap);
	}

	@Override
	public int count(Integer id) throws Exception {
		
		return sqlSession.selectOne(namespace+".count", id);
		
	}
	
	

	
}
