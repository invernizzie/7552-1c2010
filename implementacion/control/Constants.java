package control;

import control.MasksEnum;
import model.filters.impl.MaskFilter;
import model.filters.masks.Mask;
import model.filters.masks.impl.ClampingMask;
import model.filters.masks.impl.InvalidMaskException;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * @author Esteban I. Invernizzi (invernizzie@gmail.com)
 * @created 11/04/2010
 */
public class Constants {

    private static final Map<MasksEnum, Mask> masks =
            new HashMap<MasksEnum, Mask>();

    static {
    try {
        masks.put(MasksEnum.BLUR, ClampingMask.create(new double[][] {
                {1.0/9, 1.0/9, 1.0/9},
                {1.0/9, 1.0/9, 1.0/9},
                {1.0/9, 1.0/9, 1.0/9}}
            ));
        masks.put(MasksEnum.SHARPEN, ClampingMask.create(new double[][] {
                {-1.0/8, -1.0/8, -1.0/8},
                {-1.0/8,      2, -1.0/8},
                {-1.0/8, -1.0/8, -1.0/8}}
            ));
    } catch (InvalidMaskException e) {}
    }

    public static MaskFilter getMaskFilter(MasksEnum mask) throws NoSuchElementException {
        MaskFilter result = new MaskFilter();
        if (!masks.containsKey(mask))
            throw new NoSuchElementException("Mask " + mask.name() + " not registered.");
        result.addMask(masks.get(mask));
        return result;
    }
}
