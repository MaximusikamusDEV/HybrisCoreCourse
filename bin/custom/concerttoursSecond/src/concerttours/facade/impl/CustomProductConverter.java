package concerttours.facade.impl;

import concerttours.data.CustomProductData;
import concerttours.model.CustomProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

public class CustomProductConverter implements Converter<CustomProductModel, CustomProductData> {
    private CustomProductPopulator customProductPopulator;

    public void setCustomProductPopulator(CustomProductPopulator customProductPopulator) {
        this.customProductPopulator = customProductPopulator;
    }

    @Override
    public CustomProductData convert(CustomProductModel customProductModel) throws ConversionException {
        CustomProductData customProductData = new CustomProductData();
        return convert(customProductModel, customProductData);
    }

    @Override
    public CustomProductData convert(CustomProductModel customProductModel, CustomProductData customProductData) throws ConversionException {
        customProductPopulator.populate(customProductModel, customProductData);

        return customProductData;
    }
}
