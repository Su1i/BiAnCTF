package com.suli.bianctf.exception;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;

import cn.dev33.satoken.util.SaResult;
import com.suli.bianctf.common.ResponseResult;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.suli.bianctf.common.ResultCode.*;


/**
 * @author blue
 * @date 2022/3/11
 * @apiNote
 */
@ControllerAdvice(basePackages = "com.suli")
public class GlobalException {

    private static final Logger logger = LoggerFactory.getLogger(GlobalException.class);

    // 业务异常
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public ResponseResult BusinessExceptionHandler(BusinessException ex) {
        if (ex.getCode() != -1) {
            logger.error("code : " + ex.getCode() + " msg : " + ex.getMessage(), ex);
        }
        if(StringUtils.isBlank(ex.getLocalizedMessage())||StringUtils.isBlank(ex.getMessage())){
            return ResponseResult.error(ERROR.getCode(), ERROR.getDesc());
        }
        return ResponseResult.error(ex.getCode(), ex.getMessage());
    }

    // Assert业务异常
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public ResponseResult AssertExceptionHandler(IllegalArgumentException ex) {
        logger.error( " msg : " + ex.getMessage(), ex);
        if(StringUtils.isBlank(ex.getLocalizedMessage())){
            return ResponseResult.error(ERROR.getCode(),ERROR.getDesc());
        }
        return ResponseResult.error(ex.getMessage());
    }

    // 登录异常
    @ExceptionHandler(NotLoginException.class)
    public SaResult handlerNotLoginException(NotLoginException nle)
            throws Exception {

        // 打印堆栈，以供调试
        nle.printStackTrace();

        // 判断场景值，定制化异常信息
        String message = "";
        if (nle.getType().equals(NotLoginException.NOT_TOKEN)) {
            message = "未提供token";
        } else if (nle.getType().equals(NotLoginException.INVALID_TOKEN)) {
            message = "token无效";
        } else if (nle.getType().equals(NotLoginException.TOKEN_TIMEOUT)) {
            message = "token已过期";
        } else if (nle.getType().equals(NotLoginException.BE_REPLACED)) {
            message = "token已被顶下线";
        } else if (nle.getType().equals(NotLoginException.KICK_OUT)) {
            message = "token已被踢下线";
        } else {
            message = "当前会话未登录";
        }

        // 返回给前端
        return SaResult.error(message);
    }

    // 权限异常
    @ExceptionHandler(NotPermissionException.class)
    @ResponseBody
    public ResponseResult NotPermissionExceptionHandler(NotPermissionException ex) {
        logger.error( " msg : " + ex.getMessage(), ex);
        return ResponseResult.error(NO_PERMISSION.getCode(),"无此权限：" + ex.getCode());
    }

    // java异常异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseResult ExceptionHandler(Exception ex) {
        logger.error( " msg : " + ex.getMessage(), ex);
        if(StringUtils.isBlank(ex.getLocalizedMessage())){
            return ResponseResult.error(ERROR.getCode(),ERROR.getDesc());
        }
        return ResponseResult.error(ex.getMessage());
    }
}
