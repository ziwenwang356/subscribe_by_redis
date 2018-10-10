package com.tangdou.redis.sync.repository;

import com.tangdou.redis.sync.entity.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign,Integer> {

    @Query(value = "UPDATE campaign SET budgetover=1 WHERE campaign_id=?1",nativeQuery = true)
    @Modifying
    void updateTable(int campaignId);



}
