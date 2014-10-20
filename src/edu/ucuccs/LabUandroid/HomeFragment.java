package edu.ucuccs.LabUandroid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import edu.ucucss.LabUandroid.R;

public class HomeFragment extends Fragment {
	ListView lstRoom;
	DatabaseHandler db;

	RoomsTask t = new RoomsTask();
	SimpleAdapter adapter;

	int position_id;
	String description;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_home, container,
				false);
		db = new DatabaseHandler(getActivity());

		lstRoom = (ListView) rootView.findViewById(R.id.lstRooms);
		lstRoom.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				position_id = Integer.parseInt(((TextView) arg1
						.findViewById(R.id.txtLabIdinList)).getText()
						.toString());
				description = (((TextView) arg1
						.findViewById(R.id.txtLabNameInList)).getText()
						.toString());

				mgaPagpipipilian();

				return false;
			}

		});
		lstRoom.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int positiontwo, long arg3) {

				Intent i = new Intent(getActivity(), MainActivity.class);
				Bundle bundle = new Bundle();
				bundle.putInt("position", 1);
				i.putExtras(bundle);
				startActivity(i);

			}

		});
		return rootView;
	}

	private void mgaPagpipipilian() {
		AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());
		adb.setCancelable(true);
		adb.setTitle("Please select option");
		adb.setItems(R.array.pagpipilian,
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int item) {
						switch (item) {
						case 0:
							editItem(description);
							break;
						case 1:
							deleteItem();
							break;
						}
					}
				});
		adb.show();
	}

	// Tatawagin naman to pag pinindot si edit. Index 0
	// Tatawagin naman to pag pinindot si edit. Index 0
	private void editItem(String msg) {
		AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());

		adb.setTitle("Edit Room");

		final EditText input = new EditText(getActivity());

		adb.setView(input);

		input.setText(msg);
		adb.setPositiveButton("Update", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {

				String value = input.getText().toString();
				
				db.updateRoom(new RoomsTask(position_id, value, value));
				
				populateList();
			}
		});
		adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				dialog.cancel();
			}
		});
		adb.show();
	}

	private void deleteItem() {
		// tawagin nating ang databaes method na deleteTask para madelete ang
		// item gamit ang position_id
		db.deleteTask(new RoomsTask(position_id));
		// irefresh ang listview
		populateList();
	}

	public void populateList() {
		List<RoomsTask> task = db.getAllTask();
		List<Map<String, String>> data = new ArrayList<Map<String, String>>();
		for (RoomsTask ta : task) {
			Map<String, String> datum = new HashMap<String, String>();
			datum.put("id", ta.getId() + "");
			datum.put("lab", ta.getLab());
			datum.put("pc", ta.getPC());
			data.add(datum);
		}
		adapter = new SimpleAdapter(getActivity(), data, R.layout.list_view,
				new String[] { "id", "lab", "pc" }, new int[] {
						R.id.txtLabIdinList, R.id.txtLabNameInList,
						R.id.txtPCInList });
		lstRoom.setAdapter(adapter);
	}

	@Override
	public void onStart() {
		super.onStart();
		populateList();
	}
}
