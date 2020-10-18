package in.jk.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.jk.springboot.entity.Employee;

public interface EmployeeRespository extends JpaRepository<Employee, Integer> {

}
