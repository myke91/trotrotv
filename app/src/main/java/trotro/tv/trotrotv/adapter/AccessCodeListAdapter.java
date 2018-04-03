package trotro.tv.trotrotv.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import trotro.tv.trotrotv.R;
import trotro.tv.trotrotv.model.AccessCode;
import trotro.tv.trotrotv.model.Station;

/**
 * Created by michael.dugah on 3/15/2018.
 */

public class AccessCodeListAdapter extends ArrayAdapter<AccessCode> {
    private final List<AccessCode> values;
    private final Context context;

    public AccessCodeListAdapter(Context context, List<AccessCode> values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_users, parent, false);
        Button button = (Button) rowView.findViewById(R.id.button);

        button.setText(values.get(position).getUsername());

        return rowView;
    }

    private String dateFormat(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEEEE dd-MMM-yyyy", Locale.getDefault());
        try {
            return sdf.format(Date.valueOf(date));
        } catch (Exception ex) {
            return date;
        }
    }
}
