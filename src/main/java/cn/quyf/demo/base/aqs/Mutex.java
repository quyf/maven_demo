package cn.quyf.demo.base.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

//http://www.cnblogs.com/zhangjk1993/p/6715653.html
public class Mutex implements Lock {

	//通过继承，自定义同步器
	private static class Sync extends AbstractQueuedSynchronizer{
		private static final long serialVersionUID = 1L;

		@Override
		protected boolean tryAcquire(int arg) {//尝试获取锁
			if(compareAndSetState(0, 1)){
				setExclusiveOwnerThread(Thread.currentThread());
				return true;
			}
			return false;
		}

		@Override
		protected boolean tryRelease(int arg) {
			if( getState()==0){
				return false;
			}
			if( compareAndSetState(1, 0)){
				setExclusiveOwnerThread(null);
				return true;
			}
			return false;
		}

		@Override
		protected boolean isHeldExclusively() {
			return getState()==1;
		}
		
		Condition newCondition() {
            return new ConditionObject();
        }
	}
	
	// 将操作代理到 Sync 上
    private final Sync sync = new Sync();
    
    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }

    public boolean hasQueuedThreads() {
        return sync.hasQueuedThreads();
    }

    public boolean isLocked() {
        return sync.isHeldExclusively();
    }
    
    public static void withoutMutex() throws InterruptedException {
        System.out.println("Without mutex: ");
        int threadCount = 2;
        final Thread threads[] = new Thread[threadCount];
        for (int i = 0; i < threads.length; i++) {
            final int index = i;
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 100000; j++) {
                        if (j % 20000 == 0) {
                            System.out.println("Thread-" + index + ": j =" + j);
                            try {
								Thread.sleep(100);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
                        }
                    }
                }
            });
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }
    }

    public static void withMutex() {
        System.out.println("With mutex: ");
        final Mutex mutex = new Mutex();
        int threadCount = 2;
        final Thread threads[] = new Thread[threadCount];
        for (int i = 0; i < threads.length; i++) {
            final int index = i;
            threads[i] = new Thread(new Runnable() {

                @Override
                public void run() {

                    mutex.lock();
                    try {
                        for (int j = 0; j < 100000; j++) {
                            if (j % 20000 == 0) {
                                System.out.println("Thread-" + index + ": j =" + j);
                            }
                        }
                    } finally {
                        mutex.unlock();
                    }
                }
            });
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        withoutMutex();
        System.out.println();
        withMutex();

    }

}
