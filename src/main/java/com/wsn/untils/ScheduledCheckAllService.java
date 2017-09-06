package com.wsn.untils;

import com.wsn.service.CheckAllService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: Lenovo
 * Date: 2017/9/2
 * Time: 9:17
 * Description:
 */
@Component
public class ScheduledCheckAllService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledCheckAllService.class);

    @Autowired
    private SendSimpleEmail sendSimpleEmail;
    @Autowired
    private CheckAllService checkAllService;

    @Scheduled(fixedRate = 5*60*1000)
    public void checkAllService() {
        try {
            checkAllService.checkService();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            try {
                sendSimpleEmail.sendSimpleEmail("healthCheck is exception",e.toString());
            } catch (Exception ex) {
                LOGGER.error(ex.getMessage());
            }
        }

    }
}
