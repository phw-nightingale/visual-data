package com.deepazure.visualdata.config;

import com.deepazure.visualdata.util.Cmder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

@Slf4j
@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    @Value("${spring.application.client}")
    private String client;

    @Value("${spring.application.process-name}")
    private String processName;

    @Value("${spring.application.start-client}")
    private boolean startClient;

    @Override
    public void run(String... args) throws Exception {
        if (!startClient) return;
        log.info("Server start complete, running teach client...");
        startClient();
    }

    @PreDestroy
    public void preDestroy() throws Exception {
        log.info("Server is going to stopping, and teach client will be stopped soon...");
        killClient();
    }

    private void startClient() {
        Cmder.getInstance().exec(client);
    }

    private void killClient() {
        String cmd = "taskkill /f /t /im " + processName;
        Cmder.getInstance().exec(cmd);
    }
}
