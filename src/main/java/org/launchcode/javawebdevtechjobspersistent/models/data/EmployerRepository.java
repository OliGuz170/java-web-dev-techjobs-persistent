package org.launchcode.javawebdevtechjobspersistent.models.data;

import org.launchcode.javawebdevtechjobspersistent.models.Employer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/*
Created new interface: EmployerRepository
 */

@Repository
public interface EmployerRepository extends CrudRepository<Employer, Integer> {
}



