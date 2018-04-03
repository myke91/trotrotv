package trotro.tv.trotrotv;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

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

    Brand mBrand;
    Question mQuestion;

    LinearLayout formQuestion;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBrand = new Brand(getContext());
        mQuestion = new Question(getContext());
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
    Spinner mAnswerSpinner;
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
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                mFormItemLayout.setLayoutParams(lp);
                mFormItemLayout.setWeightSum(2f);

                mQuestionText = new EditText(getContext());
                mQuestionText.setText(question.getQuestion());
                mQuestionText.setEnabled(false);
                mQuestionText.setTextColor(Color.BLACK);
                LinearLayout.LayoutParams lpQuestionText = (LinearLayout.LayoutParams) mFormItemLayout.getLayoutParams();
                lpQuestionText.weight = 1.7f;
                mQuestionText.setLayoutParams(lpQuestionText);
                mFormItemLayout.addView(mQuestionText);

                mAnswerSpinner = new Spinner(getContext());
                List<String> answers = new ArrayList<String>();
                answers.add("NO");
                answers.add("YES");
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, answers);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mAnswerSpinner.setAdapter(dataAdapter);
                LinearLayout.LayoutParams lpAnswerButton = (LinearLayout.LayoutParams) mFormItemLayout.getLayoutParams();
                lpAnswerButton.weight = 0.3f;
                mAnswerSpinner.setLayoutParams(lpAnswerButton);
                mFormItemLayout.addView(mAnswerSpinner);

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
                survey.setAnswer(((Spinner) child.getChildAt(1)).getSelectedItem().toString());
                survey.setBrand(getContext().getSharedPreferences(getString(R.string.preference_file), Context.MODE_PRIVATE).getString("currentBrand", ""));
                survey.setUser(getContext().getSharedPreferences(getString(R.string.preference_file), Context.MODE_PRIVATE).getString("user", ""));
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

    private void getBrands() {
        List<Brand> list = mBrand.getAllBrands();

        BrandListAdapter adapter = new BrandListAdapter(getActivity(), list);
        listBrands.setAdapter(adapter);
    }

    private List<Question> getQuestions() {
        return mQuestion.getQuestionsForSurvey();
    }

}
