package com.krain.springcloud.controller;

import com.krain.springcloud.entities.CommonResult;
import com.krain.springcloud.entities.Payment;
import com.krain.springcloud.service.PaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @PostMapping("/payment/create")
    public CommonResult createPayment(Payment payment) {
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
            return new CommonResult(200, "success", result);
        }

        return new CommonResult(401, "get error");
    }

}
