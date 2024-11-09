package com.demo.i18n.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

@Configuration
public class LocalResolverConfig {
	
	@Bean
	public LocaleResolver localeResolver() {
		AcceptHeaderLocaleResolver localRes = new AcceptHeaderLocaleResolver();
		localRes.setDefaultLocale(Locale.US);
		return localRes;
	}
	@Bean
	public ResourceBundleMessageSource messagesource() {
		ResourceBundleMessageSource ms = new ResourceBundleMessageSource();
		ms.setBasename("i18n/messages");
		return ms;
	}

}
