package com.everdune.competitive.ui.events;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.everdune.competitive.Application;
import com.everdune.competitive.R;
import com.everdune.competitive.di.CompetitiveRepositoryVMFactoryProvider;
import com.everdune.competitive.model.events.Event;
import com.everdune.competitive.ui.common.ViewModelFactory;

import java.util.List;

import javax.inject.Inject;

public class EventsFragment extends Fragment {

  private EventsViewModel eventsViewModel;

  @Inject
  protected ViewModelFactory viewModelFactory;

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);

    ((Application)getActivity().getApplication()).getApplicationComponent().inject(this);
  }

  public View onCreateView(@NonNull LayoutInflater inflater,
                           ViewGroup container, Bundle savedInstanceState) {

    eventsViewModel =
      ViewModelProviders.of(this, viewModelFactory).get(EventsViewModel.class);

    View root = inflater.inflate(R.layout.fragment_events, container, false);

    final RecyclerView recyclerView = root.findViewById(R.id.recycler_events);

    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));

    final EventsAdapter adapter = new EventsAdapter(new EventsAdapter.OnClickedListener() {
      @Override
      public void onClicked(Event event) {
        Bundle bundle = new Bundle();
        bundle.putString("EVENT_ID", event.getId());
        NavHostFragment.findNavController(EventsFragment.this).navigate(R.id.navigation_participants, bundle);
      }
    });

    recyclerView.setAdapter(adapter);

    eventsViewModel.isLoading().observe(this, new Observer<Boolean>() {
      @Override
      public void onChanged(Boolean isLoading) {
        if (isLoading) Toast.makeText(getContext(), R.string.is_loading, Toast.LENGTH_SHORT).show();
      }
    });

    eventsViewModel.isFetching().observe(this, new Observer<Boolean>() {
      @Override
      public void onChanged(Boolean isFetching) {
        if (isFetching) Toast.makeText(getContext(), R.string.is_fetching, Toast.LENGTH_SHORT).show();
      }
    });

    eventsViewModel.isLoaded().observe(this, new Observer<Boolean>() {
      @Override
      public void onChanged(Boolean isLoaded) {
        if (isLoaded) Toast.makeText(getContext(), R.string.is_loaded, Toast.LENGTH_SHORT).show();
      }
    });

    eventsViewModel.canFetchMore().observe(this, new Observer<Boolean>() {
      @Override
      public void onChanged(Boolean canFetchMore) {
        if (canFetchMore) Toast.makeText(getContext(), R.string.can_fetch_more, Toast.LENGTH_SHORT).show();
      }
    });

    eventsViewModel.getError().observe(this, new Observer<String>() {
      @Override
      public void onChanged(String error) {
        if (null != error) Toast.makeText(getContext(), error, Toast.LENGTH_SHORT);
      }
    });

    eventsViewModel.getData().observe(this, new Observer<List<Event>>() {
      @Override
      public void onChanged(@Nullable List<Event> data) {
        adapter.bindData(data);
      }
    });

    return root;
  }
}