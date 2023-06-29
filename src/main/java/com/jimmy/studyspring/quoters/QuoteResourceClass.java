package com.jimmy.studyspring.quoters;

import lombok.*;

@Deprecated
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class QuoteResourceClass {

    private String type;
    private Quote value;

}
