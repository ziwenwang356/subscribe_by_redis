package com.tangdou.redis.sync.service;

import com.alibaba.fastjson.JSONObject;
import com.tangdou.redis.sync.config.Redis;
import com.tangdou.redis.sync.entity.Campaign;
import com.tangdou.redis.sync.repository.CampaignRepository;
import com.tangdou.redis.sync.utils.RedisShuffler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CampaignToRedisService {

    @Autowired
    private CampaignRepository campaignRepository;

    public void insertToRedis() {
        RedisShuffler redis = Redis.getRedis();
        List<Campaign> campaignList = campaignRepository.findAll();
        for (Campaign campaign : campaignList) {
            if (null == campaign) {
                continue;
            }
            String key = "ad_campaign_" + campaign.getCampaign_id();
            Map<String, String> map = new HashMap<>();
            map.put("campaign_id", String.valueOf(campaign.getCampaign_id()));
            map.put("campaign_name", campaign.getCampaign_name());
            map.put("user_id", String.valueOf(campaign.getUser_id()));
            map.put("start_time", String.valueOf(campaign.getStart_time()));
            map.put("end_time", String.valueOf(campaign.getEnd_time()));
            map.put("budget", String.valueOf(campaign.getBudget()));
            map.put("budget_type", String.valueOf(campaign.getBudget_type()));
            map.put("budgetover", String.valueOf(campaign.getBudgetover()));
            map.put("state", String.valueOf(campaign.getState()));
            map.put("ad_type", String.valueOf(campaign.getAd_type()));
            map.put("add_time", String.valueOf(campaign.getAdd_time()));
            map.put("add_user_id", campaign.getAdd_user_id());
            map.put("mod_time", String.valueOf(campaign.getMod_time()));
            map.put("mod_user_id", campaign.getMod_user_id());
            String jsonStr = JSONObject.toJSONString(map);
            redis.safeAccess(x -> {
                x.set(key, jsonStr);
            });
        }
    }


}
