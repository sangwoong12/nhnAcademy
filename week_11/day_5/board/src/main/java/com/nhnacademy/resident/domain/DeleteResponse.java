package com.nhnacademy.resident.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@AllArgsConstructor
@Getter
public class DeleteResponse {
    private Map<String, String> pk;
    private boolean delete;

}
