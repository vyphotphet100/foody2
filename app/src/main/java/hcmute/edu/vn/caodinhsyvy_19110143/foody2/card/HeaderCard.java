package hcmute.edu.vn.caodinhsyvy_19110143.foody2.card;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;

import hcmute.edu.vn.caodinhsyvy_19110143.foody2.MainActivity;
import hcmute.edu.vn.caodinhsyvy_19110143.foody2.R;
import hcmute.edu.vn.caodinhsyvy_19110143.foody2.SearchActivity;

public class HeaderCard extends BaseCard{
    private int layout = R.layout.card_header;
    private View view;
    private Context context;

    public EditText edtTextSearch;
    public ConstraintLayout titleLayout;
    public ImageView imgFilter;

    @Override
    protected void mapping() {
        edtTextSearch = view.findViewById(R.id.headerCard_edtTextSearch);
        titleLayout = view.findViewById(R.id.headerCard_titleLayout);
        imgFilter = view.findViewById(R.id.headerCard_imgFilter);
    }

    @Override
    public View getView() {
        return this.view;
    }

    @Override
    protected void setListenerEvent() {
        edtTextSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    if (context instanceof MainActivity) {
                        Intent intent = new Intent(context, SearchActivity.class);
                        context.startActivity(intent);
                        titleLayout.requestFocus();
                    }

                }
            }
        });

        imgFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (context instanceof MainActivity) {
                    Intent intent = new Intent(context, SearchActivity.class);
                    context.startActivity(intent);
                    titleLayout.requestFocus();
                }
            }
        });
    }

    public HeaderCard(Context context) {
        this.context = context;
        view = View.inflate(this.context, layout, null);
        onCreate();
    }

    @Override
    protected void onCreate() {
        super.onCreate();
    }
}
