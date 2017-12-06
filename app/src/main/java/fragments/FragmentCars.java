package fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
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
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rafael.catraca_web_app.R;

import java.io.IOException;

import basic.Usuario;
import basic.Veiculos;
import util.Internet;
import util.Mask;

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
            }
        });

        //Pesquisar
        inputSearchPlate.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {

                if(i == EditorInfo.IME_ACTION_SEARCH){
                    //Procurar e preecher form se existir
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

    }

    private void showPictureDialog(){
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(getActivity());
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Selecionar foto da galeria",
                "Tirar foto com a câmera" };
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
        return false;
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
