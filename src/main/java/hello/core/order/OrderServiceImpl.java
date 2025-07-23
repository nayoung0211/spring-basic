package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixedDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private DiscountPolicy discountPolicy;


    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId,String itemName,int itemPrice) {
        Member member = memberRepository.findById(memberId); // 회원 정보를 받아온 뒤 조회
        int discountPrice = discountPolicy.discount(member,itemPrice); // 받은 회원 정보를 할인 정책에 넘김

        return new Order(memberId,itemName,itemPrice,discountPrice);
    }
}
