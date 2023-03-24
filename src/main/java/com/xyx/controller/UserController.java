package com.xyx.controller;

import com.xyx.controller.exception.*;
import com.xyx.entity.User;
import com.xyx.service.UserService;
import com.xyx.utils.JsonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequestMapping("/web/user")
@RestController
public class UserController extends HandlerController{
    @Resource
    private UserService userService;

    @RequestMapping("/reg")
    public JsonResult<Void> register(User user){
        userService.addUser(user);
        return  new JsonResult<>(OK);
    }
    @RequestMapping("/login")
    public JsonResult<User> login(String username,String password,
                                    HttpSession session){
        User user = userService.login(username, password);
        //向session对象完成数据绑定
        session.setAttribute("uid", user.getUid());
        session.setAttribute("username", user.getUsername());
        //获取session绑定的数据
        System.out.println(getUidFromSession(session));
        System.out.println(getUsernameFromSession(session));
        return new JsonResult<User>(OK,user);
    }

    @RequestMapping("/changePassword")
    public JsonResult<Void> changePassword(String oldPassword,
                                           String newPassword,
                                           HttpSession session){
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changePassword(uid, username, oldPassword, newPassword);
        System.out.println("=========修改成功=========");
        return new JsonResult<Void>(OK);
    }
    @RequestMapping("/getUserByUid")
    public JsonResult<User> getUserByUid(HttpSession session){
        User user = userService.getUserByUid(getUidFromSession(session));
        User userByUid = userService.getUserByUid(user.getUid());
        System.out.println("======"+userByUid+"=======");
        return new JsonResult<>(OK,user);
    }
    @RequestMapping("/updateUser")
    public JsonResult<Void> updateUser(User user,HttpSession session){
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.updateUser(user, uid,username);
        System.out.println("uid="+uid);
        System.out.println("username="+username);
        System.out.println("user="+user);
        return new JsonResult<>(OK);
    }
    //设置上传文件的最大值
    public static final int AVATAR_MAX_SIZE = 10 * 1024 * 1024;
    //限值上传文件的类型
    public static final List<String> AVATAR_TYPE = new ArrayList<>();
    static {
        AVATAR_TYPE.add("image/jpeg");
        AVATAR_TYPE.add("image/png");
        AVATAR_TYPE.add("image/bmp");
        AVATAR_TYPE.add("image/gif");
    }
    @RequestMapping("/updateAvatar")
    public  JsonResult<String> updateAvatar(HttpSession session,
                                            MultipartFile file){
        if(file.isEmpty()){
            throw new FileEmptyException("文件为空");
        }
        if(file.getSize() > AVATAR_MAX_SIZE){
            throw new FileSizeException("文件大小超出限值");
        }
        String type = file.getContentType();
        if(!AVATAR_TYPE.contains(type)){
            throw new FileTypeException("文件类型不支持");
        }
        //上传的文件../upload/文件.png
        String path = session.getServletContext().getRealPath("upload");
        //让file对象指向这个路径，file是否存在
        File file1 = new File(path);
        if(!file1.exists()){
            file1.mkdirs();
        }
        //获取文件名称，使用uuid生成字符串作为文件名
        String originalFilename = file.getOriginalFilename();
        System.out.println("文件名==="+originalFilename);
        //获取文件后缀
        int index = originalFilename.lastIndexOf(".");
        String fileSuffix = originalFilename.substring(index);
        //随机生成前缀,生成文件名
        String fileName = UUID.randomUUID().toString().toUpperCase()+fileSuffix;
        //创建空文件
        File dest = new File(file1,fileName);
        //参数file中的数据写入到空文件中
        try {
            file.transferTo(dest);
        } catch (IOException exception) {
            throw new FileUploadIOException("文件读写异常");
        }catch (FileStateException e){
            throw new FileStateException("文件状态异常");
        }
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        //返回头像的路径
        String avatar = "/upload/"+fileName;
        userService.updateAvatar(uid,avatar,username);
        System.out.println(avatar);
        return new JsonResult<>(OK,avatar);
    }

}
