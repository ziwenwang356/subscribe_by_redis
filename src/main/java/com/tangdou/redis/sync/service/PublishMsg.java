package com.tangdou.redis.sync.service;

import com.tangdou.redis.sync.config.Redis;
import com.tangdou.redis.sync.utils.RedisShuffler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PublishMsg {

    Logger logger = LoggerFactory.getLogger(PublishMsg.class);
    RedisShuffler redis = Redis.getRedis();

    //    @Scheduled(cron = "0 0/1 * * * *")
    public void pubMsg() {
        String msg = "UPDATE campaign SET budgetover=1 WHERE campaign_id=1";
        redis.safeAccess(x -> x.publish("synUpdateDimensionTable", msg));
        logger.info("published a message: " + msg);
        System.out.println("published a message: " + msg);
    }
}


// UPDATE campaign SET budgetover=1 WHERE campaign_id=1

// UPDATE creative_info_0 SET state=1 WHERE creative_id=232


// UPDATE subscribe SET state=1 WHERE subscribe_id=4


//"UPDATE " + campaign + " SET " + budgetover=1 + " WHERE " + campaign_id=1

//"UPDATE " + creative_info_0 + " SET " + state=1 + " WHERE " + creative_id=232


//"UPDATE " + subscribe + " SET " + state=1 + " WHERE " + subscribe_id=4