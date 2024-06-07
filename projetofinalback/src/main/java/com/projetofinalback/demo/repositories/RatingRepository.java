package com.projetofinalback.demo.repositories;

import com.projetofinalback.demo.models.*;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long>{
    @Transactional
    void deleteByUserId(User user);
    @Transactional
    void deleteByPartnerId(PartnerStore partnerStore);
}
