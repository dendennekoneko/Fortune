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
	private static final long serialVersionUID = 1L;					//�V���A���C�[�[�V�����G���[��������邽�߃o�[�W�����錾
	
	Button b,b2,fortune;															//�ϐ��錾�����\�b�h�̊O�ɏ������ƂŁAActionPerform���\�b�h�ŃC���X�^���X���Ăяo����
	Label l_b,l_kiken,l_seibetsu,l_cmb,l_fortune,l_fortune2,l_fortune3,l_fortune4;
	JRadioButton r_man,r_women;
	ButtonGroup r_group;															//�{�^���O���[�v�����ƃ��W�I�{�^���ň�̃{�^���̂ݑI���ł���悤�ɂł���
	String seibetsu = "";																				
	String kiken = "";
	String combo_word="";
	JComboBox<String> jcombo;
	String[] str_cmb = {"","��","��","�P","��","��","�{","��","�w","��","��"};			//13����
	Omikuji omi = new Omikuji();												//���݂����C���X�^���X(�����_�������p)����
	
	//���C�����\�b�h
	public static void main(String[] args) {
		Fortune r = new Fortune();									//�t���[���C���X�^���X�쐬(JFrame�^�ɂ��Ȃ��Ă����̏��x��Ȃ�)
		r.setTitle("100%������^���̐l");
		r.setBounds(400,300,500,370);										//setBounds�ŕ\���ʒu�Ƒ傫���w��
	    r.setBackground(new Color(140,236,235));					//�w�i�F�w��
		r.setVisible(true);															//�t���[���L����(��^���Ƃ��Ċo����)
	}
	
	Fortune(){																		//�R���X�g���N�^��ʏ������
		getContentPane().setLayout(null);								//�p�[�c�z�u�ݒ�(�s�v����)
		partsInitialize();																//�������@
		partsSet();																		//�������A
		partsLayout();																	//�������B
		listenerSet();																	//�������C
		addWindowListener(new WinAda());								//�E�B���h�E������Ƃ��̏����B���\�b�h�܂ߒ�^���Ƃ��Ċo���邱��
	}	
	
	class WinAda extends WindowAdapter {
	    public void windowActivated(WindowEvent e) {
	        System.out.println("windowActivated");
	    }
	}
	
	public void partsInitialize() {												//�p�[�c�𐶐��A�����l��ݒ肷��
		l_kiken = new Label("�D���ȕ���I�����Ă�������");
		b = new Button("�댯�ȃ{�^��");
		b2 = new Button("���S�ȃ{�^��");
		l_b = new Label("");
		l_seibetsu = new Label("���ʂ�I�����Ă�������");
		r_man = new JRadioButton("�j��");
		r_women = new JRadioButton("����");
		l_cmb = new Label("�D���ȕ�����I��ł�������");
		r_group = new ButtonGroup();										//�{�^���O���[�v�̓��W�I�{�^���ɂ͕K�{
		jcombo = new JComboBox<String>(str_cmb);				//�z���}��
		fortune = new Button("�肤");
		l_fortune = new Label("");												//���ʔ��\�p���x��
		l_fortune2 = new Label("");	
		l_fortune3 = new Label("");	
		l_fortune4 = new Label("");	
	}
	
	public void partsSet() {														//�p�[�c�����ԂɃt���[���ɔz�u����
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
	
	public void partsLayout() {												//�p�[�c�̔z�u���蓮�ݒ�
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
	
	public void listenerSet() {													//���X�i�[�ݒ�p���\�b�h
		b.addActionListener(this);
		b2.addActionListener(this);
		fortune.addActionListener(this);
		r_man.addItemListener(this);
		r_women.addItemListener(this);
		jcombo.addItemListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {					//�{�^���������ꂽ���̏���(ActionListener�̃I�o���C)
		if(e.getSource()==b) {
			kiken = "�댯";
			l_b.setText("�댯�ȃ{�^�����I������܂����B");
		}else if(e.getSource()==b2) {
			kiken = "���S";
			l_b.setText("���S�ȃ{�^�����I������܂����B");
		}else if(e.getSource()==fortune) {
			fortune();
		}
	}
	
	public void itemStateChanged(ItemEvent i) {					//���W�I�{�^���������ꂽ���̏���(ItemListener�̃I�o���C)
		if(i.getSource()==r_man) {
			seibetsu = "�j��";
		}else if(i.getSource()==r_women) {
			seibetsu = "����";
		}else if(i.getSource()==jcombo) {
			combo_word = (String) jcombo.getSelectedItem();
		}
	}
	
	public boolean imputOk() {												//�u�肤�v���s�O�ɓ��͘R�ꂪ�Ȃ����`�F�b�N
		if(kiken!="" && combo_word!="" && seibetsu!="") {
			return true;
		}else {
			return false;
		}
	}
	
	public void fortune() {														//�肢�p�����_������
		if(imputOk()) {
			omi.random();
			omi.comboSelect();
			l_fortune.setText("���Ȃ��́A"+omi.places1+"�@�ŉ^���̐l�ɏo��܂��B");
			l_fortune2.setText("����́A"+omi.describe1+"�@"+omi.job1+"�@�ł��B");
			l_fortune3.setText("���Ȃ������ā@"+omi.gesture1+"�@�B");
			l_fortune4.setText("����Ȏ����Ȃ��́@"+omi.action1+"�@�Ƃ悢�ł��傤�B�������A���܂��B");
		}else {
			l_fortune.setText("�����͂̍��ڂ�����܂��B");
		}
	}
	
	class Omikuji {
		Random r = new Random();
		String[] places = {"�}����","�m����","�J�t�F","�d�ԓ�","�Ƃ̑O","�R���r�j","�l��","�P���^�b�L�[","�܏��H","�G���x�[�^�[��","�g���l���̐^��","TSUTAYA","�ЂƋC�̂Ȃ�����","�a�@"};		//�ꏊ
		String[] gesture = {"���΂݂����Ă��܂�","�ڂ����J���Ă��܂�","���΂��Ă��܂�","�܂𗬂��Ă��܂�","�Ƃ茾��ꂢ�Ă��܂�","�����Ă��܂�","����Ɖ���I�ƌ����Ă��܂�","���������߂Ă��܂�","�x���Ă��܂�","�J�����������Ă��܂�","�Q�Ă��܂�"};		//"���S��"�d���P�P��
		String[] gesture_kiken = {"�������΂��Ă��܂�","���΂��Ă��܂�","�܂𗬂��Ă��܂�","�Ƃ茾��ꂢ�Ă��܂�","�u�`�؂�Ă��܂�","KISS�炵�Ă��܂�","���������߂Ă��܂�","�������Ă��܂�","���ɂ����ɂ����ł�","�J�����������Ă��܂�","�h�r�Ȗڂ������Ă��܂�",};		//"�댯��"�d���P�P��
		String[] describe = {"�z�Ƃ���","�������̂���","�Ј����̂���","�����ȑԓx��","�{���z�̂悤��","�����ɂ��₩�܂�������","�����ڂ�����","�D�����Ă���","�㏸�u����","�L���s�L���s����","�L�����L�������Ă���","���ʂĂ�","�L�m�R����","��������","�����͎m�̂悤��","�b�̂悤�Ȗڂ�����"};
		String[] job = {"�Ǝ�","�w��","�V�l","�}�b�`��","���E","���t","�C��","������","���Ȃ��̈�Ԍ����Ȑ�����","48�n�A�C�h��","�A�C�h���I�^�N","�K�b�L�[","Youtuber","���K�u�t","�G���W�j�A"};														//�j���p�P�V��
		String[] job_women = {"�Ǝ�","�w��","�V�l","�}�b�`��","���E","���t","�V��","������","���Ȃ��̈�Ԍ����Ȑ�����","�W���j�[�Y�n�A�C�h��","�A�C�h���I�^�N","GACKT","Youtuber","�W���C���X�g���N�^�[","�G���W�j�A"};										//�����p�P�V��
		String[] action = {"���A����","�΂�������","�E�B���N����","�x��","���B�肷��","���U��","�m���Ղ肷��","�̂�","����","�����ɋ���"};
		String places1="",gesture1="",describe1="",job1="",action1="";
		
		public void random() {														//���ʂ������_���Ő�������B�����ꂽ�{�^���ɂ���Ďg�p���镶���񂪕ς��
			int r_places = r.nextInt(places.length);
			int r_gesture = r.nextInt(gesture.length);
			int r_describe = r.nextInt(describe.length);
			int r_job = r.nextInt(job.length);
			places1 = places[r_places];
			if(kiken == "���S") {
				gesture1 = gesture[r_gesture];
			}else {
				gesture1 = gesture_kiken[r_gesture];
			}
			describe1 = describe[r_describe];
			if(seibetsu == "�j��") {
				job1 = job[r_job];
			}else {
				job1 = job_women[r_job];
			}
		}
		public void comboSelect() {												//�R���{�{�b�N�X�őI�����ꂽ���ڂɑΉ�����action�����ʂɕ\�������
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
