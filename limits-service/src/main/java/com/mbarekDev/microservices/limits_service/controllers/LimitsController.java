package com.mbarekDev.microservices.limits_service.controllers;

import com.mbarekDev.microservices.limits_service.bean.Limits;
import com.mbarekDev.microservices.limits_service.configuration.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsController {

  @Autowired
  private  Configuration configuration;

//    public LimitsController(Configuration configuration) {
//        this.configuration = configuration;
//    }

    @GetMapping("/limits")
    public Limits retrieveLimits(){
    return new Limits(configuration.getMinimum(), configuration.getMaximum());
       // return new Limits(1,1000);
    }


}
