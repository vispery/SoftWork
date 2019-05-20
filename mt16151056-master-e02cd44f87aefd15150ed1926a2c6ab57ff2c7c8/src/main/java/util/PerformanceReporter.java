package util;
/**
 * @author ztang
 * generated at : Jul 1, 2016 - 9:02:40 AM
 */
public class PerformanceReporter {

	public static void printRuntimeStatistics(long start){	System.out.println( buildRuntimeStatistics(start, false, null) ); }
	public static void printRuntimeStatistics(long start, boolean detail){	System.out.println( buildRuntimeStatistics(start, detail, null) ); 	}	
	public static void printRuntimeStatistics(long start, String title){	System.out.println( buildRuntimeStatistics(start, false, title) ); 		}
	public static void printRuntimeStatistics(long start, boolean detail, String title){	
		System.out.println( buildRuntimeStatistics(start, detail, title) ); 	
	}
	
	public static String buildRuntimeStatistics(long start){	return buildRuntimeStatistics(start, false, null);	}			
	public static String buildRuntimeStatistics(long start, boolean detail){	return buildRuntimeStatistics(start, detail, null);	}
	public static String buildRuntimeStatistics(long start, String title){	return buildRuntimeStatistics(start, false, title);	}	
	public static String buildRuntimeStatistics(long start, boolean detail, String title){				
		int unit = 1024;
		String newline = System.getProperty("line.separator", "\n");
		String postFix = " kb" + newline;
		
		String titleLocal;
		if( title==null || title.length()<1){
			titleLocal = "Process/method";
		} else {
			titleLocal = title;
		}		 
		
		StringBuffer buf = new StringBuffer();
		buf.append(titleLocal).append(" takes ");
		buf.append(System.currentTimeMillis() - start).append(" milliseconds in total").append(newline);

		Runtime runtime = Runtime.getRuntime();
		
		long freeRAM = runtime.freeMemory()/unit;
		long totalRAM = runtime.totalMemory()/unit;
		runtime.gc();	
		long freeRAM2 = runtime.freeMemory()/unit;
		long totalRAM2 = runtime.totalMemory()/unit;	
		
		buf.append(titleLocal).append(" consumes RAM ").append(freeRAM2-freeRAM).append(postFix);
		buf.append(titleLocal).append(" leads total RAM change ").append (Math.abs(totalRAM-totalRAM2) ).append(postFix);
		
		if( detail ){
			buf.append("free  memory before gc ").append( freeRAM ).append(postFix);
			buf.append("total memory before gc ").append( totalRAM ).append(postFix);	
			buf.append("free  memory after  gc ").append( freeRAM2 ).append(postFix);
			buf.append("total memory after  gc ").append( totalRAM2 ).append(postFix);
		}
		return buf.toString();
	}
}
