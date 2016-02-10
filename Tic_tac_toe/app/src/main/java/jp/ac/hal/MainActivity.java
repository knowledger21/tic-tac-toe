package jp.ac.hal;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

	private int count;
	private boolean isGame;
	CustomImageView block[][] = new CustomImageView[3][3];
	Button replayBtn;
	TextView resultText;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        count = 1; //�Q�[���J�E���g�@max9�ŏI��
        isGame = true; //�Q�[���̏I�n���f fales���Q�[���J�n�O�@true���Q�[���J�n
        replayBtn = (Button)findViewById(R.id.replayBtn);
        resultText = (TextView)findViewById(R.id.resultText);
        replayBtn.setVisibility(View.INVISIBLE);
        resultText.setText("");
        
        //�C���X�^���X����
        block[0][0]=(CustomImageView)findViewById(R.id.imageView1);
        block[0][1]=(CustomImageView)findViewById(R.id.imageView2);
        block[0][2]=(CustomImageView)findViewById(R.id.imageView3);
        block[1][0]=(CustomImageView)findViewById(R.id.imageView4);
        block[1][1]=(CustomImageView)findViewById(R.id.imageView5);
        block[1][2]=(CustomImageView)findViewById(R.id.imageView6);
        block[2][0]=(CustomImageView)findViewById(R.id.imageView7);
        block[2][1]=(CustomImageView)findViewById(R.id.imageView8);
        block[2][2]=(CustomImageView)findViewById(R.id.imageView9);
        
        //����
        for(int i = 0; i<3; i++){
        	for (int j = 0; j < 3; j++) {
				block[i][j].setImageResource(R.drawable.brank);
			}
        }
        
        //�N���b�N���̏���
        for(int i = 0; i<block.length; i++){
        	for (int j = 0; j < block[i].length; j++) {
				block[i][j].setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						blockClick(v);
						
					}
				});
			}
        }
        
        replayBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				resetGame();
			}
		});
        
    }
    
    public void blockClick(View v) {
    	CustomImageView imageView =(CustomImageView) v;
    	if(imageView.getImageResource() == R.drawable.brank){
    		//Toast.makeText(this, "�u�����N�ł�" , Toast.LENGTH_SHORT).show();
    		if(isGame){
	    		chackChange(imageView);
	    		chackGame();
	    		count++;
    		}
    	}
    	
    	
	}
    
    //�܂�΂��f
	public void chackChange(CustomImageView imageView) {
		switch (count%2) {
		case 0:
			imageView.setImageResource(R.drawable.batu);
			break;

		case 1:
			imageView.setImageResource(R.drawable.maru);
			break;
		}
	}
	
	//�������f����ш���
	public void chackGame() {
		//���̏������f
		for(int i =0; i<3; i++){
			if(block[i][0].getImageResource() == block[i][1].getImageResource() &&
					block[i][1].getImageResource() == block[i][2].getImageResource()){
				if(block[i][0].getImageResource() != R.drawable.brank){
					//Toast.makeText(this, "��������I" , Toast.LENGTH_SHORT).show();
					isGame = false;
					replayBtn.setVisibility(View.VISIBLE);
					winSide();
					return;
					
				}
			}
		}
		
		//�c�̏������f
		for(int i =0; i<3; i++){
			if(block[0][i].getImageResource() == block[1][i].getImageResource() &&
					block[1][i].getImageResource() == block[2][i].getImageResource()){
				if(block[0][i].getImageResource() != R.drawable.brank){
					//Toast.makeText(this, "��������I" , Toast.LENGTH_SHORT).show();
					isGame = false;
					replayBtn.setVisibility(View.VISIBLE);
					winSide();
					return;
				}
			}
		}
		
		//�΂߂̏������f
		if(block[0][0].getImageResource() == block[1][1].getImageResource() &&
				block[1][1].getImageResource() == block[2][2].getImageResource()){
			if(block[0][0].getImageResource() != R.drawable.brank){
				//Toast.makeText(this, "��������I" , Toast.LENGTH_SHORT).show();
				isGame = false;
				replayBtn.setVisibility(View.VISIBLE);
				winSide();
				return;
			}
		}
		
		if(block[0][2].getImageResource() == block[1][1].getImageResource() &&
				block[1][1].getImageResource() == block[2][0].getImageResource()){
			if(block[0][2].getImageResource() != R.drawable.brank){
				//Toast.makeText(this, "��������I" , Toast.LENGTH_SHORT).show();
				isGame = false;
				replayBtn.setVisibility(View.VISIBLE);
				winSide();
				return;
			}
		}
		
		//����
		if(count == 9){
			replayBtn.setVisibility(View.VISIBLE);
			resultText.setText(R.string.draw);
			return;
		}
		
	}
	
	//�Q�[���̏���
	public void resetGame() {
		
		for(int i = 0; i<3; i++){
        	for (int j = 0; j < 3; j++) {
				block[i][j].setImageResource(R.drawable.brank);
			}
        }
		
		isGame = true;
		count = 1;
		replayBtn.setVisibility(View.INVISIBLE);
        resultText.setText("");
	}
	
	//�ǂ��炪�����������f
	public void winSide() {
		switch (count%2) {
		case 0:
			resultText.setText(R.string.batuWin);
			break;

		case 1:
			resultText.setText(R.string.maruWin);
			break;
		}
	}

	
    
}
