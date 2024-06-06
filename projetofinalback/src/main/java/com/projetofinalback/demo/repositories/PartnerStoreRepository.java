package com.projetofinalback.demo.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetofinalback.demo.models.PartnerStore;

@Repository
public interface PartnerStoreRepository extends JpaRepository<PartnerStore, Long>{
}
