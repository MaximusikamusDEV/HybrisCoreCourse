package concerttours.jobs;

import concerttours.service.CustomSessionService;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.log4j.Logger;

public class CustomSessionTokenGenerateJob extends AbstractJobPerformable<CronJobModel> {
    private static final Logger LOG = Logger.getLogger(CustomSessionTokenGenerateJob.class);
    @Autowired
    private CustomSessionService customSessionService;

    public void setCustomSessionService(CustomSessionService customSessionService) {
        this.customSessionService = customSessionService;
    }

    @Override
    public PerformResult perform(CronJobModel cronJobModel) {
        return customSessionService.getCustomSessionToken()
                .map(customSession -> {
                    String oldToken = customSession.getToken();
                    String newToken = generateSessionToken(oldToken);
                    LOG.info("Updated token from: " + oldToken + " to: " + newToken);
                    customSessionService.updateCustomSessionToken(customSession, newToken);
                    return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
                })
                .orElseGet(() -> {
                    LOG.info("No custom session token found for cron job");
                    return new PerformResult(CronJobResult.FAILURE, CronJobStatus.FINISHED);
                });
    }

    private String generateSessionToken(String oldToken) {
        long oldTokenNumber = Long.parseLong(oldToken.substring("TOKEN_".length()));
        oldTokenNumber++;

        return "TOKEN_".concat(String.valueOf(oldTokenNumber));
    }
}
