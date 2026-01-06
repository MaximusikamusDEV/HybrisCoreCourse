package concerttours.convertors;

import concerttours.data.BandData;
import concerttours.model.BandModel;
import concerttours.populators.BandImageUrlPopulator;
import concerttours.populators.BandDetailPopulator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

public class BandDetailConverter implements Converter<BandModel, BandData> {
    private BandDetailPopulator bandDetailPopulator;
    private BandImageUrlPopulator bandImageUrlPopulator;

    public void setBandImageUrlPopulator(BandImageUrlPopulator bandImageUrlPopulator) {
        this.bandImageUrlPopulator = bandImageUrlPopulator;
    }

    public void setBandDetailPopulator(BandDetailPopulator bandDetailPopulator) {
        this.bandDetailPopulator = bandDetailPopulator;
    }

    @Override
    public BandData convert(BandModel bandModel) throws ConversionException {
        return convert(bandModel, new BandData());
    }

    @Override
    public BandData convert(BandModel bandModel, BandData bandData) throws ConversionException {
        bandDetailPopulator.populate(bandModel, bandData);
        bandImageUrlPopulator.populate(bandModel, bandData);
        return bandData;
    }
}
