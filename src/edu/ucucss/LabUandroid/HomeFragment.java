package edu.ucucss.LabUandroid;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

public class HomeFragment extends Fragment {
	Spinner spinLabComment;
	ListView lstComment;
	EditText txtCommentPcName, txtCommentDes;
	Button btnAddComment, btnSaveComment;

	public HomeFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_home, container,
				false);
		spinLabComment = (Spinner) rootView.findViewById(R.id.spinCommentLab);
		lstComment = (ListView) rootView.findViewById(R.id.lstComments);
		txtCommentPcName = (EditText) rootView
				.findViewById(R.id.txtCommentPcname);
		txtCommentDes = (EditText) rootView.findViewById(R.id.txtCommentDes);
		btnAddComment = (Button) rootView.findViewById(R.id.btnAddComment);
		btnSaveComment = (Button) rootView.findViewById(R.id.btnSaveComment);

		btnAddComment.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				lstComment.setVisibility(View.GONE);
				spinLabComment.setVisibility(View.VISIBLE);
				txtCommentPcName.setVisibility(View.VISIBLE);
				txtCommentDes.setVisibility(View.VISIBLE);
				btnSaveComment.setVisibility(View.VISIBLE);
				btnAddComment.setVisibility(View.GONE);
			}

		});
		return rootView;
	}

}
