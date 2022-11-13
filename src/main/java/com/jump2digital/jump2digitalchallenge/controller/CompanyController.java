package com.jump2digital.jump2digitalchallenge.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jump2digital.jump2digitalchallenge.DTO.CompanyDTO;
import com.jump2digital.jump2digitalchallenge.model.Company;
import com.jump2digital.jump2digitalchallenge.service.CompanyService;
import com.jump2digital.jump2digitalchallenge.utils.CountByField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@JsonInclude(JsonInclude.Include.NON_NULL)
@RequestMapping("companies")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @GetMapping("/download_all")
    public List<Company> download() {
        return companyService.download();
    }

    @GetMapping("/all_ordered_by_size")
    public List<Company> getAllOrderedBySize() {
        return companyService.getAllOrderedBySize();
    }

    @GetMapping("/all_ordered_by_founded")
    public List<Company> getAllOrderedByFounded() {
        return companyService.getAllOrderedByFounded();
    }

    @GetMapping("/all_counted_by_types")
    public CountByField getCountOfCompaniesByTypes() {
        var counts = companyService.getCountOfCompaniesByTypes();
        return counts;
    }

}
