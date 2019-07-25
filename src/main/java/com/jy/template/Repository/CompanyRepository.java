package com.jy.template.Repository;

import com.jy.template.Beans.Company;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface CompanyRepository extends CrudRepository<Company, Long>
{
    ArrayList<Company> findByEmployeename(String employeename);
    ArrayList<Company> findByTitle(String title);
    ArrayList<Company> findByDepartment(String department);
}
