package com.example.budgettracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.budgettracker.model.Transaction;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Transaction> mTransactionList;

    public MyAdapter(List<Transaction> transactionList) {
        mTransactionList = transactionList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_transaction_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Transaction transaction = mTransactionList.get(position);
        holder.mTextView.setText(String.valueOf(transaction.getAmountSpent()));
        holder.mTextView.setText(transaction.getDescription());
        holder.mTextView.setText(String.valueOf(transaction.getID()));
    }

    @Override
    public int getItemCount() {
        return mTransactionList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            mTextView = itemView.findViewById(R.id.transaction_text_view);
        }

    }
}
