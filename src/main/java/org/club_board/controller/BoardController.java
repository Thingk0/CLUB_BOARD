package org.club_board.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.club_board.config.auth.PrincipalDetails;
import org.club_board.dto.board.BoardCreateRequestDto;
import org.club_board.dto.board.BoardUpdateRequestDto;
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
        // 1. 현재 요청을 보낸 유저의 JWT 토큰 정보(프론트엔드가 헤더를 통해 보내줌)를 바탕으로
        // 현재 로그인한 유저의 정보가 PathVariable 로 들어오는 BoardID 의 작성자인 user 정보와 일치하는지 확인하고
        // 맞으면 아래 로직 수행, 틀리면 다른 로직(ResponseFail 등 커스텀으로 만들어서) 수행
        // 이건 if 문으로 처리할 수 있습니다. * 이 방법 말고 service 내부에서 확인해도 상관 없음

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

