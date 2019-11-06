package com.example.test

import com.example.test.background.PersonLocal
import org.junit.Test
import java.util.logging.Logger


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun test_looper() {
        PersonLocal.testPersonLocal();
    }
}
