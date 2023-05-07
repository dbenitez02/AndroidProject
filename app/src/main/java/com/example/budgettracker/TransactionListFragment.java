package com.example.budgettracker;

import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import com.example.budgettracker.model.Transaction;
import com.example.budgettracker.model.User;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.budgettracker.ViewModel.TransactionViewModel;
import com.example.budgettracker.repo.MainRepository;

import java.util.List;

public class TransactionListFragment extends Fragment {

    private TransactionViewModel mtransactionViewModel;
    private User user;
    private List<Transaction> transactionList;
    private TextView amountSpent;
    private TextView description;
    private TextView transactionID;
    private RecyclerView mRecyclerView;
    private MyAdapter myAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewRoot = inflater.inflate(R.layout.fragment_transaction_list, container, false);
        mtransactionViewModel = new TransactionViewModel(getActivity().getApplication());
        myAdapter = new MyAdapter(transactionList);

        mRecyclerView = viewRoot.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return viewRoot;


    }
}