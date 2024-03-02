package com.movie.moviedb.web.validation;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidateResponseBody {
    public static ResponseEntity<?> throwErrors(BindingResult result){
        if (result.hasErrors()) {
            List<Map<String, String>> errorsList = new ArrayList<>();
            for (FieldError error : result.getFieldErrors()) {
                Map<String, String> errorMap = new HashMap<>();
                errorMap.put("field", error.getField());
                errorMap.put("description", error.getDefaultMessage());
                errorsList.add(errorMap);
            }
            Map<String, List<Map<String, String>>> response = new HashMap<>();
            response.put("errors", errorsList);
            return ResponseEntity.badRequest().body(response);
        }

        return null;
    }

}
