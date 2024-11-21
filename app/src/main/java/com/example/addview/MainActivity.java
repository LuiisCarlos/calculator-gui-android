package com.example.addview;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.addview.classes.Operation;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    private Operation operation = new Operation();

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.clearBtn) {
            viewPanel.setText("");
            operation = new Operation();
            setBackgroundButtonColors(0xFFBB8532);
        }  else if(v.getId() == R.id.negationBtn) {
            String view = viewPanel.getText().toString();
            if (!view.isEmpty() && !view.equals("Error")) {
                double currentNumber = Double.parseDouble((String) viewPanel.getText());
                double newNumber;
                if (currentNumber >= 0) {
                    newNumber = currentNumber * -1;
                } else {
                    newNumber = Math.abs(currentNumber);
                }
                viewPanel.setText(String.valueOf(newNumber));
            }
        } else if (v.getId() == R.id.porcentageBtn) {
            String view = viewPanel.getText().toString();
            if (!view.isEmpty() && !view.equals("Error")) {
                double currentNumber = Double.parseDouble((String) viewPanel.getText());
                double newNumber = currentNumber / 100;
                viewPanel.setText(String.valueOf(newNumber));
            }
        } else if (v.getId() == R.id.divideBtn) {
            setValueOnOperation();
            if (operation.getX() != null || operation.getY() != null) {
                setBackgroundButtonColors(0xFFBB8532);
                divideBtn.setBackgroundColor(Color.WHITE);
                divideBtn.setTextColor(Color.BLACK);
                operation.setOperator('/');
                viewPanel.setText("");
            }
        } else if (v.getId() == R.id.multiplyBtn) {
            setValueOnOperation();
            if (operation.getX() != null || operation.getY() != null) {
                setBackgroundButtonColors(0xFFBB8532);
                multiplyBtn.setBackgroundColor(Color.WHITE);
                multiplyBtn.setTextColor(Color.BLACK);
                operation.setOperator('x');
                viewPanel.setText("");
            }
        } else if (v.getId() == R.id.subtractBtn)  {
            setValueOnOperation();
            if (operation.getX() != null || operation.getY() != null) {
                setBackgroundButtonColors(0xFFBB8532);
                subtractBtn.setBackgroundColor(Color.WHITE);
                subtractBtn.setTextColor(Color.BLACK);
                operation.setOperator('-');
                viewPanel.setText("");
            }
        } else if (v.getId() == R.id.sumBtn) {
            setValueOnOperation();
            if (operation.getX() != null || operation.getY() != null) {
                setBackgroundButtonColors(0xFFBB8532);
                sumBtn.setBackgroundColor(Color.WHITE);
                sumBtn.setTextColor(Color.BLACK);
                operation.setOperator('+');
                viewPanel.setText("");
            }
        } else if (v.getId() == R.id.nineBtn) {
            setNumberInViewPanel("9");
        } else if (v.getId() == R.id.eightBtn) {
            setNumberInViewPanel("8");
        } else if (v.getId() == R.id.sevenBtn) {
            setNumberInViewPanel("7");
        } else if (v.getId() == R.id.sixBtn) {
            setNumberInViewPanel("6");
        } else if (v.getId() == R.id.fiveBtn) {
            setNumberInViewPanel("5");
        } else if (v.getId() == R.id.fourBtn) {
            setNumberInViewPanel("4");
        } else if (v.getId() == R.id.threeBtn) {
            setNumberInViewPanel("3");
        } else if (v.getId() == R.id.twoBtn) {
            setNumberInViewPanel("2");
        } else if (v.getId() == R.id.oneBtn) {
            setNumberInViewPanel("1");
        } else if (v.getId() == R.id.zeroBtn) {
            setNumberInViewPanel("0");
        } else if (v.getId() == R.id.commaBtn) {
            setNumberInViewPanel(".");
        } else if(v.getId() == R.id.equalsBtn) {
            String viewPanelContent = viewPanel.getText().toString();
            if (!viewPanelContent.equals("Error")) {
                setValueOnOperation();
                if (operation.getX() != null && operation.getY() != null && operation.getOperator() != null) {
                    String result = operation.resolve();
                    viewPanel.setText(result);
                    operation.setX(Double.parseDouble(result));
                    operation.setY(null);
                } else {
                    viewPanel.setText("Error");
                }
                setBackgroundButtonColors(0xFFBB8532);
            }
        }
    }

    private void setNumberInViewPanel(String number) {
        String viewPanelContent = viewPanel.getText().toString();
        if (number.equals(".")) {
            if (viewPanelContent.isEmpty()) {
                viewPanel.setText("0.");
            } else if (!viewPanelContent.contains(".")) {
                viewPanel.setText(viewPanelContent + number);
            }
        } else if (viewPanelContent.isEmpty() || viewPanelContent.equals("Error")) {
            viewPanel.setText(number);
        } else {
            viewPanel.setText(viewPanelContent + number);
        }
    }

    private void setValueOnOperation() {
        String view = viewPanel.getText().toString();
        if (operation.getX() == null) {
            if (!view.isEmpty() && !view.equals("Error")) {
                operation.setX(Double.parseDouble((String) viewPanel.getText()));
            }
        } else {
            if (!view.isEmpty() ) {
                operation.setY(Double.parseDouble((String) viewPanel.getText()));
            }
        }
    }

    private void setBackgroundButtonColors(int color) {
        multiplyBtn.setBackgroundColor(color);
        multiplyBtn.setTextColor(Color.WHITE);
        sumBtn.setBackgroundColor(color);
        sumBtn.setTextColor(Color.WHITE);
        subtractBtn.setBackgroundColor(color);
        subtractBtn.setTextColor(Color.WHITE);
        divideBtn.setBackgroundColor(color);
        divideBtn.setTextColor(Color.WHITE);
        equalsBtn.setBackgroundColor(color);
        equalsBtn.setTextColor(Color.WHITE);
    }

    private void initView() {
        viewPanel.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        getComponents();
        initView();
    }

    public void getComponents() {
        viewPanel = findViewById(R.id.viewPanel);
        clearBtn = findViewById(R.id.clearBtn);
        clearBtn.setOnClickListener(this);
        negationBtn = findViewById(R.id.negationBtn);
        negationBtn.setOnClickListener(this);
        porcentageBtn = findViewById(R.id.porcentageBtn);
        porcentageBtn.setOnClickListener(this);
        divideBtn = findViewById(R.id.divideBtn);
        divideBtn.setOnClickListener(this);
        multiplyBtn = findViewById(R.id.multiplyBtn);
        multiplyBtn.setOnClickListener(this);
        sumBtn = findViewById(R.id.sumBtn);
        sumBtn.setOnClickListener(this);
        subtractBtn = findViewById(R.id.subtractBtn);
        subtractBtn.setOnClickListener(this);
        nineBtn = findViewById(R.id.nineBtn);
        nineBtn.setOnClickListener(this);
        eightBtn = findViewById(R.id.eightBtn);
        eightBtn.setOnClickListener(this);
        sevenBtn = findViewById(R.id.sevenBtn);
        sevenBtn.setOnClickListener(this);
        sixBtn = findViewById(R.id.sixBtn);
        sixBtn.setOnClickListener(this);
        fiveBtn = findViewById(R.id.fiveBtn);
        fiveBtn.setOnClickListener(this);
        fourBtn = findViewById(R.id.fourBtn);
        fourBtn.setOnClickListener(this);
        threeBtn = findViewById(R.id.threeBtn);
        threeBtn.setOnClickListener(this);
        twoBtn = findViewById(R.id.twoBtn);
        twoBtn.setOnClickListener(this);
        oneBtn = findViewById(R.id.oneBtn);
        oneBtn.setOnClickListener(this);
        zeroBtn = findViewById(R.id.zeroBtn);
        zeroBtn.setOnClickListener(this);
        commaBtn = findViewById(R.id.commaBtn);
        commaBtn.setOnClickListener(this);
        equalsBtn = findViewById(R.id.equalsBtn);
        equalsBtn.setOnClickListener(this);
    }

    // Components
    private TextView viewPanel;
    private Button clearBtn;
    private Button porcentageBtn;
    private Button divideBtn;
    private Button multiplyBtn;
    private Button sumBtn;
    private Button subtractBtn;
    private Button negationBtn;
    private Button nineBtn;
    private Button eightBtn;
    private Button sevenBtn;
    private Button sixBtn;
    private Button fiveBtn;
    private Button fourBtn;
    private Button threeBtn;
    private Button twoBtn;
    private Button oneBtn;
    private Button zeroBtn;
    private Button commaBtn;
    private Button equalsBtn;
}