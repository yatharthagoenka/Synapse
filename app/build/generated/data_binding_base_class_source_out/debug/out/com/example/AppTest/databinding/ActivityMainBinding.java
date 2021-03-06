// Generated by view binder compiler. Do not edit!
package com.example.AppTest.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.example.AppTest.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityMainBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView h1;

  @NonNull
  public final TextView h2;

  @NonNull
  public final View l1;

  @NonNull
  public final View l2;

  @NonNull
  public final View l4;

  @NonNull
  public final View l5;

  @NonNull
  public final View l6;

  @NonNull
  public final View l7;

  @NonNull
  public final View li3;

  private ActivityMainBinding(@NonNull ConstraintLayout rootView, @NonNull TextView h1,
      @NonNull TextView h2, @NonNull View l1, @NonNull View l2, @NonNull View l4, @NonNull View l5,
      @NonNull View l6, @NonNull View l7, @NonNull View li3) {
    this.rootView = rootView;
    this.h1 = h1;
    this.h2 = h2;
    this.l1 = l1;
    this.l2 = l2;
    this.l4 = l4;
    this.l5 = l5;
    this.l6 = l6;
    this.l7 = l7;
    this.li3 = li3;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_main, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityMainBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.h1;
      TextView h1 = rootView.findViewById(id);
      if (h1 == null) {
        break missingId;
      }

      id = R.id.h2;
      TextView h2 = rootView.findViewById(id);
      if (h2 == null) {
        break missingId;
      }

      id = R.id.l1;
      View l1 = rootView.findViewById(id);
      if (l1 == null) {
        break missingId;
      }

      id = R.id.l2;
      View l2 = rootView.findViewById(id);
      if (l2 == null) {
        break missingId;
      }

      id = R.id.l4;
      View l4 = rootView.findViewById(id);
      if (l4 == null) {
        break missingId;
      }

      id = R.id.l5;
      View l5 = rootView.findViewById(id);
      if (l5 == null) {
        break missingId;
      }

      id = R.id.l6;
      View l6 = rootView.findViewById(id);
      if (l6 == null) {
        break missingId;
      }

      id = R.id.l7;
      View l7 = rootView.findViewById(id);
      if (l7 == null) {
        break missingId;
      }

      id = R.id.li3;
      View li3 = rootView.findViewById(id);
      if (li3 == null) {
        break missingId;
      }

      return new ActivityMainBinding((ConstraintLayout) rootView, h1, h2, l1, l2, l4, l5, l6, l7,
          li3);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
