package com.krain.springcloud.service;

import com.krain.springcloud.dao.PaymentDao;
import com.krain.springcloud.entities.Payment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentService {

    @Resource
    private PaymentDao paymentDao;

    public int createPayment(Payment payment) {
        return paymentDao.create(payment);
    }

    public Payment getPaymentById(Long id) {
        return paymentDao.getByPaymentId(id);
    }
}
