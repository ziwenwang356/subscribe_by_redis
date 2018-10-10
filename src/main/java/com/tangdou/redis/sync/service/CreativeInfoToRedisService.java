package com.tangdou.redis.sync.service;

import com.alibaba.fastjson.JSONObject;
import com.tangdou.redis.sync.config.Redis;
import com.tangdou.redis.sync.entity.CreativeInfo;
import com.tangdou.redis.sync.repository.CreativeInfoRepository;
import com.tangdou.redis.sync.utils.RedisShuffler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CreativeInfoToRedisService {

    @Autowired
    private CreativeInfoRepository creativeInfoRepository;

    public void insertToRedis(){
        RedisShuffler redis= Redis.getRedis();
        List<CreativeInfo> creativeInfoList=creativeInfoRepository.findAll();
        for (CreativeInfo creativeInfo:creativeInfoList) {
            if (null==creativeInfo){
                continue;
            }
            String key="ad_creativeinfo_"+creativeInfo.getCreative_id();
            Map<String,String> map=new HashMap<>();
            map.put("creative_id",String.valueOf(creativeInfo.getCreative_id()));
            map.put("subscribe_id",String.valueOf(creativeInfo.getSubscribe_id()));
            map.put("channel_id",String.valueOf(creativeInfo.getChannel_id()));
            map.put("campaign_id",String.valueOf(creativeInfo.getCampaign_id()));
            map.put("user_id",String.valueOf(creativeInfo.getUser_id()));
            map.put("title",creativeInfo.getTitle());
            map.put("describe",creativeInfo.getDescribe());
            map.put("target_url",creativeInfo.getTarget_url());
            map.put("open_url",creativeInfo.getOpen_url());
            map.put("pic_url",creativeInfo.getPic_url());
            map.put("show_time",String.valueOf(creativeInfo.getShow_time()));
            map.put("video_url",creativeInfo.getVideo_url());
            map.put("video_duration",String.valueOf(creativeInfo.getVideo_duration()));
            map.put("appinfo",creativeInfo.getAppinfo());
            map.put("appid",creativeInfo.getAppid());
            map.put("vid",creativeInfo.getVid());
            map.put("action",String.valueOf(creativeInfo.getAction()));
            map.put("state",String.valueOf(creativeInfo.getState()));
            map.put("repeat",String.valueOf(creativeInfo.getRepeat()));
            map.put("ad_type",String.valueOf(creativeInfo.getAd_type()));
            map.put("creative_type",String.valueOf(creativeInfo.getCreative_type()));
            map.put("position_type",String.valueOf(creativeInfo.getPosition_type()));
            map.put("pic_type",String.valueOf(creativeInfo.getPic_type()));
            map.put("local_industry",String.valueOf(creativeInfo.getLocal_industry()));
            map.put("refuse_reason",creativeInfo.getRefuse_reason());
            map.put("mod_info",creativeInfo.getMod_info());
            map.put("add_time",String.valueOf(creativeInfo.getAdd_time()));
            map.put("add_user_id",creativeInfo.getAdd_user_id());
            map.put("mod_time",String.valueOf(creativeInfo.getMod_time()));
            map.put("mod_user_id",creativeInfo.getMod_user_id());
            String jsonStr= JSONObject.toJSONString(map);
            redis.safeAccess(x->{
                x.set(key,jsonStr);
            });

        }

    }


}
