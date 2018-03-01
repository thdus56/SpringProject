package com.psy.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.psy.dto.BoardVO;
import com.psy.dto.Criteria;
import com.psy.dto.PageMaker;
import com.psy.service.BoardService;

@Controller
@RequestMapping("/gongji/*")
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	private BoardService service;
	
	// 새글 작성 페이지(초기)
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void registerGET(BoardVO vo, Model model) throws Exception {
		
		logger.info("register get..........");
	}
	
	// 글 작성 처리한 뒤 리다이렉트로 바로 글 목록 페이지로 돌아감(초기)
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPOST(BoardVO vo) throws Exception {
		logger.info("register post..........");
		System.out.println(vo.toString());
		
		service.regist(vo);
		
		return "redirect:/gongji/listPage";
	}
	
	// 글 목록 페이지(초기)
	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	public void listAll(Model model) throws Exception {
		
		logger.info("show all list....................");
		model.addAttribute("list", service.listAll());
		
	}
	
	// 글 상세조회 페이지(초기)
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public void read(@RequestParam("id") int id, Model model) throws Exception {
		// addAttribute를 할 때 아무 이름 없이 데이터를 넣으면 자동으로 클래스의 이름의 첫 글자를 소문자로 시작해서 사용
		model.addAttribute(service.read(id));
	
	}
	
	// 글 수정 폼 페이지(초기)
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void modifyGET(int id, Model model) throws Exception {
		System.out.println("글번호 "+id+"번의 update 페이지");
		
		model.addAttribute(service.read(id));
		
	}
	
	// 글 수정 처리한 뒤 리다이렉트로 전체 목록 돌아감(초기)
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modifyPOST(BoardVO vo) throws Exception {
		logger.info("mod post.............");
		
		service.modify(vo);
		
		return "redirect:/gongji/listAll";
	}
	
	// 글 삭제 + 전체 목록으로 리다이렉트(초기)
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String delete(@RequestParam("id") int id) throws Exception {
		service.remove(id);
		
		System.out.println("글 삭제 성공");
		return "redirect:/gongji/listAll";
	}
	
	
	@RequestMapping(value = "/listCri", method = RequestMethod.GET)
	public void listAll(Criteria cri, Model model) throws Exception {
		logger.info("show list Page with Criteria...........");
		
		model.addAttribute("list", service.listCriteria(cri));
	}
	
	// 페이징 적용한 글 전체 목록
	@RequestMapping(value = "/listPage", method = RequestMethod.GET)
	public void listPage(Criteria cri, Model model) throws Exception {
		logger.info(cri.toString());
		
		model.addAttribute("list", service.listCriteria(cri));
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.listCountCriteria(cri));
		
		model.addAttribute("pageMaker", pageMaker);
		
	}

	// 글 상세 보기
	@RequestMapping(value = "/readPage", method = RequestMethod.GET)
	public void read(@RequestParam("id") int id, @ModelAttribute("cri") Criteria cri, Model model) throws Exception {
		
		service.increaseViewcnt(id);
		
		model.addAttribute(service.read(id));
	}
	
	// 글 삭제 + 글 전체목록으로 리다이렉트
	@RequestMapping(value = "/removePage", method = RequestMethod.POST)
	public String remove(@RequestParam("id") int id, Criteria cri, RedirectAttributes rttr) throws Exception {
		service.remove(id);
		
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addFlashAttribute("msg", "SUCCESS");
		
		return "redirect:/gongji/listPage";
		
	}
	
	// 글 수정 폼 페이지
	@RequestMapping(value = "/modifyPage", method = RequestMethod.GET)
	public void modifyPagingGET(@RequestParam("id") int id, @ModelAttribute("cri") Criteria cri, Model model) throws Exception {
		
		model.addAttribute(service.read(id));
	}
	
	// 글 수정 + 글 전체목록으로 리다이렉트
	@RequestMapping(value = "/modifyPage", method = RequestMethod.POST)
	public String modifyPagingPOST(BoardVO vo, Criteria cri, RedirectAttributes rttr) throws Exception {
		
		service.modify(vo);
		
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addFlashAttribute("msg", "SUCCESS");
		
		return "redirect:/gongji/listPage";
	}
	
}
