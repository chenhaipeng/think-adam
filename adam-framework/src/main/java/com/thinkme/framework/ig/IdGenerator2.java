package com.thinkme.framework.ig;

import com.thinkme.utils.id.CommonIdGenerator;
import com.thinkme.utils.id.IdGenerator;
import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 根据机器IP获取工作进程Id,如果线上机器的IP二进制表示的最后10位不重复,建议使用此种方式
 * ,例如机器的IP为192.168.1.108,二进制表示:11000000 10101000 00000001 01101100
 * ,截取最后10位 01 01101100,转为十进制364,设置workerId为364.
 * <p>
 * 获取应用端口，将端口数值相加 workId = workId + port
 *
 * @author DonneyYoung
 * @author chenhaipeng
 */
@Slf4j
public class IdGenerator2 implements IdGenerator {


    private final static CommonIdGenerator commonIdGenerator = new CommonIdGenerator();

    static void initWorkerId(int port) {
        InetAddress address;
        try {
            address = InetAddress.getLocalHost();
        } catch (final UnknownHostException e) {
            throw new IllegalStateException("Cannot get LocalHost InetAddress, please check your network!", e);
        }
        byte[] ipAddressByteArray = address.getAddress();
        Long workId = (long) (((ipAddressByteArray[ipAddressByteArray.length - 2] & 0B11) << Byte.SIZE)
                + (ipAddressByteArray[ipAddressByteArray.length - 1] & 0xFF));
        char[] chars = String.valueOf(port).toCharArray();
        int sum = 0;
        for (int i = 0; i < chars.length; i++) {
            sum += chars[i];
        }
        workId += sum;
        CommonIdGenerator.setWorkerId(workId);
        log.info("初始化Id生成器,workId:{}", workId);
    }

    public static Long genId() {
        return commonIdGenerator.generateId();

    }

    @Override
    public long generateId() {
        return commonIdGenerator.generateId();
    }

}