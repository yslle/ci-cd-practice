package umc.cicd.apiPayload.exception.handler;

import umc.cicd.apiPayload.code.BaseErrorCode;
import umc.cicd.apiPayload.exception.GeneralException;

public class StoreHandler extends GeneralException {

    public StoreHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
