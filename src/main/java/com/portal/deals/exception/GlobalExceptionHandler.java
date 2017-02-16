package com.portal.deals.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * This is Global exception handler class, that will handle custom exception
 * <code>GenericException</code> in one way and others in other.
 * 
 * @author Gaurav Jain
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * This method will handle <code>GenericException</code> thrown from any
	 * controller with in the application
	 * 
	 * @param ex
	 *            The <code>GenericException</code>
	 * @return The model
	 */
	@ExceptionHandler(GenericException.class)
	public ModelAndView handleCustomException(GenericException ex) {

		ModelAndView model = new ModelAndView("generic_error");
		model.addObject("errCode", ex.getErrCode());
		model.addObject("errMsg", ex.getErrMsg());
		return model;

	}

	/**
	 * This method will handle all other exceptions that are gone uncaught in
	 * the application
	 * 
	 * @param ex
	 *            The <code>GenericException</code>
	 * @return The model
	 */
	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllException(Exception ex) {

		ModelAndView model = new ModelAndView("generic_error");
		model.addObject("errMsg", "System error has occured, please retry again.");
		return model;
	}
}