package android.lemon.theme;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;


/**
 * Created by Kevin on 2016/5/5.
 */
public abstract class ThemeActivity extends AppCompatActivity {

    LemonToolbar lemonLemonToolbar;

    protected FrameLayout root;

    private View mainView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        root = (FrameLayout) getLayoutInflater().inflate(R.layout.activity_theme, null);
        lemonLemonToolbar = (LemonToolbar) root.findViewById(R.id.tb_toolbar);
        super.setContentView(root);
        super.onCreate(savedInstanceState);
        setSupportActionBar(lemonLemonToolbar);
        ViewUtil.addOnGlobalLayoutListener(lemonLemonToolbar, new Runnable() {
            @Override
            public void run() {
                lemonLemonToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
                lemonLemonToolbar.setNavigationOnClickListener(navigationListener);
                lemonLemonToolbar.setTitleInCenter();
            }
        });

    }

    @Override
    public void setContentView(int layoutResID) {

        root.addView(getLayoutInflater().inflate(layoutResID, null), 0);
    }

    View.OnClickListener navigationListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };


    public void setViewUnderToolbar(int layoutResID) {
        mainView = getLayoutInflater().inflate(layoutResID, null);
        final FrameLayout.LayoutParams flLayoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        ViewUtil.addOnGlobalLayoutListener(lemonLemonToolbar, new Runnable() {
            @Override
            public void run() {
                mainView.setPadding(mainView.getPaddingLeft(), mainView.getPaddingTop() + lemonLemonToolbar.getHeight(), mainView.getPaddingRight(), mainView.getPaddingBottom());
                mainView.setLayoutParams(flLayoutParams);
            }
        });
        root.addView(mainView, 0, flLayoutParams);
    }

    protected LemonToolbar getLemonToolbar() {
        return lemonLemonToolbar;
    }


}
