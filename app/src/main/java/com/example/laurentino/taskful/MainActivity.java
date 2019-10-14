package com.example.laurentino.taskful;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private TextView footer;
    private EditText editText;
    private EditText editText2;
    private CheckBox checkBox;
    private Spinner spinner;
    private int count = 0; //contagem de tarefas concluídas

    private ArrayList<Tarefa> tarefas;
    private ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.listview);
        spinner = (Spinner) findViewById(R.id.spinner);
        editText = (EditText)findViewById(R.id.edittext);
        editText2 = (EditText)findViewById(R.id.edittext2);
        checkBox = (CheckBox) findViewById(R.id.checkbox);
        footer = (TextView) findViewById(R.id.footer);

        tarefas = new ArrayList<>();

        arrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,
                tarefas);
        listView.setAdapter(arrayAdapter);

        AdapterView.OnItemLongClickListener itemClickListener = new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> listview,
                                           View view,
                                           int position,
                                           long id){
                final int localPosition = position;

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Concluir tarefa")
                        .setMessage("Você realmente deseja concluir a tarefa selecionada?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int whichButton) {

                                tarefas.remove(localPosition);
                                arrayAdapter.notifyDataSetChanged();
                                count++;
                                updateFooter();

                            }
                        })
                        .setNegativeButton(android.R.string.no, null).show();

                return true;

            }
        };
        listView.setOnItemLongClickListener((AdapterView.OnItemLongClickListener) itemClickListener);

    }

    public void updateFooter(){
        int total = tarefas.size();
        int urgente = 0;
        for (int i = 0; i < tarefas.size(); i++) {
            Tarefa tarefa = tarefas.get(i);
            if (tarefa.isUrgente()){
                urgente ++;
            }
        }

        footer.setText("Tarefas = " + total + " : Urgentes = " + urgente + " : Concluídas = " + count);
    }

    public void addTarefa(View view) {
        String task = editText.getText().toString();
        String descrito = editText2.getText().toString();
        boolean checked = checkBox.isChecked();
        String item = String.valueOf(spinner.getSelectedItem());

        Tarefa tarefa = new Tarefa (task, descrito, checked, item);
        tarefas.add(tarefa);
        arrayAdapter.notifyDataSetChanged();
        updateFooter();
        Toast toast = Toast.makeText(this, "Tarefa adicionada", Toast.LENGTH_SHORT);
        toast.show();
    }

}
