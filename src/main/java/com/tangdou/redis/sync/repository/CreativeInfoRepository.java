package com.tangdou.redis.sync.repository;

import com.tangdou.redis.sync.entity.CreativeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CreativeInfoRepository extends JpaRepository<CreativeInfo,Long> {

    @Query(value = "UPDATE creative_info_0 SET state=1 WHERE creative_id=?1",nativeQuery = true)
    @Modifying
    void updateTable(long creativeId);
}
