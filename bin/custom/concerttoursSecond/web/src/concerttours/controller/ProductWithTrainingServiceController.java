package concerttours.controller;

import concerttours.model.CustomProductModel;
import concerttours.service.TrainingProductService;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import java.util.Map;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProductWithTrainingServiceController implements Controller {
    private CatalogVersionService catalogVersionService;
    private TrainingProductService trainingProductService;

    public void setCatalogVersionService(final CatalogVersionService catalogVersionService)
    {
        this.catalogVersionService = catalogVersionService;
    }

    public void setTrainingProductService(final TrainingProductService trainingProductService)
    {
        this.trainingProductService = trainingProductService;
    }

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        catalogVersionService.setSessionCatalogVersion("concertCatalog", "Online");

        String code = request.getParameter("code");
        String name = request.getParameter("name");
        Map<String, Object> model = new HashMap<>();
        CustomProductModel product = null;

        if(code != null && name != null) {
            try {
                product = (CustomProductModel) trainingProductService.getProductForCode(code, name);
            } catch (UnknownIdentifierException e) {
                model.put("error", "Product code not found");
            }
        }

        model.put("product", product);

        return new ModelAndView("product", model);
    }
}
