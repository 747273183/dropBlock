package com.example.dropblock;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dropblock.game.RedBlock;

public class MainActivity extends AppCompatActivity {

    private RedBlock mRedBlock;
    private float mGameHeight;
    private float mRedBlockMoveSpend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      GameSurfaceView gsv=  findViewById(R.id.gsv);
      gsv.setRedBlockCreated(new GameSurfaceView.OnRedBlockCreated() {
          @Override
          public void setRedBlock(RedBlock redBlock, float gameHeight) {
              mRedBlock=redBlock;
              mGameHeight=gameHeight;
              mRedBlockMoveSpend=mRedBlock.getmWidth();
          }
      });



    }

    //左 下 上按钮被单击
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.btn_left:
                mRedBlock.setmX(mRedBlock.getmX()-mRedBlockMoveSpend);
                break;
            case R.id.btn_down:
                mRedBlock.setmY(mGameHeight-mRedBlock.getmHeight());
                break;
            case R.id.btn_right:
//                  mRedBlock.animate().translationX(5000).setDuration(1000).start();

//                mRedBlock.setmX(mRedBlock.getmX()+mRedBlockMoveSpend);
                break;
        }
    }
}
