import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.Function;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SegmentFormatTest {

    @ParameterizedTest
    @MethodSource("provideFormatterArgs")
    void createFormatter(String delimiter, int everyChars, boolean fromRight,
                         String input, String expected) {
        Function<String, String> formatter
                = SegmentFormat.createFormatter(
                delimiter, everyChars, fromRight);
        assertEquals(expected, formatter.apply(input));
    }

    static Stream<Arguments> provideFormatterArgs() {
        return Stream.of(
                Arguments.of("-", 1, true, "1234567890", "1-2-3-4-5-6-7-8-9-0"),
                Arguments.of("+", 1, true, "1234567890", "1+2+3+4+5+6+7+8+9+0"),
                Arguments.of("-", 2, true, "1234567890", "12-34-56-78-90"),
                Arguments.of("-", 3, true, "1234567890", "1-234-567-890"),
                Arguments.of("-", 3, false, "1234567890", "123-456-789-0"),
                Arguments.of("-", 2, true, "123456", "12-34-56"),
                Arguments.of("-", 2, true, "12345", "1-23-45"),
                Arguments.of("-", 2, false, "12345", "12-34-5"),
                Arguments.of("-", 2, true, "1234", "12-34"),
                Arguments.of("-", 2, true, "123", "1-23"),
                Arguments.of("-", 2, false, "123", "12-3"),
                Arguments.of("_", 2, true, "12", "12"),
                Arguments.of("-", 2, true, "1", "1"),
                Arguments.of("-", 2, true, "", ""),
                Arguments.of("-", 2, true, null, null)
        );
    }
}