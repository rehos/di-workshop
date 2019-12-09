package com.everdune.competitive.ui.events;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.everdune.competitive.R;
import com.everdune.competitive.di.CompetitiveRepositoryVMFactoryProvider;
import com.everdune.competitive.model.events.Participant;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ParticipantsFragment extends Fragment {

  private ParticipantsViewModel participantsViewModel;

  public View onCreateView(@NonNull LayoutInflater inflater,
                           ViewGroup container, Bundle savedInstanceState) {

    participantsViewModel =
      ViewModelProviders.of(this, CompetitiveRepositoryVMFactoryProvider.getParticipantsVMFactory()).get(ParticipantsViewModel.class);

    View root = inflater.inflate(R.layout.fragment_events, container, false);

    final RecyclerView recyclerView = root.findViewById(R.id.recycler_events);

    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));

    final ParticipantsAdapter adapter = new ParticipantsAdapter(new ParticipantsAdapter.OnClickedListener() {
      @Override
      public void onClicked(Participant participant) {

      }
    });

    recyclerView.setAdapter(adapter);

    participantsViewModel.isLoading().observe(this, new Observer<Boolean>() {
      @Override
      public void onChanged(Boolean isLoading) {
        if (isLoading) Toast.makeText(getContext(), R.string.is_loading, Toast.LENGTH_SHORT).show();
      }
    });

    participantsViewModel.isFetching().observe(this, new Observer<Boolean>() {
      @Override
      public void onChanged(Boolean isFetching) {
        if (isFetching) Toast.makeText(getContext(), R.string.is_fetching, Toast.LENGTH_SHORT).show();
      }
    });

    participantsViewModel.isLoaded().observe(this, new Observer<Boolean>() {
      @Override
      public void onChanged(Boolean isLoaded) {
        if (isLoaded) Toast.makeText(getContext(), R.string.is_loaded, Toast.LENGTH_SHORT).show();
      }
    });

    participantsViewModel.canFetchMore().observe(this, new Observer<Boolean>() {
      @Override
      public void onChanged(Boolean canFetchMore) {
        if (canFetchMore) Toast.makeText(getContext(), R.string.can_fetch_more, Toast.LENGTH_SHORT).show();
      }
    });

    participantsViewModel.getError().observe(this, new Observer<String>() {
      @Override
      public void onChanged(String error) {
        if (null != error) Toast.makeText(getContext(), error, Toast.LENGTH_SHORT);
      }
    });

    participantsViewModel.getData(getArguments()).observe(this, new Observer<List<Participant>>() {
      @Override
      public void onChanged(@Nullable List<Participant> data) {
        adapter.bindData(data);
      }
    });

    return root;
  }
}