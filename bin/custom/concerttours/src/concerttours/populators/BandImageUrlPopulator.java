package concerttours.populators;

import concerttours.daos.BandDAO;
import concerttours.data.BandData;
import concerttours.model.BandModel;
import concerttours.service.BandService;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.media.MediaContainerModel;
import de.hybris.platform.core.model.media.MediaFormatModel;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.media.MediaService;

public class BandImageUrlPopulator implements Populator<BandModel, BandData> {
    private MediaService mediaService;
    private ConfigurationService configService;
    private String formatPropertyKey;

    public void setMediaService(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    public void setConfigService(ConfigurationService configService) {
        this.configService = configService;
    }

    public void setFormatPropertyKey(String formatPropertyKey) {
        this.formatPropertyKey = formatPropertyKey;
    }

    @Override
    public void populate(BandModel bandModel, BandData bandData) throws ConversionException {
        String mediaFormatName = configService.getConfiguration().getString(formatPropertyKey);
        MediaFormatModel format = mediaService.getFormat(mediaFormatName);

        MediaContainerModel containerModel = bandModel.getImage();

        if (containerModel != null) {
            bandData.setImageURL(mediaService.getMediaByFormat(containerModel, format).getDownloadURL());
        }
    }
}
