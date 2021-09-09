package teamD.publicParking.board.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import teamD.publicParking.board.Entity.BoardEntity;
import teamD.publicParking.board.Entity.enums.BoardType;
import teamD.publicParking.board.dto.BoardDto;
import teamD.publicParking.board.repository.BoardRepository;

@AllArgsConstructor
@Service
public class BoardService {

		private BoardRepository boardRepository;
		
		private static final int BLOCK_PAGE_NUM_COUNT = 5; //블럭에 존재하는 페이지 수
		private static final int PAGE_POST_COUNT = 10; //한 페이지에 존재하는 게시글 수
		
		@Transactional
		public Long savePost (BoardDto boardDto) {
			return boardRepository.save(boardDto.toEntity()).getBoard_id();
		}
		
		@Transactional
		public List<BoardDto> getBoardList(Integer pageNum){ //게시물의 목록을 가져온다.
			Page<BoardEntity> page = boardRepository
					.findAll(PageRequest
							.of(pageNum-1, PAGE_POST_COUNT, Sort.by(Sort.Direction.ASC,"createdDate")));
			
			//List<Board> boardList = boardRepository.findAll();
			List<BoardEntity> boardEntities = page.getContent();
			List<BoardDto> boardDtoList = new ArrayList<>();
			
			for (BoardEntity boardEntity : boardEntities) {
	            boardDtoList.add(this.convertEntityToDto(boardEntity));
			
			/*for(BoardEntity boardEntity : boardEntities) {
				BoardDto boardDto = BoardDto.builder()
						 .id(boardEntity.getId())
	                    .title(boardEntity.getTitle())
	                    .content(boardEntity.getContent())
	                    .writer(boardEntity.getWriter())
	                    .createdDate(boardEntity.getCreatedDate())
	                    .build();
				boardDtoList.add(boardDto);*/
			}
			return boardDtoList;
		}
		public Integer[] getPageList(Integer curPageNum) {
			Integer[] pageList = new Integer[BLOCK_PAGE_NUM_COUNT];
			
			//총 게시글 수
			Double postsTotalCount = Double.valueOf(this.getBoardCount());
			
			//총 게시글 수를 기준으로 계산한 마지막 페이지 번호(올림 계산)
			Integer totalLastPageNum = (int)(Math.ceil((postsTotalCount/PAGE_POST_COUNT)));
			
			//현재 페이지를 기준으로 블럭의 마지막 페이지 번호 계산
			Integer blockLastPageNum = (totalLastPageNum > curPageNum + BLOCK_PAGE_NUM_COUNT)
					? curPageNum + BLOCK_PAGE_NUM_COUNT
					: totalLastPageNum;
			
			//페이지 시작 번호 조정
			curPageNum = (curPageNum <= 3) ? 1 : curPageNum - 2;
			
			//페이지 번호 할당
			for (int val = curPageNum, idx = 0; val <= blockLastPageNum; val++, idx++) {
	            pageList[idx] = val;
	        }
			return pageList;
		}
		
		@Transactional
		public Long getBoardCount() {
			return boardRepository.count();
		}
		
		
		@Transactional
		public BoardDto getPost(Long id) { //게시글의 id를 받아 해당 게시글의 데이터만 가져와 화면에 뿌림
			Optional<BoardEntity> boardEntityWrapper = boardRepository.findById(id);
	        BoardEntity boardEntity = boardEntityWrapper.get();
			
			BoardDto boardDto = BoardDto.builder()
					.board_id(boardEntity.getBoard_id())
					.nickname(boardEntity.getNickname())
					.title(boardEntity.getTitle())
					.content(boardEntity.getContent())
					.createdDate(boardEntity.getCreatedDate())
					.boardType(boardEntity.getBoardType())
					.build();
			return boardDto;
		}
		
		
		@Transactional
		public void deletePost(Long id) {
			boardRepository.deleteById(id);
		}
		
		@Transactional
		public List<BoardDto> searchPosts(String keyword) {
		    List<BoardEntity> boardEntities = boardRepository.findByTitleContaining(keyword);
		    List<BoardDto> boardDtoList = new ArrayList<>();

		    if (boardEntities.isEmpty()) return boardDtoList;

		    for (BoardEntity boardEntity : boardEntities) {
		        boardDtoList.add(this.convertEntityToDto(boardEntity));
		    }

		    return boardDtoList;
		}
		
		private BoardDto convertEntityToDto(BoardEntity boardEntity) {
			return BoardDto.builder()
					.board_id(boardEntity.getBoard_id())
					.nickname(boardEntity.getNickname())
					.title(boardEntity.getTitle())
					.content(boardEntity.getContent())
					.createdDate(boardEntity.getCreatedDate())
					.boardType(BoardType.notice)
					.build();
		}

	}

