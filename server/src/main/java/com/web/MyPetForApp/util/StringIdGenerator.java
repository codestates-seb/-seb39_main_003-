package com.web.MyPetForApp.util;

import com.web.MyPetForApp.item.entity.ItemSeq;
import com.web.MyPetForApp.item.repository.ItemSeqRepository;
import com.web.MyPetForApp.member.entity.MemberSeq;
import com.web.MyPetForApp.member.repository.MemberSeqRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StringIdGenerator {
    private MemberSeqRepository memberSeqRepository;
    private ItemSeqRepository itemSeqRepository;

    @Autowired
    public StringIdGenerator(MemberSeqRepository memberSeqRepository, ItemSeqRepository itemSeqRepository) {
        this.memberSeqRepository = memberSeqRepository;
        this.itemSeqRepository = itemSeqRepository;
    }

    //    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ_GEN")

    int memberSeq;
    int itemSeq;

    public StringIdGenerator() {}
    public String createMemberId() {
        MemberSeq mSeq = new MemberSeq();
        memberSeqRepository.save(mSeq);
        memberSeq = mSeq.getMemberSeq();
        System.out.println("init" + memberSeq);
        return this.generateMemberKey(memberSeq);
    }

    public String createItemId() {
        ItemSeq iSeq = new ItemSeq();
        itemSeqRepository.save(iSeq);
        itemSeq = iSeq.getItemSeq();
        System.out.println("init" + itemSeq);
        return this.generateItemKey(itemSeq);
    }

    public String generateItemKey(int memberSeq) {
        return this.leftPadding(String.valueOf(memberSeq), 6);
    }

    public String generateMemberKey(int memberSeq) {
        return this.leftPadding(String.valueOf(memberSeq), 6);
    }
    public String leftPadding(String inputStr, int length) {
        String result = String.format("%1$" + length + "s", inputStr).replace(' ', '0');
        System.out.println("result" + result);
        return result;
    }

    public static void main(String args[]) {
        new StringIdGenerator();
    }
}
