package com.web.MyPetForApp.qna.contoller;

import com.web.MyPetForApp.dto.MultiResponseDto;
import com.web.MyPetForApp.dto.SingleResponseDto;
import com.web.MyPetForApp.qna.dto.AnswerDto;
import com.web.MyPetForApp.qna.dto.QuestionDto;
import com.web.MyPetForApp.qna.entity.Answer;
import com.web.MyPetForApp.qna.entity.Question;
import com.web.MyPetForApp.qna.mapper.AnswerMapper;
import com.web.MyPetForApp.qna.mapper.QuestionMapper;
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
    private final QuestionMapper questionMapper;
    private final AnswerMapper answerMapper;

    @Operation(summary = "QnA 등록")
    @ApiResponses(
            @ApiResponse(
                    responseCode = "201",
                    description = "CREATED"
            )
    )
    @PostMapping("/question")
    public ResponseEntity postQuestion(@RequestBody QuestionDto.Post requestBody){
        Question question = questionMapper.questionPostDtoToQuestion(requestBody);
        String memberId = requestBody.getMemberId();
        String itemId = requestBody.getItemId();
        Question savedQuestion = qnaService.createQuestion(question, memberId, itemId);
        QuestionDto.Response response = questionMapper.questionToQuestionResponseDto(savedQuestion);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.CREATED);
    }
    @Operation(summary = "하나의 QnA 정보 조회")
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "OK"
            )
    )
    @GetMapping("/question/{questionId}")
    public ResponseEntity getQuestion(@PathVariable Long questionId){
        Question question = qnaService.findQuestion(questionId);
        QuestionDto.Response response = questionMapper.questionToQuestionResponseDto(question);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }
    @Operation(summary = "QnA 리스트 조회")
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "OK"
            )
    )
    @GetMapping("/question")
    public ResponseEntity getQuestions(@Parameter(description = "상품 식별번호") @RequestParam String itemId,
                                  @Parameter(description = "현재 페이지") @RequestParam(required = false, defaultValue = "1") int page,
                                  @Parameter(description = "한 페이지 당 QnA 수") @RequestParam(required = false, defaultValue = "8") int size){
        Page<Question> pageQnas = qnaService.findQuestions(itemId, page-1, size);
        List<Question> questions = pageQnas.getContent();
        List<QuestionDto.Response> response = questionMapper.questionsToQuestionResponseDto(questions);
        return new ResponseEntity<>(new MultiResponseDto<>(response, pageQnas), HttpStatus.OK);
    }
    @Operation(summary = "QnA 정보 수정")
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "OK"
            )
    )
    @PatchMapping("/question/{questionId}")
    public ResponseEntity patchQuestion(@PathVariable Long questionId,
                                   @RequestBody QuestionDto.Patch requestBody){
        String memberId = requestBody.getMemberId();
        Question question = qnaService.updateQuestion(questionId, questionMapper.questionPatchDtoToQuestion(requestBody), memberId);
        QuestionDto.Response response = questionMapper.questionToQuestionResponseDto(question);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }
    @Operation(summary = "QnA 삭제")
    @ApiResponses(
            @ApiResponse(
                    responseCode = "204",
                    description = "No Content"
            )
    )
    @DeleteMapping("/question/{qnaId}")
    public ResponseEntity deleteQuestion(@PathVariable Long qnaId){
        qnaService.deleteQuestion(qnaId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    ---------------------------------------------------------------------------------------------

    @PostMapping("/answer")
    public ResponseEntity postAnswer(@RequestBody AnswerDto.Post post){
        Answer answer = answerMapper.answerPostToAnswer(post);
        qnaService.createAnswer(answer);
        AnswerDto.Response response = answerMapper.answerToAnswerResponse(answer);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @PatchMapping("/answer")
    public ResponseEntity patchAnswer(@RequestBody AnswerDto.Post patch){
        Answer answer = qnaService.updateAnswer(patch);
        AnswerDto.Response response = answerMapper.answerToAnswerResponse(answer);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @GetMapping("/answer/{questionId}")
    public ResponseEntity getAnswer(@PathVariable Long questionId){
        Answer answer = qnaService.findAnswer(questionId);
        AnswerDto.Response response = answerMapper.answerToAnswerResponse(answer);

        return new ResponseEntity(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @DeleteMapping("/answer/{answerId}")
    public ResponseEntity deleteAnswer(@PathVariable Long answerId){
        qnaService.deleteAnswer(answerId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
