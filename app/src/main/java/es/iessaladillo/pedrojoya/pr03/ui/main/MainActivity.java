package es.iessaladillo.pedrojoya.pr03.ui.main;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import es.iessaladillo.pedrojoya.pr03.R;
import es.iessaladillo.pedrojoya.pr03.data.local.Database;
import es.iessaladillo.pedrojoya.pr03.data.local.model.Avatar;
import es.iessaladillo.pedrojoya.pr03.utils.ValidationUtils;

import static es.iessaladillo.pedrojoya.pr03.data.local.Database.getInstance;
import static es.iessaladillo.pedrojoya.pr03.utils.ValidationUtils.isValidEmail;
import static es.iessaladillo.pedrojoya.pr03.utils.ValidationUtils.isValidPhone;
import static es.iessaladillo.pedrojoya.pr03.utils.ValidationUtils.isValidUrl;

public class MainActivity extends AppCompatActivity {

    private TextView lblName;
    private EditText txtName;
    private TextView lblPhoneNumber;
    private EditText txtPhoneNumber;
    private TextView lblEmail;
    private EditText txtEmail;
    private TextView lblAddress;
    private EditText txtAddress;
    private TextView lblWeb;
    private EditText txtWeb;
    private ImageView imgEmail;
    private ImageView imgPhoneNumber;
    private ImageView imgAddress;
    private ImageView imgWeb;
    private ImageView imgAvatar;
    private TextView lblAvatar;
    private Avatar avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupView();

        txtName.setOnFocusChangeListener((v, hasFocus) -> setLblBold(lblName, hasFocus));
        txtPhoneNumber.setOnFocusChangeListener((v, hasFocus) -> setLblBold(lblPhoneNumber, hasFocus));
        txtEmail.setOnFocusChangeListener((v, hasFocus) -> setLblBold(lblEmail, hasFocus));
        txtAddress.setOnFocusChangeListener((v, hasFocus) -> setLblBold(lblAddress, hasFocus));
        txtWeb.setOnFocusChangeListener((v, hasFocus) -> setLblBold(lblWeb, hasFocus));
        imgAvatar.setOnClickListener(v -> {
            avatar=getInstance().getRandomAvatar();
            imgAvatar.setImageResource(avatar.getImageResId());
            lblAvatar.setText(avatar.getName());
        });
        lblAvatar.setOnClickListener(v -> {
            avatar=getInstance().getRandomAvatar();
            imgAvatar.setImageResource(avatar.getImageResId());
            lblAvatar.setText(avatar.getName());
        });

        txtWeb.setOnEditorActionListener((v, actionId, event) -> {
            if(actionId==EditorInfo.IME_ACTION_DONE){
                save();
                InputMethodManager imm =
                        (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                return true;
            }
            return false;
        });



        txtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(TextUtils.isEmpty(txtName.getText())){
                    txtName.setError(getString(R.string.msg_error));
                    lblName.setEnabled(false);
                }else{
                   lblName.setEnabled(true);
                }
            }
        });

        txtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!isValidEmail(String.valueOf(txtEmail.getText()))){
                    txtEmail.setError(getString(R.string.msg_error));
                    lblEmail.setEnabled(false);
                    imgEmail.setEnabled(false);
                }else{
                    lblEmail.setEnabled(true);
                    imgEmail.setEnabled(true);
                }
            }
        });

        txtPhoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!isValidPhone(String.valueOf(txtPhoneNumber.getText()))){
                    txtPhoneNumber.setError(getString(R.string.msg_error));
                    lblPhoneNumber.setEnabled(false);
                    imgPhoneNumber.setEnabled(false);
                }else{
                    lblPhoneNumber.setEnabled(true);
                    imgPhoneNumber.setEnabled(true);
                }
            }
        });

        txtAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(TextUtils.isEmpty(txtAddress.getText())){
                    txtAddress.setError(getString(R.string.msg_error));
                    lblAddress.setEnabled(false);
                    imgAddress.setEnabled(false);
                }else{
                    lblAddress.setEnabled(true);
                    imgAddress.setEnabled(true);
                }
            }
        });

        txtWeb.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!isValidUrl(String.valueOf(txtWeb.getText()))){
                    txtWeb.setError(getString(R.string.msg_error));
                    lblWeb.setEnabled(false);
                    imgWeb.setEnabled(false);
                }else{
                    lblWeb.setEnabled(true);
                    imgWeb.setEnabled(true);
                }
            }
        });
    }

    private void setLblBold(TextView lbl, boolean hasFocus){
        if(hasFocus){
            lbl.setTypeface(null,Typeface.BOLD);
        }else{
            lbl.setTypeface(null, Typeface.NORMAL);
        }
    }

    private void setupView() {
        imgAvatar=ActivityCompat.requireViewById(this, R.id.imgAvatar);
        lblAvatar=ActivityCompat.requireViewById(this, R.id.lblAvatar);
        lblName=ActivityCompat.requireViewById(this,R.id.lblName);
        txtName=ActivityCompat.requireViewById(this, R.id.txtName);
        lblPhoneNumber=ActivityCompat.requireViewById(this, R.id.lblPhonenumber);
        txtPhoneNumber=ActivityCompat.requireViewById(this, R.id.txtPhonenumber);
        imgPhoneNumber=ActivityCompat.requireViewById(this, R.id.imgPhonenumber);
        lblEmail=ActivityCompat.requireViewById(this, R.id.lblEmail);
        txtEmail=ActivityCompat.requireViewById(this, R.id.txtEmail);
        imgEmail=ActivityCompat.requireViewById(this, R.id.imgEmail);
        lblAddress=ActivityCompat.requireViewById(this, R.id.lblAddress);
        txtAddress=ActivityCompat.requireViewById(this, R.id.txtAddress);
        imgAddress=ActivityCompat.requireViewById(this, R.id.imgAddress);
        lblWeb=ActivityCompat.requireViewById(this, R.id.lblWeb);
        txtWeb=ActivityCompat.requireViewById(this, R.id.txtWeb);
        imgWeb=ActivityCompat.requireViewById(this, R.id.imgWeb);
        //Set default Avatar
        imgAvatar.setImageResource(Database.getInstance().getDefaultAvatar().getImageResId());
        lblAvatar.setText(Database.getInstance().getDefaultAvatar().getName());

    }

    // DO NOT TOUCH
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // DO NOT TOUCH
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.mnuSave) {
            save();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Checks if form is valid or not and shows a Snackbar accordingly
     **/
    private void save() {
        Snackbar.make(lblName,"Student saved succesfully", Snackbar.LENGTH_LONG).show();
    }

}
