package ivg.cn.clo.common;

import java.util.concurrent.CountDownLatch;


public class ConcurrentTestTools {

	
	private int threadNums;
	
	CountDownLatch startDownLatch;
	
	CountDownLatch finshedDownLatch;
	
	CountDownLatch onceDownLatch;  //
	
	private Runnable task;
	
	public ConcurrentTestTools(int threadNums,Runnable task) {
		this.threadNums = threadNums;
		this.task = task;
		initialize();
	}
	
	public boolean initialize() {
		startDownLatch = new CountDownLatch(threadNums);
		finshedDownLatch = new CountDownLatch(threadNums);
		onceDownLatch = new CountDownLatch(1);
		
		for (int i = 0; i < threadNums; i++) {
			
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					startDownLatch.countDown();  //控制线程初始化
					try {
						System.out.println(Thread.currentThread().getName() + " init finshed.");
						startDownLatch.await();
						onceDownLatch.await();
						task.run();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName() + " execute finshed.");
					finshedDownLatch.countDown();
				}
			},"ConcurrentTestTools-thread-"+i).start();
		}
		
		return true;
	}
	
	public boolean start() {
		onceDownLatch.countDown();
		try {
			finshedDownLatch.await();  // 等待子线程运行结束
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
}
