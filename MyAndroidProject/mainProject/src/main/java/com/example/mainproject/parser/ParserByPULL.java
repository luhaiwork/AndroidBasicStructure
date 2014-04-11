/**
 * 
 */
package com.example.mainproject.parser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import com.example.mainproject.models.City;
import com.example.mainproject.models.District;
import com.example.mainproject.models.Province;

import android.util.Xml;


public class ParserByPULL {
	
    //采用XmlPullParser来解析XML文件  
    public static List<Province> getProvince(InputStream inStream) throws Throwable 
    {
        List<Province> provinces = null;  
        Province province = null;  
        
        //========创建XmlPullParser,有两种方式=======
        //方式一:使用工厂类XmlPullParserFactory
		XmlPullParserFactory pullFactory = XmlPullParserFactory.newInstance();
		XmlPullParser parser = pullFactory.newPullParser();
		//方式二:使用Android提供的实用工具类android.util.Xml
        //XmlPullParser parser = Xml.newPullParser();  
        
        //解析文件输入流  
        parser.setInput(inStream, "UTF-8");  
        //产生第一个事件  
        int eventType = parser.getEventType();  
        //只要不是文档结束事件，就一直循环  
        while(eventType!=XmlPullParser.END_DOCUMENT)  
        {  
            switch (eventType)
            { 
                //触发开始文档事件  
                case XmlPullParser.START_DOCUMENT:  
                	provinces = new ArrayList<Province>();  
                    break;  
                //触发开始元素事件  
                case XmlPullParser.START_TAG:  
                    //获取解析器当前指向的元素的名称  
                    String name = parser.getName();  
                    if("province".equals(name))  
                    {  
                        //通过解析器获取id的元素值，并设置student的id  
                    	province = new Province();
                    	for(int i=0;i<parser.getAttributeCount();i++){
                    		if("code".equals(parser.getAttributeName(i))){
                    			province.setProvinceCode(parser.getAttributeValue(i));
                    		}
                    		if("name".equals(parser.getAttributeName(i))){
                    			province.setProvinceName(parser.getAttributeValue(i));
                    		}
                    	}
                    }  
                    break;  
                //触发结束元素事件  
                case XmlPullParser.END_TAG:  
                    if("province".equals(parser.getName()))  
                    {  
                    	provinces.add(province);  
                    	province = null;  
                    }  
                    break;  
                default:  
                    break;  
            }  
            eventType = parser.next();  
        }  
        return provinces;  
    }  
    
