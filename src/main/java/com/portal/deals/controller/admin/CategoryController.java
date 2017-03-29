package com.portal.deals.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.portal.deals.exception.BaseException;
import com.portal.deals.exception.GenericException;
import com.portal.deals.model.Category;
import com.portal.deals.service.CategoryService;

/**
 * This is the controller class for category CRUD operation. Only ADMIN will
 * have access to this controller
 * 
 * @author Gaurav Jain
 *
 */
@Controller
@RequestMapping(value = "/admin")
public class CategoryController {

	/** Initializing the Logger */
	private static final Logger LOG = LoggerFactory.getLogger(CategoryController.class);

	/**
	 * Service class for communicating with DAO layer for CRUD operation for
	 * CARD entity
	 */
	@Autowired
	private CategoryService service;

	/** The JSP name for add new category page */
	private static final String CATEGORY_FORM_JSP = "categoryForm";

	/** The JSP name for category list page */
	private static final String CATEGORY_LIST_JSP = "categoryList";

	/**
	 * This method will render the add new category page
	 * 
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/newCategory")
	public String addCategory(ModelMap model) {
		LOG.info("Rendering the add new category page");
		try {
			/**
			 * Adding the blank Category object as model attribute for Form
			 */
			model.addAttribute("category", new Category());
		} catch (Exception ex) {
			LOG.error("Exception occured while loading add new category Page", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return CATEGORY_FORM_JSP;
	}

	/**
	 * This method will persist the data in the database
	 * 
	 * @param category
	 *            The Category added on add category form
	 * @param result
	 *            The binding result, from validation etc.
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/newCategory", method = RequestMethod.POST)
	public String addCategory(@Valid Category category, BindingResult result, ModelMap model,
			HttpServletRequest request) {
		LOG.info("Saving the Category to the database");

		try {
			/**
			 * If there is any validation error, then reload the Category form
			 * page with relevant errors
			 */
			if (result.hasErrors()) {
				return CATEGORY_FORM_JSP;
			}

			/** Save the Category in the database */
			service.saveCategory(category);
		} catch (Exception ex) {
			LOG.error("Exception occured while saving the Category", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return "redirect:/admin/listCategories";
	}

	/**
	 * This method will get the list of categories from the database.
	 * 
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/listCategories")
	public String listAllCards(ModelMap model) {
		LOG.info("Loading the  categories list page");
		try {
			/** Get the list of categories from the database */
			List<Category> categories = service.listAllCategories();

			/**
			 * Adding the categories list to model, to be used for rendering in
			 * JSP
			 */
			model.addAttribute("categories", categories);
		} catch (Exception ex) {
			LOG.error("Exception occured while loading the category listing Page", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return CATEGORY_LIST_JSP;
	}

	/**
	 * This method will update the Category details
	 * 
	 * @param id
	 *            Id of the Category, that has to be updated
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/updateCategory/{id}")
	public String updateCategoryForm(@PathVariable("id") int id, ModelMap model) {
		LOG.info("Loading update  Category page");

		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug("The Category id to be updated [" + id + "]");
			}
			/** Get the Category entity by id from the database */
			Category category = service.getCategoryById(id);

			/** Add edit to true, to identify the request is coming from edit */
			model.addAttribute("edit", true);
			model.addAttribute("category", category);
		} catch (Exception ex) {
			LOG.error("Exception occured while updating the  Category", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return CATEGORY_FORM_JSP;
	}

	/**
	 * This method will update the Category data in the database
	 * 
	 * @param The
	 *            CARD details added on add Category form
	 * @param result
	 *            The binding result, from validation etc.
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/updateCategory", method = RequestMethod.POST)
	public String updateCard(@Valid Category category, BindingResult result, ModelMap model,
			HttpServletRequest request) {
		LOG.info("Updating the  Category details");
		try {
			/** Reload the update Category page in case of any error */
			if (result.hasErrors()) {
				return "redirect:/admin/updateCategory/" + category.getId();
			}

			/** Updating the Category in the database */
			service.updateCategory(category);
		} catch (Exception ex) {
			LOG.error("Exception occured while loading Category Page", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return "redirect:/admin/listCategories";
	}

	/**
	 * This method will delete the Category from the database, based on the id
	 * passed
	 * 
	 * @param id
	 *            The id of the Category that has to be deleted
	 * @return the redirect value
	 */
	@RequestMapping(value = "/deleteCategory/{id}")
	public String deleteCard(@PathVariable("id") int id) {
		LOG.info("Deleting the  Category from database");
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug("The Category id to be deleted [" + id + "]");
			}
			/** Call to database to delete the Category */
			service.deleteCategoryById(id);
		} catch (Exception ex) {
			LOG.error("Exception occured while deleting the  Category", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return "redirect:/admin/listCategories";
	}

}
