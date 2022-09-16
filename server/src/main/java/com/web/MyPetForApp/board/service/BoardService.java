package com.web.MyPetForApp.board.service;

import com.web.MyPetForApp.board.entity.Board;
import com.web.MyPetForApp.board.entity.BoardTag;
import com.web.MyPetForApp.board.repository.BoardRepository;
import com.web.MyPetForApp.board.repository.TagRepository;
import com.web.MyPetForApp.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final TagRepository tagRepository;
    @Transactional
    public Board write(Board board, List<Long> tagIds){
        System.out.println(tagRepository.findById(tagIds.get(0)).get().getTagId());
        if(tagIds != null){
            List<BoardTag> boardTags = tagIds.stream()
                    .map(id ->
                            BoardTag.builder()
                            .board(board)
                            .tag(tagRepository.findById(id).get())
                            .build()
                    ).collect(Collectors.toList());
            board.setBoardTags(boardTags);
        }
        return boardRepository.save(board);
    }
}
