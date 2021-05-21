package com.example.AppTest;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

import static android.app.Activity.RESULT_OK;

public class MailDialog extends AppCompatDialogFragment {
    private static final int REQUEST_CODE_SPEECH_INPUT = 1;
    ImageView mic;
    EditText nameVal;
    private EditText toastid;
    private EditText totext;
    private EditText sub;
    private EditText body;
    private DialogShare listener;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        MailDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.CustomAlertDialog);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.mail_dialog, null);
        builder.setView(view).setTitle("E-Mail")
                .setNegativeButton("Discard", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("Send", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String to = totext.getText().toString();
                        String subject = sub.getText().toString();
                        String message = body.getText().toString();
                        Intent email = new Intent(Intent.ACTION_SEND);
                        email.putExtra(Intent.EXTRA_EMAIL, new String[]{to});
                        email.putExtra(Intent.EXTRA_SUBJECT, subject);
                        email.putExtra(Intent.EXTRA_TEXT, message);
                        email.setType("message/rfc822");
                        startActivity(Intent.createChooser(email, "Choose an Email client :"));
                        listener.sharetext(to);
                    }
                });

        totext = view.findViewById(R.id.toid);
        sub = view.findViewById(R.id.subtext);
        body = view.findViewById(R.id.bodytext);
        return builder.create();
    }
    @Override
    public void onStart() {
        super.onStart();
        Button positive=((AlertDialog) getDialog()).getButton(AlertDialog.BUTTON_POSITIVE);
        positive.setTextColor(Color.BLACK);
        Button negative=((AlertDialog) getDialog()).getButton(AlertDialog.BUTTON_NEGATIVE);
        negative.setTextColor(Color.BLACK);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener=(DialogShare) context;
        } catch (Exception e) {
            throw new ClassCastException(context.toString()+"Must implement Dialog Listener");
        }
    }

    public interface DialogShare{
        void sharetext(String id);
    }

}