    //采用XmlPullParser来解析XML文件  
    public static List<City> getCities(InputStream inStream,String provinceCode) throws Throwable 
    {
        List<City> cities = null;  
        City city = null;  
        boolean isProvinceCity=false;
        //========创建XmlPullParser,有两种方式=======
        //方式一:使用工厂类XmlPullParserFactory
		XmlPullParserFactory pullFactory = XmlPullParserFactory.newInstance();
		XmlPullParser parser = pullFactory.newPullParser();
		//方式二:使用Android提供的实用工具类android.util.Xml
        //XmlPullParser parser = Xml.newPullParser();  
        
        //解析文件输入流  
        parser.setInput(inStream, "UTF-8");  
        //产生第一个事件  
        int eventType = parser.getEventType();  
        //只要不是文档结束事件，就一直循环  
        while(eventType!=XmlPullParser.END_DOCUMENT)  
        {  
        	String name = parser.getName();  
            switch (eventType)
            { 
                //触发开始文档事件  
                case XmlPullParser.START_DOCUMENT:  
                	cities = new ArrayList<City>();  
                    break;  
                //触发开始元素事件  
                case XmlPullParser.START_TAG:  
                    //获取解析器当前指向的元素的名称  
                    if("province".equals(name))  
                    {  
                    	for(int i=0;i<parser.getAttributeCount();i++){
                    		if("code".equals(parser.getAttributeName(i))){
                    			String code=parser.getAttributeValue(i);
                    			if(code.equals(provinceCode)){
                    				isProvinceCity=true;
                    			}else{
                    				isProvinceCity=false;
                    			}
                    		}
                    	}
                    }else if("city".equals(name)&&isProvinceCity==true){
                    	city=new City();
                    	for(int i=0;i<parser.getAttributeCount();i++){
                    		if("name".equals(parser.getAttributeName(i))){
                    			city.setName(parser.getAttributeValue(i));
                    		}else if("code".equals(parser.getAttributeName(i))){
                    			city.setCode(parser.getAttributeValue(i));
                    		}
                    	}
                    	cities.add(city);
                    } 
                    break;  
                //触发结束元素事件  
                case XmlPullParser.END_TAG:  
                    if("province".equals(name)) {
                    	isProvinceCity=false;
                    }
                    break;  
                default:  
                    break;  
            }  
            eventType = parser.next();  
        }  
        return cities;
    }  
    //采用XmlPullParser来解析XML文件
    public static List<District> getDistricts(InputStream inStream,String provinceCode,String cityCode) throws Throwable 
    {
        List<District> districts = null;  
        District district = null;  
        boolean isProvinceCity=false;
        boolean isCityDistrict=false;
        //========创建XmlPullParser,有两种方式=======
        //方式一:使用工厂类XmlPullParserFactory
		XmlPullParserFactory pullFactory = XmlPullParserFactory.newInstance();
		XmlPullParser parser = pullFactory.newPullParser();
		//方式二:使用Android提供的实用工具类android.util.Xml
        //XmlPullParser parser = Xml.newPullParser();  
        
        //解析文件输入流  
        parser.setInput(inStream, "UTF-8");  
        //产生第一个事件  
        int eventType = parser.getEventType();  
        //只要不是文档结束事件，就一直循环  
        while(eventType!=XmlPullParser.END_DOCUMENT)  
        {  
        	String name = parser.getName();  
            switch (eventType)
            { 
                //触发开始文档事件  
                case XmlPullParser.START_DOCUMENT:  
                	districts = new ArrayList<District>();  
                    break;  
                //触发开始元素事件  
                case XmlPullParser.START_TAG:  
                    //获取解析器当前指向的元素的名称  
                    if("province".equals(name))  
                    {
                    	for(int i=0;i<parser.getAttributeCount();i++){
                    		if("code".equals(parser.getAttributeName(i))){
                    			String code=parser.getAttributeValue(i);
                    			if(code.equals(provinceCode)){
                    				isProvinceCity=true;
                    			}else{
                    				isProvinceCity=false;
                    			}
                    		}
                    	}
                    }else if("city".equals(name)&&isProvinceCity==true){
                    	for(int i=0;i<parser.getAttributeCount();i++){
                    		if("code".equals(parser.getAttributeName(i))){
                    			String code=parser.getAttributeValue(i);
                    			if(code.equals(cityCode)){
                    				isCityDistrict=true;
                    			}else{
                    				isCityDistrict=false;
                    			}
                    		}
                    	}
                    }else if("district".equals(name)&&isCityDistrict&&isProvinceCity){
                    	district=new District();
                    	for(int i=0;i<parser.getAttributeCount();i++){
                    		if("name".equals(parser.getAttributeName(i))){
                    			district.setName(parser.getAttributeValue(i));
                    		}else if("code".equals(parser.getAttributeName(i))){
                    			district.setCode(parser.getAttributeValue(i));
                    		}
                    	}
                    	districts.add(district);
                    }
                    break;  
                //触发结束元素事件  
                case XmlPullParser.END_TAG:  
                    if("province".equals(name)) {
                    	isProvinceCity=false;
                    }else if("city".equals(name)){
                    	isCityDistrict=false;
                    }
                    break;  
                default:  
                    break;  
            }  
            eventType = parser.next();  
        }  
        return districts;
    }  
}

