package concerttours.controller;

import concerttours.data.CustomProductData;
import concerttours.facade.CustomProductFacade;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class ProductWithFacadeController implements Controller {
    private CatalogVersionService catalogVersionService;
    private CustomProductFacade customProductFacade;

    public void setCatalogVersionService(final CatalogVersionService catalogVersionService)
    {
        this.catalogVersionService = catalogVersionService;
    }

    public void setCustomProductFacade(final CustomProductFacade customProductFacade){
        this.customProductFacade = customProductFacade;
    }

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        catalogVersionService.setSessionCatalogVersion("concertCatalog", "Online");

        String code = request.getParameter("code");
        String name = request.getParameter("name");
        Map<String, Object> model = new HashMap<>();
        CustomProductData customProductData = null;

        if(code != null) {
            try {
                customProductData = customProductFacade.getCustomProductData(code, name);
            } catch (UnknownIdentifierException e) {
                model.put("error", "Product code not found");
            }
        }

        model.put("product", customProductData);

        return new ModelAndView("productFacade", model);
    }
}
