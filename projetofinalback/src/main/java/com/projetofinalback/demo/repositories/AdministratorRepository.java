package com.projetofinalback.demo.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.projetofinalback.demo.models.Administrator;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Long>{

}
