package com.tangdou.redis.sync.service;

import com.alibaba.fastjson.JSONObject;
import com.tangdou.redis.sync.config.Redis;
import com.tangdou.redis.sync.entity.Subscribe;
import com.tangdou.redis.sync.repository.SubscribeRepository;
import com.tangdou.redis.sync.utils.RedisShuffler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SubscribeToRedisService {

    @Autowired
    private SubscribeRepository subscribeRepository;

    public void insertToRedis() {
        RedisShuffler redis = Redis.getRedis();
        List<Subscribe> subscribeList = subscribeRepository.findAll();
        for (Subscribe subscribe : subscribeList) {
            if (null == subscribe) {
                continue;
            }
            String key = "ad_subscribe_" + subscribe.getSubscribe_id();
            Map<String, String> map = new HashMap<>();
            map.put("subscribe_id", String.valueOf(subscribe.getSubscribe_id()));
            map.put("industry", subscribe.getIndustry());
            map.put("network", subscribe.getNetwork());
            map.put("region", subscribe.getRegion());
            map.put("user_id", String.valueOf(subscribe.getUser_id()));
            map.put("campaign_id", String.valueOf(subscribe.getCampaign_id()));
            map.put("channel_id", String.valueOf(subscribe.getChannel_id()));
            map.put("start_time", String.valueOf(subscribe.getStart_time()));
            map.put("end_time", String.valueOf(subscribe.getEnd_time()));
            map.put("bid", String.valueOf(subscribe.getBid()));
            map.put("state", String.valueOf(subscribe.getState()));
            map.put("ad_type", String.valueOf(subscribe.getAd_type()));
            map.put("add_time", String.valueOf(subscribe.getAdd_time()));
            map.put("add_user_id", subscribe.getAdd_user_id());
            map.put("mod_time", String.valueOf(subscribe.getMod_time()));
            map.put("mod_user_id", subscribe.getMod_user_id());
            String jsonStr = JSONObject.toJSONString(map);
            redis.safeAccess(x -> {
                x.set(key, jsonStr);
            });
        }

    }
}
