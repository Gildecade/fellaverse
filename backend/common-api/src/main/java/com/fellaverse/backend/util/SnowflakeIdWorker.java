package com.fellaverse.backend.util;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;

import java.net.Inet4Address;
import java.net.UnknownHostException;

public class SnowflakeIdWorker {

    // first bit in binary is signed flag, 1 negative, 0 positive
    // there are only positive numbers, so the first bit is 0

    // machine ID, 5 bits, 31 in total
    private long workerId;
    // center ID, 5 bits, 31 in total
    private long datacenterId;
    // generated ID in 1 ms, 12 bits, 4096 -1 = 4095 in total
    private long sequence = 1L;
    // start timestamp    2^41 - 1   for about 69 years
    private long twepoch = 1678546958667L;
    private long workerIdBits = 5L;
    private long datacenterIdBits = 5L;
    private long sequenceBits = 12L;
    // 2 ^ 5 - 1
    private long maxWorkerId = -1L ^ (-1L << workerIdBits);
    // 2 ^ 5 - 1
    private long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);

    private long workerIdShift = sequenceBits;
    private long datacenterIdShift = sequenceBits + workerIdBits;
    private long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
    private long sequenceMask = -1L ^ (-1L << sequenceBits);
    // check if in the same ms
    private long lastTimestamp = -1L;
    private static SnowflakeIdWorker idWorker;

    static {
        idWorker = new SnowflakeIdWorker(getWorkId(),getDataCenterId());
    }
    private static Long getWorkId(){
        try {
            String hostAddress = Inet4Address.getLocalHost().getHostAddress();
            int[] ints = StringUtils.toCodePoints(hostAddress);
            int sums = 0;
            for(int b : ints){
                sums += b;
            }
            return (long)(sums % 32);
        } catch (UnknownHostException e) {
            // if failed, use random number
            return RandomUtils.nextLong(0,31);
        }
    }

    private static Long getDataCenterId(){
        int[] ints = StringUtils.toCodePoints(SystemUtils.getHostName());
        int sums = 0;
        for (int i: ints) {
            sums += i;
        }
        return (long)(sums % 32);
    }



    public SnowflakeIdWorker(long workerId, long datacenterId) {

        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(
                    String.format("worker Id can't be greater than %d or less than 0",maxWorkerId));
        }

        if (datacenterId > maxDatacenterId || datacenterId < 0) {

            throw new IllegalArgumentException(
                    String.format("datacenter Id can't be greater than %d or less than 0",maxDatacenterId));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    // core method, generate a global unique snowflake ID
    public synchronized long nextId() {
        // current timestamp
        long timestamp = timeGen();
        if (timestamp < lastTimestamp) {

            System.err.printf(
                    "clock is moving backwards. Rejecting requests until %d.", lastTimestamp);
            throw new RuntimeException(
                    String.format("Clock moved backwards. Refusing to generate id for %d milliseconds",
                            lastTimestamp - timestamp));
        }

        // received multiple requests in the same ms
        // sequence increase by 1, max 4096
        if (lastTimestamp == timestamp) {

            // secure sequence ID mod 4096
            sequence = (sequence + 1) & sequenceMask;
            // if overflows, wait till next ms
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }

        } else {
            sequence = 0;
        }
        lastTimestamp = timestamp;
        // generate a 64bit ID
        // shift timestamp etc. to right place
        // concat to a 64 bits binary number, convert to decimal as Long
        return ((timestamp - twepoch) << timestampLeftShift) |
                (datacenterId << datacenterIdShift) |
                (workerId << workerIdShift) | sequence;
    }

    /**
     * if generated id number > 4095 in a certain ms, wait till next ms to continue
     * @param lastTimestamp
     * @return
     */
    private long tilNextMillis(long lastTimestamp) {

        long timestamp = timeGen();

        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }
    // get current timestamp
    private long timeGen(){
        return System.currentTimeMillis();
    }

    /**
     * static utils class
     *
     * @return global unique id
     */
    public static synchronized Long generateId(){
        return idWorker.nextId();
    }
//    /**
//     *  main test class
//     * @param args
//     */
//    public static void main(String[] args) {
//        SnowflakeIdWorker worker = new SnowflakeIdWorker(1,1);
//        System.out.println(worker.timeGen());
//		for (int i = 0; i < 22; i++) {
//			System.out.println(worker.nextId());
//		}
//    }
}
