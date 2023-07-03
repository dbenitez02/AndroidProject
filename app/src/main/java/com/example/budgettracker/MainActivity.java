package com.example.budgettracker;

import android.graphics.Color;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.content.Intent;
import android.os.Bundle;

import com.example.budgettracker.model.Transaction;
import com.example.budgettracker.viewmodel.TransactionListViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Boolean mLoadTransactionList = true;

    private TransactionAdapter mTransactionAdapter;
    private RecyclerView mRecyclerView;
    private TransactionListViewModel mTransactionListViewModel;
    private Transaction mSelectedTransaction;

    private List<Transaction> mTransactionList;

    private int mSelectedPosition = RecyclerView.NO_POSITION;
    private ActionMode mActionMode = null;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TransactionListViewModel mTransactionViewModel = new ViewModelProvider(this).get(TransactionListViewModel.class);
        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        Button btnHelp = findViewById(R.id.btnHelp);


        // Setting up the RecyclerView to list transactions.
        mRecyclerView = findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);

        // Initializing the Transaction Adapter.
        mTransactionAdapter = new TransactionAdapter(mTransactionList);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HelpActivity.class);
                startActivity(intent);
            }
        });

        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, TransactionFormActivity.class);
                startActivity(intent);

            }
        });

        mTransactionViewModel.getTransactions().observe(this, transactions -> {
            if(mLoadTransactionList) {
                updateUI(transactions);
            }
        });

    }
    private void updateUI(List<Transaction> transactionList) {
        TransactionAdapter mTransactionAdapter = new TransactionAdapter(transactionList);
        mRecyclerView.setAdapter(mTransactionAdapter);
    }

    /**
     * Where RecyclerView is defined.
     */
    private class TransactionHolder extends RecyclerView.ViewHolder
            implements View.OnLongClickListener {

        private final TextView mTransactionTextView;
        private Transaction mTransaction;

        public TransactionHolder(LayoutInflater layoutInflater, ViewGroup parent) {
            super(layoutInflater.inflate(R.layout.recycler_view_items, parent, false));
            mTransactionTextView = itemView.findViewById(R.id.transaction_text_view);

            itemView.setOnLongClickListener(this);
        }

        public void bind(Transaction transaction, int position) {

            mTransaction = transaction;
            mTransactionTextView.setText(transaction.getText());

        }

        @Override
        public boolean onLongClick(View view) {


            if(mActionMode != null) {
                return false;
            }

            mSelectedTransaction = mTransaction;
            mSelectedPosition = getAbsoluteAdapterPosition();


            // Rebind selected item
            mTransactionAdapter.notifyItemChanged(mSelectedPosition);

            // show callback action
            mActionMode = MainActivity.this.startActionMode(mActionModeCallback);

            return true;
        }

    }

    /**
     * Action Callback to delete a transaction using context menu.
     */
    private final ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {

            // Provide context menu
            MenuInflater inflater = actionMode.getMenuInflater();
            inflater.inflate(R.menu.context_menu, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {

            // Process action
            if(menuItem.getItemId() == R.id.delete) {
                mLoadTransactionList = false;

                Log.d("Debugging delete ", mSelectedTransaction.getText());

                // Delete from the database
                mTransactionListViewModel.deleteTransaction(mSelectedTransaction);

                // Remove from RecyclerView
                mTransactionAdapter.removeTransaction(mSelectedTransaction);

                actionMode.finish();
                return true;
            }
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode actionMode) {
            mActionMode = null;

            mTransactionAdapter.notifyItemChanged(mSelectedPosition);
            mSelectedPosition = RecyclerView.NO_POSITION;

        }
    };

    /**
     * Transaction Adapter
     */
    private class TransactionAdapter extends RecyclerView.Adapter<TransactionHolder> {

        private final List<Transaction> mTransactionList;

        // This fills the adapter with all transactions
        public TransactionAdapter(List<Transaction> transactions) {
            mTransactionList = transactions;
        }


        /**
         * Remove Transaction
         * @param transaction
         */
        public void removeTransaction(Transaction transaction) {
            int index = mTransactionList.indexOf(transaction);

            if (index >= 0) {
                mTransactionList.remove(index);

                notifyItemRemoved(index);
            }
        }

        @NonNull
        @Override
        public TransactionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getApplicationContext());
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