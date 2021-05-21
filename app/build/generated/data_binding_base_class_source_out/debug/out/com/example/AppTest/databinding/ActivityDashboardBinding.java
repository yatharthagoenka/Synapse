// Generated by view binder compiler. Do not edit!
package com.example.AppTest.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import com.example.AppTest.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityDashboardBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final CardView card1;

  @NonNull
  public final CardView card2;

  @NonNull
  public final CardView card3;

  @NonNull
  public final CardView card4;

  @NonNull
  public final TextView handles;

  @NonNull
  public final ImageView i1;

  @NonNull
  public final ImageView i2;

  @NonNull
  public final ImageView i3;

  @NonNull
  public final ImageView i4;

  @NonNull
  public final LinearLayout ll2;

  @NonNull
  public final LinearLayout llhead;

  private ActivityDashboardBinding(@NonNull RelativeLayout rootView, @NonNull CardView card1,
      @NonNull CardView card2, @NonNull CardView card3, @NonNull CardView card4,
      @NonNull TextView handles, @NonNull ImageView i1, @NonNull ImageView i2,
      @NonNull ImageView i3, @NonNull ImageView i4, @NonNull LinearLayout ll2,
      @NonNull LinearLayout llhead) {
    this.rootView = rootView;
    this.card1 = card1;
    this.card2 = card2;
    this.card3 = card3;
    this.card4 = card4;
    this.handles = handles;
    this.i1 = i1;
    this.i2 = i2;
    this.i3 = i3;
    this.i4 = i4;
    this.ll2 = ll2;
    this.llhead = llhead;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityDashboardBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityDashboardBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_dashboard, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityDashboardBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.card1;
      CardView card1 = rootView.findViewById(id);
      if (card1 == null) {
        break missingId;
      }

      id = R.id.card2;
      CardView card2 = rootView.findViewById(id);
      if (card2 == null) {
        break missingId;
      }

      id = R.id.card3;
      CardView card3 = rootView.findViewById(id);
      if (card3 == null) {
        break missingId;
      }

      id = R.id.card4;
      CardView card4 = rootView.findViewById(id);
      if (card4 == null) {
        break missingId;
      }

      id = R.id.handles;
      TextView handles = rootView.findViewById(id);
      if (handles == null) {
        break missingId;
      }

      id = R.id.i1;
      ImageView i1 = rootView.findViewById(id);
      if (i1 == null) {
        break missingId;
      }

      id = R.id.i2;
      ImageView i2 = rootView.findViewById(id);
      if (i2 == null) {
        break missingId;
      }

      id = R.id.i3;
      ImageView i3 = rootView.findViewById(id);
      if (i3 == null) {
        break missingId;
      }

      id = R.id.i4;
      ImageView i4 = rootView.findViewById(id);
      if (i4 == null) {
        break missingId;
      }

      id = R.id.ll2;
      LinearLayout ll2 = rootView.findViewById(id);
      if (ll2 == null) {
        break missingId;
      }

      id = R.id.llhead;
      LinearLayout llhead = rootView.findViewById(id);
      if (llhead == null) {
        break missingId;
      }

      return new ActivityDashboardBinding((RelativeLayout) rootView, card1, card2, card3, card4,
          handles, i1, i2, i3, i4, ll2, llhead);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
