package com.example.mainproject.dialogfragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;

/**
 * Created by luh on 2014/4/25.
 * Dialog Test Demo With DialogFragment
 */
public class MyDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        return super.onCreateDialog(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("my title");
        builder.setMessage("tip message ");
        builder.setPositiveButton("positiveButton", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "positive button click", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("nagativeButton", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "negativebutton click", Toast.LENGTH_SHORT).show();
            }
        });
        return builder.create();
    }

}
