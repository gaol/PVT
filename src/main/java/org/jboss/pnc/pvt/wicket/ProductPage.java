package org.jboss.pnc.pvt.wicket;

import org.apache.wicket.Application;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.jboss.pnc.pvt.dao.PVTDataAccessObject;
import org.jboss.pnc.pvt.model.Product;

/**
 * @author <a href="mailto:yyang@redhat.com">Yong Yang</a>
 */
public class ProductPage extends TemplatePage {

    private Product product = new Product();

    public ProductPage() {
        setActiveMenu(Menu.PRODUCTS);
        product = (Product) Session.get().getAttribute("product");
        
        Button editButton = new Button("edit")
        {
        	@Override
			public void onSubmit() {
            	Session.get().setAttribute("product", product);                        
                setResponsePage(ProductEditPage.class);
            }
        };
        editButton.setDefaultFormProcessing(false);
        
        Button backButton = new Button("back"){
        	@Override
			public void onSubmit() {                     
                setResponsePage(ProductsPage.class);
            }
        };
        backButton.setDefaultFormProcessing(false);
        
        Button removeButton = new Button("remove"){
        	@Override
			public void onSubmit() {   
        		PVTDataAccessObject dao = PVTApplication.getDAO();
                dao.getPvtModel().removeProduct(product);
                dao.persist();
                setResponsePage(ProductsPage.class);
            }
        };
        removeButton.setDefaultFormProcessing(false);
        
        
        Form viewProductForm = new Form("form-viewproduct", new CompoundPropertyModel(product));

        
        viewProductForm.add(editButton);
        viewProductForm.add(backButton);
        viewProductForm.add(removeButton);
        viewProductForm.add(new TextField<String>("name"));
        viewProductForm.add(new TextField<String>("packages"));
        viewProductForm.add(new TextField<String>("maintainer"));
        viewProductForm.add(new TextField<String>("developer"));
        viewProductForm.add(new TextField<String>("qe"));
        viewProductForm.add(new TextArea<String>("description"));

        add(viewProductForm);
    }
}
