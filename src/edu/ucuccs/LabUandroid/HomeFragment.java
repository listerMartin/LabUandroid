package edu.ucuccs.LabUandroid;

import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import edu.ucucss.LabUandroid.R;

public class HomeFragment extends Fragment {
	Spinner spinLabComment;
	ListView lstRoom;
	EditText txtCommentPcName, txtCommentDes;
	Button btnAddComment, btnSaveComment;
	DatabaseHandler db;
	

	public HomeFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
		db = new DatabaseHandler(getActivity());
		spinLabComment = (Spinner) rootView.findViewById(R.id.spinCommentLab);
		lstRoom = (ListView) rootView.findViewById(R.id.lstRooms);
		txtCommentPcName = (EditText) rootView
				.findViewById(R.id.txtCommentPcname);
		txtCommentDes = (EditText) rootView.findViewById(R.id.txtCommentDes);
		btnSaveComment = (Button) rootView.findViewById(R.id.btnSaveComment);
		populateList();
        if (container == null) {
            return null;
        }
        return (LinearLayout) inflater.inflate(R.layout.fragment_home, container, false);
	}
	public void populateList() {
		List<RoomsTask> list = db.getAllTask();
		ArrayList<String> listahan = new ArrayList<String>();

		for (RoomsTask l : list) {
			Toast.makeText(getActivity(), l.getLab().toString(),
					Toast.LENGTH_LONG).show();

			ArrayAdapter<String> adapter = new ArrayAdapter<String>(
					getActivity(), android.R.layout.simple_list_item_1,
					listahan);
			lstRoom.setAdapter(adapter);
		}

	}
	
}
