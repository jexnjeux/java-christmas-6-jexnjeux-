package christmas.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import christmas.utils.Validator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ValidatorTest {

    @DisplayName("유효한 날 입력")
    @Test
    void testValidDate_ValidDate() {
        String input = "5";
        assertTrue(Validator.validateDate(input));
    }

    @DisplayName("31 이상 날짜 입력 시 false")
    @Test
    void testValidDate_InvalidDate() {
        String input = "40";
        assertFalse(Validator.validateDate(input));
    }

    @DisplayName("숫자 아닌 날짜 입력 시 false")
    @Test
    void testValidDate_NonNumericInput() {
        String input = "ㄱㄴㄷ";
        assertFalse(Validator.validateDate(input));
    }

    @DisplayName("유효한 주문")
    @Test
    void validateOrder_ValidOrder() {
        String input = "해산물파스타-2,레드와인-1,초코케이크-1";
        assertTrue(Validator.validateOrder(input));
    }

    @DisplayName("주문 유효성 검사")
    @ParameterizedTest(name = "{displayName} - [{index}]")
    @CsvSource(value = {
            "치킨-2,레드와인-1,초코케이크-1; false",
            "해산물파스타-0,레드와인-1,초코케이크-1; false",
            "해산물파스타0,레드와인1,초코케이크1; false",
            "해산물파스타-1,해산물파스타-1,초코케이크-1; false",
            "해산물파스타-ㄱㄷㄴㄷㄱ,해산물파스타-1,초코케이크-1; false",
            "해산물파스타-1,레드와인-1,초코케이크-1; true"
    }, delimiter = ';')
    void validateOrder(String input, boolean expected) {
        assertEquals(expected, Validator.validateOrder(input));
    }

}