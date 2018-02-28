package com.psy.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.psy.dao.ReplyDAO;
import com.psy.dto.Criteria;
import com.psy.dto.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Inject
	private ReplyDAO dao;
	
	@Override
	public void addReply(ReplyVO vo) throws Exception {
		
		dao.create(vo);
		
	}

	@Override
	public List<ReplyVO> listReply(Integer id) throws Exception {
	
		return dao.list(id);
	}

	@Override
	public void modifyReply(ReplyVO vo) throws Exception {
		
		dao.update(vo);
		
	}

	@Override
	public void removeReply(Integer comment_id) throws Exception {
	
		dao.delete(comment_id);
		
	}

	@Override
	public List<ReplyVO> listReplyPage(Integer id, Criteria cri) throws Exception {
		
		return dao.listPage(id, cri);
	}

	@Override
	public int count(Integer id) throws Exception {
		
		return dao.count(id);
	}

}
