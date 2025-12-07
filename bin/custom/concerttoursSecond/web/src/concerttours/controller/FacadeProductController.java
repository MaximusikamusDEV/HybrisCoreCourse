package concerttours.controller;

import concerttours.data.Product2Data;
import concerttours.facade.Product2Facade;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class FacadeProductController implements Controller {
    private CatalogVersionService catalogVersionService;
    private Product2Facade product2Facade;

    public void setCatalogVersionService(final CatalogVersionService catalogVersionService)
    {
        this.catalogVersionService = catalogVersionService;
    }

    public void setProduct2Facade(final Product2Facade product2Facade){
        this.product2Facade = product2Facade;
    }

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        catalogVersionService.setSessionCatalogVersion("concertCatalog", "Online");

        String code = request.getParameter("code");
        String name = request.getParameter("name");
        Map<String, Object> model = new HashMap<>();
        Product2Data product2Data = null;

        if(code != null) {
            try {
                product2Data = product2Facade.getProduct2Data(code, name);
            } catch (UnknownIdentifierException e) {
                model.put("error", "Product code not found");
            }
        }

        model.put("product", product2Data);

        return new ModelAndView("productFacade", model);
    }
}
