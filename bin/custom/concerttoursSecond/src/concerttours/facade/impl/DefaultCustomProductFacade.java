package concerttours.facade.impl;

import concerttours.data.CustomProductData;
import concerttours.facade.CustomProductFacade;
import concerttours.model.CustomProductModel;
import concerttours.service.TrainingProductService;

public class DefaultCustomProductFacade implements CustomProductFacade {
    private TrainingProductService trainingProductService;
    private CustomProductConverter customProductConverter;

    public void setCustomProductConverter(CustomProductConverter customProductConverter) {
        this.customProductConverter = customProductConverter;
    }

    public void setTrainingProductService(TrainingProductService trainingProductService) {
        this.trainingProductService = trainingProductService;
    }

    @Override
    public CustomProductData getCustomProductData(String code, String name) {
        CustomProductModel customProductModel = (CustomProductModel) trainingProductService.getProductForCode(code, name);
        CustomProductData customProductData;

        customProductData = customProductConverter.convert(customProductModel);

        return customProductData;
    }
}
