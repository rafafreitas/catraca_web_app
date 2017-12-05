package fragments;


import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rafael.catraca_web_app.R;

import basic.Auth;
import basic.Usuario;
import basic.Visitantes;
import util.Internet;
import util.Mask;
import util.Util;

/**
 * Created by rafael on 29/11/17.
 */

public class FragmentVisitors  extends Fragment {
    private TextWatcher dateMask;
    private TextWatcher cpfMask;

    private Vibrator vib;
    Animation animShake;

    private EditText inputCpf, inputName, inputRG, inputDate;
    private TextInputLayout inputLayoutCpf, inputLayoutName, inputLayoutRG, inputLayoutDate;
    private Button btnUpdate;

    private Internet internet;
    private Auth auth; //SingleUser

    //Para Imagens
    private ImageView ivPhotoFace, ivPhotoDoc;
    private Bitmap bitmap;
    private String sPhotoFace, sPhotoDoc;

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

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){

        auth = Auth.getInstance();
        //internet = new Internet(this);
        Util.setCtxAtual(getActivity());
        //initInputs();
        mask();
    }



    private void initInputs(Visitantes visitantes){
        //Pegar Valores para realizar a validação do form
        inputLayoutCpf = (TextInputLayout)getActivity().findViewById(R.id.input_layout_pesquisa_cpf);
        inputLayoutName = (TextInputLayout)getActivity().findViewById(R.id.input_layout_nome);
        inputLayoutRG = (TextInputLayout)getActivity().findViewById(R.id.input_layout_rg);
        inputLayoutDate = (TextInputLayout)getActivity().findViewById(R.id.input_layout_date);

        inputCpf = (EditText)getActivity().findViewById(R.id.input_pesquisa_cpf);
        inputName = (EditText)getActivity().findViewById(R.id.input_nome);
        inputRG = (EditText)getActivity().findViewById(R.id.input_rg);
        inputDate = (EditText)getActivity().findViewById(R.id.input_date);


        btnUpdate = (Button)getActivity().findViewById(R.id.btn_save_visit);


        animShake = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.shake);
        vib = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
    }

    private void mask(){
        //Chamando Máscara do Aniversário
        final EditText dateNiver = (EditText) getActivity().findViewById(R.id.input_date);
        dateMask = Mask.insert("##/##/####", dateNiver);
        dateNiver.addTextChangedListener(dateMask);

        //Chamando Máscara do CPF
        final EditText cpfUser = (EditText) getActivity().findViewById(R.id.input_pesquisa_cpf);
        cpfMask = Mask.insert("###.###.###-##", cpfUser);
        cpfUser.addTextChangedListener(cpfMask);

    }



}
