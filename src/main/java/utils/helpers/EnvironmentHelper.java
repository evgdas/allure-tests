package utils.helpers;

public class EnvironmentHelper {
    public final static String
            platform = System.getProperty("platform", "web"),
            browser = System.getProperty("browser", "chrome"),
            version = System.getProperty("version"),
            driverFromFile = System.getProperty("driver"),
            remoteDriverUrl = System.getProperty("remote_driver_url"), // https://username:password@selenoid.autotests.cloud:4444/wd/hub/
            videoStorageUrl = System.getProperty("video_storage_url"); // https://selenoid.autotests.cloud/video/


    public static final boolean
            isWeb = platform.equals("web"),
            isVideoOn = videoStorageUrl != null,
            isDriverFromFile = driverFromFile != null,
            isRemoteDriver = remoteDriverUrl != null;
}
