package concerttours.facade.impl;

import concerttours.data.CustomProductData;
import concerttours.model.CustomProductModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class CustomProductPopulator implements Populator<CustomProductModel, CustomProductData> {
    @Override
    public void populate(CustomProductModel customProductModel, CustomProductData customProductData) throws ConversionException {
        customProductData.setCatalogVersion(customProductModel.getCatalogVersion().getCategorySystemName());
        customProductData.setName(customProductModel.getName());
        customProductData.setFinalHashtag(customProductModel.getHashtag().concat(customProductModel.getCustomHashtag()));
        customProductData.setId(customProductModel.getTenantId());
    }
}
