package utils.helpers;

import static java.lang.Boolean.parseBoolean;

public class EnvironmentHelper {
    public final static String
            platform = System.getProperty("platform", "web"),
            browser = System.getProperty("browser", "chrome"),
            version = System.getProperty("version", "84.0.4147.135"),
            remoteDriverUrl = System.getProperty("remote_driver_url"), // https://username:password@selenoid.autotests.cloud:4444/wd/hub/
            videoStorageUrl = System.getProperty("video_storage_url"); // https://selenoid.autotests.cloud/video/

    public static final boolean
            isWeb = platform.equals("web"),
            isApi = platform.equals("api"),
            isHeadless = parseBoolean(System.getProperty("headless", "false")),
            isVideoOn = videoStorageUrl != null,
            isRemoteDriver = remoteDriverUrl != null;
}
