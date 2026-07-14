package com.shinhan.bananaapp.exception;


import com.shinhan.bananaapp.common.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice  //모든 Controller에서 발생하는 예외를 한 곳에서 처리하는 클래스
public class GlobalExceptionHandler {

    // REST API 예외 → JSON 응답
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public ResponseEntity<ApiResponse<Void>> handleBusiness(BusinessException e) {
        log.warn("[BusinessException] {} - {}", e.getErrorCode(), e.getMessage());
        return ResponseEntity.badRequest().body(ApiResponse.fail(e.getMessage()));
    }

    // 유효성 검사 예외  @Valid에서   @NotBlank사용 시 입력없으면
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<ApiResponse<Void>> handleValidation(
            MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(", "));
        return ResponseEntity.badRequest().body(ApiResponse.fail(message));
    }

    // View 기반 404 처리
    @ExceptionHandler(NoResourceFoundException.class)
    public String handle404(NoResourceFoundException e, Model model) {

        model.addAttribute("errCode", 404);
        model.addAttribute("errMessage", "요청한 페이지를 찾을 수 없습니다.");

        return "error/404";
    }

    // 최상위 예외 처리
    /*@ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ApiResponse<Void>> handleAll(Exception e) {
        log.error("[Unhandled Exception]", e);
        return ResponseEntity.internalServerError()
                .body(ApiResponse.fail("서버 오류가 발생했습니다."));
    }*/


    @ExceptionHandler(Exception.class)
    public String handle(Exception e, Model model) {
        model.addAttribute("errMessage", e.getMessage());
        model.addAttribute("errClass", e.getClass().getSimpleName());
        if (e instanceof AccountNotFoundException ee) {
            model.addAttribute("errCode", ee.getErrorCode());
        }
        return "error/500";
    }

}