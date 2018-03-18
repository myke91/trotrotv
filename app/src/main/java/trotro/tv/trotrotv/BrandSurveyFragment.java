package trotro.tv.trotrotv;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

import trotro.tv.trotrotv.adapter.BrandListAdapter;
import trotro.tv.trotrotv.model.Brand;
import trotro.tv.trotrotv.model.Question;
import trotro.tv.trotrotv.model.Survey;


/**
 * A simple {@link Fragment} subclass.
 */
public class BrandSurveyFragment extends Fragment {
    ListView listBrands;
    TextView mBrandName;

    LinearLayout formQuestion;

    public BrandSurveyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.fragment_brand_survey, container, false);
        listBrands = layout.findViewById(R.id.list_brands);
        listBrands.setOnItemClickListener(itemClickListener);
        mButtonSave = layout.findViewById(R.id.button_save);
        formQuestion = layout.findViewById(R.id.form_question);
        mBrandName = layout.findViewById(R.id.brand_name);
        getBrands();

        return layout;
    }

    LinearLayout mFormItemLayout;
    EditText mQuestionText;
    ToggleButton mAnswerButton;
    Button mButtonSave;

    AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            Brand brand = (Brand) listBrands.getItemAtPosition(position);
            getContext().getSharedPreferences(getString(R.string.preference_file), Context.MODE_PRIVATE).edit().putString("currentBrand", brand.getBrandName()).apply();
            List<Question> list = getQuestions();
            formQuestion.removeAllViews();
            for (Question question : list) {
                mFormItemLayout = new LinearLayout(getContext());
                mFormItemLayout.setOrientation(LinearLayout.HORIZONTAL);
                mFormItemLayout.setWeightSum(2);

                mQuestionText = new EditText(getContext());
                mQuestionText.setText(question.getQuestion());
                mQuestionText.setEnabled(false);
                mQuestionText.setTextColor(Color.BLACK);
                mFormItemLayout.addView(mQuestionText);

                mAnswerButton = new ToggleButton(getContext());
                mAnswerButton.setTextOff("NO");
                mAnswerButton.setTextOn("YES");
                mAnswerButton.setText("NO");
                mFormItemLayout.addView(mAnswerButton);

                formQuestion.addView(mFormItemLayout);
            }

            mBrandName.setText(getContext().getSharedPreferences(getString(R.string.preference_file), Context.MODE_PRIVATE).getString("currentBrand", ""));
            mBrandName.setVisibility(View.VISIBLE);

            mButtonSave.setOnClickListener(saveClickListener);
            mButtonSave.setVisibility(View.VISIBLE);

            listBrands.setVisibility(View.GONE);
            formQuestion.setVisibility(View.VISIBLE);

        }
    };

    View.OnClickListener saveClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            for (int i = 0; i < formQuestion.getChildCount(); i++) {
//                try {
                Survey survey = new Survey(getContext());
                LinearLayout child = (LinearLayout) formQuestion.getChildAt(i);
                survey.setQuestion(((EditText) child.getChildAt(0)).getText().toString());
                survey.setAnswer(((ToggleButton) child.getChildAt(1)).getText().toString());
                survey.setBrand(getContext().getSharedPreferences(getString(R.string.preference_file), Context.MODE_PRIVATE).getString("currentBrand", ""));
                survey.saveSurvey(survey);

                formQuestion.setVisibility(View.GONE);
                mButtonSave.setVisibility(View.GONE);
                mBrandName.setVisibility(View.GONE);

                listBrands.setVisibility(View.VISIBLE);

//                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//                    builder.setMessage("SAVE SUCCESSFUL");
//                    builder.setTitle(R.string.app_name);
//                    AlertDialog dialog = builder.create();
//                    dialog.show();
//                } catch (Exception ex) {
//                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//                    builder.setMessage("ERROR OCCURED WHILST SAVING SURVEY");
//                    builder.setTitle(R.string.app_name);
//                    AlertDialog dialog = builder.create();
//                    dialog.show();
//                }
            }
        }
    };

    private List<Brand> getBrands() {
        List<Brand> list = new ArrayList();

        Brand brand = new Brand(getContext());
        brand.setLocation("STARBITE");
        brand.setBrandName("STARBITE");
        list.add(brand);

        brand = new Brand(getContext());
        brand.setLocation("GOLDEN TULIP");
        brand.setBrandName("GOLDEN TULIP");
        list.add(brand);

        brand = new Brand(getContext());
        brand.setLocation("SHELL");
        brand.setBrandName("SHELL");
        list.add(brand);

        BrandListAdapter adapter = new BrandListAdapter(getActivity(), list);
        listBrands.setAdapter(adapter);

        return list;

    }

    private List<Question> getQuestions() {
        List<Question> list = new ArrayList<Question>();
        Question que = new Question(getContext());
        que.setQuestion("Is the screen working?");
        list.add(que);

        que = new Question(getContext());
        que.setQuestion("Does it start with key?");
        list.add(que);

        que = new Question(getContext());
        que.setQuestion("Is driver happy?");
        list.add(que);


        return list;

    }

}
