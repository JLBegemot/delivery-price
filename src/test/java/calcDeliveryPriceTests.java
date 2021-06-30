import entities.Sizes;
import entities.WorkLoadTypes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;


public class calcDeliveryPriceTests {

    @ParameterizedTest
    @MethodSource("calcDeliveryPriceTestData")
    @Tag("calcItemsInfo")
    @DisplayName("Проверка расчета стоимости доставки")
    public void calcDeliveryPriceTests(double distance, Sizes size, boolean fragile, WorkLoadTypes workLoad, double expectedResult, int dataSetId) {
        try {
            BigDecimal result = DeliveryPriceCalcaulator.calcDeliveryPrice(distance, size, fragile, workLoad);
            Assertions.assertNotNull(result);
            Assertions.assertEquals(0, result.compareTo(BigDecimal.valueOf(expectedResult)), "Не корректный расчет, ожидаемый результат: "
                    + BigDecimal.valueOf(expectedResult) + " фактический: " + result.doubleValue() + " для сета: " + dataSetId);
        } catch (Exception e) {
            Assertions.assertEquals(e.getMessage(), "Хрупкие предметы нельзя перевозить на расстояние более 30км");
        }
    }

    private static Stream<Arguments> calcDeliveryPriceTestData() {


        return Stream.of(
                arguments(30.01, Sizes.SMALL, true, WorkLoadTypes.LITTLE_HIGH, 0, 1),
                arguments(30, Sizes.SMALL, true, WorkLoadTypes.LITTLE_HIGH, 840, 2),
                arguments(29.999, Sizes.BIG, true, WorkLoadTypes.VERY_HIGH, 1120, 3),
                arguments(10, Sizes.SMALL, false, WorkLoadTypes.HIGH, 420, 4),
                arguments(9.999, Sizes.BIG, true, WorkLoadTypes.LITTLE_HIGH, 720, 5),
                arguments(2, Sizes.BIG, true, WorkLoadTypes.NORMAL, 600, 6),
                arguments(1.999, Sizes.SMALL, true, WorkLoadTypes.LITTLE_HIGH, 540, 7),
                arguments(0.001, Sizes.SMALL, true, WorkLoadTypes.LITTLE_HIGH, 540, 8),
                arguments(0.001, Sizes.SMALL, false, WorkLoadTypes.LITTLE_HIGH, 400, 8)
        );
    }
}
