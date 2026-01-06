package concerttours.populators;

import concerttours.convertors.ProducerConverter;
import concerttours.data.BandData;
import concerttours.data.ProducerData;
import concerttours.data.TourSummaryData;
import concerttours.enums.MusicType;
import concerttours.model.BandModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.i18n.I18NService;

import java.util.List;
import java.util.Locale;

public class BandDetailPopulator implements Populator<BandModel, BandData> {
    private I18NService i18NService;
    private ProducerConverter producerConverter;

    public void setProducerConverter(ProducerConverter producerConverter) {
        this.producerConverter = producerConverter;
    }

    public void setI18NService(I18NService i18NService) {
        this.i18NService = i18NService;
    }

    @Override
    public void populate(BandModel bandModel, BandData bandData) throws ConversionException {
        Locale locale = i18NService.getCurrentLocale();

        bandData.setId(bandModel.getCode());
        bandData.setName(bandModel.getName());
        bandData.setDescription(bandModel.getHistory(locale));
        bandData.setAlbumsSold(bandModel.getAlbumSales());

        List<TourSummaryData> tours = bandModel.getTours().stream()
                .map(tour -> {
                    TourSummaryData tourSummaryData = new TourSummaryData();
                    tourSummaryData.setId(tour.getCode());
                    tourSummaryData.setTourName(tour.getName(locale));
                    tourSummaryData.setNumberOfConcerts(
                            Integer.toString(tour.getVariants().size())
                    );

                    return tourSummaryData;
                }).toList();

        List<ProducerData> producersData = bandModel.getProducers().stream()
                .map(producerModel -> {
                    ProducerData producerData = new ProducerData();
                    producerConverter.convert(producerModel, producerData);
                    return producerData;
                }).toList();

        bandData.setGenres(
                bandModel.getTypes().stream()
                        .map(MusicType::getCode).toList()
        );
        bandData.setTours(tours);
        bandData.setProducers(producersData);
    }
}
