package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum WorkLoadTypes {
    VERY_HIGH("VERY_HIGH", 1.6),
    HIGH("HIGH", 1.4),
    LITTLE_HIGH("LITTLE_HIGH", 1.2),
    NORMAL("NORMAL", 1.0);

    private String typeName;
    private double ratio;

}
