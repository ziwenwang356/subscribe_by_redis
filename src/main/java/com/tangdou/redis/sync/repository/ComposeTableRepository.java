package com.tangdou.redis.sync.repository;

import com.tangdou.redis.sync.entity.ComposeTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComposeTableRepository extends JpaRepository<ComposeTable,Long> {
}
