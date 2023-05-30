package com.nhnacademy.resident.advice;

import com.nhnacademy.resident.domain.error.ErrorResponse;
import com.nhnacademy.resident.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CommonControllerAdvice {

    @ExceptionHandler({AccessDeniedException.class
            , NotFoundBirthDeathReportResidentException.class
            , NotFoundFamilyRelationshipException.class
            , NotFoundHouseholdException.class
            , NotFoundHouseholdMovementAddressException.class
            , NotFoundFamilyRelationshipException.class})
    public String customException(Exception ex, Model model) {
        model.addAttribute("error",ex.getMessage());
        return "error";
    }
}
