package org.club_board.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.club_board.config.auth.PrincipalDetails;
import org.club_board.dto.board.request.BoardCreateRequestDto;
import org.club_board.dto.board.request.BoardUpdateRequestDto;
import org.club_board.entity.User;
import org.club_board.repository.UserRepository;
import org.club_board.response.Response;
import org.club_board.service.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/boards")
public class BoardController {

    private final BoardService boardService;
    private final UserRepository userRepository;


    // 동아리별 게시글 조회
    @ApiOperation(value = "전체 게시글 조회", notes = "전체 게시글을 조회한다.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{clubId}/list")
    public Response<?> getBoardList(@PathVariable Long clubId) {
        return new Response<>("성공", "전체 게시글 조회", boardService.getBoardList(clubId));
    }

    // 개별 게시글 조회
    @ApiOperation(value = "게시글 조회", notes = "개별 게시글 조회한다.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Response<?> getBoard(@PathVariable("id") Long id) {
        return new Response<>("성공", "게시글 조회", boardService.getBoard(id));
    }

    // 게시글 작성
    @ApiOperation(value = "게시글 작성", notes = "게시글을 작성한다.")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{clubId}/create")
    public Response<?> createBoard(@RequestBody BoardCreateRequestDto boardCreateRequestDto,
                                   @PathVariable Long clubId,
                                   Authentication authentication) {

        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        User user = principalDetails.getUser();

        return new Response<>("성공", "게시글 작성",
                boardService.createBoard(boardCreateRequestDto, clubId, user));
    }


    // 게시글 수정
    @ApiOperation(value = "게시글 수정", notes = "게시글을 수정한다.")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/update/{id}")
    public Response<?> updateBoard(@RequestBody BoardUpdateRequestDto boardUpdateRequestDto,
                                   @PathVariable Long id,
                                   Authentication authentication) {

        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        User user = principalDetails.getUser();

        // 로그인된 유저의 이름과 작성자가 같을 경우,
        if (user.getNickname().equals(boardService.getBoard(id).getWriter())) {
            return new Response<>("성공", "게시글 수정", boardService.updateBoard(id, boardUpdateRequestDto));
        } else {
            return new Response<>("실패", "본인 게시물만 수정할 수 있습니다.", null);
        }
    }


    // 게시글 삭제
    @ApiOperation(value = "게시글 삭제", notes = "게시글을 삭제한다.")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/delete/{id}")
    public Response<?> deleteBoard(@PathVariable("id") Long id,
                                   Authentication authentication) {

        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        User user = principalDetails.getUser();

        // 로그인된 유저의 이름과 작성자가 같을 경우,
        if (user.getNickname().equals(boardService.getBoard(id).getWriter())) {
            boardService.deleteBoard(id);
            return new Response<>("성공", "게시글 삭제", null);
        } else {
            return new Response<>("실패", "본인 게시물만 삭제할 수 있습니다.", null);
        }
    }
}

