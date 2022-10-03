package com.web.MyPetForApp.board.service;

import com.web.MyPetForApp.board.dto.BoardCategoryDto;
import com.web.MyPetForApp.board.entity.Board;
import com.web.MyPetForApp.board.entity.BoardCategory;
import com.web.MyPetForApp.board.repository.BoardCategoryRepository;
import com.web.MyPetForApp.board.repository.BoardRepository;
import com.web.MyPetForApp.exception.BusinessLogicException;
import com.web.MyPetForApp.exception.ExceptionCode;
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
    private final BoardCategoryRepository boardCategoryRepository;
    private final MemberService memberService;

    @Transactional
    public Board create(Board board, String memberId, Long boardCategoryId) {

        Optional.ofNullable(memberService.findVerifiedMember(memberId))
                .ifPresent(member -> board.setMember(member));
        Optional.ofNullable(findVerifiedBoardCategory(boardCategoryId))
                .ifPresent(boardCategory -> board.setBoardCategory(boardCategory));

        return boardRepository.save(board);
    }

    public Board update(Long boardId, Board board) {
        Board findBoard = findVerifiedBoard(boardId);

        Optional.ofNullable(board.getTitle())
                .ifPresent(title -> findBoard.setTitle(title));
        Optional.ofNullable(board.getBoardContent())
                .ifPresent(contents -> findBoard.setBoardContent(contents));

        return boardRepository.save(findBoard);
    }

    public void delete(Long boardId) {
        Board board = findVerifiedBoard(boardId);

        boardRepository.delete(board);
    }

    public Page<Board> getBoard(Long boardCategoryId, int page, int size) {

        Page<Board> boards = boardRepository.findAllByBoardCategory(
                findVerifiedBoardCategory(boardCategoryId),
                PageRequest.of(page, size, Sort.by("modifiedAt").descending()));

        return boards;
    }

    public Board selectBoard(Long boardId) {
        Board board = findVerifiedBoard(boardId);

        increaseViewCnt(board);

        return board;
    }

    public List<BoardCategoryDto.Response> getCategories(int pid){

        List<BoardCategory> boardCategories = boardCategoryRepository.findAllByPid(pid);

        return boardCategories.stream()
                .map(boardCategory -> BoardCategoryDto.Response.builder()
                        .boardCategoryId(boardCategory.getCategoryId())
                        .boardCategoryName(boardCategory.getCategoryName())
                        .pid(boardCategory.getPid())
                        .build()
        ).collect(Collectors.toList());
    }

    public Page<Board> searchBoards(Long categoryId, String keyword, int page, int size) {

        Page<Board> boards = boardRepository.findByBoardCategoryAndTitleContaining(
                findVerifiedBoardCategory(categoryId),
                keyword,
                PageRequest.of(page, size, Sort.by("modifiedAt").descending()));

        return boards;
    }

    //-----------------------------------------------------------------------------------------

    public BoardCategory findVerifiedBoardCategory(Long categoryId){
        return boardCategoryRepository.findById(categoryId).orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.BOARD_NOT_FOUND)
        );
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
