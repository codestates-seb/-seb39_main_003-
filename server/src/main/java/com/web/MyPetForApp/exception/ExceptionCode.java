package com.web.MyPetForApp.exception;

import lombok.Getter;

public enum ExceptionCode {
    MEMBER_NOT_FOUND(404, "Member not found"),
    MEMBER_EXISTS(409, "Member exists"),
    CANNOT_CHANGE_PASSWORD(409, "Password must not be the same as before"),
    ITEM_NOT_FOUND(404, "Item not found"),
    ITEM_CATEGORY_NOT_FOUND(404, "Item Category not found"),
    OUT_OF_STOCK(400, "Out of stock"),
    CANNOT_CHANGE_ORDER(409, "Order can not change"),
    ORDER_NOT_FOUND(404, "Order not found"),
    CANNOT_CHANGE_QNA(409, "QnA can not change"),
    QUESTION_NOT_FOUND(404, "Quetion not found"),
    ANSWER_NOT_FOUND(404, "Answer not found"),
    CANNOT_CHANGE_REVIEW(409, "Review can not change"),
    REVIEW_NOT_FOUND(404, "Review not found"),
    CANNOT_CHANGE_CART_ITEM(409, "Cart item can not change"),
    CART_ITEM_NOT_FOUND(404, "Cart item not found"),
    CART_ITEM_EXISTS(409, "Cart item exists"),
    PAY_NOT_FOUND(404, "Pay not found"),
    BOARD_NOT_FOUND(404, "Board not found"),
    COMMENT_NOT_FOUND(404, "Comment not found"),
    CANNOT_UPLOAD_FILE(409, "Can not upload file"),
    CANNOT_DOWNLOAD_FILE(409, "Can not download file"),
    FILE_NOT_FOUND(404, "File Not found"),
    FILE_UPLOAD_LIMIT_ONE(409, "The number of files is limited to one"),
    FILE_UPLOAD_LIMIT_FIVE(409, "The number of files is limited to five"),
    DOMAIN_IS_INVALID(400, "Domain is invalid"),
    EXTENSION_IS_INVALID(400, "File's extension is invalid"),
    PRICE_DIFFERENT(400, "Request price is different from orderPrice");
    EXTENSION_IS_INVALID(400, "File's extension is invalid");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message){
        this.status = code;
        this.message = message;
    }
}
