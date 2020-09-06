package utils.helpers;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:config.properties"})
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
}
