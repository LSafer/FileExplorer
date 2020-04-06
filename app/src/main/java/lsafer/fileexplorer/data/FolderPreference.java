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

@SuppressWarnings("JavaDoc")
final public class FolderPreference extends AbstractBean implements FileLoadable, FormatLoadable {
	final public static FolderPreference global;

	@Property
	public boolean show_hidden_items = false;

	private Format format;
	private File file;

	static {
		global = new FolderPreference(new File(FileExplorer.getApplication().getDir("settings", 0), "folder.json"), JSON.global);

		try {
			global.load();
		} catch (IOException | ParseException e) {
			Util.log(e);
		}
	}

	public FolderPreference(File file, Format format) {
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
