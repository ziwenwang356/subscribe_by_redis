package com.tangdou.redis.sync.service;

import com.alibaba.fastjson.JSONObject;
import com.tangdou.redis.sync.config.Redis;
import com.tangdou.redis.sync.entity.AdUser;
import com.tangdou.redis.sync.repository.AdUserRepository;
import com.tangdou.redis.sync.utils.RedisShuffler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdUserToRedisService {

    @Autowired
    private AdUserRepository adUserRepository;

    public void insertToRedis() {
        RedisShuffler redis = Redis.getRedis();
        List<AdUser> adUserList = adUserRepository.findAll();
        for (AdUser adUser : adUserList) {
            if (null == adUser) {
                continue;
            }
            redis.safeAccess(x -> {
                String key = "ad_user_" + adUser.getUser_id();
                Map<String, String> map = new HashMap<>();
                map.put("user_id", String.valueOf(adUser.getUser_id()));
                map.put("username", adUser.getUsername());
                map.put("password", adUser.getPassword());
                map.put("encrypt", adUser.getEncrypt());
                map.put("mobile", adUser.getMobile());
                map.put("email", adUser.getEmail());
                map.put("realname", adUser.getRealname());
                map.put("amount", String.valueOf(adUser.getAmount()));
                map.put("card",adUser.getCard());
                map.put("iscompany",String.valueOf(adUser.getIscompany()));
                map.put("lastloginip",adUser.getLastloginip());
                map.put("lastlogintime",String.valueOf(adUser.getLastlogintime()));
                map.put("add_time",String.valueOf(adUser.getAdd_time()));
                String jsonObjectStr = JSONObject.toJSONString(map);
                x.set(key, jsonObjectStr);
            });
        }
    }

}
