import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JRadioButton;

public class Fortune extends JFrame implements ActionListener,ItemListener{
	/*
	 */
	private static final long serialVersionUID = 1L;					//シリアライゼーションエラーを回避するためバージョン宣言
	
	Button b,b2,fortune;															//変数宣言をメソッドの外に書くことで、ActionPerformメソッドでインスタンスを呼び出せた
	Label l_b,l_kiken,l_seibetsu,l_cmb,l_fortune,l_fortune2,l_fortune3,l_fortune4;
	JRadioButton r_man,r_women;
	ButtonGroup r_group;															//ボタングループを作るとラジオボタンで一つのボタンのみ選択できるようにできる
	String seibetsu = "";																				
	String kiken = "";
	String combo_word="";
	JComboBox<String> jcombo;
	String[] str_cmb = {"","愛","詩","輝","魔","九","怒","成","努","力","漢"};			//13項目
	Omikuji omi = new Omikuji();												//おみくじインスタンス(ランダム処理用)生成
	
	//メインメソッド
	public static void main(String[] args) {
		Fortune r = new Fortune();									//フレームインスタンス作成(JFrame型にしなくても今の所支障なし)
		r.setTitle("100%当たる運命の人");
		r.setBounds(400,300,500,370);										//setBoundsで表示位置と大きさ指定
	    r.setBackground(new Color(140,236,235));					//背景色指定
		r.setVisible(true);															//フレーム有効化(定型文として覚える)
	}
	
	Fortune(){																		//コンストラクタ画面初期状態
		getContentPane().setLayout(null);								//パーツ配置設定(不要かも)
		partsInitialize();																//初期化①
		partsSet();																		//初期化②
		partsLayout();																	//初期化③
		listenerSet();																	//初期化④
		addWindowListener(new WinAda());								//ウィンドウを閉じたときの処理。メソッド含め定型文として覚えること
	}	
	
	class WinAda extends WindowAdapter {
	    public void windowActivated(WindowEvent e) {
	        System.out.println("windowActivated");
	    }
	}
	
	public void partsInitialize() {												//パーツを生成、初期値を設定する
		l_kiken = new Label("好きな方を選択してください");
		b = new Button("危険なボタン");
		b2 = new Button("安全なボタン");
		l_b = new Label("");
		l_seibetsu = new Label("性別を選択してください");
		r_man = new JRadioButton("男性");
		r_women = new JRadioButton("女性");
		l_cmb = new Label("好きな文字を選んでください");
		r_group = new ButtonGroup();										//ボタングループはラジオボタンには必須
		jcombo = new JComboBox<String>(str_cmb);				//配列を挿入
		fortune = new Button("占う");
		l_fortune = new Label("");												//結果発表用ラベル
		l_fortune2 = new Label("");	
		l_fortune3 = new Label("");	
		l_fortune4 = new Label("");	
	}
	
	public void partsSet() {														//パーツを順番にフレームに配置する
		getContentPane().add(l_kiken);
		getContentPane().add(b);
		getContentPane().add(b2);
		getContentPane().add(l_b);
		getContentPane().add(l_seibetsu);
		getContentPane().add(r_man);
		getContentPane().add(r_women);
		getContentPane().add(l_cmb);
		getContentPane().add(jcombo);
		r_group.add(r_man);
		r_group.add(r_women);
		getContentPane().add(fortune);
		getContentPane().add(l_fortune);
		getContentPane().add(l_fortune2);
		getContentPane().add(l_fortune3);
		getContentPane().add(l_fortune4);
	}
	
	public void partsLayout() {												//パーツの配置を手動設定
		l_kiken.setBounds(160,10,180,20);
		b.setBounds(90,30,150,20);
		b2.setBounds(230,30,150,20);
		l_b.setBounds(160,55,200,20);
		l_seibetsu.setBounds(160,80,200,20);
		r_man.setBounds(160,95,80,30);
		r_women.setBounds(260,95,80,30);
		l_cmb.setBounds(160,130,180,30);
		jcombo.setBounds(210,160,50,20);
		fortune.setBounds(200,200,70,20);
		l_fortune.setBounds(60,230,600,20);
		l_fortune2.setBounds(60,250,600,20);
		l_fortune3.setBounds(60,270,600,20);
		l_fortune4.setBounds(60,290,600,20);
	}
	
