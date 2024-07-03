package umc.cicd.apiPayload.exception.handler;

import umc.cicd.apiPayload.code.BaseErrorCode;
import umc.cicd.apiPayload.exception.GeneralException;

public class RegionHandler extends GeneralException {

    public RegionHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}