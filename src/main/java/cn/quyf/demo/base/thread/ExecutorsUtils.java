package cn.quyf.demo.base.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class ExecutorsUtils {

	private ExecutorService getThreadPool(int maxThreads, int coreThreads, String nameHint,
		      BlockingQueue<Runnable> passedWorkQueue) {
		    // shared HTable thread executor not yet initialized
		    if (maxThreads == 0) {
		      maxThreads = Runtime.getRuntime().availableProcessors() * 8;
		    }
		    if (coreThreads == 0) {
		      coreThreads = Runtime.getRuntime().availableProcessors() * 8;
		    }
		    long keepAliveTime =  60;
		    BlockingQueue<Runnable> workQueue = passedWorkQueue;
		    if (workQueue == null) {
		      workQueue =
		        new LinkedBlockingQueue<>(maxThreads * 100);
		      coreThreads = maxThreads;
		    }
		    ThreadPoolExecutor tpe = new ThreadPoolExecutor(
		        coreThreads,
		        maxThreads,
		        keepAliveTime,
		        TimeUnit.SECONDS,
		        workQueue,
		        new ThreadFactory(){

					@Override
					public Thread newThread(Runnable r) {
						return null;
					}
		        });
		    tpe.allowCoreThreadTimeOut(true);
		    return tpe;
		  }
}
