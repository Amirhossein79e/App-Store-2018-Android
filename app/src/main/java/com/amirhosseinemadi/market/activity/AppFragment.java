package com.amirhosseinemadi.market.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import com.amirhosseinemadi.market.R;
import com.amirhosseinemadi.market.common.AppCls;
import com.amirhosseinemadi.market.model.adapter.AppImageAdapter;
import com.amirhosseinemadi.market.model.adapter.CommentAdapter;
import com.amirhosseinemadi.market.model.entity.App;
import com.amirhosseinemadi.market.model.entity.Comment;
import com.amirhosseinemadi.market.model.request.MyRetrofit;
import com.amirhosseinemadi.market.presenter.AppPresenter;
import com.amirhosseinemadi.market.view.AppView;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.ms.square.android.expandabletextview.ExpandableTextView;
import com.squareup.picasso.Picasso;
import java.io.File;
import java.text.DecimalFormat;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AppFragment extends Fragment implements AppView {

    private ExpandableTextView expandableTextView;
    private AppCompatImageView imageView;
    private AppCompatTextView appName,starText,sizeTxt,priceTxt,progressTxt,noComment;
    private AppCompatEditText comment;
    private RecyclerView recyclerView;
    private App app;
    private RecyclerView commentRecycler;
    private AppCompatRatingBar ratingBar;
    private AppCompatButton submit,open,delete,install;
    private AppPresenter appPresenter;
    private float stars;
    private ProgressBar progressBar,progressBar2;
    private Bundle bundle;

    public AppFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        bundle = this.getArguments();
        if (bundle!=null)
        {
            app = bundle.getParcelable("app");
        }

        View view = inflater.inflate(R.layout.fragment_app, container, false);

        expandableTextView = view.findViewById(R.id.app_short_text);
        imageView = view.findViewById(R.id.app_image);
        appName = view.findViewById(R.id.app_text);
        recyclerView = view.findViewById(R.id.app_recycler);
        commentRecycler = view.findViewById(R.id.comment_recycler);
        comment = view.findViewById(R.id.user_comment);
        ratingBar = view.findViewById(R.id.rating);
        submit = view.findViewById(R.id.submit);
        starText = view.findViewById(R.id.star_txt);
        sizeTxt = view.findViewById(R.id.size_txt);
        priceTxt = view.findViewById(R.id.price_txt);
        open = view.findViewById(R.id.btn_open);
        delete = view.findViewById(R.id.btn_delete);
        install = view.findViewById(R.id.btn_install);
        progressBar = view.findViewById(R.id.download_progress);
        progressTxt = view.findViewById(R.id.txt_progress);
        progressBar2 = view.findViewById(R.id.app_progress);
        noComment = view.findViewById(R.id.txt_no_comment);
        ratingBar.setMax(5);

        if (bundle!=null)
        {
            if (AppCls.component.apiCaller().appInstalled(app.getPackagee())) {
                open.setVisibility(View.VISIBLE);
                delete.setVisibility(View.VISIBLE);
                install.setVisibility(View.GONE);
            }
        }


        if (bundle!=null)
        {
            appPresenter = new AppPresenter(AppFragment.this, app.getSlider(), app.getName());
        }


        submit.setOnClickListener(this::submitComment);

        open.setOnClickListener(this::openClick);
        delete.setOnClickListener(this::deleteClick);
        install.setOnClickListener(AppFragment.this::installClick);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                stars = rating;
            }
        });



        return view;
    }

    @Override
    public void initLayout(List<String> list, List<Comment> comments) {

        expandableTextView.setText(app.getDetail());

        Picasso.get().load(MyRetrofit.APPIC_URL+app.getIcon()+".png").into(imageView);

        appName.setText(app.getName());
        sizeTxt.setText(app.getSize());
        priceTxt.setText(app.getPrice());

        AppImageAdapter appImageAdapter = new AppImageAdapter(list,app.getName());
        LinearLayoutManager layoutManager = new LinearLayoutManager(AppCls.component.context(),RecyclerView.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(appImageAdapter);

        CommentAdapter commentAdapter = new CommentAdapter(comments);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(AppCls.component.context(),RecyclerView.VERTICAL,false);
        commentRecycler.setLayoutManager(layoutManager2);
        commentRecycler.setAdapter(commentAdapter);

        if (comments.size() == 0)
        {
            noComment.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void showProgress() {

        progressBar2.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgress() {

        progressBar2.setVisibility(View.GONE);

    }

    @Override
    public void initRate(Float f) {

        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        starText.setText(decimalFormat.format(f));

    }

    @Override
    public void connectionError() {

        Snackbar.make(getView(),R.string.ConnectionFailed,BaseTransientBottomBar.LENGTH_LONG).setAnchorView(R.id.bottom_app_bar).show();

    }

    @Override
    public void showProgressBar() {

        progressBar.setVisibility(View.VISIBLE);
        progressTxt.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgressBar() {

        progressBar.setVisibility(View.GONE);
        progressTxt.setVisibility(View.GONE);

    }

    @Override
    public void handleProgress(Integer progress) {

        progressBar.setProgress(progress);
        progressTxt.setText(progress+"%");

    }

    @Override
    public void installApp() {

        File file = new File(AppCls.component.context().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),app.getPackagee());
        Uri apk = FileProvider.getUriForFile(AppCls.component.context(),"com.amirhosseinemadi.market.provider",file);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.putExtra(Intent.EXTRA_RETURN_RESULT,true);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N)
        {
            intent.setDataAndType(Uri.fromFile(file),"application/vnd.android.package-archive");
        }
        else
        {
            intent.setDataAndType(apk,"application/vnd.android.package-archive");
            System.out.println(apk+"!!!!!!!!!!!!!!!");
        }
        startActivityForResult(intent,1);

    }

    @Override
    public void comment() {

        Snackbar.make(getView(), R.string.commentSubmitted,BaseTransientBottomBar.LENGTH_LONG).setAnchorView(R.id.bottom_app_bar).show();
        AppFragment appFragment = new AppFragment();
        Bundle bundle2 = new Bundle();
        bundle2.putParcelable("app",bundle.getParcelable("app"));
        appFragment.setArguments(bundle2);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame,appFragment).commit();
    }


    private void submitComment(View v)
    {
        if (AppCls.component.loginManager().getStatus())
        {
        String userComment = comment.getText().toString().trim();
        if (stars>0)
        {
        appPresenter.addComment(AppCls.component.loginManager().getUsername(),userComment, (int) stars);
        }else
            {
                Snackbar.make(getView(), R.string.SetStars,BaseTransientBottomBar.LENGTH_LONG).setAnchorView(R.id.bottom_app_bar).show();
            }

        }
        else
        {
            Snackbar.make(getView(), R.string.NeedLogin,BaseTransientBottomBar.LENGTH_LONG).setAnchorView(R.id.bottom_app_bar).show();
        }

    }


    private void installClick(View v)
    {
        install.setVisibility(View.GONE);
        appPresenter.downloadApp(app.getName(),app.getPackagee(),getContext());
    }


    private void openClick(View v)
    {
        Intent intent = AppCls.component.context().getPackageManager().getLaunchIntentForPackage(app.getPackagee());
        if (intent!=null)
        {
            startActivity(intent);
        }
    }


    private void deleteClick(View v)
    {
        if (AppCls.component.apiCaller().appInstalled(app.getPackagee()))
        {
            Uri packagee = Uri.parse("package:"+app.getPackagee());
            Intent intent = new Intent(Intent.ACTION_DELETE,packagee);
            intent.putExtra(Intent.EXTRA_RETURN_RESULT,true);
            startActivityForResult(intent,2);
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1)
        {
            if (resultCode == Activity.RESULT_OK)
            {
                open.setVisibility(View.VISIBLE);
                delete.setVisibility(View.VISIBLE);
            }
            else
            {
                if (AppCls.component.apiCaller().appInstalled(app.getPackagee()))
                {
                    open.setVisibility(View.VISIBLE);
                    delete.setVisibility(View.VISIBLE);
                }else
                    {
                     install.setVisibility(View.VISIBLE);
                    }
            }
        }

        if (requestCode == 2)
        {
            if (resultCode == getActivity().RESULT_OK)
            {
                open.setVisibility(View.GONE);
                delete.setVisibility(View.GONE);
                install.setVisibility(View.VISIBLE);
            }
        }

    }
}
