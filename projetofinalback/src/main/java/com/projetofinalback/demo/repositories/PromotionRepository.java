package com.projetofinalback.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.projetofinalback.demo.models.Promotion;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long>{

}
