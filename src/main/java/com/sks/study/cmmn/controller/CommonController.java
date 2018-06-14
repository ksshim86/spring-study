package com.sks.study.cmmn.controller;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sks.study.cmmn.CommandMap;
import com.sks.study.cmmn.service.CommonService;

@RequestMapping(value="/")
@Controller
public class CommonController {
	private static final Logger logger = Logger.getLogger(CommonController.class);
	
	@Resource( name="commonService" )
	private CommonService commonService;
	
    @RequestMapping(value="/openSampleList.do")
    public ModelAndView openSampleList(Map<String,Object> commandMap) throws Exception{
        ModelAndView mv = new ModelAndView("");
        logger.debug("인터셉터 테스트");
         
        return mv;
    }
    
    @RequestMapping(value = "/login.do")
    public String viewLoginPage() throws Exception
    {
    	logger.debug("function : viewLoginPage()");
        return "/cmmn/login";
    }
    
    @RequestMapping( value = "/main.do" )
    public ModelAndView viewMainPage() throws Exception {
    	logger.debug("function : viewMainPage()");
    	ModelAndView mv = new ModelAndView( "" );
    	
//    	List<Map<String, Object>> list = commonService.selectMainTestData();
    	
//    	mv.addObject( "list", list );
    	mv.setViewName( "/cmmn/main" );
    	return mv;
    }
    
    @RequestMapping(value="/testMapArgumentResolver.do")
    public ModelAndView testMapArgumentResolver(CommandMap commandMap) throws Exception{
    	logger.debug("function : testMapArgumentResolver()");
        ModelAndView mv = new ModelAndView("");
         
        if(commandMap.isEmpty() == false){
            Iterator<Entry<String,Object>> iterator = commandMap.getMap().entrySet().iterator();
            Entry<String,Object> entry = null;
            while(iterator.hasNext()){
                entry = iterator.next();
                logger.debug("key : "+entry.getKey()+", value : "+entry.getValue());
            }
        }
        return mv;
    }
    
}
