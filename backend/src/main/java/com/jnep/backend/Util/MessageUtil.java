package com.jnep.backend.Util;

public class MessageUtil {

    public MessageUtil() {
        super();
        //TODO Auto-generated constructor stub
    }

    private final static String joinSuccessMessage = "정상적으로 회원가입 되었습니다.";

    private final static String saveSuccessMessage = "정상적으로 등록 되었습니다.";

    private final static String updateSuccessMessage = "정상적으로 수정 되었습니다.";

    public static String joinSuccess(){
        return joinSuccessMessage;
    }

    public static String UpdateSuccess(){
        return updateSuccessMessage;
    }

    public static String SaveSuccessMessage(){
        return saveSuccessMessage;
    }
}
