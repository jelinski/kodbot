package pl.jellysoft.kodbot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {

    @Value("${git.branch}")
    private String branchName;

    @Value("${git.commit.id}")
    private String revision;

    @Value("${git.commit.time}")
    private String commitTime;

    @GetMapping("/version")
    public VersionInfo obtainVersionInfo() {
        return new VersionInfo(branchName, revision, commitTime);
    }

    @lombok.Value
    static class VersionInfo {

        private final String branchName;
        private final String revision;
        private final String commitTime;

    }

}
