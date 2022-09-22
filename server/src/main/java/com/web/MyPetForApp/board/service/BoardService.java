package com.web.MyPetForApp.board.service;

import com.web.MyPetForApp.board.entity.Board;
import com.web.MyPetForApp.board.entity.BoardTag;
import com.web.MyPetForApp.board.repository.BoardRepository;
import com.web.MyPetForApp.board.repository.BoardTagRepository;
import com.web.MyPetForApp.board.repository.TagRepository;
import com.web.MyPetForApp.member.entity.Member;
import com.web.MyPetForApp.member.repository.MemberRepository;
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
    private final MemberRepository memberRepository;
    private final BoardTagRepository boardTagRepository;

    @Transactional
    public Board create(Board board, List<Long> tagIds, Long memberId) {

        Optional.ofNullable(idsToBoardTag(board, tagIds))
                .ifPresent(boardTags -> board.setBoardTags(boardTags));
        Optional.ofNullable(checkVerifiedMember(memberId))
                .ifPresent(member -> board.setMember(member));

        return boardRepository.save(board);
    }

    @Transactional
    public Board update(Long boardId, Board board, List<Long> tagIds) {
        Board findBoard = checkVerifiedBoard(boardId);

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
        Board board = checkVerifiedBoard(boardId);
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
        Board board = checkVerifiedBoard(boardId);

        increaseViewCnt(board);

        return board;
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

    private Board checkVerifiedBoard(Long boardId) {
        return boardRepository.findById(boardId).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
    }

    private Member checkVerifiedMember(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 회원 입니다.")
        );
    }

    private Board increaseViewCnt(Board board) {
        board.setView(board.getView() + 1);
        return board;
    }
}
