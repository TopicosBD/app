package com.example.saba.faculdadev1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DisciplinaActivity extends Activity {
    protected EditText editTextDisciplin;
    protected  DisciplinaValue disciplinaSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disciplina);

        Button button = (Button) findViewById(R.id.botao);

        this.editTextDisciplin = (EditText) findViewById(R.id.disciplina);


        Intent intent = getIntent();
        disciplinaSelecionada = (DisciplinaValue)     intent.getSerializableExtra("disciplinaSelecionada");
        if(disciplinaSelecionada!=null) {
            button.setText("Alterar");
            editTextDisciplin.setText(disciplinaSelecionada.getTipo());
        }


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                DisciplinaDAO dao = new DisciplinaDAO(DisciplinaActivity.this);
                if (disciplinaSelecionada==null) {
                    DisciplinaValue disciplinaValue = new DisciplinaValue();
                    disciplinaValue.setTipo(editTextDisciplin.getText().toString());
                    dao.salvar(disciplinaValue);
                }else{
                    disciplinaSelecionada.setTipo(editTextDisciplin.getText().toString());
                    dao.alterar(disciplinaSelecionada);

                }

                finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_disciplina, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
