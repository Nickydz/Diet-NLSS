package com.example.nickydcruz.loginregister;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class superfood_main extends AppCompatActivity {

    SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_superfoods);
        pref = getSharedPreferences("login.conf", Context.MODE_PRIVATE);

        String[] foods = {"Proteins", "Calcium", "Carbohydrates", "Miscellaneous"};
        ListAdapter buckysAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, foods);
        ListView buckysListView = (ListView) findViewById(R.id.supfood);
        buckysListView.setAdapter(buckysAdapter);

        buckysListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if (position == 0) {
                            //startActivity(new Intent(MainActivity.this, one.class));
                            AlertDialog.Builder builder = new AlertDialog.Builder(superfood_main.this);
                            builder.setMessage("1. Eggs – Each egg has 6 grams of protein but just 72 calories. \n\n"+
                                    "2. Spirulina-  Highest form of protein found anywhere in the world (70%). This superfood is a great recommendation for those seeking to lose weight and maintaining great health. \n\n"+
                                    "3. Bee Pollen - These granules created by bees from flowering plants is another nutrient dense food that has 5 to 7 times more protein than beef. \n\n"+
                                    "4. Chia Seeds - this is a perfect food, containing essential fatty acids, protein, as well as being a soluble fiber.\n\n"+
                                    "5. Almonds - They are loaded with important nutrients, including fiber, vitamin E, manganese and magnesium.\n\n"+
                                    "6. Chicken Breast - Chicken breast is one of the most popular protein-rich foods.If you eat it without the skin, the majority of the calories in it come from protein.\n\n"+
                                    "7. Oats - They are also loaded with healthy fibers, magnesium, manganese, thiamin (vitamin B1) and several other nutrients.\n\n"+
                                    "8. Cottage cheese - Cottage cheese is a type of cheese that tends to be very low in fat and calories. It is loaded with calcium.\n\n"+
                                    "9. Whey protein – It is exceptionally rich in both calcium and proteins.\n\n")
                                    .setNegativeButton("OK", null)
                                    .create()
                                    .show();

                        } else if (position == 1) {
                            //startActivity(new Intent(MainActivity.this, two.class));
                            AlertDialog.Builder builder = new AlertDialog.Builder(superfood_main.this);
                            builder.setMessage("1. Cheese - Most cheeses are excellent sources of calcium. Parmesan cheese has the most, with 331 mg.\n\n" +
                                    "2. Yogurt - Yogurt is an excellent source of calcium.Many types of yogurt are also rich in live probiotic bacteria, which have various health benefits.\n\n" +
                                    "3. Sardines and canned salmon - Sardines and canned salmon are loaded with calcium, thanks to their edible bones. These oily fish also provide high-quality protein and omega-3 fatty acids, which are good for your heart, brain and skin.\n\n" +
                                    "4. Almonds - Of all the nuts, almonds are the highest in calcium.\n\n" +
                                    "5. Whey protein – It is exceptionally rich in both calcium and proteins.\n\n" +
                                    "6. Some leafy greens - Dark, leafy greens are incredibly healthy, and some of them are high in calcium like spinach.\n\n" +
                                    "7. Milk - Milk is one of the best and cheapest calcium sources. The calcium in dairy is also absorbed well.\n\n")
                                    .setNegativeButton("OK", null)
                                    .create()
                                    .show();


                        }
                        else if (position == 2) {
                            //startActivity(new Intent(MainActivity.this,three.class));
                            AlertDialog.Builder builder = new AlertDialog.Builder(superfood_main.this);
                            builder.setMessage("1. Quinoa - Cooked quinoa is 21.3% carbs, making it a high-carb food. However, it is also a good source of protein and fiber.\n\n"+
                                    "2. Banana - They are made up of about 23% carbs, either in the form of starches or sugars.They are also high in potassium, vitamin B6 and vitamin C.\n\n"+
                                    "3. Sweet potatoes - Cooked sweet potatoes contain about 18–21% carbs. This carb content consists of starch, sugar and fiber.\n\n"+
                                    "4. Beetroots - Raw and cooked beets contain about 8–10% carbs, made up of sugar and fiber. They are packed with vitamins, minerals, potent antioxidants and plant compounds.\n\n"+
                                    "5. Oranges - They are mainly composed of water and contain 11.8% carbs. Oranges are also a good source of fiber.\n\n"+
                                    "6. Blueberries - Blueberries are incredibly delicious. They consist mostly of water, as well as about 14.5% carbs.\n\n"+
                                    "7. Kidney beans - Cooked kidney beans contain 22.8% carbs, in the form of starches and fiber. They are also high in protein.\n\n")
                                    .setNegativeButton("OK", null)
                                    .create()
                                    .show();

                        }
                        else if (position == 3) {
//                            startActivity(new Intent(MainActivity.this,four.class));
                            AlertDialog.Builder builder = new AlertDialog.Builder(superfood_main.this);
                            builder.setMessage("1. Tomato Sauce - It's loaded with lycopene, which makes your skin look younger and keeps your heart healthy.\n\n"+
                                    "2. Dried plums – They’re packed with polyphenols, plant chemicals that have been shown to boost bone density by stimulating your bone-building cells. \n\n"+
                                    "3. Walnuts – Just 14 walnuts halves provide more than twice your daily dose of alpha-linolenic acid, an omega-3 fat that’s been shown to improve memory and co-ordination.\n\n"+
                                    "4. Apples – They contain quercetin, an antioxidant that may reduce your risk of lung cancer.\n\n"+
                                    "5. Avocados – Their healthy fat keeps you satisfied and helps you absorb other nutrients.\n\n"+
                                    "6. Pumpkins – It’s filled with natural cancer fighters alpha and beta carotene.\n\n"+
                                    "7. Collard greens – They’re exploding with nutrients like vitamin A, which keeps your eyes healthy.\n\n"+
                                    "8. Brown rice – It’s a top source of magnesium, a mineral your body uses for more than 300 chemical reactions.\n\n"+
                                    "9. Oysters – These keep your immune system strong. A 3-oz serving i.e about 6 oysters dishes up a quarter of your iron.\n\n"+
                                    "10. Strawberries -  They are loaded with antioxidants thatmay halt the growth of cervical and colon cancers.\n\n"+
                                    "11. Bran flakes – Their whole grains keep your heart in tip-top shape by reducing inflammation and melting away belly fat.\n\n"+
                                    "12. Kiwi fruit – Researchers have found that it reduces asthma-related wheezing, thanks to it’s high vitamin C content.\n\n"+
                                    "13. Sunflower seeds – A quarter cup delivers half your day’s vitamin E, which keeps heart healthy and fights infection.\n\n"+
                                    "14. Asparagus – A half cup supplies 50% of your daily bone-building vitamin K and a third of your days folate which banishes bloating.\n\n"+
                                    "15. Tea: green and black tea – They prevent hardeninig of the arteries.\n\n"+
                                    "16. Blackberries – The king of all berries which boasts more antioxidants than any other berries.\n\n"+
                                    "17. Brazil nuts – They have more selenium than any other food.\n\n"+
                                    "18. Barley – A top source of beta-glucan, a fibre that lowers cholesterol and helps control blood.\n\n"+
                                    "19. Mushrooms – One serving provides as much vitamin D as you’d get from a glass of milk.\n\n"  )
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_homescreen_actions,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i = new Intent(getApplicationContext(),Homescreen.class);
        switch (item.getItemId()) {
            case R.id.foodcravings: i = new Intent(getApplicationContext(), FoodCravings.class);
                break;

            case R.id.superfood: i = new Intent(getApplicationContext(), superfood_main.class);
                break;

            case R.id.excal: i = new Intent(getApplicationContext(), ExerciseCalculator.class);
                break;

            case R.id.diet: i = new Intent(getApplicationContext(), Homescreen.class);
                break;

            case R.id.advanceSurvey: i = new Intent(getApplicationContext(), Advanced_Survey.class);
                break;
            case R.id.logout: {
                pref.edit().clear().commit();
                i = new Intent(getApplicationContext(), LoginActivity.class);
                break;
            }

        }
        superfood_main.this.startActivity(i);
        return super.onOptionsItemSelected(item);
    }

}
