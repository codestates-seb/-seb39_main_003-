package com.web.MyPetForApp.board.service;

import com.web.MyPetForApp.board.dto.TagDto;
import com.web.MyPetForApp.board.entity.Board;
import com.web.MyPetForApp.board.entity.BoardTag;
import com.web.MyPetForApp.board.entity.Tag;
import com.web.MyPetForApp.board.repository.BoardRepository;
import com.web.MyPetForApp.board.repository.BoardTagRepository;
import com.web.MyPetForApp.board.repository.TagRepository;
import com.web.MyPetForApp.exception.BusinessLogicException;
import com.web.MyPetForApp.exception.ExceptionCode;
import com.web.MyPetForApp.member.entity.Member;
import com.web.MyPetForApp.member.repository.MemberRepository;
import com.web.MyPetForApp.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final TagRepository tagRepository;
    private final BoardTagRepository boardTagRepository;
    private final MemberService memberService;

    @Transactional
    public Board create(Board board, List<Long> tagIds, String memberId) {

        Optional.ofNullable(idsToBoardTag(board, tagIds))
                .ifPresent(boardTags -> board.setBoardTags(boardTags));
        Optional.ofNullable(memberService.findVerifiedMember(memberId))
                .ifPresent(member -> board.setMember(member));

        return boardRepository.save(board);
    }

    @Transactional
    public Board update(Long boardId, Board board, List<Long> tagIds) {
        Board findBoard = findVerifiedBoard(boardId);

        Optional.ofNullable(board.getTitle())
                .ifPresent(title -> findBoard.setTitle(title));
        Optional.ofNullable(board.getBoardContent())
                .ifPresent(contents -> findBoard.setBoardContent(contents));
        Optional.ofNullable(idsToBoardTag(findBoard, tagIds))
                .ifPresent(boardTags -> {
                    boardTagRepository.deleteAllByBoard(boardId);
                    findBoard.setBoardTags(boardTags);
                });

        return boardRepository.save(findBoard);
    }

    public void delete(Long boardId) {
        Board board = findVerifiedBoard(boardId);
        boardTagRepository.deleteAllByBoard(boardId);

        boardRepository.delete(board);
    }

    public Page<Board> getBoard(List<Long> tagIds, int page, int size) {
        Page<Board> boards = boardRepository.findAllByTags(
                tagIds,
                tagIds.size(),
                PageRequest.of(page, size, Sort.by("modified_at").descending()));

        return boards;
    }

    public Board selectBoard(Long boardId) {
        Board board = findVerifiedBoard(boardId);

        increaseViewCnt(board);

        return board;
    }

    public List<TagDto.Response> getAllTags(){

        List<Tag> tags = tagRepository.findAll();

        return tags.stream().map(tag ->
                TagDto.Response.builder()
                        .tagId(tag.getTagId())
                        .tagName(tag.getTagName())
                        .build()
        ).collect(Collectors.toList());
    }

    //-----------------------------------------------------------------------------------------

    private List<BoardTag> idsToBoardTag(Board board, List<Long> tagIds) {
        if (!tagIds.isEmpty()) {
            List<BoardTag> boardTags = tagIds.stream()
                    .map(id ->
                            BoardTag.builder()
                                    .board(board)
                                    .tag(tagRepository.findById(id).get())
                                    .build()
                    ).collect(Collectors.toList());
            return boardTags;
        }
        return null;
    }

    public Board findVerifiedBoard(Long boardId) {
        return boardRepository.findById(boardId).orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.BOARD_NOT_FOUND)
        );
    }

    private Board increaseViewCnt(Board board) {
        board.setView(board.getView() + 1);
        return board;
    }
}
