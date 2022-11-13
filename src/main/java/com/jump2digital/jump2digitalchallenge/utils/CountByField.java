package com.jump2digital.jump2digitalchallenge.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CountByField {
    private HashMap<String, Long> sizes;
    private HashMap<Integer, Long> founded;
    private HashMap<String, Long> industries;
}
