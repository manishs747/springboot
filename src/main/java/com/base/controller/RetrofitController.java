package com.base.controller;


import com.base.entity.response.BaseResponse;
import com.base.entity.response.SuccessResponse;
import com.base.service.RetrofitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/retrofit")
public class RetrofitController {


    @Autowired
    RetrofitService retrofitService;


    @RequestMapping(
            value = {"/sample"},
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public BaseResponse createTransaction(){
            return  new SuccessResponse(retrofitService.getRetrofitData());

    }

}
