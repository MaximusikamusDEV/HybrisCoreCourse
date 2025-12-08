package concerttours.controller;

import concerttours.data.CustomProductData;
import concerttours.facade.CustomProductFacade;
import concerttours.facade.impl.CustomProductPopulator;
import concerttours.jalo.CustomProduct;
import concerttours.model.CustomProductModel;
import concerttours.service.TrainingProductService;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class ProductWithPopulatorController implements Controller {
    private CatalogVersionService catalogVersionService;
    private CustomProductPopulator customProductPopulator;
    private TrainingProductService trainingProductService;

    public void setCatalogVersionService(final CatalogVersionService catalogVersionService)
    {
        this.catalogVersionService = catalogVersionService;
    }

    public void setCustomProductPopulator(CustomProductPopulator customProductPopulator) {
        this.customProductPopulator = customProductPopulator;
    }

    public void setTrainingProductService(TrainingProductService trainingProductService) {
        this.trainingProductService = trainingProductService;
    }

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        catalogVersionService.setSessionCatalogVersion("concertCatalog", "Online");

        String code = request.getParameter("code");
        String name = request.getParameter("name");
        Map<String, Object> model = new HashMap<>();
        CustomProductData customProductData = new CustomProductData();

        if(code != null) {
            try {
                CustomProductModel customProduct = (CustomProductModel) trainingProductService.getProductForCode(code, name);
                customProductPopulator.populate(customProduct, customProductData);
            } catch (UnknownIdentifierException e) {
                model.put("error", "Product code not found");
            }
        }

        model.put("product", customProductData);

        return new ModelAndView("productFacade", model);
    }
}