	public void listenerSet() {													//リスナー設定用メソッド
		b.addActionListener(this);
		b2.addActionListener(this);
		fortune.addActionListener(this);
		r_man.addItemListener(this);
		r_women.addItemListener(this);
		jcombo.addItemListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {					//ボタンが押された時の処理(ActionListenerのオバライ)
		if(e.getSource()==b) {
			kiken = "危険";
			l_b.setText("危険なボタンが選択されました。");
		}else if(e.getSource()==b2) {
			kiken = "安全";
			l_b.setText("安全なボタンが選択されました。");
		}else if(e.getSource()==fortune) {
			fortune();
		}
	}
	
	public void itemStateChanged(ItemEvent i) {					//ラジオボタンが押された時の処理(ItemListenerのオバライ)
		if(i.getSource()==r_man) {
			seibetsu = "男性";
		}else if(i.getSource()==r_women) {
			seibetsu = "女性";
		}else if(i.getSource()==jcombo) {
			combo_word = (String) jcombo.getSelectedItem();
		}
	}
	
	public boolean imputOk() {												//「占う」実行前に入力漏れがないかチェック
		if(kiken!="" && combo_word!="" && seibetsu!="") {
			return true;
		}else {
			return false;
		}
	}
	
	public void fortune() {														//占い用ランダム処理
		if(imputOk()) {
			omi.random();
			omi.comboSelect();
			l_fortune.setText("あなたは、"+omi.places1+"　で運命の人に出会います。");
			l_fortune2.setText("相手は、"+omi.describe1+"　"+omi.job1+"　です。");
			l_fortune3.setText("あなたを見て　"+omi.gesture1+"　。");
			l_fortune4.setText("そんな時あなたは　"+omi.action1+"　とよいでしょう。恋が成就します。");
		}else {
			l_fortune.setText("未入力の項目があります。");
		}
	}
	
	class Omikuji {
		Random r = new Random();
		String[] places = {"図書館","洋服屋","カフェ","電車内","家の前","コンビニ","浜辺","ケンタッキー","袋小路","エレベーター内","トンネルの真ん中","TSUTAYA","ひと気のない公園","病院"};		//場所
		String[] gesture = {"微笑みかけています","目を見開いています","爆笑しています","涙を流しています","独り言を呟いています","驚いています","やっと会えた！と言っています","助けを求めています","踊っています","カメラを向けています","寝ています"};		//"安全な"仕草１１件
		String[] gesture_kiken = {"這いつくばっています","爆笑しています","涙を流しています","独り言を呟いています","ブチ切れています","KISS顔しています","助けを求めています","興奮しています","今にも死にそうです","カメラを向けています","ドＳな目を向けています",};		//"危険な"仕草１１件
		String[] describe = {"凛とした","清潔感のある","威圧感のある","横柄な態度の","ボロ布のような","いかにもやかましそうな","遠い目をした","泥酔している","上昇志向の","キャピキャピした","キョロキョロしている","疲れ果てた","キノコ頭の","髪が無い","金剛力士のような","獣のような目をした"};
		String[] job = {"業者","学生","老人","マッチョ","無職","恩師","海女","同級生","あなたの一番嫌いな政治家","48系アイドル","アイドルオタク","ガッキー","Youtuber","ヨガ講師","エンジニア"};														//男性用１７件
		String[] job_women = {"業者","学生","老人","マッチョ","無職","恩師","坊主","同級生","あなたの一番嫌いな政治家","ジャニーズ系アイドル","アイドルオタク","GACKT","Youtuber","ジムインストラクター","エンジニア"};										//女性用１７件
		String[] action = {"挨拶する","笑いかける","ウィンクする","踊る","自撮りする","手を振る","知らんぷりする","歌う","叫ぶ","足早に去る"};
		String places1="",gesture1="",describe1="",job1="",action1="";
		
		public void random() {														//結果をランダムで生成する。押されたボタンによって使用する文字列が変わる
			int r_places = r.nextInt(places.length);
			int r_gesture = r.nextInt(gesture.length);
			int r_describe = r.nextInt(describe.length);
			int r_job = r.nextInt(job.length);
			places1 = places[r_places];
			if(kiken == "安全") {
				gesture1 = gesture[r_gesture];
			}else {
				gesture1 = gesture_kiken[r_gesture];
			}
			describe1 = describe[r_describe];
			if(seibetsu == "男性") {
				job1 = job[r_job];
			}else {
				job1 = job_women[r_job];
			}
		}
		public void comboSelect() {												//コンボボックスで選択された項目に対応したactionが結果に表示される
			int combo_length = 0;
			for(String s : str_cmb) {
				if(s == combo_word) {
					action1 = action[combo_length-1];	
					combo_length = 0;
				}
				combo_length++;
			}
		}
	}
	
}
