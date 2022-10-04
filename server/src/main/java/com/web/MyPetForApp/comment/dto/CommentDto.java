package com.web.MyPetForApp.comment.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class CommentDto {
    @Schema(name = "CommentPost", description = "댓글 Post 요청 모델")
    @Getter
    public static class Post{
        @Schema(description = "게시글 식별번호", example = "1")
        private Long boardId;
        @Schema(description = "회원 식별번호", example = "000001")
        private String memberId;
        @Schema(description = "댓글 내용", example = "댓글1")
        private String commentContent;
    }
    @Schema(name = "CommentPatch", description = "댓글 Patch 요청 모델")
    @Getter
    public static class Patch{
        @Schema(description = "댓글 내용", example = "댓글1")
        @NotBlank
        private String commentContent;
    }
    @Schema(name = "CommentResponse", description = "댓글 Response 요청 모델")
    @Getter
    @Setter
    @Builder
    public static class Response{
        @Schema(description = "게시글 식별번호", example = "1")
        private Long boardId;
        @Schema(description = "댓글 식별번호", example = "1")
        private Long commentId;
        @Schema(description = "회원 별명", example = "별명1")
        private String nickName;
        @Schema(description = "댓글 내용", example = "댓글1")
        private String commentContent;
        @Schema(description = "마지막 수정 일자", example = "2022-09-28 22:26:56")
        private LocalDateTime modifiedAt;
    }
}
