package concerttours.service;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.ProductService;

public interface TrainingProductService extends ProductService {
    ProductModel getProductForCode(String code, String name);
}
