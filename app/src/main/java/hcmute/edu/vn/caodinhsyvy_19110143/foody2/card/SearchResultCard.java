package hcmute.edu.vn.caodinhsyvy_19110143.foody2.card;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import hcmute.edu.vn.caodinhsyvy_19110143.foody2.R;
import hcmute.edu.vn.caodinhsyvy_19110143.foody2.SearchActivity;

public class SearchResultCard extends BaseCard{
    private int layout = R.layout.card_search_result;
    private View view;
    private Context context;

    public ImageView imgReturn;

    @Override
    protected void mapping() {
        imgReturn = view.findViewById(R.id.searchResultCard_imgReturn);
    }

    public SearchResultCard(Context context) {
        this.context = context;
        view = View.inflate(this.context, layout, null);
        onCreate();
    }

    @Override
    public View getView() {
        return this.view;
    }

    @Override
    protected void setListenerEvent() {
        imgReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View clickedView) {
                ((SearchActivity)context).finish();
            }
        });
    }
}
