package com.example.sqlitedatabase;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import static com.example.sqlitedatabase.RecyclerViewActivity.mAdapter1;

public class NotesAdapter  extends RecyclerView.Adapter<NotesAdapter.MyViewHolder> {

    private Context context;
    private List<Data> notesList;
    DatabaseHelper db;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.data_recycler, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        Data note = notesList.get(position);
        holder.email.setText(note.getEmail());
        holder.id.setText( Integer.toString(note.getId()));
        holder.pass.setText(note.getPass());

        holder.email.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                showActionsDialog(position);
                return false;
            }
        });
    }


    public NotesAdapter(Context context, List<Data> notesList) {
        this.context = context;
        this.notesList = notesList;
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView id;
        public TextView email;
        public TextView pass;

        public MyViewHolder(View view) {
            super(view);
            id = view.findViewById(R.id.id);
            email = view.findViewById(R.id.email);
            pass = view.findViewById(R.id.pass);

        }
    }

    private void showActionsDialog(final int position) {
        CharSequence colors[] = new CharSequence[]{"Edit", "Delete"};

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Choose option");
        builder.setItems(colors, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    showDialog(position);
                } else {
                    deleteNote(position);
                }
            }
        });
        builder.show();
    }

    private void deleteNote(int position) {
        // deleting the note from db
        db = new DatabaseHelper(context);
        db.deleteNote(notesList.get(position));

        // removing the note from the list
        notesList.remove(position);
        mAdapter1.notifyItemRemoved(position);
    }


    public void showDialog(final int position){
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(context);
        View view = layoutInflaterAndroid.inflate(R.layout.dialog_layout, null);

        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(context);
        alertDialogBuilderUserInput.setView(view);

        final EditText inputNote = view.findViewById(R.id.update);
        alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {
                        updateNote(inputNote.getText().toString(), position);
                    }
                })
                .setNegativeButton("cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                dialogBox.cancel();
                            }
                        });

        final AlertDialog alertDialog = alertDialogBuilderUserInput.create();
        alertDialog.show();
    }

    private void updateNote(String note, int position) {
        Data n = notesList.get(position);
        // updating note text
        n.setPass(note);
        db = new DatabaseHelper(context);
        // updating note in db
        db.updateNote(n);
        // refreshing the list
        notesList.set(position, n);
        mAdapter1.notifyItemChanged(position);
    }
}
