package utils.helpers;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config.properties"})
public interface ConfigGithub extends Config {

    @DefaultValue("")
    @Key("github.password")
    String password();

    @DefaultValue("")
    @Key("github.login")
    String login();

    @DefaultValue("")
    @Key("github.token")
    String token();

    @DefaultValue("")
    @Key("github.base_url")
    String baseUrl();
}
