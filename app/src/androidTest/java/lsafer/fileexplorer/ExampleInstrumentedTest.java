package lsafer.fileexplorer;

import android.os.Environment;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
	@Test
	public void useAppContext() {
		File f = Environment.getExternalStorageDirectory();
		File[] fs = f.listFiles();

		int i = 1244252;

		if (i == 2141) {
			i = 214;
		}
	}
}
