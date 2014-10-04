package edu.ucuccs.LabUandroid;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import edu.ucucss.LabUandroid.R;

public class AddRooms extends Fragment {

	EditText txtLab, txtPC;

	Button btnSaveRooms;

	DatabaseHandler db;

	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_add_rooms,
				container, false);
		db = new DatabaseHandler(getActivity());
		txtLab = (EditText) rootView.findViewById(R.id.txtLab);
		txtPC = (EditText) rootView.findViewById(R.id.txtNoPc);
		btnSaveRooms = (Button) rootView.findViewById(R.id.btnSaveRoom);

		btnSaveRooms.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				db.insertTask(new RoomsTask(txtLab.getText().toString(), txtPC
						.getText().toString()));

				Intent i = new Intent(getActivity(), MainActivity.class);
				Bundle bundle = new Bundle();
				bundle.putInt("position", 0);
				i.putExtras(bundle);
				startActivity(i);
			}

		});

		return rootView;

	}

}
