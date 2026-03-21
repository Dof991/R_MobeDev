package ru.mirea.stulovad.dialog;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class ProgressDialogFragment extends DialogFragment {

    public ProgressDialogFragment() {}
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        ProgressDialog.Builder builder = new ProgressDialog.Builder(getActivity());
        builder.setTitle("Загрузка")
                .setMessage("Загрузка");
        // .setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        return builder.create();
    }
}
