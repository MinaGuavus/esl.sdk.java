package com.silanis.esl.sdk.examples;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by mpoitras on 22/04/14.
 */
public class SenderAuthenticationTokenExampleTest {
    @Test
    public void verifyResult() {
        SenderAuthenticationTokenExample senderAuthenticationTokenExample = new SenderAuthenticationTokenExample( Props.get() );
        senderAuthenticationTokenExample.run();

        assertThat(senderAuthenticationTokenExample.getSessionIdForSender(), is(notNullValue()));
    }

}
