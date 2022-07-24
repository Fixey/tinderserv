package tinder.tindesrv.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum CrushType {
    MEN("Сударъ"),
    WOMEN("Сударыня"),
    ALL("Все");
    private static final Map<String, CrushType> BY_CRUSH_TYPE = new HashMap<>();

    public final String crushType;

    static {
        for (CrushType crushType : values()) {
            BY_CRUSH_TYPE.put(crushType.crushType, crushType);
        }
    }

    private CrushType(String label) {
        this.crushType = label;
    }

    public static CrushType valueOfCrushType(String crushType) {
        for (CrushType crushType1 : values()) {
            if (crushType1.crushType.equals(crushType)) {
                return crushType1;
            }
        }
        return null;
    }

    public static List<CrushType> valuesExceptCrushType(String crushType) {
        List<CrushType> crushTypeList = new ArrayList<>();
        for (CrushType crushType1 : values()) {
            if (!crushType1.crushType.equals(crushType)) {
                crushTypeList.add(crushType1);
            }
        }
        return crushTypeList;
    }

    public static CrushType valueOfLabel(String label) {
        return BY_CRUSH_TYPE.get(label);
    }
}
