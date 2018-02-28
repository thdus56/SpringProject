package com.psy.dao;

import java.util.List;

import com.psy.dto.Criteria;
import com.psy.dto.ReplyVO;

public interface ReplyDAO {
	
	public List<ReplyVO> list(Integer id) throws Exception;
	
	public void create(ReplyVO vo) throws Exception;
	
	public void update(ReplyVO vo) throws Exception;
	
	public void delete(Integer comment_id) throws Exception;
	
	// ��� ������ ó��
	public List<ReplyVO> listPage(Integer id, Criteria cri) throws Exception;
	
	// ��� �� ī��Ʈ
	public int count(Integer id) throws Exception;
	
}
