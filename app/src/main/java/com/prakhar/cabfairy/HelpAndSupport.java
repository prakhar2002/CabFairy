package com.prakhar.cabfairy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class HelpAndSupport  extends Fragment {



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        getActivity().getFragmentManager().popBackStack();
        View root = inflater.inflate(R.layout.helpandsupport_fragment, container, false);




        return root;
    }




}
