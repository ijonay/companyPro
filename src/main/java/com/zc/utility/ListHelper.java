package com.zc.utility;

import java.util.Arrays;
import java.util.List;

/**
 * Created by polun on 2016/9/23.
 */
public interface ListHelper {

    @SafeVarargs
    static <T> Boolean isEmpty(List<T>... objects) {
        return Arrays.stream(objects).anyMatch(os -> (os == null || os.size() < 1));
    }
}
