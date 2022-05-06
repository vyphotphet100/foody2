package hcmute.edu.vn.caodinhsyvy_19110143.foody2;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import hcmute.edu.vn.caodinhsyvy_19110143.foody2.card.HeaderCard;
import hcmute.edu.vn.caodinhsyvy_19110143.foody2.card.SearchResultCard;

public class SearchActivity extends AppCompatActivity {
    FrameLayout headerContainerFragment, mainContainerFrame;

    TextView txtSearch;
    EditText edtTextSearch;

    private void mapping() {
        headerContainerFragment = findViewById(R.id.headerContainerFragment_Search);
        mainContainerFrame = findViewById(R.id.mainContainerFrame_search);
        txtSearch = findViewById(R.id.txtSearch);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mapping();
        setupHeader();
        setEvent();

        edtTextSearch = findViewById(R.id.headerCard_edtTextSearch);
        edtTextSearch.requestFocus();
    }

    private void setEvent() {
        txtSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainContainerFrame.removeAllViews();
                SearchResultCard searchResultCard = new SearchResultCard(SearchActivity.this);
                mainContainerFrame.addView(searchResultCard.getView());
            }
        });
    }

    private void setupHeader() {
        HeaderCard headerCard = new HeaderCard(SearchActivity.this);
        headerContainerFragment.removeAllViews();
        headerContainerFragment.addView(headerCard.getView());
   }
}