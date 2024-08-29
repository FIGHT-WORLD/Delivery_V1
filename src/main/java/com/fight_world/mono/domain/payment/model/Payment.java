package com.fight_world.mono.domain.payment.model;

import com.fight_world.mono.domain.model.TimeBase;
import com.fight_world.mono.domain.order.model.Order;
import com.fight_world.mono.domain.payment.dto.request.PaymentCreateRequestDto;
import com.fight_world.mono.domain.payment.model.constant.PaymentType;
import com.fight_world.mono.domain.payment.model.value_object.PaymentTotalPrice;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "p_payment")
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment extends TimeBase {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Embedded
    private PaymentTotalPrice totalPrice;

    @Column(nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @Column(nullable = false, updatable = false)
    private String pgPaymentId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false, updatable = false)
    private Order order;

    @Builder(access = AccessLevel.PRIVATE)
    public Payment(PaymentTotalPrice totalPrice, PaymentType paymentType, String pgPaymentId,
            Order order) {
        this.totalPrice = totalPrice;
        this.paymentType = paymentType;
        this.pgPaymentId = pgPaymentId;
        this.order = order;
    }

    public static Payment of(Order order, PaymentCreateRequestDto requestDto) {

        return Payment.builder()
                .order(order)
                .totalPrice(new PaymentTotalPrice(requestDto.total_price()))
                .pgPaymentId(requestDto.pg_payment_id())
                .paymentType(PaymentType.valueOf(requestDto.payment_type()))
                .build();
    }
}
