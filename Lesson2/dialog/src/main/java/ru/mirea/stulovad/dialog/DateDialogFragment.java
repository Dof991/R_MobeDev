package ru.mirea.stulovad.dialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class DateDialogFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        DatePickerDialog.Builder builder = new DatePickerDialog.Builder(getActivity());
        builder.setTitle("Выберите дату");
        return builder.create();
    }
}