package teamD.publicParking.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import teamD.publicParking.board.dto.BoardDto;
import teamD.publicParking.board.service.BoardService;

@Controller //Http 요청이 진입하는 지점
@RequestMapping("board")
public class BoardController {
		private BoardService boardService;
		
		public BoardController(BoardService boardService) {
			this.boardService = boardService;
		}
		
		@GetMapping("/")
		public String list(Model model, @RequestParam(value = "page", defaultValue = "1") Integer pageNum) { //가져온 getBoard 데이터를 model을 통해 view에 전달
			List<BoardDto> boardDtoList = boardService.getBoardList(pageNum);
			Integer[] pageList = boardService.getPageList(pageNum);
			
			model.addAttribute("postList", boardDtoList);
			model.addAttribute("pageList", pageList);
			
			return "board/list.html";
		}
		
		@GetMapping("/post")
		public String post() {
			return "board/post.html";
		}
		
		@PostMapping("/post")
		public String write(BoardDto boardDto) {
			boardService.savePost(boardDto);
			return "redirect:/";
		}
		
		@GetMapping("/post/{board_id}")
		public String detail(@PathVariable("board_id") Long board_id, Model model) {
			BoardDto boardDto = boardService.getPost(board_id);
			
			model.addAttribute("post", boardDto);
			return "board/detail.html";
		}
		
		@GetMapping("/post/edit/{board_id}")
		public String edit(@PathVariable("board_id") Long board_id, Model model) {
			BoardDto boardDto = boardService.getPost(board_id);
			
			model.addAttribute("post", boardDto);
			return "board/update.html";
		}
		
		@PutMapping("/post/edit/{board_id}")
		public String update(BoardDto boardDto) {
			boardService.savePost(boardDto);
			return "redirect:/";
		}
		
		@DeleteMapping("/post/{board_id}")
		public String delete(@PathVariable("board_id") Long board_id) {
			boardService.deletePost(board_id);
			return "redirect:/";
		}
		
		@GetMapping("/board/search")
		public String search(@RequestParam(value="keyword") String keyword, Model model) {
		    List<BoardDto> boardDtoList = boardService.searchPosts(keyword);
		    
		    model.addAttribute("boardList", boardDtoList);
		    
		    return "board/list.html";
		}

	}
