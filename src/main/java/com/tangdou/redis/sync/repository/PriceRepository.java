package com.tangdou.redis.sync.repository;

import com.tangdou.redis.sync.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<Price,Integer> {
}
