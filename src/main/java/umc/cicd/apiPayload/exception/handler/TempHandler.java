package umc.cicd.apiPayload.exception.handler;

import umc.cicd.apiPayload.code.BaseErrorCode;
import umc.cicd.apiPayload.exception.GeneralException;

public class TempHandler extends GeneralException {

    public TempHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}