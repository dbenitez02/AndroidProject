package com.example.budgettracker;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import com.google.android.material.button.MaterialButton;


import com.example.budgettracker.model.Transaction;
import com.example.budgettracker.viewmodel.TransactionListViewModel;
import androidx.lifecycle.ViewModelProvider;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class TransactionFormFragment extends Fragment {

    private Float amountSpent;
    private String description;
    private Transaction mTransaction;
    private TransactionListViewModel mTransactionViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewRoot = inflater.inflate(R.layout.fragment_transaction_form, container, false);

        mTransactionViewModel = new ViewModelProvider(this).get(TransactionListViewModel.class);
        Button btnSubmit = viewRoot.findViewById(R.id.btnSubmit);
        EditText editAmount = (EditText) viewRoot.findViewById(R.id.editAmount);
        EditText editDes = (EditText) viewRoot.findViewById(R.id.editDescription);



        editAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Do nothing
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Do nothing
            }

            @Override
            public void afterTextChanged(Editable editable) {
                amountSpent = Float.valueOf(editable.toString());
            }
        });

        editDes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //  Do nothing
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //  Do nothing
            }

            @Override
            public void afterTextChanged(Editable editable) {
                description = editable.toString();
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mTransaction = new Transaction();
                mTransaction.setAmountSpent(amountSpent);
                mTransaction.setDescription(description);

                mTransactionViewModel.addTransaction(mTransaction);

                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);


            }
        });
        return viewRoot;
    }


}