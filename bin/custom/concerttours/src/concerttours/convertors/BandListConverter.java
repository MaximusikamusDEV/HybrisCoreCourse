package concerttours.convertors;

import concerttours.data.BandData;
import concerttours.model.BandModel;
import concerttours.populators.BandImageUrlPopulator;
import concerttours.populators.BandDetailPopulator;
import concerttours.populators.BandListPopulator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

public class BandListConverter implements Converter<BandModel, BandData> {
    private BandListPopulator bandListPopulator;
    private BandImageUrlPopulator bandImageUrlPopulator;

    public void setBandListPopulator(BandListPopulator bandListPopulator) {
        this.bandListPopulator = bandListPopulator;
    }

    public void setBandImageUrlPopulator(BandImageUrlPopulator bandImageUrlPopulator) {
        this.bandImageUrlPopulator = bandImageUrlPopulator;
    }

    @Override
    public BandData convert(BandModel bandModel) throws ConversionException {
        return convert(bandModel, new BandData());
    }

    @Override
    public BandData convert(BandModel bandModel, BandData bandData) throws ConversionException {
        bandListPopulator.populate(bandModel, bandData);
        bandImageUrlPopulator.populate(bandModel, bandData);
        return bandData;
    }
}
