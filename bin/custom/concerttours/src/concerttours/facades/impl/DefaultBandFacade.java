package concerttours.facades.impl;

import concerttours.convertors.BandDetailConverter;
import concerttours.convertors.BandListConverter;
import de.hybris.platform.core.model.media.MediaFormatModel;
import de.hybris.platform.core.model.product.ProductModel;

import java.util.ArrayList;
import java.util.List;

import concerttours.data.BandData;
import concerttours.data.TourSummaryData;
import concerttours.enums.MusicType;
import concerttours.facades.BandFacade;
import concerttours.model.BandModel;
import concerttours.service.BandService;

import java.util.Locale;

public class DefaultBandFacade implements BandFacade {
    private BandService bandService;
    private BandDetailConverter bandDetailConverter;
    private BandListConverter bandListConverter;

    public void setBandService(BandService bandService) {
        this.bandService = bandService;
    }

    public void setBandDetailConverter(BandDetailConverter bandDetailConverter) {
        this.bandDetailConverter = bandDetailConverter;
    }

    public void setBandListConverter(BandListConverter bandListConverter) {
        this.bandListConverter = bandListConverter;
    }

    @Override
    public List<BandData> getBands() {
        final List<BandModel> bandModels = bandService.getBands();
        final List<BandData> bandFacadeData = new ArrayList<>();

        if (bandModels != null && !bandModels.isEmpty()) {
            for (final BandModel sm : bandModels) {
                final BandData sfd = new BandData();
                bandListConverter.convert(sm, sfd);
                bandFacadeData.add(sfd);
            }
        }

        return bandFacadeData;
    }

    @Override
    public BandData getBand(final String name) {
        if (name == null) {
            throw new IllegalArgumentException("Band name cannot be null");
        }
        final BandModel band = bandService.getBandForCode(name);
        if (band == null) {
            return null;
        }

        return bandDetailConverter.convert(band);
    }
}