package com.test.concurrent.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程执行结果返回测试
 * @author tom
 *
 */
public class FutureTest {
	
	/**
	 * 测试多个线程
	 */
	public void multThreadTest2(ThreadPoolExecutor pool){
		LinkedBlockingQueue<String> paramQueue = new LinkedBlockingQueue<String>(10);
		try {
			//阻塞队列总放入10个姓名，共多个线程使用
			for (int i = 0; i < 10; i++) {
				paramQueue.put("brighttang"+i);
			}
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		ConsumerCallable cc1 = new ConsumerCallable(paramQueue,5);
		ConsumerCallable cc2 = new ConsumerCallable(paramQueue,10);
		ConsumerCallable cc3 = new ConsumerCallable(paramQueue, 15);
		System.out.println("回调前代码段");
		Future<ThreadResponseVo> future1 = pool.submit(cc1);
		Future<ThreadResponseVo> future2 = pool.submit(cc2);
		Future<ThreadResponseVo> future3 = pool.submit(cc3);
		ThreadResponseVo trv1 = new ThreadResponseVo();
		ThreadResponseVo trv2 = new ThreadResponseVo();
		ThreadResponseVo trv3 = new ThreadResponseVo();
		try {
			trv1 = future1.get(2, TimeUnit.SECONDS);//此处阻塞，Waits if necessary for the computation to complete, and then retrieves its result.
			trv2 = future2.get();
			trv3 = future3.get();
			
			/**
			 * 当所有线程返回后，关闭线程池执行器
			 */
			System.out.println("线程池所有线程执行完毕，关闭线程池");
			pool.shutdown();
		} catch (Exception e) {
//			e.printStackTrace();
		}
		/**
		 * 实现callable接口的线程，子线程执行完成，可以直接返回线程中的对象,如下，显示子线程返回内容：
		 */
		System.out.println(trv1.getName()+"|"+trv1.getSex()+"|"+trv1.getAge());
		System.out.println(trv2.getName()+"|"+trv2.getSex()+"|"+trv2.getAge());
		System.out.println(trv3.getName()+"|"+trv3.getSex()+"|"+trv3.getAge());
		System.out.println("回调后代码段");
		
	}
	
	/**
	 * 测试多个线程
	 */
	public void multThreadTest(ThreadPoolExecutor pool){
		LinkedBlockingQueue<String> paramQueue = new LinkedBlockingQueue<String>(10);
		try {
			paramQueue.put("brighttang");
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		ThreadResponseVo trvParam = new ThreadResponseVo();
		RunnableThread cc = new RunnableThread(trvParam,5);
		RunnableThread cc2 = new RunnableThread(trvParam,10);
		
		System.out.println("回调前代码段");
		Future<?> future = pool.submit(cc);
		Future<?> future2 = pool.submit(cc2);
		ThreadResponseVo trv = new ThreadResponseVo();
		try {
			System.out.println("future.get():"+future.get());
			System.out.println("future2.get():"+future2.get());
			/*if(future.get() != null){
				trv = future.get();//此处阻塞，Waits if necessary for the computation to complete, and then retrieves its result.
				System.out.println("线程处理完成，trv:"+trv);
//				pool.shutdown();
			}*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		/**
		 * 实现callable接口的线程，子线程执行完成，可以直接返回线程中的对象,如下，显示子线程返回内容：
		 */
		System.out.println(trv.getName()+"|"+trv.getSex()+"|"+trv.getAge());
		System.out.println("回调后代码段");
		
	}
	/**
	 * runable
	 */
	public void runableTest(ThreadPoolExecutor pool){
		ThreadResponseVo trv = new ThreadResponseVo();
		RunnableThread rt = new RunnableThread(trv,5);
		System.out.println("回调前代码段");
		Future<?> f = pool.submit(rt);
		try {
			f.get(2000, TimeUnit.MILLISECONDS);
			System.out.println("线程返回");//此处阻塞，Waits if necessary for the computation to complete, and then retrieves its result.
		} catch (Exception e) {
//			e.printStackTrace();
			System.out.println("************获取数据超时");
			pool.shutdown();
		}
		/**
		 * 实现runnable接口的线程，执行完成后，返回的是null,要想获取线程执行后的数据，可以通过传递引用对象的方式，本例传入ThreadResponseVo对象，
		 * 在子线程执行完后，修改ThreadResponseVo对象值，在主线程中获取对象值，如下：
		 */
		System.out.println(trv.getName()+"|"+trv.getSex()+"|"+trv.getAge());
		System.out.println("回调后代码段");
	}

	/**
	 * callable
	 * @param args
	 */
	public void callableTest(ThreadPoolExecutor pool){
		LinkedBlockingQueue<String> paramQueue = new LinkedBlockingQueue<String>();
		try {
			paramQueue.put("brighttang");
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		ConsumerCallable cc = new ConsumerCallable(paramQueue,5);
		System.out.println("回调前代码段");
		Future<ThreadResponseVo> future = pool.submit(cc);
		ThreadResponseVo trv = new ThreadResponseVo();
		try {
			if(future.get() != null){
				trv = future.get();//此处阻塞，Waits if necessary for the computation to complete, and then retrieves its result.
				System.out.println("线程处理完成，trv:"+trv);
//				pool.shutdown();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		/**
		 * 实现callable接口的线程，子线程执行完成，可以直接返回线程中的对象,如下，显示子线程返回内容：
		 */
		System.out.println(trv.getName()+"|"+trv.getSex()+"|"+trv.getAge());
		System.out.println("回调后代码段");
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FutureTest ft = new FutureTest();
		int corePoolSize = 2;
		int maximumPoolSize = 10; 
		int keepAliveTime=2; 
		BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>(20);
		ThreadPoolExecutor pool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.MINUTES, workQueue);
		
//		ft.callableTest(pool);
		ft.runableTest(pool);
//		ft.multThreadTest(pool);
//		ft.multThreadTest2(pool);
		
	}

}
