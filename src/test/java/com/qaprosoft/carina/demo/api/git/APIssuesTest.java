/*
 * Copyright 2013-2021 QAPROSOFT (http://qaprosoft.com/).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.qaprosoft.carina.demo.api.git;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.qaprosoft.carina.core.foundation.api.http.HttpResponseStatusType;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import com.qaprosoft.carina.demo.api.apiTest.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.invoke.MethodHandles;

/**
 * This sample shows how create REST API tests.
 *
 * @author qpsdemo
 */
public class APIssuesTest implements IAbstractTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    public static int RESPONSE_STATUS_CODE = 204;
    @Test()
    @MethodOwner(owner = "qpsdemo")
    public void testCreateNewIssue() {
        PostNewIssueMethod api = new PostNewIssueMethod();
        api.expectResponseStatus(HttpResponseStatusType.CREATED_201);
        api.callAPI();
        api.validateResponse();
    }

    @Test()
    @MethodOwner(owner = "qpsdemo")
    public void testUpdateIssue() {
        PatchIssueMethod api = new PatchIssueMethod();
        api.expectResponseStatus(HttpResponseStatusType.OK_200);
        api.callAPI();
        api.validateResponse();
    }

    @Test()
    @MethodOwner(owner = "qpsdemo")
    public void testLockIssue() {
        PutLockIssueMethod api = new PutLockIssueMethod();
        api.expectResponseStatus(HttpResponseStatusType.NO_CONTENT_204);
        api.callAPI();
        int response = api.callAPI().statusCode();
        Assert.assertEquals(response, RESPONSE_STATUS_CODE,"response are not valid");
    }

    @Test()
    @MethodOwner(owner = "qpsdemo")
    public void testUnlockIssue() {
        DelUnlockIssueMethod api = new DelUnlockIssueMethod();
        api.expectResponseStatus(HttpResponseStatusType.NO_CONTENT_204);
        api.callAPI();
        //api.validateResponse();
    }

    @Test()
    @MethodOwner(owner = "qpsdemo")
    public void testReturnRepoIssueList() {
        GetIssuesListMethod api = new GetIssuesListMethod();
        api.expectResponseStatus(HttpResponseStatusType.OK_200);
        api.callAPI();
        api.validateResponse();
    }
}
