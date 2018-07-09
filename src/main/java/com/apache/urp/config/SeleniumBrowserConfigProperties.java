package com.apache.urp.config;

public class SeleniumBrowserConfigProperties extends GlobalSettings{
   
        
    public BrowserConfig getBrowserConfig() {
        return new BrowserConfig(browserType, useHub, hubLocation);
    }

    public String getPageUrl() {
    	return url;
    }
    
    public static void main(String[] args) {
    	SeleniumBrowserConfigProperties test=new SeleniumBrowserConfigProperties();
    	//System.out.println(test.getPageUrl());
    	System.out.println(test.getBrowserConfig());
	}
}
