package com.nipuneshop.androidapp.ui.user.phonelogin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.LoggingBehavior;
import com.google.firebase.auth.FirebaseAuth;
import com.nipuneshop.androidapp.MainActivity;
import com.nipuneshop.androidapp.R;
import com.nipuneshop.androidapp.binding.FragmentDataBindingComponent;
import com.nipuneshop.androidapp.databinding.FragmentPhoneLoginBinding;
import com.nipuneshop.androidapp.ui.common.PSFragment;
import com.nipuneshop.androidapp.utils.AutoClearedValue;
import com.nipuneshop.androidapp.utils.PSDialogMsg;
import com.nipuneshop.androidapp.utils.Utils;
import com.nipuneshop.androidapp.viewmodel.user.UserViewModel;

import java.util.Random;

public class PhoneLoginFragment extends PSFragment {


    //region Variables

    private final androidx.databinding.DataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this);

    private PSDialogMsg psDialogMsg;

    @VisibleForTesting
    private AutoClearedValue<FragmentPhoneLoginBinding> binding;

    public AutoClearedValue<ProgressDialog> prgDialog;

    private CallbackManager callbackManager;

    private String number, userName;

    //Firebase test
    private FirebaseAuth mAuth;

    private FirebaseAuth.AuthStateListener mAuthListener;

    //endregion

    private UserViewModel viewModel ;
    //region Override Methods

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FacebookSdk.addLoggingBehavior(LoggingBehavior.REQUESTS);
        FacebookSdk.sdkInitialize(getContext());

        callbackManager = CallbackManager.Factory.create();

        // Inflate the layout for this fragment
        FragmentPhoneLoginBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_phone_login, container, false, dataBindingComponent);

        binding = new AutoClearedValue<>(this, dataBinding);

        return binding.get().getRoot();
    }


    //firebase
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.addAuthStateListener(mAuthListener);
        }
    }

    @Override
    protected void initUIAndActions() {
        dataBindingComponent.getFragmentBindingAdapters().bindFullImageDrawbale(binding.get().bgImageView, getResources().getDrawable(R.drawable.login_app_bg));

        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).setToolbarText(((MainActivity) getActivity()).binding.toolbar, getString(R.string.login__login));
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
            ((MainActivity) this.getActivity()).binding.toolbar.setBackgroundColor(getResources().getColor(R.color.global__primary));
//            ((MainActivity) getActivity()).updateToolbarIconColor(Color.WHITE);
//            ((MainActivity) getActivity()).updateMenuIconWhite();
//            ((MainActivity) getActivity()).refreshPSCount();
        }

        psDialogMsg = new PSDialogMsg(getActivity(), false);

        // Init Dialog
        prgDialog = new AutoClearedValue<>(this, new ProgressDialog(getActivity()));
        //prgDialog.get().setMessage(getString(R.string.message__please_wait));

        prgDialog.get().setMessage((Utils.getSpannableString(getContext(), getString(R.string.message__please_wait), Utils.Fonts.MM_FONT)));
        prgDialog.get().setCancelable(false);

        //fadeIn Animation
        fadeIn(binding.get().getRoot());

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        mAuthListener = firebaseAuth -> {

        };
        //end

        binding.get().backToLoginTextView.setOnClickListener(v ->
                Utils.navigateAfterLogin(PhoneLoginFragment.this.getActivity(), navigationController));

        binding.get().addPhoneButton.setOnClickListener(v -> {
            if ( binding.get().nameEditText.getText().toString().trim().isEmpty()) {

                psDialogMsg.showWarningDialog(getString(R.string.error_message__blank_name), getString(R.string.app__ok));
                psDialogMsg.show();

            }else if(binding.get().phoneEditText.getText().toString().trim().isEmpty()){
                psDialogMsg.showWarningDialog(getString(R.string.error_message__blank_phone_no), getString(R.string.app__ok));
                psDialogMsg.show();
            }
            else {
                number = binding.get().phoneEditText.getText().toString();
                userName = binding.get().nameEditText.getText().toString();
                PhoneLoginFragment.this.validNo(number);
                String otp = getRandomNumberString();
                viewModel.sendSMS(number,otp).observe(this,smsApiResponse -> {

                });
                Utils.navigateAfterPhoneVerify(getActivity(),navigationController,number,userName,otp);

                //Toast.makeText(PhoneLoginFragment.this.getActivity(), number, Toast.LENGTH_LONG).show();
            }
        });


    }



    public static String getRandomNumberString() {
        // It will generate 6 digit random Number.
        // from 0 to 999999
        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        // this will convert any number sequence into 6 character.
        return String.format("%06d", number);
    }

    @Override
    protected void initViewModels() {
        viewModel = ViewModelProviders.of(this,viewModelFactory).get(UserViewModel.class);
    }

    @Override
    protected void initAdapters() {

    }

    @Override
    protected void initData() {

    }

    private void validNo(String no) {
        if (no.isEmpty() || no.length() < 10) {
            binding.get().phoneEditText.setError("Enter a valid mobile");
            binding.get().phoneEditText.requestFocus();
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

    }
}
//endregion
