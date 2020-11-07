package HRManagement.repository;

import java.util.List;
import java.util.Optional;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import HRManagement.model.*;

public interface EmployeeService {

    List <Employee> getAllEmployees();

    Optional<Employee> employeeGetById(@NotNull Long id);

    Employee createEmployee(@NotBlank String name, String email ,String phone , String address);

    int updateEmployee(@NotNull Long id, @NotBlank String name , String email ,
                        String phone , String address);
   
    void deleteEmployeeById(Long id);

}
