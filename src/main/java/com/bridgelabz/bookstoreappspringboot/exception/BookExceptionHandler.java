package com.bridgelabz.bookstoreappspringboot.exception;

import com.bridgelabz.bookstoreappspringboot.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

public class BookExceptionHandler {

  @ExceptionHandler(UserRegistrationException.class)
  public ResponseEntity<ResponseDTO> handleBookException(BookException exception) {
    ResponseDTO responseDTO = new ResponseDTO("Exception while processing REST request",
            exception.getMessage());
    return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ResponseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
    List<ObjectError> errorList = exception.getBindingResult().getAllErrors();
    List<String> errorMessage = errorList.stream().map(objectError -> objectError.getDefaultMessage())
            .collect(Collectors.toList());
    ResponseDTO responseDTO = new ResponseDTO("Exception while processing REST request",errorMessage);
    return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
  }
}
