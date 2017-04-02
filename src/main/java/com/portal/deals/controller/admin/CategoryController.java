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
import com.portal.deals.exception.EntityNotFoundException;
import com.portal.deals.exception.GenericException;
import com.portal.deals.form.CommonConstants;
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

	/** The update new category page */
	private static final String UPDATE_CATEGORY_FORM_JSP = "updateCategoryForm";

	/** The JSP name for add new category page */
	private static final String CATEGORY_FORM_JSP = "categoryForm";

	/** The JSP name for category list page */
	private static final String CATEGORY_LIST_JSP = "categoryList";

	/** The update new category page */
	private static final String CHILD_UPDATE_CATEGORY_FORM_JSP = "updateChildCategoryForm";

	/** The JSP name for add new category page */
	private static final String CHILD_CATEGORY_FORM_JSP = "childCategoryForm";

	/** The JSP name for category list page */
	private static final String CHILD_CATEGORY_LIST_JSP = "childCategoryList";

	/** The module name */
	private static final String MODULE = "categoryManager";

	/** The Parent Id */
	private static final String PARENT_ID = "parentId";

	/** The root category */
	private static final String ROOT_CATEGORY = "rootCategories";

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
			model.addAttribute(CommonConstants.PAGE_NAME, CATEGORY_FORM_JSP);
			model.addAttribute(CommonConstants.MODULE, MODULE);
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
				model.addAttribute(CommonConstants.PAGE_NAME, CATEGORY_FORM_JSP);
				model.addAttribute(CommonConstants.MODULE, MODULE);
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
	public String listAllCategories(ModelMap model) {
		LOG.info("Loading the  categories list page");
		try {
			/** Get the list of categories from the database */
			List<Category> categories = service.listAllRootCategories();

			/**
			 * Adding the categories list to model, to be used for rendering in
			 * JSP
			 */
			model.addAttribute("categories", categories);
			model.addAttribute(CommonConstants.PAGE_NAME, CATEGORY_LIST_JSP);
			model.addAttribute(CommonConstants.MODULE, MODULE);
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
	public String updateCategory(@PathVariable("id") int id, ModelMap model) {
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
			model.addAttribute(CommonConstants.PAGE_NAME, UPDATE_CATEGORY_FORM_JSP);
			model.addAttribute(CommonConstants.MODULE, MODULE);
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
	public String updateCategory(@Valid Category category, BindingResult result, ModelMap model,
			HttpServletRequest request) {
		LOG.info("Updating the  Category details");
		try {
			/** Reload the update Category page in case of any error */
			if (result.hasErrors()) {
				model.addAttribute(CommonConstants.PAGE_NAME, UPDATE_CATEGORY_FORM_JSP);
				model.addAttribute(CommonConstants.MODULE, MODULE);
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
	public String deleteCategory(@PathVariable("id") int id) {
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

	/**
	 * This method will render the add new childCategory page
	 * 
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/newChildCategory/{parentId}")
	public String addChildCategory(@PathVariable(PARENT_ID) int parentId, ModelMap model) {
		LOG.info("Rendering the add new childCategory page");
		try {

			/**
			 * Adding the blank Category object as model attribute for Form
			 */
			model.addAttribute("category", new Category());
			model.addAttribute(PARENT_ID, parentId);
			model.addAttribute(CommonConstants.PAGE_NAME, CHILD_CATEGORY_FORM_JSP);
			model.addAttribute(CommonConstants.MODULE, MODULE);

			List<Category> rootCategories = service.listAllRootCategories();
			model.addAttribute(ROOT_CATEGORY, rootCategories);
		} catch (Exception ex) {
			LOG.error("Exception occured while loading add new childCategory Page", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return CHILD_CATEGORY_FORM_JSP;
	}

	/**
	 * This method will persist the data in the database
	 * 
	 * @param childCategory
	 *            The Category added on add childCategory form
	 * @param result
	 *            The binding result, from validation etc.
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/newChildCategory/{parentId}", method = RequestMethod.POST)
	public String newChildCategory(@PathVariable(PARENT_ID) int parentId, @Valid Category childCategory,
			BindingResult result, ModelMap model, HttpServletRequest request) {
		LOG.info("Saving the Category to the database");

		try {
			Category parentCategory = service.getCategoryById(childCategory.getParentId());
			List<Category> rootCategories = service.listAllRootCategories();
			model.addAttribute(ROOT_CATEGORY, rootCategories);

			/**
			 * If there is any validation error, then reload the Category form
			 * page with relevant errors
			 */
			if (result.hasErrors()) {
				model.addAttribute(CommonConstants.PAGE_NAME, CHILD_CATEGORY_FORM_JSP);
				model.addAttribute(CommonConstants.MODULE, MODULE);
				model.addAttribute(PARENT_ID, parentId);
				return CATEGORY_FORM_JSP;
			}
			if (parentCategory == null) {
				throw new EntityNotFoundException("ErrorAddDeal", "Parent Category not found");
			}
			childCategory.setCategory(parentCategory);
			/** Save the Category in the database */
			service.saveCategory(childCategory);
		} catch (Exception ex) {
			LOG.error("Exception occured while saving the Category", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return "redirect:/admin/listChildCategories/" + parentId;
	}

	/**
	 * This method will get the list of categories from the database.
	 * 
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/listChildCategories/{parentId}")
	public String listAllChildCategories(@PathVariable(PARENT_ID) int parentId, ModelMap model) {
		LOG.info("Loading the  categories list page");
		try {
			/** Get the list of categories from the database */
			Category parentCategory = service.getCategoryById(parentId);
			List<Category> rootCategories = service.listAllRootCategories();
			model.addAttribute(ROOT_CATEGORY, rootCategories);
			/**
			 * Adding the categories list to model, to be used for rendering in
			 * JSP
			 */
			model.addAttribute(PARENT_ID, parentId);
			model.addAttribute("categories", parentCategory.getCategories());
			model.addAttribute(CommonConstants.PAGE_NAME, CHILD_CATEGORY_LIST_JSP);
			model.addAttribute(CommonConstants.MODULE, MODULE);
		} catch (Exception ex) {
			LOG.error("Exception occured while loading the childCategory listing Page", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return CHILD_CATEGORY_LIST_JSP;
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
	@RequestMapping(value = "/updateChildCategory/{id}/{parentId}")
	public String updateCategory(@PathVariable(PARENT_ID) int parentId, @PathVariable("id") int id, ModelMap model) {
		LOG.info("Loading update  Category page");

		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug("The Category id to be updated [" + id + "]");
			}
			/** Get the Category entity by id from the database */
			Category childCategory = service.getCategoryById(id);
			List<Category> rootCategories = service.listAllRootCategories();
			model.addAttribute(ROOT_CATEGORY, rootCategories);

			/** Add edit to true, to identify the request is coming from edit */
			model.addAttribute("edit", true);
			model.addAttribute(PARENT_ID, parentId);
			model.addAttribute("childCategory", childCategory);
			model.addAttribute(CommonConstants.PAGE_NAME, CHILD_UPDATE_CATEGORY_FORM_JSP);
			model.addAttribute(CommonConstants.MODULE, MODULE);
		} catch (Exception ex) {
			LOG.error("Exception occured while updating the  Category", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return CHILD_CATEGORY_FORM_JSP;
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
	@RequestMapping(value = "/updateChildCategory/{parentId}", method = RequestMethod.POST)
	public String updateChildCategory(@PathVariable(PARENT_ID) int parentId, @Valid Category childCategory,
			BindingResult result, ModelMap model, HttpServletRequest request) {
		LOG.info("Updating the  Category details");
		try {
			List<Category> rootCategories = service.listAllRootCategories();
			model.addAttribute(ROOT_CATEGORY, rootCategories);

			/** Reload the update Category page in case of any error */
			if (result.hasErrors()) {
				model.addAttribute(CommonConstants.PAGE_NAME, CHILD_UPDATE_CATEGORY_FORM_JSP);
				model.addAttribute(CommonConstants.MODULE, MODULE);
				model.addAttribute(PARENT_ID, parentId);
				return "redirect:/admin/updateChildCategory/" + childCategory.getId();
			}

			/** Updating the Category in the database */
			service.updateCategory(childCategory);
		} catch (Exception ex) {
			LOG.error("Exception occured while loading Category Page", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return "redirect:/admin/listChildCategories/" + parentId;
	}

	/**
	 * This method will delete the Category from the database, based on the id
	 * passed
	 * 
	 * @param id
	 *            The id of the Category that has to be deleted
	 * @return the redirect value
	 */
	@RequestMapping(value = "/deleteChildCategory/{id}/{parentId}")
	public String deleteCard(@PathVariable(PARENT_ID) int parentId, @PathVariable("id") int id) {
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
		return "redirect:/admin/listChildCategories/" + parentId;
	}

}
