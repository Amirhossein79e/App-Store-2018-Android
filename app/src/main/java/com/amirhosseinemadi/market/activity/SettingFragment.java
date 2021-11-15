package com.amirhosseinemadi.market.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import com.amirhosseinemadi.market.R;
import com.amirhosseinemadi.market.common.AppCls;
import com.amirhosseinemadi.market.presenter.SettingPresenter;
import com.amirhosseinemadi.market.view.SettingView;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment implements SettingView {

    private AppCompatEditText emailLogin,passwordLogin,emailSignUp,userSignUp,passwordSignUp,passwordSignUpRe;
    private AppCompatButton login,signUp;
    private AppCompatTextView signUpText,loginText,username,logout;
    private CardView loginCard,signUpCard,userCard;
    private ProgressBar progressBar;
    private SettingPresenter settingPresenter;
    private AppCompatSpinner spinner;
    private boolean first;


    public SettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        settingPresenter = new SettingPresenter(this);

        emailLogin = view.findViewById(R.id.email_login);
        passwordLogin = view.findViewById(R.id.password_login);
        emailSignUp = view.findViewById(R.id.email_signUp);
        userSignUp = view.findViewById(R.id.user_signUp);
        passwordSignUp = view.findViewById(R.id.password_signUp);
        passwordSignUpRe = view.findViewById(R.id.password_signUp_re);
        login = view.findViewById(R.id.btn_login);
        signUp = view.findViewById(R.id.btn_signUp);
        signUpText = view.findViewById(R.id.txt_signUp);
        loginText = view.findViewById(R.id.txt_login);
        loginCard = view.findViewById(R.id.card_login);
        signUpCard = view.findViewById(R.id.card_signUp);
        userCard = view.findViewById(R.id.card_user);
        progressBar = view.findViewById(R.id.setting_progress);
        username = view.findViewById(R.id.txt_username);
        logout = view.findViewById(R.id.txt_logout);
        spinner = view.findViewById(R.id.spinner);

        if (AppCls.component.loginManager().getStatus())
        {
            loginCard.setVisibility(View.GONE);
            userCard.setVisibility(View.VISIBLE);
            username.setText(AppCls.component.loginManager().getUsername());
        }



        loginText.setOnClickListener(this::loginTextClick);
        signUpText.setOnClickListener(this::signUpTextClick);
        login.setOnClickListener(this::loginClick);
        signUp.setOnClickListener(this::signUpClick);
        logout.setOnClickListener(this::logoutClick);

        first = true;

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                if (!first)
                {
                    switch (parent.getSelectedItemPosition())
                    {
                        case 1:
                            AppCls.component.langManager().setLang("en");
                            AlarmManager alarmManager = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
                            Intent intent = getContext().getPackageManager().getLaunchIntentForPackage(getContext().getPackageName());
                            PendingIntent pendingIntent = PendingIntent.getActivity(getContext(),0,intent,PendingIntent.FLAG_CANCEL_CURRENT);
                            alarmManager.set(AlarmManager.RTC,System.currentTimeMillis()+100,pendingIntent);
                            break;

                        case 2:
                            AppCls.component.langManager().setLang("fa");
                            AlarmManager alarmManager2 = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
                            Intent intent2 = getContext().getPackageManager().getLaunchIntentForPackage(getContext().getPackageName());
                            PendingIntent pendingIntent2 = PendingIntent.getActivity(getContext(),0,intent2,PendingIntent.FLAG_CANCEL_CURRENT);
                            alarmManager2.set(AlarmManager.RTC,System.currentTimeMillis()+100,pendingIntent2);
                            break;
                    }
                }

                first = false;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        return view;
    }

    private void loginTextClick(View v)
    {
        loginCard.setVisibility(View.VISIBLE);
        signUpCard.setVisibility(View.GONE);
    }


    private void signUpTextClick(View v)
    {
        signUpCard.setVisibility(View.VISIBLE);
        loginCard.setVisibility(View.GONE);
    }


    private void loginClick(View v)
    {
        String email = emailLogin.getText().toString().trim().toLowerCase();
        String password = passwordLogin.getText().toString().trim();
        if (!email.equals("")&&!password.equals("")&&password.length()>=8)
        {
            settingPresenter.login(email, password);
        }
        else
            {
                Snackbar.make(getView(), R.string.filed, BaseTransientBottomBar.LENGTH_LONG).setAnchorView(R.id.bottom_app_bar).show();
            }
    }


    private void logoutClick(View v)
    {
        AppCls.component.loginManager().setStatus(false);
        loginCard.setVisibility(View.VISIBLE);
        userCard.setVisibility(View.GONE);
    }


    private void signUpClick(View v)
    {

        String email = emailSignUp.getText().toString().trim().toLowerCase();
        String username = userSignUp.getText().toString().trim();
        String pass = passwordSignUp.getText().toString().trim();
        String rePass = passwordSignUpRe.getText().toString().trim();
        if (!email.equals("")&&!username.equals("")&&!pass.equals("")&&!rePass.equals("")&&pass.length()>=8)
        {
            if (pass.equals(rePass)) {
                settingPresenter.signUp(username, email, pass);
            } else {
                Snackbar.make(getView(), R.string.Retype, BaseTransientBottomBar.LENGTH_LONG).setAnchorView(R.id.bottom_app_bar).show();
            }
        }else
            {
                Snackbar.make(getView(), R.string.filed, BaseTransientBottomBar.LENGTH_LONG).setAnchorView(R.id.bottom_app_bar).show();
            }

    }

    @Override
    public void showProgress() {

        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgress() {

        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void loginSnack() {

        Snackbar.make(getView(), R.string.LoginTrue, BaseTransientBottomBar.LENGTH_LONG).setAnchorView(R.id.bottom_app_bar).show();

    }

    @Override
    public void failedSnack() {

        Snackbar.make(getView(), R.string.LoginFailed, BaseTransientBottomBar.LENGTH_LONG).setAnchorView(R.id.bottom_app_bar).show();

    }

    @Override
    public void signUpSnack() {

        Snackbar.make(getView(), R.string.SignupTrue, BaseTransientBottomBar.LENGTH_LONG).setAnchorView(R.id.bottom_app_bar).show();

    }

    @Override
    public void failedSignUp() {

        Snackbar.make(getView(), R.string.SignupFailed, BaseTransientBottomBar.LENGTH_LONG).setAnchorView(R.id.bottom_app_bar).show();

    }

    @Override
    public void connectionError() {

        Snackbar.make(getView(),R.string.ConnectionFailed, BaseTransientBottomBar.LENGTH_LONG).setAnchorView(R.id.bottom_app_bar).show();

    }

    @Override
    public void handleCard() {
        //signUpCard.setVisibility(View.GONE);
        //loginCard.setVisibility(View.GONE);
        //userCard.setVisibility(View.VISIBLE);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame,new SettingFragment()).commit();
    }

}
