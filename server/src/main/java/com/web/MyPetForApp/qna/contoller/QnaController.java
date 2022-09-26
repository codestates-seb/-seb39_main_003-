package com.web.MyPetForApp.qna.contoller;

import com.web.MyPetForApp.dto.MultiResponseDto;
import com.web.MyPetForApp.dto.SingleResponseDto;
import com.web.MyPetForApp.qna.dto.QnaDto;
import com.web.MyPetForApp.qna.entity.Qna;
import com.web.MyPetForApp.qna.mapper.QnaMapper;
import com.web.MyPetForApp.qna.service.QnaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/qna")
public class QnaController {
    private final QnaService qnaService;
    private final QnaMapper mapper;

    @PostMapping
    public ResponseEntity postQna(@RequestBody QnaDto.Post requestBody){
        Qna qna = mapper.qnaPostDtoToQna(requestBody);
        String memberId = requestBody.getMemberId();
        String itemId = requestBody.getItemId();
        Qna savedQna = qnaService.createQna(qna, memberId, itemId);
        QnaDto.Response response = mapper.qnaToQnaResponseDto(savedQna);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.CREATED);
    }

    @GetMapping("/{qnaId}")
    public ResponseEntity getQna(@PathVariable Long qnaId){
        Qna qna = qnaService.findQna(qnaId);
        QnaDto.Response response = mapper.qnaToQnaResponseDto(qna);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getQnas(@RequestParam String itemId,
                                  @RequestParam(required = false, defaultValue = "1") int page,
                                  @RequestParam(required = false, defaultValue = "8") int size){
        Page<Qna> pageQnas = qnaService.findQnas(itemId, page-1, size);
        List<Qna> qnas = pageQnas.getContent();
        List<QnaDto.Response> response = mapper.qnasToQnaResponseDto(qnas);
        return new ResponseEntity<>(new MultiResponseDto<>(response, pageQnas), HttpStatus.OK);
    }

    @PatchMapping("/{qnaId}")
    public ResponseEntity patchQna(@PathVariable Long qnaId,
                                   @RequestBody QnaDto.Patch requestBody){
        String memberId = requestBody.getMemberId();
        Qna qna = qnaService.updateQna(qnaId, mapper.qnaPatchDtoToQna(requestBody), memberId);
        QnaDto.Response response = mapper.qnaToQnaResponseDto(qna);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @DeleteMapping("/{qnaId}")
    public ResponseEntity deleteQna(@PathVariable Long qnaId){
        qnaService.deleteQna(qnaId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
