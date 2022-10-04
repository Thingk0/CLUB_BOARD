package org.club_board.handler;

import org.club_board.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExceptionAdvice {

    /**
     *
     ExceptionHandler 어노테이션 괄호 안에 IllegalArgumentException.class 를 통해,
     IllegalArgumentException 으로 오류를 보내주면 해당 메소드를 타게 된다..
     */

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response<?> illegalArgumentExceptionAdvice(IllegalArgumentException e) {
        return new Response<>("실패", e.getMessage().toString(), null);
    }
}
