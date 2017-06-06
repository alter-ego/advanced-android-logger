package solutions.alterego.exampleloggerapplication;

import com.alterego.advancedandroidlogger.implementations.DetailedAndroidLogger;
import com.alterego.advancedandroidlogger.interfaces.IAndroidLogger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IAndroidLogger logger = DetailedAndroidLogger.DetailedAndroidLoggerBuilder.get()
                .withTag("example")
                .withLoggingLevel(IAndroidLogger.LoggingLevel.VERBOSE)
                .withUsingStandardOutput(false)
                .withStacktraceLevel(-1)
                .build();


        logger.debug("test!");
    }
}
