package com.jump2digital.jump2digitalchallenge.service;

import com.jump2digital.jump2digitalchallenge.model.Company;
import com.jump2digital.jump2digitalchallenge.repository.CompanyRepository;
import com.jump2digital.jump2digitalchallenge.utils.CountByField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final String URL = "https://challenges-asset-files.s3.us-east-2.amazonaws.com/jobMadrid/companies.json";

    @Autowired
    CompanyRepository companyRepository;

    @Override
    public List<Company> download() {
        RestTemplate restTemplate = new RestTemplate();
        String resp = restTemplate.getForObject(URL, String.class);
        JsonParser springParser = JsonParserFactory.getJsonParser();
        List<Object> companyList = springParser.parseList(resp);
        return create(companyList);
    }

    @Override
    public List<Company> create(List<Object> companyList) {
        companyRepository.deleteAll();
        List<Company> companies = new ArrayList<>();
        for (Object company : companyList) {
            if (company instanceof Map) {
                Map<String, Object> comp = (Map<String, Object>) company;
                companies.add(convertMapToEntity(comp));
            }
        }
        return companyRepository.saveAll(companies);
    }

    private Company convertMapToEntity(Map<String, Object> companies) {
        int i = 0;
        Company company = new Company();
        for (Map.Entry<String, Object> comp : companies.entrySet()) {
            switch (comp.getKey()) {
                case "id" -> company.setId(String.valueOf(comp.getValue()));
                case "website" -> company.setWebsite(String.valueOf(comp.getValue()));
                case "name" -> company.setName(String.valueOf(comp.getValue()));
                case "founded" -> company.setFounded((Integer) comp.getValue());
                case "size" -> company.setSize(String.valueOf(comp.getValue()));
                case "locality" -> company.setLocality(String.valueOf(comp.getValue()));
                case "region" -> company.setRegion(String.valueOf(comp.getValue()));
                case "country" -> company.setCountry(String.valueOf(comp.getValue()));
                case "industry" -> company.setIndustry(String.valueOf(comp.getValue()));
                case "linkedin_url" -> company.setLinkedInUrl(String.valueOf(comp.getValue()));
                default -> System.out.println("Unknown attribute");
            }
            i++;
        }
        return company;
    }

    @Override
    public List<Company> getAllOrderedBySize() {
        return companyRepository.getAllOrderedBySize();
    }

    @Override
    public List<Company> getAllOrderedByFounded() {
        return companyRepository.getAllOrderedByFounded();
    }

    @Override
    public CountByField getCountOfCompaniesByTypes() {
        var countByField = new CountByField();
        HashMap<String, Long> industries = new HashMap<>();
        HashMap<String, Long> sizeRanges = new HashMap<>();
        HashMap<Integer, Long> yearsFoundation = new HashMap<>();

        var companiesInEachIndustry = companyRepository.getCountOfCompaniesInEachIndustry();
        var companiesInEachSizeRange = companyRepository.getCountOfCompaniesInEachSizeRange();
        var companiesFoundedTheSameYear = companyRepository.getCountOfCompaniesFoundedTheSameYear();

        for (Object[] industry : companiesInEachIndustry) {
            industries.put((String)industry[0], (Long)industry[1]);
        }
        for (Object[] size : companiesInEachSizeRange) {
            sizeRanges.put((String)size[0], (Long)size[1]);
        }
        for (Object[] yearFoundation : companiesFoundedTheSameYear) {
            yearsFoundation.put((Integer)yearFoundation[0], (Long)yearFoundation[1]);
        }

        countByField.setIndustries(industries);
        countByField.setSizes(sizeRanges);
        countByField.setFounded(yearsFoundation);

        return countByField;
    }

}
