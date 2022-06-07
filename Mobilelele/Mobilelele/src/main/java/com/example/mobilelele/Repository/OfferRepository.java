package com.example.mobilelele.Repository;

import com.example.mobilelele.Models.Entity.OfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends JpaRepository<OfferEntity, Long> {
}
