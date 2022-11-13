package com.jump2digital.jump2digitalchallenge.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jump2digital.jump2digitalchallenge.DTO.CountByFieldDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CountByField {
    private HashMap<String, Long> sizes;
    private HashMap<Integer, Long> founded;
    private HashMap<String, Long> industries;
}
