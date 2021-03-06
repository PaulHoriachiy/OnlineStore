package com.company.store.controller;

import com.company.store.entities.ProductAttribute;
import com.company.store.repository.CategoryAttributeDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;


@Controller
public class CategoryAttributeController {
    private CategoryAttributeDAO categoryAttributeDAO;
    @Autowired
    public void setCategoryAttributeDAO(CategoryAttributeDAO categoryAttributeDAO) {
        this.categoryAttributeDAO = categoryAttributeDAO;
    }

    @RequestMapping(value = "attributesforcategory")
    public ModelAndView getAttributesForCategory(@RequestParam(value = "id") int id) {
        Collection<ProductAttribute> attributes = categoryAttributeDAO.getAttributesForCategory(id);
        return new ModelAndView("attributesforcategory", "attributes", attributes);
    }

    @RequestMapping(value = "attributebyid")
    public ModelAndView getAttributeById(@RequestParam(value = "id") int id) {
        categoryAttributeDAO.getAttributeById(id);
        return new ModelAndView("attr_id");
    }

    @RequestMapping(value = "saveattribute")
    public ModelAndView saveAttribute(ProductAttribute attribute) {
        categoryAttributeDAO.saveAttribute(attribute);
        return new ModelAndView("saveattribute");
    }

    @RequestMapping(value = "removeattribute")
    public ModelAndView removeAttribute(@RequestParam(value = "id") int id) {
        categoryAttributeDAO.removeAttribute(id);
        return new ModelAndView("removeattr_id");
    }
}
