package HRManagement.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.validation.Valid;

import org.json.simple.JSONObject;

import HRManagement.model.Employee;
import HRManagement.repository.EmployeeService;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.validation.Validated;

@Validated
@Controller("/employee")
public class EmployeeController {
    
    final EmployeeService employeeService;
    // HashMap<String,Object> additionalDetails = new HashMap<String,Object>();

    JSONObject json = new JSONObject();

    @Inject public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @Get(uri = "/dashboard" , produces=MediaType.TEXT_PLAIN)
    public String index() {
        return "Welcome to Employee Management Dashboard";
    }


    @Get(uri="/list", produces = MediaType.APPLICATION_JSON)
    public JSONObject list() {
        List<Employee> allEmp = employeeService.getAllEmployees();
        json.put("data", allEmp);
        return json;
    }

    @Get(uri="/list/{id}", produces = MediaType.APPLICATION_JSON)
    public JSONObject listOne(Long id){
        Optional<Employee> allEmp = employeeService.employeeGetById(id);
        json.put(("data"), allEmp.orElse(null));
        return json;
    }

    @Post(uri="/add", produces = MediaType.APPLICATION_JSON)
    public JSONObject addEmp(@Body @Valid Employee newEmp) {
        employeeService.createEmployee(newEmp.getName(), newEmp.getEmail(), newEmp.getPhone(), newEmp.getAddress());
        json.put("data", "Employee Added Successfully");
        return json;
    }

    @Put(uri="/update", produces = MediaType.APPLICATION_JSON)
    public JSONObject updateEmp(@Body @Valid Employee newEmp) {
        employeeService.updateEmployee(newEmp.getId(),newEmp.getName(), newEmp.getEmail(), newEmp.getPhone(), newEmp.getAddress());
        json.put("data", newEmp.getName() + " Updated Successfully");
        return json;
    }

    @Delete(uri="/delete/{id}", produces = MediaType.APPLICATION_JSON)
    public JSONObject deleteEmp(Long id) {
        employeeService.deleteEmployeeById(id);
        json.put("data", "Employee Deleted Successfully");
        return json;
    }

        
}