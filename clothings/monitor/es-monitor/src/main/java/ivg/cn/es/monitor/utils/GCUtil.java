package ivg.cn.es.monitor.utils;

import java.util.concurrent.atomic.AtomicLong;

public class GCUtil {
	// 期待调用次数
	AtomicLong expectTimes = new AtomicLong(0);

	AtomicLong times = new AtomicLong(0);
	
	AtomicLong totalCount = new AtomicLong(0);
	
	AtomicLong totalTakeTime = new AtomicLong(0);
	
	AtomicLong takeTime = new AtomicLong(0);
	
	long avgTimeInMillis = 0;
	volatile long frequency = 0;
	
	public GCUtil(Long expectTimes) {
		this.expectTimes.set(expectTimes);
	}
	
	/** 次数是否有更新, 如果有更新就更新 */
	public boolean countHasUpdate(long totalOldValue,long newValue) {
		if (expectTimes.decrementAndGet() == 0) {
			frequency = times.get();
			// 做清空处理
			times.set(0);
			takeTime.set(0);
		}
		if (totalOldValue == newValue) {
			return false;
		}
		if (totalCount.compareAndSet(totalOldValue, newValue)) {
			// 如果更新成功
			times.addAndGet(newValue-totalOldValue);
			return true;
		}
		
		return false;
	}
	
	public long getTotalCount() {
		return totalCount.get();
	}
	
	public void updateTime(long newValue) {
		long take = newValue - totalTakeTime.get();
		totalTakeTime.set(newValue);
		takeTime.addAndGet(take);
	}
	
	public long avgTimeInMillis() {
		if (times.get() == 0) {
			return 0;
		}
		long value = takeTime.get()/times.get();
		return value;
	}
	
	public long frequency() {
		return frequency;
	}
	
}
