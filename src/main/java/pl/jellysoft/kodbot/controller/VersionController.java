package pl.jellysoft.kodbot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class VersionController {

    @Value("${git.branch}")
    private String branchName;

    @Value("${git.commit.id}")
    private String revision;

    @Value("${git.build.time}")
    private String buildTime;

    @RequestMapping(method = GET, value = "/version")
    public VersionInfo obtainVersionInfo() {
        return new VersionInfo(branchName, revision, buildTime);
    }

    @lombok.Value
    static class VersionInfo {

        private final String branchName;
        private final String revision;
        private final String buildTime;

    }

}
