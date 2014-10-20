package edu.ucuccs.LabUandroid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import edu.ucucss.LabUandroid.R;

public class ScheduleFragment extends Fragment {

	Button btnsched;
	DatabaseHandler db;
	SchedulesTask t = new SchedulesTask();
	SimpleAdapter adapter3;

	ListView listahanNgSched;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_schedule, container,
				false);
		db = new DatabaseHandler(getActivity());
		btnsched = (Button) rootView.findViewById(R.id.btnIntentToAddSeched);
		listahanNgSched = (ListView) rootView
				.findViewById(R.id.listOfAllSchedul);

		btnsched.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(getActivity(), MainActivity.class);
				Bundle bundle = new Bundle();
				bundle.putInt("position", 5);
				i.putExtras(bundle);
				startActivity(i);
			}

		});
		return rootView;
	}

	public void populateList3() {
		List<SchedulesTask> task = db.getSched();
		List<Map<String, String>> data = new ArrayList<Map<String, String>>();
		for (SchedulesTask ta : task) {
			Map<String, String> datum = new HashMap<String, String>(9);
			datum.put("id", ta.getId() + "");
			datum.put("labNameSched", ta.getlabNameSched());
			datum.put("code", ta.getcode());
			datum.put("description", ta.getdescription());
			datum.put("subject", ta.getsubject());
			datum.put("day", ta.getday());
			datum.put("from", ta.getfrom());
			datum.put("to", ta.getto());
			datum.put("instructor", ta.getinstructor());
			data.add(datum);
		}
		adapter3 = new SimpleAdapter(getActivity(), data,
				R.layout.list_schedule, new String[] { "id", "labNameSched",
						"code", "description", "subject", "day", "from", "to",
						"instructor" }, new int[] { R.id.txtIdSched,R.id.labinschedule,
						R.id.codeInList, R.id.tDescriptionInList,
						R.id.subjectInList, R.id.dayInList, R.id.fromInList,
						R.id.toInList, R.id.instructorInList });
		listahanNgSched.setAdapter(adapter3);
	}

	@Override
	public void onStart() {
		super.onStart();
		populateList3();
	}

}
