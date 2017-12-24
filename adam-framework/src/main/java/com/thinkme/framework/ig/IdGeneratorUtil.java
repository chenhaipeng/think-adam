package com.thinkme.framework.ig;

import com.thinkme.utils.id.IPIdGenerator;
import com.thinkme.utils.id.IdGenerator;

/**
 * 生成主键id
 *
 * @author chenhaipeng
 * @version 1.0
 * @mail donotcoffee@gmail.com
 * @date 2017/12/22 下午8:48
 */
public class IdGeneratorUtil {
    public final static IdGenerator idGenerator = new IPIdGenerator();

    public static Long generateId() {
        return idGenerator.generateId();
    }
}
