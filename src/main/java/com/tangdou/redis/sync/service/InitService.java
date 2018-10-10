package com.tangdou.redis.sync.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class InitService {


    @Autowired
    private AdUserToRedisService adUserToRedisService;

    @Autowired
    private CampaignToRedisService campaignToRedisService;

    @Autowired
    private CreativeInfoToRedisService creativeInfoToRedisService;

    @Autowired
    private PriceToRedisService priceToRedisService;

    @Autowired
    private SubscribeToRedisService subscribeToRedisService;

//    @Scheduled(cron="0 0 0/1 * * ?")// 整点执行一次
    @Scheduled(cron = "0 0/1 * * * *") // 没隔1分钟触发一次
    public void initMysqlToRedis(){
        System.out.println("Write to redis...");
        adUserToRedisService.insertToRedis();
        campaignToRedisService.insertToRedis();
        creativeInfoToRedisService.insertToRedis();
        priceToRedisService.insertToRedis();
        subscribeToRedisService.insertToRedis();
        System.out.println("finished write");
    }

}
