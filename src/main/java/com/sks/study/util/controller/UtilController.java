package com.sks.study.util.controller;

import java.io.FileOutputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sks.study.cmmn.CommandMap;
import com.sks.study.util.WebCrawling;

@RequestMapping(value="/util")
@Controller
public class UtilController {
	
//	private static final Logger logger = Logger.getLogger(UtilController.class);
	
	@RequestMapping(value = "/html2Canvas.do")
    public String html2Canvas() throws Exception
    {
        return "/util/html2canvas";
    }
	
	@RequestMapping(value = "/webCrawling.do")
    public String webCrawling() throws Exception
    {
        return "/util/webCrawling";
    }
	
	@RequestMapping(value = "/runWebCrawling.do")
	@ResponseBody
	public ModelAndView runWebCrawling( CommandMap commandMap ) throws Exception 
	{
		String message = "";
		ModelAndView mv = new ModelAndView("");
		WebCrawling wc = new WebCrawling();
		
		try
		{
			List<String> list = wc.webCrawling( commandMap );
			message = "success";
			mv.addObject( "list", list );
		}
		catch( Exception e )
		{
			e.printStackTrace();
			message = "fail";
		}
		
		mv.addObject( "message", message );
        mv.setViewName( "jsonView" );
        return mv;
	}
	
	@RequestMapping(value="/imageCreate.do")
	public ModelAndView createImage (HttpServletRequest request) throws Exception {

		String binaryData = request.getParameter("imgSrc");
		FileOutputStream stream = null;
		ModelAndView mav = new ModelAndView();
		mav.setViewName("jsonView");        
		
		String path = request.getSession().getServletContext().getContext("/").getRealPath("") + "\\upload\\";
		try
		{
			System.out.println("binary file   "  + binaryData);
			if(binaryData == null || binaryData=="")
			{
				throw new Exception();    
			}

			binaryData = binaryData.replaceAll("data:image/png;base64,", "");
			byte[] file = Base64.decodeBase64(binaryData);
			System.out.println("file  :::::::: " + file + " || " + file.length);
			String fileName=  UUID.randomUUID().toString();

			stream = new FileOutputStream(path+fileName+".png");
			stream.write(file);
			stream.close();
			System.out.println("파일 작성 완료");
			mav.addObject("msg","ok");
			mav.addObject("imgName", fileName+".png");

		}
		catch(Exception e)
		{
			System.out.println("파일이 정상적으로 넘어오지 않았습니다");
			mav.addObject("msg","no");
			return mav;
		}
		finally
		{
			stream.close();
		}
		
		return mav;
	}
}
