package com.springboot.employeeapiboot.one_to_more.controllers;

import com.springboot.employeeapiboot.one_to_more.dto.CompanyDTO;
import com.springboot.employeeapiboot.one_to_more.entities.Company;
import com.springboot.employeeapiboot.one_to_more.respositories.CompanyRepository;
import com.springboot.employeeapiboot.one_to_more.respositories.EmployeeRepository;
import com.springboot.employeeapiboot.one_to_more.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/api/v1/companies")
public class CompanysController {
    private CompanyService companyService;
    @Autowired
    public CompanysController(CompanyService companyService){
        this.companyService=companyService;
    }
    @Transactional
    @GetMapping(path="",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Company>> getAllCompany(){
        return ResponseEntity.ok(companyService.getAllCompany());

    }
    @Transactional
    @GetMapping(path="/{id}")
    public CompanyDTO getCompanyById(@PathVariable long id){
        return companyService.getCompanyById(id);
    }
    @Transactional
    @PostMapping(path="")
    public Company addOneCompany(@RequestBody Company company){
        return companyService.addOneCompany(company);
    }
    @Transactional
    @PutMapping("/{id}")
    public Company modifyCompanyById(@RequestBody Company company){
        return companyService.modifyCompanyById(company);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteCompanyById(@PathVariable long id){
        if(companyService.deleteCompanyById(id)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
