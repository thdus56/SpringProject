package com.psy.dao;

import java.util.List;

import com.psy.dto.Criteria;
import com.psy.dto.ReplyVO;

public interface ReplyDAO {
	
	public List<ReplyVO> list(Integer id) throws Exception;
	
	public void create(ReplyVO vo) throws Exception;
	
	public void update(ReplyVO vo) throws Exception;
	
	public void delete(Integer comment_id) throws Exception;
	
	// 댓글 페이지 처리
	public List<ReplyVO> listPage(Integer id, Criteria cri) throws Exception;
	
	// 댓글 수 카운트
	public int count(Integer id) throws Exception;
	
}
