#AdvancedAndroidLogger

This library is meant to offer a wrapper around the standard Android Log.x methods in few distinct flavours. 
It gives you the possibility to have one logger that will use the same logging tag for the whole app and
also the ability to quickly change the logging level for the whole app - you can go from debug to release 
by changing just one constant.

## IAndroidLogger

**IAndroidLogger** is the interface for all the different flavours used in this library. 
It has two flavours of logging methods (with or without logging tag) and you have the logging tag and logging level
getters and setters. The logging methods are bit more explanatory than the regular Android ones (e.g. **debug()** instead 
of **Log.d()**).

## NullAndroidLogger

This is the null-version of the **IAndroidLogger**, used for initializing the logger variables in order to prevent
Null Pointer Exception if the logger is called before being instantiated. You can also use it if you want
to stop logging altogether in a certain part of the app.

## AndroidLogger

This is the standard Log.x implementation of the IAndroidLogger. It has 3 constructors:
+ **public AndroidLogger()** - instantiates the logger with the LoggingLevel.NORMAL (equates to the **Log.i**,
	advised by Google for public releases) and the default logging tag "ADVANCEDANDROIDLOGGER"
+ **public AndroidLogger(String tag)** - instantiates the logger with the **LoggingLevel.NORMAL** and a logging tag
+ **AndroidLogger(String tag, LoggingLevel level)** - instantiates the logger with desired logging level and tag

You can always change the logging level afterwards with **setLoggingLevel (LoggingLevel level)** and 
**setLoggingLevel (int level)** methods. You can override the logging tag by calling methods in the form of 
**verbose (String tag, String content)** or use the tag by calling **verbose (String content)**.

## DetailedAndroidLogger

This is the enriched Log.x implementation of that prints the class name, method and line number before in front of the
regular logging content (e.g. ReaderActivity.logPrinting() @ line 132: ReaderActivity loggerTesting 3). That way you don't have to
put the class details in the message manually.

## MemoryUsageAndroidLogger

This is the IAndroidLogger decorator that adds current memory info to the beginning of the logging content. It is implemented
as a decorator so you can use it with every IAndroidLogger implementation - just pass the reference in the constructor.
The constructor **public MemoryUsageAndroidLogger(IAndroidLogger logger, MemoryUnit unit)** also needs a **MemoryUnit** which
can be **Bytes**, **Kilobytes** = KB and **Megabytes** = MB. 

The memory info is in the following format:
"Current memory stats RAM used = (totalMemory - freeMemory)/maxAvailableMemory; HEAP used = heapAllocatedMemory FOR **regular log message**"
where:
totalMemory = Runtime.getRuntime().totalMemory()
freeMemory = Runtime.getRuntime().freeMemory()
maxAvailableMemory = Runtime.getRuntime().maxMemory()
heapAllocatedMemory = Debug.getNativeHeapAllocatedSize()

## MultiLoggerWrapper

This is the IAndroidLogger wrapper that enables you to use multiple loggers at the same time. Just instantiate it,
call **registerLogger(IAndroidLogger logger)** and **unregisterLogger(IAndroidLogger logger)** to register and unregister
already instantiated loggers, and then call regular **IAndroidLogger** methods like you would normally do. 

That way you can not only have multiple loggers (for example, regular AndroidLogger and TestFlight logger implementation) called together,
but you can also have multiple loggers set for multiple logging levels. If you want, you can set the same logging level for all of them
calling **setLoggingLevel** methods, but you cannot undo it afterwards (i.e. set different logging levels for different 
loggers).

## DEPENDENCIES

None.

## USAGE

Add this new repo to your `build.gradle` file:

```groovy
repositories {
    mavenCentral()
    maven { url "https://github.com/alter-ego/advanced-android-logger/raw/develop/releases/" }
}
```

Add a new dependency:

```groovy
compile 'com.alterego:advancedandroidlogger:1.0.6@aar'
```

Create your logger:

```java
DetailedAndroidLogger logger = new DetailedAndroidLogger("YOUR_TAG", IAndroidLogger.LoggingLevel.VERBOSE);
```


## LICENSE

This library is licensed under [Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html).
