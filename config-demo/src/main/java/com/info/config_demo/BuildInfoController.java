package com.info.config_demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class BuildInfoController {

    @Value("${build.name:default}")
    private String buildName;

    @Value("${build.version:default}")
    private String buildVersion;

    @Value("${build.id:default}")
    private String buildId;

    //    environment variable of system
    @Value("${OS:default}")     // in powershell ->  try $env:OS
    private String os;

    //    environment variable of system
    @Value("${JAVA_HOME:default}")     // in powershell ->  try $env:JAVA_HOME
    private String javaVersion;

    // direct value coming form .env file not mapped to .yml file
    @Value("${CUSTOM.ENVIRONMENT.VAR:default}")
    private String customVal;



    @GetMapping("/build-info")
    public String getBuildInfo() {
        //return "Build id:  " + buildId + " Build name: " + buildName + " Build version: " + buildVersion;
        return """
                Build id: %s
                Build name: %s
                Build version: %s
                ----------------------------------------
                Windows version: %s
                Java version: %s
                Environment: %s
                """.formatted(buildId, buildName, buildVersion, os, javaVersion, customVal);
    }


}
