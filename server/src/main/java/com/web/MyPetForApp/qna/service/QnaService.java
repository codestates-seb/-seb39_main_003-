package com.web.MyPetForApp.qna.service;

import com.web.MyPetForApp.item.entity.Item;
import com.web.MyPetForApp.item.service.ItemService;
import com.web.MyPetForApp.member.entity.Member;
import com.web.MyPetForApp.member.service.MemberService;
import com.web.MyPetForApp.qna.entity.Qna;
import com.web.MyPetForApp.qna.repository.QnaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class QnaService {
    private final QnaRepository qnaRepository;
    private final MemberService memberService;
    private final ItemService itemService;

    public Qna createQna(Qna qna, String memberId, String itemId){
        Member member = memberService.findVerifiedMember(memberId);
        Item item = itemService.findVerifiedItem(itemId);
        qna.changeItem(item);
        qna.changeMember(member);
        return qnaRepository.save(qna);
    }

    public Qna findQna(Long qnaId){
        return findVerifiedQna(qnaId);
    }

    public Page<Qna> findQnas(String itemId, int page, int size){
        Item item = itemService.findVerifiedItem(itemId);
        return qnaRepository.findAllByItem(item, PageRequest.of(page, size,
                Sort.by("createdAt").descending()));
    }

    public Qna updateQna(Long qnaId, Qna qna, String memberId){
        Qna findQna = findVerifiedQna(qnaId);
        if(!memberId.equals(findQna.getMember().getMemberId())) {
            throw new IllegalArgumentException("QnA는 작성자만 수정할 수 있습니다.");
        }
        findQna.updateQna(qna);
        return findQna;
    }

    public void deleteQna(Long qnaId){
        Qna qna = findVerifiedQna(qnaId);
        qnaRepository.delete(qna);
    }

    public Qna findVerifiedQna(Long qnaId) {
        return qnaRepository.findById(qnaId)
                .orElseThrow(() -> new IllegalArgumentException("QnA가 존재하지 않습니다."));
    }

}
