package concerttours.controller;

import concerttours.model.Product2Model;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.search.restriction.SearchRestrictionService;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Collections;

@Controller
public class ProductController {
    private final ProductService productService;
    private final CatalogVersionService catalogVersionService;
    private final SearchRestrictionService searchRestrictionService;

    public ProductController(ProductService productService, CatalogVersionService catalogVersionService, SearchRestrictionService searchRestrictionService) {
        this.productService = productService;
        this.catalogVersionService = catalogVersionService;
        this.searchRestrictionService = searchRestrictionService;
    }

    @GetMapping("/product")
    public String getProductPage(@RequestParam String code, Model model) {
        searchRestrictionService.disableSearchRestrictions();

        if(code != null) {
            try {
                CatalogVersionModel catalogVersionModel = catalogVersionService.getCatalogVersion("concertCatalog",
                        "Online");

                catalogVersionService.setSessionCatalogVersions(Collections.singletonList(catalogVersionModel));

                Product2Model productModel = (Product2Model) productService.getProductForCode(catalogVersionModel, code);

                model.addAttribute("product", productModel);
            }catch (UnknownIdentifierException e) {
                model.addAttribute("error", "Product code not found");
            }
        }

        return "product";
    }
}
