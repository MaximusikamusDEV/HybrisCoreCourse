package concerttours.facade.impl;

import concerttours.data.CustomProductData;
import concerttours.facade.CustomProductFacade;
import concerttours.model.CustomProductModel;
import concerttours.service.TrainingProductService;

public class DefaultCustomProductFacade implements CustomProductFacade {
    private TrainingProductService trainingProductService;

    public void setTrainingProductService(TrainingProductService trainingProductService) {
        this.trainingProductService = trainingProductService;
    }

    @Override
    public CustomProductData getCustomProductData(String code, String name) {
        CustomProductModel productModel = (CustomProductModel) trainingProductService.getProductForCode(code, name);
        CustomProductData customProductData = new CustomProductData();

        customProductData.setId(productModel.getCode());
        customProductData.setName(productModel.getName());
        customProductData.setCatalogVersion(productModel.getCatalogVersion().toString());
        customProductData.setFinalHashtag(productModel.getHashtag().concat(productModel.getCustomHashtag()));

        return customProductData;
    }
}
