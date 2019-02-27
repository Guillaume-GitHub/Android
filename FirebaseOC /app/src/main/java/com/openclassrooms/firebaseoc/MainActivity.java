package com.openclassrooms.firebaseoc;

import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import butterknife.BindView;
import butterknife.OnClick;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.openclassrooms.firebaseoc.auth.ProfileActivity;
import com.openclassrooms.firebaseoc.base.BaseActivity;

import java.util.Arrays;


public class MainActivity extends BaseActivity {

    private static final int RC_SIGN_IN = 123 ;

    @BindView(R.id.main_activity_coordinator_layout) CoordinatorLayout coordinatorLayout;
    @BindView(R.id.main_activity_button_login) Button buttonLogin;

    @Override
    public int getFragmentLayout() { return R.layout.activity_main; }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Handle SignIn Activity response on activity result
        this.handleResponseAfterSignIn(requestCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 5 - Update UI when activity is resuming
        this.updateUIWhenResuming();
    }

    /*--------------- UI -------------------*/

    // Show Snack Bar with a message
    private void showSnackBar(CoordinatorLayout coordinatorLayout, String message){
        Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_SHORT).show();
    }

    // Update UI when activity is resuming
    private void updateUIWhenResuming(){
        this.buttonLogin.setText(this.isCurrentUserLogged() ? getString(R.string.button_login_text_logged) : getString(R.string.button_login_text_not_logged));
    }

    /*--------------- ACTION -------------------*/

    @OnClick (R.id.main_activity_button_login)
    public void onClickLoginButton(){
        if (this.isCurrentUserLogged()){
            this.startProfileActivity();
        } else {
            this.startSignInActivity();
        }
    }

    /*--------------- NAVIGATION -------------------*/
    private void startSignInActivity() {
        startActivityForResult(
            AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setTheme(R.style.LoginTheme)
                    .setAvailableProviders(
                            Arrays.asList(
                                    new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(), // SUPPORT EMAIL
                                    new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build() // SUPPORT GOOGLE
                            ))
                    .setIsSmartLockEnabled(false, true)
                    .setLogo(R.drawable.ic_logo_auth)
                    .build(),
                RC_SIGN_IN);
    }

    //Launching Profile Activity
    private void startProfileActivity(){
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    /*--------------- UTILS -------------------*/

    //Method that handles response after SignIn Activity close
    private void handleResponseAfterSignIn(int requestCode, int resultCode, Intent data){

        IdpResponse response = IdpResponse.fromResultIntent(data);

        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) { // SUCCESS
                showSnackBar(this.coordinatorLayout, getString(R.string.connection_succeed));
            } else { // ERRORS
                if (response == null) {
                    showSnackBar(this.coordinatorLayout, getString(R.string.error_authentication_canceled));
                } else if (response.getErrorCode() == ErrorCodes.NO_NETWORK) {
                    showSnackBar(this.coordinatorLayout, getString(R.string.error_no_internet));
                } else if (response.getErrorCode() == ErrorCodes.UNKNOWN_ERROR) {
                    showSnackBar(this.coordinatorLayout, getString(R.string.error_unknown_error));
                }
            }
        }
    }
}
