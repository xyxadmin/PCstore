package com.xyx.controller;

import com.xyx.controller.exception.*;
import com.xyx.service.exception.*;
import com.xyx.utils.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

public class HandlerController {
    //用户注册成功
    public static final int OK = 200;

    @ExceptionHandler({ServiceException.class,FileUploadException.class})//用于统一处理抛出的异常
    public JsonResult<Void> handleException(Throwable e){
        JsonResult<Void> jsonResult = new JsonResult<>(e);
        if(e instanceof UsernameDuplicatedException){
            jsonResult.setState(4000);
            jsonResult.setMessage("用户名已被使用的异常");
        } else if(e instanceof InsertException){
            jsonResult.setState(5000);
            jsonResult.setMessage("注册中产生未知错误的异常");
        }else if(e instanceof UserNotFoundException){
            jsonResult.setState(5001);
            jsonResult.setMessage("用户数据没有找到的异常");
        }else if(e instanceof PasswordNotMatchException){
            jsonResult.setState(5002);
            jsonResult.setMessage("用户密码错误的异常");
        }else if(e instanceof UpdateException){
            jsonResult.setState(5003);
            jsonResult.setMessage("更新时产生的异常");
        }else if(e instanceof FileEmptyException){
            jsonResult.setState(6001);
            jsonResult.setMessage("文件为空的异常");
        }else if(e instanceof FileSizeException){
            jsonResult.setState(6002);
            jsonResult.setMessage("文件大小的异常");
        }else if(e instanceof FileStateException){
            jsonResult.setState(6003);
            jsonResult.setMessage("文件状态的异常");
        }else if(e instanceof FileTypeException){
            jsonResult.setState(6004);
            jsonResult.setMessage("文件类型的异常");
        }else if(e instanceof FileUploadIOException){
            jsonResult.setState(6005);
            jsonResult.setMessage("文件上传io的异常");
        }else if(e instanceof AddressCountLimitException){
            jsonResult.setState(7001);
            jsonResult.setMessage("用户收货地址数量超出限值的异常");
        }else if(e instanceof AddressNotFoundException){
            jsonResult.setState(8001);
            jsonResult.setMessage("用户收货地址不存在的异常");
        }else if(e instanceof AccessDeniedException){
            jsonResult.setState(8002);
            jsonResult.setMessage("非法访问的异常");
        }else if(e instanceof DeleteException){
            jsonResult.setState(9001);
            jsonResult.setMessage("删除收货地址的异常");
        }else if(e instanceof ProductNotFoundException){
            jsonResult.setState(111);
            jsonResult.setMessage("商品不存在的异常");
        }else if(e instanceof CartNotFounException){
            jsonResult.setState(1010);
            jsonResult.setMessage("购物车数据不存在的异常");
        }
        return jsonResult;
    }

    /**
     * 获取session对象中的uid
     * @param session session对象
     * @return 当前登录的用户uid
     */
    public final Integer getUidFromSession(HttpSession session){
        return Integer.valueOf(session.getAttribute("uid").toString());
    }

    /**
     * 获取当前登录用户的username
     * @param session session对象
     * @return 当前登录用户的用户名
     */
    public final String getUsernameFromSession(HttpSession session){
        return session.getAttribute("username").toString();
    }

}
