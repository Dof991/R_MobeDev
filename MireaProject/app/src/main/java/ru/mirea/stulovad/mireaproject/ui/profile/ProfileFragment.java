package ru.mirea.stulovad.mireaproject.ui.profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ru.mirea.stulovad.mireaproject.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {
    private FragmentProfileBinding binding;

    public ProfileFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        binding.etstatus.setText("");
        SharedPreferences sharedPref = requireActivity().getSharedPreferences("mirea_settings", Context.MODE_PRIVATE);

        String savedStatus = sharedPref.getString("STATUS", "");
        if (!savedStatus.isEmpty()) {
            binding.twstatus.setText(savedStatus);
        }

        binding.btnsetstatus.setOnClickListener(v -> {
            String status = binding.etstatus.getText().toString();
            sharedPref.edit().putString("STATUS", status).apply();
            binding.twstatus.setText(status);
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
