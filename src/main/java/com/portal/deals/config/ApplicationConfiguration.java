package com.portal.deals.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import com.portal.deals.converter.RoleToUserProfileConverter;

/**
 * This is a Spring configuration Class which defines the View Resolvers and
 * other configurations that are used across the Project
 * 
 * @author Gaurav Jain
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.portal.deals")
public class ApplicationConfiguration extends WebMvcConfigurerAdapter {

	/**
	 * This is a formatter to be used for converting String values to
	 * UserProfile
	 */
	@Autowired
	RoleToUserProfileConverter roleToUserProfileConverter;

	/**
	 * This is Apache Tiles Configure Bean, that will be used to handle the
	 * layouts.
	 */
	@Bean
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer tilesConfigurer = new TilesConfigurer();
		/**
		 * Path of the tiles.xml file, where all the pages has to be configured
		 */
		tilesConfigurer.setDefinitions(new String[] { "/WEB-INF/views/**/tiles.xml" });
		tilesConfigurer.setCheckRefresh(true);
		return tilesConfigurer;
	}

	/**
	 * This is mail sender bean, used to send out email.
	 * 
	 * @return
	 */
	@Bean
	public JavaMailSender getMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		/** Using GMAIL SMTP configuration. */
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		/**
		 * User name and password for the account that will send out the email.
		 */
		mailSender.setUsername("card.deals.portal@gmail.com");
		mailSender.setPassword("gauravjain");

		Properties javaMailProperties = new Properties();
		javaMailProperties.put("mail.smtp.starttls.enable", "true");
		javaMailProperties.put("mail.smtp.auth", "true");
		javaMailProperties.put("mail.transport.protocol", "smtp");
		javaMailProperties.put("mail.debug", "true");

		mailSender.setJavaMailProperties(javaMailProperties);
		return mailSender;
	}

	/**
	 * Configure ViewResolvers to deliver preferred views.
	 */
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		TilesViewResolver viewResolver = new TilesViewResolver();
		registry.viewResolver(viewResolver);
	}

	/**
	 * Configure ResourceHandlers to serve static resources like CSS / Java
	 * script / images etc.
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	/**
	 * Configure Converter to be used. In our example, we need a converter to
	 * convert string values[Roles] to UserRoles
	 */
	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(roleToUserProfileConverter);
	}

	/**
	 * Configure MessageSource to lookup any validation/error message in
	 * internationalized property files
	 */
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}

	/**
	 * Optional. It's only required when handling '.' in @PathVariables which
	 * otherwise ignore everything after last '.' in @PathVaidables argument.
	 */
	@Override
	public void configurePathMatch(PathMatchConfigurer matcher) {
		matcher.setUseRegisteredSuffixPatternMatch(true);
	}
}
