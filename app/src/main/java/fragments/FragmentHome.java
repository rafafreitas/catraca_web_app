package fragments;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rafael.catraca_web_app.NewVisitsActivity;
import com.example.rafael.catraca_web_app.R;

/**
 * Created by rafael on 29/11/17.
 */

public class FragmentHome extends Fragment {

    public FragmentHome() {
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        CardView novaVisita = (CardView) view.findViewById(R.id.cv_gerar_visita);
        novaVisita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),NewVisitsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

        CardView visitasAtivas = (CardView) view.findViewById(R.id.cv_visitas_ativas);
        visitasAtivas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do what you want on click event
            }
        });

//        ((TextView) view.findViewById(R.id.fragmentText)).setText("One");

        return view;
    }

    
}
