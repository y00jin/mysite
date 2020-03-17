package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.service.AdminService;
import com.douzone.mysite.service.FileUploadService;
import com.douzone.mysite.vo.SiteVo;
import com.douzone.security.Auth;

@Auth("ADMIN")
@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	@Autowired
	private FileUploadService fileUploadService;
	
	@RequestMapping("")
	public String main(Model model) {
		SiteVo siteVo = adminService.setOrigin();
		model.addAttribute("siteVo", siteVo);
		return "admin/main";
	}
	
	@RequestMapping(value = "/main/update", method = RequestMethod.POST)
	public String update(
			@ModelAttribute SiteVo siteVo,
			@RequestParam(value="file") MultipartFile multipartFile) {
		
		if(siteVo.getProfile() == null) {
			SiteVo vo = adminService.setOrigin();
			siteVo.setProfile(vo.getProfile());
		} else {
			String url = fileUploadService.restore(multipartFile);
			System.out.println(url);
			siteVo.setProfile(url);
		}
		adminService.update(siteVo);
		
		return "redirect:/main";
	}
	
	@RequestMapping("/guestbook")
	public String guestbook() {
		return "admin/guestbook";
	}
	
	@RequestMapping("/board")
	public String board() {
		return "admin/board";
	}
	
	@RequestMapping("/user")
	public String user() {
		return "admin/user";
	}
	
	
}
