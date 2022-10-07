package org.club_board.service;

import lombok.RequiredArgsConstructor;
import org.club_board.dto.board.request.BoardCreateRequestDto;
import org.club_board.dto.board.response.BoardCreateResponseDto;
import org.club_board.dto.board.request.BoardUpdateRequestDto;
import org.club_board.entity.Board;
import org.club_board.entity.User;
import org.club_board.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    // 전체 게시물 조회
    @Transactional(readOnly = true)
    public List<BoardCreateResponseDto> getBoardList(Long clubId) {
        // 리포지터리로 부터 Board 객체를 모두 List 로 반환.
        // clubId 를 통해 해당 아이디가 들어가는 보드들만 리스트로 만들어서 반환.
        List<Board> boards = boardRepository.findByClubId(clubId);

        // Dto 로 반환해주기 위해서 List 새롭게 생성.
        List<BoardCreateResponseDto> boarList = new ArrayList<>();
        boards.forEach(board -> boarList.add(BoardCreateResponseDto.toDto(board)));

        return boarList;
    }

    // 개별 게시물 조회
    @Transactional(readOnly = true)
    public BoardCreateResponseDto getBoard(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("해당 게시글을 찾을 수 없습니다.");
        });

        return BoardCreateResponseDto.toDto(board);
    }

    // 게시물 작성
    @Transactional
    public BoardCreateResponseDto createBoard(BoardCreateRequestDto boardCreateRequestDto,
                                              Long clubId,
                                              User user) {
        Board board = Board.builder()
                .title(boardCreateRequestDto.getTitle())
                .content(boardCreateRequestDto.getContent())
                .clubId(clubId)
                .writer(user.getNickname())
                .user(user)
                .build();

        boardRepository.save(board);
        return BoardCreateResponseDto.toDto(board);
    }

    // 게시물 수정
    @Transactional
    public BoardUpdateRequestDto updateBoard(Long id, BoardUpdateRequestDto boardUpdateRequestDto) {
        Board board = boardRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("해당 게시글을 찾을 수 없습니다!");
        });

        board.setTitle(boardUpdateRequestDto.getTitle());
        board.setContent(boardUpdateRequestDto.getContent());

        boardRepository.save(board);
        return BoardUpdateRequestDto.toDto(board);
    }


    // 게시글 삭제
    @Transactional
    public void deleteBoard(Long id) {
        // id 값으로, 게시글이 존재하는지 먼저 찾기.
        Board board = boardRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("Board Id를 찾을 수 없습니다!");
        });

        // 게시글이 있는 경우 삭제
        boardRepository.deleteById(id);
    }

}
