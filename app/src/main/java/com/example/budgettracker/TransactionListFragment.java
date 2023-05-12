package com.example.budgettracker;

import android.content.Intent;
import android.os.Bundle;

import com.example.budgettracker.model.Transaction;
import com.example.budgettracker.viewmodel.TransactionListViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;

public class TransactionListFragment extends Fragment {


    private List<Transaction> transactionList;
    private TextView amountSpent;
    private TextView description;
    private TextView transactionID;
    private TransactionAdapter mtransactionAdapter;
    private RecyclerView mRecyclerView;
    private TransactionListViewModel mTransactionViewModel;
    private FloatingActionButton floatingActionButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewRoot = inflater.inflate(R.layout.fragment_transaction_list, container, false);
        mTransactionViewModel = new TransactionListViewModel(getActivity().getApplication());
        floatingActionButton = (FloatingActionButton) viewRoot.findViewById(R.id.floatingActionButton);


        // Right here we will implement all the database nonsense

        mRecyclerView = viewRoot.findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());

        mRecyclerView.setLayoutManager(linearLayoutManager);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TransactionFormActivity.class);
                startActivity(intent);
            }
        });

        return viewRoot;

    }

    private class TransactionHolder extends RecyclerView.ViewHolder {

        private Transaction mTransaction;
        private final TextView mTransactionTextView;


        public TransactionHolder(LayoutInflater layoutInflater, @NonNull View itemView) {
            super(LayoutInflater.from(getActivity().getApplicationContext())
                    .inflate(R.layout.fragment_transaction_list, (ViewGroup) itemView, false));
            mTransactionTextView = itemView.findViewById(R.id.transaction_text_view);
        }

        public void bind(Transaction transaction, int position) {
            mTransaction =  transaction;
            mTransactionTextView.setText(transaction.getText());
        }
    }

    private class TransactionAdapter extends RecyclerView.Adapter<TransactionHolder> {

        private final List<Transaction> mTransactionList;

        public TransactionAdapter(List<Transaction> transactions) {
            mTransactionList = transactions;
        }

        @NonNull
        @Override
        public TransactionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity().getApplicationContext());
            return new TransactionHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(TransactionHolder holder, int position) {
            holder.bind(mTransactionList.get(position), position);
        }

        @Override
        public int getItemCount() {
            return mTransactionList.size();
        }
    }

}

