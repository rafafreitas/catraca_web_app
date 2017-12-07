package fragments;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rafael.catraca_web_app.R;
import com.example.rafael.catraca_web_app.UserDataActivity;

import org.json.JSONException;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import basic.Auth;
import basic.Usuario;
import basic.Veiculos;
import request.RequesterVeiculo;
import util.Internet;
import util.Mask;
import util.Util;

/**
 * Created by rafael on 29/11/17.
 */

public class FragmentCars extends Fragment {

    private TextWatcher plateMask;
    private TextWatcher searchPlateMask;

    private Vibrator vib;
    Animation animShake;

    private EditText inputSearchPlate, inputPlate, inputModel;
    private ImageView btnCarImageView;
    private TextInputLayout layoutSearchPlate, layoutPlate, layoutModel;
    private Button btnSaveCar;

    private Internet internet;
    private Veiculos veiculo;
    private Auth auth;

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

        animShake = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.shake);
        vib = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
        auth = Auth.getInstance();
        internet = new Internet(getActivity());
        Util.setCtxAtual(getActivity());

        veiculo = new Veiculos();
        initInputs();
        mask();
    }

    private void initInputs(){

        inputSearchPlate = (EditText)getActivity().findViewById(R.id.input_pesquisa_placa);
        inputPlate = (EditText)getActivity().findViewById(R.id.input_placa);
        inputModel = (EditText)getActivity().findViewById(R.id.input_model);

        btnCarImageView = (ImageView) getActivity().findViewById(R.id.btn_car_image);

        layoutSearchPlate = (TextInputLayout)getActivity().findViewById(R.id.input_layout_pesquisa_placa);
        layoutPlate = (TextInputLayout)getActivity().findViewById(R.id.input_layout_placa);
        layoutModel = (TextInputLayout)getActivity().findViewById(R.id.input_layout_model);

        btnSaveCar = (Button)getActivity().findViewById(R.id.btn_save_car);

        setListeners();
    }

    private void setListeners(){

        //Salvar
        btnSaveCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Validar form e depois salvar
                if(formIsValid()){
                    if(!internet.verificarConexao()){

                        new android.support.v7.app.AlertDialog.Builder(getActivity())
                                .setCancelable(false)
                                .setTitle(R.string.app_name)
                                .setMessage(R.string.info_internet)

                                // Positive button
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                }).show();
                    }else{
                        final Veiculos saveVeiculo = getFromForm();

                        Util.AtivaDialogHandler(2, "", "Saving...");
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                RequesterVeiculo rv = new RequesterVeiculo();

                                try{
                                    rv.setContext(getActivity());
                                    String result = rv.saveVeiculo(saveVeiculo);

                                    auth = Auth.getInstance();

                                    if (auth.getMessage().equals("ERROR")) {
                                        Util.AtivaDialogHandler(5, "", "");
                                        Util.AtivaDialogHandler(1, "CatracaWeb", auth.getMensagemErroApi());
                                    }else{
                                        Util.stopProgressDialog();
                                        Util.AtivaDialogHandler(1, "CatracaWeb", result );
                                        //Toast.makeText(getApplicationContext(), "Dados Atualizados.", Toast.LENGTH_SHORT).show();
                                    }
                                }catch(Exception e){
                                    Util.stopProgressDialog();
                                    e.printStackTrace();
                                }
                            }
                        }).start();
                    }
                }
            }
        });

        //Pesquisar
        inputSearchPlate.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {

                if(i == EditorInfo.IME_ACTION_SEARCH){
                    //Procurar e preecher form se existir
                    final String placaSearch = inputSearchPlate.getText().toString();
                    layoutSearchPlate.setErrorEnabled(false);

                    if(placaSearch.length() < 8){

                        inputSearchPlate.setAnimation(animShake);
                        inputSearchPlate.startAnimation(animShake);
                        vib.vibrate(120);
                        layoutSearchPlate.setErrorEnabled(true);
                        layoutSearchPlate.setError(getString(R.string.error_wrong_search_plate));
                        inputSearchPlate.setError(getString(R.string.err_msg_required));

                    }else if(!internet.verificarConexao()){

                        new android.support.v7.app.AlertDialog.Builder(getActivity())
                                .setCancelable(false)
                                .setTitle(R.string.app_name)
                                .setMessage(R.string.info_internet)

                                // Positive button
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                }).show();
                    }
                    else{
                        //Util.AtivaDialogHandler(2, "", "Searching...");

                        Callable<Veiculos> myCallable = new Callable<Veiculos>(){

                            @Override
                            public Veiculos call(){

                                RequesterVeiculo rv = new RequesterVeiculo();

                                try {
                                    rv.setContext(getActivity());
                                    veiculo = rv.checkVeiculoByPlaca(placaSearch);
                                }catch (Exception e){
                                    //Util.stopProgressDialog();
                                    e.printStackTrace();
                                }
                                return  veiculo;
                            }
                        };

                        ExecutorService executor = Executors.newFixedThreadPool(1);
                        Future<Veiculos> future = executor.submit(myCallable);

                        try {
                            Veiculos v = future.get();
                            auth = Auth.getInstance();

                            if (auth.getMessage().equals("ERROR")) {
                                Util.AtivaDialogHandler(5, "", "");
                                Util.AtivaDialogHandler(1, getString(R.string.app_name), auth.getMensagemErroApi());
                            } else {
                                if(v != null){
                                    feedForm();
                                }
                               // Util.stopProgressDialog();
                            }

                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
                return false;
            }
        });

        //Trocar imagem
        btnCarImageView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                showPictureDialog();
            }
        });
    }

    private void feedForm(){

        if(veiculo == null){
            return;
        }
        inputPlate.setText(veiculo.getVeic_placa());
        //inputPlate.setEnabled(veiculo.getUser_id() < 1);

        inputModel.setText(veiculo.getVeic_modelo());
        if(!veiculo.getVeic_foto().equals("")) {
            Bitmap image = Util.getImageFromBase64(veiculo.getVeic_foto());
            btnCarImageView.setImageBitmap(image);
        }

    }

    private Veiculos getFromForm(){
        Veiculos v = new Veiculos();
        if(veiculo != null){
            v.setVeic_id(veiculo.getVeic_id());
        }
        v.setVeic_placa(inputPlate.getText().toString());
        v.setVeic_modelo(inputModel.getText().toString());
        Bitmap image = ((BitmapDrawable)btnCarImageView.getDrawable()).getBitmap();
        v.setVeic_foto(Util.getBase64Image(image));
        v.setUser_id(auth.getUsuario().getUser_id());

        return v;
    }

    private void showPictureDialog(){
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(getActivity());
        pictureDialog.setTitle(getString(R.string.image_dialog_title));
        String[] pictureDialogItems = {
                getString(R.string.image_get_gallery),
                getString(R.string.image_get_camera) };
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallary();
                                break;
                            case 1:
                                takePhotoFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    private void choosePhotoFromGallary(){

        Intent buscarFoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(buscarFoto, 1);
    }

    private void takePhotoFromCamera(){

        Intent tirarFoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(tirarFoto,0);
    }

    private boolean formIsValid(){

        if(inputPlate.getText().toString().length() < 8){
            inputPlate.setAnimation(animShake);
            inputPlate.startAnimation(animShake);
            vib.vibrate(120);
            inputPlate.setError(getString(R.string.err_msg_required));
            layoutPlate.setError(getString(R.string.error_wrong_search_plate));
            layoutPlate.setErrorEnabled(true);
            return false;
        }
        if(inputModel.getText().toString().length() < 1){
            inputModel.setAnimation(animShake);
            inputModel.startAnimation(animShake);
            vib.vibrate(120);
            inputModel.setError(getString(R.string.err_msg_required));
            layoutModel.setError(getString(R.string.error_wrong_search_plate));
            layoutModel.setErrorEnabled(true);
            return  false;
        }

        layoutPlate.setErrorEnabled(false);
        layoutModel.setErrorEnabled(false);
        return true;
    }

    private void mask(){
        plateMask = Mask.insert("###-####", inputPlate);
        inputPlate.addTextChangedListener(plateMask);

        searchPlateMask = Mask.insert("###-####",inputSearchPlate);
        inputSearchPlate.addTextChangedListener(searchPlateMask);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultIntent){
        super.onActivityResult(requestCode, resultCode, resultIntent);

        switch (requestCode){
            case 0: //Camera
                if(resultCode == getActivity().RESULT_OK && resultIntent.getExtras().get("data") != null){
                    Bitmap thumbnail = (Bitmap) resultIntent.getExtras().get("data");
                    btnCarImageView.setImageBitmap(thumbnail);
                }
                break;
            case 1: //Galeria
                if(resultCode == getActivity().RESULT_OK && resultIntent.getData() != null){
                    Uri contentURI = resultIntent.getData();
                    try{
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), contentURI);
                        btnCarImageView.setImageBitmap(bitmap);
                    }
                    catch (IOException e){
                    }

                }break;
        }
    }

}
