package com.wsn.service;

import com.wsn.entity.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo on 2017/9/6.
 */
@Service
public class CheckAllService {

    @Autowired
    private CheckWebServerService checkWebServerService;
    @Autowired
    private CheckDataBaseService checkDataBaseService;
    @Autowired
    private CheckActiveMQService checkActiveMQService;
    @Autowired
    private CheckFireVgwService checkFireVgwService;

    /**
     * 检查服务器
     * @throws Exception
     */
    public List<CustomResponse> checkService() throws Exception {
        List<CustomResponse> list = new ArrayList<>();
        list.add(checkActiveMQService.judgeIsHealth());
        list.add(checkWebServerService.requestFireinfoUrl());
        list.add(checkDataBaseService.checkDataBase());
        list.add(checkFireVgwService.judgeVgw());
        return list;
    }
}
