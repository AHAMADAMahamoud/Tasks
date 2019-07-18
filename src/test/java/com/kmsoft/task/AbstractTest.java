package com.kmsoft.task;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 
 * (Description)
 *
 * @since 18 juil. 2019
 * @author mohamed.hanafi
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Config.class)
@ActiveProfiles({"test"})
@Ignore
public class AbstractTest {

}
