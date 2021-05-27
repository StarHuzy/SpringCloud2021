package com.huzongyue.springcloud.controller;

import com.huzongyue.springcloud.entities.CommonResult;
import com.huzongyue.springcloud.entities.Payment;
import com.huzongyue.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    PaymentService paymentService;

    @Value("${server.port}")
    String serverPort;

    @PostMapping(value="/payment/create")
    public CommonResult<Payment> create(@RequestBody Payment payment){
       int result = paymentService.create(payment);
       if (result>0){
           return new CommonResult(200,"插入数据库成功,serverPort: "+serverPort,result);
       }else {
           return new CommonResult(444,"插入数据库失败");
       }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        if(payment != null){
            return new CommonResult(200,"查询成功，serverPort : "+serverPort,payment);
        }else {
            return new CommonResult(444,"没有对应记录,查询ID : "+ id,null);
        }
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }

    @GetMapping(value = "/payment/feign/timeout")
    public String getPaymentFeignTimeout(){

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }

}
