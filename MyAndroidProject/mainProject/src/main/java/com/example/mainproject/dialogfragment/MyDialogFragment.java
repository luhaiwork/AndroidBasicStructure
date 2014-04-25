package com.example.mainproject.dialogfragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.example.mainproject.R;

/**
 * Created by luh on 2014/4/25.
 * Dialog Test Demo With DialogFragment
 */
public class MyDialogFragment extends DialogFragment {
    private final String KEY_TYPE_ORDER = "KEY_TYPE_ORDER";
    Type type;

    public MyDialogFragment(Type type) {
        this.type = type;
    }

    public MyDialogFragment() {
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        return super.onCreateDialog(savedInstanceState);
        if (savedInstanceState != null && savedInstanceState.getInt(KEY_TYPE_ORDER) >= 0) {
            type = Type.values()[savedInstanceState.getInt(KEY_TYPE_ORDER)];
        }
        if (type == null) {
            type = Type.COMM_DIAG;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        switch (type) {
            case COMM_DIAG:
                createCommonDialog(builder);
                break;
            case ITEM_DIAG:
                createItemDialog(builder);
                break;
            case ITEM_MULTICHOICE:
                createMultiChoiceDialog(builder);
                break;
            case ITEM_CUS:
                createCustomViewDialog(builder);
                break;
        }
        return builder.create();
    }

    /**
     * carete normal dialog
     *
     * @param builder
     */
    private void createCommonDialog(AlertDialog.Builder builder) {
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
    }

    /**
     * carete item dialog
     *
     * @param builder
     */
    private void createItemDialog(AlertDialog.Builder builder) {
        builder.setTitle("my title item dialog");
        //when you use setItems not use "setMessage"
//        builder.setMessage("tip message ");
        builder.setItems(R.array.array_dialog, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "click item index :" + which, Toast.LENGTH_SHORT).show();
            }
        });
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
    }

    /**
     * carete multichoice dialog
     *
     * @param builder
     */
    private void createMultiChoiceDialog(AlertDialog.Builder builder) {
        builder.setTitle("my title item dialog");
        //when you use setItems not use "setMessage"
//        builder.setMessage("tip message ");
        boolean[] checked = {true, false, true};
        builder.setMultiChoiceItems(R.array.array_dialog, checked, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                Toast.makeText(getActivity(), "choiced:" + which + "isChecked:" + isChecked, Toast.LENGTH_SHORT).show();
            }
        });
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
    }

    /**
     * carete custom view dialog
     *
     * @param builder
     */
    private void createCustomViewDialog(AlertDialog.Builder builder) {
//        builder.setTitle("my title item dialog");
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        builder.setView(layoutInflater.inflate(R.layout.dialog_cus, null));
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
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (this.type != null) {
            outState.putInt(KEY_TYPE_ORDER, type.ordinal());
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        Toast.makeText(getActivity(), "dialog canceled", Toast.LENGTH_SHORT).show();
        super.onCancel(dialog);
    }

    public enum Type {
        COMM_DIAG, ITEM_DIAG, ITEM_MULTICHOICE,ITEM_CUS;
    }
}
