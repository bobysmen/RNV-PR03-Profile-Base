package es.iessaladillo.pedrojoya.pr03.ui.main;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
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

        //OnFocusListener
        txtName.setOnFocusChangeListener((v, hasFocus) -> setLblBold(lblName, hasFocus));
        txtPhoneNumber.setOnFocusChangeListener((v, hasFocus) -> setLblBold(lblPhoneNumber, hasFocus));
        txtEmail.setOnFocusChangeListener((v, hasFocus) -> setLblBold(lblEmail, hasFocus));
        txtAddress.setOnFocusChangeListener((v, hasFocus) -> setLblBold(lblAddress, hasFocus));
        txtWeb.setOnFocusChangeListener((v, hasFocus) -> setLblBold(lblWeb, hasFocus));
        //OnClickListener
        imgAvatar.setOnClickListener(v -> {
            avatar=getInstance().getRandomAvatar();
            imgAvatar.setImageResource(avatar.getImageResId());
            imgAvatar.setTag(avatar.getImageResId());
            lblAvatar.setText(avatar.getName());
        });
        lblAvatar.setOnClickListener(v -> {
            avatar=getInstance().getRandomAvatar();
            imgAvatar.setImageResource(avatar.getImageResId());
            imgAvatar.setTag(avatar.getImageResId());
            lblAvatar.setText(avatar.getName());
        });
        //OnEditorActionListener IME
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

        //TextChangedListener
        txtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                checkName();
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
                checkEmail();
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
                checkPhoneNumber();
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
                checkAddress();
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
                checkWeb();
            }
        });
    }


    //Function for Validate
    private boolean checkName() {
        if(TextUtils.isEmpty(txtName.getText())){
            txtName.setError(getString(R.string.main_invalid_data));
            lblName.setEnabled(false);
            return false;
        }else{
            lblName.setEnabled(true);
            return true;
        }
    }

    private boolean checkEmail() {
        if(!isValidEmail(String.valueOf(txtEmail.getText()))){
            txtEmail.setError(getString(R.string.main_invalid_data));
            lblEmail.setEnabled(false);
            imgEmail.setEnabled(false);
            return false;
        }else{
            lblEmail.setEnabled(true);
            imgEmail.setEnabled(true);
            return true;
        }
    }

    private boolean checkPhoneNumber() {
        if(!isValidPhone(String.valueOf(txtPhoneNumber.getText()))){
            txtPhoneNumber.setError(getString(R.string.main_invalid_data));
            lblPhoneNumber.setEnabled(false);
            imgPhoneNumber.setEnabled(false);
            return false;
        }else{
            lblPhoneNumber.setEnabled(true);
            imgPhoneNumber.setEnabled(true);
            return true;
        }
    }

    private boolean checkAddress() {
        if(TextUtils.isEmpty(txtAddress.getText())){
            txtAddress.setError(getString(R.string.main_invalid_data));
            lblAddress.setEnabled(false);
            imgAddress.setEnabled(false);
            return false;
        }else{
            lblAddress.setEnabled(true);
            imgAddress.setEnabled(true);
            return true;
        }
    }

    private boolean checkWeb() {
        if(!isValidUrl(String.valueOf(txtWeb.getText()))){
            txtWeb.setError(getString(R.string.main_invalid_data));
            lblWeb.setEnabled(false);
            imgWeb.setEnabled(false);
            return false;
        }else{
            lblWeb.setEnabled(true);
            imgWeb.setEnabled(true);
            return true;
        }
    }

    //Function Label Bold
    private void setLblBold(TextView lbl, boolean hasFocus){
        if(hasFocus){
            lbl.setTypeface(Typeface.DEFAULT,Typeface.BOLD);
        }else{
            lbl.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
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
        imgAvatar.setTag(Database.getInstance().getDefaultAvatar().getImageResId());
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
        boolean isCheckName, isCheckEmail, isCheckPhoneNumber, isCheckAddress, isCheckWeb;
        isCheckName=checkName();
        isCheckEmail=checkEmail();
        isCheckPhoneNumber=checkPhoneNumber();
        isCheckAddress=checkAddress();
        isCheckWeb=checkWeb();
        if(isCheckName && isCheckEmail && isCheckPhoneNumber && isCheckAddress && isCheckWeb){
            Snackbar.make(lblName,R.string.main_saved_succesfully, Snackbar.LENGTH_LONG).show();
        }else{
            Snackbar.make(lblName, R.string.main_error_saving, Snackbar.LENGTH_LONG).show();
        }

    }

}
