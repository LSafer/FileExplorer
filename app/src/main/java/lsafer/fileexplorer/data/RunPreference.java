package lsafer.fileexplorer.data;

import java.io.File;
import java.io.IOException;

import cufy.beans.AbstractBean;
import cufy.loadable.FileLoadable;
import cufy.loadable.FormatLoadable;
import cufy.text.Format;
import cufy.text.JSON;
import cufy.text.ParseException;
import lsafer.fileexplorer.FileExplorer;
import lsafer.fileexplorer.util.Util;

/**
 * @author LSaferSE
 * @version 1 alpha (06-Apr-20)
 * @since 06-Apr-20
 */
@SuppressWarnings("JavaDoc")
public class RunPreference extends AbstractBean implements FileLoadable, FormatLoadable {
	final public static RunPreference global;

	@Property
	public File[] default_pages = new File[0];

	private Format format;
	private File file;

	static {
		global = new RunPreference(new File(FileExplorer.getApplication().getDir("settings", 0), "run.json"), JSON.global);

		try {
			global.load();
		} catch (IOException | ParseException e) {
			Util.log(e);
		}
	}

	public RunPreference(File file, Format format) {
		this.file = file;
		this.format = format;
	}

	@Override
	public Format getFormat() {
		return this.format;
	}

	@Override
	public File getFile() {
		return this.file;
	}
}
