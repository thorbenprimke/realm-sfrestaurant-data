package co.moonmonkeylabs.realmsfrestaurantdata;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import co.moonmonkeylabs.realmsfrestaurantdata.model.Business;

public class SFRestaurantDataLoader {

    private static final int DATA_SET_SMALL_NUM = 500;

    public final List<Business> loadBusinessSmallDataSet(Context context) {
        return loadBusinessesData(context, DATA_SET_SMALL_NUM);
    }

    public final List<Business> loadBusinessesData(Context context, int limit) {
        List<Business> businesses = new ArrayList<>();

        InputStream is = context.getResources().openRawResource(R.raw.businesses);

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        try {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null && (limit == -1 || lineNumber < limit)) {
                if (lineNumber++ == 0) {
                    continue;
                }

                String[] rowData = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                if (rowData[6].isEmpty()) {
                    continue;
                }

                businesses.add(new Business(
                        Integer.parseInt(rowData[0]),
                        removeQuotes(rowData[1]),
                        Float.parseFloat(removeQuotes(rowData[6])),
                        Float.parseFloat(removeQuotes(rowData[7]))));
            }
        }
        catch (IOException ex) {}
        finally {
            try {
                is.close();
            }
            catch (IOException e) {}
        }
        return businesses;
    }

    private String removeQuotes(String original) {
        return original.subSequence(1, original.length() - 1).toString();
    }
}
