package edu.ucuccs.LabUandroid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import edu.ucucss.LabUandroid.R;

public class ManageFragment extends Fragment {

	DatabaseHandler db;

	PCtask t = new PCtask();
	SimpleAdapter adapter1;

	ListView listahanngpc;
	String equipment, comment;
	int idOfpc;
	String value1;
	String value2;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_manage, container,
				false);
		db = new DatabaseHandler(getActivity());
		listahanngpc = (ListView) rootView.findViewById(R.id.lstPC);
		listahanngpc
				.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

					@Override
					public boolean onItemLongClick(AdapterView<?> parent,
							View view, int position, long id) {
						idOfpc = Integer.parseInt(((TextView) view
								.findViewById(R.id.txtidpc)).getText()
								.toString());
						equipment = (((TextView) view
								.findViewById(R.id.txtEquipment)).getText()
								.toString());
						comment = (((TextView) view
								.findViewById(R.id.txtComment)).getText()
								.toString());
						mgaPagpipipilian();
						return false;
					}
				});
		return rootView;
	}

	private void mgaPagpipipilian() {
		AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());
		adb.setCancelable(true);
		adb.setTitle("Please select option");
		adb.setItems(R.array.AddingComment,
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int item) {
						switch (item) {
						case 0:
							editItem(equipment, comment);
							break;
						case 1:
							deleteItem();
							break;
						}
					}
				});
		adb.show();
	}
	private void deleteItem() {
		// tawagin nating ang databaes method na deleteTask para madelete ang
		// item gamit ang position_id
		db.deletePC(new PCtask(idOfpc));
		// irefresh ang listview
		populateList2();
	}
	// magdagdag ng comment at equipment
	private void editItem(String equipment, String comment) {
		AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());

		adb.setTitle("Add Comment and Equipment");
		// gagawa ng dalawang editText para kay comment at equipment
		final EditText input = new EditText(getActivity());
		input.setHint("Equipment");
		final EditText input2 = new EditText(getActivity());
		input2.setHint("Comment");
		// ito yung para sa layout

		LinearLayout layout = new LinearLayout(getActivity());
		layout.setOrientation(LinearLayout.VERTICAL);

		layout.addView(input);
		layout.addView(input2);
		adb.setView(layout);
		input.setText(equipment);
		input2.setText(comment);
		adb.setPositiveButton("Add", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				value1 = input.getText().toString();
				value2 = input2.getText().toString();

				db.updatePC(new PCtask(idOfpc, value2,value1, value2, value1 ));
				
				
				populateList2();
			}
		});
		adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				dialog.cancel();
			}
		});
		adb.show();
	}

	public void populateList2() {
		List<PCtask> task = db.getPC();
		List<Map<String, String>> data = new ArrayList<Map<String, String>>();
		for (PCtask ta : task) {
			Map<String, String> datum = new HashMap<String, String>(5);
			datum.put("id", ta.getId() + "");
			datum.put("labname", ta.getLabname());
			datum.put("pcname", ta.getPCname());
			datum.put("equipment", ta.getEquipment());
			datum.put("comment", ta.getcomment());
			data.add(datum);
		}
		adapter1 = new SimpleAdapter(getActivity(), data, R.layout.list_of_pc,
				new String[] { "id", "labname", "pcname", "equipment",
						"comment" }, new int[] { R.id.txtidpc,
						R.id.txtLabNameinPCList, R.id.txtPCNameinPCList,
						R.id.txtEquipment, R.id.txtComment });
		listahanngpc.setAdapter(adapter1);
	}

	@Override
	public void onStart() {
		super.onStart();
		populateList2();
	}
}
