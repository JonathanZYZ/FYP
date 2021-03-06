package com.myapplicationdev.android.fyp.Scoreboards;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Environment;
import android.text.Html;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.ajts.androidmads.library.SQLiteToExcel;
import com.myapplicationdev.android.fyp.Models.ScoreBoard;
import com.myapplicationdev.android.fyp.R;
import com.myapplicationdev.android.fyp.Utilities.DBHelper;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.ArrayList;

public class ShowScoreboardActivity<SqliteToExcel> extends AppCompatActivity {

    TextView tvHeading, tvMode, tvUsername;
    Button btnFilter, btnShowAll, btnExcel;
    ListView lvScoreBoardData;
    ArrayList<ScoreBoard> al, filterAL;
    ArrayList<String> spinnerUsernameAL, spinnerModeAL;
    ScoreboardAdapter aa;
    ArrayAdapter spinnerUsernameAA, spinnerModeAA;
    Spinner spUsername, spMode;
    String folderLocation = Environment.getExternalStorageDirectory().getPath() + "/Backup/";
    DBHelper dbh;
    File folder;
    SqliteToExcel sqliteToExcel;
    private File filePath = new File(Environment.getExternalStorageDirectory() + "/Demo.xls");


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_show);
        setContentView(R.layout.activity_show_scoreboard);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // TODO: At the start of the ShowScoreboardActivity initialize() method,
        //  initialize all UI variables and other objects.
        tvHeading = findViewById(R.id.tvHeading);
        tvMode = findViewById(R.id.tvMode);
        tvUsername = findViewById(R.id.tvUsername);
        btnShowAll = findViewById(R.id.btnShowAll);
        btnFilter = findViewById(R.id.btnFilter);
        btnExcel = findViewById(R.id.btnExcel);
        lvScoreBoardData = findViewById(R.id.lvScoreBoardData);
        spUsername = findViewById(R.id.spUsername);
        spMode = findViewById(R.id.spMode);


        // setting of TextView
        tvMode.setText(Html.fromHtml("<u>Select user by mode:</u>"));
        tvUsername.setText(Html.fromHtml("<u>Select user by username:</u>"));


        // TODO: setting of SQLite to Excel ...


        String[] permission = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
        ActivityCompat.requestPermissions(ShowScoreboardActivity.this, permission, 0);
        String folderLocation_I = getFilesDir().getAbsolutePath() + "/Folder";
        File folder_I = new File(folderLocation_I);
        if (!folder_I.exists()) {
            boolean result = folder_I.mkdir();
            if (result) {
                Log.d("File Read/Write", "Folder created");
            }
        }
        btnExcel.setOnClickListener(v -> {
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
            HSSFSheet hssfSheet = hssfWorkbook.createSheet("Custom Sheet");


            HSSFRow hssfRow = hssfSheet.createRow(0);
            HSSFCell hssfCell = hssfRow.createCell(0);
            hssfCell.setCellValue("ID");

            HSSFCell hssfCell1 = hssfRow.createCell(1);
            hssfCell1.setCellValue("Username");

            HSSFCell hssfCell2 = hssfRow.createCell(2);
            hssfCell2.setCellValue("Score");

            HSSFCell hssfCell3 = hssfRow.createCell(3);
            hssfCell3.setCellValue("Mode");

            HSSFCell hssfCell4 = hssfRow.createCell(4);
            hssfCell4.setCellValue("Date");

            al = dbh.getAllScoreBoard();
            for (int i = 1; i < al.size() + 1; i++) {
                HSSFRow hssfRow1 = hssfSheet.createRow(i);
                HSSFCell hssfCells1 = hssfRow1.createCell(0);
                Log.d("TEST123", al.get(i - 1).getId() + " TEST");
                hssfCells1.setCellValue(al.get(i - 1).getId());

                HSSFCell hssfCells2 = hssfRow1.createCell(1);
                Log.d("TEST123", al.get(i - 1).getUsername() + " TEST");
                hssfCells2.setCellValue(al.get(i - 1).getUsername());

                HSSFCell hssfCells3 = hssfRow1.createCell(2);
                Log.d("TEST123", al.get(i - 1).getId() + " TEST");
                hssfCells3.setCellValue(al.get(i - 1).getScore());

                HSSFCell hssfCells4 = hssfRow1.createCell(3);
                Log.d("TEST123", al.get(i - 1).getId() + " TEST");
                hssfCells4.setCellValue(al.get(i - 1).getMode());

                HSSFCell hssfCells5 = hssfRow1.createCell(4);
                Log.d("TEST123", al.get(i - 1).getId() + " TEST");
                hssfCells5.setCellValue(al.get(i - 1).getDate());
            }

            try {
                String folderLocation =
                        Environment.getExternalStorageDirectory()
                                .getAbsolutePath() + "/Folder";
                File folder = new File(folderLocation);
                if (!folder.exists()) {
                    boolean result = folder.mkdir();
                    if (result) {
                        Log.d("File Read/Write", "Folder created");
                    }
                }
                try {
                    folderLocation = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Folder";
                    File targetFile = new File(folderLocation, "data.xls");


                    FileOutputStream fileOutputStream = new FileOutputStream(targetFile, true);
                    hssfWorkbook.write(fileOutputStream);

                    fileOutputStream.flush();
                    fileOutputStream.close();

                    Toast.makeText(ShowScoreboardActivity.this,
                            "Successfully created excel file",
                            Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(ShowScoreboardActivity.this, "Failed to write!", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        /*================================================================================== */


        btnShowAll.setOnClickListener(v -> {
            tvHeading.setText("ScoreBoard (Total Users)");
            al = dbh.getAllScoreBoard();
            if (!al.isEmpty()) {

                filterAL.clear();
                filterAL.addAll(al);
                aa = new ScoreboardAdapter(this, R.layout.scoreboard_row, filterAL);
                lvScoreBoardData.setAdapter(aa);

                Toast.makeText(ShowScoreboardActivity.this,
                        "The total number of users who have played the game is shown above.",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(ShowScoreboardActivity.this,
                        "Because you haven't played the game yet, " +
                                "there will be no data displayed in the scoreboard.",
                        Toast.LENGTH_SHORT).show();
            }

            aa.notifyDataSetChanged();
        });


        btnFilter.setOnClickListener(view -> {

            tvHeading.setText("ScoreBoard (The Top Scorer)");
            al = dbh.getAllScoreBoard();
            filterAL.clear();
            int dbLength = al.size();


            if (!al.isEmpty()) {


                //finds the highest value
                String topScore = al.get(0).getScore();

                for (ScoreBoard i : al) {
                    if (Integer.parseInt(i.getScore()) > Integer.parseInt(topScore)) {
                        topScore = i.getScore();
                        filterAL.add(i);
                    }
                }

                aa = new ScoreboardAdapter(this, R.layout.scoreboard_row, filterAL);
                lvScoreBoardData.setAdapter(aa);

                Toast.makeText(ShowScoreboardActivity.this,
                        "Here is the user with the highest score out of all users.",
                        Toast.LENGTH_SHORT).show();


            } else {
                Toast.makeText(ShowScoreboardActivity.this,
                        "Because you haven't played the game yet," +
                                " there will be no data displayed in the scoreboard.",
                        Toast.LENGTH_SHORT).show();
            }

            aa.notifyDataSetChanged();
        });

        lvScoreBoardData.setOnItemClickListener((parent, view, position, id) -> {
            Intent i = new Intent(ShowScoreboardActivity.this, ModifyScoreboardActivity.class);
            i.putExtra("Scoreboard", filterAL.get(position));
            startActivity(i);
        });

        spUsername.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

                ((TextView) adapterView.getChildAt(0)).setTypeface(null, Typeface.BOLD);
                ((TextView) adapterView.getChildAt(0)).setTextColor(Color.BLACK);
                ((TextView) adapterView.getChildAt(0)).setTextSize(16);


                al = dbh.getAllScoreBoard();
                filterAL.clear();


                String selected = spinnerUsernameAL.get(position);


                for (ScoreBoard i : al) {
                    if (i.getUsername().equalsIgnoreCase(selected)) {
                        filterAL.add(i);
                    }


                    String tvHeadingOutput = String.format("ScoreBoard (User: %s)", selected);
                    tvHeading.setText(tvHeadingOutput);
                }
                aa = new ScoreboardAdapter(ShowScoreboardActivity.this, R.layout.scoreboard_row, filterAL);
                lvScoreBoardData.setAdapter(aa);
                aa.notifyDataSetChanged();

                Toast.makeText(ShowScoreboardActivity.this,
                        "Here are the stats for user " + selected + " in the game.",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                aa.notifyDataSetChanged();
            }
        });

        spMode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

                ((TextView) adapterView.getChildAt(0)).setTypeface(null, Typeface.BOLD);
                ((TextView) adapterView.getChildAt(0)).setTextColor(Color.BLACK);
                ((TextView) adapterView.getChildAt(0)).setTextSize(16);


                al = dbh.getAllScoreBoard();
                filterAL.clear();


                String selected = spinnerModeAL.get(position);

                for (ScoreBoard i : al) {
                    if (i.getMode().equalsIgnoreCase(selected)) {
                        filterAL.add(i);
                    }

                    String tvHeadingOutput = String.format("ScoreBoard (%s level)", selected);
                    tvHeading.setText(tvHeadingOutput);

                }
                aa = new ScoreboardAdapter(ShowScoreboardActivity.this, R.layout.scoreboard_row, filterAL);
                lvScoreBoardData.setAdapter(aa);
                aa.notifyDataSetChanged();

                Toast.makeText(ShowScoreboardActivity.this,
                        "Here are all of the " + selected + " mode users.",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                aa.notifyDataSetChanged();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        // TODO: Sets the data for ListView
        dbh = new DBHelper(ShowScoreboardActivity.this);
        al = dbh.getAllScoreBoard();
        filterAL = al;
        aa = new ScoreboardAdapter(this, R.layout.scoreboard_row, al);
        lvScoreBoardData.setAdapter(aa);


        // TODO: Sets the SpinnerAdapter used to provide the data which backs spinnerUsernameAL.
        spinnerUsernameAL = new ArrayList<>();

        for (ScoreBoard i : al) {
            spinnerUsernameAL.add(i.getUsername());
        }
        spinnerUsernameAA = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerUsernameAL);
        spinnerUsernameAA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spUsername.setAdapter(spinnerUsernameAA);

        // TODO: Sets the SpinnerAdapter used to provide the data which backs spinnerModeAA.
        spinnerModeAL = new ArrayList<>();
        spinnerModeAL.add("basic");
        spinnerModeAL.add("intermediate");
        spinnerModeAL.add("advanced");

        spinnerModeAA = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerModeAL);
        spinnerModeAA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spMode.setAdapter(spinnerModeAA);

    }


}