package hcmute.edu.vn.caodinhsyvy_19110143.foody2.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import hcmute.edu.vn.caodinhsyvy_19110143.foody2.MainActivity;
import hcmute.edu.vn.caodinhsyvy_19110143.foody2.R;

public class DBManager extends SQLiteOpenHelper {
    private static Boolean initSetupData = false;
    private Context context;

    public DBManager(@Nullable Context context) {
        super(context, "N4_Foody.sqlite", null, 1);

        this.context = context;

        try {
            if (!initSetupData) {
                dropData();
                addData();
            }
            initSetupData = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    // Truy van k tra ket qua
    public void QueryData(String sql) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    // Truy van tra ket qua
    public Cursor GetData(String sql) {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void dropData() throws IOException {
        insertFromFile(R.raw.drop_table);

    }

    public void addData() throws IOException {
        insertFromFile( R.raw.create_table);
        insertFromFile( R.raw.add_data);
    }

    private int insertFromFile(int resourceId) throws IOException {
        // Resetting Counter
        int result = 0;

        // Open the resource
        InputStream insertsStream = this.context.getResources().openRawResource(resourceId);
        BufferedReader insertReader = new BufferedReader(new InputStreamReader(insertsStream));

        // Iterate through lines (assuming each insert has its own line and theres no other stuff)
        StringBuilder queryStmt = new StringBuilder();
        while (insertReader.ready()) {
            String line = insertReader.readLine().trim();
            queryStmt.append(line);
            if (line.contains(";") && line.indexOf(';') == line.length()-1) {
                QueryData(queryStmt.toString());
                queryStmt = new StringBuilder();
                result++;
            }
        }
        insertReader.close();

        // returning number of inserted rows
        return result;
    }
}
