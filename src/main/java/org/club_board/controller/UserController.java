package org.club_board.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.club_board.dto.UserRegisterRequestDto;
import org.club_board.response.Response;
import org.club_board.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "회원리스트 조회", notes = "전체 회원을 조회")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public Response<?> findAll() {
        return new Response<>("true", "회원리스트 조회 성공", userService.findAll());
    }

    @ApiOperation(value = "회원조회", notes = "id 값으로 특정 회원 조회")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Response<?> findUser(@PathVariable("id") Long id) {
        return new Response<>("true", "회원조회 성공", userService.findUser(id));
    }

    @ApiOperation(value = "회원가입", notes="회원가입 진행")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public Response<?> register(@RequestBody UserRegisterRequestDto userRegisterRequestDto) {
        return new Response<>("true", "회원가입 성공", userService.register(userRegisterRequestDto));
    }
}


