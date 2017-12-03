package fragments;


import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.rafael.catraca_web_app.R;

import basic.Usuario;
import basic.Veiculos;
import util.Internet;

/**
 * Created by rafael on 29/11/17.
 */

public class FragmentCars extends Fragment {

    private TextWatcher plateMask;

    private Vibrator vib;
    Animation animShake;

    private EditText inputSearchPlate, inputPlate, inputModel;
    private ImageView carImageView;
    private TextInputLayout layoutSearchPlate, layoutPlate, layoutModel;
    private Button btnSaveCar;

    private Internet internet;
    private Veiculos veiculo;

    public FragmentCars() {
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
        View view = inflater.inflate(R.layout.fragment_cars, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){

        initInputs(new Veiculos());
        mask();
    }

    private void initInputs(Veiculos veiculo){

    }

    private void mask(){

    }

}
