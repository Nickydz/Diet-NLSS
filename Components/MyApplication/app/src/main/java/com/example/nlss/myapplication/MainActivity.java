package com.example.nlss.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] foods = {"Sweet cravings", "Emotional eating", "Eating out", "Grocery shopping"};
        ListAdapter buckysAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, foods);
        ListView buckysListView = (ListView) findViewById(R.id.buckysListView);
        buckysListView.setAdapter(buckysAdapter);

        buckysListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if (position == 0) {
                            //startActivity(new Intent(MainActivity.this, one.class));
                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                            builder.setMessage("1. Eating enough protein can also stabilize your blood sugar and help you feel more satisfied. \n\n" +
                                    "2. Eat a lollypop it lasts longer in your mouth and will satisfy your craving with minimal calorie. \n\n" +
                                    "3. Serotonin, a.k.a. the happiness hormone, can be raised through diet, exercise, and the right sleep schedule. \n\n" +"4. You may sometimes think that your body is asking for sugar, when in fact it's dehydrated and really craving water! \n\n"+
                                    "5. Eat several small, healthy Body Ecology meals throughout your day instead of three large portions to avoid dips in blood sugar. \n\n" +"6.Loaded with nutrition, green drinks help boost your energy and reduce cravings for sugar and processed foods.\n\n"+
                                    "7. Fermented foods and drinks are perhaps the most important way to reduce or even eliminate cravings for sugar. \n\n"+"8. Digestion starts in the mouth. Digestive enzymes start the process of breaking down starches into sugar. \n\n"+
                                    "9. Just hold back for 10 minutes. Cravings usually pass after 10 minutes. ")
                                    .setNegativeButton("OK", null)
                                    .create()
                                    .show();

                        } else if (position == 1) {
                            //startActivity(new Intent(MainActivity.this, two.class));
                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                            builder.setMessage("1. Track which situation leads to emotional eating and try to avoid such situations. \n\n"+
                            "2.	Listen to your favourite music – it’ll cheer you up. \n\n"+
                            "3.	Eating may bring temporary satisfaction, but it will ultimately leave you with unpleasant feelings you started out with. \n\n"+
                            "4.	Talking to a friend and getting moral support will be much helpful than eating. \n\n"+
                            "5.	Sip black tea. Subjects who drank black tea experienced a 47% drop in their cortisol levels, the stress hormone that makes you crave food. \n\n"+
                            "6.	Self-massage like rubbing your own foot slows your heart rate and lowers your level of cortisol. \n\n"+
                            "7.	Go for a walk or indulge in your favourite hobby, they will free your mind. \n\n"+
                            "8.	Brush your teeth and gargle! The lingering tastes of tempting food will disappear. \n\n")
                                    .setNegativeButton("OK", null)
                                    .create()
                                    .show();


                        }
                        else if (position == 2) {
                            //startActivity(new Intent(MainActivity.this,three.class));
                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                            builder.setMessage("1. Ask for it your way. For instance, ask for a smaller portion of the meat and a larger portion of the salad. \n\n"+
                              "2.Ask to triple the vegetables, please. When ordering, ask for three or four times the normal serving of veggies, and offer to pay extra. \n\n"+
                               "3. Ask how the food was prepared; don’t go by the menu. For instance, cholesterol-free does not mean fat-free; the dish could still be filled with calorie-dense oil. Neither does “lite” necessarily mean light in calories or fat. \n\n" +
                                "4.Eat slowly. It takes about 20 minutes for your brain to get the message from your stomach that you are no longer hungry. \n\n"+
                                 "5.Drink water before and during Your meal.One study showed that people on a diet who drank 500 ml (17 oz) of water half an hour before a meal ate fewer calories and lost 44% more weight than those who didn’t. ")
                                    .setNegativeButton("OK", null)
                                    .create()
                                    .show();

                        }
                        else if (position == 3) {
//                            startActivity(new Intent(MainActivity.this,four.class));
                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                            builder.setMessage("1. Be prepared for temptation. Remember to choose a healthy snack. \n\n"+
                            "2. Pick up a variety of fresh vegetables and fruits. \n\n"+"3. Don't come to grocery store hungry - eat a small healthy snack beforehand. \n\n"+
                             "4. Replace high-sugar foods with low-sugar alternatives. \n\n"+"5. Don't be tempted by those free food samples - just day no. \n\n"+
                               "6. Don't bring your kids! They'll talk you into buying sweets and snacks. \n\n"+ "7.Check the nutrition label on pack - you might be surprised.")
                                    .setNegativeButton("OK", null)
                                    .create()
                                    .show();

                        }
                        else if (position == 4) {
//                            startActivity(new Intent(MainActivity.this,five.class));
//                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//                            builder.setMessage()
//                                    .setNegativeButton("OK", null)
//                                    .create()
//                                    .show();

                        }
                        else if (position == 5) {
//                            startActivity(new Intent(MainActivity.this,six.class));
//                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//                            builder.setMessage()
//                                    .setNegativeButton("OK", null)
//                                    .create()
//                                    .show();

                        }
                        else if (position == 6) {
//                            startActivity(new Intent(MainActivity.this,seven.class));
//                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//                            builder.setMessage()
//                                    .setNegativeButton("OK", null)
//                                    .create()
//                                    .show();

                        }
                        else if (position == 7) {
//                            startActivity(new Intent(MainActivity.this,eight.class));
//                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//                            builder.setMessage()
//                                    .setNegativeButton("OK", null)
//                                    .create()
//                                    .show();

                        }
                        else if (position == 8) {
//                            startActivity(new Intent(MainActivity.this,nine.class));
//                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//                            builder.setMessage()
//                                    .setNegativeButton("OK", null)
//                                    .create()
//                                    .show();

                        }
                        else if (position == 9) {
//                            startActivity(new Intent(MainActivity.this,ten.class));
//                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//                            builder.setMessage()
//                                    .setNegativeButton("OK", null)
//                                    .create()
//                                    .show();

                        }

                    }
                }
        );
    }

}
