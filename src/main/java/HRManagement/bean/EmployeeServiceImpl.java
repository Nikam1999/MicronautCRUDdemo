package HRManagement.bean;

import java.util.List;
import java.util.Optional;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import HRManagement.model.Employee;
import HRManagement.repository.EmployeeService;


@Singleton
public class EmployeeServiceImpl implements EmployeeService {

    @PersistenceContext
    private EntityManager entityManager;

    public EmployeeServiceImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Transactional
    public List<Employee> getAllEmployees() {
        TypedQuery<Employee> allEmp = entityManager.createQuery("SELECT h FROM Employee as h", Employee.class);
        return allEmp.getResultList();
    }

    @Override
    @Transactional
    public Optional<Employee> employeeGetById(Long id){
        return Optional.ofNullable(entityManager.find(Employee.class, id));
    }

    @Override
    @Transactional
    public Employee createEmployee(@NotBlank String name, String email ,String phone , String address){
        Employee Emp = new Employee(name, email, phone, address);
        System.out.println("Body"+Emp.toString());
        entityManager.persist(Emp);
        return Emp;
    }

    @Override
    @Transactional
    public int updateEmployee(@NotNull Long id, @NotBlank String name ,String email, String phone, String address) {
        return entityManager.createQuery("UPDATE Employee e SET name = :name , email= :email , phone= :phone, address= :address where id = :id")
                .setParameter("name", name)
                .setParameter("id", id)
                .setParameter("email", email)
                .setParameter("phone", phone)
                .setParameter("address", address)
                .executeUpdate();
    }

    @Override
    @Transactional
    public void deleteEmployeeById(@NotNull Long id) {
        employeeGetById(id).ifPresent(employee -> entityManager.remove(employee));
    }
}