package com.wsn.controller;

import com.wsn.entity.CustomResponse;
import com.wsn.service.*;
import com.wsn.untils.SendSimpleEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lenovo
 * Date: 2017/9/3
 * Time: 9:17
 * Description:
 */
@Controller
@RequestMapping("/healthcheck")
public class CheckAllServiceController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CheckAllServiceController.class);

    @Autowired
    private SendSimpleEmail sendSimpleEmail;
    @Autowired
    private CheckAllService checkAllService;
    @Autowired
    private Environment environment;

    @RequestMapping("/checkallservice")
    public String checkAllService(ModelMap model) {
        List<CustomResponse> list;
        try {
            list = checkAllService.checkService();
            model.addAttribute("list", list);
            model.addAttribute("platformName", environment.getProperty("platform.name"));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            try {
                sendSimpleEmail.sendSimpleEmail("healthCheck is exception",e.toString());
            } catch (Exception ex) {
                LOGGER.error(ex.getMessage());
            }
        }
        return "index";
    }


}
