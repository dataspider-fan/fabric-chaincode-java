/*
Copyright IBM Corp. All Rights Reserved.

SPDX-License-Identifier: Apache-2.0
*/
package org.hyperleder.fabric.shim.integration.shimtests;

import static org.hamcrest.core.StringContains.containsString;
import static org.hamcrest.Matchers.not; 
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;

import org.hyperleder.fabric.shim.integration.util.FabricState;
import org.hyperleder.fabric.shim.integration.util.InvokeHelper;
import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;
import org.hyperledger.fabric.sdk.exception.ProposalException;
import org.junit.BeforeClass;
import org.junit.Test;

public class SBECCIntegrationTest {

    @BeforeClass
    public static void setUp() throws Exception {
        FabricState.getState().start();
        

    }

    @Test
    public void RunSBE_pub_setget() throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException,
            IOException, ProposalException, InvalidArgumentException {
        final String mode = "pub";

        final InvokeHelper helper = InvokeHelper.newHelper("shimcc", "sachannel");

        String text;

        text = helper.invoke("org1",new String[] {"EndorsementCC:setval", mode, "foo"});
        assertThat(text, containsString("success"));

        text = helper.invoke("org1",new String[] {"EndorsementCC:getval", mode});
        assertThat(text, containsString("foo"));

        text = helper.invoke("org1",new String[] {"EndorsementCC:addorgs", mode, "org1MSP"});
        assertThat(text, containsString("success"));

        text = helper.invoke("org1",new String[] {"EndorsementCC:listorgs", mode});
        assertThat(text, containsString("org1MSP"));

        text = helper.invoke("org1",new String[] {"EndorsementCC:setval", mode, "val1"});
        assertThat(text, containsString("success"));

        text = helper.invoke("org1",new String[] {"EndorsementCC:getval", mode});
        assertThat(text, containsString("val1"));

        text = helper.invoke("org1",new String[] {"EndorsementCC:setval", mode, "val2"});

        text = helper.invoke("org1",new String[] {"EndorsementCC:getval", mode});
        assertThat(text, containsString("val2"));

        text = helper.invoke("org1",new String[] {"EndorsementCC:addorgs", mode, "org2MSP"});
        assertThat(text, containsString("success"));

        text = helper.invoke("org1",new String[] {"EndorsementCC:listorgs", mode});
        assertThat(text, containsString("org2MSP"));
        assertThat(text, containsString("org1MSP"));


        text = helper.invoke("org1",new String[] {"EndorsementCC:setval", mode, "val3"});
        assertThat(text, containsString("success"));

        text = helper.invoke("org1",new String[] {"EndorsementCC:getval", mode});
        assertThat(text, containsString("val3"));

        text = helper.invoke("org1",new String[] {"EndorsementCC:setval", mode, "val4"});
        assertThat(text, containsString("success"));

        text = helper.invoke("org1",new String[] {"EndorsementCC:getval", mode});
        assertThat(text, containsString("val4"));

        text = helper.invoke("org1",new String[] {"EndorsementCC:delorgs", mode, "org1MSP"});
        assertThat(text, containsString("success"));

        text = helper.invoke("org1",new String[] {"EndorsementCC:listorgs", mode});
        assertThat(text, containsString("org2MSP"));
        assertThat(text, not(containsString("org1MSP")));

        text = helper.invoke("org1",new String[] {"EndorsementCC:deleteval", mode});
        assertThat(text, containsString("success"));
        text = helper.invoke("org1",new String[] {"EndorsementCC:recordExists", mode});
        assertThat(text, containsString("false"));

    }

   @Test
    public void RunSBE_priv() throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException,
            IOException, ProposalException, InvalidArgumentException {
        final String mode = "priv";

        final InvokeHelper helper = InvokeHelper.newHelper("shimcc", "sachannel");

        String text;

        text = helper.invoke("org1",new String[] {"EndorsementCC:setval", mode, "foo"});
        assertThat(text, containsString("success"));

        text = helper.invoke("org1",new String[] {"EndorsementCC:getval", mode});
        assertThat(text, containsString("foo"));

        text = helper.invoke("org1",new String[] {"EndorsementCC:addorgs", mode, "org1MSP"});
        assertThat(text, containsString("success"));

        text = helper.invoke("org1",new String[] {"EndorsementCC:listorgs", mode});
        assertThat(text, containsString("org1MSP"));

        text = helper.invoke("org1",new String[] {"EndorsementCC:setval", mode, "val1"});
        assertThat(text, containsString("success"));

        text = helper.invoke("org1",new String[] {"EndorsementCC:getval", mode});
        assertThat(text, containsString("val1"));

        text = helper.invoke("org1",new String[] {"EndorsementCC:setval", mode, "val2"});
        assertThat(text, containsString("success"));

        text = helper.invoke("org1",new String[] {"EndorsementCC:getval", mode});
        assertThat(text, containsString("val2"));

        text = helper.invoke("org1",new String[] {"EndorsementCC:addorgs", mode, "org2MSP"});
        assertThat(text, containsString("success"));

        text = helper.invoke("org1",new String[] {"EndorsementCC:listorgs", mode});
        assertThat(text, containsString("org2MSP"));
        assertThat(text, containsString("org1MSP"));
      

        text = helper.invoke("org1",new String[] {"EndorsementCC:setval", mode, "val3"});
        assertThat(text, containsString("success"));

        text = helper.invoke("org1",new String[] {"EndorsementCC:getval", mode});
        assertThat(text, containsString("val3"));

        text = helper.invoke("org1",new String[] {"EndorsementCC:setval", mode, "val4"});
        assertThat(text, containsString("success"));

        text = helper.invoke("org1",new String[] {"EndorsementCC:getval", mode});
        assertThat(text, containsString("val4"));

        text = helper.invoke("org1",new String[] {"EndorsementCC:delorgs", mode, "org1MSP"});
        assertThat(text, containsString("success"));

        text = helper.invoke("org1",new String[] {"EndorsementCC:listorgs", mode});
        assertThat(text, containsString("org2MSP"));
        assertThat(text, not(containsString("org1MSP")));

        text = helper.invoke("org1",new String[] {"EndorsementCC:deleteval", mode});
        assertThat(text, containsString("success"));
        text = helper.invoke("org1",new String[] {"EndorsementCC:recordExists", mode});
        assertThat(text, containsString("false"));

    }

}
