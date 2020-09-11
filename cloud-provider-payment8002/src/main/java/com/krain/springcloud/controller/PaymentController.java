package com.krain.springcloud.controller;

import com.krain.springcloud.entities.CommonResult;
import com.krain.springcloud.entities.Payment;
import com.krain.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @PostMapping("/payment/create")
    public CommonResult createPayment(@RequestBody Payment payment) {
        log.info("create payment " + payment);
        int result = paymentService.createPayment(payment);

        if (result > 0) {
            return new CommonResult(200, "success", result);
        }

        return new CommonResult(400, "insert error");
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult getPayment(@PathVariable("id") Long id) {
        Payment result = paymentService.getPaymentById(id);

        if (result != null) {
            return new CommonResult(200, "success 8002", result);
        }

        return new CommonResult(401, "get error");
    }

}
