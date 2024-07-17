package com.article.multithreadbatch.scheduler;

import com.article.multithreadbatch.service.DemoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ThreadDemoScheduler {

    private final DemoService demoService;

    // runs exactly ones on up the project
    @Scheduled (initialDelay = 1000, fixedRate = Long.MAX_VALUE)
    public void demo() {
        demoService.executeDemoBatch();
    }
}
