package concerttours.facade.impl;

import concerttours.data.Product2Data;
import concerttours.facade.Product2Facade;
import concerttours.model.Product2Model;
import concerttours.service.TrainingProductService;

public class DefaultProduct2Facade implements Product2Facade {
    private TrainingProductService trainingProductService;

    public void setTrainingProductService(TrainingProductService trainingProductService) {
        this.trainingProductService = trainingProductService;
    }

    @Override
    public Product2Data getProduct2Data(String code, String name) {
        Product2Model product2Model = (Product2Model) trainingProductService.getProductForCode(code, name);
        Product2Data product2Data = new Product2Data();

        product2Data.setId(product2Model.getCode());
        product2Data.setName(product2Model.getName());
        product2Data.setCatalogVersion(product2Model.getCatalogVersion().toString());
        product2Data.setFinalHashtag(product2Model.getHashtag().concat(product2Model.getHashtag2()));

        return product2Data;
    }
}
