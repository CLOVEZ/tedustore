package cn.tedu.store.controller;

import cn.tedu.store.entity.User;
import cn.tedu.store.service.IUserService;
import cn.tedu.store.service.ex.FileEmptyException;
import cn.tedu.store.service.ex.FileSizeException;
import cn.tedu.store.service.ex.FileTypeException;
import cn.tedu.store.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController extends BaseController {
	@Autowired
	private IUserService userService;
	
	@RequestMapping("/reg")
	public ResponseResult<Void> reg(User user) {
		userService.reg(user);
		return new ResponseResult<>(SUCCESS);
	}
	
	@RequestMapping("/login")
	public ResponseResult<User> login(
		@RequestParam("username") String username,
		@RequestParam("password") String password,
		HttpSession session) {
		// 执行登录验证
		User data = userService.login(username, password);
		// 向Session中封装用户信息
		session.setAttribute("uid", data.getUid());
		session.setAttribute("username", data.getUsername());
		// 返回
		return new ResponseResult<>(SUCCESS, data);
	}
	
	@RequestMapping("/change_password")
	public ResponseResult<Void> changePassword(
		@RequestParam("old_password") String oldPassword,
		@RequestParam("new_password") String newPassword,
		HttpSession session) {
		// 从session中获取uid和username
		Integer uid = getUidFromSession(session);
		String username = session.getAttribute("username").toString();
		// 执行修改密码：service.changePassword(uid,username,oldPassword,newPassword)
		userService.changePassword(uid, username, oldPassword, newPassword);
		// 返回结果
		return new ResponseResult<>(SUCCESS);
	}

	@RequestMapping("change_info")
	public ResponseResult<Void> changeInfo(User user , HttpSession session){
		Integer uid = getUidFromSession(session);
		String username = session.getAttribute("username").toString();
		user.setUid(uid);
		user.setModifiedUser(username);
		//调用业务层对象执行修改个人资料
		userService.changeInfo(user);
		return new ResponseResult<>(SUCCESS);

	}

	@RequestMapping("details")
	public  ResponseResult<User> getByUid(HttpSession session){
		Integer uid = getUidFromSession(session);
		User user =  userService.getByUid(uid);
		return new ResponseResult<>(SUCCESS,user);
	}

	/**
	 * 上传头像的文件夹名称
	 */
	public static final String UPLOAD_DIR = "upload";

	/**
	 * 上传头像的最大大小
	 */
	public static final long UPLOAD_AVATAR_MAX_SIZE = 1*1024*1024;
	/**
	 * 上传头像的文件类型
	 */
	public static final List<String> UPLOAD_AVATAR_TYPES =new ArrayList<>();

	static {
		UPLOAD_AVATAR_TYPES.add("image/jpeg");
		UPLOAD_AVATAR_TYPES.add("image/png");
	}

	@PostMapping("change_avatar")
	public ResponseResult<String> changeAvatar(
			HttpServletRequest request,
			@RequestParam("avatar")MultipartFile avatar,
			HttpSession session){
		if(avatar.isEmpty()){
			throw new FileEmptyException("上传头像失败，未选择头像文件，或选择的文件为空");
		}
		//检查文件大小是否超标
		long size = avatar.getSize();
		if(size>UPLOAD_AVATAR_MAX_SIZE){
			throw new FileSizeException("上传头像失败！不允许使用超过" + UPLOAD_AVATAR_MAX_SIZE / 1024 + "KB的文件！");
		}
		// 检查文件类型是否在允许的范围之内
		String contentType = avatar.getContentType();
		if (!UPLOAD_AVATAR_TYPES.contains(contentType)) {
			// 抛出异常：FileTypeException
			throw new FileTypeException(
					"上传头像失败！不支持所提交的文件类型！允许的文件类型有：" + UPLOAD_AVATAR_TYPES);
		}
		//确定保存文件的文件夹的File对象
		String parentPath = request.getServletContext().getRealPath(UPLOAD_DIR);
		File parent =  new File(parentPath);
		if(!parent.exists()){
			parent.mkdirs();
		}
		//确定保存的文件的文件名
		String originalFilename =  avatar.getOriginalFilename();
		String suffix = "";
		int beginIndex = originalFilename.lastIndexOf(".");
		if(beginIndex!=-1){
			suffix =  originalFilename.substring(beginIndex);
		}
		String child = UUID.randomUUID().toString()+suffix;
		//创建保存文件的File对象：dest = new File(parent,chile);
		File dest = new File(parent,child);
		try {
			//执行上传并保存头像文件：avatar.transferTo(dest);
			avatar.transferTo(dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//执行修改头像
		//从Session中获取uid和username
		Integer uid = getUidFromSession(session);
		String username = session.getAttribute("username").toString();
		//执行修改数据：service.changeAvatar(uid,avatarPath,username)
		String avatarPath = UPLOAD_DIR +"/"+child;
		userService.changeAvatar(uid,avatarPath,username);
		//返回成功及avatarPath
		ResponseResult<String> rr = new ResponseResult<>();
		rr.setState(SUCCESS);
		rr.setData(avatarPath);
		return rr;
	}





	
}







