package com.onlineelection.system.RegisterationService.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlineelection.system.RegisterationService.Entity.Campaign;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {
    Campaign findByCampaignId(Long campaingId);
}
