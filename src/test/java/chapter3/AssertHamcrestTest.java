package chapter3;

import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class AssertHamcrestTest {

    @Test
    @Ignore
    public void assertDoublesEqual() {
        assertThat(2.32 * 3, equalTo(6.96));
    }

    @Test
    public void assertWithTolerance() {
        assertTrue(Math.abs(2.32 * 3) - 6.96 < 0.0005);
    }
}
