package com.krain.springcloud.controller;

import com.krain.springcloud.entities.CommonResult;
import com.krain.springcloud.entities.Payment;
import com.krain.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class PaymentController {

    @Value("server.port")
    private String serverPort;

    @Resource
    private PaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping("/payment/create")
    public CommonResult createPayment(@RequestBody Payment payment) {
        log.info("create payment " + payment);
        int result = paymentService.createPayment(payment);

        if (result > 0) {
            return new CommonResult(200, "success " + serverPort, result);
        }

        return new CommonResult(400, "insert error");
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult getPayment(@PathVariable("id") Long id) {
        Payment result = paymentService.getPaymentById(id);

        if (result != null) {
            return new CommonResult(200, "success " + serverPort, result);
        }

        return new CommonResult(401, "get error");
    }

    @GetMapping("/payment/discovery")
    public Object discovery() {

        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            log.info("*** element " + element);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());
        }

        return discoveryClient;
    }

}
