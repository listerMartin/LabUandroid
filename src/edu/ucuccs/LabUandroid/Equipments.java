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

public class Equipments extends Fragment {
	
	DatabaseHandler db;

	PCtask t = new PCtask();
	SimpleAdapter adapter1;

	ListView listahanNgEquipment;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_equipments, container, false);
        db = new DatabaseHandler(getActivity());
        listahanNgEquipment = (ListView) rootView.findViewById(R.id.lstEquipments);
        return rootView;
    }
	public void populateList2() {
		List<PCtask> task = db.getPC();
		List<Map<String, String>> data = new ArrayList<Map<String, String>>();
		for (PCtask ta : task) {
			Map<String, String> datum = new HashMap<String, String>(5);
			
			datum.put("labname", ta.getLabname());
			datum.put("pcname", ta.getPCname());
			datum.put("equipment", ta.getEquipment());
			datum.put("comment", ta.getcomment());
			data.add(datum);
		}
		adapter1 = new SimpleAdapter(getActivity(), data, R.layout.list_equipment,
				new String[] { "labname", "pcname", "equipment",
						"comment" }, new int[] { R.id.labinschedule,
						R.id.textView2, R.id.codeInList,
						R.id.textView4});
		listahanNgEquipment.setAdapter(adapter1);
	}

	@Override
	public void onStart() {
		super.onStart();
		populateList2();
	}
}
