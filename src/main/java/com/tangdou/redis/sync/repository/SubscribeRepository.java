package com.tangdou.redis.sync.repository;

import com.tangdou.redis.sync.entity.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscribeRepository extends JpaRepository<Subscribe,Integer> {

    @Query(value = "UPDATE subscribe SET state=1 WHERE subscribe_id=?1",nativeQuery = true)
    @Modifying
    void updateTable(int subscribeId);

}
