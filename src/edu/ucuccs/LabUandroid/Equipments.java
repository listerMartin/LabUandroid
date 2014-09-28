package edu.ucuccs.LabUandroid;

import edu.ucucss.LabUandroid.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Equipments extends Fragment {
	
	public Equipments(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_equipments, container, false);
         
        return rootView;
    }
}
