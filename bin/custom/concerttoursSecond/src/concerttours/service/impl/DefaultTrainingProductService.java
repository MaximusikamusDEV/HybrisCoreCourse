package concerttours.service.impl;

import concerttours.jalo.Product2;
import concerttours.model.Product2Model;
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

    public DefaultTrainingProductService(FlexibleSearchService flexibleSearchService) {
        this.flexibleSearchService = flexibleSearchService;
    }

    @Override
    public ProductModel getProductForCode(String code, String name) {
        final FlexibleSearchQuery query = new FlexibleSearchQuery(
                "SELECT {pk} FROM {Product2} WHERE {code}=?code AND {name} LIKE ?name"
        );

        query.addQueryParameter(Product2.CODE, code);
        query.addQueryParameter(Product2.NAME, name);
        final SearchResult<Product2Model> result = this.flexibleSearchService.search(query);
        final int resultCount = result.getTotalCount();
        if (resultCount == 0)
        {
            throw new UnknownIdentifierException("Product not found!");
        }
        else if (resultCount > 1)
        {
            throw new AmbiguousIdentifierException("Product code and name is not unique!");
        }
        return result.getResult().get(0);
    }
}
