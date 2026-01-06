package concerttours.convertors;

import concerttours.data.ProducerData;
import concerttours.model.ProducerModel;
import concerttours.populators.ProducerPopulator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

public class ProducerConverter implements Converter<ProducerModel, ProducerData> {
    private ProducerPopulator producerPopulator;

    public void setProducerPopulator(ProducerPopulator producerPopulator) {
        this.producerPopulator = producerPopulator;
    }

    @Override
    public ProducerData convert(ProducerModel producerModel) throws ConversionException {
        return convert(producerModel, new ProducerData());
    }

    @Override
    public ProducerData convert(ProducerModel producerModel, ProducerData producerData) throws ConversionException {
        producerPopulator.populate(producerModel, producerData);
        return producerData;
    }
}
