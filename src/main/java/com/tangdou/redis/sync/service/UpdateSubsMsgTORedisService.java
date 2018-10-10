package com.tangdou.redis.sync.service;

import com.tangdou.redis.sync.entity.Campaign;
import com.tangdou.redis.sync.repository.CampaignRepository;
import com.tangdou.redis.sync.repository.CreativeInfoRepository;
import com.tangdou.redis.sync.repository.SubscribeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UpdateSubsMsgTORedisService {


    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private SubscribeRepository subscribeRepository;

    @Autowired
    private CreativeInfoRepository creativeInfoRepository;


    private static final String CAMPAIGN = "campaign";
    private static final String SUBSCRIBE = "subscribe";
    private static final String CREATIVE_INFO_0 = "creative_info_0";

    Logger logger = LoggerFactory.getLogger(UpdateSubsMsgTORedisService.class);
    public void nothingToDo() {
        List<Campaign> campaignList = campaignRepository.findAll();
        for (Campaign campaign : campaignList) {
            System.out.println(campaign.getCampaign_id());
        }
    }


    public void updateMysql(String msg) {
        if ("".equals(msg) || null == msg) {
            return;
        }
        if (msg.contains(CAMPAIGN)) {
            if (msg.split("=").length < 3) {
                logger.info("UPDATE campaign SET budgetover=1 WHERE campaign_id=? ,this message broken!,and its message is: " + msg);
            } else {
                String id = msg.split("=")[2];
                this.campaignRepository.updateTable(Integer.parseInt(id));
                System.out.println("Execute sucessfully in campaign.");
            }
        } else if (msg.contains(SUBSCRIBE)) {
            if (msg.split("=").length < 3) {
                logger.info("UPDATE subscribe SET state=1 WHERE subscribe_id=?,this message broken!,and its message is: " + msg);
            } else {
                String id = msg.split("=")[2];
                this.subscribeRepository.updateTable(Integer.parseInt(id));
                System.out.println("Execute sucessfully in subscribe.");
            }
        } else if (msg.contains(CREATIVE_INFO_0)) {
            if (msg.split("=").length < 3) {
                logger.info("UPDATE creative_info_0 SET state=1 WHERE creative_id=?,this message broken!,and its message is: " + msg);
            } else {
                String id = msg.split("=")[2];
                this.creativeInfoRepository.updateTable(Long.parseLong(id));
                System.out.println("Execute sucessfully in creative_info_0.");
            }
        } else {
            return;
        }
    }



}
