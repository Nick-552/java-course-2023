package edu.hw2.ex4;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CallingInfoUtilTest {
    private static final CallingInfo CALLING_INFO = CallingInfoUtil.callingInfo();

    @Test
    void callingInfo_should() {
        CallingInfo callingInfoExpected = new CallingInfo(
            "edu.hw2.ex4.CallingInfoUtilTest",
            "<clinit>"
        );
        assertThat(CALLING_INFO).isEqualTo(callingInfoExpected);
    }

    @Test
    void callingInfo_shouldReturnThisMethodAndThisClass() {
        CallingInfo callingInfoExpected = new CallingInfo(
            "edu.hw2.ex4.CallingInfoUtilTest",
            "callingInfo_shouldReturnThisMethodAndThisClass"
        );
        assertThat(CallingInfoUtil.callingInfo()).isEqualTo(callingInfoExpected);
    }
}
