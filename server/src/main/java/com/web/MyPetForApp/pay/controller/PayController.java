package com.web.MyPetForApp.pay.controller;

import com.web.MyPetForApp.order.dto.OrderDto;
import com.web.MyPetForApp.pay.dto.PayDto;
import com.web.MyPetForApp.pay.entity.Pay;
import com.web.MyPetForApp.pay.mapper.PayMapper;
import com.web.MyPetForApp.pay.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Tag(name = "결제 API")
@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/v1/pay")
public class PayController {

    private final PayService payService;
    private final PayMapper mapper;

    @Operation(summary = "결제 요청")
    @ApiResponses(
            @ApiResponse(
                    responseCode = "201",
                    description = "CREATED"
            )
    )
    @PostMapping
    public ResponseEntity postPay(@Valid @RequestBody PayDto.Post post ) {
        String memberId = post.getMemberId();
        Pay pay = mapper.payPostToPay(post);
        OrderDto.Post orderPostDto = mapper.payPostToOrderPost(post);
        Pay savedPay = payService.create(pay, memberId, orderPostDto);

        return new ResponseEntity<>(mapper.payToResponse(savedPay), HttpStatus.CREATED);
    }

    @Operation(summary = "결제 정보 조회")
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "OK"
            )
    )
    // 결제 정보 조회 요청
    @GetMapping("/{payId}")
    public ResponseEntity getPay(@Positive @PathVariable Long payId) {
        Pay findPay = payService.read(payId);
        return new ResponseEntity<>(mapper.payToResponse(findPay), HttpStatus.OK);
    }
    // 결제 취소 요청
    // 결제를 취소 요청하면
    // 환불 처리는 생략하고
    // 결제 상태 -> 결제 취소
    // 주문 상태 -> 주문 취소
    // 주문 내역은 보존한다.
    public ResponseEntity cancelPay() {
        return null;
    }
    // 결제 정보 삭제 요청
    // 안쓴다
//    @DeleteMapping("/{payId}")
//    public ResponseEntity deletePay(@PathVariable Long payId) {
//        payService.delete(payId);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
}
