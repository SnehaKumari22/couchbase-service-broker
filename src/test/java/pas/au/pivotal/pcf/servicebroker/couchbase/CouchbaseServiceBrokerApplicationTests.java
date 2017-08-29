package pas.au.pivotal.pcf.servicebroker.couchbase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pas.au.pivotal.pcf.servicebroker.couchbase.service.CouchbaseAdminService;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CouchbaseServiceBrokerApplicationTests {

    private final String testBucketName = "testbucket";
    private final String testPassword = "qrwrwgeg15!";

    @Autowired
    private CouchbaseAdminService couchbaseAdminService;

    @Test
    public void aa_createDatabase()
    {
        couchbaseAdminService.createDatabase(testBucketName, testPassword);
        Assert.assertTrue(couchbaseAdminService.hasBucket(testBucketName));
    }

    @Test
    public void ab_hasBucket()
    {
        Assert.assertTrue(couchbaseAdminService.hasBucket(testBucketName));
    }

    @Test
    public void ac_addPrimaryIndex()
    {
        boolean result = couchbaseAdminService.createPrimaryIndex(testBucketName, testPassword);
        Assert.assertTrue("true", result);
    }

    @Test
    public void ad_deleteDatabase()
    {
        couchbaseAdminService.deleteDatabase(testBucketName);
        Assert.assertFalse(couchbaseAdminService.hasBucket(testBucketName));
    }

}
