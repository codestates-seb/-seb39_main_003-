package com.web.MyPetForApp.qna.service;

import com.web.MyPetForApp.item.entity.Item;
import com.web.MyPetForApp.item.repository.ItemRepository;
import com.web.MyPetForApp.member.entity.Member;
import com.web.MyPetForApp.member.repository.MemberRepository;
import com.web.MyPetForApp.qna.entity.Qna;
import com.web.MyPetForApp.qna.repository.QnaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class QnaService {
    private final MemberRepository memberRepository;
    private final QnaRepository qnaRepository;
    private final ItemRepository itemRepository;

    public Qna createQna(Qna qna, Long memberId, Long itemId){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않습니다."));
        qna.setItem(item);
        qna.setMember(member);
        return qnaRepository.save(qna);
    }

    public Qna findQna(Long qnaId){
        return qnaRepository.findById(qnaId)
                .orElseThrow(() -> new IllegalArgumentException("QnA가 존재하지 않습니다."));
    }

    public Page<Qna> findQnas(Long itemId, int page, int size){
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않습니다."));
        return qnaRepository.findAllByItem(item, PageRequest.of(page, size,
                Sort.by("createdAt").descending()));
    }
    public Qna updateQna(Long qnaId, Qna qna, Long memberId){
        Qna findQna = qnaRepository.findById(qnaId)
                .orElseThrow(() -> new IllegalArgumentException("QnA가 존재하지 않습니다."));
        if(memberId != findQna.getMember().getMemberId()) {
            throw new IllegalArgumentException("QnA는 작성자만 수정할 수 있습니다.");
        }
        findQna.update(qna);
        return findQna;
    }


    public void deleteQna(Long qnaId){
        Qna qna = qnaRepository.findById(qnaId)
                .orElseThrow(() -> new IllegalArgumentException("QnA가 존재하지 않습니다."));
        qnaRepository.delete(qna);
    }

}
