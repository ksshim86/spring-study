package com.sks.study.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sks.study.cmmn.CommandMap;

public class WebCrawling {
	
	public List<String> webCrawling( CommandMap commandMap ) throws ClientProtocolException, IOException
	{
		String crawlingUrl = (String)commandMap.get( "crawlingUrl" );
		List<String> list = new ArrayList<String>();
		
		Document doc = Jsoup.connect( crawlingUrl ).get();
//			System.out.println( doc.data() );
		
		Elements elements = doc.select("img");
		
		for( Element element : elements )
		{
			System.out.println( element.attr( "src" ) );
			list.add( element.attr( "src" ) );
		}
		
		return list;
	}
}
