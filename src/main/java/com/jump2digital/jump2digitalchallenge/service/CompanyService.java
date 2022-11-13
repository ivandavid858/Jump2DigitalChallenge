package com.jump2digital.jump2digitalchallenge.service;

import com.jump2digital.jump2digitalchallenge.model.Company;
import com.jump2digital.jump2digitalchallenge.utils.CountByField;

import java.util.List;

public interface CompanyService {

    List<Company> download();

    List<Company> create(List<Object> companies);


    List<Company> getAllOrderedBySize();

    List<Company> getAllOrderedByFounded();

    CountByField getCountOfCompaniesByTypes();
}
