package com.data.common;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.framework.util.ManageConstants;
import com.framework.util.ManageUtil;

public class ContextListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent context) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent context) {
		
		String basePath = context.getServletContext().getContextPath()+"/";
		Properties properties;
		String staticPath = "";
		try {
			properties = ManageUtil.ReadProperties("/config.properties");
			staticPath = properties.getProperty("resource.path");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// 如果静态地址为空，则引用本地资源
		if("".equals(staticPath) || staticPath == null){
			staticPath = basePath;	
		}
		
		context.getServletContext().setAttribute(ManageConstants.BASE_PATH, basePath);
		context.getServletContext().setAttribute(ManageConstants.STATIC_PATH, staticPath);
		
	}

}
