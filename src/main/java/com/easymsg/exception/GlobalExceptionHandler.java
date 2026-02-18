package com.easymsg.exception;

import com.easymsg.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleValidation(MethodArgumentNotValidException ex, HttpServletRequest req) {
    String msg = ex.getBindingResult().getAllErrors().isEmpty()
        ? "Validation error"
        : ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
    return new ErrorResponse(400, "Bad Request", msg, req.getRequestURI());
  }

  @ExceptionHandler(IllegalArgumentException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleIllegalArg(IllegalArgumentException ex, HttpServletRequest req) {
    return new ErrorResponse(400, "Bad Request", ex.getMessage(), req.getRequestURI());
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorResponse handleAny(Exception ex, HttpServletRequest req) {
    return new ErrorResponse(500, "Internal Server Error", ex.getMessage(), req.getRequestURI());
  }
}
