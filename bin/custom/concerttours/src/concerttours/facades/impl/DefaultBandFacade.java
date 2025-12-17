package concerttours.facades.impl;

import de.hybris.platform.core.model.media.MediaContainerModel;
import de.hybris.platform.core.model.media.MediaFormatModel;
import de.hybris.platform.core.model.product.ProductModel;

import java.util.ArrayList;
import java.util.List;

import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.i18n.I18NService;
import de.hybris.platform.servicelayer.media.MediaService;
import concerttours.data.BandData;
import concerttours.data.TourSummaryData;
import concerttours.enums.MusicType;
import concerttours.facades.BandFacade;
import concerttours.model.BandModel;
import concerttours.service.BandService;

import java.util.Locale;

public class DefaultBandFacade implements BandFacade {
    public static final String BAND_LIST_FORMAT = "band.list.format.name";
    private static final String BAND_DETAIL_FORMAT = "band.detail.format.name";
    private MediaService mediaService;
    private BandService bandService;
    private ConfigurationService configService;
    private I18NService i18NService;

    public void setBandService(final BandService bandService) {
        this.bandService = bandService;
    }

    public void setMediaService(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    public void setConfigurationService(ConfigurationService configService) {
        this.configService = configService;
    }

    public void setI18NService(I18NService i18NService) {
        this.i18NService = i18NService;
    }

    @Override
    public List<BandData> getBands() {
        final List<BandModel> bandModels = bandService.getBands();
        final List<BandData> bandFacadeData = new ArrayList<>();

       // MediaFormatModel format = mediaService.getFormat("bandList");     Version without Properties

        if (bandModels != null && !bandModels.isEmpty()) {
            String mediaFormatName = configService.getConfiguration().getString(BAND_LIST_FORMAT);
            MediaFormatModel format = mediaService.getFormat(mediaFormatName);     //version with Properties

            Locale locale = i18NService.getCurrentLocale();

            for (final BandModel sm : bandModels) {
                final BandData sfd = new BandData();
                sfd.setId(sm.getCode());
                sfd.setName(sm.getName());
                sfd.setDescription(sm.getHistory(locale));
                sfd.setAlbumsSold(sm.getAlbumSales());
                sfd.setImageURL(getImageUrl(sm, format));
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

        final List<String> genres = new ArrayList<>();
        if (band.getTypes() != null) {
            for (final MusicType musicType : band.getTypes()) {
                genres.add(musicType.getCode());
            }
        }

        Locale locale = i18NService.getCurrentLocale();
        final List<TourSummaryData> tourHistory = new ArrayList<>();
        if (band.getTours() != null) {
            for (final ProductModel tour : band.getTours()) {
                final TourSummaryData summary = new TourSummaryData();
                summary.setId(tour.getCode());
                summary.setTourName(tour.getName(locale));
                summary.setNumberOfConcerts(Integer.toString(tour.getVariants().size()));
                tourHistory.add(summary);
            }
        }

        String mediaFormatName = configService.getConfiguration().getString(BAND_DETAIL_FORMAT);
        MediaFormatModel format = mediaService.getFormat(mediaFormatName);

        final BandData bandData = new BandData();
        bandData.setId(band.getCode());
        bandData.setName(band.getName());
        bandData.setAlbumsSold(band.getAlbumSales());
        bandData.setDescription(band.getHistory(locale));
        bandData.setGenres(genres);
        bandData.setTours(tourHistory);
        bandData.setImageURL(getImageUrl(band, format));
        return bandData;
    }

    protected String getImageUrl(BandModel sm, MediaFormatModel format) {
        MediaContainerModel containerModel = sm.getImage();

        if (containerModel != null) {
            return mediaService.getMediaByFormat(containerModel, format).getDownloadURL();
        }

        return null;
    }
}