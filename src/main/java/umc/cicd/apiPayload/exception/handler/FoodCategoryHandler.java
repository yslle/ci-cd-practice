package umc.cicd.apiPayload.exception.handler;

import umc.cicd.apiPayload.code.BaseErrorCode;
import umc.cicd.apiPayload.exception.GeneralException;

public class FoodCategoryHandler extends GeneralException {

    public FoodCategoryHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}