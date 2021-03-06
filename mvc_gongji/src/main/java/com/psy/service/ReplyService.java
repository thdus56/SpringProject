package com.psy.service;

import java.util.List;

import com.psy.dto.Criteria;
import com.psy.dto.ReplyVO;

public interface ReplyService {

	public void addReply(ReplyVO vo) throws Exception;
	
	public List<ReplyVO> listReply(Integer id) throws Exception;
	
	public void modifyReply(ReplyVO vo) throws Exception;
	
	public void removeReply(Integer comment_id) throws Exception;
	
	public List<ReplyVO> listReplyPage(Integer id, Criteria cri) throws Exception;
	
	public int count(Integer id) throws Exception;
	
}
