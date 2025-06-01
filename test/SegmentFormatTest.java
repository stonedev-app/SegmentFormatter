import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class SegmentFormatTest {

    @org.junit.jupiter.api.Test
    void createFormatter() {
        Function<String, String> formatter
                = SegmentFormat.createFormatter(
                "-",
                2,
                true);
        String result = formatter.apply("123456789");
        assertEquals("1-23-45-67-89", result);
    }
}