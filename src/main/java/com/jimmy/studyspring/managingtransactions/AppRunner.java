package com.jimmy.studyspring.managingtransactions;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import static java.lang.Thread.sleep;

@Slf4j
@Component
@RequiredArgsConstructor
public class AppRunner implements CommandLineRunner {

    private final BookingService bookingService;

    @Override
    public void run(String... args) throws Exception {
        bookingService.book("Alice", "Bob", "Carol");
        Assert.isTrue(bookingService.findAllBookings().size() == 3,
                "First booking should work with no problem");

        sleep(3000);

        // 5글자 제한보다 더 긴 6글자의 Samuel 저장 시도 -> 트랜잭션 동작해서 롤백되어야함
        try {
            bookingService.book("Chris", "Samuel");
        } catch (RuntimeException e) {
            log.info("v--- The following exception is expect because 'Samuel' is too " +
                    "big for the DB ---v");
            log.error(e.getMessage());
        }

        sleep(3000);

        for (String person : bookingService.findAllBookings()) {
            log.info("So far, " + person + " is booked.");
        }
        log.info("You shouldn't see Chris or Samuel. Samuel violated DB constraints, " +
                "and Chris was rolled back in the same TX");
        Assert.isTrue(bookingService.findAllBookings().size() == 3,
                "'Samuel' should have triggered a rollback");

        sleep(3000);

        // not null 필드에 null 값 저장 시도
        try {
            bookingService.book("Buddy", null);
        } catch (RuntimeException e) {
            log.info("v--- The following exception is expect because null is not " +
                    "valid for the DB ---v");
            log.error(e.getMessage());
        }

        sleep(3000);

        for (String person : bookingService.findAllBookings()) {
            log.info("So far, " + person + " is booked.");
        }
        log.info("You shouldn't see Buddy or null. null violated DB constraints, and " +
                "Buddy was rolled back in the same TX");
        Assert.isTrue(bookingService.findAllBookings().size() == 3,
                "'null' should have triggered a rollback");
    }

}
