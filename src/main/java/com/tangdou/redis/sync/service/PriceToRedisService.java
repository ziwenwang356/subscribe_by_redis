package com.tangdou.redis.sync.service;

import com.alibaba.fastjson.JSONObject;
import com.tangdou.redis.sync.config.Redis;
import com.tangdou.redis.sync.entity.Price;
import com.tangdou.redis.sync.repository.PriceRepository;
import com.tangdou.redis.sync.utils.RedisShuffler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PriceToRedisService {

    @Autowired
    private PriceRepository priceRepository;

    public void insertToRedis(){
        RedisShuffler redis= Redis.getRedis();
        List<Price> priceList=priceRepository.findAll();
        for (Price price:priceList) {
            if (null==price){
                continue;
            }
            String key="ad_price_"+price.getPrice_id();
            Map<String,String> map=new HashMap<>();
            map.put("price_id",String.valueOf(price.getPrice_id()));
            map.put("channel_id",String.valueOf(price.getChannel_id()));
            map.put("position_id",String.valueOf(price.getPosition_id()));
            map.put("type",String.valueOf(price.getType()));
            map.put("base_price",String.valueOf(price.getBase_price()));
            map.put("suggest_price",String.valueOf(price.getSuggest_price()));
            map.put("time_unit",String.valueOf(price.getTime_unit()));
            map.put("add_time",String.valueOf(price.getAdd_time()));
            map.put("add_user_id",price.getAdd_user_id());
            map.put("mod_time",String.valueOf(price.getMod_time()));
            map.put("mod_user_id",price.getMod_user_id());
            String jsonStr= JSONObject.toJSONString(map);
            redis.safeAccess(x->{
                x.set(key,jsonStr);
            });

        }
    }
}
