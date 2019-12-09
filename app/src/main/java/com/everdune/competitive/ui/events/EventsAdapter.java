package com.everdune.competitive.ui.events;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.everdune.competitive.R;
import com.everdune.competitive.model.events.Event;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventViewHolder> {

  public interface OnClickedListener {
    void onClicked(Event event);
  }

  public class EventViewHolder extends RecyclerView.ViewHolder {
    public TextView nameLabel;
    public TextView dateLabel;

    public EventViewHolder(View view) {
      super(view);

      nameLabel = view.findViewById(R.id.label_name);
      dateLabel = view.findViewById(R.id.label_date);
    }
  }

  private final OnClickedListener onClickedListener;

  private List<Event> data;

  public EventsAdapter(OnClickedListener onClickedListener) {
    this.onClickedListener = onClickedListener;
  }

  public void bindData(List<Event> data) {
    this.data = data;
    notifyDataSetChanged();
  }

  @NonNull
  @Override
  public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View itemView = LayoutInflater.from(parent.getContext())
      .inflate(R.layout.layout_event_list_item, parent, false);

    return new EventViewHolder(itemView);
  }

  @Override
  public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
    final Event event = data.get(position);

    holder.nameLabel.setText(event.getName());
    holder.dateLabel.setText(event.getDate().toString());

    if (null != onClickedListener) {
      holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          onClickedListener.onClicked(event);
        }
      });
    }
  }

  @Override
  public int getItemCount() {
    return null != data ? data.size() : 0;
  }
}
