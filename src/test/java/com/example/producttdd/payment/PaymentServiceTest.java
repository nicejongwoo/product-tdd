package com.example.producttdd.payment;

import com.example.producttdd.order.domain.Order;
import com.example.producttdd.product.domain.DiscountPolicy;
import com.example.producttdd.product.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

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
        String cardNumber = "1234-1234-1234-1234";
        Long orderId = 1L;
        PaymentRequest request = new PaymentRequest(orderId, cardNumber);

        paymentService.payment(request);
    }

    private record PaymentRequest(Long orderId, String cardNumber) {
        private PaymentRequest {
            Assert.notNull(orderId, "주문 ID는 필수입니다.");
            Assert.hasText(cardNumber, "카드 번호는 필수입니다.");
        }
    }

    private class PaymentService {
        private final PaymentPort paymentPort;

        private PaymentService(PaymentPort paymentPort) {
            this.paymentPort = paymentPort;
        }

        public void payment(PaymentRequest request) {
            Order order = paymentPort.getOrder(request.orderId());

            Payment payment = new Payment(order, request.cardNumber());
            paymentPort.pay(payment);
            paymentPort.save(payment);
        }
    }

    private interface PaymentPort {
        Order getOrder(Long orderId);

        void pay(Payment payment);

        void save(Payment payment);
    }

    private class Payment {
        private Long id;
        private final Order order;
        private final String cardNumber;

        public Payment(Order order, String cardNumber) {
            Assert.notNull(order, "주문은 필수입니다.");
            Assert.hasText(cardNumber, "카드 번호는 필수입니다.");
            this.order = order;
            this.cardNumber = cardNumber;
        }

        public void assignId(Long id) {
            this.id = id;
        }

        public Long getId() {
            return id;
        }
    }

    private class PaymentAdapter implements PaymentPort {
        private final PaymentGateway paymentGateway;
        private final PaymentRepository paymentRepository;

        private PaymentAdapter(PaymentGateway paymentGateway, PaymentRepository paymentRepository) {
            this.paymentGateway = paymentGateway;
            this.paymentRepository = paymentRepository;
        }

        @Override
        public Order getOrder(Long orderId) {
            return new Order(new Product("상품명", 10000, DiscountPolicy.NONE), 2);
        }

        @Override
        public void pay(Payment payment) {
            paymentGateway.execute(payment);
        }

        @Override
        public void save(Payment payment) {
            paymentRepository.save(payment);
        }
    }

    private interface PaymentGateway {
        void execute(Payment payment);
    }

    public class ConsolePaymentGateway implements PaymentGateway {

        @Override
        public void execute(Payment payment) {
            System.out.println("결제 완료");
        }
    }

    private class PaymentRepository {
        Map<Long, Payment> persistence = new HashMap<>();
        private Long sequence = 0L;

        public void save(Payment payment) {
            payment.assignId(++sequence);
            persistence.put(payment.getId(), payment);
        }
    }
}
