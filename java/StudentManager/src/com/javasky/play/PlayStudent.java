package com.javasky.play;

import java.util.Scanner;

import com.javasky.dao.StudentDao;
import com.javasky.pojo.Student;

public class PlayStudent {
	/**
	 *�������н������£�
	 *��ӭʹ��ѧԱ����ϵͳ��(1)����ѧԱ.(2)�޸ĳɼ�.(3)ɾ��ѧԱ.
	 *				 (4)�ɼ�����.(5)��ʾ����ѧ����Ϣ.(6)�˳�����
	 *ѡ��(1)�������������ѧԱ������������Java�ɼ�,�����밴1��ȡ���밴0��ϵͳ����������ʾ��Ϣ���������ɹ����û�ȡ����.
     *ѡ��(2)�������������Ҫ�޸ĵ�ѧ��ID��������Ҫ�޸ĵ�ѧ���ɼ��������밴1��ȡ���밴0��ϵͳ����������ʾ��Ϣ�����޸ĳɹ���ѧ�������ڣ��������Ϸ����û�ȡ����.
     *ѡ��(3)�������������Ҫɾ����ѧ��ID��ɾ���밴1��ȡ���밴0��ϵͳ����������ʾ��Ϣ����ɾ���ɹ���ѧ�������ڣ��û�ȡ����.
     *ѡ��(4)����������շ����Ӹߵ�������ϵͳ����������ʾ��Ϣ����������ɣ�.
	 *ѡ��(5)�������������ʾ����ѧ����Ϣ����ʽΪ��Xxx 60��. Yyy70��.
	 *ѡ��(6)�������������ʾ��ллʹ��.�˳�ϵͳ.
	 */
	public static void main(String[] args) {
		Student student = new Student();
		StudentDao studentdao = new StudentDao();
		boolean  isOver = false;//�����Ƿ��˳�����
		int cmd;//�����û��Ĺ���ѡ��
		int save;//�Ƿ񱣴�
		int id;
		Scanner s = new Scanner(System.in);
		System.out.println("��ӭʹ��ѧԱ����ϵͳ");
		while(!isOver){
			System.out.println("1.����ѧԱ;2.�޸ĳɼ�;3.ɾ��ѧԱ;4.�ɼ�����;5.��ʾ����ѧ����Ϣ;6.�˳�����;");
			cmd = s.nextInt();
			switch(cmd){
			case 1:
				System.out.println("������ѧԱID��");
				student.setId(s.nextInt());
				System.out.println("������ѧԱ������");
				student.setName(s.next());
				System.out.println("������ѧԱ�ɼ���");
				student.setScore(s.nextInt());
				System.out.println("�����밴1��ȡ���밴0��");
				save= s.nextInt();
				if(save==1){
					if(studentdao.save(student)){
						System.out.println("�����ɹ�");
					}else{
						System.out.println("����ʧ��");
					}
				}else{
					System.out.println("�û�ȡ��");
				}
				break;
			case 2:
				System.out.println("������Ҫ�޸ĵ�ѧ��ID:");
				id = s.nextInt();
				while(true){
					if(!studentdao.selectId(id)){
						System.out.println("���޴��ˣ�����������");
					}else{
						break;
					}
				}
				student.setId(id);
				System.out.println("������Ҫ�޸ĵ�ѧ������");
				student.setName(s.next());
				System.out.println("������Ҫ�޸ĵ�ѧ���ɼ�");
				student.setScore(s.nextInt());
				System.out.println("�����밴1��ȡ���밴0");
				cmd=s.nextInt();
				if(cmd==1){
					if(studentdao.update(student)){
						System.out.println("�޸ĳɹ�");
					}else{
						System.out.println("�޸�ʧ��");
					}
				}else{
					System.out.println("�û�ȡ��");
				}
				break;
			case 3:
				System.out.println("������Ҫɾ����ѧ��ID");
				id = s.nextInt();
				while(true){
					if(!studentdao.selectId(id)){
						System.out.println("���޴��ˣ�����������");
					}else{
						break;
					}
				}
				System.out.println("ɾ���밴1��ȡ���밴0");
				cmd=s.nextInt();
				if(cmd==1){
					if(studentdao.delete(id)){
						System.out.println("ɾ���ɹ�");
					}else{
						System.out.println("ɾ��ʧ��");
					}
				}else{
					System.out.println("�û�ȡ��");
				}
				break;
			case 4:
				studentdao.sort();
				System.out.println("�������!");
				break;
			case 5:
				studentdao.select();
				break;
			case 6:
				isOver=true;
				s.close();
				System.out.println("ллʹ�ã��������˳�");
				break;
			default:
				System.out.println("�޴˹���");
				break;
			}
		}
	}
}
