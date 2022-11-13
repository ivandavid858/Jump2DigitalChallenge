package com.jump2digital.jump2digitalchallenge.repository;

import com.jump2digital.jump2digitalchallenge.DTO.CountByFieldDTO;
import com.jump2digital.jump2digitalchallenge.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, String> {

    @Query(value = "SELECT * FROM company ORDER BY CAST(REGEXP_SUBSTR(size, '[^\\+-]*') AS UNSIGNED)", nativeQuery = true)
    List<Company> getAllOrderedBySize();

    @Query(value = "SELECT c FROM Company c ORDER BY c.founded")
    List<Company> getAllOrderedByFounded();

    @Query(value = "SELECT industry as name, COUNT(id) as count FROM Company GROUP BY industry")
    List<Object[]> getCountOfCompaniesInEachIndustry();

    @Query(value = "SELECT c.size as name, COUNT(c.id) as count FROM Company c GROUP BY c.size")
    List<Object[]> getCountOfCompaniesInEachSizeRange();

    @Query(value = "SELECT COALESCE(c.founded, '0') as name, COUNT(c.id) as count FROM Company c GROUP BY 1")
    List<Object[]> getCountOfCompaniesFoundedTheSameYear();
}
