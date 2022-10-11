package com.web.MyPetForApp.pay.entity;

import com.web.MyPetForApp.member.entity.Member;
import com.web.MyPetForApp.order.entity.Order;
import com.web.MyPetForApp.order.entity.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Pay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long PayId;

    @Column(nullable = false)
    private int amount;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private PayBy payBy = PayBy.ACCOUNT_TRANSFER;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private PayStatus payStatus = PayStatus.PAY_WAIT;

    @CreatedDate
    @Column(nullable = false)
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "pay")
    private Order order;

    public void changeMember(Member member) {
        if(this.member != null) {
            this.member.getPays().remove(this);
        }
        this.member = member;
        if(!member.getPays().contains(this)) {
            member.addPay(this);
        }
    }
    // Order - Pay 양방향 연관관계 편의 메서드
    public void addOrder(Order order) {
        this.order = order;
        if (order.getPay() != this) {
            order.changePay(this);
        }
    }

    public void updatePayStatus(PayStatus payStatus) {
        this.payStatus = payStatus;
    }

    public enum PayBy {
        WITHOUT_BANKBOOK(1, "무통장 입금"),
        CREDIT_CARD(2, "신용카드"),
        ACCOUNT_TRANSFER(3, "계좌이체"),
        KAKAO_PAY(4, "카카오 페이");

        private static final Map<String, String> map = Collections.unmodifiableMap(
                Stream.of(values()).collect(Collectors.toMap(Pay.PayBy::getPayDescription, Pay.PayBy::name))
        );

        @Getter
        private int payNumber;

        @Getter
        private String payDescription;

        PayBy(int payNumber, String payDescription) {
            this.payNumber = payNumber;
            this.payDescription = payDescription;
        }
        public static Pay.PayBy of(String payDescription){
            return Pay.PayBy.valueOf(map.get(payDescription));
        }
    }

    public enum PayStatus {
        PAY_WAIT(1, "결제 대기"),
        PAY_ING(2, "결제 중"),
        PAY_COMPLETE(3, "결제 완료"),
        PAY_CANCEL(4, "결제 취소"),
        PAY_FAIL(5, "결제 실패");

        @Getter
        private int stepNumber;

        @Getter
        private String stepDescription;

        PayStatus(int stepNumber, String stepDescription) {
            this.stepNumber = stepNumber;
            this.stepDescription = stepDescription;
        }
    }


}
