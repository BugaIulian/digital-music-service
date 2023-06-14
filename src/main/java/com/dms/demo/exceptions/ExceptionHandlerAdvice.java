package com.dms.demo.exceptions;

import com.dms.demo.exceptions.artist.ArtistAlreadyExistsException;
import com.dms.demo.exceptions.artist.ArtistNotFoundException;
import com.dms.demo.exceptions.gender.IllegalGenderException;
import com.dms.demo.exceptions.musicgenre.IllegalMusicGenreException;
import com.dms.demo.exceptions.token.InvalidTokenException;
import com.dms.demo.exceptions.token.TokenExpiredException;
import com.dms.demo.exceptions.user.UserAlreadyExistsException;
import com.dms.demo.exceptions.user.UserNotFoundException;
import com.dms.demo.exceptions.user.UserPasswordException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerAdvice {

    @Autowired
    private final ObjectMapper objectMapper;

    public ExceptionHandlerAdvice(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> userNotFoundException(UserNotFoundException userNotFoundException) {
        return getExceptionResponse(userNotFoundException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserPasswordException.class)
    public ResponseEntity<Object> userPasswordException(UserPasswordException userPasswordException) {
        return getExceptionResponse(userPasswordException, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Object> userAlreadyExistsException(UserAlreadyExistsException userAlreadyExistsException) {
        return getExceptionResponse(userAlreadyExistsException, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ArtistNotFoundException.class)
    public ResponseEntity<Object> artistNotFoundException(ArtistNotFoundException artistNotFoundException) {
        return getExceptionResponse(artistNotFoundException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ArtistAlreadyExistsException.class)
    public ResponseEntity<Object> artistAlreadyExistsException(ArtistAlreadyExistsException artistAlreadyExistsException) {
        return getExceptionResponse(artistAlreadyExistsException, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(IllegalMusicGenreException.class)
    public ResponseEntity<Object> illegalMusicGenreException(IllegalMusicGenreException illegalMusicGenreException) {
        return getExceptionResponse(illegalMusicGenreException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalGenderException.class)
    public ResponseEntity<Object> illegalGenderException(IllegalGenderException illegalGenderException) {
        return getExceptionResponse(illegalGenderException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<Object> tokenExpiredException(TokenExpiredException tokenExpiredException) {
        return getExceptionResponse(tokenExpiredException, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<Object> invalidTokenException(InvalidTokenException invalidTokenException) {
        return getExceptionResponse(invalidTokenException, HttpStatus.UNAUTHORIZED);
    }

    private ResponseEntity<Object> getExceptionResponse(RuntimeException runtimeException, HttpStatus httpStatus) {
        Map<String, Object> result = new HashMap<>();
        result.put("Message: ", runtimeException.getMessage());
        return new ResponseEntity<>(objectToString(result), httpStatus);
    }

    private String objectToString(Object response) {
        try {
            objectMapper.findAndRegisterModules();
            return objectMapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            log.info("Json processing exception.");
        }
        return null;
    }
}