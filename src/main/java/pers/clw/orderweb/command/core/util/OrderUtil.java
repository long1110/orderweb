package pers.clw.orderweb.command.core.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;


public class OrderUtil {
	
	    private static final ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<Integer>();
	    private static final CountDownLatch latch = new CountDownLatch(1);
	    static ConcurrentMap<String, Object> map = new ConcurrentHashMap<String,Object>();
	    /**
	    * 每毫秒生成订单号数量最大值，约定取整百，整千。
	    */
	    public static final int maxPerMSECSize = 100;

	    private static void init() {
	        for (int i = 0; i < maxPerMSECSize; i++) {
	            queue.offer(i);
	        }
	        latch.countDown();
	    }

	    public static Integer poll() {
	        try {
	            if (latch.getCount() > 0) {
	                init();
	                latch.await(1, TimeUnit.SECONDS);
	            }
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        Integer i = queue.poll();
	        queue.offer(i);
	        return i;
	    }

	    public synchronized  static String get(String type) {
	        long nowLong = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
	        String number = maxPerMSECSize + poll() + "";
		     try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	        return type+nowLong + number.substring(1);
	    }
	
	    
	    public static void main(String[] Args ){
	    
	    	Runnable run = new Runnable() {
				public void run() {
			    	for(int i=0;i<100000000;i++){
			    		String test = get("NO");
			    		System.out.println(test);
			    		if(map.get(test)!=null){
			    			System.out.println("系统错误!出现在"+(i+1)+"次");
			    			System.exit(0);
			    			return;
			    		}
			    		map.put(test, test);
			    	}
				}
			};
			Thread th2  = new Thread(run);
			Thread th3  = new Thread(run);
			th2.start();
			th3.start();
	    	
	    }
	
}
