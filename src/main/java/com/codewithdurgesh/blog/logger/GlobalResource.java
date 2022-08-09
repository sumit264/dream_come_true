package com.codewithdurgesh.blog.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GlobalResource {

	public static Logger getLogger(Class className) {
		
		
		return LoggerFactory.getLogger(className);
		
		
	}
}
