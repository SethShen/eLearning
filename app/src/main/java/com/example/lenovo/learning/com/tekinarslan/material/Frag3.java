package com.example.lenovo.learning.com.tekinarslan.material;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.lenovo.learning.Frag4;
import com.example.lenovo.learning.R;
import com.example.lenovo.learning.SampleActivity;
import com.example.lenovo.learning.com.tekinarslan.material.musicList.MusicListFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017/3/20.
 */

public class Frag3 extends Activity {
    private Map<String,String> speakData  = new HashMap<String,String>();
    private List<String> titles = new ArrayList<>();
    private ListView ls;
    private SampleActivity sampleActivity;
    private Frag4 frag4;
    private MusicListFragment musicListFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_speak);
        ls = (ListView)findViewById(R.id.myListView);
        getDatas();
        setListView();

    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.main:
                if(sampleActivity==null){
                    sampleActivity=new SampleActivity();
                }
                Intent intent = new Intent(this,sampleActivity.getClass());
                startActivity(intent);
                break;
            case R.id.listen:
                if (musicListFragment == null) {
                    musicListFragment = new MusicListFragment();
                }
                intent = new Intent(this,musicListFragment.getClass());
                startActivity(intent);
                break;
            case R.id.spokenEnglish:

                break;
            case R.id.like:
                if (frag4 == null) {
                    frag4 = new Frag4();
                }
                intent = new Intent(this,frag4.getClass());
                startActivity(intent);
                break;
        }
    }
    public void setListView(){
        final Intent intent = new Intent(this,SpeakDataDetails.class);
        ls.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,getTitles()));
        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent.putExtra("article",speakData.get(titles.get(position)));
                intent.putExtra("title",titles.get(position));
                startActivity(intent);
            }
        });
    }
    public Map<String,String> getDatas(){
        speakData.put("How to Spend the Time In College","When september comes, it means the new semester begins. For the students who have finished their high school education, they finally have come to a new stage. Most Chinese students think it is time for them to seek for fun and forget to fulfill themselves. We can better schedule the campus time.The most important thing is to focus on study. As Chinese students have worked so hard to realize the dream of entering university, they sacrifice so much entertainment time, so they may feel free in the college and is easy to lose themselve. It is time for students to learn their major well and then master the skill, so that they can be competitive in the job market.");
        speakData.put("The Number of Average Salary Surprises People","Every year, the average salary will be announced to the public and there is no doubt that people laugh at themselves for they are so far away from these numbers. The data seems to be ridiculous for most people, but as a matter of fact, it shows that the gap between the rich and the poor is bigger and bigger.China’s economy develops very fast and the world is watching us. Since the policy of Reform and Open carried out, we met many challenges and chances. A lot of people seize the chance and earn a lot o f money. These rich people use their first money to invest other projects and become the millionaires. While for some people who just do their own jobs and earn the basic money, they do not earn much money. ");
        speakData.put("Numerous Beauties In Ukraine","In China, since the policy of family plan carried out, our large growth of population has slowed down. According to the research, China will have 30million bachelors, which means boys will have great pressure to find a wife. While in Ukraine, the situation is opposite, the number of girls surpasses boys. People play a joke that Chinese boys can go to Ukraine to seek for the chance.Many years ago, some pictures showing that Chinese boys married Ukraine girls were very popular on the Internet. The boys were homely looking while the girls were so pretty. People started to pay a lot of attention on this country. Indeed, there were so many pretty girls and the proportion of gender loses balance. As a result, many girls can’t find boyfriends.");
        speakData.put("Taylor’s Got In Trouble","The world’s hottest star must be Taylor Swift. She not only has many hot songs, but the most important thing is that she is talented in composing songs. As this girl gets famous, every single word she said on the Internet can be caught by the media and it is easy to push herself in the trouble.");
        speakData.put("Restarted After the Car Crash","Many years ago, the TV series The Legend of Sword and Fairy was a great success and people started to know the protagonist Hu Ge. He was so young and handsome, many girls were so crazy about him. As a raising star, unfortunately, he had a car accident and was injured badly. His face was disfigured, which meant that he would likely to end his career. The god will always test his people by giving them some disasters. As for Hu Ge he did not give up and spent a year to do the plastic surgery. Finally, he recovered but still a long way to go for full recovery. Many people had forgot him for his disappearance. What’s more, young stars took the place of him. But Hu shot the film from scratch and he began to focus a lot on acting. Finally, his effort paid off and proved himself. ");
        speakData.put("How to Fit In the Group","When students go into college, they need to join in the group, because there is no way for them to live alone. They have new classmates and roommates. How to fit in the group is the main problem for them to solve. A successful student can handle it well.To fit in the group, the students must have the collective consciousness. As most students are only child in the family, so they don’t know how to get along well with their roommates. They always notice themselves and ignore others’ feelings. But as they live in a dormitory, they need to realize these. Only in this way can make everybody live in a harmonious environment.");
        speakData.put("Indian Populatioin Will Surpass China’s","It is known to all that China has the largest population in the world. The large population brings many problems. The gap between the rich and the poor becomes more serious. But since the policy of family plan, the growth is slow down. It has been predicted that India’s population will surpass China in the future.");
        speakData.put("The famous Parents","Everybody knows the famous football player David Beckham that he not only has a beautiful and successful wife, but also has three handsome sons and a lovely daughter. Though he has retired, he is busy all the time. David and his wife put a lot of attention to their family, no matter how successful they are.");
        speakData.put("Five Sentences to Hurt Children","It is said that parents are the children’s best teachers. Indeed, childen spend most of the time to stay with parents, so they imitate their habits. So the good parents have their ways to educate children, while some hurt their kids by saying the following sentences. Such the following five.");
        speakData.put("The Fathers That Hurting Their Babies","In Chinese classic family, the young mothers and fathers need to go to work everyday and the job of taking care of the children belongs to the grandparents. So when a foreign man goes to the kindergarten to pick up his child, the old people will very surprise and think he is not working. Actually, foreign fathers are willing to focus more attention to babies while Chinese fathers ignore their kids all the time.");
        speakData.put("The Job Options","For college students, when the graduate season comes, it is time for them to think about their future and figure out what kind of job they want to work on. Some students choose to work in the company while others decide to start a business on their own. Both options have their advantages.");
        speakData.put("How Character Influences People","It is known to all that women live longer than men generally, because men are under much pressure than women, but a research found that character also had influence on life span. There were over 200 people who were older than 100 took the experiment and the result showed that people who were outgoing lived longer.");
        speakData.put("Chinese Blind Date","Chinese parents are considerated to be the most responsible persons in the world, they take care of the kids all the time, even they have already grown up and been adults. Unlike the western parents, the kids need to move out and to be independent after 18. Chinese parents even interfere their children’ marriage.");
        speakData.put("Love and Age","People always play the joke that love is nothing to do with the age, weight and height. But it is normal for an older man to fall in love with the a young girl, while it is criticized for an older woman to fall in love with a young boy. As far as I am concerned, love is equal in front of two people who love each other.");
        speakData.put("The Meaning of Earning Money","There is no doubt that money is of great importance. We need money to support our lives and realize our dreams. For our parents, they save as much money as possible, while young generation trends to spend money for fun. In my opinion, the meaning of earning money is to improve life standard.");
        speakData.put("American Soccer","American people are crazy about sports, basketball, baseball and football are very popular there. Most of them take exercise everyday, there is no doubt that sports have been part of their lives. But soccer as the most popular sport around the world, meets the awkward situation in the United States.");
        return speakData;
    }
    public List<String> getTitles(){
        titles.add("How to Spend the Time In College");
        titles.add("The Number of Average Salary Surprises People");
        titles.add("Numerous Beauties In Ukraine");
        titles.add("Taylor’s Got In Trouble");
        titles.add("Restarted After the Car Crash");
        titles.add("How to Fit In the Group");
        titles.add("Indian Populatioin Will Surpass China’s");
        titles.add("The famous Parents");
        titles.add("Five Sentences to Hurt Children");
        titles.add("The Fathers That Hurting Their Babies");
        titles.add("The Job Options");
        titles.add("How Character Influences People");
        titles.add("Chinese Blind Date");
        titles.add("Love and Age");
        titles.add("The Meaning of Earning Money");
        titles.add("American Soccer");
        return titles;
    }
}