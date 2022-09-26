package com.web.MyPetForApp.comment.service;

import com.web.MyPetForApp.board.entity.Board;
import com.web.MyPetForApp.board.repository.BoardRepository;
import com.web.MyPetForApp.comment.dto.CommentDto;
import com.web.MyPetForApp.comment.entity.Comment;
import com.web.MyPetForApp.comment.repository.CommentRepository;
import com.web.MyPetForApp.member.entity.Member;
import com.web.MyPetForApp.member.repository.MemberRepository;
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
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    public Comment create(Comment comment){
        comment.setMember(checkVerifiedMember(comment.getMember().getMemberId()));
        comment.setBoard(checkVerifiedBoard(comment.getBoard().getBoardId()));

        return commentRepository.save(comment);
    }

    public Comment update(Long commentId, CommentDto.Patch patch){
        Comment comment = checkVerifiedComment(commentId);
        Optional.ofNullable(patch.getCommentContent())
                .ifPresent(content -> comment.setCommentContent(content));

        return commentRepository.save(comment);
    }

    public void deleteComment(Long commentId){
        Comment comment = checkVerifiedComment(commentId);

        commentRepository.delete(comment);
    }

    public Page<Comment> getComments(String where, Long id, int page, int size){
        if(where.equals("members")){
            return commentRepository.findAllByBoard(
                    checkVerifiedBoard(id),
                    PageRequest.of(page, size, Sort.by("modifiedAt").descending()));
        }else if(where.equals("boards")){
            return commentRepository.findAllByMember(
                    checkVerifiedMember(id),
                    PageRequest.of(page, size, Sort.by("modifiedAt").descending()));
        }
        throw new IllegalArgumentException("식별자 오류");
    }


//----------------------------------------------------------------------------------------------------------

    private Board checkVerifiedBoard(Long boardId) {
        return boardRepository.findById(boardId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시글 입니다.")
        );
    }
    private Member checkVerifiedMember(Long memberId){
        return memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 회원 입니다.")
        );
    }

    private Comment checkVerifiedComment(Long commentId){
        return commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 댓글 입니다.")
        );
    }
}
