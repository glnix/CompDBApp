package ru.goryachev.testjobs.compdb.screens;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.Collections;
import java.util.List;

import ru.goryachev.testjobs.compdb.R;
import ru.goryachev.testjobs.compdb.logic.presenters.CompListPresenter;
import ru.goryachev.testjobs.compdb.models.Comp;
import ru.goryachev.testjobs.compdb.screens.common.BaseActivity;
import ru.goryachev.testjobs.compdb.views.CompListView;
import timber.log.Timber;

public class CompListActivity extends BaseActivity implements CompListView {
    @InjectPresenter
    CompListPresenter compListPresenter;
    CompRecyclerViewAdapter adapter;
    RecyclerView compListRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comp_list);
        initBaseViews();
        setToolbarTitle(R.string.app_name);

        initViews();
    }

    private void initViews() {
        adapter = new CompRecyclerViewAdapter();
        compListRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_comp_list);
        compListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        compListRecyclerView.setAdapter(adapter);
    }


    @Override
    public void showCompList(List<Comp> compList) {
        adapter.setCompList(compList);
    }

    @Override
    public void showLoadDataError(@StringRes int message) {
        Snackbar
                .make(getRootView(), getString(message), Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.repeat, v -> compListPresenter.getCompList())
                .show();
    }

    //    Adapter
    class CompRecyclerViewAdapter extends RecyclerView.Adapter<CompRecyclerViewAdapter.CompViewHolder> {
        List<Comp> compList = Collections.emptyList();

        @Override
        public CompViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View compItemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_comp_list, parent, false);

            return new CompViewHolder(compItemView);
        }

        @Override
        public void onBindViewHolder(CompViewHolder holder, int position) {
            Comp comp = compList.get(position);
            holder.name.setText(comp.getName());
            holder.manufacturer.setText(comp.getCompany().getName());
            holder.itemView.setOnClickListener(v -> {
                        Intent intent = new Intent(getActivityContext(), CompDetailsActivity.class);
                        intent.putExtra("ID", comp.getId());
                        startActivity(intent);
                    });
        }

        @Override
        public int getItemCount() {
            return compList.size();
        }

        public void setCompList(List<Comp> compList) {
            this.compList = compList;
            notifyDataSetChanged();
        }

        class CompViewHolder extends RecyclerView.ViewHolder {
            AppCompatTextView name;
            AppCompatTextView manufacturer;

            public CompViewHolder(View itemView) {
                super(itemView);
                name = (AppCompatTextView) itemView.findViewById(R.id.comp_name);
                manufacturer = (AppCompatTextView) itemView.findViewById(R.id.comp_manufacturer);
            }
        }
    }

}
