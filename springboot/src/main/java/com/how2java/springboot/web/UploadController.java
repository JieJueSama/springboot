package com.how2java.springboot.web;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {
	
	
	@RequestMapping("/uploadPage")
	public String uploadPage() {
		return "uploadPage";
	}
	
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
												//接收上传文件
	public String upload(HttpServletRequest req, @RequestParam("file") MultipartFile file, Model m) {
		try {
			//根据时间戳创建新的文件名
			//即使第二次上传相同的文件名称的文件也不会把第一次的文件覆盖了
			String fileName = System.currentTimeMillis() + file.getOriginalFilename();
			//获取当前项目的真是路径，然后拼接前面的文件名
			String destFileName = req.getServletContext().getRealPath("")+"upload"+File.separator+fileName;
			//当文件的目录不存在时创建目录
			File destFile = new File(destFileName);
			destFile.getParentFile().mkdirs();
			//把浏览器上传的文件复制到位置
			file.transferTo(destFile);
			//把文件名放在model里，以便后续显示用
			m.addAttribute("fileName", fileName);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "上传失败，" + e.getMessage();
		}
		
		return "showImg";
	}

}
