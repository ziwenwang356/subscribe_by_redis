package com.tangdou.redis.sync.repository;

import com.tangdou.redis.sync.entity.AdUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdUserRepository extends JpaRepository<AdUser,Long> {
}
