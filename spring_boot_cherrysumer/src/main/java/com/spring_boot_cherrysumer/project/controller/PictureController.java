package com.spring_boot_cherrysumer.project.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.spring_boot_cherrysumer.project.model.Exhibition2VO;
import com.spring_boot_cherrysumer.project.model.ExhibitionVO;
import com.spring_boot_cherrysumer.project.model.MemberVO;
import com.spring_boot_cherrysumer.project.model.PictureVO;
import com.spring_boot_cherrysumer.project.service.ArtistService;
import com.spring_boot_cherrysumer.project.service.ExhibitionService;
import com.spring_boot_cherrysumer.project.service.PictureService;
@Controller
public class PictureController {
	@Autowired
	PictureService service;	
	
	@Autowired
    ArtistService service2;
	
		@RequestMapping("/picture") 
		public String picture() {
			return "/picture/picture2"; 
		}

		
		
//		메인 컨트롤러//	메인 컨트롤러//	메인 컨트롤러//	메인 컨트롤러//	메인 컨트롤러//	메인 컨트롤러	
		@RequestMapping("/")
		
		public String pictureList1(Model model) {
			

			// 현재 날짜 구하기
			LocalDate now = LocalDate.now();

			// 포맷 정의
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

			// 포맷 적용
			String today = now.format(formatter);


			ArrayList<Exhibition2VO> ex = service2.exList3(today); //현재 전시중
			
			 
			model.addAttribute("ex", ex);


			
		ArrayList<PictureVO> pic = service.ListPicture1();
		model.addAttribute("pic",pic);
		
		
		System.out.println(ex.get(0).getArtImg());
			return "NewFile"; 	
		}
//		메인 컨트롤러//	메인 컨트롤러//	메인 컨트롤러//	메인 컨트롤러//	메인 컨트롤러//	메인 컨트롤러			
		
		
		
		@RequestMapping("/picture/picture_list/")
	
		public String pictureList(Model model) {
		
		ArrayList<PictureVO> pic = service.ListPicture();

		model.addAttribute("pic",pic);
		
			return "/picture/picture_list"; 	
		}
			
			
		@RequestMapping("/picture/photo_list/")
			
			public String photo_list(Model model) {
			
			ArrayList<PictureVO> photo = service.ListPicture();

			model.addAttribute("photo",photo);
			
				return "/picture/photo_list"; 	
			
			
		}
		@RequestMapping("/register")
		public String insert (PictureVO vo,
							@RequestParam("upload")MultipartFile file, Model model) throws IOException{
 String uploadPath="/Users/shimgyumin/java_class/cherrysumer_upload/";
//	String uploadPath = "/Users/pizza/STS3/SpringWorkspace/cherrysumer_upload/";
//String uploadPath = "C:/springWorkspace/upload/";
//String uploadPath =  "/usr/local/project/upload/upload/";
			 
			 
			String orgName=file.getOriginalFilename();
			

			
			UUID uuid = UUID.randomUUID();
			String savedFileName=uuid.toString() + "_" + orgName;
			
			File sendFile = new File(uploadPath + savedFileName);
			
			file.transferTo(sendFile);
			
			model.addAttribute(savedFileName, sendFile);
			
			

			vo.picimg = savedFileName;
			
			
			service.insert(vo);
			
			return "redirect:/picture/picture_list/"; 
			
		}
		@RequestMapping("/picture/registerform/") 
		public String picture1() {
			return "/picture/register"; 
		}		
		
		
		@RequestMapping("/picture/searchResult")
		public String Search (@RequestParam String keyword, Model model) {
			
			ArrayList<PictureVO> searchList = service.Search(keyword);
			model.addAttribute("searchList", searchList);
			
			
			return"picture/picture_listSearch";
			
		}
		
		@RequestMapping("/main") 
		public String main() {
			return "/picture/main"; 
		}
////		삭제
//		@RequestMapping("/picture/Delete")
//		public String Delete(@RequestParam String memId,
//							@RequestParam String picNo,
//							HttpSession session) {		
//			if(memId == session.getAttribute(memId))
//			{	
//		service.Delete(picNo);
//		}		
//			return "";
//		}

}


//
//@RequestMapping("/register01")
//public String insert (PictureVO vo,
//					@RequestParam("upload") ArrayList<MultipartFile> files, Model model) 
//							throws IOException{
//	
//	String uploadPath="/Users/shimgyumin/java_class/cherrysumer_upload/";
//////String uploadPath = "/Users/pizza/STS3/SpringWorkspace/cherrysumer_upload/";
//////String uploadPath = "C:/springWorkspace/upload/";


//	
//	ArrayList<String> originalFileNameList=new ArrayList<String>();
//
//        for(MultipartFile file : files) {
//	
//
//	
//        }
//        model.addAttribute("originalFileNameList",originalFileNameList);
//	
//
//	service.insert(vo);
//	
//	return "picture/register"; 
//	
//}
//
//
//@RequestMapping("/picture/registerform/") 
//public String picture1() {
//	return "/picture/register"; 
//}				





