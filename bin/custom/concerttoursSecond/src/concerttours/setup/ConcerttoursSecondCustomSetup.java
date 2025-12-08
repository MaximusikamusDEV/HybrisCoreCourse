package concerttours.setup;

import de.hybris.platform.core.initialization.SystemSetup;
import de.hybris.platform.servicelayer.impex.ImportConfig;
import de.hybris.platform.servicelayer.impex.ImportResult;
import de.hybris.platform.servicelayer.impex.ImportService;
import de.hybris.platform.servicelayer.impex.impl.StreamBasedImpExResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStream;

@SystemSetup(extension = "concerttoursSecond")
public class ConcerttoursSecondCustomSetup {
    private static final Logger LOG = LoggerFactory.getLogger(ConcerttoursSecondCustomSetup.class);
    @Autowired
    private ImportService importService;

    public ImportService getImportService() {
        return importService;
    }

    public void setImportService(ImportService importService) {
        this.importService = importService;
    }

    @SystemSetup(type = SystemSetup.Type.ESSENTIAL)
    public boolean addEssentialData(){
        LOG.info("Starting custom essential data loading for the ConcerttoursSecond extension");
        impexImport("/impex/concerttoursSecond-productAndCatalog.impex");
        LOG.info("Custom essential data loading for the ConcerttoursSecond extension completed.");
        return true;
    }

    @SystemSetup(type = SystemSetup.Type.PROJECT)
    public boolean addMyProjectData(){
        LOG.info("Starting custom project data loading for the ConcerttoursSecond extension");
        impexImport("/impex/concerttoursSecond-bandAndMusicType.impex");
        impexImport("/impex/concerttoursSecond-importTriggerForCustomSession.impex");
        impexImport("/impex/concerttoursSecond-superItem.impex");
        impexImport("/impex/concerttoursSecond-customSessionWithToken.impex");
        LOG.info("Custom project data loading for the ConcerttoursSecond extension completed.");
        return true;
    }

    protected boolean impexImport(final String filename)
    {
        final String message = "ConcerttoursSecond impexing [" + filename + "]...";
        try (final InputStream resourceAsStream = getClass().getResourceAsStream(filename))
        {
            LOG.info(message);
            final ImportConfig importConfig = new ImportConfig();
            importConfig.setScript(new StreamBasedImpExResource(resourceAsStream, "UTF-8"));
            importConfig.setLegacyMode(Boolean.FALSE);
            final ImportResult importResult = getImportService().importData(importConfig);
            if (importResult.isError())
            {
                LOG.error(message + " FAILED");
                return false;
            }
        }
        catch (final Exception e)
        {
            LOG.error(message + " FAILED", e);
            return false;
        }
        return true;
    }
}
