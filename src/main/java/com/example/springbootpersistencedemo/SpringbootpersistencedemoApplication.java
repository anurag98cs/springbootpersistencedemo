package com.example.springbootpersistencedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

@SpringBootApplication
public class SpringbootpersistencedemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootpersistencedemoApplication.class, args);

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
		EntityManager em = emf.createEntityManager();

		System.out.println("Starting Transaction");
		em.getTransaction().begin();
		Employee employee = new Employee();
		//employee.setEmployeeId(3);
		employee.setName("Pankaj");
		System.out.println("Saving Employee to Database");

		em.persist(employee);
		em.getTransaction().commit();
		System.out.println("Generated Employee ID = " + employee.getEmployeeId());

		// get an object using primary key.
		Employee emp = em.find(Employee.class, employee.getEmployeeId());
		System.out.println("got object " + emp.getName() + " " + emp.getEmployeeId());

		// get all the objects from Employee table
		@SuppressWarnings("unchecked")
		List<Employee> listEmployee = em.createQuery("SELECT e FROM Employee e").getResultList();

		if (listEmployee == null) {
			System.out.println("No employee found . ");
		} else {
			for (Employee empl : listEmployee) {
				System.out.println("Employee name= " + empl.getName() + ", Employee id " + empl.getEmployeeId());
			}
		}
		// remove and entity
		/*
		em.getTransaction().begin();
		System.out.println("Deleting Employee with ID = " + emp.getEmployeeId());
		em.remove(emp);
		em.getTransaction().commit();
		*/
		// close the entity manager
		em.close();
		em.close();
	}
}
