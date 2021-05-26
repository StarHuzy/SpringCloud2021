package com.huzongyue.springcloud.service.impl;

import com.huzongyue.springcloud.dao.PaymentDao;
import com.huzongyue.springcloud.entities.Payment;
import com.huzongyue.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
