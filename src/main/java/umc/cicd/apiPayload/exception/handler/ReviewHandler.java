package umc.cicd.apiPayload.exception.handler;

import umc.cicd.apiPayload.code.BaseErrorCode;
import umc.cicd.apiPayload.exception.GeneralException;

public class ReviewHandler extends GeneralException {

    public ReviewHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
