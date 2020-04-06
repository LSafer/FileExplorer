package lsafer.fileexplorer.activity;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;

import lsafer.fileexplorer.R;
import lsafer.fileexplorer.data.RunPreference;

@SuppressWarnings("JavaDoc")
public class FolderActivity extends AppCompatActivity {
	private File folder;
	private RecyclerViewAdapter recyclerViewAdapter;
	private RecyclerView recyclerView;
	private RecyclerView.LayoutManager layoutManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_folder);
		this.requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 4635747);

		this.recyclerView = this.findViewById(R.id.files);
		this.layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
		this.recyclerViewAdapter = new RecyclerViewAdapter();
		this.recyclerView.setAdapter(this.recyclerViewAdapter);
		this.recyclerView.setLayoutManager(this.layoutManager);

		File[] defaultPages = RunPreference.global.default_pages;

		this.setFolder(defaultPages.length == 0 ? Environment.getExternalStorageDirectory() : defaultPages[0]);
	}

	@Override
	protected void onResume() {
		super.onResume();
		this.setFolder(this.folder);
	}

	@Override
	public void onBackPressed() {
		File parent = this.folder.getParentFile();
		this.setFolder(parent == null ? this.folder : parent);
	}

	private void setFolder(File folder) {
		this.folder = folder;
		this.recyclerViewAdapter.setFiles(this.folder.listFiles());
	}

	private class RecyclerViewAdapter extends RecyclerView.Adapter {
		private File[] files = new File[0];

		@Override
		public int getItemCount() {
			return this.files == null ? 0 : this.files.length;
		}

		@Override
		public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
			File file = this.files[position];

			((FileViewHolder) holder).name.setText(file.getName());
			((FileViewHolder) holder).file = file;
		}

		@Override
		public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			return new FileViewHolder(parent);
		}

		private void setFiles(File[] files) {
			this.files = files;
			this.notifyDataSetChanged();
		}
	}

	private class FileViewHolder extends RecyclerView.ViewHolder {
		private TextView name;
		private File file;

		private FileViewHolder(ViewGroup parent) {
			super(LayoutInflater.from(FolderActivity.this).inflate(R.layout.filelist_file, parent, false));
			this.name = this.itemView.findViewById(R.id.name);
			this.name.setOnClickListener(l -> {
				if (file.isDirectory()) {
					FolderActivity.this.setFolder(this.file);
				} else {
					Toast.makeText(FolderActivity.this, file.getName(), Toast.LENGTH_LONG).show();
				}
			});
		}
	}
}
