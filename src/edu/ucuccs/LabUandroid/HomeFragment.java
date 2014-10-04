package edu.ucuccs.LabUandroid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import edu.ucucss.LabUandroid.R;

public class HomeFragment extends Fragment {
	ListView lstRoom;
	DatabaseHandler db;

	RoomsTask t = new RoomsTask();
	SimpleAdapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_home, container,
				false);
		db = new DatabaseHandler(getActivity());
		lstRoom = (ListView) rootView.findViewById(R.id.lstRooms);
		populateList();

		return rootView;
	}

	public void populateList(){
        List<RoomsTask> task = db.getAllTask(); 
        List<Map<String, String>> data = new ArrayList<Map<String, String>>();
        for (RoomsTask ta : task) {
        	Map<String, String> datum = new HashMap<String, String>(2);
        	datum.put("lab",ta.getLab()+"");
        	datum.put("pc",ta.getPC());
        	data.add(datum);
        }
        adapter = new SimpleAdapter(getActivity(), data, R.layout.list_view_row, new String[] {"lab", "pc"},new int[] {R.id.txtLab ,R.id.txtPC});
        lstRoom.setAdapter(adapter);
    }

}
