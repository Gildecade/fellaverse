package com.fellaverse.backend.delay;

import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayTask implements Delayed {
    final private TaskBase data;
    final private long expire;

    /**
     * constructor, notice that expire time is set to a timestamp
     *
     * @param data   delay task time
     * @param expire how long to delay(ms)
     */
    public DelayTask(TaskBase data, long expire) {
        super();
        this.data = data;
        this.expire = expire + System.currentTimeMillis();
    }

    public TaskBase getData() {
        return data;
    }

    public long getExpire() {
        return expire;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DelayTask) {
            return this.data.getIdentifier().equals(((DelayTask) obj).getData().getIdentifier());
        }
        return false;
    }

    @Override
    public String toString() {
        return "{" + "data:" + data.toString() + "," + "expire:" + new Date(expire) + "}";
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.expire - System.currentTimeMillis(), unit);
    }

    @Override
    public int compareTo(Delayed o) {
        long delta = getDelay(TimeUnit.NANOSECONDS) - o.getDelay(TimeUnit.NANOSECONDS);
        return (int) delta;
    }
}
