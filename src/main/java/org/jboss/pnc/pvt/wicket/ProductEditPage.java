package org.jboss.pnc.pvt.wicket;


import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.jboss.pnc.pvt.dao.PVTDataAccessObject;
import org.jboss.pnc.pvt.model.Product;

/**
 * @author <a href="mailto:huwang@redhat.com">Hui Wang</a>
 *
 */
public class ProductEditPage extends ProductNewPage {
	
    public ProductEditPage(PageParameters pp) {
        this(pp, "PVT product is to be modified.");
    }
    
    public ProductEditPage(PageParameters pp, String info) {
    	super(pp, info);
    }
    
    @Override
    public String getTitle() {
        return "Modify a Prodcut";
    }
	
    @Override
    protected Product getProduct(PageParameters pp) {
        if (pp != null) {
            if (!pp.get("productId").isNull()) {
                PVTDataAccessObject dao = PVTApplication.getDAO();
                return dao.getPvtModel().getProductById(pp.get("productId").toString());
            }
        }

        return null;
    }
    
    @Override
    public void doSubmit() {
        PVTDataAccessObject dao = PVTApplication.getDAO();
        dao.getPvtModel().updateProduct(product);
        dao.persist();
        setInfo("Product: " + product.getName() + " is Updated.");
    }
    
    @Override
    public void doReset() {
    	productForm.getModel().setObject(product);
    }
    
    @Override
    public boolean doRemove() {
        PVTDataAccessObject dao = PVTApplication.getDAO();
        boolean success = dao.getPvtModel().removeProduct(product);
        if(success) {
            dao.persist();
            return true;
        }
        else {
            return false;
        }
    }
    
    
}
