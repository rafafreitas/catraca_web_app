package fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rafael.catraca_web_app.R;

/**
 * Created by rafael on 29/11/17.
 */

public class FragmentVisitors  extends Fragment {

    public FragmentVisitors() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_visitors, container, false);

//        ((TextView) view.findViewById(R.id.fragmentText)).setText("One");

        return view;
    }
}