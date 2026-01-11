package concerttours.populators;

import concerttours.data.ProducerData;
import concerttours.model.ProducerModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class ProducerPopulator implements Populator<ProducerModel, ProducerData> {
    @Override
    public void populate(ProducerModel producerModel, ProducerData producerData) throws ConversionException {
        producerData.setId(producerModel.getCode());
        producerData.setName(producerModel.getName());
        producerData.setSecondName(producerModel.getSecondName());
    }
}
