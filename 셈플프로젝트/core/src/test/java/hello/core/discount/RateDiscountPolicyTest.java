package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {
    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP 10% 할인됨.")
    void vip_o() {
        //g
        Member memberVIP = new Member(1L, "memberVIP", Grade.VIP);
        //w
        int discount = discountPolicy.discount(memberVIP,10000);
        //t
        Assertions.assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("VIP 10% 할no인됨.")
    void vip_X() {
        //g
        Member memberVIP = new Member(1L, "memberVIP", Grade.BASIC);
        //w
        int discount = discountPolicy.discount(memberVIP,10000);
        //t
        Assertions.assertThat(discount).isEqualTo(0);
    }
}