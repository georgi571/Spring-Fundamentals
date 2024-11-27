package com.philately.init;

import com.philately.service.PaperService;
import com.philately.service.StampService;
import com.philately.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final PaperService paperService;
    private final UserService userService;
    private final StampService stampService;

    public CommandLineRunnerImpl(PaperService paperService, UserService userService, StampService stampService) {
        this.paperService = paperService;
        this.userService = userService;
        this.stampService = stampService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.paperService.seedPapers();
        this.userService.seedUsers();
        this.stampService.seedStamps();
    }
}
