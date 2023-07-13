package com.jimmy.studyspring.asyncmethod;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
@RequiredArgsConstructor
public class AppRunner implements CommandLineRunner {

    private final GitHubLookupService gitHubLookupService;

    @Override
    public void run(String... args) throws Exception {
        long start = System.currentTimeMillis();

        CompletableFuture<User> page1 =
                gitHubLookupService.findUser("PivotalSoftware");
        CompletableFuture<User> page2 =
                gitHubLookupService.findUser("CloudFoundry");
        CompletableFuture<User> page3 =
                gitHubLookupService.findUser("Spring-Projects");
        CompletableFuture<User> page4 =
                gitHubLookupService.findUser("sarasa0310");

        CompletableFuture.allOf(page1, page2, page3, page4).join();

        log.info("Elapsed time: " + (System.currentTimeMillis() - start));

        log.info("--> " + page1.get());
        log.info("--> " + page2.get());
        log.info("--> " + page3.get());
        log.info("--> " + page4.get());
    }

}
