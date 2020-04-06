package lsafer.fileexplorer;

import android.app.Application;

import androidx.core.util.Supplier;

/**
 * The class of the application.
 *
 * @author LSaferSE
 * @version 1 alpha (06-Apr-20)
 * @since 06-Apr-20
 */
public class FileExplorer extends Application {
	/**
	 * The context supplier.
	 */
	private static Supplier<FileExplorer> _app;

	/**
	 * Get the context of the static application instance.
	 *
	 * @return the context of the application
	 */
	public static FileExplorer getApplication() {
		return _app.get();
	}

	@Override
	public void onCreate() {
		super.onCreate();

		_app = () -> this;
	}
}
