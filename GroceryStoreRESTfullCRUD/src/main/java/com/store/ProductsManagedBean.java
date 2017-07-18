package com.store;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.store.components.entity.Products;
import com.store.service.ProductsService;

@ManagedBean(name = "productsManagedBean")
@ViewScoped
@Controller
public class ProductsManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private LazyDataModel<Products> productLazyModel;

	@Autowired
	private ProductsService productService;

	@Autowired
	private Products product;
	
	@PostConstruct
	public void init() {
		List<Products> product = getProductsService().infoAboutProducts(0);
		productLazyModel = new ProductsLazyList(product);
	}

	public LazyDataModel<Products> getAllProducts() {
		return productLazyModel;
	}

	public ProductsService getProductsService() {
		return productService;
	}

	public void setProductsService(ProductsService productService) {
		this.productService = productService;
	}
	
	public void addProduct(ActionEvent actionEvent) {
		productService.addProduct(product);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Save into DB", "Adding a new product has passed successfully"));
	}
}