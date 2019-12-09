package com.everdune.competitive.ui.events;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.everdune.competitive.R;
import com.everdune.competitive.model.events.Participant;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ParticipantsAdapter extends RecyclerView.Adapter<ParticipantsAdapter.ParticipantViewHolder> {

  public interface OnClickedListener {
    void onClicked(Participant event);
  }

  public class ParticipantViewHolder extends RecyclerView.ViewHolder {
    public TextView nameLabel;
    public TextView numberLabel;
    public TextView resultLabel;

    public ParticipantViewHolder(View view) {
      super(view);

      nameLabel = view.findViewById(R.id.label_name);
      numberLabel = view.findViewById(R.id.label_number);
      resultLabel = view.findViewById(R.id.label_result);
    }
  }

  private final OnClickedListener onClickedListener;

  private List<Participant> data;

  public ParticipantsAdapter(OnClickedListener onClickedListener) {
    this.onClickedListener = onClickedListener;
  }

  public void bindData(List<Participant> data) {
    this.data = data;
    notifyDataSetChanged();
  }

  @NonNull
  @Override
  public ParticipantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View itemView = LayoutInflater.from(parent.getContext())
      .inflate(R.layout.layout_participant_list_item, parent, false);

    return new ParticipantViewHolder(itemView);
  }

  @Override
  public void onBindViewHolder(@NonNull ParticipantViewHolder holder, int position) {
    final Participant participant = data.get(position);

    holder.nameLabel.setText(participant.getName());
    Integer number = participant.getNumber();
    if (null != number) {
      holder.numberLabel.setVisibility(View.VISIBLE);
      holder.numberLabel.setText(participant.getNumber().toString());
    } else {
      holder.numberLabel.setVisibility(View.GONE);
    }
    holder.resultLabel.setText(participant.getFormattedResult());

    if (null != onClickedListener) {
      holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          onClickedListener.onClicked(participant);
        }
      });
    }
  }

  @Override
  public int getItemCount() {
    return null != data ? data.size() : 0;
  }
}
