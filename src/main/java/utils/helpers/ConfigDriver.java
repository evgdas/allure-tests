package utils.helpers;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"classpath:${driver}.properties"})
public interface ConfigDriver extends Config {

    @Config.Key("remote_driver_url")
    String remoteURL();

    @Config.Key("video_storage_url")
    String remoteStorage();

}