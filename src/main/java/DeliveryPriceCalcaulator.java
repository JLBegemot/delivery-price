import entities.Sizes;
import entities.WorkLoadTypes;

import java.math.BigDecimal;

public class DeliveryPriceCalcaulator {

    public static BigDecimal calcDeliveryPrice(double distance, Sizes size, boolean fragile, WorkLoadTypes workLoad) throws Exception {

        if (fragile == true && distance > 30.00)
            throw new Exception("Хрупкие предметы нельзя перевозить на расстояние более 30км");

        BigDecimal result = new BigDecimal(0);

        if (distance < 2.00) result = result.add(BigDecimal.valueOf(50));
        else if (2.00 <= distance && distance < 10.00) result = result.add(BigDecimal.valueOf(100));
        else if (10.00 <= distance && distance < 30.00) result = result.add(BigDecimal.valueOf(200));
        else if (distance >= 30.00) result = result.add(BigDecimal.valueOf(300));

        switch (size) {
            case BIG: {
                result = result.add(BigDecimal.valueOf(200));
                break;
            }
            case SMALL: {
                result = result.add(BigDecimal.valueOf(100));
                break;
            }
        }

        if (fragile == true) result = result.add(BigDecimal.valueOf(300));

        switch (workLoad) {
           case VERY_HIGH: {
                result = result.multiply(BigDecimal.valueOf(WorkLoadTypes.VERY_HIGH.getRatio()));
                break;
            }
            case HIGH: {
                result = result.multiply(BigDecimal.valueOf(WorkLoadTypes.HIGH.getRatio()));
                break;
            }
            case LITTLE_HIGH: {
                result = result.multiply(BigDecimal.valueOf(WorkLoadTypes.LITTLE_HIGH.getRatio()));
                break;
            }
            case NORMAL: {
                result = result.multiply(BigDecimal.valueOf(WorkLoadTypes.NORMAL.getRatio()));
                break;
            }
        }

        if (result.doubleValue() < 400.00) return new BigDecimal(400);

        return result;

    }
}
