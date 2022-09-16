package com.web.MyPetForApp.board.mapper;

import com.web.MyPetForApp.board.dto.BoardDto;
import com.web.MyPetForApp.board.entity.Board;
import com.web.MyPetForApp.member.entity.Member;
import com.web.MyPetForApp.member.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BoardMapper {
    private final MemberRepository memberRepository;

    public Board boardPostToBoard(BoardDto.Post post){
        Member member = memberRepository.findById(post.getMemberId()).get();

        return Board.builder()
                .title(post.getTitle())
                .boardContent(post.getBoardContents())
                .member(member)
                .build();
    }
}
