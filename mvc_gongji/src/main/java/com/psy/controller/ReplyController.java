package com.psy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.psy.dto.Criteria;
import com.psy.dto.PageMaker;
import com.psy.dto.ReplyVO;
import com.psy.service.ReplyService;

@RestController
@RequestMapping("/replies")
public class ReplyController {

	@Inject
	private ReplyService service;
	
	// ¥Ò±€ µÓ∑œ
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<String> register(@RequestBody ReplyVO vo) {
		
		ResponseEntity<String> entity = null;
		
		try {
			service.addReply(vo);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	// ¥Ò±€ ∏Ò∑œ
	@RequestMapping(value = "/all/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<ReplyVO>> list(@PathVariable("id") Integer id) {
		
		ResponseEntity<List<ReplyVO>> entity = null;
		
		try {
			entity = new ResponseEntity<>(service.listReply(id), HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	// ¥Ò±€ ºˆ¡§
	@RequestMapping(value = "/{comment_id}", method = {RequestMethod.PUT, RequestMethod.PATCH} )
	public ResponseEntity<String> update(@PathVariable("comment_id") Integer comment_id, @RequestBody ReplyVO vo) {
		
		ResponseEntity<String> entity = null;
		
		try {
			vo.setComment_id(comment_id);
			service.modifyReply(vo);
			
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	// ¥Ò±€ ªË¡¶
	@RequestMapping(value = "/{comment_id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> remove(@PathVariable("comment_id") Integer comment_id) {
		
		ResponseEntity<String> entity = null;
		
		try {
			service.removeReply(comment_id);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	// ¥Ò±€ ∏Ò∑œ ∆‰¿Ã¬° √≥∏Æ
	@RequestMapping(value = "/{id}/{page}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> listPage(@PathVariable("id") Integer id, @PathVariable("page") Integer page) {
		
		ResponseEntity<Map<String, Object>> entity = null;
		
		try {
			Criteria cri = new Criteria();
			cri.setPage(page);
			
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(cri);
			
			Map<String, Object> map = new HashMap<String, Object>();
			List<ReplyVO> list = service.listReplyPage(id, cri);
			
			map.put("list", list);
			
			int replyCount = service.count(id);
			pageMaker.setTotalCount(replyCount);
			
			map.put("pageMaker", pageMaker);
			
			entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
}
