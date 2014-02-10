
package com.alterego.advancedandroidlogger.implementations;

import com.alterego.advancedandroidlogger.interfaces.IAndroidLogger;

import android.os.Debug;

/**
 * This is the wrapper around the {@link IAndroidLogger} interface - you can pass it {@link AndroidLogger} or {@link DetailedAndroidLogger}. 
 * It adds memory info in configurable units. It is in the following format:
 * Current memory stats RAM used = (totalMemory - freeMemory)/maxMemory; HEAP used = heapAllocatedMemory FOR ... (regular logging text)

 * 	totalMemory = Runtime.getRuntime().totalMemory()
 	freeMemory = Runtime.getRuntime().freeMemory()			
	maxMemory = Runtime.getRuntime().maxMemory()
	heapAllocatedMemory = Debug.getNativeHeapAllocatedSize()
 */
public class MemoryUsageAndroidLogger implements IAndroidLogger {

	public static enum MemoryUnit {
		Bytes, Kilobytes, Megabytes
	}
	private IAndroidLogger mLogger;
	private MemoryUnit mUnit;

	
	/**
	 * Initializes the MemoryUsageLogger with the {@link IAndroidLogger} base and a {@link MemoryUnit} which takes following values:
	 *
	 * @param logger {@link IAndroidLogger} reference
	 * @param unit {@link MemoryUnit} takes following values = Bytes, Kilobytes, Megabytes
	 */
	public MemoryUsageAndroidLogger(IAndroidLogger logger, MemoryUnit unit) {
		mLogger = logger;
		mUnit = unit;
	}

	private int getUnitDivider (MemoryUnit unit) {
		int divider = 1;

		try {
			if (unit.equals(MemoryUnit.Bytes))
				divider = 1;
			else if (unit.equals(MemoryUnit.Kilobytes))
				divider = 1024;
			else if (unit.equals(MemoryUnit.Megabytes))
				divider = 1048576;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return divider;
	}

	private String getUnitString (MemoryUnit unit) {
		String unit_string = "Bytes";

		try {
			if (unit.equals(MemoryUnit.Bytes))
				unit_string = "Bytes";
			else if (unit.equals(MemoryUnit.Kilobytes))
				unit_string = "KB";
			else if (unit.equals(MemoryUnit.Megabytes))
				unit_string = "MB";

		} catch (Exception e) {
			e.printStackTrace();
		}

		return unit_string;
	}

	private String getDetails(String content) {
		String memoryString = "";

		try {
			int totalMemory = (int) (Runtime.getRuntime().totalMemory() / getUnitDivider(mUnit));
			int freeMemory = (int) (Runtime.getRuntime().freeMemory() / getUnitDivider(mUnit));
			int heapAllocatedMemory = (int) (Debug.getNativeHeapAllocatedSize() / getUnitDivider(mUnit));
			int maxAvailableMemory = (int) (Runtime.getRuntime().maxMemory() / getUnitDivider(mUnit));

			memoryString = "Current memory stats RAM used = " + (totalMemory - freeMemory) + getUnitString (mUnit)+ "/" + 
					maxAvailableMemory  + getUnitString (mUnit)+ "; HEAP used = " + heapAllocatedMemory + getUnitString (mUnit) 
					+ " FOR " + content;
		} catch (Exception e) {
			memoryString = "Can't determine memory usage FOR " + content;
		}

		return memoryString;
	}

	@Override
	public IAndroidLogger getLogger(Object instance) {
		return this;
	}

	@Override
	public void verbose(String tag, String content) {
		mLogger.verbose(tag, getDetails(content));
	}

	@Override
	public void verbose(String content) {
		mLogger.verbose(getDetails(content));
	}

	@Override
	public void debug(String tag, String content) {
		mLogger.debug(tag, getDetails(content));
	}

	@Override
	public void debug(String content) {
		mLogger.debug(getDetails(content));
	}

	@Override
	public void info(String tag, String content) {
		mLogger.info(tag, getDetails(content));
	}

	@Override
	public void info(String content) {
		mLogger.info(getDetails(content));
	}

	@Override
	public void warning(String tag, String content) {
		mLogger.warning(tag, getDetails(content));
	}

	@Override
	public void warning(String content) {
		mLogger.warning(getDetails(content));
	}

	@Override
	public void error(String tag, String content) {
		mLogger.error(tag, getDetails(content));
	}

	@Override
	public void error(String content) {
		mLogger.error(getDetails(content));
	}

	@Override
	public void fail(String tag, String content) {
		mLogger.fail(tag, getDetails(content));
	}

	@Override
	public void fail(String content) {
		mLogger.fail(getDetails(content));
	}

	@Override
	public void setLoggingLevel(LoggingLevel level) {
		mLogger.setLoggingLevel(level);
	}

	@Override
	public String getLoggingTag(Object instance) {
		return mLogger.getLoggingTag(instance);
	}

	@Override
	public void setLoggingLevel(int level) {
		mLogger.setLoggingLevel(level);

	}

	@Override
	public int getLoggingLevel() {
		return mLogger.getLoggingLevel();
	}



}
