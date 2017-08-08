package fr.iocean.dta.employee.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import fr.iocean.dta.employee.model.Employee;
import fr.iocean.dta.employee.service.EmployeeService;
import fr.iocean.dta.repository.AbstractJdbcRepository;

@Repository("employeeRepository")
@Profile(value = { "JDBC" })
public class EmployeeJdbcRepository extends AbstractJdbcRepository implements EmployeeRepository {

    @Override
    public void saveEmployee(Employee employee) {
        getJdbcTemplate().update(
                "insert into employee(firstname, lastname, ssn, hiredate, salary) " +
                        "values (?, ?, ?, ?, ?)",
                employee.getFirstName(), employee.getLastName(),
                employee.getSsn(), employee.getHireDate(),
                employee.getSalary());
    }

    @Override
    public List<Employee> findAllEmployees() {
        return getJdbcTemplate().query(
                "select id, firstname, lastname, ssn, hiredate, salary from employee",
                new EmployeeMapper());
    }

    @Override
    public Employee findBySsn(String ssn) {
        return getJdbcTemplate().queryForObject(
                "select id, firstname, lastname, ssn, hiredate, salary from employee where ssn = ?",
                new Object[]{ssn},
                new EmployeeMapper());
    }

    @Override
    public void updateEmployee(Employee employee) {
        int rowsAffected = getJdbcTemplate().update(
                "update employee set firstname = ?, lastname = ?, ssn = ?, hiredate = ?, salary = ? where id = ?",
                employee.getFirstName(), employee.getLastName(),
                employee.getSsn(), employee.getHireDate(),
                employee.getSalary(), employee.getId());
        if (rowsAffected == 0){
        	throw new RuntimeException();
        }

    }

    @Override
    public Employee findLastHired() {
        return getJdbcTemplate().queryForObject(
                "select id, firstname, lastname, ssn, hiredate, salary from employee order by hiredate desc limit 1",
                new EmployeeMapper());
    }

    private static final class EmployeeMapper implements RowMapper<Employee> {
    	@Override
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
            Employee employee = new Employee();
            employee.setId(rs.getLong("id"));
            employee.setFirstName(rs.getString("firstname"));
            employee.setLastName(rs.getString("lastname"));
            employee.setSsn(rs.getString("ssn"));
            employee.setHireDate(rs.getDate("hiredate"));
            employee.setSalary(rs.getBigDecimal("salary"));
            return employee;
        }

		
		
    }

}
