package fr.iocean.dta.employee.repository;

import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.iocean.dta.employee.model.Employee;
import fr.iocean.dta.repository.AbstractJpaRepository;

@Repository("employeeRepository")
@Profile(value = { "JPA" })
@Transactional
public class EmployeJpaRepository extends AbstractJpaRepository implements EmployeeRepository {

	@Override
	public void saveEmployee(Employee employee) {
        persist(employee);
    }
 
	@Override
    @SuppressWarnings("unchecked")
    public List<Employee> findAllEmployees() {
        Criteria criteria = getSession().createCriteria(Employee.class);
        return (List<Employee>) criteria.list();
    }
 
    public void deleteEmployeeBySsn(String ssn) {
        Query query = getSession().createSQLQuery("delete from Employee where ssn = :ssn");
        query.setString("ssn", ssn);
        query.executeUpdate();
    }
 
    @Override
    public Employee findBySsn(String ssn){
        Criteria criteria = getSession().createCriteria(Employee.class);
        criteria.add(Restrictions.eq("ssn",ssn));
        return (Employee) criteria.uniqueResult();
    }

    @Override
    public void updateEmployee(Employee employee){
        update(employee);
    }
    
    @Override
    public Employee findLastHired() {
        Criteria criteria = getSession().createCriteria(Employee.class);
        criteria.addOrder(Order.desc("hireDate"));
        return (Employee) criteria.uniqueResult();
    }

	@Override
	public void deleteAllEmployees() {
        getSession().createQuery("delete from Employee").executeUpdate();		
	}

}
