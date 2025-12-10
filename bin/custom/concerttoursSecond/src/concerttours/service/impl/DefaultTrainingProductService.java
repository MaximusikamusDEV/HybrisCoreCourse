package concerttours.service.impl;

import concerttours.jalo.CustomProduct;
import concerttours.model.CustomProductModel;
import concerttours.service.TrainingProductService;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.impl.DefaultProductService;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

public class DefaultTrainingProductService extends DefaultProductService implements TrainingProductService {
    private final FlexibleSearchService flexibleSearchService;
    private final String QUERY_GET_CUSTOM_PRODUCT = "SELECT {pk} FROM {CustomProduct} WHERE {code}=?code AND {name} LIKE ?name";

    public DefaultTrainingProductService(FlexibleSearchService flexibleSearchService) {
        this.flexibleSearchService = flexibleSearchService;
    }

    @Override
    public ProductModel getProductForCode(String code, String name) {
        final FlexibleSearchQuery query = new FlexibleSearchQuery(
                QUERY_GET_CUSTOM_PRODUCT
        );

        query.addQueryParameter(CustomProduct.CODE, code);
        query.addQueryParameter(CustomProduct.NAME, name);
        final SearchResult<CustomProductModel> result = this.flexibleSearchService.search(query);
        final int resultCount = result.getTotalCount();
        if (resultCount == 0) {
            throw new UnknownIdentifierException("Product not found!");
        } else if (resultCount > 1) {
            throw new AmbiguousIdentifierException("Product code and name is not unique!");
        }
        return result.getResult().get(0);
    }
}
