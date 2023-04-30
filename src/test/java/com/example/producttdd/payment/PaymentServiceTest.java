package com.example.producttdd.payment;

import com.example.producttdd.payment.adapter.ConsolePaymentGateway;
import com.example.producttdd.payment.adapter.PaymentAdapter;
import com.example.producttdd.payment.adapter.PaymentGateway;
import com.example.producttdd.payment.adapter.PaymentRepository;
import com.example.producttdd.payment.application.port.PaymentPort;
import com.example.producttdd.payment.application.service.PaymentRequest;
import com.example.producttdd.payment.application.service.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PaymentServiceTest {

    private PaymentService paymentService;
    private PaymentPort paymentPort;

    @BeforeEach
    void setUp() {
        PaymentGateway paymentGateway = new ConsolePaymentGateway();
        PaymentRepository paymentRepository = new PaymentRepository();

        paymentPort = new PaymentAdapter(paymentGateway, paymentRepository);
        paymentService = new PaymentService(paymentPort);
    }

    @Test
    void 상품주문_테스트() throws Exception {
        PaymentRequest request = PaymentSteps.주문결제_요청_생성();

        paymentService.payment(request);
    }

}
