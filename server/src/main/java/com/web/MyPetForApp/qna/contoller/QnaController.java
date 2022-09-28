package com.web.MyPetForApp.qna.contoller;

import com.web.MyPetForApp.dto.MultiResponseDto;
import com.web.MyPetForApp.dto.SingleResponseDto;
import com.web.MyPetForApp.qna.dto.QnaDto;
import com.web.MyPetForApp.qna.entity.Qna;
import com.web.MyPetForApp.qna.mapper.QnaMapper;
import com.web.MyPetForApp.qna.service.QnaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "QnA API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/qna")
public class QnaController {
    private final QnaService qnaService;
    private final QnaMapper mapper;

    @Operation(summary = "QnA 등록")
    @ApiResponses(
            @ApiResponse(
                    responseCode = "201",
                    description = "CREATED"
            )
    )
    @PostMapping
    public ResponseEntity postQna(@RequestBody QnaDto.Post requestBody){
        Qna qna = mapper.qnaPostDtoToQna(requestBody);
        String memberId = requestBody.getMemberId();
        String itemId = requestBody.getItemId();
        Qna savedQna = qnaService.createQna(qna, memberId, itemId);
        QnaDto.Response response = mapper.qnaToQnaResponseDto(savedQna);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.CREATED);
    }
    @Operation(summary = "하나의 QnA 정보 조회")
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "OK"
            )
    )
    @GetMapping("/{qnaId}")
    public ResponseEntity getQna(@PathVariable Long qnaId){
        Qna qna = qnaService.findQna(qnaId);
        QnaDto.Response response = mapper.qnaToQnaResponseDto(qna);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }
    @Operation(summary = "QnA 리스트 조회")
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "OK"
            )
    )
    @GetMapping
    public ResponseEntity getQnas(@Parameter(description = "상품 식별번호") @RequestParam String itemId,
                                  @Parameter(description = "현재 페이지") @RequestParam(required = false, defaultValue = "1") int page,
                                  @Parameter(description = "한 페이지 당 QnA 수") @RequestParam(required = false, defaultValue = "8") int size){
        Page<Qna> pageQnas = qnaService.findQnas(itemId, page-1, size);
        List<Qna> qnas = pageQnas.getContent();
        List<QnaDto.Response> response = mapper.qnasToQnaResponseDto(qnas);
        return new ResponseEntity<>(new MultiResponseDto<>(response, pageQnas), HttpStatus.OK);
    }
    @Operation(summary = "QnA 정보 수정")
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "OK"
            )
    )
    @PatchMapping("/{qnaId}")
    public ResponseEntity patchQna(@PathVariable Long qnaId,
                                   @RequestBody QnaDto.Patch requestBody){
        String memberId = requestBody.getMemberId();
        Qna qna = qnaService.updateQna(qnaId, mapper.qnaPatchDtoToQna(requestBody), memberId);
        QnaDto.Response response = mapper.qnaToQnaResponseDto(qna);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }
    @Operation(summary = "QnA 삭제")
    @ApiResponses(
            @ApiResponse(
                    responseCode = "204",
                    description = "No Content"
            )
    )
    @DeleteMapping("/{qnaId}")
    public ResponseEntity deleteQna(@PathVariable Long qnaId){
        qnaService.deleteQna(qnaId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
