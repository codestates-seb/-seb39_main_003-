package com.web.MyPetForApp.comment.service;

import com.web.MyPetForApp.board.service.BoardService;
import com.web.MyPetForApp.comment.dto.CommentDto;
import com.web.MyPetForApp.comment.entity.Comment;
import com.web.MyPetForApp.comment.repository.CommentRepository;
import com.web.MyPetForApp.exception.BusinessLogicException;
import com.web.MyPetForApp.exception.ExceptionCode;
import com.web.MyPetForApp.member.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final MemberService memberService;
    private final BoardService boardService;

    public Comment create(Comment comment){
        comment.changeMember(memberService.findVerifiedMember(comment.getMember().getMemberId()));
        comment.changeBoard(boardService.findVerifiedBoard(comment.getBoard().getBoardId()));

        return commentRepository.save(comment);
    }

    public Comment update(Long commentId, CommentDto.Patch patch){
        Comment comment = findVerifiedComment(commentId);
        comment.updateCommentContent(patch.getCommentContent());

        return commentRepository.save(comment);
    }

    public void deleteComment(Long commentId){
        Comment comment = findVerifiedComment(commentId);

        commentRepository.delete(comment);
    }

    public Page<Comment> getComments(String where, Long boardId,String memberId, int page, int size){
        if(where.equals("boards")){
            return commentRepository.findAllByBoard(
                    boardService.findVerifiedBoard(boardId),
                    PageRequest.of(page, size, Sort.by("createdAt").descending()));
        }else if(where.equals("members")){
            return commentRepository.findAllByMember(
                    memberService.findVerifiedMember(memberId),
                    PageRequest.of(page, size, Sort.by("createdAt").descending()));
        }
        throw new BusinessLogicException(ExceptionCode.DOMAIN_IS_INVALID);
    }


//----------------------------------------------------------------------------------------------------------


    private Comment findVerifiedComment(Long commentId){
        return commentRepository.findById(commentId).orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.COMMENT_NOT_FOUND)
        );
    }
}
