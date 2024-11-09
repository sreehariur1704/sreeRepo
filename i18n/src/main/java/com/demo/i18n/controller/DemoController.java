package com.demo.i18n.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("locale")
public class DemoController {
	
//	private final MessageSource ms;
//	
//	public DemoController(MessageSource ms) {
//		this.ms=ms;
//	}
	
	@Autowired
	private MessageSource messagesource;
	
	@Value("${greetings}")
	private String val;
	
	@GetMapping
	public String hello() {
		
		return val;
		//return messagesource.getMessage("common.hello", null,LocaleContextHolder.getLocale());
	}
	@GetMapping("with-header")
	public String sayhello(@RequestHeader(name="Accept-Language",required =false) Locale local) {
		return messagesource.getMessage("common.hello", null,LocaleContextHolder.getLocale());
	}

}
