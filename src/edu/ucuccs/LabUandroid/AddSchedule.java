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
import android.widget.Toast;
import edu.ucucss.LabUandroid.R;

public class AddSchedule extends Fragment {
	Button saveSched;
	EditText txtLabName, txtcode, txtDescription, txtSubject, txtDay, txtFrom,
			txtTo, txtInstructor;
	DatabaseHandler db;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.activity_add_schedule,
				container, false);
		db = new DatabaseHandler(getActivity());
		txtLabName = (EditText) rootView
				.findViewById(R.id.txtLabNameInSchedule);
		txtcode = (EditText) rootView.findViewById(R.id.txtCode);
		txtDescription = (EditText) rootView.findViewById(R.id.txtDescription);
		txtSubject = (EditText) rootView.findViewById(R.id.txtSubject);
		txtDay = (EditText) rootView.findViewById(R.id.txtDay);
		txtFrom = (EditText) rootView.findViewById(R.id.txtFrom);
		txtTo = (EditText) rootView.findViewById(R.id.txtTo);
		txtInstructor = (EditText) rootView.findViewById(R.id.txtInstructor);

		saveSched = (Button) rootView.findViewById(R.id.btnSavesched);

		saveSched.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				db.insertSched(new SchedulesTask(txtLabName.getText()
						.toString(), txtcode.getText().toString(),
						txtDescription.getText().toString(), txtSubject
								.getText().toString(), txtDay.getText()
								.toString(), txtFrom.getText().toString(),
						txtTo.getText().toString(), txtInstructor.getText()
								.toString()));
				Toast.makeText(getActivity(), "Successfuly Save",
						Toast.LENGTH_SHORT).show();
				
				txtLabName.setText("");
				txtcode.setText("");
				txtDescription.setText("");
				txtSubject.setText("");
				txtDay.setText("");
				txtFrom.setText("");
				txtTo.setText("");
				txtInstructor.setText("");

				Intent i = new Intent(getActivity(), MainActivity.class);
				Bundle bundle = new Bundle();
				bundle.putInt("position", 3);
				i.putExtras(bundle);
				startActivity(i);
			}

		});

		return rootView;
	}
}
